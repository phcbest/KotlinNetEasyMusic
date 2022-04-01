package org.phcbest.neteasymusic.utils

import android.os.CountDownTimer

abstract class CountDownTimeWithPause(millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval) {

    //todo 增强CountDownTime 使其支持暂停
    fun pause() {

    }

}