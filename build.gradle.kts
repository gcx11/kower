allprojects {
    repositories {
        mavenCentral()
        jcenter()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlinx")
        }
    }
}

subprojects {
    version = "0.0.1"
}