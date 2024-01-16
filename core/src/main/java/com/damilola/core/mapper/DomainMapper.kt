package com.damilola.core.mapper

interface DomainMapper<D, U> {

    fun mapFromDomain(domain: D): U

    fun mapToDomain(uiModel: U): D

    fun mapFromDomainList(domainModels: List<D>): List<U> {
        return domainModels.mapTo(mutableListOf(), ::mapFromDomain)
    }

    fun mapFromUiList(uiModels: List<U>): List<D> {
        return uiModels.mapTo(mutableListOf(), ::mapToDomain)
    }
}
