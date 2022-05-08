package org.phcbest.neteasymusic.ui.mvvm_adapter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan
import android.text.style.RelativeSizeSpan
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.UserPlaylistBean
import org.phcbest.neteasymusic.ui.widget.custom.CircularImageView
import org.phcbest.neteasymusic.utils.SpStorageUtils
import org.phcbest.neteasymusic.utils.ndk_link.GaussianBlurUtils
import java.text.DecimalFormat


class CustomBindingAdapter {

    companion object {
        private const val TAG = "CustomBindingAdapter"
        var uid = SpStorageUtils.newInstance().getLoginBean()?.profile?.userId

        @JvmStatic
        @BindingAdapter("circularImageSrc")
        fun setCircularImageView(view: View, imageSrc: String) {
            if (view is CircularImageView) {
                view.setImage(imageSrc)
            }
        }

        @JvmStatic
        @BindingAdapter("roundedCornersImageSrc")
        fun setRoundedCornersImageView(imageView: ImageView, imageSrc: String) {
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
        fun setVisible(view: View, status: Boolean) {
            view.visibility = if (status) View.VISIBLE else View.GONE
        }


        /**
         * 设置 模糊背景
         */
        @JvmStatic
        @BindingAdapter("setBlurBackground")
        fun setBlurBackground(view: View, url: String) {
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
                                .getGaussBlurZoomBmp(resource, 200, view.width, view.height)
                            view.background = BitmapDrawable(view.resources, gaussBlurBmp)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
            }
        }


        @JvmStatic
        @BindingAdapter("setPlayCount")
        fun setPlayCount(textView: TextView, playCount: Long) {
            val ssb = SpannableStringBuilder()
            ssb.append("\u0020")
            ssb.setSpan(ImageSpan(textView.context,
                R.drawable.ic_play_white,
                ImageSpan.ALIGN_CENTER),
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

    }
}