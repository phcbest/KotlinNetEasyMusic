package org.phcbest.neteasymusic

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationBarView
import org.phcbest.neteasymusic.databinding.ActivityMainBinding
import org.phcbest.neteasymusic.ui.*
import kotlin.math.log

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor(window, 0xffffff)
        initView()
    }

    private fun initView() {
        //设置导航栏tint效果为null
        binding.navMain.itemIconTintList = null
        //init fragment
        //kotlin回调demo
        binding.navMain.setOnItemSelectedListener(object :
            NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.menu_discover -> {
                        switchFragment(DiscoverFragment.newInstance())
                    }
                    R.id.menu_radio_station -> {
                        switchFragment(RadioStationFragment.newInstance())
                    }
                    R.id.menu_mine -> {
                        switchFragment(MineFragment.newInstance())
                    }
                    R.id.menu_follow -> {
                        switchFragment(FollowFragment.newInstance())
                    }
                    R.id.menu_cloud_village -> {
                        switchFragment(CloudVillageFragment.newInstance())
                    }
                }
                return true
            }

        })
        //初始化主页位置
        binding.navMain.findViewById<View>(R.id.menu_discover).performClick()

    }

    private fun switchFragment(fragment: Fragment) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.fragment_home, fragment)
        beginTransaction.addToBackStack(null)
        beginTransaction.commit()
    }

    //设置状态栏颜色
    @TargetApi(Build.VERSION_CODES.M)
    fun setStatusBarColor(window: Window, color: Int) {
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.setStatusBarColor(color)
        //状态栏白底黑字的实现方法
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        //去掉系统状态栏下的windowContentOverlay
        findViewById<View>(android.R.id.content).setForeground(null)
    }
}