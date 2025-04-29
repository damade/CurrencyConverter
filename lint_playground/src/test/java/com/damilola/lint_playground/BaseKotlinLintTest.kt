package com.damilola.lint_playground

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.checks.infrastructure.TestLintTask
import com.android.tools.lint.checks.infrastructure.TestMode
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
abstract class BaseKotlinLintTest : LintDetectorTest() {

    protected val commonStubs =
        arrayOf(
//            kotlin(
//                """
//          package org.junit
//
//          @Target(AnnotationTarget.FUNCTION)
//          annotation class Test
//      """
//                    .trimIndent()
//            ),
            kotlin(
                """
          package org.junit

          @Target(AnnotationTarget.FUNCTION)
          annotation class Test
      """
                    .trimIndent()
            ),
        )

    /**
     * Lint periodically adds new "TestModes" to LintDetectorTest. These modes act as a sort of chaos
     * testing mechanism, adding different common variations of code (extra spaces, extra parens, etc)
     * to the test files to ensure that lints are robust against them. They also make it quite
     * difficult to test against and need extra work sometimes to properly support, so we expose this
     * property to allow tests to skip certain modes that need more work in subsequent PRs.
     */
    open val skipTestModes: Array<TestMode>? = null

    abstract override fun getDetector(): Detector

    abstract override fun getIssues(): List<Issue>

    override fun lint(): TestLintTask {
        val lintTask = super.lint()
        lintTask.allowMissingSdk()
        lintTask.allowCompilationErrors(false)

        skipTestModes?.let { testModesToSkip -> lintTask.skipTestModes(*testModesToSkip) }
        return lintTask
    }
}