package org.phcbest.neteasymusic.ui.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.CountDownTimer
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.R
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.databinding.ActivityLoginBinding
import org.phcbest.neteasymusic.databinding.DialogLoginQrBinding
import org.phcbest.neteasymusic.presenter.PresenterManager
import org.phcbest.neteasymusic.ui.activity.viewmodel.LoginActivityViewModel
import org.phcbest.neteasymusic.ui.widget.custom.VerifyCodeView
import org.phcbest.neteasymusic.utils.MMKVStorageUtils
import org.phcbest.neteasymusic.utils.ToastUtils
import org.phcbest.neteasymusic.utils.custom.CenterAlignImageSpan
import java.util.regex.Pattern

private const val TAG = "LoginActivity"

class LoginActivity : BaseActivity() {
    var binding: ActivityLoginBinding? = null
    private lateinit var loginActivityViewModel: LoginActivityViewModel

    override fun initPresenter() {
    }

    override fun initEvent() {
        super.initEvent()
        //扫码登录
        binding?.tvScanQrcodeLogin?.setOnClickListener {
            loginActivityViewModel.setQrCodeBitMatrix()
        }
    }

    override fun initView() {
        //验证码输入完成触发
        binding?.etVerifyCode?.setInputCompleteListener(object :
            VerifyCodeView.InputCompleteListener {
            override fun inputComplete() {
                //请求登录
                doLogin(binding?.etVerifyCode?.getEditContent()!!)
            }

            override fun invalidComplete() {
            }

        })
        binding?.btnNext?.setOnClickListener(onClickSendCaptcha)
    }

    private fun doLogin(captcha: String) {
        val phone = binding?.etPhone?.text.toString()
        PresenterManager.getInstance().getLoginPresenter().login(phone, captcha,
            { result ->
                val body = result.body()!!
                if (body.code == 200) {
//                    Log.i(TAG, "doLogin: ${result.headers()}")
                    MMKVStorageUtils.getInstance().storageCookie(result)
                    MMKVStorageUtils.getInstance().storageLoginBean(body)
                    ToastUtils.SEND_SMG("登录成功")
                    startActivity(Intent(baseContext, MainActivity::class.java))
                } else {
                    ToastUtils.SEND_SMG("登录失败,验证码或手机错误")
                }
            },
            {
                ToastUtils.SEND_SMG("登录失败,验证码或手机错误,或者账号未注册")
            })
    }

    private val onClickSendCaptcha = View.OnClickListener { _ ->
        val phone = binding?.etPhone?.text.toString()
        val compile =
            Pattern.compile("^1(3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\\d|9[0-35-9])\\d{8}\$")
        if (compile.matcher(phone).matches()) {
            sendCaptcha(phone)
        } else {
            ToastUtils.SEND_SMG("请输入正确的手机号")
        }
    }

    private fun sendCaptcha(phone: String) {
        PresenterManager.getInstance().getLoginPresenter().getCaptcha(phone,
            { result ->
                if (result["data"] == true) {
                    val animationIn = AlphaAnimation(0F, 1F)
                    animationIn.duration = 500
                    binding?.etVerifyCode?.animation = animationIn
                    binding?.etVerifyCode?.isVisible = true
                    val animationOut = AlphaAnimation(1F, 0F)
                    animationOut.duration = 500
                    binding?.btnNext?.animation = animationOut
                    binding?.btnNext?.isVisible = false
                } else {
                    ToastUtils.SEND_SMG(result["message"].toString())
                }
            }, { })
    }

    override fun observeViewModel() {
        super.observeViewModel()
        loginActivityViewModel.qrCodeLiveData.observe(this) {
            if (it != null) {
                //进行dialog显示
                Log.i(TAG, "observeViewModel: 进行dialog显示")
                val colors = IntArray(it.width * it.height)
                for (i in 0 until it.width) {
                    for (j in 0 until it.height) {
                        if (it.get(i, j)) {
                            colors[i * it.width + j] = Color.BLACK
                        } else {
                            colors[i * it.width + j] = Color.WHITE
                        }
                    }
                }
                val qrBitmap =
                    Bitmap.createBitmap(colors, it.width, it.height, Bitmap.Config.RGB_565)
                //显示图片dialog
                showQrDialog(qrBitmap)
            }
        }
        loginActivityViewModel.qrLoginCheckLiveData.observe(this) {
            if (it == null) {
                return@observe
            }
//            Log.i(TAG, "observeViewModel: $it")
            if (it.code == 803) {
                //保存Cookie并且进入主页
//                MMKVStorageUtils.getInstance().storageCookie(it.cookie)
//                MMKVStorageUtils.getInstance().storageLoginBean(body)
                ToastUtils.SEND_SMG("登录成功")
                startActivity(Intent(baseContext, MainActivity::class.java))
            } else {
                ToastUtils.SEND_SMG(it.message)
            }
        }
    }

    /**
     * 显示二维码的dialog
     */
    private fun showQrDialog(qrBitmap: Bitmap?) {
        val dialog = Dialog(this, R.style.QRDialogTheme)
        val dialogLoginQrBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
            R.layout.dialog_login_qr, null, false) as DialogLoginQrBinding
        dialogLoginQrBinding.ivQrCode.setImageBitmap(qrBitmap)
        dialog.setContentView(dialogLoginQrBinding.root)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.setOnCancelListener {
            loginActivityViewModel.stopLoopCheckQrLoginStatus()
        }
        dialog.show()
        //开始轮询登录状态
        loginActivityViewModel.doLoopCheckQrLoginStatus()
    }

    override fun getViewBinding(): ViewBinding {
        loginActivityViewModel = ViewModelProviders.of(this)[LoginActivityViewModel::class.java]
        binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding!!
    }
}


