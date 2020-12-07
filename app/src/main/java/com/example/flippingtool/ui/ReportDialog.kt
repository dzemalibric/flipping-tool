package com.example.flippingtool.ui

import android.content.Context
import com.example.flippingtool.R
import com.example.flippingtool.data.report.Report
import com.example.flippingtool.ui.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_report.*

/**
 * Used to display previous user interaction with [CatalogueDialog].
 * Here it is shown how many seconds user spent on each image and if those images were zoomed during
 * time he spent there.
 * @author Dzemal at 6.12.2020.
 **/

class ReportDialog(mContext: Context?,
                   private val reportData: ArrayList<Report>) : BaseDialog(mContext) {

    override val mLayoutRId: Int
        get() = R.layout.dialog_report

    override fun setupDialog() {
        super.setupDialog()
        generateReportData()
    }

    /**
     * Simple way to generate report data that will show id, timeSpent and zoomed value of every
     * report provided for each image.
     */
    private fun generateReportData() {
        var reportDataText = ""
        for (report in reportData) {
            reportDataText += report.id + ": " + report.timeSpent + " seconds, Zoomed :" + report.zoomed.toString() + "\n\n"
        }
        reportText.text = reportDataText
    }
}