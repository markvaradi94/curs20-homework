package ro.fasttrackit.homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src\\main\\resources\\countries2.txt");
        CountryReader reader = new CountryReader();
        List<Country> countries = reader.readCountries(file);
        System.out.println();

        CountryService service = new CountryService(countries);

        System.out.println(service.countryNames() + "\n");
        System.out.println(service.countryCapital("austria") + "\n");
        System.out.println(service.populationOfCountry("germany") + "\n");
        System.out.println(service.countriesOnContinent("americas") + "\n");
        System.out.println(service.countryNeighbours("romania") + "\n");
        System.out.println(service.continentCountriesWithMinimumPopulation("europe", 50_000_000L) + "\n");
        System.out.println(service.continentCountriesSortedByPopulation("americas") + "\n");
        System.out.println(service.continentCountriesSortedByArea("asia") + "\n");
        System.out.println(service.countryAndPopulation("portugal") + "\n");
        System.out.println(service.countriesFromContinent("oceania") + "\n");
        System.out.println(service.countriesFromContinentSortedByPopulation("africa") + "\n");
        System.out.println(service.countriesByNeighbours("HUN", "ROU") + "\n");
        System.out.println(service.countriesToPopulationsMap() + "\n");

        for (String key : service.countriesToContinentsMap().keySet()) {
            System.out.println(key + " = " + service.countriesToContinentsMap().get(key));
            System.out.println();
        }

        System.out.println("===========================================================================================" + "\n");

        for (String key : service.countriesToContinentsSortedByPopulationMap().keySet()) {
            System.out.println(key + " = " + service.countriesToContinentsSortedByPopulationMap().get(key));
            System.out.println();
        }
    }


}
