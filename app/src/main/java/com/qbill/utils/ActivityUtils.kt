package com.qbill.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.result.ActivityResultLauncher
import java.io.Serializable

object ActivityUtils {
    /**
     * for finish all activity
     */
    fun <A : Activity> Activity.callNewPage(activity: Class<A>) {
        Intent(this, activity).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(it)
            finish()
        }
    }

    /**
     * for move one to another page without skip last page
     */
    fun <A : Activity> Activity.callForwardPage(activity: Class<A>) {
        Intent(this, activity).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(it)
        }
    }
    /**
     * for move one to another page without skip last page with return intent
     */
    fun <A : Activity> Activity.callForwardPageReturn(activity: Class<A>,result: ActivityResultLauncher<Intent>,vararg params: Pair<String, Any>) =
        Intent(this, activity).apply {
            params.let {
                params.forEach { (key, value) ->
                    when (value) {
                        is Int -> this.putExtra(key, value)
                        is Byte -> this.putExtra(key, value)
                        is Char -> this.putExtra(key, value)
                        is Long -> this.putExtra(key, value)
                        is Float -> this.putExtra(key, value)
                        is Short -> this.putExtra(key, value)
                        is Double -> this.putExtra(key, value)
                        is Boolean -> this.putExtra(key, value)
                        is Bundle -> this.putExtra(key, value)
                        is String -> this.putExtra(key, value)
                        is IntArray -> this.putExtra(key, value)
                        is ByteArray -> this.putExtra(key, value)
                        is CharArray -> this.putExtra(key, value)
                        is LongArray -> this.putExtra(key, value)
                        is FloatArray -> this.putExtra(key, value)
                        is Parcelable -> this.putExtra(key, value)
                        is ShortArray -> this.putExtra(key, value)
                        is DoubleArray -> this.putExtra(key, value)
                        is BooleanArray -> this.putExtra(key, value)
                        is CharSequence -> this.putExtra(key, value)
                        is Array<*> -> {
                            when {
                                value.isArrayOf<String>() ->
                                    this.putExtra(key, value as Array<String?>)
                                value.isArrayOf<Parcelable>() ->
                                    this.putExtra(key, value as Array<Parcelable?>)
                                value.isArrayOf<CharSequence>() ->
                                    this.putExtra(key, value as Array<CharSequence?>)
                                else -> this.putExtra(key, value)
                            }
                        }
                        is Serializable -> this.putExtra(key, value)
                    }
                }
            }
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            result.launch(this)
        }


}