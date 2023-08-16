package com.zeel_enterprise.androidcustomlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zeel_enterprise.customlibrary.CustomClass

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CustomClass.showToast(this@MainActivity, "Hey there, This is Toast using library!")

    }
}