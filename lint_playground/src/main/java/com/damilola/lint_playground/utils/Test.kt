package com.damilola.lint_playground.utils

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope.JAVA_FILE
import com.android.tools.lint.detector.api.Scope.TEST_SOURCES
import com.android.tools.lint.detector.api.Severity
import com.damilola.lint_playground.TestMethodNameDetector
import com.intellij.psi.PsiClass
import org.jetbrains.uast.UAnnotated
import java.util.EnumSet

/**
 * Returns true if the given method is annotated with `@Test`.
 */
val UAnnotated.isTest: Boolean
    get() = findAnnotation(fqName = "org.junit.Test") != null

val PsiClass.isTestClass: Boolean
    get() = hasAnnotation("Test")

val String.isTestClass: Boolean
    get() = endsWith("Test")

internal fun issue(
    id: String,
    briefDescription: String,
    explanation: String,
): Issue = Issue.create(
    id = id,
    briefDescription = briefDescription,
    explanation = explanation,
    category = Category.TESTING,
    priority = Priorities.NORMAL,
    severity = Severity.ERROR,
    implementation = Implementation(
        TestMethodNameDetector::class.java,
        EnumSet.of(JAVA_FILE, TEST_SOURCES),
    ),
)