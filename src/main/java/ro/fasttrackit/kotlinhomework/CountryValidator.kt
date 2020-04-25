package ro.fasttrackit.kotlinhomework

import java.lang.IllegalArgumentException
import java.util.regex.Matcher
import java.util.regex.Pattern

class CountryValidator {

    fun verify(name: String, capital: String, population: Long, area: Long, continent: String) {
        verifyName(name)
        verifyName(capital)
        verifyValue(population)
        verifyValue(area)
        verifyName(continent)
    }

    private fun verifyValue(element: Long) {
        if (element < 0) throw IllegalArgumentException("Invalid value for $element")
    }

    private fun verifyName(element: String) {
        val pattern: Pattern = Pattern.compile("^[ (),.ÅA-za-zíéáçóēåñôșăãú'\\-]+$")
        val matcher: Matcher = pattern.matcher(element)
        if (!matcher.matches()) throw IllegalArgumentException("$element is not an accepted argument")
    }
}