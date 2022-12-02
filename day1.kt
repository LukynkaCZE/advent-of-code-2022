import java.io.File;

fun main() {
    val lineList = mutableListOf<String>()
    val elves = mutableListOf<Int>()
    var currentElf = 0

    File("day1_input.txt").useLines { lines -> lines.forEach { lineList.add(it) }}
    lineList.forEach {
        if (it != "") { currentElf += Integer.parseInt(it) }
        else {elves.add(currentElf); currentElf = 0}
    }
    elves.sortDescending()
    elves.forEach {println(it)}
    var topThreeElvesTotal = elves[0] + elves[1] + elves[2] 

    println("[Solution to Part 1] The Elf carrying the most calories has total of ${elves[0]} calories!")
    println("[Solution to Part 2] The top 3 Elves are carrying total of ${topThreeElvesTotal} calories!")
}