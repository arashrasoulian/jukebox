repositories {
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
}

plugins {
    java
    id("org.springframework.boot") version "2.7.5"
    id("idea")
    kotlin("jvm") version "1.7.20"
    kotlin("kapt") version "1.7.20"
    id("com.netflix.dgs.codegen") version "5.6.0"
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.7.8")

    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.7.5"))
    modules {
        module("org.springframework.boot:spring-boot-starter-logging") {
            replacedBy("org.springframework.boot:spring-boot-starter-log4j2")
        }
        module("org.springframework.boot:spring-boot-starter-web") {
            replacedBy("org.springframework.boot:spring-boot-starter-webflux")
        }
    }

    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    kapt("org.springframework.boot:spring-boot-configuration-processor:2.7.5")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.newrelic.logging:log4j2:2.6.0")

    implementation("io.github.microutils:kotlin-logging-jvm:2.1.23")

    implementation("com.github.ben-manes.caffeine:caffeine:3.1.2")

    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:5.0.5"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-webflux-starter")
    implementation("com.netflix.graphql.dgs:graphql-dgs-extended-scalars")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.mockito")
    }
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
    testImplementation("org.awaitility:awaitility-kotlin")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }

    named<Test>("test") {
        useJUnitPlatform()
    }

    generateJava {
        packageName = "com.jukebox.model"
        generateClient = true // Enable generating the type safe query API
        typeMapping = mutableMapOf(
            "Url" to "java.net.URL",
            "GraphQLLong" to "Long"
        )
    }

//    installDependencies(type: YarnTask) {
//        execOverrides {
//            it.workingDir = '../web'
//        }
//    }
//
//    buildWeb(type: YarnTask) {
//        args = ['build:gradle']
//        execOverrides {
//            it.workingDir = '../web'
//        }
//    }
//
//    buildWeb.dependsOn installDependencies
//            build.dependsOn buildWeb

}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(11))
    }
}

idea {
    module {
        sourceDirs.add(tasks.generateJava.get().getOutputDir())
    }
}

