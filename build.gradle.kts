// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Config.Plugins.gradle)
        classpath(Config.Plugins.spotless)
        classpath(Config.Plugins.kotlin)
        classpath(Config.Plugins.navSaveArgs)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { setUrl("https://jitpack.io") }
    }
}

tasks.withType<Delete> {
    delete(rootProject.buildDir)
}
