package org.phcbest.neteasymusic.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.StartActivity


class CloudVillageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cloud_village, container, false)

        (view.findViewById(R.id.btn_login) as Button).setOnClickListener { v ->
            val intent = Intent(
                v.context, StartActivity::class.java
            )
            intent.putExtra("user", (view.findViewById(R.id.et_user) as EditText).text)
            intent.putExtra("pwd", (view.findViewById(R.id.et_pwd) as EditText).text)
            startActivity(intent)
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = CloudVillageFragment()
    }
}