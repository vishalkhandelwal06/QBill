package com.qbill.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import com.qbill.R
import com.qbill.base.BaseActivity
import com.qbill.databinding.ActivityMainBinding
import com.qbill.ui.activity.login.LoginActivity
import com.qbill.ui.adapter.DrawerAdapter
import com.qbill.ui.model.DrawerModel
import com.qbill.utils.ActivityUtils.callNewPage
import com.qbill.utils.Conditions.setOnClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private var context = this
    private var backFlag = false
    private lateinit var drawerAdapter: DrawerAdapter
    private var navController: NavController? = null
    override val viewModel: MainViewModel by viewModels()
    override fun getLayoutRes(): Int = R.layout.activity_main
    override fun initViewModel(viewModel: MainViewModel) {
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
        setAdapter()
        setOnClick()
    }

    /**
     * for perform action on click
     */

    private fun setOnClick() {
        binding.apply {

        }
    }

    /**
     * set adapter and show listing of data
     */
    private fun setAdapter() {
        viewModel.drawerArrayList = ArrayList()
        viewModel.drawerArrayList.apply {
            add(DrawerModel(R.drawable.ic_home, context.getString(R.string.home)))
            add(DrawerModel(R.drawable.ic_home, context.getString(R.string.home)))
            add(DrawerModel(R.drawable.ic_home, context.getString(R.string.home)))
            add(DrawerModel(R.drawable.ic_home, context.getString(R.string.home)))
            add(DrawerModel(R.drawable.ic_home, context.getString(R.string.home)))
        }
        drawerAdapter = DrawerAdapter(context) { drawerModel, position ->
            binding.drawerLayout.closeDrawers()

        }
        drawerAdapter.submitList(viewModel.drawerArrayList)
        binding.drawer.rvDrawer.adapter = drawerAdapter
        binding.toolbar.ivNav.setOnClick {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.drawer.consUserInfo.setOnClick {
            binding.drawerLayout.closeDrawers()
        }
        binding.drawer.tvLogOut.setOnClick {
            binding.drawerLayout.closeDrawers()
            callNewPage(LoginActivity::class.java)
        }
    }
}