package com.example.flippingtool

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flippingtool.custom_views.FlippingToolView
import com.example.flippingtool.images.ImageQuality
import com.example.flippingtool.images.ImagesGenerator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val images = ImagesGenerator.generateImages()
        for (image in images) {
            flipToolLayout.addView(FlippingToolView(this, ImagesGenerator.generateImageUrl(image, ImageQuality.HIGH)))
        }
    }
}