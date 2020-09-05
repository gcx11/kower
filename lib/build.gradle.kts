plugins {
    kotlin("js") version "1.4.0"
}

group = "me.gcx11"
version = "1.0-SNAPSHOT"

dependencies {
}

kotlin {
    js {
        browser {
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
        }
    }
}