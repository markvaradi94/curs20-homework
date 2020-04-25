package ro.fasttrackit.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;


class CountryTest {
    private Country test;

    @BeforeEach
    void setUp() {
        test = new Country("slovakia", "bratislava", 15_213_542L,
                143_232L, "europe", List.of("HUN", "CZH", "ETC"));
    }

    @Test
    @DisplayName("WHEN arguments are valid THEN a country is created")
    void validCountry() {
        assertNotNull(test);
    }

    @Test
    @DisplayName("WHEN requested THEN country name is returned")
    void countryName() {
        assertThat(test.getName()).isEqualTo("slovakia");
    }

    @Test
    @DisplayName("WHEN requested THEN country capital is returned")
    void countryCapital() {
        assertThat(test.getCapital()).isEqualTo("bratislava");
    }

    @Test
    @DisplayName("WHEN requested THEN country population is returned")
    void countryPopulation() {
        assertThat(test.getPopulation()).isEqualTo(15_213_542L);
    }

    @Test
    @DisplayName("WHEN requested THEN country area is returned")
    void countryArea() {
        assertThat(test.getArea()).isEqualTo(143_232L);
    }

    @Test
    @DisplayName("WHEN requested THEN continent is returned")
    void countryContinent() {
        assertThat(test.getContinent()).isEqualTo("europe");
    }

    @Test
    @DisplayName("WHEN requested THEN list of neighbours is returned")
    void countryNeighbours() {
        assertThat(test.getNeighbours()).isEqualTo(List.of("HUN", "CZH", "ETC"));
    }

    @Test
    @DisplayName("WHEN invalid country name is inserted THEN exception is thrown")
    void invalidCountryName() {
        assertThrows(IllegalArgumentException.class, () -> new Country("sl0vak1a", "bratislava", 15_213_542L,
                143_232L, "europe", List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN invalid capital name is inserted THEN exception is thrown")
    void invalidCapital() {
        assertThrows(IllegalArgumentException.class, () -> new Country("slovakia", "brat1slav@", 15_213_542L,
                143_232L, "europe", List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN invalid continent name is inserted THEN exception is thrown")
    void invalidContinent() {
        assertThrows(IllegalArgumentException.class, () -> new Country("slovakia", "bratislava", 15_213_542L,
                143_232L, "europe123", List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN invalid population is inserted THEN exception is thrown")
    void invalidPopulation() {
        assertThrows(IllegalArgumentException.class, () -> new Country("slovakia", "bratislava", -15_213_542L,
                143_232L, "europe", List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN invalid area is inserted THEN exception is thrown")
    void invalidArea() {
        assertThrows(IllegalArgumentException.class, () -> new Country("slovakia", "bratislava", 15_213_542L,
                -143_232L, "europe", List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN null country name is inserted THEN exception is thrown")
    void nullCountryName() {
        assertThrows(IllegalArgumentException.class, () -> new Country(null, "bratislava", 15_213_542L,
                143_232L, "europe", List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN null capital name is inserted THEN exception is thrown")
    void nullCapital() {
        assertThrows(IllegalArgumentException.class, () -> new Country("slovakia", null, 15_213_542L,
                143_232L, "europe", List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN null continent name is inserted THEN exception is thrown")
    void nullContinent() {
        assertThrows(IllegalArgumentException.class, () -> new Country("slovakia", "bratislava", 15_213_542L,
                143_232L, null, List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN null population is inserted THEN exception is thrown")
    void nullPopulation() {
        assertThrows(IllegalArgumentException.class, () -> new Country("slovakia", "bratislava", null,
                143_232L, "europe", List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN null area is inserted THEN exception is thrown")
    void nullArea() {
        assertThrows(IllegalArgumentException.class, () -> new Country("slovakia", "bratislava", 15_213_542L,
                null, "europe", List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN empty country name is inserted THEN exception is thrown")
    void emptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Country("", "bratislava", 15_213_542L,
                143_232L, "europe", List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN empty capital name is inserted THEN exception is thrown")
    void emptyCapital() {
        assertThrows(IllegalArgumentException.class, () -> new Country("slovakia", "", 15_213_542L,
                143_232L, "europe", List.of("HUN", "CZH", "ETC")));
    }

    @Test
    @DisplayName("WHEN empty capital name is inserted THEN exception is thrown")
    void emptyContinent() {
        assertThrows(IllegalArgumentException.class, () -> new Country("slovakia", "bratislava", 15_213_542L,
                143_232L, "", List.of("HUN", "CZH", "ETC")));
    }
}