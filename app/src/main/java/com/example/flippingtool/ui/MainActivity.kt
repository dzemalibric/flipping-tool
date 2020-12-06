package com.example.flippingtool.ui

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flippingtool.R
import com.example.flippingtool.helper.ScreenManager
import com.example.flippingtool.data.images.ImageState
import com.example.flippingtool.data.report.ReportData
import com.example.flippingtool.helper.ImagesGenerator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), CatalogueDialog.OnDismissListener {

    private val images = ImagesGenerator.generateImages()
    private var reportData = ArrayList<ReportData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initScreen()
        Picasso.get()
            .load(ImagesGenerator.generateImageUrl(images["1"]!!, ImageState.PREVIEW))
            .into(catalogueThumbnail)
        catalogueThumbnail.setOnClickListener {
            CatalogueDialog(this, images, this).show()
        }
        reportButton.setOnClickListener {
            ReportDialog(this, reportData).show()
        }
    }

    private fun initScreen() {
        val width = Resources.getSystem().displayMetrics.widthPixels
        val height = Resources.getSystem().displayMetrics.heightPixels
        ScreenManager.screenHeight = height
        ScreenManager.screenWidth = width
    }

    override fun onDismiss(reportData: ArrayList<ReportData>) {
        this.reportData = reportData
    }

}