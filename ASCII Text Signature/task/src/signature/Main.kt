package signature

fun main() {
    println("Enter name and surname:")
    val fullName = readLine()
    println("Enter person's status:")
    val personStatus = readLine()

    printFullName(fullName, personStatus)
}

private fun printFullName(fullName: String?, personStatus: String?) {
    fullName?.let {
        var nameAsAscii: MutableList<Array<String>?> = mutableListOf()
        fullName.toCharArray().map { c: Char ->
            asciiLetters[c.toString().toUpperCase()]
        }.toCollection(nameAsAscii)

        var lines: MutableList<String?> = mutableListOf()
        for (i in 0..2){
            var line = ""
            nameAsAscii.stream().forEach { letterArray ->
                letterArray?.let {
                    when(line){
                        "" -> line = letterArray[i]
                        else -> line = line + " " + letterArray[i]
                    }
                }
            }
            lines.add("$line")
        }

        val status = "$personStatus"
        val lineSize = calculateLineSize(lines, status)
        // Top line
        val topLine = buildLine("*", lineSize + 2)
        // Print blank line or name
        val statusLine = status?.let { buildTextLine(status, lineSize) }
        // Bottom line
        val bottomLine = buildLine("*", lineSize + 2)

        // Print
        val ascii = mutableListOf<String>()
        ascii.add(topLine)
        // Full name
        lines.forEach { s ->
            val nameLine = s?.let { it1 -> buildTextLine(it1, lineSize) } ?: ""
            ascii.add(nameLine)
        }
        statusLine?.let { ascii.add(statusLine) }
        ascii.add(bottomLine)

        ascii.forEach { println(it) }
    }

}

private fun calculateLineSize(lines: MutableList<String?>, personStatus: String?): Int {
    val nameSize = lines[0]?.let { it.length  } ?: 0
    val statusSize = personStatus?.let { it.length } ?: 0
    return maxOf(nameSize, statusSize) + 4
}

fun buildTextLine(name: String, lineSize: Int): String {
    var line = "*"
    for (i in 1..lineSize) {
        line += " "
    }
    line += "*"
    val linePlusAsterisk = lineSize + 2
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
