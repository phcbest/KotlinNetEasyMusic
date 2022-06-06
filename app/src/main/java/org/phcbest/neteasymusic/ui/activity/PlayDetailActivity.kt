package org.phcbest.neteasymusic.ui.activity

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.databinding.ActivityPlayDetailBinding
import org.phcbest.neteasymusic.service.MusicPlayerService
import org.phcbest.neteasymusic.ui.widget.playDisc.PlayDiscHelper
import org.phcbest.neteasymusic.utils.ui.StatusBarUtil

class PlayDetailActivity : BaseActivity() {

    private var playDiscHelper: PlayDiscHelper? = null

    companion object {
        const val TAG = "PlayDetailActivity"
    }

    override fun initPresenter() {
    }

    override fun initView() {
        //设置透明状态栏
        isTransparentStatusBar = true
        //设置状态栏沉浸
        binding?.llTop?.setPadding(0, StatusBarUtil.getStatusBarHeight(this), 0, 0)
        //初始化cd view帮助器
        playDiscHelper = PlayDiscHelper().setView(binding?.clCd?.rootView!!)
    }

    override fun initEvent() {
        super.initEvent()
        binding?.ivPlayDetailPlay?.setOnClickListener {
            //设置不同状态下的ui表现
            //设置不同状态下的暂停和播放事件
            if (mMusicPlayerServiceLD.value?.isPlayerLD?.value!!) {
                mMusicPlayerServiceLD.value?.playControl(1)
            } else {
                mMusicPlayerServiceLD.value?.playControl(2)
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        //服务绑定的回调
        mMusicPlayerServiceLD.observe(this) {
            //设置初始指针位置
            if (it!!.isPlayerLD.value!!) {
                playDiscHelper?.setInitNeedlePlace(PlayDiscHelper.NEEDLE_STATE.OVERLAY)
            } else {
                playDiscHelper?.setInitNeedlePlace(PlayDiscHelper.NEEDLE_STATE.LEAVE)
            }
            //设置cd图片
            

            //设置播放状态的回调
            it.isPlayerLD.observe(this) { isPlayer ->
                if (isPlayer!!) {
                    playDiscHelper?.play()
                } else {
                    playDiscHelper?.stop()
                }
            }
        }
    }

    var binding: ActivityPlayDetailBinding? = null
    override fun getViewBinding(): ViewBinding {
        //绑定播放服务
        doBindService()
        binding = DataBindingUtil.inflate(layoutInflater,
            R.layout.activity_play_detail,
            null,
            false)
        return binding!!
    }

    private fun doBindService() {
        bindService(Intent(this, MusicPlayerService::class.java),
            playServiceConnection(),
            BIND_AUTO_CREATE)
    }

    var mMusicPlayerServiceLD: MutableLiveData<MusicPlayerService?> = MutableLiveData()
    private fun playServiceConnection(): ServiceConnection {
        return object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                mMusicPlayerServiceLD.postValue((service as MusicPlayerService.MyBinder).getService())
                Log.i(TAG, "onServiceConnected: 活动和服务连接完成")
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                mMusicPlayerServiceLD.postValue(null)
                Log.i(TAG, "onServiceDisconnected: 服务断开连接")
            }

        }
    }
}