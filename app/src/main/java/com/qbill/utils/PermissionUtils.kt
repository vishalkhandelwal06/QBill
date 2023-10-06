package com.qbill.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

object PermissionUtils {
    /**
     * this is for check the notification permission
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun Context.hasNotificationPermission() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.POST_NOTIFICATIONS
    ) == PackageManager.PERMISSION_GRANTED

}