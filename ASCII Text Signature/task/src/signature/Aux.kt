package signature

val SEPARATOR = arrayOf("    ", "    ", "    ")

val A = arrayOf("____", "|__|", "|  |")
val B = arrayOf("___ ", "|__]", "|__]")
val C = arrayOf("____", "|   ", "|___")
val D = arrayOf("___ ", "|  \\", "|__/")
val E = arrayOf("____", "|___", "|___")
val F = arrayOf("____", "|___", "|   ")
val G = arrayOf("____", "| __", "|__]")
val H = arrayOf("_  _", "|__|", "|  |")
val I = arrayOf("_", "|", "|")
val J = arrayOf(" _", " |", "_|")
val K = arrayOf("_  _", "|_/ ", "| \\_")
val L = arrayOf("_   ", "|   ", "|___")
val M = arrayOf("_  _", "|\\/|", "|  |")
val N = arrayOf("_  _", "|\\ |", "| \\|")
val O = arrayOf("____", "|  |", "|__|")
val P = arrayOf("___ ", "|__]", "|   ")
val Q = arrayOf("____", "|  |", "|_\\|")
val R = arrayOf("____", "|__/", "|  \\")
val S = arrayOf("____", "[__ ", "___]")
val T = arrayOf("___", " | ", " | ")
val U = arrayOf("_  _", "|  |", "|__|")
val V = arrayOf("_  _", "|  |", " \\/ ")
val W = arrayOf("_ _ _", "| | |", "|_|_|")
val X = arrayOf("_  _", " \\/ ", "_/\\_")
val Y = arrayOf("_   _", " \\_/ ", "  |  ")
val Z = arrayOf("___ ", "  / ", " /__")

val asciiLetters = mapOf<String, Array<String>>(
    " " to SEPARATOR,
    "A" to A,
    "B" to B,
    "C" to C,
    "D" to D,
    "E" to E,
    "F" to F,
    "G" to G,
    "H" to H,
    "I" to I,
    "J" to J,
    "K" to K,
    "L" to L,
    "M" to M,
    "N" to N,
    "O" to O,
    "P" to P,
    "Q" to Q,
    "R" to R,
    "S" to S,
    "T" to T,
    "U" to U,
    "V" to V,
    "W" to W,
    "X" to X,
    "Y" to Y,
    "Z" to Z
)