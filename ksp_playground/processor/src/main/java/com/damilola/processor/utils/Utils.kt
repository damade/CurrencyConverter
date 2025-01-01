package com.damilola.processor.utils

import com.google.devtools.ksp.symbol.KSClassDeclaration

val KSClassDeclaration.className get() = simpleName.asString()
val KSClassDeclaration.packageNameString get() = packageName.asString()
fun KSClassDeclaration.propertiesName() = getAllProperties().map { it.simpleName.asString() }