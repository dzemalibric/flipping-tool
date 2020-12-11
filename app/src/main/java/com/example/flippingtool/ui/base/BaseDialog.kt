package com.example.flippingtool.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window

/**
 * Base class for dialogs in the app. Simplifies layout inflation
 * @author Dzemal at 6.12.2020.
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

    /**
     * Override and declare your Layout Resource in inherited class
     */
    abstract val mLayoutRId: Int

    /**
     * Optional method for setting up dialog views
     *
     * Use this method instead of [onCreate] to setup views inside your layout
     */
    open fun setupDialog() {}
}