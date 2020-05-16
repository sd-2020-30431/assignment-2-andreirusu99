package com.example.wasteless.page.decorator

import android.app.AlertDialog
import android.content.Context
import com.example.wasteless.R
import com.example.wasteless.page.report.Report

class GreenReportDecorator constructor(var report: Report, override var wastedCalories: Int) :
    ReportDecorator(report) {

    override fun show(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("ALL GOOD!\nYou waste only $wastedCalories calories.")
        //dialogBuilder.setView(R.layout.green_report_dialog_layout)
        dialogBuilder.show()
    }
}