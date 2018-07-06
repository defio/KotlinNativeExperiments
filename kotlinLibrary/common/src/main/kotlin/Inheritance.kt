package com.ndefiorenze

import kotlin.math.pow

class CannotInheritFromMe(val parentValue: Int)

open class YouCanInheritFromMe(val parentValue: Int)

abstract class AbstractClass(val abstractClassValue: Int) {

    abstract fun transformValue(): String

    fun concreteFunction(): Int{
        return transformValue().length
    }

}

interface Shape {

    var interfaceVariable: String
    val interfaceValue: Int

    fun area(): Float
    fun perim(): Float
}

class Square(val side: Float) : Shape {

    override var interfaceVariable: String = "Hi I'm a square with side = $side"
    override val interfaceValue: Int = 123

    override fun area() = side.pow(2)

    override fun perim() = 4 * side

}