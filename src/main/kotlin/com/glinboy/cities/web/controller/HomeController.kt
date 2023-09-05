package com.glinboy.cities.web.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun getHome(response: HttpServletResponse) {
        response.sendRedirect("/swagger-ui.html")
    }
}
