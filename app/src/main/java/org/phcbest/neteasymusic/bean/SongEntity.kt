package org.phcbest.neteasymusic.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class SongEntity(
    val id: String,
    val name: String,
    val songId: String,
    val cover: String,
    val author: String,
)