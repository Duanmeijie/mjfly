package com.dmj.fly.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class VirtualStickView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val basePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xFF4CAF50.toInt()
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    private val stickPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xFF2196F3.toInt()
        style = Paint.Style.FILL
    }

    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f
    private var stickX = 0f
    private var stickY = 0f

    private var normalizedX = 0f
    private var normalizedY = 0f

    var onStickChanged: ((x: Float, y: Float) -> Unit)? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        radius = minOf(w, h) / 2f - 40f
        stickX = centerX
        stickY = centerY
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(centerX, centerY, radius, basePaint)
        canvas.drawCircle(stickX, stickY, radius / 3f, stickPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                updateStickPosition(event.x, event.y)
                return true
            }
            MotionEvent.ACTION_UP -> {
                resetStick()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun updateStickPosition(x: Float, y: Float) {
        val dx = x - centerX
        val dy = y - centerY
        val distance = kotlin.math.sqrt(dx * dx + dy * dy)

        if (distance <= radius) {
            stickX = x
            stickY = y
        } else {
            val angle = kotlin.math.atan2(dy, dx)
            stickX = centerX + radius * kotlin.math.cos(angle)
            stickY = centerY + radius * kotlin.math.sin(angle)
        }

        normalizedX = (stickX - centerX) / radius
        normalizedY = (stickY - centerY) / radius

        onStickChanged?.invoke(normalizedX, normalizedY)
        invalidate()
    }

    private fun resetStick() {
        stickX = centerX
        stickY = centerY
        normalizedX = 0f
        normalizedY = 0f
        onStickChanged?.invoke(0f, 0f)
        invalidate()
    }

    fun setValues(x: Float, y: Float) {
        normalizedX = x.coerceIn(-1f, 1f)
        normalizedY = y.coerceIn(-1f, 1f)
        stickX = centerX + normalizedX * radius
        stickY = centerY + normalizedY * radius
        invalidate()
    }
}