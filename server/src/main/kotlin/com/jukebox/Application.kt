package com.jukebox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.boot.WebApplicationType.REACTIVE

@SpringBootApplication
@ConfigurationPropertiesScan
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args) {
        webApplicationType = REACTIVE
    }
}
