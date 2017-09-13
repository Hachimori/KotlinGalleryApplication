package com.github.hachimori.kotlingalleryapplication.ui.base

import android.support.v7.app.AppCompatActivity

/**
 * Created by benhachimori on 2017/09/12.
 */
open class BaseActivity: AppCompatActivity() {
    
    lateinit var viewModel: BaseViewModel

    protected fun bindViewModel(viewModel: BaseViewModel) {
        this.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        viewModel.onPause()
        super.onPause()
    }
}