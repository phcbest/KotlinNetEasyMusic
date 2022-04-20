package org.phcbest.neteasymusic.ui.mvvm_adapter

import android.view.RoundedCorner
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.bean.UserPlaylistBean
import org.phcbest.neteasymusic.ui.widget.custom.CircularImageView
import org.phcbest.neteasymusic.utils.SpStorageUtils
import java.util.*

class CustomBindingAdapter {

    companion object {
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
    }
}