package com.example.wasteless.page.decorator

import android.content.Context
import com.example.wasteless.page.report.Report

abstract class ReportDecorator constructor(var decoratedReport: Report)
    : Report {
    
    override fun show(context: Context) {
        decoratedReport.show(context)
    }
}