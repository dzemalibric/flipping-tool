package com.example.flippingtool.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/

abstract class BaseViewHolder<T>(val view : View) : RecyclerView.ViewHolder(view) {

    var mItem : T? = null
    var mPosition : Int? = null

    /**
     * Bind viewholder with view declared in createViewHolder method
     */
    protected fun bind(item : T?,
                       position: Int? = null) {
        mItem = item
        mPosition = position
    }

    protected fun getContext() : Context {
        return view.context
    }
}