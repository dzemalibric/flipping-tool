package com.example.flippingtool

import android.content.res.Resources
import android.graphics.Matrix
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewStructure
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flippingtool.custom_views.FlippingToolView
import com.example.flippingtool.images.Image
import com.example.flippingtool.images.ImageQuality
import com.example.flippingtool.images.ImagesGenerator
import com.otaliastudios.zoom.ZoomEngine
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ZoomEngine.Listener {

    private var qualityStatus : ImageQuality = ImageQuality.DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val width = Resources.getSystem().displayMetrics.widthPixels
        val height = Resources.getSystem().displayMetrics.heightPixels
        ScreenManager.screenHeight = height
        ScreenManager.screenWidth = width
        val images = ImagesGenerator.generateImages()
        for (image in images) {
            flipToolLayout.addView(FlippingToolView(this, image))
        }
        zoomLayout.scrollTo(0,0)
        zoomLayout.engine.addListener(this)
    }

    override fun onIdle(engine: ZoomEngine) {
        if (engine.zoom > 1.3f && qualityStatus != ImageQuality.HIGH) {
            for (image in flipToolLayout.children) {
                (image as FlippingToolView).loadHigh()
            }
            qualityStatus = ImageQuality.HIGH
        } else if (engine.zoom <= 1.3f && qualityStatus == ImageQuality.HIGH) {
            for (image in flipToolLayout.children) {
                (image as FlippingToolView).loadMedium()
            }
            qualityStatus = ImageQuality.DEFAULT
        }
    }

    override fun onUpdate(engine: ZoomEngine, matrix: Matrix) {

    }
}