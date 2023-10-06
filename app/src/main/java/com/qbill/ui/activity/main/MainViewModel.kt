package com.qbill.ui.activity.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.qbill.ui.model.DrawerModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    /**
     * initialize all the variable and get/set the value
     */
    var drawerArrayList = ArrayList<DrawerModel>()

}