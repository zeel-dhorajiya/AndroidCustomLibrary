package com.zeel_enterprise.customlibrary

import android.app.Activity
import android.widget.Toast

class CustomClass {

    companion object {

        fun showToast(context: Activity, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

    }

}