package com.example.flippingtool.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window

/**
 *
 *
 *
 * @author Comp at 6.12.2020.
 **/

abstract class BaseDialog(mContext: Context?) : Dialog(mContext!!) {

    init {
        window.run { this?.setBackgroundDrawableResource(android.R.color.transparent) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayoutRId)
        setupDialog()
    }

    abstract val mLayoutRId : Int

    open fun setupDialog() {}
}