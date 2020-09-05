plugins {
    kotlin("js") version "1.4.0"
}

group = "me.gcx11"

artifacts {
    base {
        archivesBaseName = "kower"
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