package com.qbill.ui.activity.splash

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import com.qbill.R
import com.qbill.databinding.ActivitySplashBinding
import com.qbill.ui.activity.login.LoginActivity
import com.qbill.utils.ActivityUtils.callNewPage
import com.qbill.utils.PermissionUtils.hasNotificationPermission
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialization()
    }

    /**
     * this is for initialize all the functions
     */
    private fun initialization() {
        showAnimation()
    }

    /**
     * for show animation
     */
    private fun showAnimation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        binding.ivImage.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (hasNotificationPermission()) {
                        performAction()
                    } else {
                        requestSinglePermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }

                }else{
                    performAction()
                }
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
    }

    /***
     * perform action
     */
    private fun performAction() {
        callNewPage(LoginActivity::class.java)
    }

    /**
     *
     * this is for notification permission
     *
     */
    private val requestSinglePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            performAction()
        }
}