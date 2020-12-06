package com.example.flippingtool.ui

import android.content.Context
import com.example.flippingtool.R
import com.example.flippingtool.data.report.ReportData
import com.example.flippingtool.ui.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_report.*

/**
 *
 *
 *
 * @author Comp at 6.12.2020.
 **/

class ReportDialog(mContext: Context?,
                   private val reportData: ArrayList<ReportData>) : BaseDialog(mContext) {

    override val mLayoutRId: Int
        get() = R.layout.dialog_report

    override fun setupDialog() {
        super.setupDialog()
        var reportDataText = ""
        for (report in reportData) {
            reportDataText += report.id + ": " + report.timeSpent + " seconds, Zoomed :" + report.zoomed.toString() + "\n\n"
        }
        reportText.text = reportDataText
    }
}