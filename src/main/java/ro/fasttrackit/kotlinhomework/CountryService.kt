package ro.fasttrackit.kotlinhomework

data class Country(val name: String, val capital: String, val population: Long,
                   val area: Long, val continent: String, val neighbours: List<String>) {
    val validator = CountryValidator().verify(name, capital, population, area, continent)
}

class CountryService(private val countries: List<Country>) {

    fun getAllCountries(): List<Country> = countries

    fun countryNames(): List<String> = countries.map { it.name }

    fun countryCapital(countryName: String): String? = countries
            .find { it == getCountryByName(countryName) }?.capital

    fun countryPopulation(countryName: String): Long? = countries
            .find { it == getCountryByName(countryName) }?.population

    fun continentCountries(continent: String) = countries.filter { isFromContinent(it, continent) }

    fun countryNeighbours(countryName: String): List<String>? = countries
            .find { it == getCountryByName(countryName) }?.neighbours

    fun continentCountriesWithMinimumPopulation(continent: String, minimumPopulation: Long) = countries
            .filter { isFromContinent(it, continent) }
            .filter { it.population >= minimumPopulation }

    fun continentCountriesSortedByPopulation(continent: String) = countries
            .filter { isFromContinent(it, continent) }
            .sortedBy { it.population }

    fun continentCountriesSortedByArea(continent: String) = countries
            .filter { isFromContinent(it, continent) }
            .sortedBy { it.area }

    fun countryAndPopulationMap(countryName: String): Map<String, Long> = countries
            .filter { isCountry(it, countryName) }
            .associate { it.name to it.population }

    fun countriesAndPopulationMap(): Map<String, Long> = countries.associate { it.name to it.population }

    fun countriesFromContinentMap(continent: String): Map<String, List<Country>> = countries
            .filter { isFromContinent(it, continent) }
            .groupBy { it.continent }

    fun countriesToContinentsMap(): Map<String, List<Country>> = countries.groupBy { it.continent }

    fun countriesToContinentSortedByPopulationMap(): Map<String, List<Country>> = countries
            .sortedBy { it.population }
            .groupBy { it.continent }

    fun countriesFromContinentSortedByPopulationMap(continent: String): Map<String, List<Country>> = countries
            .filter { isFromContinent(it, continent) }
            .sortedBy { it.population }
            .groupBy { it.continent }

    fun countriesByNeighbours(neighbouring: String, avoiding: String): List<Country> = countries
            .filter { it.neighbours.contains(neighbouring) }
            .filter { !it.neighbours.contains(avoiding) }

    private fun isFromContinent(country: Country, continent: String) = country.continent.equals(continent, ignoreCase = true)

    private fun isCountry(country: Country, countryName: String) = country.name.equals(countryName, ignoreCase = true)

    private fun getCountryByName(countryName: String): Country = countries.first { it.name.equals(countryName, ignoreCase = true) }
}