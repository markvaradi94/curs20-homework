package ro.fasttrackit.homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static java.util.stream.Collectors.*;

public class CountryReader {

    public List<Country> readCountries(File file) throws FileNotFoundException {
        return new BufferedReader(new FileReader(file))
                .lines()
                .map(this::readCountry)
                .collect(toList());
    }

    private Country readCountry(String line) {
        String[] elements = line.split("\\|");
        return new Country(
                elements[0],
                elements[1],
                Long.parseLong(elements[2]),
                Long.parseLong(elements[3]),
                elements[4],
                elements.length > 5 ? readNeighbours(elements[5]) : Collections.emptyList());
    }

    private List<String> readNeighbours(String neighbours) {
        String[] elements = neighbours.split("~");
        return new ArrayList<>(Arrays.asList(elements));
    }


}
