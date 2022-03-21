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
    }

    fun pay(block: (String) -> Int) {
        println("before block")
        block("支付宝")
        println("end block")
    }
}