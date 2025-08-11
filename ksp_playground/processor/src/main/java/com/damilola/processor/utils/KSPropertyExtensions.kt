package com.damilola.processor.utils

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
import kotlin.reflect.KClass

// region KSPropertyDeclaration
internal val KSPropertyDeclaration.isNullable
    get() = type.resolve().isMarkedNullable

internal val KSPropertyDeclaration.propertyType
    get() = type.resolve().declaration.qualifiedName?.asString() ?: "Any"


internal fun KSPropertyDeclaration.isAnnotatedWith(annotationName: String): Boolean {
    val propertyType: KSType = type.resolve()
    val typeDeclaration: KSClassDeclaration? = propertyType.declaration as? KSClassDeclaration

    return typeDeclaration?.annotations?.any { annotation ->
        annotation.shortName.asString() == annotationName
    } ?: false
}


internal fun KSPropertyDeclaration.asClassName(): KSClassDeclaration? {
    val propertyType: KSType = type.resolve()
    return propertyType.declaration as? KSClassDeclaration
}

internal fun KSPropertyDeclaration.isNonPrimitiveType(): Boolean {
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

    if (qualifiedNameString in primitiveTypes) return false
    return declaration is KSClassDeclaration
}

internal fun KSPropertyDeclaration.getPrimitiveDefaultValue(): Any = when (propertyType) {
    "kotlin.String" -> "\"\""
    "kotlin.Char" -> "\'\'"
    "kotlin.Int", "kotlin.Short", "kotlin.Byte" -> 0
    "kotlin.Long" -> 0L
    "kotlin.Double", "kotlin.Float" -> 0.0
    "kotlin.Boolean" -> false
    else -> throw IllegalArgumentException("Non-nullable type $propertyType requires a default value")
}

internal fun KSPropertyDeclaration.getPrimitiveKClass(): KClass<out Any>? = when (propertyType) {
    "kotlin.String" -> String::class
    "kotlin.Char" -> Char::class
    "kotlin.Int" -> Int::class
    "kotlin.Short" -> Short::class
    "kotlin.Byte" -> Byte::class
    "kotlin.Long" -> Long::class
    "kotlin.Double" -> Double::class
    "kotlin.Float" -> Float::class
    "kotlin.Boolean" -> Boolean::class
    "kotlin.Any" -> Any::class
    else -> null
}

// endregion
