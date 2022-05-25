package org.phcbest.neteasymusic.ui.fragment

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseFragment
import org.phcbest.neteasymusic.databinding.FragmentCloudVillageBinding


class CloudVillageFragment : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun initPresenter() {
    }

    override fun initView() {
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        val view = DataBindingUtil.inflate<FragmentCloudVillageBinding>(inflater,
            R.layout.fragment_cloud_village,
            container,
            false)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = CloudVillageFragment()
    }
}