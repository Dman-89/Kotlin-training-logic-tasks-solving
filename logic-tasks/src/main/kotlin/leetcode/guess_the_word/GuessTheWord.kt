package leetcode.guess_the_word

import kotlin.random.Random

//https://leetcode.com/problems/guess-the-word/
fun main() {
//    GuessTheWord.findSecretWord(arrayOf(
//        "acckzz","ccbazz","eiowzz","abcczz",
//        "bcckzz","cdbazz","ziowzz","axcvbq"), MasterImpl("axcvbq"))
//    repeat(10000)
//    {
        GuessTheWord.findSecretWord(arrayOf(
            "ccoyyo","wichbx","oahwep","tpulot","eqznzs","vvmplb","eywinm","dqefpt","kmjmxr","ihkovg","trbzyb","xqulhc","bcsbfw","rwzslk","abpjhw","mpubps","viyzbc","kodlta","ckfzjh","phuepp","rokoro","nxcwmo","awvqlr","uooeon","hhfuzz","sajxgr","oxgaix","fnugyu","lkxwru","mhtrvb","xxonmg","tqxlbr","euxtzg","tjwvad","uslult","rtjosi","hsygda","vyuica","mbnagm","uinqur","pikenp","szgupv","qpxmsw","vunxdn","jahhfn","kmbeok","biywow","yvgwho","hwzodo","loffxk","xavzqd","vwzpfe","uairjw","itufkt","kaklud","jjinfa","kqbttl","zocgux","ucwjig","meesxb","uysfyc","kdfvtw","vizxrv","rpbdjh","wynohw","lhqxvx","kaadty","dxxwut","vjtskm","yrdswc","byzjxm","jeomdc","saevda","himevi","ydltnu","wrrpoc","khuopg","ooxarg","vcvfry","thaawc","bssybb","ajcwbj","arwfnl","nafmtm","xoaumd","vbejda","kaefne","swcrkh","reeyhj","vmcwaf","chxitv","qkwjna","vklpkp","xfnayl","ktgmfn","xrmzzm","fgtuki","zcffuv","srxuus","pydgmq",
        ), MasterImpl("ccoyyo", 10)) //, it
//    }
}

// This is the Master's API interface.
// You should not implement it, or speculate about its implementation
interface Master {
    fun guess(word: String): Int
}

class MasterImpl(val secretWord: String, val totalGuesses: Int): Master { //, val numIter: Int

    companion object {
        var guesses = 0
    }

    override fun guess(word: String): Int {
        guesses++
        if (guesses > totalGuesses) throw Exception("fail")//s at iteration $numIter")
        var res = 0
        secretWord.forEachIndexed { idx, char -> if (char == word[idx]) res++ }
        return res
    }
}

class GuessTheWord {
    companion object {
        //Runtime: 198 ms
        //Memory Usage: 33.8 MB beats 75.93 % of kotlin submissions.
        fun findSecretWord(words: Array<String>, master: Master) {
            var guessCount = 0
            val wordListMutable = words.toMutableList()
            while (guessCount < 6) {
                var totalWords = wordListMutable.size
                val randomWordIdx = Random(wordListMutable.size.toLong()).nextInt(totalWords)
                val randomChosenWord = wordListMutable[randomWordIdx]
                guessCount = master.guess(wordListMutable[randomWordIdx])
                if (guessCount == 6)
                    return
                var guessCountCurrWord = 0
                var numWord = 0
                while (numWord < totalWords) {
                    wordListMutable[numWord].forEachIndexed { idx, char ->
                        if (char == randomChosenWord[idx]) guessCountCurrWord++
                    }
                    if (guessCountCurrWord != guessCount) {
                        wordListMutable.removeAt(numWord)
                        totalWords--
                    } else
                        numWord++
                    guessCountCurrWord = 0
                }
            }
        }
    }
}