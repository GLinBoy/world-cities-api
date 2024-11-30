package com.glinboy.cities.web.rest

import com.glinboy.cities.entity.City
import com.glinboy.cities.projection.Country
import com.glinboy.cities.service.CountryService
import com.glinboy.cities.util.PaginationUtil
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springdoc.core.converters.models.PageableAsQueryParam
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/countries")
class CountryResource(val countryService: CountryService) {

    @JvmField final val BASE_URL = "/api/v1/countries"
    @JvmField final val BASE_COUNTRY_SEARCH_URL = "/api/v1/countries/search?q=%s"
    @JvmField final val BASE_COUNTRY_CITIES_ISO2_URL = "/api/v1/countries/%s/cities"

    @GetMapping
    @PageableAsQueryParam
    fun getCountries(@Parameter(hidden = true) pageable: Pageable): ResponseEntity<List<Country>> {
        var page = countryService.getAllCountries(pageable)
        return ResponseEntity.ok()
            .headers(PaginationUtil.generateHeaders(page, BASE_URL))
            .body(page.content)
    }

    @GetMapping("search")
    @PageableAsQueryParam
    fun searchCountry(@Valid @Pattern(regexp = "\\p{L}+")
                      @RequestParam(name = "q", required = true) query: String,
                      @Parameter(hidden = true) pageable: Pageable): ResponseEntity<List<Country>> {
        val page = countryService.searchCountry(query, pageable)
        return ResponseEntity.ok()
            .headers(PaginationUtil.generateHeaders(page, BASE_COUNTRY_SEARCH_URL.format(query)))
            .body(page.content)
    }

    @GetMapping("{isoCode:[a-zA-Z]{2,3}}/cities")
    @PageableAsQueryParam
    fun getCountryCities(@PathVariable isoCode: String, @Parameter(hidden = true) pageable: Pageable): ResponseEntity<List<City>> {
        val page = countryService.getCountryCities(isoCode, pageable)
        return ResponseEntity.ok()
            .headers(PaginationUtil.generateHeaders(page, BASE_COUNTRY_CITIES_ISO2_URL.format(isoCode)))
            .body(page.content)
    }
}
