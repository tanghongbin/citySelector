package com.component.cityselector

import android.app.Application
import component.com.cityslectorcomponent.view.AddressSelectorHelper
import org.litepal.LitePal

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
        AddressSelectorHelper.initAreaData(this)
    }
}