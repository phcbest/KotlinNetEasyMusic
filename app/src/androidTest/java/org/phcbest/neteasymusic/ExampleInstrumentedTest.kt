package org.phcbest.neteasymusic

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.phcbest.neteasymusic.utils.SpStorageUtils

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
private const val TAG = "ExampleInstrumentedTest"

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("org.phcbest.neteasymusic", appContext.packageName)
    }


    @Test
    fun TestSp() {
        println("TestSp")
        println("TestSp: ${Gson().toJson(SpStorageUtils.newInstance().getLoginInfo())}")
    }
}