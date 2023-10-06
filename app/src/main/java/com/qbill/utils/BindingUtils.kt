package com.qbill.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.qbill.utils.Conditions.gone
import com.qbill.utils.Conditions.show

object BindingUtils {

    /**
     * set Background
     */
    @BindingAdapter("setEmailVisibility")
    fun View.setEmailVisibility(value:Int) {
        if(value==1) {
            show()
        }else{
            gone()
        }
    }
    /**
     * set Background
     */
    @BindingAdapter("setPasswordVisibility")
    fun View.setPasswordVisibility(value:Int) {
        if(value==2) {
            show()
        }else{
            gone()
        }
    }
}