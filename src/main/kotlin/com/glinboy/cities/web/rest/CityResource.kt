package com.glinboy.cities.web.rest

import com.glinboy.cities.entity.City
import com.glinboy.cities.service.CityService
import com.glinboy.cities.util.PaginationUtil
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Pattern

@RestController
@RequestMapping("/api/v1/cities")
class CityResource(val cityService: CityService) {

    @JvmField final val BASE_CITY_URL = "/api/v1/cities"
    @GetMapping
    fun getCities(pageable: Pageable): ResponseEntity<List<City>> {
        val page = cityService.getAllCities(pageable)
        return ResponseEntity.ok()
            .headers(PaginationUtil.generateHeaders(page, BASE_CITY_URL))
            .body(page.content)
    }
}