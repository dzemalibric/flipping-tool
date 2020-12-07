package com.example.flippingtool.ui

import android.content.Context
import android.graphics.Matrix
import com.example.flippingtool.R
import com.example.flippingtool.data.images.Image
import com.example.flippingtool.data.report.Report
import com.example.flippingtool.ui.base.BaseDialog
import com.otaliastudios.zoom.ZoomEngine
import kotlinx.android.synthetic.main.dialog_catalogue.*

/**
 * Dialog used to show catalogue populated with generated images injected in class constructor.
 * @author Dzemal at 6.12.2020.
 **/
class CatalogueDialog(mContext: Context?,
                      private val images : LinkedHashMap<String, Image>,
                      private val dismissListener: OnDismissListener) : BaseDialog(mContext), ZoomEngine.Listener {

    //Data for last user interaction with catalogue
    private var reportData = ArrayList<Report>()

    override val mLayoutRId: Int
        get() = R.layout.dialog_catalogue

    override fun setupDialog() {
        super.setupDialog()
        flipToolLayout.addImages(images)
        flipToolLayout.attachToZoomLayout(zoomLayout)
        zoomLayout.engine.addListener(this)
    }

    /**
     * Method called each time when user scrolls in either direction.
     * We simply use it to update our [flipToolLayout] and load states
     */
    override fun onIdle(engine: ZoomEngine) {
        flipToolLayout.update(engine)
    }

    override fun onUpdate(engine: ZoomEngine, matrix: Matrix) {}

    /**
     * We want to send last report data whenever user dismissed this dialog.
     * Before it is dismissed we will populate our [reportData] with data from [flipToolLayout]
     * and dispatch it to [dismissListener]
     */
    override fun onDetachedFromWindow() {
        generateReportData()
        dismissListener.onDismiss(reportData)
        super.onDetachedFromWindow()
    }

    private fun generateReportData() {
        for (index in 0 until flipToolLayout.childCount) {
            val flipView = flipToolLayout.getChild(index)
            reportData.add(Report(flipView.mImage.id, flipView.getTimeSpent(), flipView.wasZoomed()))
        }
    }

    /**
     * Listener to notify when dialog is dismissed.
     * Needed in [MainActivity] for report data
     */
    interface OnDismissListener {
        fun onDismiss(reportData: ArrayList<Report>)
    }
}