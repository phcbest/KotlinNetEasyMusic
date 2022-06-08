package org.phcbest.neteasymusic.ui.activity

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.os.IBinder
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.ViewBinding
import com.google.android.material.slider.Slider
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
        binding?.ivPlayBtn?.setOnClickListener {
            //设置不同状态下的ui表现
            //设置不同状态下的暂停和播放事件
            if (mMusicPlayerServiceLD.value?.isPlayerLD?.value!!) {
                binding?.ivPlayBtn?.setImageResource(R.drawable.ic_play_detail_play)
                mMusicPlayerServiceLD.value?.playControl(1)
            } else {
                binding?.ivPlayBtn?.setImageResource(R.drawable.ic_play_detail_pause)
                mMusicPlayerServiceLD.value?.playControl(2)
            }
        }
        //上一首和下一首的事件
        binding?.ivPlayPrevious?.setOnClickListener {
            mMusicPlayerServiceLD.value?.switchSongNOP(false)
        }
        binding?.ivPlayNext?.setOnClickListener {
            mMusicPlayerServiceLD.value?.switchSongNOP(true)
        }
        //设置进度条
        binding?.srSongProgress?.addOnChangeListener(object : Slider.OnChangeListener {
            @SuppressLint("RestrictedApi")
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
            }
        })
    }

    override fun observeViewModel() {
        super.observeViewModel()
        //服务绑定的回调
        mMusicPlayerServiceLD.observe(this) {
            //设置初始指针位置,初始播放按钮样式
            if (it!!.isPlayerLD.value!!) {
                binding?.ivPlayBtn?.setImageResource(R.drawable.ic_play_detail_pause)
                playDiscHelper?.setInitNeedlePlace(PlayDiscHelper.NEEDLE_STATE.OVERLAY)
            } else {
                binding?.ivPlayBtn?.setImageResource(R.drawable.ic_play_detail_play)
                playDiscHelper?.setInitNeedlePlace(PlayDiscHelper.NEEDLE_STATE.LEAVE)
            }
            //设置歌曲变动的观察者回调
            it.currentSongEntityLD.observe(this) { songEntity ->
                Log.i(TAG, "observeViewModel: $songEntity")
                //设置歌曲封面
                playDiscHelper?.setDiscInfo(songEntity?.cover!!)
                //设置歌曲名字
                //使用ssb设置不同的样式
                val ssb = SpannableStringBuilder()
                ssb.append(songEntity.name)
                ssb.append("\n")
                val l1 = ssb.length
                ssb.append(songEntity.author)
                ssb.setSpan(ForegroundColorSpan(Color.parseColor("#8bffffff")),
                    l1,
                    ssb.length,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                ssb.setSpan(AbsoluteSizeSpan(10, true),
                    l1,
                    ssb.length,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                binding?.tvSongName?.text = ssb
            }

            //设置播放状态的观察者
            it.isPlayerLD.observe(this) { isPlayer ->
                if (isPlayer!!) {
                    playDiscHelper?.play()
                } else {
                    playDiscHelper?.stop()
                }
            }
            //设置进度推出器的观察者
            it.playProgressLD.observe(this) { progress ->
                Log.i(TAG, "observeViewModel: 当前进度$progress")
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

            //这个是由服务决定的
            override fun onServiceDisconnected(name: ComponentName?) {
                mMusicPlayerServiceLD.postValue(null)
                Log.i(TAG, "onServiceDisconnected: 服务断开连接")
            }

        }
    }
}