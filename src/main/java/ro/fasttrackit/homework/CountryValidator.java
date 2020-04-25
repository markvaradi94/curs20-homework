package ro.fasttrackit.homework;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryValidator {

    public void verify(String name, String capital, Long population, Long area, String continent) {
        verifyNullName(name);
        verifyNullName(capital);
        verifyNullValue(population);
        verifyNullValue(area);
        verifyNullName(continent);
        verifyName(name);
        verifyName(capital);
        verifyValue(population);
        verifyValue(area);
        verifyName(continent);
    }

    private void verifyName(String element) {
        verifyNullName(element);
        Pattern pattern = Pattern.compile("^[ (),.ÅA-za-zíéáçóēåñôșăãú'\\-]+$");
        Matcher matcher = pattern.matcher(element);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(element + " is not an accepted argument");
        }
    }

    private void verifyValue(Long element) {
        verifyNullValue(element);
        if (element < 0) throw new IllegalArgumentException("Invalid value for " + element);
    }

    private void verifyNullName(String element) {
        if (element == null || element.equals("")) {
            throw new IllegalArgumentException("Invalid empty argument");
        }
    }

    private void verifyNullValue(Long element) {
        if (element == null) {
            throw new IllegalArgumentException("Invalid argument value");
        }
    }
}
