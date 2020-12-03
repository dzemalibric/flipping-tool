package com.example.flippingtool.adapter

/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/

open class RVModel(var mViewType : ViewType) {

    enum class ViewType {
        IMAGE;

        companion object {
            fun getViewType(position: Int): ViewType {
                return values()[position]
            }
        }
    }
}