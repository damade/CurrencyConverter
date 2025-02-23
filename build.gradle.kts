plugins {
    // Existing plugins
    alias(libs.plugins.compose.compiler) apply false
}

tasks.register<GenerateFeatureModuleTask>("generateFeatureModule")

// Tasks for automatically installing git-hooks and making it executable also.
tasks.register("installGitHook", Copy::class) {
    from(file("$rootDir/scripts"))
    into(file("$rootDir/.git/hooks"))
    fileMode = 0b0111101101 // -rwxr-xr-x
}

tasks.create(name = "gitExecutableHooks") {
    doLast {
        Runtime.getRuntime().exec("chmod -R +x .git/hooks/")
    }
}
tasks.getByPath("gitExecutableHooks").dependsOn(tasks.named("installGitHook"))
tasks.getByPath(":app:clean").dependsOn(tasks.named("gitExecutableHooks"))