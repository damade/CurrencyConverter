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
import com.android.tools.lint.detector.api.endsWith
import com.damilola.lint_playground.utils.Priorities
import com.damilola.lint_playground.utils.isTest
import org.jetbrains.uast.UComment
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.asRecursiveLogString

class TestFunctionCommentDetector : Detector(), SourceCodeScanner {

    companion object {
        private val IMPLEMENTATION = Implementation(
            TestFunctionCommentDetector::class.java,
            Scope.JAVA_FILE_SCOPE
        )

        val ISSUE = Issue.create(
            id = "TestCommentLintCheck",
            briefDescription = "Ensure test comment is restricted to // given, // when, and // then",
            explanation =
            """
                This check makes sure the test comment is only // given, // when, and // then in a test function.
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = Priorities.LOW,
            severity = Severity.ERROR,
            implementation = IMPLEMENTATION,
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>> {
        return listOf(UMethod::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return TestFunctionCommentScanner(context)
    }

    inner class TestFunctionCommentScanner(private val context: JavaContext): UElementHandler() {

        override fun visitMethod(node: UMethod) {
            if (node.isTest && endsWith(context.file.name, "Test.kt") ) {
                val hereWeCome = node.asRecursiveLogString()
                // println(hereWeCome)
                val allComments = node.comments
                val acceptedComments = listOf("// given", "// when", "// then")
                allComments.forEach { comment ->
                    val commentText = comment.text
                    println(commentText)
                    if(!acceptedComments.contains(commentText)) {
                        reportUsage(context = context, comment = comment)
                    }
                }
            }
        }

        private fun reportUsage(context: JavaContext, comment: UComment) {
            context.report(
                issue = ISSUE,
                scope = comment,
                location = context.getLocation(comment),
                message = "Test comment should be restricted to // given, // when, and // then",
                quickfixData = null,
            )
        }
    }
}