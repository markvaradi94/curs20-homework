package ro.fasttrackit.kotlinhomework

import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.util.NoSuchElementException

internal class CountryServiceTest {
    private lateinit var service: CountryService
    private val file = File("src\\main\\resources\\countries2.txt")
    private lateinit var reader: CountryReader

    @BeforeEach
    fun setUp() {
        reader = CountryReader()
        val countries = reader.readCountries(file)
        service = CountryService(countries)
    }

    @Test
    fun `WHEN requested THEN list of countries is returned`() {
        assertThat(service.getAllCountries()).extracting("name").contains("Canada", "China", "Australia", "Germany")
    }

    @Test
    fun `WHEN requested THEN list of country names is returned`() {
        service = CountryService(listOf(
                Country("Romania", "Bucharest", 123456, 789456, "Europe",
                        listOf("BGR", "HUN", "MDA", "SRB", "UKR")),
                Country("Moldova", "Chisinau", 11223, 2254, "Europe",
                        listOf("ROU", "UKR"))))

        assertThat(service.countryNames()).containsExactlyInAnyOrder("Romania", "Moldova")
    }

    @Test
    fun `WHEN requested THEN the capital of a country is returned`() {
        assertThat(service.countryCapital("norway")).isEqualToIgnoringCase("oslo")
    }

    @Test
    fun `WHEN country capital is requested but the country is not found THEN exception is thrown`() {
        assertThrows<NoSuchElementException> { service.countryCapital("United States of Irak") }
    }

    @Test
    fun `WHEN requested THEN the population of a country is returned`() {
        assertThat(service.countryPopulation("canada")).isEqualTo(36155487)
    }

    @Test
    fun `WHEN country population is requested but the country is not found THEN exception is thrown`() {
        assertThrows<NoSuchElementException> { service.countryPopulation("United States of Irak") }
    }

    @Test
    fun `WHEN requested THEN list of countries from specified continent is returned`() {
        val countries = service.continentCountries("americas")

        assertThat(countries).extracting("name").contains("Mexico", "Canada", "Argentina", "Brazil", "Peru")
    }

    @Test
    fun `WHEN countries from an invalid continent are requested THEN empty list is returned`() {
        val countries = service.continentCountries("americanas")

        assertThat(countries).isEmpty()
    }

    @Test
    fun `WHEN requested THEN list of neighbours of specified country is returned`() {
        val neighbours = service.countryNeighbours("spain")

        assertThat(neighbours).containsExactlyInAnyOrder("PRT", "AND", "FRA", "GIB", "MAR")
    }

    @Test
    fun `WHEN country neighbours are requested but the country is not found THEN exception is thrown`() {
        assertThrows<NoSuchElementException> { service.countryNeighbours("United States of Irak") }
    }

    @Test
    fun `WHEN requested THEN countries from specified continent with population larger than expected is returned`() {
        val countries = service.continentCountriesWithMinimumPopulation("europe", 80_000_000)

        assertThat(countries).extracting("name").containsExactlyInAnyOrder("Russian Federation", "Germany")
    }

    @Test
    fun `WHEN no countries with enough population are found THEN empty list returned`() {
        val countries = service.continentCountriesWithMinimumPopulation("europe", 200_000_000)

        assertThat(countries).isEmpty()
    }

    @Test
    fun `WHEN requested THEN list of countries sorted by population from specified continent is returned`() {
        val countries = service.continentCountriesSortedByPopulation("oceania")

        assertThat(countries[0]).extracting("name").isEqualTo("Pitcairn")
        assertThat(countries[countries.size - 1]).extracting("name").isEqualTo("Australia")
    }

    @Test
    fun `WHEN sorted countries by population for invalid continent are requested THEN empty list is returned`() {
        val countries = service.continentCountriesSortedByPopulation("oceanicul")

        assertThat(countries).isEmpty()
    }

    @Test
    fun `WHEN requested THEN list of countries sorted by area from specified continent is returned`() {
        val countries = service.continentCountriesSortedByArea("africa")

        assertThat(countries[0]).extracting("name").isEqualTo("British Indian Ocean Territory")
        assertThat(countries[countries.size - 1]).extracting("name").isEqualTo("Algeria")
    }

    @Test
    fun `WHEN sorted countries by area for invalid continent are requested THEN empty list is returned`() {
        val countries = service.continentCountriesSortedByArea("oceanicul")

        assertThat(countries).isEmpty()
    }

    @Test
    fun `WHEN requested THEN map of country name and population is returned`() {
        val result = service.countryAndPopulationMap("iceland")

        assertThat(result.keys).containsExactly("Iceland")
        assertThat(result.values).containsExactly(334300)
    }

    @Test
    fun `WHEN country with populations is requested but the country is not found THEN empty map is returned`() {
        val result = service.countryAndPopulationMap("romaniastan")

        assertThat(result).isEmpty()
    }

    @Test
    fun `WHEN requested THEN map with country name keys and population values is returned`() {
        val result = service.countriesAndPopulationMap()

        assertThat(result.keys).contains("United States of America", "Japan", "Australia", "Germany", "Nigeria")
        assertThat(result.values).contains(323947000, 126960000, 24117360, 81770900, 186988000)
    }

    @Test
    fun `WHEN requested THEN map with continents as keys and lists of countries as values is returned`() {
        val result = service.countriesToContinentsMap()

        assertThat(result.keys).containsExactlyInAnyOrder("Americas", "Europe", "Asia", "Oceania", "Africa")
        assertThat(result["Europe"]).extracting("name").contains("Romania", "Italy", "Germany", "Portugal", "Gibraltar", "Austria")
    }

    @Test
    fun `WHEN requested THEN map with specified continent as key and list of countries is returned`() {
        val countries = service.countriesFromContinentMap("asia")

        assertThat(countries.keys).containsExactly("Asia")
        assertThat(countries["Asia"]).extracting("name").contains("Japan", "China", "India", "Indonesia")
    }

    @Test
    fun `WHEN countries from invalid continent are requested THEN empty list is returned`() {
        val countries = service.countriesFromContinentMap("asiul")

        assertThat(countries).isEmpty()
    }

    @Test
    fun `WHEN requested THEN map with continents as keys and with lists of countries sorted by population is returned`() {
        val countries = service.countriesToContinentSortedByPopulationMap()

        assertThat(countries.keys).containsExactlyInAnyOrder("Americas", "Europe", "Asia", "Oceania", "Africa")
        assertThat(countries["Europe"]?.get(0)).extracting("name").isEqualTo("Holy See")
        assertThat(countries["Europe"]?.get((countries["Europe"]?.size
                ?: 0) - 1)).extracting("name").isEqualTo("Russian Federation")
    }

    @Test
    fun `WHEN requested THEN map with specified continent as key and with list of countries sorted by population is returned`() {
        val countries = service.countriesFromContinentSortedByPopulationMap("asia")

        assertThat(countries["Asia"]?.get(0)).extracting("name").isEqualTo("Maldives")
        assertThat(countries["Asia"]?.get((countries["Asia"]?.size
                ?: 0) - 1)).extracting("name").isEqualTo("China")
    }

    @Test
    fun `WHEN sorted countries by population from invalid continent are requested THEN empty list is returned`() {
        val countries = service.countriesFromContinentSortedByPopulationMap("asiul")

        assertThat(countries).isEmpty()
    }

    @Test
    fun `WHEN requested THEN country which neighbours country A but doesn't neighbour country B is returned`() {
        val countries = service.countriesByNeighbours("ROU", "SRB")

        assertThat(countries).extracting("name").containsExactlyInAnyOrder("Ukraine", "Serbia", "Moldova (Republic of)")
    }

    @Test
    fun `WHEN no countries are found THEN empty list is returned`() {
        val countries = service.countriesByNeighbours("SRB", "SRB")

        assertThat(countries).isEmpty()
    }
}