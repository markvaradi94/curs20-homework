package ro.fasttrackit.kotlinhomework

import java.io.File

fun main() {
    val file = File("src\\main\\resources\\countries2.txt")
    val reader = CountryReader()
    val countries = reader.readCountries(file)
    val service = CountryService(countries)

    println()
    service.getAllCountries().forEach { country -> println(country) }
    println()
    println(service.countryNames())
    println()
    println(service.countryCapital("france"))
    println()
    println(service.countryPopulation("spain"))
    println()
    println(service.continentCountries("africa"))
    println()
    println(service.countryNeighbours("uzbekistan"))
    println()
    println(service.continentCountriesWithMinimumPopulation("africa", 50_000_000L))
    println()
    println(service.continentCountriesSortedByPopulation("oceania"))
    println()
    println(service.continentCountriesSortedByArea("americas"))
    println()
    println(service.countryAndPopulationMap("greece"))
    println()
    println(service.countriesFromContinentMap("asia"))
    println()
    println(service.countriesFromContinentSortedByPopulationMap("africa"))
    println()
    println(service.countriesByNeighbours("ESP", "FRA"))
    println()
    println(service.countriesAndPopulationMap())
    println()
    service.countriesToContinentsMap().entries.forEach { println(it) }
    println()
    service.countriesToContinentSortedByPopulationMap().entries.forEach { println(it) }
    println()

}