package org.phcbest.neteasymusic

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        pay {
            println("高阶函数..it=$it")
            1
        }
        pay(object : Consumer {
            override fun accept(way: String) {
                println("回调函数..it=$way")
            }
        })
    }

    interface Consumer {
        fun accept(way: String)
    }


    fun pay(block: Consumer) {
        println("before block")
        block.accept("支付宝")
        println("end block")
    }

    fun pay(block: (String) -> Int) {
        println("before block")
        block("支付宝")
        println("end block")
    }
}