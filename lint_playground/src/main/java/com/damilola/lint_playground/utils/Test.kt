package com.damilola.lint_playground.utils

import org.jetbrains.uast.UAnnotated

/**
 * Returns true if the given method is annotated with `@Composable`.
 */
val UAnnotated.isTest: Boolean
    get() = findAnnotation(fqName = "org.junit.Test") != null