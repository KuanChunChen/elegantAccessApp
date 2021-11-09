package com.oringnet.wm

import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


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
    traceSequence()

}