package com.qbill.ui.activity.login

import android.os.Bundle
import androidx.activity.viewModels
import com.qbill.R
import com.qbill.base.BaseActivity
import com.qbill.databinding.ActivityLoginBinding
import com.qbill.ui.activity.main.MainActivity
import com.qbill.utils.ActivityUtils.callNewPage
import com.qbill.utils.Conditions.hideKeyboard
import com.qbill.utils.Conditions.passwordVisibilityAndInvisibility
import com.qbill.utils.Conditions.setOnClick
import com.qbill.utils.Conditions.setOnWithoutAvoidDoubleClick
import com.qbill.utils.Conditions.snackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    private var context = this
    private var showPass = false
    override val viewModel: LoginViewModel by viewModels()
    override fun getLayoutRes(): Int = R.layout.activity_login
    override fun initViewModel(viewModel: LoginViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialization()
    }

    /***
     * initialization of all functions
     */
    private fun initialization() {
        setOnClick()
    }

    /**
     * for perform action on click
     */

    private fun setOnClick() {
        binding.apply {
            ivEye.setOnWithoutAvoidDoubleClick {
                showPass = !showPass
                passwordVisibilityAndInvisibility(etPassword, ivEye, showPass)
            }

            tvLogin.setOnClick {
                hideKeyboard()
                when (context.viewModel.validation()) {
                    0 -> {
                        binding.root.snackBar("Successfully Logged in.")
                        callNewPage(MainActivity::class.java)
                    }
                    else -> {
                        binding.root.snackBar(context.viewModel.errorMessage.value.toString().trim())
                    }
                }
            }
        }
    }

}