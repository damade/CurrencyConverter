package com.damilola.processor.utils

import com.google.devtools.ksp.isInternal
import com.google.devtools.ksp.isPrivate
import com.google.devtools.ksp.isProtected
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
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
// endregion

// region KSPropertyDeclaration
val KSPropertyDeclaration.isNullable
    get() = type.resolve().isMarkedNullable

val KSPropertyDeclaration.propertyType
    get() = type.resolve().declaration.qualifiedName?.asString() ?: "Any"


fun KSPropertyDeclaration.isAnnotatedWith(annotationName: String): Boolean {
    val propertyType: KSType = type.resolve()
    val typeDeclaration: KSClassDeclaration? = propertyType.declaration as? KSClassDeclaration

    return typeDeclaration?.annotations?.any { annotation ->
        annotation.shortName.asString() == annotationName
    } ?: false
}


fun KSPropertyDeclaration.asClassName(): KSClassDeclaration? {
    val propertyType: KSType = type.resolve()
    return propertyType.declaration as? KSClassDeclaration
}



fun KSPropertyDeclaration.isNonPrimitiveType(): Boolean {
    val resolvedType: KSType = this.type.resolve()
    val declaration = resolvedType.declaration

    if (declaration.qualifiedName == null) return true

    val qualifiedNameString = declaration.qualifiedName!!.asString()

    val primitiveTypes = setOf(
        "kotlin.Boolean",
        "kotlin.Byte",
        "kotlin.Short",
        "kotlin.Int",
        "kotlin.Long",
        "kotlin.Char",
        "kotlin.Float",
        "kotlin.Double",
        "kotlin.String",
    )

    return qualifiedNameString !in primitiveTypes || declaration is KSClassDeclaration
}


// endregion


