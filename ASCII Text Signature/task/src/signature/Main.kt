package signature

import java.io.File

data class Letter(val character: String, val width: Int, val ascii: List<String>)

fun main() {
    var romanMap: MutableMap<String, Letter> = fillFontMap("roman.txt")
    var mediumMap: MutableMap<String, Letter> = fillFontMap("medium.txt")

    println("Enter name and surname:")
    val fullName = readLine()
    println("Enter person's status:")
    val personStatus = readLine()

    printFullName(fullName, personStatus, romanMap, mediumMap)
}

fun fillFontMap(fontFile: String): MutableMap<String, Letter> {
    val fileUri = {}.javaClass.classLoader.getResource(fontFile)
    val fileLines = File(fileUri.toURI()).readLines()
    var letterMap: MutableMap<String, Letter> = mutableMapOf()
    val fileIterator = fileLines.iterator()
    val config = fileIterator.next().split(" ")
    val sizeOfLetters: Int = config[0].toInt()

    fillMapWithLetters(fileIterator, sizeOfLetters, letterMap)
    return letterMap
}

private fun fillMapWithLetters(
    fileIterator: Iterator<String>,
    sizeOfLetters: Int,
    letterMap: MutableMap<String, Letter>
) {
    while (fileIterator.hasNext()) {
        val letterConfig = fileIterator.next().split(" ")
        val letter: String = letterConfig[0]
        val size: Int = letterConfig[1].toInt()
        val asciiArray = mutableListOf<String>()
        for (i in 1..sizeOfLetters) {
            asciiArray.add(fileIterator.next())
        }
        letterMap[letter] = Letter(letter, size, asciiArray)
    }
}

private fun printFullName(
    fullName: String?,
    personStatus: String?,
    romanMap: MutableMap<String, Letter>,
    mediumMap: MutableMap<String, Letter>
) {
    fullName?.let {
        var lines: MutableList<String?> = mutableListOf()

        var nameAsAscii: MutableList<List<String>?> = convertNameToRoman(fullName, romanMap)
        val maxLetterHeight = getMaxLetterHeight(nameAsAscii)

        createFinalLinesFromName(maxLetterHeight, nameAsAscii, lines)

        val status = "$personStatus"
        // Print blank line or name
        createFinalLinesFromStatus(status, mediumMap, lines)
        val lineSize = calculateLineSize(lines)
        // Top line
        val topLine = buildLine("8", lineSize + 4)
        // Bottom line
        val bottomLine = buildLine("8", lineSize + 4)

        // Print
        printContent(topLine, lines, lineSize, bottomLine)
    }

}

private fun printContent(
    topLine: String,
    lines: MutableList<String?>,
    lineSize: Int,
    bottomLine: String
) {
    val ascii = mutableListOf<String>()
    ascii.add(topLine)
    // Full name and Status
    prepareNameAndStatusForPrinting(lines, lineSize, ascii)
    ascii.add(bottomLine)

    ascii.forEach { println(it) }
}

private fun prepareNameAndStatusForPrinting(
    lines: MutableList<String?>,
    lineSize: Int,
    ascii: MutableList<String>
) {
    lines.forEach { s ->
        val nameLine = s?.let { it1 -> buildTextLine(it1, lineSize) } ?: ""
        ascii.add(nameLine)
    }
}

private fun createFinalLinesFromStatus(
    status: String,
    mediumMap: MutableMap<String, Letter>,
    lines: MutableList<String?>
) {
    for (i in 0..2) {
        var line = ""
        status?.toCharArray().forEach { letter ->
            var letterArray = mediumMap.get(letter.toString())?.ascii
            if (letterArray.isNullOrEmpty()) {
                letterArray = createEmptyArrayForSep(3, 5)
            }
            letterArray?.let {
                when (line) {
                    "" -> line = letterArray!![i]
                    else -> line = line + letterArray!![i]
                }
            }
        }
        lines.add("$line")
    }
}

private fun createFinalLinesFromName(
    maxLetterHeight: Int,
    nameAsAscii: MutableList<List<String>?>,
    lines: MutableList<String?>
) {
    for (i in 0 until maxLetterHeight) {
        var line = ""
        nameAsAscii.stream().forEach { letterArray ->
            letterArray?.let {
                val updateLetterArray =
                    if (letterArray.isEmpty()) createEmptyArrayForSep(maxLetterHeight, 10) else letterArray
                when (line) {
                    "" -> line = updateLetterArray[i]
                    else -> line = line + updateLetterArray[i]
                }
            }
        }
        lines.add("$line")
    }
}

private fun convertNameToRoman(
    fullName: String,
    romanMap: MutableMap<String, Letter>
): MutableList<List<String>?> {
    var nameAsAscii: MutableList<List<String>?> = mutableListOf()
    fullName.toCharArray().map { c: Char ->
        val letter: Letter? = romanMap[c.toString()]
        letter?.let { it.ascii } ?: emptyList()
    }.toCollection(nameAsAscii)
    return nameAsAscii
}

fun createEmptyArrayForSep(maxLetterHeigth: Int, times: Int): List<String> {
    var list: MutableList<String> = mutableListOf()
    for(i in 0 until maxLetterHeigth){
        list.add(" ".repeat(times))
    }
    return list
}

fun getMaxLetterHeight(nameAsAscii: MutableList<List<String>?>): Int {
    var maxHeight = 0
    nameAsAscii.stream().forEach { it?.let { if (it.size > maxHeight) maxHeight = it.size } }
    return maxHeight
}

private fun calculateLineSize(lines: MutableList<String?>): Int {
    var maxLength = 0
    lines.stream().forEach { if (it?.length!! > maxLength) maxLength = it.length }
    return maxLength + 4
}

fun buildTextLine(name: String, lineSize: Int): String {
    var line = "88"
    for (i in 1..lineSize) {
        line += " "
    }
    line += "88"
    val linePlusAsterisk = lineSize + 4
    val lineLengthIsOdd = linePlusAsterisk % 2 != 0
    val nameLengthIsOdd = name.length % 2 != 0
    val middle: Int = (linePlusAsterisk) / 2 //- (if (lineLengthIsOdd) 1 else 0)
    val init = middle - (name.length / 2) - getInitOffset(lineLengthIsOdd, nameLengthIsOdd)
    val end = init + name.length

    return line.replaceRange(init, end, name)
}

private fun getInitOffset(lineIsOdd: Boolean, nameIsOdd: Boolean): Int{
    return when(lineIsOdd){
        true -> 0
        false -> (if (nameIsOdd) 1 else 0)
    }
}

private fun buildLine(s: String, size: Int): String {
    var line = s
    for (i in 1 until size) {
        line += s
    }
    return line
}
