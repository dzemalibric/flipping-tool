package com.example.flippingtool.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.flippingtool.R
import com.example.flippingtool.data.images.Image
import com.example.flippingtool.data.images.ImageState
import com.example.flippingtool.helper.stopwatch.CustomStopwatch
import com.example.flippingtool.helper.ImagesGenerator
import com.example.flippingtool.helper.ScreenManager
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*


/**
 * We use this class to load images into layout that holds [ImageView].
 * @author Dzemal at 3.12.2020.
 **/

class FlippingToolView : LinearLayout {

    lateinit var mImage : Image
    lateinit var mPosition : String
    lateinit var mListener : ImageLoadingListener

    //Stopwatch counts time user spent on this image
    private var mStopwatch : CustomStopwatch? = null
    //Variable to keep track if user was watching this image zoomed
    private var wasZoomed = false

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

    /**
     * Method is used to initialize view, setup image layout params and load first state.
     */
    private fun init() {
        inflate(context, R.layout.item_image, this)
        imageView.layoutParams.width = ScreenManager.screenWidth
        imageView.layoutParams.height = ScreenManager.screenHeight
        imageView.requestLayout()
        loadThumbnailState()
    }

    /**
     * Load first image state or THUMBNAIL state. [Picasso] is used to load images into [imageView]
     * For execution purposes [MemoryPolicy.NO_CACHE] is used because it is not needed for THUMBNAIL
     * state since many images will never be loaded to THUMBNAIL state.
     */
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

    /**
     * Method used to let [FlippingToolView] know if it is currently visible on the screen.
     * Additionally, method is used to count time spent on this view.
     */
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

    /**
     * Get exact time in seconds this view has been visible on the screen while
     * it was attached to the layout.
     */
    fun getTimeSpent() : Long {
        val elapsedTime = mStopwatch?.elapsedTime
        return elapsedTime?.div(1000) ?: 0
    }

    /**
     * Returns the flag [wasZoomed] that indicates if this view has loaded [ImageState.HIGH] or
     * [ImageState.ULTRA] state of the image. Alternatively, this means view was in zoom state.
     */
    fun wasZoomed() : Boolean {
        return wasZoomed
    }

    /**
     * Load second state or [ImageState.PREVIEW] state of the image. Using noFade() and noPlaceholder()
     * because loading of this view is not visible to the user and to not waste memory
     */
    fun loadPreviewState() {
        Picasso.get().load(ImagesGenerator.generateImageUrl(mImage, ImageState.PREVIEW))
            .noFade()
            .noPlaceholder()
            .into(imageView)
    }

    /**
     * Load third state or [ImageState.MEDIUM] state of the image. Using noFade() and noPlaceholder()
     * because loading of this view is not visible to the user and to not waste memory.
     *
     * This is default state that all visible items load.
     */
    fun loadMediumState() {
        Picasso.get().load(ImagesGenerator.generateImageUrl(mImage, ImageState.MEDIUM))
            .noFade()
            .noPlaceholder()
            .into(imageView)
    }

    /**
     * Load high res image or [ImageState.HIGH] state of the image. Using noFade() and noPlaceholder()
     * because loading of this view is not visible to the user and to not waste memory
     *
     * This state will only be loaded if the image is currently being zoomed to certain value defined
     * in [FlippingToolLayout]
     */
    fun loadHighState() {
        wasZoomed = true
        Picasso.get().load(ImagesGenerator.generateImageUrl(mImage, ImageState.HIGH))
            .noFade()
            .noPlaceholder()
            .into(imageView)
    }
    /**
     * Load ultra res image or [ImageState.ULTRA] state of the image. Using noFade() and noPlaceholder()
     * because loading of this view is not visible to the user and to not waste memory
     *
     * This state will only be loaded if the image is currently being zoomed to certain value defined
     * in [FlippingToolLayout]
     */
    fun loadUltraState() {
        wasZoomed = true
        Picasso.get().load(ImagesGenerator.generateImageUrl(mImage, ImageState.ULTRA))
            .noFade()
            .noPlaceholder()
            .into(imageView)
    }

    /**
     * Listener that is called only for initial load of the images with state [ImageState.THUMBNAIL]
     * Useful application of this listener is in [FlippingToolLayout] to know exactly when first state
     * of all images has been loaded.
     */
    interface ImageLoadingListener {
        fun onImageLoaded(position: String)
    }
}