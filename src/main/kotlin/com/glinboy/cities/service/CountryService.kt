package com.glinboy.cities.service

import com.glinboy.cities.entity.City
import com.glinboy.cities.projection.Country
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CountryService {
    fun getAllCountries(pageable: Pageable): Page<Country>
    fun searchCountry(query: String, pageable: Pageable): Page<Country>
    fun getCountryCities(isoCode: String, pageable: Pageable): Page<City>
}