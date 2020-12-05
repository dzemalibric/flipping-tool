package com.example.flippingtool.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.size
import com.example.flippingtool.R
import com.example.flippingtool.ScreenManager
import com.example.flippingtool.images.Image
import com.example.flippingtool.images.ImageQuality
import com.example.flippingtool.images.ImagesGenerator
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*
import java.lang.Exception
import java.net.URL

/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/

class FlippingToolView : LinearLayout {
    lateinit var image : Image
    lateinit var mListener: LoadingListener

    constructor(context: Context?, image: Image, listener: LoadingListener) : super(context) {
        this.image = image
        mListener = listener
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
        loadThumbnail()
        loadMedium()
    }

    fun loadThumbnail() {
        Picasso.get().load(ImagesGenerator.generateImageUrl(image, ImageQuality.THUMBNAIL))
            .resize(ScreenManager.screenWidth, ScreenManager.screenHeight)
            .noFade()
            .noPlaceholder()
            .into(imageView)
    }

    fun loadPreview() {
        Picasso.get().load(ImagesGenerator.generateImageUrl(image, ImageQuality.PREVIEW))
            .fit()
            .noFade()
            .noPlaceholder()
            .into(imageView)
    }

    fun loadMedium() {
        Picasso.get().load(ImagesGenerator.generateImageUrl(image, ImageQuality.MEDIUM))
            .fit()
            .centerInside()
            .noFade()
            .noPlaceholder()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    mListener.onLoaded()
                }

                override fun onError(e: Exception?) {
                }

            })
    }

    fun loadHigh() {
        Picasso.get().load(ImagesGenerator.generateImageUrl(image, ImageQuality.HIGH))
            .fit()
            .noFade()
            .noPlaceholder()
            .into(imageView)
    }

    interface LoadingListener {
        fun onLoaded()
    }
}