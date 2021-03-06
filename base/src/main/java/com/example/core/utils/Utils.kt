package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication

class Utils {

    companion object {
        private val displayMetrics = Resources.getSystem().displayMetrics


        @JvmStatic
        fun dp2px(dp: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
        }

        @JvmStatic
        fun toast(string: String) {
            toast(string, Toast.LENGTH_SHORT)
        }

        @JvmStatic
        fun toast(string: String, duration: Int) {
            Toast.makeText(BaseApplication.currentApplication, string, duration).show()

        }

    }
}