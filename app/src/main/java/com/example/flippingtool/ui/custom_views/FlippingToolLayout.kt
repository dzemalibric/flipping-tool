package com.example.flippingtool.ui.custom_views

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.children
import com.example.flippingtool.helper.ScreenManager
import com.example.flippingtool.data.images.Image
import com.otaliastudios.zoom.ZoomEngine
import com.otaliastudios.zoom.ZoomLayout
import java.lang.Exception
import java.security.InvalidParameterException


/**
 * This custom layout is essential part of the project. Every major logic for scrolling, zooming and
 * [Image] states is handled here.
 *
 * With major help from guys developing it, [ZoomLayout] attaches to [FlippingToolLayout] to give
 * all important parameters needed to figure out scrolling, zooming and current visible children.
 *
 *
 * @author Dzemal at 2.12.2020.
 **/

class FlippingToolLayout : LinearLayout, FlippingToolView.ImageLoadingListener {

    lateinit var zoomLayout: ZoomLayout
    lateinit var images: LinkedHashMap<String, Image>
    private var imagesLoaded = false
    private lateinit var gestureDetector: GestureDetector

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    )

    /**
     * Overridden method to ensure that all the views added in this layout are explicitly of type
     * [FlippingToolView]. Does not have usage for UX, but gives developer a nudge in right direction.
     */
    override fun addView(child: View?) {
        if (child !is FlippingToolView)
            throw InvalidParameterException("View in FlippingToolLayout must be of type FlippingToolView")
        super.addView(child)
    }

    /**
     * Simplify adding of raw images to the layout. Generate list of
     * [FlippingToolView] using provided data.
     */
    fun addImages(images: LinkedHashMap<String, Image>) {
        this.images = images
        for ((key, value) in images) {
            addView(FlippingToolView(context, value, key, this))
        }
    }

    fun getChild(position: Int): FlippingToolView {
        return getChildAt(position) as FlippingToolView
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    fun attachToZoomLayout(zoomLayout: ZoomLayout) {
        this.zoomLayout = zoomLayout
        gestureDetector = GestureDetector(context, GestureListener(zoomLayout))
    }

    /**
     * This method is essential for updating viewPort. It is called every time user swipes in
     * either direction
     *
     * We define [zoomEngine] from [ZoomLayout] to give us value of the current zoom. If [zoomEngine]
     * is not provided it means that we are initiating layout for the first time
     */
    fun update(zoomEngine: ZoomEngine?) {
        val visibleChildren = getVisibleChildren()
        for (index in 0 until childCount) {
            val flipToolView = getChild(index)
            //Logic for not currently visible items
            if (!isVisible(flipToolView)) {
                flipToolView.exitedViewPort()
                //Load initial states
                if (withinPreviewRange(flipToolView, visibleChildren)) {
                    flipToolView.loadPreviewState()
                } else {
                    flipToolView.loadThumbnailState()
                }
                //Load next states for eligible items
                if (nextToVisibleItem(flipToolView, visibleChildren)) {
                    flipToolView.loadMediumState()
                }
            }
            //Logic for visible items and zoom mechanics
            else {
                flipToolView.enteredViewPort()
                //If there is a zoom applied we must go through zoom mechanics
                if (zoomEngine != null) {
                    updateZoom(zoomEngine, flipToolView)
                }
                //If there is no zoom load medium state of the visible image
                else {
                    flipToolView.loadMediumState()
                }
            }
        }
    }

    /**
     * To check if THUMBNAIL images loaded we need to have information about every single one of
     * those images loading state.
     *
     * To not call this method every time image loads, we needed to add simple flag [imagesLoaded]
     * to know when we are all set.
     */
    override fun onImageLoaded(position: String) {
        if (!imagesLoaded && position.toInt() == images.size) {
            update(null)
            imagesLoaded = true
        }
    }

    /**
     * Method returns all children currently visible on the screen
     */
    private fun getVisibleChildren(): ArrayList<FlippingToolView> {
        val visibleChildren = ArrayList<FlippingToolView>()
        for (image in children) {
            if (isVisible(image)) {
                visibleChildren.add(image as FlippingToolView)
            }
        }
        return visibleChildren
    }

    /**
     * Check if current [flipToolView] is in [PREVIEW_RANGE].
     *
     * This method will take [firstVisibleChild] and [lastVisibleChild] from visible children in
     * this layout and compare if the view is within [PREVIEW_RANGE].
     *
     * We need this method so we know when to load PREVIEW state of the image
     */
    private fun withinPreviewRange(flipToolView: FlippingToolView,
                                   visibleChildren: ArrayList<FlippingToolView>): Boolean {
        return try {
            val firstVisibleChild = getVisibleChildren()[0]
            val lastVisibleChild = getVisibleChildren()[visibleChildren.size - 1]
            val itemPosition = flipToolView.mPosition.toInt()
            val firstVisibleChildPosition = firstVisibleChild.mPosition.toInt()
            val lastVisibleChildPosition = lastVisibleChild.mPosition.toInt()
            (itemPosition < PREVIEW_RANGE + lastVisibleChildPosition
                    && itemPosition > firstVisibleChildPosition - PREVIEW_RANGE)
        } catch (e: IndexOutOfBoundsException) {
            false
        }
    }

    /**
     * Check if current [flipToolView] is next to any visible child.
     *
     * We need this method so we know when to load MEDIUM state of not currently visible image
     */
    private fun nextToVisibleItem(flipToolView: FlippingToolView,
                                  visibleChildren: ArrayList<FlippingToolView>): Boolean {
        return try {
            val firstVisibleChild = getVisibleChildren()[0]
            val lastVisibleChild = getVisibleChildren()[visibleChildren.size - 1]
            firstVisibleChild.mPosition.toInt() - flipToolView.mPosition.toInt() == 1 ||
                    flipToolView.mPosition.toInt() - lastVisibleChild.mPosition.toInt() == 1
        } catch (e: IndexOutOfBoundsException) {
            false
        }
    }

    /**
     * This is an essential zoom view logic.
     * [zoomValue] is a float value of the current zoom in our [ZoomLayout].
     *
     * We update image on [HIGH_RES_ZOOM_VALUE] and [ULTRA_RES_ZOOM_VALUE]. Upon returning to
     * [DEFAULT_ZOOM_VALUE] we should load MEDIUM image state again.
     */
    private fun updateZoom(zoomEngine: ZoomEngine, flipToolView: FlippingToolView) {
        val zoomValue = zoomEngine.zoom
        if (zoomValue > HIGH_RES_ZOOM_VALUE && zoomValue < ULTRA_RES_ZOOM_VALUE) {
            flipToolView.loadHighState()
        } else if (zoomValue <= HIGH_RES_ZOOM_VALUE) {
            flipToolView.loadMediumState()
        } else if (zoomValue >= ULTRA_RES_ZOOM_VALUE) {
            flipToolView.loadUltraState()
        }
    }

    /**
     * Method checks if [view] is currently visible on the screen. To check this we must have
     * current [scrollX] value given in [zoomLayout] that we attached to this layout.
     *
     * Logic is simple. Generate two rectangles using coordinates for [view] and current visible screen.
     * Use top, left, bottom and right coordinates to generate rectangles and check if they intersect.
     */
    private fun isVisible(view: View?): Boolean {
        if (view == null) {
            return false
        }
        if (!view.isShown) {
            return false
        }
        val actualPosition = Rect()
        view.getGlobalVisibleRect(actualPosition)
        val scrollX = zoomLayout.panX
        val screen = Rect(
                -scrollX.toInt(),
                0,
                -scrollX.toInt() + ScreenManager.screenWidth,
                ScreenManager.screenHeight)
        return actualPosition.intersect(screen)
    }

    /**
     * Listener used to implement double tap on this layout. Since [zoomLayout] already has
     * [GestureDetector] and it is advised not to override it, we implement [GestureListener] in
     * [FlippingToolLayout] to detect double tap and call [zoomLayout].zoomTo() method
     */
    internal class GestureListener(val zoomLayout: ZoomLayout) : SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        // event when double tap occurs
        override fun onDoubleTap(e: MotionEvent): Boolean {
            if (zoomLayout.engine.zoom < DOUBLE_TAP_ZOOM_VALUE) {
                zoomLayout.zoomTo(DOUBLE_TAP_ZOOM_VALUE, true)
            } else {
                zoomLayout.zoomTo(DEFAULT_ZOOM_VALUE, true)
            }
            return true
        }
    }

    companion object {
        const val PREVIEW_RANGE = 15
        const val DOUBLE_TAP_ZOOM_VALUE = 1.5f
        const val DEFAULT_ZOOM_VALUE = 1.0f
        const val HIGH_RES_ZOOM_VALUE = 1.3f
        const val ULTRA_RES_ZOOM_VALUE = 2.0f
    }
}