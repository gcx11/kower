plugins {
    kotlin("js") version "1.4.0"
}

artifacts {
    base {
        archivesBaseName = rootProject.name
    }
}

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