allprojects {
    repositories {
        mavenCentral()
        jcenter()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlinx")
        }
    }
}

val libraryGroup = "me.gcx11"
val libraryVersion = "0.0.3"

subprojects {
    group = libraryGroup
    version = libraryVersion
}

plugins {
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = libraryGroup
            artifactId = rootProject.name
            version = libraryVersion

            artifact("lib/build/libs/$artifactId-$version.jar")
        }

        repositories {
            maven {
                val localProperties = java.util.Properties()
                localProperties.load(rootProject.file("local.properties").inputStream())

                val githubUser: String by localProperties
                val githubToken: String by localProperties

                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/gcx11/kower")
                credentials {
                    username = githubUser
                    password = githubToken
                }
            }
        }
    }
}