package com.example.flippingtool.ui

import android.content.Context
import android.graphics.Matrix
import com.example.flippingtool.R
import com.example.flippingtool.data.images.Image
import com.example.flippingtool.data.images.ImageState
import com.example.flippingtool.data.report.ReportData
import com.example.flippingtool.ui.base.BaseDialog
import com.otaliastudios.zoom.ZoomEngine
import kotlinx.android.synthetic.main.dialog_catalogue.*

/**
 *
 *
 *
 * @author Comp at 6.12.2020.
 **/

class CatalogueDialog(mContext: Context?,
                      private val images : LinkedHashMap<String, Image>,
                      val dismissListener: OnDismissListener) : BaseDialog(mContext), ZoomEngine.Listener {

    private var reportData = ArrayList<ReportData>()

    override val mLayoutRId: Int
        get() = R.layout.dialog_catalogue

    override fun setupDialog() {
        super.setupDialog()
        flipToolLayout.addImages(images)
        flipToolLayout.attachToZoomLayout(zoomLayout)
        zoomLayout.engine.addListener(this)
    }

    override fun onIdle(engine: ZoomEngine) {
        flipToolLayout.update(engine)
    }

    override fun onUpdate(engine: ZoomEngine, matrix: Matrix) {
    }

    override fun onDetachedFromWindow() {
        for (index in 0 until flipToolLayout.childCount) {
            val flipView = flipToolLayout.getChild(index)
            reportData.add(ReportData(flipView.mImage.id, flipView.getTimeSpent(), flipView.wasZoomed()))
        }
        dismissListener.onDismiss(reportData)
        super.onDetachedFromWindow()
    }

    interface OnDismissListener {
        fun onDismiss(reportData: ArrayList<ReportData>)
    }
}