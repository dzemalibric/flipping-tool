package com.example.flippingtool.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.flippingtool.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*
import java.net.URL

/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/

class FlippingToolView : LinearLayout {
    lateinit var mUrl : String
    constructor(context: Context?, url: String) : super(context) {
        mUrl = url
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.item_image, this)
        Picasso.get().load(mUrl).into(imageView)
    }
}