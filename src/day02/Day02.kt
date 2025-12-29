package day02

import kotlin.io.path.Path
import kotlin.io.path.readText

var debugMode = true

fun println(string: String) {
    if (debugMode) kotlin.io.println(string)
}

fun String.hasEvenDigits(): Boolean = this.removePrefix("-").length.rem(2) == 0

fun main() {

    fun readInput(fileName: String): String = Path("src/day02/$fileName.txt").readText().trim()

    fun parse(input: String): List<Long> = input.split(",").flatMap {
        val ends = it.split("-")
        LongRange(start = ends.first().toLong(), endInclusive = ends.last().toLong())
    }

    fun partOne(fileName: String): List<Long> {
        val inputRanges = parse(readInput(fileName))
        println("Count: ${inputRanges.size}")
        val invalidIds = mutableListOf<Long>()
        for (i in inputRanges) {
            val inputId = i.toString()
            if (inputId.hasEvenDigits()) {
                val half = inputId.length / 2
                val firstPart = inputId.substring(0, half)
                val secondPart = inputId.substring(half, inputId.length)
                if (firstPart == secondPart) invalidIds.add(i)
            }
        }
        println("InvalidIds: $invalidIds")
        return invalidIds.toList()
    }

    fun partTwo(fileName: String): List<Long> {
        val inputRanges = parse(readInput(fileName))
        println("Count: ${inputRanges.size}")
        val invalidIds = mutableListOf<Long>()
        for (input in inputRanges) {
            val inputId = input.toString()
            val maxChunkSize = inputId.length / 2
            for (chunk in 1 .. maxChunkSize) {
                val idChunks = inputId.chunked(chunk)
                val invalid = idChunks.all { it == idChunks.first() }
                if (invalid) {
                    invalidIds.add(input)
                    break
                }
            }
        }
        println("InvalidIds: $invalidIds")
        return invalidIds.toList()
    }

    fun sumAllInvalidIds(invalidIds: () -> List<Long>): Long = invalidIds().sum()

    println("Sum of all invalidIds: ${sumAllInvalidIds { partOne("input") }}")
    println("Sum of all invalidIds: ${sumAllInvalidIds { partTwo("input") }}")
}