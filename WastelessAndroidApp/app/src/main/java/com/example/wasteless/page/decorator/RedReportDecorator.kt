package com.example.wasteless.page.decorator

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.example.wasteless.R
import com.example.wasteless.page.report.Report

class RedReportDecorator constructor(var report: Report, override var wastedCalories: Int) :
    ReportDecorator(report) {

    override fun show(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("WATCH OUT!\nYou waste $wastedCalories calories!")
        //dialogBuilder.setView(R.layout.red_report_dialog_layout)
        dialogBuilder.show()
    }
}