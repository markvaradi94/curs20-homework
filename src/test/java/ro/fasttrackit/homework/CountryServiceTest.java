package ro.fasttrackit.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CountryServiceTest {
    private CountryService service;
    private final File file = new File("src\\main\\resources\\countries2.txt");
    private CountryReader reader;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        reader = new CountryReader();
        List<Country> countries = reader.readCountries(file);
        service = new CountryService(countries);
    }

    @Test
    @DisplayName("WHEN requested THEN list of countries is returned")
    void getCountries() {
        assertThat(service.getCountries()).extracting("name").contains("Romania", "France", "Australia");
    }

    @Test
    @DisplayName("WHEN requested THEN list of country names is returned")
    void countryNames() {
        final CountryService service = new CountryService(List.of(
                new Country("Romania", "Bucharest", 19_000_000L,
                        696969L, "Europe", List.of("BGR", "HUN", "MDA", "SRB", "UKR")),
                new Country("Moldova", "Chisinau", 6_000_000L,
                        80085L, "Europe", List.of("ROU", "UKR"))
        ));
        List<String> names = service.countryNames();

        assertThat(names).containsExactly("Romania", "Moldova");
    }

    @Test
    @DisplayName("WHEN requested THEN the capital of a country is returned")
    void countryCapital() {
        assertThat(service.countryCapital("denmark")).isEqualToIgnoringCase("copenhagen");
    }

    @Test
    @DisplayName("WHEN country capital is requested but the country is not found THEN exception is thrown")
    void noCountryCapital() {
        assertThrows(NoSuchElementException.class, () -> service.countryCapital("Romaniastan"));
    }

    @Test
    @DisplayName("WHEN requested THEN the population of a country is returned")
    void populationOfCountry() {
        assertThat(service.populationOfCountry("mexico")).isEqualTo(122273473L);
    }

    @Test
    @DisplayName("WHEN country population is requested but the country is not found THEN exception is thrown")
    void noCountryPopulation() {
        assertThrows(NoSuchElementException.class, () -> service.populationOfCountry("Romaniastan"));
    }

    @Test
    @DisplayName("WHEN requested THEN list of countries from specified continent is returned")
    void countriesByContinent() {
        List<Country> countriesFromContinent = service.countriesOnContinent("europe");

        assertThat(countriesFromContinent).extracting("name").contains("France", "Italy", "Germany", "Romania");
    }

    @Test
    @DisplayName("WHEN countries from an invalid continent are requested THEN empty list is returned")
    void invalidContinentCountries() {
        List<Country> countriesFromContinent = service.countriesOnContinent("europenische");

        assertThat(countriesFromContinent).isEmpty();
    }

    @Test
    @DisplayName("WHEN requested THEN list of neighbours of specified country is returned")
    void countryNeighbours() {
        List<String> neighbours = service.countryNeighbours("Moldova (Republic of)");

        assertThat(neighbours).containsExactly("ROU", "UKR");
    }

    @Test
    @DisplayName("WHEN country neighbours are requested but the country is not found THEN exception is thrown")
    void noCountryNeighbours() {
        assertThrows(NoSuchElementException.class, () -> service.countryNeighbours("Romaniastan"));
    }

    @Test
    @DisplayName("WHEN requested THEN countries from specified continent with population larger than expected is returned")
    void continentCountriesWithMinimumPopulation() {
        List<Country> countries = service.continentCountriesWithMinimumPopulation("africa", 90_000_000L);

        assertThat(countries).extracting("name").containsExactlyInAnyOrder("Nigeria", "Egypt", "Ethiopia");
    }

    @Test
    @DisplayName("WHEN no countries with enough population are found THEN empty list returned")
    void noCountriesWithEnoughPopulation() {
        List<Country> countries = service.continentCountriesWithMinimumPopulation("africa", 300_000_000L);

        assertThat(countries).isEmpty();
    }

    @Test
    @DisplayName("WHEN requested THEN list of countries sorted by population from specified continent is returned")
    void continentCountriesSortedByPopulation() {
        List<Country> countries = service.continentCountriesSortedByPopulation("americas");

        assertThat(countries.get(0)).extracting("name").isEqualTo("Falkland Islands (Malvinas)");
        assertThat(countries.get(countries.size() - 1)).extracting("name").isEqualTo("United States of America");
    }

    @Test
    @DisplayName("WHEN sorted countries by population for invalid continent are requested THEN empty list is returned")
    void noContinentCountriesSortedByPopulation() {
        List<Country> countries = service.continentCountriesSortedByPopulation("americastol");

        assertThat(countries).isEmpty();
    }

    @Test
    @DisplayName("WHEN requested THEN list of countries sorted by area from specified continent is returned")
    void continentCountriesSortedByArea() {
        List<Country> countries = service.continentCountriesSortedByArea("americas");

        assertThat(countries.get(0)).extracting("name").isEqualTo("Saint Barthélemy");
        assertThat(countries.get(countries.size() - 1)).extracting("name").isEqualTo("Canada");
    }

    @Test
    @DisplayName("WHEN sorted countries by area for invalid continent are requested THEN empty list is returned")
    void noContinentCountriesSortedByArea() {
        List<Country> countries = service.continentCountriesSortedByArea("americastol");

        assertThat(countries).isEmpty();
    }

    @Test
    @DisplayName("WHEN requested THEN map of country name and population is returned")
    void countryAndPopulation() {
        Map<String, Long> result = service.countryAndPopulation("iceland");

        assertThat(result.keySet()).containsExactly("Iceland");
        assertThat(result.values()).containsExactly(334300L);
    }

    @Test
    @DisplayName("WHEN country with populations is requested but the country is not found THEN exception is thrown")
    void noCountryAndPopulation() {
        assertThrows(NoSuchElementException.class, () -> service.countryAndPopulation("Romaniastan"));
    }

    @Test
    @DisplayName("WHEN requested THEN map with country name keys and population values is returned")
    void countriesToPopulationsMap() {
        Map<String, Long> result = service.countriesToPopulationsMap();

        assertThat(result.keySet()).contains("United States of America", "Japan", "Australia", "Germany", "Nigeria");
        assertThat(result.values()).contains(323947000L, 126960000L, 24117360L, 81770900L, 186988000L);
    }

    @Test
    @DisplayName("WHEN requested THEN map with continents as keys and lists of countries as values is returned")
    void countriesToContinentsMap() {
        Map<String, List<Country>> countries = service.countriesToContinentsMap();

        assertThat(countries.keySet()).containsExactlyInAnyOrder("Americas", "Europe", "Asia", "Oceania", "Africa");
        assertThat(countries.get("Asia")).extracting("name").contains("Japan", "Indonesia", "Sri Lanka", "Bhutan", "India");
    }

    @Test
    @DisplayName("WHEN requested THEN map with continents as keys and with lists of countries sorted by population is returned")
    void countriesToContinentsSortedByPopulationMap() {
        Map<String, List<Country>> countries = service.countriesToContinentsSortedByPopulationMap();

        assertThat(countries.keySet()).containsExactlyInAnyOrder("Americas", "Europe", "Asia", "Oceania", "Africa");
        assertThat(countries.get("Europe").get(0)).extracting("name").isEqualTo("Holy See");
        assertThat(countries.get("Europe").get(countries.get("Europe").size() - 1)).extracting("name").isEqualTo("Russian Federation");
    }

    @Test
    @DisplayName("WHEN requested THEN map with specified continent as key and list of countries is returned")
    void countriesFromContinent() {
        Map<String, List<Country>> countries = service.countriesFromContinent("africa");

        assertThat(countries.keySet()).containsExactly("Africa");
        assertThat(countries.get("Africa")).extracting("name").contains("Egypt", "Senegal", "Nigeria", "Cameroon", "South Africa", "Uganda");
    }

    @Test
    @DisplayName("WHEN countries from invalid continent are requested THEN empty list is returned")
    void countriesFromNoContinent() {
        Map<String, List<Country>> countries = service.countriesFromContinent("africul");

        assertThat(countries).isEmpty();
    }

    @Test
    @DisplayName("WHEN requested THEN map with specified continent as key and with list of countries sorted by population is returned")
    void countriesFromContinentSortedByPopulation() {
        Map<String, List<Country>> countries = service.countriesFromContinentSortedByPopulation("asia");

        assertThat(countries.keySet()).containsExactly("Asia");
        assertThat(countries.get("Asia").get(0)).extracting("name").isEqualTo("Maldives");
        assertThat(countries.get("Asia").get(countries.get("Asia").size() - 1)).extracting("name").isEqualTo("China");
    }

    @Test
    @DisplayName("WHEN sorted countries by population from invalid continent are requested THEN empty list is returned")
    void countriesSortedByPopulationContinentNotFound() {
        Map<String, List<Country>> countries = service.countriesFromContinentSortedByPopulation("asiul");

        assertThat(countries).isEmpty();
    }

    @Test
    @DisplayName("WHEN requested THEN country which neighbours country A but doesn't neighbour country B is returned")
    void countriesByNeighbours() {
        List<Country> countries = service.countriesByNeighbours("ROU", "SRB");

        assertThat(countries).extracting("name").containsExactlyInAnyOrder("Ukraine", "Serbia", "Moldova (Republic of)");
    }

    @Test
    @DisplayName("WHEN no countries are found THEN empty list is returned")
    void noCountriesByNeighbours() {
        List<Country> countries = service.countriesByNeighbours("ROU", "ROU");

        assertThat(countries).isEmpty();
    }
}