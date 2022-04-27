package org.phcbest.neteasymusic.utils.ui

import android.R
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.IntRange
import org.phcbest.neteasymusic.utils.Constants.ViewIDs.Companion.FAKE_TRANSLUCENT_VIEW_ID


class StatusBarUtil {
    companion object {
        private const val TAG_KEY_HAVE_SET_OFFSET = -123


        fun setTransparent(activity: Activity) {
            this.transparentStatusBar(activity)
            this.setRootView(activity)
        }

        /**
         * 为头部是 ImageView 的界面设置状态栏全透明
         *
         * @param activity       需要设置的activity
         * @param needOffsetView 需要向下偏移的 View
         */
        fun setTransparentForImageView(activity: Activity, needOffsetView: View) {
            setTranslucentForImageView(activity, 0, needOffsetView);
        }

        fun setTranslucentForImageView(
            activity: Activity,
            @IntRange(from = 0, to = 255) statusBarAlpha: Int,
            needOffsetView: View,
        ) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return
            }
            setTransparentForWindow(activity)
            addTranslucentView(activity, statusBarAlpha)
            //虽然imageView需要填充到状态栏，但是上面的文字按钮等控件并不需要填充到状态栏，所以需要设置偏移
            if (needOffsetView != null) {
                val haveSetOffset = needOffsetView.getTag(TAG_KEY_HAVE_SET_OFFSET)
                if (haveSetOffset != null && haveSetOffset as Boolean) {
                    return
                }
                val layoutParams = needOffsetView.layoutParams as MarginLayoutParams
                layoutParams.setMargins(layoutParams.leftMargin,
                    layoutParams.topMargin + getStatusBarHeight(activity),
                    layoutParams.rightMargin,
                    layoutParams.bottomMargin)
                needOffsetView.setTag(TAG_KEY_HAVE_SET_OFFSET, true)
            }
        }

        /**
         * 设置透明
         */
        private fun setTransparentForWindow(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.statusBarColor = Color.TRANSPARENT
                activity.window
                    .decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                activity.window
                    .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }

        /**
         * 添加半透明矩形条
         *
         * @param activity       需要设置的 activity
         * @param statusBarAlpha 透明值
         */
        private fun addTranslucentView(
            activity: Activity,
            @IntRange(from = 0, to = 255) statusBarAlpha: Int,
        ) {
            val contentView = activity.findViewById<View>(R.id.content) as ViewGroup
            val fakeTranslucentView = contentView.findViewById<View>(FAKE_TRANSLUCENT_VIEW_ID)
            if (fakeTranslucentView != null) {
                if (fakeTranslucentView.visibility == View.GONE) {
                    fakeTranslucentView.visibility = View.VISIBLE
                }
                fakeTranslucentView.setBackgroundColor(Color.argb(statusBarAlpha, 0, 0, 0))
            } else {
                contentView.addView(createTranslucentStatusBarView(activity, statusBarAlpha))
            }
        }

        /**
         * 创建半透明矩形 View
         *
         * @param alpha 透明值
         * @return 半透明 View
         */
        private fun createTranslucentStatusBarView(activity: Activity, alpha: Int): View? {
            // 绘制一个和状态栏一样高的矩形
            val statusBarView = View(activity)
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity))
            statusBarView.layoutParams = params
            statusBarView.setBackgroundColor(Color.argb(alpha, 0, 0, 0))
            statusBarView.id = FAKE_TRANSLUCENT_VIEW_ID
            return statusBarView
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
            val parent: ViewGroup = activity.findViewById(R.id.content)
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