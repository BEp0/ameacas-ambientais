package com.example.ameacas.ameacas.ambientais

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun teste() {
        val input = "28/02/2023"
        assertTrue(validar(input))
    }

    private fun validar(input: String): Boolean {
        return (input.length == 10) &&
                (input.substring(2, 3) == "/") &&
                (input.substring(5, 6) == "/")
    }
}