package org.phcbest.neteasymusic.utils

interface Constant {

    interface HandlerParamKey {
        companion object {
            //toast_util的handle
            const val HANDLER_MSG_WHAT = 1

            //cd旋转的暂停和开始handler
            const val HANDLER_TURN_IMAGE_COVER_START = 2
            const val HANDLER_TURN_IMAGE_COVER_STOP = 3

        }
    }

    interface IntentParamKey {
        companion object {
            const val Intent_SONG_ID = "songId"
        }
    }

}