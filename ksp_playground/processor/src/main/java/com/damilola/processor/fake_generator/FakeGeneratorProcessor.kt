package com.damilola.processor.fake_generator

import com.damilola.annotation.fake_generator.GenerateFake
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.validate
import kotlin.reflect.KClass

class FakeGeneratorProcessor(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator,
): SymbolProcessor {
    private var invoked = false

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if(invoked) return emptyList()
        val symbols = resolver
            .getSymbolsWithAnnotation(GenerateFake::class.qualifiedName.toString())
            .filterIsInstance<KSClassDeclaration>()

        symbols.forEach { symbol ->
            val file = codeGenerator.createFakeClassFile(symbol = symbol)
            val builderFunction = symbol.buildFakeFunction()
            resolver.getNewFiles()
            file.write(builderFunction.toByteArray())
            file.close()
        }

        invoked = true
        return resolver.getSymbols(kClass = GenerateFake::class).toList()
    }

    private fun Resolver.getSymbols(kClass: KClass<*>) =
        this.getSymbolsWithAnnotation(kClass.qualifiedName.orEmpty())
            .filterIsInstance<KSClassDeclaration>()
            .filter(KSNode::validate)
}