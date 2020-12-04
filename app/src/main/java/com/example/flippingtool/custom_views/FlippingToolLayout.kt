package com.example.flippingtool.custom_views

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.Rect
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.children
import com.example.flippingtool.ScreenManager
import java.security.InvalidParameterException

/**
 *
 *
 *
 * @author Comp at 2.12.2020.
 **/

class FlippingToolLayout : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun addView(child: View?) {
        if (child !is FlippingToolView) throw InvalidParameterException("View in FlippingToolLayout must be of type FlippingToolView")
        super.addView(child)
    }
}