package com.glinboy.cities.respository

import com.glinboy.cities.entity.City
import com.glinboy.cities.projection.Country
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CityRepository : JpaRepository<City, Long> {
}