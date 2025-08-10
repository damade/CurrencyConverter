package com.damilola.processor.fake_it_generator

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class FakeItGeneratorProcessorProvider: SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return FakeItGeneratorProcessor(
            logger = environment.logger,
            codeGenerator = environment.codeGenerator,
        )
    }
}