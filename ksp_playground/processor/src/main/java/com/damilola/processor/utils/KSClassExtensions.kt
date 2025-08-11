package com.damilola.processor.utils

import com.google.devtools.ksp.isInternal
import com.google.devtools.ksp.isPrivate
import com.google.devtools.ksp.isProtected
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.KModifier


// region KSClassDeclaration
val KSClassDeclaration.className get() = simpleName.asString()
val KSClassDeclaration.packageNameString get() = packageName.asString()
fun KSClassDeclaration.propertiesName() = getAllProperties().map { it.simpleName.asString() }
fun KSClassDeclaration.getVisibilityModifierString() = when {
    isInternal() -> "internal"
    isPrivate() -> "private"
    else -> "public"
}

fun KSClassDeclaration.getVisibilityModifier() = when {
    isPrivate() -> KModifier.PRIVATE
    isInternal() -> KModifier.INTERNAL
    isProtected() -> KModifier.PROTECTED
    else -> KModifier.PUBLIC
}

fun KSClassDeclaration.toKotlinPoetClassName(): ClassName {
    val qualifiedNameStr = this.qualifiedName?.asString()
    if (qualifiedNameStr == null) {
        // This can happen for local classes or anonymous classes,
        // which might not be directly referenceable by qualified name in generated code
        // or for error types. Handle as appropriate for your use case.
        // For example, you might throw an error or return a placeholder like ANY.
        throw IllegalStateException("Cannot get qualified name for KSClassDeclaration: ${this.simpleName.asString()}")
    }
    // KSClassDeclaration.packageName gives you the package name directly
    // KSClassDeclaration.simpleName gives you the simple class name
    return ClassName(this.packageName.asString(), this.simpleName.asString())
}

// endregion

