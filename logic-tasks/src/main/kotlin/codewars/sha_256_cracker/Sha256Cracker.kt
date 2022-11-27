package codewars.sha_256_cracker

import org.junit.jupiter.api.Assertions.assertEquals
import java.security.MessageDigest

//https://www.codewars.com/kata/587f0abdd8730aafd4000035/train/kotlin
fun main() {
    assertEquals("GoOutside", crackSha256("b8c49d81cb795985c007d78379e98613a4dfc824381be472238dbd2f974e37ae", "deGioOstu"))
    assertEquals("abc", crackSha256("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", "cba"))
    assertEquals(null, crackSha256("f58262c8005bb64b8f99ec6083faf050c502d099d9929ae37ffed2fe1bb954fb", "abc"))
}

fun crackSha256(hash: String, chars: String): String? {
    return permute(chars.toCharArray(), 0, chars.length - 1, hash)
}

fun permute(ary: CharArray, startIndex: Int, endIndex: Int, hash: String): String? {
    if (startIndex == endIndex) {
        return if (hashString(String(ary), "SHA-256") == hash) String(ary) else null
    } else {
        for (i in startIndex..endIndex) {
            swap(ary, startIndex, i)
            val b = permute(ary, startIndex + 1, endIndex, hash)
            if (b != null)
                return b
            swap(ary, startIndex, i)
        }
    }
    return null
}

fun swap(ary: CharArray, x: Int, y: Int) {
    val temp = ary[x]
    ary[x] = ary[y]
    ary[y] = temp
}

private fun hashString(input: String, algorithm: String): String    {
    return MessageDigest.getInstance(algorithm)
        .digest(input.toByteArray())
        .fold("", { str, it -> str + "%02x".format(it) })
}