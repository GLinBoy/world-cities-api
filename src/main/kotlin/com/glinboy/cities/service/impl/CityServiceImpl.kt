package com.glinboy.cities.service.impl

import com.glinboy.cities.entity.City
import com.glinboy.cities.respository.CityRepository
import com.glinboy.cities.service.CityService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CityServiceImpl(val cityRepository: CityRepository) : CityService {
    override fun getAllCities(pageable: Pageable): Page<City> = cityRepository.findAll(pageable)
}