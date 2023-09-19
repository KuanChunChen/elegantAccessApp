package com.elegant.access

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class KotlinExample {
    fun twoSum(nums: IntArray, target: Int): IntArray {

        var diff = 0;
        val positions = HashMap<Int, Int>()

        for (i in nums.indices) {
            diff = target - nums[i]
            if (positions.containsKey(diff)) {
                return intArrayOf(positions[diff]!!, i)
            } else {
                positions[nums[i]] = i

            }
        }
        return nums
    }
}


fun traceSequence() {
    println("1")
    runBlocking {
        launch {
            println("5")
            foo("a")
            println("8")
            foo("c")
            println("10")
        }
        println("2")
        launch {
            println("6")
            foo("b")
            println("9")
        }
        println("3")
        launch {
            println("7")
        }
        println("4")
    }
}

private suspend fun foo(param: String) {
    delay(1)
    println(param)
}

fun main(){
//    traceSequence()
//    println("ttes:"+Solution.isValid("({}())"))
    ParserUrlUtil.parseTest()
}

object ParserUrlUtil{
    fun parseTest(){
        val doc : Document = Jsoup.connect("https://play.bnbheroes.io/market").get();
//        val content : Element = doc.getElementById("css-lzjlzw");
//        val links : Elements = doc.getElementsByClass("__APP");
        val links : Elements = doc.getElementsByClass("css-1ej4hfo");
//        doc.select()
        links.fold(ArrayList<Map<String,String>>()){ acc, elements ->
            elements.getElementsByTag("a")
                .mapTo(acc){
                    println(it.text())
                    mapOf(
                        "text" to it.text(),
                        "href" to it.getElementsByTag("a").attr("href"),
                        "date" to it.getElementsByClass("date").html()
                    )
                }
        }.forEach(::println)
//            println("head:${doc.head().toString()}")
//            println("body:${doc.body().toString()}")

        val title : String = doc.title()
        println("title:$title")
    }
}


object Solution {
    fun isValid(str: String):Boolean {
        val strMedium = str.length / 2
        val strArray = str.toCharArray()

        if (str.isEmpty() || str.length % 2 != 0) {
            return false
        }

        for (index in 0 until strMedium) {
            val prefixString = strArray[strMedium - index - 1].toString()
            val suffixString = strArray[strMedium + index].toString()

            when (prefixString) {
                "("->{ if (suffixString!=")")return false}
                "{"->{ if (suffixString!="}")return false}
                "["->{ if (suffixString!="]")return false}
            }
        }

        return true

    }


    fun findRepeatIndex(str: String):Int {
        val strLength = str.length
        val strArray = str.toCharArray()

        if (str.isEmpty()) {
            return 0
        }

        return 0

    }
}


