package com.component.cityselector

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import cardiac.live.com.livecardiacandroid.view.dialog.AddressSelectorDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun showCity(view:View?){
        var dialog = AddressSelectorDialog(this)
        dialog.show()
        dialog.onConfirmListener = {
            Log.d("TAG","打印地址:${it?.province}    ${it?.city}    ${it?.distinct}")
        }
    }
}
