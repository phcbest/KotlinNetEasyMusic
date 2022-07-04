package org.phcbest.neteasymusic.remote_view.notify

import android.widget.RemoteViews
import org.phcbest.neteasymusic.R

class PlayServiceNotify {
    /**
     * 使用单例模式构建
     */
    companion object {
        var playServiceNotify: PlayServiceNotify? = null
        fun getInstance(): PlayServiceNotify {
            if (playServiceNotify == null) {
                playServiceNotify = PlayServiceNotify()
            }
            return playServiceNotify!!
        }
    }

    init {
//        RemoteViews("NetEasyMusic", R.layout.remote_playservice_ctrl)
    }
}