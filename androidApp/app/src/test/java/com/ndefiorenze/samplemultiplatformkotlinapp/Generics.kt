package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.Animal
import com.ndefiorenze.AnimalBox
import com.ndefiorenze.Box
import com.ndefiorenze.Cat
import com.ndefiorenze.Dog
import com.ndefiorenze.mapIntegersToStrings
import junit.framework.Assert.assertEquals
import org.junit.Test

class Generics {

    @Test
    fun genericsBasicUsage() {
        val intBox = Box(8)
        val stringBox = Box("Hi")

        assertEquals(8, intBox.elemet)
        assertEquals("Hi", stringBox.elemet)

        // intBox.elemet = "World" <--- compilation error
    }

    @Test
    fun genericsUsage() {
        val animalBox: AnimalBox<Animal> = AnimalBox(Dog())
        assertEquals("woof", animalBox.elemet.speak())
        animalBox.elemet = Cat()
        assertEquals("meow", animalBox.elemet.speak())

        // animalBox.elemet = 2 <--- compilation error
    }

    @Test
    fun genericsMapUsage() {
        mapIntegersToStrings[1] = "one"
        mapIntegersToStrings[2] = "two"
        // mapIntegersToStrings[3] = 3 <--- compilation error
        // mapIntegersToStrings["three"] = "three" <--- compilation error
    }

}