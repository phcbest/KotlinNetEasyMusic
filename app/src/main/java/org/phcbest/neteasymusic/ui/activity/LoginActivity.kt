package org.phcbest.neteasymusic.ui.activity

import android.content.Intent
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import org.phcbest.neteasymusic.base.BaseActivity
import org.phcbest.neteasymusic.databinding.ActivityLoginBinding
import org.phcbest.neteasymusic.presenter.PresenterManager
import org.phcbest.neteasymusic.ui.widget.custom.VerifyCodeView
import org.phcbest.neteasymusic.utils.SpStorageUtils
import org.phcbest.neteasymusic.utils.ToastUtils
import java.util.regex.Pattern

private const val TAG = "LoginActivity"

class LoginActivity : BaseActivity() {
    var binding: ActivityLoginBinding? = null

    override fun initPresenter() {
    }

    override fun initEvent() {
        super.initEvent()
    }

    override fun initView() {
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
                    SpStorageUtils.instance.storageCookie(body)
                    SpStorageUtils.instance.storageLoginBean(body)
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

    override fun getViewBinding(): ViewBinding {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding!!
    }
}


