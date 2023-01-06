import java.io.File;

fun main() {
    val inputFile = mutableListOf<String>() 
    File("day2_input.txt").useLines { lines -> lines.forEach { inputFile.add(it) }}
    var overallScore = 0
    var overallScore2 = 0

    // SOLUTION TO PART 1
    inputFile.forEach {
        val split = it.split(" ")
        val elf = parseRPSType(split[0]);
        val me = parseRPSType(split[1]);
        overallScore += getRPSScore(me, runRPSMatch(elf, me))
    }
    println("[Solution to Part 1] My Overall Score: ${overallScore}")

    //SOLUTION TO PART 2
    inputFile.forEach {
        val split = it.split(" ")
        val result = parseRPSResult(split[1])
        val elf = parseRPSType(split[0])
        val me = pickRPSTypeByResult(elf, result)
        overallScore2 += getRPSScore(me, runRPSMatch(elf, me))
    }
    println("[Solution to Part 2] My Overall Score: ${overallScore2}")
}

// RPS Match Functions
fun runRPSMatch(elf: RPSType, me: RPSType): RPSResult {
    if(elf == me) {
        return RPSResult.Draw;
    } else if (elf == RPSType.Rock) {
        if(me == RPSType.Paper) {
            return RPSResult.Won
        } else {
            return RPSResult.Lost
        }
    } else if (elf == RPSType.Paper) {
        if(me == RPSType.Scissors) {
            return RPSResult.Won
        } else {
            return RPSResult.Lost
        }
    } else if (elf == RPSType.Scissors) {
        if(me == RPSType.Rock) {
            return RPSResult.Won
        } else {
            return RPSResult.Lost
        }
    }
    return RPSResult.Draw
}


// SCORING STUFF

//Get total amount of score from match and shape picked
fun getRPSScore(shape: RPSType, result: RPSResult): Int {
    return getScoreFromShape(shape) + getScoreFromResult(result);
}

//Get score from shape picked
fun getScoreFromShape(shape: RPSType): Int {
    when (shape) {
        RPSType.Rock -> return 1;
        RPSType.Paper -> return 2;
        RPSType.Scissors -> return 3;
    }
}

//Get score from result of RPS Match
fun getScoreFromResult(result: RPSResult): Int {
    when (result) {
        RPSResult.Lost -> return 0;
        RPSResult.Draw -> return 3;
        RPSResult.Won -> return 6;
    }
}

//Parse "ABCXYZ" into RPSType (Shape)
fun parseRPSType(input: String): RPSType {
    if(input == "A" || input == "X") {
        return RPSType.Rock;
    } else if(input == "B" || input == "Y") {
        return RPSType.Paper
    } else if(input == "C" || input == "Z") {
        return RPSType.Scissors
    }
    return RPSType.Rock;
}


// FUNCTIONS USED IN PART 2 OF THE CHALLANGE

//Picks shape for me based on what elf picked and what result of the RPS Match must be
fun pickRPSTypeByResult(elf: RPSType, result: RPSResult): RPSType {
    when(result) {
        RPSResult.Lost -> return getLosingRPSType(elf)
        RPSResult.Draw -> return elf
        RPSResult.Won -> return getWinningRPSType(elf)
    }
}

//Gets winning shape based on what elf picked
fun getWinningRPSType(elf: RPSType): RPSType {
    when(elf) {
        RPSType.Rock -> return RPSType.Paper
        RPSType.Paper -> return RPSType.Scissors
        RPSType.Scissors -> return RPSType.Rock
    }
}

//Gets losing shape based on what elf picked
fun getLosingRPSType(elf: RPSType) : RPSType {
    when(elf) {
        RPSType.Rock -> return RPSType.Scissors
        RPSType.Paper -> return RPSType.Rock
        RPSType.Scissors -> return RPSType.Paper
    }
}

//Parse "XYZ" into RPSResult (Result of the RPS Match)
fun parseRPSResult(result: String): RPSResult {
    when(result) {
        "X" -> return RPSResult.Lost
        "Y" -> return RPSResult.Draw
        "Z" -> return RPSResult.Won
        else -> return RPSResult.Draw
    }
}

// ENUM CLASSES
// to make everything more clean ;)

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