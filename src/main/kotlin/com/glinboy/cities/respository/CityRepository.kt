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
    @Query(value = "SELECT country, iso2, iso3 from CITY GROUP BY country, iso2, iso3",
        countQuery = "SELECT COUNT(*) FROM (SELECT country, iso2, iso3 from CITY GROUP BY country, iso2, iso3)",
        nativeQuery = true)
    fun findDistinctCountry(pageable: Pageable): Page<Country>

    @Query("SELECT c FROM City c " +
            " WHERE " +
            " lower(c.city) like lower( concat('%', ?1,'%') ) " +
            " OR " +
            " lower(c.cityAscii) like lower( concat('%', ?1,'%') ) " +
            " OR " +
            " lower(c.country) like lower( concat('%', ?1,'%') ) " +
            " OR " +
            " lower(c.iso2) like lower( concat('%', ?1,'%') ) " +
            " OR " +
            " lower(c.iso3) like lower( concat('%', ?1,'%') ) " +
            " OR " +
            " lower(c.adminName) like lower( concat('%', ?1,'%') ) ")
    fun searchCity(query: String, pageable: Pageable): Page<City>
}