object BITS {

    fun sumOfVersions(message: String): Int {
        val byteString = message.hexToByte()
        return sumOfVersionsOfBinaryString(byteString)
    }

    fun sumOfVersionsOfBinaryString(byteString: String): Int {
        var versionSum = 0
        var currentPacket = Packet()
        var i = 0
        while (i < byteString.length) {
            if (currentPacket.s == "") {
                if (byteString.length - i < 11) {
                    break
                }
                currentPacket.s += byteString.substring(i, i + 3)
                i += 3
                val version = currentPacket.s.toInt(2)
                versionSum += version
            } else if (currentPacket.s.length == 3) {
                val id = byteString.substring(i, i + 3)
                currentPacket.s += id
                i += 3
                currentPacket.id = id.toInt(2)
            } else if (currentPacket.id == 4) {
                val payload = byteString.substring(i, i + 5)
                currentPacket.s += payload
                i += 5
                if (payload[0] == '0') {
                    //End of standard packet
                    currentPacket = Packet()
                }
            } else if (currentPacket.lengthId == null) {
                //Operator length ID
                val lengthId = byteString[i]
                currentPacket.s += lengthId
                currentPacket.lengthId = lengthId
                i += 1
            } else if (currentPacket.lengthId == '1') {
                val lengthOfBytes = byteString.substring(i, i + 11)
                currentPacket.s += lengthOfBytes
                val subPacketNb = lengthOfBytes.toInt(2)
                i += 11
                currentPacket = Packet()

            } else if (currentPacket.lengthId == '0') {
                val packetNumber = byteString.substring(i, i + 15)
                currentPacket.s += packetNumber
                val lengthOfSubPackets = packetNumber.toInt(2)
                i += 15
                currentPacket = Packet()
            }
        }
        return versionSum
    }
}

data class Packet(var s: String = "", var id: Int? = null, var lengthId: Char? = null) {

}


private fun String.hexToByte(): String {
    var result = ""
    for (i in this.indices) {
        when (this[i]) {
            '0' -> result += "0000"
            '1' -> result += "0001"
            '2' -> result += "0010"
            '3' -> result += "0011"
            '4' -> result += "0100"
            '5' -> result += "0101"
            '6' -> result += "0110"
            '7' -> result += "0111"
            '8' -> result += "1000"
            '9' -> result += "1001"
            'A' -> result += "1010"
            'B' -> result += "1011"
            'C' -> result += "1100"
            'D' -> result += "1101"
            'E' -> result += "1110"
            'F' -> result += "1111"
        }
    }
    return result
}
