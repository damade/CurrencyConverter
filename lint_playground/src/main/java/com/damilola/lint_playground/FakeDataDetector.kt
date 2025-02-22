package com.damilola.lint_playground

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.damilola.lint_playground.utils.Priorities
import com.damilola.lint_playground.utils.isComposablePreview
import com.damilola.lint_playground.utils.isTest
import com.damilola.lint_playground.utils.isTrue
import com.intellij.psi.PsiClass
import org.jetbrains.uast.UAnnotated
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.getContainingUClass
import org.jetbrains.uast.getParentOfType

class FakeDataDetector : Detector(), SourceCodeScanner {

    companion object {
        private val IMPLEMENTATION = Implementation(
            FakeDataDetector::class.java,
            Scope.JAVA_FILE_SCOPE
        )

        val ISSUE = Issue.create(
            id = "FakeDataObject",
            briefDescription = "Fake Data should only be used in Preview and Test functions or Test class",
            explanation =
            """
                This check makes sure Fake data class such as *JohnDoeFake.data()*, *JohnDoe.Companion.fake()*,\n 
                and *JohnDoe.fake()* are only used in Test and Compose preview functions.
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = Priorities.NORMAL,
            severity = Severity.ERROR,
            implementation = IMPLEMENTATION,
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>> {
        return listOf(UCallExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return FakeDataScanner(context)
    }

    inner class FakeDataScanner(private val context: JavaContext) : UElementHandler() {

        override fun visitCallExpression(node: UCallExpression) {
            val methodName = node.methodIdentifier?.name ?: return
            val enclosingClass = node.getContainingUClass() ?: return
            val className = enclosingClass.name.orEmpty()

            println("$methodName $className")

            if (isValidFakeData(methodName = methodName, className = className)) {
                val hereWeGo = isNotUsedInValidContext(
                    enclosingClass = enclosingClass,
                    className = className,
                    containingElement = node,
                )
                println(hereWeGo)
                if (isNotUsedInValidContext(
                        enclosingClass = enclosingClass,
                        className = className,
                        containingElement = node,
                    )
                ) {
                    reportUsage(context = context, scope = node.uastParent)
                }
            }
        }

        private fun isValidFakeData(methodName: String?, className: String): Boolean =
            (methodName == "data" && className.endsWith("FakeKt")) || methodName == "fake"

        private fun isNotUsedInValidContext(
            enclosingClass: PsiClass,
            className: String,
            containingElement: UElement?,
        ): Boolean {
            val hasPreviewAnnotationImport =
                enclosingClass.hasAnnotation("import androidx.compose.ui.tooling.preview.Preview")
            val hasTestAnnotationImport = enclosingClass.hasAnnotation("import org.junit.Test")
            val hasPreviewAnnotation = enclosingClass.hasAnnotation("Preview")
            val hasTestAnnotation = enclosingClass.hasAnnotation("Test")
            val isTestClass = className.endsWith("Test")
            val isInsidePreview = containingElement
                ?.getParentOfType(UAnnotated::class.java, true)?.isComposablePreview.isTrue()
            val isInsideTest = containingElement
                ?.getParentOfType(UAnnotated::class.java, true)?.isTest.isTrue()

            return (!hasPreviewAnnotation || !hasPreviewAnnotationImport || !isInsidePreview) &&
                    (!hasTestAnnotation || !hasTestAnnotationImport || !isInsideTest) && !isTestClass
        }

        private fun reportUsage(context: JavaContext, scope: UElement?) {
            context.report(
                issue = ISSUE,
                scope = scope,
                location = context.getLocation(scope),
                message = "Fake data should only be used in functions/classes annotated with @Preview or @Test or class with suffix Test",
                quickfixData = null,
            )
        }
    }
}