package com.glinboy.cities.service

import com.glinboy.cities.entity.City
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CityService {
    fun getAllCities(pageable: Pageable): Page<City>
}