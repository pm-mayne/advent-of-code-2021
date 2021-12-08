package day08

object DigitSegmentScreen {

    fun countEasyDigits(input: List<String>): Int {
        return input.flatMap { it.split(" | ")[1].split(" ") }
            .filter { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }
            .count()
    }

    fun decodeScreens(input: List<String>): Int {
        return input
            .map {
                val parts = it.split(" | ")
                parts[0] to parts[1]
            }
            .map {
                val decoder = it.first.toDecoder()
                val valuesToDecode = it.second.split(" ")
                decoder.decode(valuesToDecode)
            }
            .reduce { a, b -> a + b }
    }
}


private fun String.toDecoder(): Decoder {
    val decoder = Decoder()
    val values = this.split(" ")

    val cf = values.find { it.length == 2 }!!.toMutableList() // lf 1 (cf)
    val a = values.find { it.length == 3 }!!.toMutableList() // lf 7 (acf)
    a.removeAll(cf)
    decoder.code[a[0]] = 'a'

    val bd = values.find { it.length == 4 }!!.toMutableList() // lf 4 (bcdf)
    bd.removeAll(cf)

    val bcdf = values.find { it.length == 4 }!!.toMutableList() // lf 4 (bcdf)
    val acf = values.find { it.length == 3 }!!.toMutableList() // lf 7 (acf)


    val g = values.filter { it.length == 5 }
        .find { it.contains(bd[0]) && it.contains(bd[1]) }!! // Looking for a 5 (abdfg)
        .toMutableList()
    g.removeAll(a)
    g.removeAll(bcdf)

    val e = values.find { it.length == 7 }!!.toMutableList() // Looking for an 8
    e.removeAll(acf)
    e.removeAll(bcdf)
    e.removeAll(g)

    val abdfg = values.filter { it.length == 5 }
        .find { it.contains(bd[0]) && it.contains(bd[1]) }!! // Looking for a 5 (abdfg)
        .toMutableList()
    val c = mutableListOf(cf[0], cf[1])
    c.removeAll(abdfg)

    val f = values.find { it.length == 2 }!!.toMutableList()
    f.removeAll(c)

    val d = values.filter { it.length == 5 }
        .find { it.contains(a[0]) && it.contains(c[0]) && it.contains(e[0]) && it.contains(g[0]) }!! // Looking for a 2 (acdeg)
        .toMutableList()

    d.remove(g[0])
    d.remove(c[0])
    d.remove(e[0])
    d.remove(a[0])

    val b = mutableListOf(bd[0], bd[1])
    b.remove(d[0])

    decoder.code[a[0]] = 'a'
    decoder.code[b[0]] = 'b'
    decoder.code[c[0]] = 'c'
    decoder.code[d[0]] = 'd'
    decoder.code[e[0]] = 'e'
    decoder.code[f[0]] = 'f'
    decoder.code[g[0]] = 'g'

    return decoder
}

data class Decoder(val code: MutableMap<Char, Char> = mutableMapOf()) {
    fun decode(valuesToDecode: List<String>): Int {
        return valuesToDecode.joinToString("") {
            val displayed = it
                .map { c -> code[c] }
                .toDisplayedDigit()
            displayed
        }.toInt()

    }

}

private fun List<Char?>.toDisplayedDigit(): String {
    if (this.size == 2) {
        return "1"
    }
    if (this.size == 3) {
        return "7"
    }
    if (this.size == 4) {
        return "4"
    }
    if (this.size == 5) {
        return when {
            this.contains('e') -> {
                "2"
            }
            this.contains('b') -> {
                "5"
            }
            else -> {
                "3"
            }
        }
    }
    if (this.size == 6) {
        return when {
            !this.contains('d') -> {
                "0"
            }
            this.contains('e') -> {
                "6"
            }
            else -> {
                "9"
            }
        }
    }
    return "8"


}
