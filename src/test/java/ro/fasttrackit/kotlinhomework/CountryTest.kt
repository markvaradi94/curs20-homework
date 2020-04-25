package ro.fasttrackit.kotlinhomework

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertNotNull


internal class CountryTest {
    private lateinit var test: Country

    @BeforeEach
    fun setUp() {
        test = Country("slovakia", "bratislava", 15213542L,
                143232L, "europe", listOf("HUN", "CZH", "ETC"))
    }

    @Test
    fun `WHEN arguments are valid THEN a country is created`() {
        assertNotNull(test)
    }

    @Test
    fun `WHEN requested THEN country name is returned`() {
        assertThat(test.name).isEqualToIgnoringCase("slovakia")
    }

    @Test
    fun `WHEN requested THEN country capital is returned`() {
        assertThat(test.capital).isEqualToIgnoringCase("bratislava")
    }

    @Test
    fun `WHEN requested THEN country population is returned`() {
        assertThat(test.population).isEqualTo(15213542L)
    }

    @Test
    fun `WHEN requested THEN country area is returned`() {
        assertThat(test.area).isEqualTo(143232L)
    }

    @Test
    fun `WHEN requested THEN continent is returned`() {
        assertThat(test.continent).isEqualTo("europe")
    }

    @Test
    fun `WHEN requested THEN list of neighbours is returned`() {
        assertThat(test.neighbours).isEqualTo(listOf("HUN", "CZH", "ETC"))
    }

    @Test
    fun `WHEN invalid country name is inserted THEN exception is thrown`() {
        assertThrows<IllegalArgumentException> {
            Country("slovak1@", "bratislava", 15213542L,
                    143232L, "europe", listOf("HUN", "CZH", "ETC"))
        }
    }

    @Test
    fun `WHEN invalid capital name is inserted THEN exception is thrown`() {
        assertThrows<IllegalArgumentException> {
            Country("slovakia", "brati1s5lava", 15213542L,
                    143232L, "europe", listOf("HUN", "CZH", "ETC"))
        }
    }

    @Test
    fun `WHEN invalid continent name is inserted THEN exception is thrown`() {
        assertThrows<IllegalArgumentException> {
            Country("slovakia", "bratislava", 15213542L,
                    143232L, "eur0pe", listOf("HUN", "CZH", "ETC"))
        }
    }

    @Test
    fun `WHEN invalid population value is inserted THEN exception is thrown`() {
        assertThrows<IllegalArgumentException> {
            Country("slovakia", "bratislava", -15213542L,
                    143232L, "europe", listOf("HUN", "CZH", "ETC"))
        }
    }

    @Test
    fun `WHEN invalid area value is inserted THEN exception is thrown`() {
        assertThrows<IllegalArgumentException> {
            Country("slovakia", "bratislava", 15213542L,
                    -143232L, "europe", listOf("HUN", "CZH", "ETC"))
        }
    }

    @Test
    fun `WHEN empty country name is inserted THEN exception is thrown`() {
        assertThrows<IllegalArgumentException> {
            Country("", "bratislava", 15213542L,
                    143232L, "europe", listOf("HUN", "CZH", "ETC"))
        }
    }

    @Test
    fun `WHEN empty capital name is inserted THEN exception is thrown`() {
        assertThrows<IllegalArgumentException> {
            Country("slovakia", "", 15213542L,
                    143232L, "europe", listOf("HUN", "CZH", "ETC"))
        }
    }

    @Test
    fun `WHEN empty continent name is inserted THEN exception is thrown`() {
        assertThrows<IllegalArgumentException> {
            Country("slovakia", "bratislava", 15213542L,
                    143232L, "", listOf("HUN", "CZH", "ETC"))
        }
    }
}