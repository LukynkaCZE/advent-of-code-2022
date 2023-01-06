// This is solution to Advent of Code Day 3: Rucksack Reorganization
// You can find the challenge at https://adventofcode.com/2022/day/3
// You can find the challenge input at https://adventofcode.com/2022/day/3/input

import java.io.File

fun main() {
    val inputArray = mutableListOf<String>()
    File("day3_input.txt").useLines { lines -> lines.forEach { inputArray.add(it) }}

    var totalPrioritySum = 0

    // PART 1
    for (input in inputArray) {
        val compartments = splitRucksackIntoCompartments(input)
        val sameTypes = findSameTypesInCompartments(compartments)
        for(type in sameTypes) {
            totalPrioritySum += getPriorityFromItem(type)
        }
    }
    println("[Solution to Part 1] Sum of priorities is: $totalPrioritySum")

    //PART 2
    var badges = mutableListOf<Char>()
    var group = mutableListOf<String>()
    var badgePrioritySum = 0

    for(input in inputArray) {
        group.add(input)
        if(group.size == 3) {
            badges.add(getBadgeFromGroupOfRucksacks(group))
            group = mutableListOf<String>()
        }
    }
    for(badge in badges) {
        badgePrioritySum += getPriorityFromItem(badge)
    }
    println("[Solution to Part 2] Sum of priorities from all badges is: $badgePrioritySum")
}

fun splitRucksackIntoCompartments(rucksack: String): MutableList<String> {
    val half = (rucksack.length + 1) / 2
    val half1 = rucksack.substring(0, half)
    val half2 = rucksack.substring(half)

    return mutableListOf<String>(half1, half2)
}

fun getBadgeFromGroupOfRucksacks(rucksacks: MutableList<String>): Char {
    val sameCharacters = mutableListOf<Char>()
    val sameCharactersInTwoRucksacks =  mutableListOf<Char>()
    for(character in rucksacks[0]) {
        if(rucksacks[1].contains(character)) {
            if(sameCharactersInTwoRucksacks.contains(character)) {continue}
            sameCharactersInTwoRucksacks.add(character)
        }
    }
    for(character in rucksacks[2]) {
        if(sameCharactersInTwoRucksacks.contains(character)) {
            if(sameCharacters.contains(character)) {continue}
            sameCharacters.add(character)
        }
    }
    return sameCharacters[0]
}

fun findSameTypesInCompartments(compartments: MutableList<String>): MutableList<Char> {
    val sameCharacters = mutableListOf<Char>()
    for (character in compartments[0]) {
        if(compartments[1].contains(character)) {
            if(sameCharacters.contains(character)) {continue}
            sameCharacters.add(character)
        }
    }
    return sameCharacters
}

fun getPriorityFromItem(item: Char): Int {
    var subNumber = 96
    if(item.isUpperCase()) {subNumber = 38}

    val ascii = item.code
    val index = ascii - subNumber
    return index
}