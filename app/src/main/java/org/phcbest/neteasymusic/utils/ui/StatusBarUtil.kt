package org.phcbest.neteasymusic.utils.ui

import android.R
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.IntRange


class StatusBarUtil {
    companion object {

        fun setTransparent(activity: Activity) {
            this.transparentStatusBar(activity)
            this.setRootView(activity)
        }

        fun setColor(
            activity: Activity,
            @ColorInt color: Int,
            @IntRange(from = 0, to = 255) statusBarAlpha: Int,
        ) {
            //5.0以上版本直接设置状态栏颜色透明度
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                activity.window.statusBarColor = calculateStatusColor(color, statusBarAlpha)
                //4.4-5.0版本通过伪装一个状态栏来设置颜色和透明度
            }

        }

        /**
         * 计算状态栏颜色
         */
        private fun calculateStatusColor(color: Int, alpha: Int): Int {
            if (alpha == 0) {
                return color
            }
            val a: Float = 1 - alpha / 255f
            var red = color shr 16 and 0xff
            var green = color shr 8 and 0xff
            var blue = color and 0xff
            red = (red * a + 0.5).toInt()
            green = (green * a + 0.5).toInt()
            blue = (blue * a + 0.5).toInt()
            return 0xff shl 24 or (red shl 16) or (green shl 8) or blue
        }


        /**
         * 获取状态栏高度
         *
         * @param context context
         * @return 状态栏高度
         */
        fun getStatusBarHeight(context: Context): Int {
            // 获得状态栏高度
            val resourceId: Int =
                context.resources.getIdentifier("status_bar_height", "dimen", "android")
            return context.resources.getDimensionPixelSize(resourceId)
        }

        private fun transparentStatusBar(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //需要设置这个flag contentView才能延伸到状态栏
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                //状态栏覆盖在contentView上面，设置透明使contentView的背景透出来
                activity.window.statusBarColor = Color.TRANSPARENT;
                //黑色字体
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                //让contentView延伸到状态栏并且设置状态栏颜色透明
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }

        private fun setRootView(activity: Activity) {
            val parent: ViewGroup = activity.findViewById(android.R.id.content)
            val count = parent.childCount
            for (i in 0 until count) {
                val childView = parent.getChildAt(i)
                if (childView is ViewGroup) {
                    childView.setFitsSystemWindows(true)
                    childView.clipToPadding = true
                }
            }
        }
    }

}