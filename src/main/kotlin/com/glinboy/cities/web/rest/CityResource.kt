package com.glinboy.cities.web.rest

import com.glinboy.cities.entity.City
import com.glinboy.cities.service.CityService
import com.glinboy.cities.util.PaginationUtil
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/cities")
class CityResource(val cityService: CityService) {

    @JvmField final val BASE_CITY_URL = "/api/v1/cities"
    @JvmField final val BASE_CITY_SEARCH_URL = "/api/v1/cities/search?q=%s"

    @GetMapping
    fun getCities(pageable: Pageable): ResponseEntity<List<City>> {
        val page = cityService.getAllCities(pageable)
        return ResponseEntity.ok()
            .headers(PaginationUtil.generateHeaders(page, BASE_CITY_URL))
            .body(page.content)
    }

    @GetMapping("search")
    fun searchCity(@Valid @Pattern(regexp = "\\p{L}+")
                   @RequestParam(name = "q", required = true) query: String,
                   pageable: Pageable): ResponseEntity<List<City>> {
        val page = cityService.searchCity(query, pageable)
        return ResponseEntity.ok()
            .headers(PaginationUtil.generateHeaders(page, BASE_CITY_SEARCH_URL.format(query)))
            .body(page.content)
    }
}
