package com.damilola.lint_playground.utils

import com.android.tools.lint.client.api.JavaEvaluator
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.uast.UAnnotated
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.UParameter

/**
 * Returns true if the given method is annotated with `@Composable`.
 */
val UAnnotated.isComposable: Boolean
    get() = findAnnotation("androidx.compose.runtime.Composable") != null

/**
 * Returns true if the given method is annotated with `@Composable`.
 */
val UAnnotated.isComposablePreview: Boolean
    get() = findAnnotation("import androidx.compose.ui.tooling.preview.Preview") != null


/**
 * If a parameter is a Modifier,
 */

val ModifierNames by lazy(LazyThreadSafetyMode.NONE) { setOf("Modifier", "GlanceModifier") }
val ModifierQualifiedNames by
lazy(LazyThreadSafetyMode.NONE) {
    setOf("androidx.compose.ui.Modifier", "androidx.glance.GlanceModifier")
}

val KtCallableDeclaration.isModifier: Boolean
    get() = ModifierNames.contains(typeReference?.text)

fun UParameter.isModifier(evaluator: JavaEvaluator): Boolean {
    (sourcePsi as? KtParameter)?.let {
        if (it.typeReference?.text in ModifierNames) {
            return true
        }
    }
    // Fall back to more thorough approach
    return ModifierQualifiedNames.any { evaluator.typeMatches(type, it) }
}

val KtCallableDeclaration.isModifierReceiver: Boolean
    get() = ModifierNames.contains(receiverTypeReference?.text)

val KtFunction.modifierParameter: KtParameter?
    get() {
        val modifiers = valueParameters.filter { it.isModifier }
        return modifiers.firstOrNull { it.name == "modifier" } ?: modifiers.firstOrNull()
    }

fun UMethod.modifierParameter(evaluator: JavaEvaluator): UParameter? {
    val modifiers = uastParameters.filter { it.isModifier(evaluator) }
    return modifiers.firstOrNull { it.name == "modifier" } ?: modifiers.firstOrNull()
}