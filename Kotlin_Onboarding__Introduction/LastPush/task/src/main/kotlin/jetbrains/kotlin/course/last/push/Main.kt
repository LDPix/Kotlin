package jetbrains.kotlin.course.last.push

// You will use this function later
fun getPattern(): String {
    println(
        "Do you want to use a pre-defined pattern or a custom one? " +
                "Please input 'yes' for a pre-defined pattern or 'no' for a custom one"
    )
    do {
        when (safeReadLine()) {
            "yes" -> {
                return choosePattern()
            }
            "no" -> {
                println("Please, input a custom picture")
                return safeReadLine()
            }
            else -> println("Please input 'yes' or 'no'")
        }
    } while (true)
}

// You will use this function later
fun choosePattern(): String {
    do {
        println("Please choose a pattern. The possible options: ${allPatterns().joinToString(", ")}")
        val name = safeReadLine()
        val pattern = getPatternByName(name)
        pattern?.let {
            return@choosePattern pattern
        }
    } while (true)
}

fun getPatternHeight(pattern: String): Int {
    return(pattern.lines().size)
}

fun fillPatternRow(patternRow: String, patternWidth: Int): String {
    if (patternRow.length > patternWidth) {
        throw IllegalStateException()
    }
    val sb = StringBuilder()
    sb.append(patternRow)
    sb.append(separator.toString().repeat(patternWidth - patternRow.length))
    return sb.toString()
}

fun repeatHorizontally(pattern: String, n: Int, patternWidth: Int): String {
    val sb = StringBuilder()
    for (line in pattern.lines()) {
        sb.append(fillPatternRow(line, patternWidth).repeat(n))
        sb.append('\n')
    }
    return sb.toString()
}

fun dropTopLine(image: String, width: Int, patternHeight: Int, patternWidth: Int): String {
    if (patternHeight > 1) {
        return image.drop(patternWidth * width + newLineSymbol.length)
    }
    return image
}

fun canvasGenerator(pattern: String, width: Int, height: Int): String {
    val sb = StringBuilder()
    val repeated = repeatHorizontally(pattern, width, getPatternWidth(pattern))
    sb.append(repeated)
    sb.append(dropTopLine(repeated, width, getPatternHeight(pattern), getPatternWidth(pattern)).repeat(height - 1))
    return sb.toString()
}

fun canvasWithGapsGenerator(pattern: String, width: Int, height: Int): String {
    if (width == 1) {
        val sb = StringBuilder()
        val repeated = repeatHorizontally(pattern, width, getPatternWidth(pattern))
        sb.append(repeated)
        sb.append(repeated.repeat(height - 1))
        return sb.toString()
    }
    val patH = getPatternHeight(pattern)
    val patW = getPatternWidth(pattern)      // assumed to exist, like in your other code
    val patternLines = pattern.lines()
    val paddedPatternLines = patternLines.map { fillPatternRow(it, patW) }
    val gapChunk = " ".repeat(patW)
    val sb = StringBuilder()

    for (level in 0 until height) {
        for (lineIdx in 0 until patH) {
            val row = StringBuilder()
            for (pos in 0 until width) {
                val isGap =
                    (level % 2 == 0 && pos % 2 == 1) ||
                            (level % 2 == 1 && pos % 2 == 0)
                if (isGap) {
                    row.append(gapChunk)
                } else {
                    row.append(paddedPatternLines[lineIdx])
                }
            }
            sb.append(row).append('\n')
        }
    }
    return sb.toString()
}

fun applyGenerator(pattern: String, generatorName: String, width: Int, height: Int): String {
    if (generatorName == "canvas") {
        return canvasGenerator(pattern, width, height)
    }
    return canvasWithGapsGenerator(pattern, width, height)
}

// You will use this function later
fun chooseGenerator(): String {
    var toContinue = true
    var generator = ""
    println("Please choose the generator: 'canvas' or 'canvasGaps'.")
    do {
        when (val input = safeReadLine()) {
            "canvas", "canvasGaps" -> {
                toContinue = false
                generator = input
            }
            else -> println("Please, input 'canvas' or 'canvasGaps'")
        }
    } while (toContinue)
    return generator
}

// You will use this function later
fun safeReadLine(): String = readlnOrNull() ?: error("Your input is incorrect, sorry")

fun main() {
    // Uncomment this code on the last step of the game

     val pattern = getPattern()
     val generatorName = chooseGenerator()
     println("Please input the width of the resulting picture:")
     val width = safeReadLine().toInt()
     println("Please input the height of the resulting picture:")
     val height = safeReadLine().toInt()

     println("The pattern:$newLineSymbol${pattern.trimIndent()}")

     println("The generated image:")
     println(applyGenerator(pattern, generatorName, width, height))
}
