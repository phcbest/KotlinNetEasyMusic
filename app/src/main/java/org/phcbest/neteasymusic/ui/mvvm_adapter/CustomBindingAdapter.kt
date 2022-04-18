package org.phcbest.neteasymusic.ui.mvvm_adapter

import android.view.View
import androidx.databinding.BindingAdapter
import org.phcbest.neteasymusic.ui.widget.custom.CircularImageView

class CustomBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("imageSrc")
        fun setCircularImageView(view: View, imageSrc: String) {
            if (view is CircularImageView) {
                view.setImage(imageSrc)
            }
        }
    }
}