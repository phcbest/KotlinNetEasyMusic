package org.phcbest.neteasymusic.ui.mvvm_adapter

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.*
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.UserPlaylistBean
import org.phcbest.neteasymusic.ui.widget.custom.CircularImageView
import org.phcbest.neteasymusic.utils.MMKVStorageUtils
import org.phcbest.neteasymusic.utils.custom.CenterAlignImageSpan
import org.phcbest.neteasymusic.utils.ndk_link.GaussianBlurUtils
import java.text.SimpleDateFormat
import java.util.*


class CustomBindingAdapter {

    companion object {
        private const val TAG = "CustomBindingAdapter"
        var uid = MMKVStorageUtils.getInstance().getLoginBean()?.profile?.userId

        @JvmStatic
        @BindingAdapter("circularImageSrc",
            "circularSizeLimit",
            "circularStroke",
            requireAll = false)
        fun setCircularImageView(
            view: View,
            imageSrc: String,
            sizeLimit: Int,
            stroke: Boolean = false,
        ) {
            if (view is CircularImageView) {
//                Log.i(TAG, "setCircularImageView: 图片限制$sizeLimit")
                view.setImage(imageSrc, sizeLimit, stroke)
            }
        }

        @JvmStatic
        @BindingAdapter("roundedCornersImageSrc",
            "roundedCornersImageSizeLimit",
            requireAll = false)
        fun setRoundedCornersImageView(
            imageView: ImageView,
            imageSrc: String,
            sizeLimit: Int = 150,
        ) {
            var url = imageSrc
            if (sizeLimit != 0) {
                url = "$imageSrc?param=$sizeLimit" + "y$sizeLimit"
            }
            if (imageSrc.contains("http")) {
                Glide.with(imageView).load(imageSrc)
                    .error(R.drawable.sample_avatar)
                    .into(imageView)
            }
        }

        /**
         * 设置播放列表的描述
         */
        @JvmStatic
        @BindingAdapter("playlistItemDesc")
        fun setPlayListItemDesc(textView: TextView, playlist: UserPlaylistBean.Playlist) {
            //判断是否是自己创建的歌单
            if (playlist.creator.userId == uid) {
                textView.text =
                    StringBuffer().append(playlist.trackCount + playlist.cloudTrackCount)
                        .append("首").toString()
            } else {
                textView.text =
                    StringBuffer().append(playlist.trackCount + playlist.cloudTrackCount)
                        .append("首,\tby\t")
                        .append(playlist.creator.nickname).toString()
            }
        }


        /**
         * 设置控件的隐藏和显示
         * @param status true是显示 false是隐藏
         */
        @JvmStatic
        @BindingAdapter("setVisible")
        fun setVisible(view: View, status: Boolean = true) {
            view.visibility = if (status) View.VISIBLE else View.GONE
        }


        /**
         * 设置 模糊背景
         */
        @JvmStatic
        @BindingAdapter("setBlurBackground", "setBlurBackgroundRadius", requireAll = false)
        fun setBlurBackground(view: View, url: String, radius: Int = 500) {
            Log.i(TAG, "setBlurBackground: 模糊图片url为 $url")
            url.let {
                Glide.with(view).asBitmap().load(it).error(R.drawable.sample_avatar)
//                    .override(view.width, view.height)
//                    .centerCrop()
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?,
                        ) {
                            val gaussBlurBmp = GaussianBlurUtils.newInstance()
                                .getGaussBlurZoomBmp(resource, radius, view.width, view.height)
                            val bitmapDrawable = BitmapDrawable(view.resources, gaussBlurBmp)
                            //对bitmap做灰色处理
                            bitmapDrawable.colorFilter =
                                PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
                            view.background = bitmapDrawable
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
            }
        }


        @JvmStatic
        @BindingAdapter("setPlayCount")
        fun setPlayCount(textView: TextView, playCount: Long) {
            val drawable = textView.context.resources.getDrawable(R.drawable.ic_play_white)
            drawable.setBounds(0, 0, textView.lineHeight, textView.lineHeight)
            val ssb = SpannableStringBuilder()
            ssb.append("\u0020")
            ssb.setSpan(CenterAlignImageSpan(drawable),
                0,
                1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            if (playCount in 0..9999) {
                ssb.append(playCount.toString())
            }
            if (playCount in 10000..99999999) {
                ssb.append((playCount / 10000).toString())
                ssb.append("万")
            }
            if (playCount > 100000000) {
                ssb.append("%.2f亿".format(playCount / 100000000.0))
            }
            textView.text = ssb
        }

        /**
         * 使用image设置TextView
         * @param imageRes 图片R资源
         * @param text 设置的文字
         * @param position 设置的位置 0为在前面，1为在后面
         */
        @JvmStatic
        @BindingAdapter("setTextWithImageImageRes",
            "setTextWithImageImageText",
            "setTextWithImageImagePosition",
            requireAll = true)
        fun setTextWithImage(textView: TextView, imageRes: Drawable, text: String, position: Int) {
            imageRes.setBounds(0, 0, textView.lineHeight, textView.lineHeight)
            val ssb = SpannableStringBuilder()
            if (position == 0) {
                ssb.append("\u0020")
                ssb.setSpan(CenterAlignImageSpan(imageRes),
                    0,
                    1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            ssb.append(text)
            if (position == 1) {
                ssb.append("\u0020")
                ssb.setSpan(ImageSpan(imageRes),
                    ssb.length - 1,
                    ssb.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            textView.text = ssb
        }


        @JvmStatic
        @BindingAdapter("setPayBarTextName", "setPayBarTextAuthor", requireAll = true)
        fun setPayBarText(textView: TextView, name: String, author: String) {
            val ssb = SpannableStringBuilder()
            ssb.append(name)
            val length = ssb.length
            ssb.append("-")
            ssb.append(author)
            ssb.setSpan(AbsoluteSizeSpan(30),
                length,
                ssb.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            ssb.setSpan(ForegroundColorSpan(Color.GRAY),
                length,
                ssb.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            textView.text = ssb
        }


        //将时间戳转换为时间显示
        @JvmStatic
        @BindingAdapter("setTextByTime")
        fun setTextByTime(textView: TextView, time: Long) {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
            textView.text = simpleDateFormat.format(time)
        }


        @JvmStatic
        @BindingAdapter("setImageFromSrc")
        fun setImageFromSrc(imageView: ImageView, src: Int) {
            imageView.setImageResource(src)
        }

        @JvmStatic
        @BindingAdapter("setVipGrowthphintProgress")
        fun setVipGrowthphintProgress(progressBar: ProgressBar, progress: Int) {
            //按照阶梯设置MAX
            when (progress) {
                in 0..1 -> progressBar.max = 1
                in 2..450 -> progressBar.max = 450
                in 451..1350 -> progressBar.max = 1350
                in 1351..3150 -> progressBar.max = 3150
                in 3151..4950 -> progressBar.max = 4950
                in 4951..9450 -> progressBar.max = 9450
                in 9451..21600 -> progressBar.max = 21600
            }
            //设置进度
            progressBar.progress = progress
        }
    }
}