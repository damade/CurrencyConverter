package com.damilola.processor.fake_generator

import com.damilola.processor.utils.className
import com.damilola.processor.utils.getVisibilityModifierString
import com.damilola.processor.utils.packageNameString
import com.damilola.processor.utils.propertiesName
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import java.io.OutputStream

internal val KSClassDeclaration.generatedClassName get() = "${className}Fake"

internal fun CodeGenerator.createFakeClassFile(symbol: KSClassDeclaration): OutputStream =
    with(symbol) {
        createNewFile(
            dependencies = Dependencies(false, containingFile!!),
            packageName = packageNameString,
            fileName = generatedClassName,
        )
    }

private fun getDefaultValueFromProperty(property: KSPropertyDeclaration): String {
    val propType = property.type.resolve().declaration.qualifiedName?.asString() ?: "Any"
    val isNullable = property.type.resolve().isMarkedNullable

    return if (isNullable) "null" else when (propType) {
        "kotlin.String" -> "\"\""
        "kotlin.Int", "kotlin.Long", "kotlin.Short", "kotlin.Byte" -> "0"
        "kotlin.Double", "kotlin.Float" -> "0.0"
        "kotlin.Boolean" -> "false"
        else -> throw IllegalArgumentException("Non-nullable type $propType requires a default value")
    }
}

private fun KSClassDeclaration.buildFakeParameter() = getAllProperties().map { property ->
    val propertyType = property.type.resolve().declaration.simpleName.asString()
        .let { if (property.type.resolve().isMarkedNullable) "$it?" else it }
    Triple(
        (property.simpleName.asString()),
        propertyType,
        getDefaultValueFromProperty(property = property)
    )
}

internal fun KSClassDeclaration.buildFakeFunction() = buildString {
    appendLine("package $packageNameString")
    appendLine()
    appendLine()
    appendLine("${getVisibilityModifierString()} class $generatedClassName {")
    appendLine("    companion object {")
    appendLine("        fun data(")
    buildFakeParameter().forEach { (propertyName, propertyType, defaultParameter) ->
        appendLine("            $propertyName: $propertyType = $defaultParameter,")
    }
    appendLine("        ) = ${className}(")
    propertiesName().forEach { propertyName ->
        appendLine("            $propertyName = $propertyName,")
    }
    appendLine("        )")
    appendLine("    }")
    appendLine("}")
}