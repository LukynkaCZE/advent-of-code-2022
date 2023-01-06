// This is solution to Advent of Code Day 2: Rock Paper Scissors
// You can find the challenge at https://adventofcode.com/2022/day/2
// You can find the challenge input at https://adventofcode.com/2022/day/2/input

import java.io.File

fun main() {
    val inputFile = mutableListOf<String>() 
    File("day2_input.txt").useLines { lines -> lines.forEach { inputFile.add(it) }}
    var overallScore = 0
    var overallScore2 = 0

    // SOLUTION TO PART 1
    inputFile.forEach {
        val split = it.split(" ")
        val elf = parseRPSType(split[0])
        val me = parseRPSType(split[1])
        overallScore += getRPSScore(me, runRPSMatch(elf, me))
    }
    println("[Solution to Part 1] My Overall Score: $overallScore")

    //SOLUTION TO PART 2
    inputFile.forEach {
        val split = it.split(" ")
        val result = parseRPSResult(split[1])
        val elf = parseRPSType(split[0])
        val me = pickRPSTypeByResult(elf, result)
        overallScore2 += getRPSScore(me, runRPSMatch(elf, me))
    }
    println("[Solution to Part 2] My Overall Score: $overallScore2")
}

// RPS Match Functions
fun runRPSMatch(elf: RPSType, me: RPSType): RPSResult {
    when (elf) {
        me -> {
            return RPSResult.Draw
        }
        RPSType.Rock -> {
            return if(me == RPSType.Paper) {
                RPSResult.Won
            } else {
                RPSResult.Lost
            }
        }
        RPSType.Paper -> {
            return if(me == RPSType.Scissors) {
                RPSResult.Won
            } else {
                RPSResult.Lost
            }
        }
        RPSType.Scissors -> {
            return if(me == RPSType.Rock) {
                RPSResult.Won
            } else {
                RPSResult.Lost
            }
        }
        else -> return RPSResult.Draw
    }
}


// SCORING STUFF

//Get total amount of score from match and shape picked
fun getRPSScore(shape: RPSType, result: RPSResult): Int {
    return getScoreFromShape(shape) + getScoreFromResult(result)
}

//Get score from shape picked
fun getScoreFromShape(shape: RPSType): Int {
    return when (shape) {
        RPSType.Rock -> 1
        RPSType.Paper -> 2
        RPSType.Scissors -> 3
    }
}

//Get score from result of RPS Match
fun getScoreFromResult(result: RPSResult): Int {
    return when (result) {
        RPSResult.Lost -> 0
        RPSResult.Draw -> 3
        RPSResult.Won -> 6
    }
}

//Parse "ABCXYZ" into RPSType (Shape)
fun parseRPSType(input: String): RPSType {
    return when (input) {
        "A", "X" -> {
            RPSType.Rock
        }
        "B", "Y" -> {
            RPSType.Paper
        }
        "C", "Z" -> {
            RPSType.Scissors
        }
        else -> RPSType.Rock
    }
}


// FUNCTIONS USED IN PART 2 OF THE CHALLENGE

//Picks shape for me based on what elf picked and what result of the RPS Match must be
fun pickRPSTypeByResult(elf: RPSType, result: RPSResult): RPSType {
    return when(result) {
        RPSResult.Lost -> getLosingRPSType(elf)
        RPSResult.Draw -> elf
        RPSResult.Won -> getWinningRPSType(elf)
    }
}

//Gets winning shape based on what elf picked
fun getWinningRPSType(elf: RPSType): RPSType {
    return when(elf) {
        RPSType.Rock -> RPSType.Paper
        RPSType.Paper -> RPSType.Scissors
        RPSType.Scissors -> RPSType.Rock
    }
}

//Gets losing shape based on what elf picked
fun getLosingRPSType(elf: RPSType) : RPSType {
    return when(elf) {
        RPSType.Rock -> RPSType.Scissors
        RPSType.Paper -> RPSType.Rock
        RPSType.Scissors -> RPSType.Paper
    }
}

//Parse "XYZ" into RPSResult (Result of the RPS Match)
fun parseRPSResult(result: String): RPSResult {
    return when(result) {
        "X" -> RPSResult.Lost
        "Y" -> RPSResult.Draw
        "Z" -> RPSResult.Won
        else -> RPSResult.Draw
    }
}

// ENUM CLASSES
// to make everything more clean

//Shape
enum class RPSType {
    Rock,
    Paper,
    Scissors,
}

//Match Result
enum class RPSResult {
    Lost,
    Draw,
    Won
}