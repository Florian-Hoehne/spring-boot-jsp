package de.fho.lobbyinc.webapp

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController
{
    @GetMapping("/home")
    fun index() = "home"

    @GetMapping("/test")
    fun test() = "test"
}
