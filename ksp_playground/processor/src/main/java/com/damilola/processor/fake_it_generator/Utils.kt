package com.damilola.processor.fake_it_generator

import com.damilola.annotation.fake_generator.FakeIt
import com.damilola.processor.utils.asClassName
import com.damilola.processor.utils.className
import com.damilola.processor.utils.getPrimitiveKClass
import com.damilola.processor.utils.getPrimitiveDefaultValue
import com.damilola.processor.utils.getVisibilityModifier
import com.damilola.processor.utils.isAnnotatedWith
import com.damilola.processor.utils.isNonPrimitiveType
import com.damilola.processor.utils.isNullable
import com.damilola.processor.utils.packageNameString
import com.damilola.processor.utils.propertyType
import com.damilola.processor.utils.toKotlinPoetClassName
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName

private const val LITERAL_FORMAT_SPECIFIER = "%L"
private const val MEMBER_FUNCTION_CALL_FORMAT_SPECIFIER =
    "%M()" // Or a more descriptive name based on its exact usage

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

internal fun KSClassDeclaration.buildFakeFunctions() = FunSpec.builder(name = "fake")
    .returns(returnType = this.toKotlinPoetClassName())
    .addParameters(parameterSpecs = buildFakeParameters())
    .build()


private fun KSClassDeclaration.buildFakeParameters() =
    getAllProperties().asIterable().map { property ->
        val (format, arg) = property.getDefaultValueFromProperty()
        val propertyKClass = property.getPrimitiveKClass()
        val parameterBuilder = if (propertyKClass != null) {
            ParameterSpec.builder(
                name = property.simpleName.asString(),
                type = propertyKClass.asTypeName().copy(nullable = property.isNullable)
            )
        } else {
            val propertyType = property.asClassName()?.toKotlinPoetClassName() ?: Any::class.asTypeName()
            ParameterSpec.builder(
                name = property.simpleName.asString(),
                type = propertyType.copy(nullable = property.isNullable)
            )
        }
        parameterBuilder.defaultValue(format = format, arg).build()
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

        else -> throw IllegalArgumentException("Non-nullable type '$propertyType' for property '$simpleName' requires a default value for @FakeIt.")
    }
}