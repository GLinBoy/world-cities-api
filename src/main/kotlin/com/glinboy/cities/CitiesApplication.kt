package com.glinboy.cities

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CitiesApplication

fun main(args: Array<String>) {
    runApplication<CitiesApplication>(*args)
}
