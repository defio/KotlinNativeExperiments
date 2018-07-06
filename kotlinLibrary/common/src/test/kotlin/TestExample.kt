package com.ndefiorenze

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class TestExample {

    @Test
    fun `square should respect the perim and area formulas`() {
        val square = Square(2f)

        assertEquals(2f, square.side)
        assertEquals(8f, square.perim())
        assertEquals(4f, square.area())
        assertEquals("Hi I'm a square with side = 2.0", square.interfaceVariable)
        assertEquals(123, square.interfaceValue)
    }

    @Test
    fun `Greeting()_greeting() should start with 'Hello, '`() {
        assertTrue {
            Greeting().greeting().startsWith("Hello, ")
        }
    }
}