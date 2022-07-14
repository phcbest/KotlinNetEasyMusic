package org.phcbest.neteasymusic.ui.activity

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.databinding.ActivityTestNetBinding
import org.phcbest.neteasymusic.utils.MMKVStorageUtils
import org.phcbest.neteasymusic.utils.RetrofitApi
import org.phcbest.neteasymusic.utils.ToastUtils
import java.io.IOException

class TestNetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = DataBindingUtil.inflate<ActivityTestNetBinding>(LayoutInflater.from(this),
            R.layout.activity_test_net,
            null,
            false)
        setContentView(inflate.root)

        val client: OkHttpClient = OkHttpClient().newBuilder().build()
        inflate.tvResult.text = "请求中..."

        inflate.btnRequest.setOnClickListener {
            val request = Request.Builder()
                .url("${RetrofitApi.baseUrl}${inflate.etTestNetInput.text}")
                .method("GET", null)

            //得到cookie
            MMKVStorageUtils.getInstance().getCookie()
                .let {
                    if (!it.contains(MMKVStorageUtils.SP_NULL)) {
                        request.addHeader("Cookie", it).build()
                    }
                }

            client.newCall(request.build()).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        inflate.tvResult.text = "请求失败\n${e.message}"
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        ToastUtils.SEND_SMG("请求成功,点击显示区域复制到粘贴板")
                        inflate.tvResult.text = "请求成功:\n${response.body?.string()}"
                    }
                }
            })
        }
        //设置点击复制
        val clipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        inflate.tvResult.setOnClickListener {
            clipboardManager.text = inflate.tvResult.text
            ToastUtils.SEND_SMG("复制成功")

        }

    }
}