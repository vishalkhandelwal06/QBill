package com.qbill.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel


abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    abstract val viewModel: VM

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as VB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel(viewModel)
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initViewModel(viewModel: VM)


}