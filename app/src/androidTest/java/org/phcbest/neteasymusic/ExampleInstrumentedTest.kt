package org.phcbest.neteasymusic

import android.text.TextUtils
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.tencent.mmkv.MMKV
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.phcbest.neteasymusic.utils.MMKVStorageUtils
import org.phcbest.neteasymusic.utils.RetrofitUtils
import org.phcbest.neteasymusic.utils.ndk_link.GaussianBlurUtils


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
        println("TestSp: ${Gson().toJson(MMKVStorageUtils.newInstance().getCookie())}")
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


    @Test
    fun viewMMKV() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        MMKV.initialize(appContext)
        val mmkv = MMKV.mmkvWithID("login")
        Log.i(TAG, "viewMMKV: ${mmkv.allKeys()?.size}")
        for (i in mmkv.allKeys()!!.indices) {
            val objectValue = getObjectValue(mmkv, mmkv.allKeys()!![i])
            Log.i(TAG, "viewMMKV: key=${mmkv.allKeys()!![i]}  value=$objectValue")
        }
    }

    private fun getObjectValue(mmkv: MMKV, key: String): Any? {
        // 因为其他基础类型value会读成空字符串,所以不是空字符串即为string or string-set类型
        val value = mmkv.decodeString(key)
        if (!TextUtils.isEmpty(value)) {
            // 判断 string or string-set
            return if (value!![0].toInt() == 0x01) {
                mmkv.decodeStringSet(key)
            } else {
                value
            }
        }
        // float double类型可通过string-set配合判断
        // 通过数据分析可以看到类型为float或double时string类型为空字符串且string-set类型读出空数组
        // 最后判断float为0或NAN的时候可以直接读成double类型,否则读float类型
        // 该判断方法对于非常小的double类型数据 (0d < value <= 1.0569021313E-314) 不生效
        val set = mmkv.decodeStringSet(key)
        if (set != null && set.size == 0) {
            val valueFloat = mmkv.decodeFloat(key)
            val valueDouble = mmkv.decodeDouble(key)
            return if (valueFloat.compareTo(0f) == 0 || valueFloat.compareTo(Float.NaN) == 0
            ) {
                valueDouble
            } else {
                valueFloat
            }
        }
        // int long bool 类型的处理放在一起, int类型1和0等价于bool类型true和false
        // 判断long或int类型时, 如果数据长度超出int的最大长度, 则long与int读出的数据不等, 可确定为long类型
        val valueInt = mmkv.decodeInt(key)
        val valueLong = mmkv.decodeLong(key)
        return if (valueInt.toLong() != valueLong) {
            valueLong
        } else {
            valueInt
        }
    }

}