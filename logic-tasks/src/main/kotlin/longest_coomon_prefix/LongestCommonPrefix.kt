package longest_coomon_prefix

import java.lang.StringBuilder
import java.util.Collections.min

//https://leetcode.com/problems/longest-common-prefix/
fun main(args: Array<String>) {
    LongestCommonPrefix.longestCommonPrefix(arrayOf("flower","flow","flight"))
}

class LongestCommonPrefix {
    companion object {

        fun longestCommonPrefix(strs: Array<String>): String {
            if (strs.isEmpty())
                return ""
            if (strs.size == 1)
                return strs[0]
            val res: StringBuilder = StringBuilder()
            var minLen = strs[0].length
            var minIdx: Int = 0
            strs.forEachIndexed{ idx, str ->
                if(str.length < minLen) {
                    minLen = str.length
                    minIdx = idx
                }
            }
            res.append(strs[minIdx])
            var i = 0
            while (i < strs.size)
                if (!(strs[i].startsWith(res))) res.deleteCharAt(res.length - 1) else i++
            return res.toString()
        }
    }
}