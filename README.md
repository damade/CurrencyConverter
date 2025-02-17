# CurrencyConverter [Under Construction]
A simple app that converts the value of a currency to other currencies from [ExchangeRate](https://exchangerate.host/) and [Countries GraphQL API](https://countries.trevorblades.com/graphql) for the GraphQl APIs to get the list of currencies with their country flags; built to achieve Clean Architecture and Modularization via MVVM pattern (architecture), Kotlin Flow, Coroutines, RxJava, Compose. 

## Link To Project
[My Currency Converter](https://github.com/damade/CurrencyConverter) inspired by [@theseuntaylor](https://github.com/theseuntaylor)

## Prerequisite
To build this project, you require:
- Android Studio 
- Gradle 8.7.0
- Kotlin 2.0.0
- Android Gradle Plugin 8.5.0

## Features
* Clean Architecture with MVVM (Uni-directional data model with UI states)
* GraphQl
* Rest API
* Multimodule
* Jetpack Libraries such as Navigation and others
* View components
* Kotlin Coroutines with Flow
* RxJava with Relays
* Dagger Hilt
* Kotlin Gradle DSL
* Compose (Compose with Flow, RxJava Observable, Navigation)
* Unit Test
* Plugin for managing dependencies
* KSP experimentation to generate basic fakes for tests and compose previews
* Gradle tasks experimentation to create feature module based on project structure

## Helpful Commands in project
* To generate a feature module run: <br>
    `./gradlew generateFeatureModule -PfeatureName="FeatureName" -PtemplateName=featureTemplate`

## Future Tech Roadmap
In no particular order
* Add in-memory and local cache for rate particular to currencies to reduce Api request calls conversion
* Setup Ktlint on the project
* Migrate to KSP and K2 compiler.
* Use the Konsist library to write rules about the architecture and class conventions.
* Set up macro benchmark and fix memory leaks. 
* Replace BuildSrc, migrate to using version catalogs and update App Dependencies.
* Add screenshot and UI/integration testing, 

## Product Roadmap
* Add widget
* Add live rates (5) and a view more screen.
* Add favourite currencies to have on the live rate list and potentially have notifications sent based on a price drop.  
* Add a local profile screen with the possibility of having a remote profile in the future.
* Add features that utilize Locale, Accessibility, Notifications and Workmanager.
* Create a WearOs app showing live saved/favourite currency conversion rates.

<h2 align="left">Screenshots Of App</h2>
<h4 align="center">  
<img src="https://github.com/damade/CurrencyConverter/blob/main/screenshots/Screenshot_20220502-094654_CurrencyConverter.jpg" width="20%" vspace="10" hspace="10">
<img src="https://github.com/damade/CurrencyConverter/blob/main/screenshots/Screenshot_20220502-094810_CurrencyConverter.jpg" width="20%" vspace="10" hspace="10">
<img src="https://github.com/damade/CurrencyConverter/blob/main/screenshots/Screenshot_20220502-192804_CurrencyConverter.jpg" width="20%" vspace="10" hspace="10">
<img alt="Compose Screen" src="https://github.com/damade/CurrencyConverter/blob/main/screenshots/CurrencyCalculator.png" width="20%" vspace="10" hspace="10">

