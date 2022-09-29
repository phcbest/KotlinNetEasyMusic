package org.phcbest.neteasymusic.ui.activity.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import org.phcbest.neteasymusic.bean.LoginQrBean
import org.phcbest.neteasymusic.utils.RetrofitUtils

class LoginActivityViewModel() : ViewModel() {
    companion object {
        private const val TAG = "LoginActivityViewModel"
    }

    //二维码解析相关
    private var qrCodeWriter: QRCodeWriter = QRCodeWriter()
    private var qrHint: MutableMap<EncodeHintType, String> =
        mutableMapOf(Pair(EncodeHintType.CHARACTER_SET, "utf-8"))
    private var qrCodeHeight: Int = 300
    private var qrCodeWidth: Int = 300

    //二维码位矩阵
    var qrCodeLiveData: MutableLiveData<BitMatrix?> = MutableLiveData()


    //获得二维码
    fun setQrCodeBitMatrix() {
        //获得key
        RetrofitUtils.newInstance().getLoginQrKey().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                Log.i(TAG, "getLoginQrKey: $it")
            }.doOnError {
                Log.e(TAG, "getLoginQrKey: 请求qrcodeKey出错")
                qrCodeLiveData.postValue(null)
                it.printStackTrace()
            }.observeOn(Schedulers.io())
            .flatMap(object :
                Function<LoginQrBean.LoginQrKeyBean, Observable<LoginQrBean.LoginQrCreateBean>> {
                override fun apply(t: LoginQrBean.LoginQrKeyBean): Observable<LoginQrBean.LoginQrCreateBean> {
                    return RetrofitUtils.newInstance().getLoginQrCreate(t.data.unikey)
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //请求成功，将结果转换为二维码数组
                if (it.code == 200 && it.data.qrurl.isNotEmpty()) {
                    val encode = qrCodeWriter.encode(it.data.qrurl,
                        BarcodeFormat.QR_CODE,
                        qrCodeWidth,
                        qrCodeHeight,
                        qrHint)
                    qrCodeLiveData.postValue(encode)
                }
            }, {
                qrCodeLiveData.postValue(null)
                //进行报错
                it.printStackTrace()
            })
    }


}