package com.example.flippingtool.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.flippingtool.R
import com.example.flippingtool.data.images.Image
import com.example.flippingtool.data.images.ImageState
import com.example.flippingtool.helper.CustomStopwatch
import com.example.flippingtool.helper.ImagesGenerator
import com.example.flippingtool.helper.ScreenManager
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*
import timerx.Stopwatch
import timerx.StopwatchBuilder
import java.util.concurrent.TimeUnit


/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/

class FlippingToolView : LinearLayout {

    lateinit var mImage : Image
    lateinit var mPosition : String
    lateinit var mListener : ImageLoadingListener

    //Stopwatch counts time user spent on this image
    private var mStopwatch : CustomStopwatch? = null
    //Variable to keep track if user was watching this image zoomed
    private var mZoomed = false

    constructor(context: Context?, image: Image, position: String, listener: ImageLoadingListener) :
            super(context) {
        mImage = image
        mPosition = position
        mListener = listener
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.item_image, this)
        imageView.layoutParams.width = ScreenManager.screenWidth
        imageView.layoutParams.height = ScreenManager.screenHeight
        imageView.requestLayout()
        loadThumbnailState()
    }

    fun loadThumbnailState() {
        Picasso.get().load(ImagesGenerator.generateImageUrl(mImage, ImageState.THUMBNAIL))
            .noFade()
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    mListener.onImageLoaded(mPosition)
                }

                override fun onError(e: Exception?) {

                }

            })
    }

    fun enteredViewPort() {

        if (mStopwatch == null) {
            mStopwatch = CustomStopwatch()
            mStopwatch?.start()
        } else {
            mStopwatch?.resume()
        }
    }

    fun exitedViewPort() {
        mStopwatch?.pause()
    }

    fun getTimeSpent() : Long {
        val elapsedTime = mStopwatch?.elapsedTime
        return elapsedTime?.div(1000) ?: 0
    }

    fun wasZoomed() : Boolean {
        return mZoomed
    }

    fun loadPreviewState() {
        Picasso.get().load(ImagesGenerator.generateImageUrl(mImage, ImageState.PREVIEW))
            .noFade()
            .noPlaceholder()
            .into(imageView)
    }

    fun loadMediumState() {
        Picasso.get().load(ImagesGenerator.generateImageUrl(mImage, ImageState.MEDIUM))
            .noFade()
            .noPlaceholder()
            .into(imageView)
    }

    fun loadHighState() {
        mZoomed = true
        Picasso.get().load(ImagesGenerator.generateImageUrl(mImage, ImageState.HIGH))
            .noFade()
            .noPlaceholder()
            .into(imageView)
    }

    fun loadUltraState() {
        mZoomed = true
        Picasso.get().load(ImagesGenerator.generateImageUrl(mImage, ImageState.ULTRA))
            .noFade()
            .noPlaceholder()
            .into(imageView)
    }

    interface ImageLoadingListener {
        fun onImageLoaded(position: String)
    }
}