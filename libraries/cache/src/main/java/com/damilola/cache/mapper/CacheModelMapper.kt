package com.damilola.cache.mapper

interface CacheModelMapper<E, D> {

    fun mapToModel(domain: D): E

    fun mapToDomain(model: E): D

    fun mapListToModel(domain: List<D>): List<E>

    fun mapListToDomain(model: List<E>): List<D>
}