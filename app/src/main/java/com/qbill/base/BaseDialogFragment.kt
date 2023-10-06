package com.qbill.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.qbill.BR

abstract class BaseDialogFragment<VB : ViewDataBinding, VM : ViewModel> : DialogFragment() {

    abstract val viewModel: VM
    open lateinit var binding: VB

    private fun init(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init(inflater, container)
        super.onCreateView(inflater, container, savedInstanceState)
        binding.setVariable(BR._all, viewModel)
        return binding.root
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int
}


