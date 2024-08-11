package com.damilola.lint_playground

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Context
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.damilola.lint_playground.utils.Priorities
import com.damilola.lint_playground.utils.isComposable
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UFile
import org.jetbrains.uast.asRecursiveLogString
import org.jetbrains.uast.visitor.AbstractUastVisitor

class ModifierClickablePaddingDetector : Detector(), SourceCodeScanner {

    companion object {
        private val IMPLEMENTATION = Implementation(
            ModifierClickablePaddingDetector::class.java,
            Scope.JAVA_FILE_SCOPE
        )

        val ISSUE = Issue.create(
            id = "ModifierClickablePadding",
            briefDescription = "Clickable Modifier after Padding Modifier",
            explanation =
            """
                This check makes sure *clickable* modifier comes before *padding* modifier.
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = Priorities.NORMAL,
            severity = Severity.ERROR,
            implementation = IMPLEMENTATION,
        )
    }

    override fun beforeCheckFile(context: Context) {
        context as JavaContext
        val uastFile = context.uastFile?.asRecursiveLogString()
        println(uastFile)
//        context.uastFile?.accept(
//            object : AbstractUastVisitor(){
//                override fun visitCallExpression(node: UCallExpression): Boolean {
//                    if(node.isComposable) {
//                        context.report(
//                            ISSUE,
//                            node,
//                            context.getLocation(node),
//                            "Put clickable modifier before padding modifier",
//                        )
//
//                    }
//                    return super.visitCallExpression(node)
//                }
//            }
//        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>> {
        return listOf(UFile::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return ModifierScanner(context)
    }

    inner class ModifierScanner(private val context: JavaContext): UElementHandler() {

        override fun visitFile(node: UFile) {
            if (node.isComposable) {
                val modifier = node.uastParent
                context.report(
                    issue = ISSUE,
                    scope = modifier,
                    location = context.getLocation(modifier),
                    message = "Clickable Modifier after Padding Modifier"
                )
            }
        }
    }
}