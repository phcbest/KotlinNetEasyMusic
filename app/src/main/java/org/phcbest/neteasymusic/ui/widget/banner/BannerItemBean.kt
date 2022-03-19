package org.phcbest.neteasymusic.ui.widget.banner

class BannerItemBean {

    interface OnTap {
        fun OnClick()
    }

    var imageUrl: String
    var onTap: OnTap

    constructor(imageUrl: String, onTap: OnTap) {
        this.imageUrl = imageUrl
        this.onTap = onTap
    }
}