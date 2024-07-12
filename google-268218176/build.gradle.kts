plugins {
    id("java")
}

java {

}
project.extensions.getByName("javaToolchains").apply {
    this as JavaToolchainService
    this.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
    println("java19")
}
tasks.register<JavaExec>("runOn17") {
    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(17))
    })

    classpath = sourceSets["main"].runtimeClasspath
}