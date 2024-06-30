package com.damilola.config

interface AppsConfig {
    val API_KEY: String
    val BASE_URL: String
    val APOLLO_BASE_URL: String
    val isDebug: Boolean
    val DB_NAME: String
    val applicationIdSuffix: String
    val versionNameSuffix: String
}

object BuildTypeDebug : AppsConfig {
    override val API_KEY: String = "833784589adc2e2db471cdb439a13301"
    override val BASE_URL: String = "https://api.exchangerate.host/"
    override val APOLLO_BASE_URL: String = "https://countries.trevorblades.com/graphql"
    override val isDebug: Boolean = true
    override val DB_NAME: String = "currency_converter_db_debug"
    override val applicationIdSuffix: String = ".debug"
    override val versionNameSuffix: String = "-DEBUG"
}

object BuildTypeRelease : AppsConfig {
    override val API_KEY: String = "833784589adc2e2db471cdb439a13301"
    override val BASE_URL: String = "https://api.exchangerate.host/"
    override val APOLLO_BASE_URL: String = "https://countries.trevorblades.com/graphql"
    override val isDebug: Boolean = false
    override val DB_NAME: String = "currency_converter_db"
    override val applicationIdSuffix: String = ".release"
    override val versionNameSuffix: String = "-RELEASE"
}