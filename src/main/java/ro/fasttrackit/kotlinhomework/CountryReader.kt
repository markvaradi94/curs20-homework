package ro.fasttrackit.kotlinhomework

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.stream.Collectors.toList

class CountryReader {
    fun readCountries(file: File): List<Country> {
        return BufferedReader(FileReader(file))
                .lines()
                .map(this::readCountry)
                .collect(toList())
    }

    private fun readCountry(line: String): Country {
        val elements = line.split("|")
        return Country(
                elements[0],
                elements[1],
                elements[2].toLong(),
                elements[3].toLong(),
                elements[4],
                if (elements.size > 5) readNeighbours(elements[5]) else emptyList()
        )
    }

    private fun readNeighbours(neighbours: String): List<String> {
        val elements = neighbours.split("~")
        return elements.toList()
    }
}