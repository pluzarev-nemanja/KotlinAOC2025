package day01

import readInput
import kotlin.math.absoluteValue
import kotlin.math.sign

var debugMode = true
fun println(string: String) {
    if (debugMode) kotlin.io.println(string)
}

fun main() {

    fun parseInput(fileName: String): List<Int> = readInput(fileName, "day01").map {
        val value = it.drop(1).toInt()
        if (it.first() == 'L') -value else value
    }

    fun firstPart(fileName: String, initialPosition: Int = 50, range: IntRange = 0..99): Int {
        val rotations = parseInput(fileName)
        println("Rotations: $rotations")
        var counter = 0
        var position = initialPosition
        for (rotation in rotations) {
            val step = rotation.sign
            repeat(rotation.absoluteValue) {
                position += step
                if (position < range.first) position = range.last
                if (position > range.last) position = range.first
                if (position == 0) counter++
            }
        }
        return counter
    }
    println("Password number is : ${firstPart("input")}")
}
