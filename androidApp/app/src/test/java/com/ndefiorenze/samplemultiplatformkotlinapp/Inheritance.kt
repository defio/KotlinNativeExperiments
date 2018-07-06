package com.ndefiorenze

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

class Inheritance {

    @Test
    fun inheritanceForFinalClass() {
        // class Attempt(val parentValue: Int) : CannotInheritFromMe(parentValue) <--- compilation error
    }

    @Test
    fun inheritanceForOpenClass() {
        class Attempt(val childValue: String, parentValue: Int) : YouCanInheritFromMe(parentValue)


        val attempt = Attempt(childValue = "value for Child", parentValue = 1)

        assertEquals("value for Child", attempt.childValue)
        assertEquals(1, attempt.parentValue)
    }

    @Test
    fun inheritanceForAbstractClass() {
        class Attempt(val childValue: String, parentValue: Int) : AbstractClass(parentValue) {
            override fun transformValue(): String {
                return "$childValue : $abstractClassValue"
            }
        }

        val attempt = Attempt(childValue = "value for Child", parentValue = 1)

        assertEquals("value for Child", attempt.childValue)
        assertEquals(1, attempt.abstractClassValue)
        assertEquals("value for Child : 1", attempt.transformValue())
        assertEquals(19, attempt.concreteFunction())
    }

    @Test
    fun inheritanceForInterface() {
        class Rect(val width: Float, val height: Float) : Shape {
            override var interfaceVariable = "Hi I'm a rect with width = $width and height = $height"
            override val interfaceValue: Int = 456

            override fun area() = width * height

            override fun perim() = 2 * (width + height)
        }

        val rect = Rect(2f, 3f)
        val square = Square(2f)

        assertTrue(rect is Shape)
        assertTrue(square is Shape)

        assertEquals(10f, rect.perim())
        assertEquals(6f, rect.area())
        assertEquals("Hi I'm a rect with width = 2.0 and height = 3.0", rect.interfaceVariable)
        assertEquals(456, rect.interfaceValue)

        assertEquals(2f, square.side)
        assertEquals(8f, square.perim())
        assertEquals(4f, square.area())
        assertEquals("Hi I'm a square with side = 2.0", square.interfaceVariable)
        assertEquals(123, square.interfaceValue)
    }


}