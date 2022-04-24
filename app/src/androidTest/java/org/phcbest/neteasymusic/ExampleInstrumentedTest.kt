package org.phcbest.neteasymusic

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.phcbest.neteasymusic.utils.ndk_link.GaussianBlurUtils
import org.phcbest.neteasymusic.utils.RetrofitUtils
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
        println("TestSp: ${Gson().toJson(SpStorageUtils.newInstance().getCookie())}")
    }

    @Test
    fun testUserAccount() {
        RetrofitUtils.newInstance().getUserAccount().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ userAccount ->
                print(userAccount)
            }, {
                it.printStackTrace()
            })
    }

    @Test
    fun testNdk() {
        Log.i(TAG, "testNdk: ${GaussianBlurUtils.newInstance().stringFromJNI()}")
    }
}