package com.example.flippingtool.custom_views

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.example.flippingtool.images.Image
import com.otaliastudios.zoom.ZoomLayout
import java.security.InvalidParameterException


/**
 *
 *
 *
 * @author Comp at 2.12.2020.
 **/

class FlippingToolLayout : LinearLayout, FlippingToolView.LoadingListener  {

    lateinit var zoomLayout : ZoomLayout
    lateinit var images : ArrayList<Image>
    private var imagesLoaded = 0
    private var loaded = false
    private lateinit var gestureDetector: GestureDetector


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

    fun addImages(images: ArrayList<Image>) {
        this.images = images
        for (image in images) {
            addView(FlippingToolView(context, image, this))
        }
    }

    fun attachToZoomLayout(zoomLayout: ZoomLayout) {
        this.zoomLayout = zoomLayout
        gestureDetector = GestureDetector(context, GestureListener(zoomLayout))
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    override fun onLoaded() {
        if (!loaded) {
            imagesLoaded++
            if (imagesLoaded == images.size) {
                Handler(Looper.getMainLooper()).postDelayed({
                    zoomLayout.moveTo(0f, 0f, 0f, true)
                }, 1000)
                loaded = true
            }
        }
    }

    internal class GestureListener(val zoomLayout: ZoomLayout) : SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        // event when double tap occurs
        override fun onDoubleTap(e: MotionEvent): Boolean {
            if (zoomLayout.engine.zoom < 1.5f) {
                zoomLayout.zoomTo(1.5f,true)
            } else {
                zoomLayout.zoomTo(1.0f,  true)
            }
            return true
        }
    }
}