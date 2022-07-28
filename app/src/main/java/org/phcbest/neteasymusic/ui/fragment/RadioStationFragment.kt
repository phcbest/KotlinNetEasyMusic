package org.phcbest.neteasymusic.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseFragment

class RadioStationFragment : BaseFragment() {


    override fun initPresenter() {
    }

    override fun initView() {
    }

    override fun setViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        return DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_radio_station,
            container,
            false
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RadioStationFragment()
    }
}