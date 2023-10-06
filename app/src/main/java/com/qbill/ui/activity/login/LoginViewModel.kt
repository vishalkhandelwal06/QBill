package com.qbill.ui.activity.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qbill.R
import com.qbill.utils.Conditions
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    /**
     * initialize all the variable and get/set the value
     */
    var email = MutableLiveData("")
    var password = MutableLiveData("")
    var errorMessage = MutableLiveData("")

    /**
     * for show validation
     */
    fun validation(): Int {
        return  if (!Conditions.checkNull(email.value.toString().trim())) {
            errorMessage.value = context.getString(R.string.please_enter_email_address)
            1
        } else if (Conditions.checkNull(email.value.toString().trim()) && Conditions.isValidEmail(
                email.value.toString().trim()
            )
        ) {
            errorMessage.value = context.getString(R.string.please_enter_valid_email_address)
            1
        } else if (!Conditions.checkNull(password.value.toString().trim())) {
            errorMessage.value = context.getString(R.string.please_enter_password)
            2
        } else {
            0
        }
    }

}