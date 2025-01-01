package com.damilola.processor.fake_generator

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class FakeGeneratorProcessorProvider: SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return FakeGeneratorProcessor(
            logger = environment.logger,
            codeGenerator = environment.codeGenerator,
        )
    }
}