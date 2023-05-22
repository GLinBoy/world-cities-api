package com.glinboy.cities.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class City(
    @Id
    var id: Long = -1,
    var city: String = "",
    var cityAscii: String = "",
    var lat: Double = 0.0,
    var lng: Double = 0.0,
    var country: String = "",
    var iso2: String = "",
    var iso3: String = "",
    var adminName: String = "",
    var capital: String = "",
    var population: Long = -1
)
