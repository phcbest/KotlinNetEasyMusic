package org.phcbest.neteasymusic.utils

interface Constants {

    interface HandlerParamKey {
        companion object {
            //toast_util的handle
            const val HANDLER_TOAST = 1

            //cd旋转的暂停和开始handler
            const val HANDLER_TURN_IMAGE_COVER_START = 2
            const val HANDLER_TURN_IMAGE_COVER_STOP = 3

            //控制计时器的handler msg
            const val HANDLER_CDT = 4

        }
    }

    interface IntentParamKey {
        companion object {
            const val Intent_SONG_ID = "songId"
        }
    }

}