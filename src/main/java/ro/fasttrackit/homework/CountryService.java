package ro.fasttrackit.homework;

import java.util.*;

import static java.util.stream.Collectors.*;


public class CountryService {

    private final List<Country> countries;

    public CountryService(List<Country> countries) {
        this.countries = Optional.ofNullable(countries)
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }

    public List<Country> getCountries() {
        return new ArrayList<>(countries);
    }

    public List<String> countryNames() {
        return countries.stream()
                .map(Country::getName)
                .collect(toList());
    }

    public String countryCapital(String countryName) {
        return countries.stream()
                .map(country -> findCountryByName(countryName).getCapital())
                .findAny()
                .orElse(Optional.empty().toString());
    }

    public Long populationOfCountry(String countryName) {
        return countries.stream()
                .map(country -> findCountryByName(countryName).getPopulation())
                .findAny()
                .orElse(-1L);
    }

    public List<Country> countriesOnContinent(String continent) {
        return countries.stream()
                .filter(country -> isFromContinent(country, continent))
                .collect(toList());
    }

    public List<String> countryNeighbours(String countryName) {
        return countries.stream()
                .map(country -> findCountryByName(countryName).getNeighbours())
                .findAny()
                .orElseGet(ArrayList::new);
    }

    public List<Country> continentCountriesWithMinimumPopulation(String continent, Long minPopulation) {
        return countries.stream()
                .filter(country -> isFromContinent(country, continent))
                .filter(country -> country.getPopulation() >= minPopulation)
                .collect(toList());
    }

    public List<Country> continentCountriesSortedByPopulation(String continent) {
        return countries.stream()
                .filter(country -> isFromContinent(country, continent))
                .sorted(Comparator.comparing(Country::getPopulation))
                .collect(toList());
    }

    public List<Country> continentCountriesSortedByArea(String continent) {
        return countries.stream()
                .filter(country -> isFromContinent(country, continent))
                .sorted(Comparator.comparing(Country::getArea))
                .collect(toList());
    }

    public Map<String, Long> countryAndPopulation(String countryName) {
        return countries.stream()
                .map(country -> findCountryByName(countryName))
                .collect(toMap(Country::getName, Country::getPopulation, (v1, v2) -> v1));
    }

    public Map<String, Long> countriesToPopulationsMap() {
        return countries.stream()
                .collect(toMap(Country::getName, Country::getPopulation));
    }

    public Map<String, List<Country>> countriesToContinentsMap() {
        return countries.stream()
                .collect(groupingBy(Country::getContinent));
    }

    public Map<String, List<Country>> countriesToContinentsSortedByPopulationMap() {
        return countries.stream()
                .sorted(Comparator.comparing(Country::getPopulation))
                .collect(groupingBy(Country::getContinent));
    }

    public Map<String, List<Country>> countriesFromContinent(String continent) {
        return countries.stream()
                .filter(country -> isFromContinent(country, continent))
                .collect(groupingBy(Country::getContinent));
    }

    public Map<String, List<Country>> countriesFromContinentSortedByPopulation(String continent) {
        return countries.stream()
                .filter(country -> isFromContinent(country, continent))
                .sorted(Comparator.comparing(Country::getPopulation))
                .collect(groupingBy(Country::getContinent));
    }

    public List<Country> countriesByNeighbours(String neighbouring, String avoiding) {
        return countries.stream()
                .filter(country -> country.getNeighbours().contains(neighbouring))
                .filter(country -> !country.getNeighbours().contains(avoiding))
                .collect(toList());
    }

    private boolean isFromContinent(Country country, String continent) {
        return country.getContinent().equalsIgnoreCase(continent);
    }

    private Country findCountryByName(String countryName) {
        return countries.stream()
                .filter(country -> country.getName().equalsIgnoreCase(countryName))
                .findAny()
                .orElse(new Country("default", "default", 0L, 0L, "default", List.of()));
    }

}
