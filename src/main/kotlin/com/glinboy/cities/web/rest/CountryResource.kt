package com.glinboy.cities.web.rest

import com.glinboy.cities.entity.City
import com.glinboy.cities.projection.Country
import com.glinboy.cities.service.CountryService
import com.glinboy.cities.util.PaginationUtil
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Pattern

@RestController
@RequestMapping("/api/v1/countries")
class CountryResource(val countryService: CountryService) {

    @JvmField final val BASE_URL = "/api/v1/countries"
    @GetMapping
    fun getCountries(pageable: Pageable): ResponseEntity<List<Country>> {
        var page = countryService.getAllCountries(pageable)
        return ResponseEntity.ok()
            .headers(PaginationUtil.generateHeaders(page, BASE_URL))
            .body(page.content)
    }
}