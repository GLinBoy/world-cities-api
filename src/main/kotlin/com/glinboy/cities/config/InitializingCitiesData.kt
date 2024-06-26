package com.glinboy.cities.config

import com.glinboy.cities.entity.City
import com.glinboy.cities.respository.CityRepository
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.io.BufferedReader
import java.io.FileReader

@Component
@ConditionalOnProperty(prefix = "application", name = ["initialize-cities-data"], havingValue = "true")
class InitializingCitiesData(val cityRepository: CityRepository) : InitializingBean {

    @Value("\${application.cities-file-path}")
    lateinit var filePath: String;

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun afterPropertiesSet() {
        log.info("Initializing Cities Data...")
        cityRepository.deleteAllInBatch()
        val worldCities = ClassPathResource(filePath)
        if(worldCities.exists()) {
            FileReader(worldCities.file).use {
                BufferedReader(it).use {
                    CSVParser(it, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build()).use {
                        val cities = mutableListOf<City>()
                        for (csvRecord in it) {
                            cities.add(
                                City(
                                    id = csvRecord.get("id").toLong(),
                                    city = csvRecord.get("city"),
                                    cityAscii = csvRecord.get("city_ascii"),
                                    lat = csvRecord.get("lat").toDouble(),
                                    lng = csvRecord.get("lng").toDouble(),
                                    country = csvRecord.get("country"),
                                    iso2 = csvRecord.get("iso2"),
                                    iso3 = csvRecord.get("iso3"),
                                    adminName = csvRecord.get("admin_name"),
                                    capital = csvRecord.get("capital"),
                                    population = if (csvRecord.get("population").isNotBlank())
                                        csvRecord.get("population").toLong() else -1,
                                )
                            )
                        }
                        cityRepository.saveAll(cities)
                        log.info("{} cities have been saved in database.", cities.size)
                    }
                }
            }
            log.info("Cities Data has been Initialized.")
        } else {
            log.info("Cities file didn't exist ({}).", filePath)
        }
    }
}
