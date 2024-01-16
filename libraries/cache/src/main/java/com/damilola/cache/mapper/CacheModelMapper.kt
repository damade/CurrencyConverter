package com.damilola.cache.mapper

interface CacheModelMapper<M, E> {

    fun mapToModel(entity: E): M

    fun mapToEntity(model: M): E

    fun mapListToModel(entity: List<E>): List<M>

    fun mapListToEntity(model: List<M>): List<E>
}