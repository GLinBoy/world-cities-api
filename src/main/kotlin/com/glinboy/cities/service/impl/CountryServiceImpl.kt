package com.glinboy.cities.service.impl

import com.glinboy.cities.entity.City
import com.glinboy.cities.projection.Country
import com.glinboy.cities.respository.CityRepository
import com.glinboy.cities.service.CountryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CountryServiceImpl(val cityRepository: CityRepository): CountryService {
    override fun getAllCountries(pageable: Pageable): Page<Country> =
        cityRepository.findDistinctCountry(pageable)
    override fun searchCountry(query: String, pageable: Pageable): Page<Country> =
        cityRepository.searchCountry(query, pageable)
}