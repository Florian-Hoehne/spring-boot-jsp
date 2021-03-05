package de.fho.lobbyinc.webapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.ServletContextApplicationContextInitializer
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class WebappApplication : SpringBootServletInitializer()
{
    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder
    {
        return builder.sources(WebappApplication::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<WebappApplication>(*args)
}
