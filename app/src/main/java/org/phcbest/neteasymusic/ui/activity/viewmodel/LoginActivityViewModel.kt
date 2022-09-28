package org.phcbest.neteasymusic.ui.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginActivityViewModel() : ViewModel() {
    companion object {
        private const val TAG = "LoginActivityViewModel"
    }

    //使用数组保存二维码
    var qrCodeLiveData: MutableLiveData<IntArray> = MutableLiveData()

    //获得二维码
    fun setQrCode() {
        
    }


}