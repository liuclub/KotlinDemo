package com.example.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.example.app.R
import com.example.core.utils.Utils
import java.util.jar.Attributes
import kotlin.random.Random

class CodeView(context: Context, attributeSet: AttributeSet?) : AppCompatTextView(context, attributeSet) {

    constructor(context: Context) : this(context, null)

    private val paint = Paint().also {
        it.isAntiAlias = true
        it.style = Paint.Style.STROKE
        it.color = resources.getColor(R.color.colorAccent)
        it.strokeWidth = Utils.dp2px(6f)
    }
    private val codeList = arrayListOf(
            "kotlin",
            "android",
            "java",
            "http",
            "https",
            "okhttp",
            "retrofit",
            "tcp/ip"
    )

    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(resources.getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)

        updateCode()
    }

    fun updateCode() {
        val random = Random.nextInt(codeList.size)
        val code = codeList[random]
        text = code
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0f, height.toFloat(), width.toFloat(), 0f, paint)
        super.onDraw(canvas)
    }
}