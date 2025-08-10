package jetbrains.kotlin.course.almost.done

fun trimPicture(picture: String): String {
    return picture.trimIndent()
}

fun applyBordersFilter(picture: String): String {
    val pictureWidth = getPictureWidth(picture)
    val sb = StringBuilder()
    sb.append(borderSymbol.toString().repeat(pictureWidth + 4))
    sb.append('\n')
    val lines = picture.lines()
    for (line in lines) {
        val sb1 = StringBuilder()
        sb1.append(borderSymbol)
        sb1.append(separator)
        sb1.append(line)
        sb1.append(separator.toString().repeat(pictureWidth + 1 - line.length))
        sb1.append(borderSymbol)
        sb.append(sb1.toString())
        sb.append('\n')
    }
    sb.append(borderSymbol.toString().repeat(pictureWidth + 4))
    return sb.toString()
}

fun applySquaredFilter(picture: String): String {
    val pictureWidth = getPictureWidth(picture)
    val sb = StringBuilder()
    sb.append(borderSymbol.toString().repeat(pictureWidth + 4))
    sb.append(borderSymbol.toString().repeat(pictureWidth + 4))
    sb.append('\n')
    val lines = picture.lines()
    for (line in lines) {
        val sb1 = StringBuilder()
        sb1.append(borderSymbol)
        sb1.append(separator)
        sb1.append(line)
        sb1.append(separator.toString().repeat(pictureWidth + 1 - line.length))
        sb1.append(borderSymbol)
        sb.append(sb1.toString())
        sb.append(sb1.toString())
        sb.append('\n')
    }
    sb.append(borderSymbol.toString().repeat(pictureWidth + 4))
    sb.append(borderSymbol.toString().repeat(pictureWidth + 4))
    sb.append('\n')
    for (line in lines) {
        val sb1 = StringBuilder()
        sb1.append(borderSymbol)
        sb1.append(separator)
        sb1.append(line)
        sb1.append(separator.toString().repeat(pictureWidth + 1 - line.length))
        sb1.append(borderSymbol)
        sb.append(sb1.toString())
        sb.append(sb1.toString())
        sb.append('\n')
    }
    sb.append(borderSymbol.toString().repeat(pictureWidth + 4))
    sb.append(borderSymbol.toString().repeat(pictureWidth + 4))
    return sb.toString()
}

fun safeReadLine(): String {
    val a = readlnOrNull()
    return a?: error("")
}

fun chooseFilter(): String {
    print("Please choose the filter: 'borders' or 'squared'.")
    var a = safeReadLine()
    while (a != "borders" && a != "squared") {
        print("Please input 'borders' or 'squared'")
        a = safeReadLine()
    }
    return a
}

fun choosePicture(): String {
    print("Please choose a picture. The possible options are: spongeBob, simba, brianGriffin, cat, pig, fox, monkey, elephant, android, apple")
    var a = safeReadLine()
    while (getPictureByName(a) == null) {
        print("Please choose a picture. The possible options are: spongeBob, simba, brianGriffin, cat, pig, fox, monkey, elephant, android, apple")
        a = safeReadLine()
    }
    return getPictureByName(a)?: error("")
}

fun getPicture(): String {
    print("Do you want to use a predefined picture or a custom one? Please input 'yes' for a predefined image or 'no' for a custom one")
    var a = safeReadLine()
    while (a != "yes" && a!= "no") {
        print("Please input 'yes' or 'no'")
        a = safeReadLine()
    }
    if (a == "yes") {
        return choosePicture()
    } else {
        print("Please input a custom picture")
        return safeReadLine()
    }
}

fun applyFilter(picture: String, filter: String): String {
    if (filter == "borders") {
        return applyBordersFilter(picture)
    } else {
        return applySquaredFilter(picture)
    }
}

fun photoshop(): Unit {
    val pic = getPicture()
    val filter = chooseFilter()
    print("The old image:")
    print(pic)
    val newpic = applyFilter(pic, filter)
    print("The transformed picture:\n")
    print(newpic)
}

fun main() {
    // Uncomment this code on the last step of the game

     photoshop()
}
