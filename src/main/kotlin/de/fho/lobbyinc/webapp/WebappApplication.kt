package de.fho.lobbyinc.webapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication

@SpringBootApplication
class WebappApplication

fun main(args: Array<String>) {
    runApplication<WebappApplication>(*args)
}