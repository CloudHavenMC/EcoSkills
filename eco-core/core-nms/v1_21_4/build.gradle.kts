import org.jetbrains.kotlin.gradle.dsl.JvmTarget

group = "com.willfp"
version = rootProject.version

val spigotVersion = "1.21.4-R0.1-SNAPSHOT"

dependencies {
    compileOnly("org.spigotmc:spigot:$spigotVersion") {
        exclude(group = "io.netty")
    }
}

configurations.compileOnly {
    resolutionStrategy {
        force("org.spigotmc:spigot:$spigotVersion")
    }
}

tasks {
    compileJava {
        options.release = 21
    }

    compileKotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
}
