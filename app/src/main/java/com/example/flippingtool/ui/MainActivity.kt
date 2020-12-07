package com.example.flippingtool.ui

import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flippingtool.R
import com.example.flippingtool.helper.ScreenManager
import com.example.flippingtool.data.images.ImageState
import com.example.flippingtool.data.report.Report
import com.example.flippingtool.helper.ImagesGenerator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Dzemal at 6.12.2020.
 **/
class MainActivity : AppCompatActivity(), CatalogueDialog.OnDismissListener {

    //Images provided in json
    private val images = ImagesGenerator.generateImages()
    //Data to show after user interaction
    private var reportData = ArrayList<Report>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initScreen()
    }

    private fun initScreen() {
        updateScreenDimensions()
        loadThumbnail()
        setupButtons()
    }

    /**
     * Load thumbnail of the first image in [images] to [catalogueThumbnail].
     */
    private fun loadThumbnail() {
        Picasso.get()
            .load(ImagesGenerator.generateImageUrl(images["1"]!!, ImageState.PREVIEW))
            .into(catalogueThumbnail)
    }

    /**
     * Setup all interactive views in layout.
     */
    private fun setupButtons() {
        catalogueThumbnail.setOnClickListener {
            CatalogueDialog(this, images, this).show()
        }
        reportButton.setOnClickListener {
            if (reportData.isNotEmpty()) {
                ReportDialog(this, reportData).show()
            } else {
                Toast.makeText(
                    this,
                    "Please open catalogue to show user data",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    /**
     * Set values to [ScreenManager] for other classes to use later on.
     */
    private fun updateScreenDimensions() {
        val width = Resources.getSystem().displayMetrics.widthPixels
        val height = Resources.getSystem().displayMetrics.heightPixels
        ScreenManager.screenHeight = height
        ScreenManager.screenWidth = width
    }

    /**
     * Callback from [CatalogueDialog] indicating that user finished interacting with it.
     * [reportData] is populated here and ready to be shown in [ReportDialog]
     */
    override fun onDismiss(reportData: ArrayList<Report>) {
        this.reportData = reportData
    }

}