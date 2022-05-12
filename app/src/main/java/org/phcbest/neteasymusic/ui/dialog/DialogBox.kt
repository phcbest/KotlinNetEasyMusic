package org.phcbest.neteasymusic.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseApplication
import org.phcbest.neteasymusic.databinding.DialogMainPlaylistBinding

class DialogBox {

    companion object {
        private var instance: DialogBox = DialogBox()
        fun newInstance(): DialogBox {
            return instance
        }
    }

    data class MainPlayListResult(
        val dialog: Dialog,
        val dialogMainPlaylistBinding: DialogMainPlaylistBinding,
    )

    fun initMainPlayListDialog(context: Context): MainPlayListResult {
        val dialog = Dialog(context, R.style.DialogTheme)
        val dialogMainPlaylistBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.dialog_main_playlist,
            null, false,
        ) as DialogMainPlaylistBinding
        dialog.setContentView(dialogMainPlaylistBinding.root)
        dialog.window?.setWindowAnimations(R.style.anim_playlist_down_to_up)
        dialog.window?.setGravity(Gravity.BOTTOM)
        dialogMainPlaylistBinding.isDialogLoad = true
        return MainPlayListResult(dialog, dialogMainPlaylistBinding)
    }
}