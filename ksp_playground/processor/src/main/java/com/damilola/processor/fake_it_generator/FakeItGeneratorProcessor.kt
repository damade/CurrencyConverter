package com.damilola.processor.fake_it_generator

import com.damilola.annotation.fake_generator.FakeIt
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.ksp.writeTo
import kotlin.reflect.KClass

class FakeItGeneratorProcessor(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {
    private var invoked = false

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (invoked) return emptyList()
        val symbols = resolver
            .getSymbolsWithAnnotation(annotationName = FakeIt::class.qualifiedName.toString())
            .filterIsInstance<KSClassDeclaration>()

        symbols.forEach { symbol ->
            val file = symbol.generateFakeIt()
            resolver.getNewFiles()
            file.writeTo(
                codeGenerator = codeGenerator,
                aggregating = false,
            )
        }

        invoked = true
        return resolver.getSymbols(kClass = FakeIt::class).toList()
    }

    private fun Resolver.getSymbols(kClass: KClass<*>) =
        this.getSymbolsWithAnnotation(kClass.qualifiedName.orEmpty())
            .filterIsInstance<KSClassDeclaration>()
            .filter(KSNode::validate)
}