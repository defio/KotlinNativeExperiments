package com.ndefiorenze

data class Box<T>(var elemet: T)

data class AnimalBox<T : Animal>(var elemet: T)

val mapIntegersToStrings: MutableMap<Int, String> = mutableMapOf()

interface Animal {
    fun speak(): String
}

class Dog : Animal {
    override fun speak() = "woof"
}

class Cat : Animal {
    override fun speak() = "meow"
}