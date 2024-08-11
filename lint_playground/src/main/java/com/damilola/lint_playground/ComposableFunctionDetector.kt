package com.damilola.lint_playground

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.android.tools.lint.detector.api.isKotlin
import com.damilola.lint_playground.utils.LintOption
import com.damilola.lint_playground.utils.OptionLoadingDetector
import com.damilola.lint_playground.utils.isComposable
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtPropertyAccessor
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.kotlin.isKotlin

abstract class ComposableFunctionDetector(vararg options: LintOption) :
    OptionLoadingDetector(*options), SourceCodeScanner {

    final override fun getApplicableUastTypes() = listOf(UMethod::class.java)

    final override fun createUastHandler(context: JavaContext): UElementHandler? {
        if (!isKotlin(context.uastFile?.lang)) return null
        return object : UElementHandler() {
            override fun visitMethod(node: UMethod) {
                if (node.isComposable) {
                    visitComposable(context, node)
                    when (val sourcePsi = node.sourcePsi ?: return) {
                        is KtPropertyAccessor -> {
                            visitComposable(context, node, sourcePsi)
                        }
                        is KtFunction -> {
                            visitComposable(context, node, sourcePsi)
                        }
                    }
                }
            }
        }
    }

    open fun visitComposable(context: JavaContext, method: UMethod) {}

    open fun visitComposable(context: JavaContext, method: UMethod, property: KtPropertyAccessor) {}

    open fun visitComposable(context: JavaContext, method: UMethod, function: KtFunction) {}
}