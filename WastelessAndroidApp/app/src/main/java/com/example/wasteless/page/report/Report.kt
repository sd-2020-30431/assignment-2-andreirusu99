package com.example.wasteless.page.report

import android.content.Context

interface Report {

    var wastedCalories: Int

    fun show(context: Context)
}