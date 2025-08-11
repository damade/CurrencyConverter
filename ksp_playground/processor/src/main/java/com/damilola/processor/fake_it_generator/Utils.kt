package com.damilola.processor.fake_it_generator

import com.damilola.annotation.fake_generator.FakeIt
import com.damilola.processor.utils.asClassName
import com.damilola.processor.utils.className
import com.damilola.processor.utils.getVisibilityModifier
import com.damilola.processor.utils.getVisibilityModifierString
import com.damilola.processor.utils.isAnnotatedWith
import com.damilola.processor.utils.isNonPrimitiveType
import com.damilola.processor.utils.isNullable
import com.damilola.processor.utils.packageNameString
import com.damilola.processor.utils.propertiesName
import com.damilola.processor.utils.propertyType
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import java.io.OutputStream

private const val LITERAL_FORMAT_SPECIFIER = "%L"
private const val MEMBER_FUNCTION_CALL_FORMAT_SPECIFIER = "%M()" // Or a more descriptive name based on its exact usage

private val KSClassDeclaration.generatedClassName get() = "${className}FakeIt"

internal fun KSClassDeclaration.generateFakeIt() = FileSpec
    .builder(packageName = packageNameString, fileName = generatedClassName)
    .addType(buildFakeObject())
    .build()

internal fun KSClassDeclaration.buildFakeObject() =
    TypeSpec.objectBuilder(name = generatedClassName)
        .addModifiers(getVisibilityModifier())
        .addFunction(buildFakeFunctions())
        .build()

internal fun KSClassDeclaration.buildFakeFunctions() = FunSpec.builder(name = "data")
    .returns(returnType = this::class)
    .addParameters(parameterSpecs = buildFakeParameters())
    .build()


private fun KSClassDeclaration.buildFakeParameters() =
    getAllProperties().asIterable().map { property ->
        val (format, arg) = property.getDefaultValueFromProperty()
        ParameterSpec.builder(
            name = property.simpleName.asString(),
            type = property::class.asTypeName().copy(nullable = property.isNullable)
        ).defaultValue(
            format = format,
            arg
        ).build()
    }

private fun KSPropertyDeclaration.getDefaultValueFromProperty(): Pair<String, Any?> {
    return when {
        isNullable -> LITERAL_FORMAT_SPECIFIER to null
        !isNonPrimitiveType() -> LITERAL_FORMAT_SPECIFIER to getPrimitiveDefaultValue()
        isAnnotatedWith(annotationName = FakeIt::class.qualifiedName.toString()) -> {
            val classDeclaration = asClassName()
                ?: throw IllegalArgumentException("Cannot determine class declaration for property '$simpleName' of type '$propertyType' annotated with FakeIt.")
            val defaultValuesClass = ClassName(
                classDeclaration.packageNameString,
                "${classDeclaration.generatedClassName}FakeIt"
            )
            MEMBER_FUNCTION_CALL_FORMAT_SPECIFIER to defaultValuesClass.member(simpleName = "data")
        }
        else -> throw IllegalArgumentException("Non-nullable type '$propertyType' for property '$simpleName' requires a default value.")
    }
}


private fun KSPropertyDeclaration.getPrimitiveDefaultValue(): Any = when (propertyType) {
    "kotlin.String" -> "\"\""
    "kotlin.Char" -> "\'\'"
    "kotlin.Int", "kotlin.Long", "kotlin.Short", "kotlin.Byte" -> 0
    "kotlin.Double", "kotlin.Float" -> 0.0
    "kotlin.Boolean" -> false
    else -> throw IllegalArgumentException("Non-nullable type $propertyType requires a default value")
}