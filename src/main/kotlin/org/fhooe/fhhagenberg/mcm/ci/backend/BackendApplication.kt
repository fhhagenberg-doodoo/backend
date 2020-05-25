package org.fhooe.fhhagenberg.mcm.ci.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {

	println("API-Docs available at: http://localhost:8081/swagger-ui.html")

	runApplication<BackendApplication>(*args)
}
