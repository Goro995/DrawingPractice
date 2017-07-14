package com.trial.toucheventshandler


import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.MotionEvent
import android.view.View

class DrawActivity : Activity() {

    internal var dv: DrawingView? = null
    private var mPaint: Paint? = null

    val TOUCH_TOLERANCE = 4f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dv = DrawingView(this)
        setContentView(dv)
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.isDither = true
        mPaint!!.color = Color.RED
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeJoin = Paint.Join.ROUND
        mPaint!!.strokeCap = Paint.Cap.ROUND
        mPaint!!.strokeWidth = 12f
    }

    inner class DrawingView(internal var context: Context) : View(context) {
        
        private var mBitmap: Bitmap? = null
        private var mCanvas: Canvas? = null
        private val mPath: Path
        private val mBitmapPaint: Paint
        private val circlePaint: Paint
        private val circlePath: Path

        init {
            mPath = Path()
            mBitmapPaint = Paint(Paint.DITHER_FLAG)
            circlePaint = Paint()
            circlePath = Path()
            circlePaint.isAntiAlias = false
            circlePaint.color = Color.BLUE
            circlePaint.style = Paint.Style.STROKE
            circlePaint.strokeJoin = Paint.Join.MITER
            circlePaint.strokeWidth = 4f
        }

        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)

            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            mCanvas = Canvas(mBitmap!!)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            canvas.drawBitmap(mBitmap!!, 0f, 0f, mBitmapPaint)
            canvas.drawPath(mPath, mPaint!!)
            canvas.drawPath( circlePath,  circlePaint)
        }

        private var mX: Float = 0.toFloat()
        private var mY: Float = 0.toFloat()

        private fun touch_start(x: Float, y: Float) {
            mPath.reset()
            mPath.moveTo(x, y)
            mX = x
            mY = y
            mPath.quadTo(mX, mY, (x), (y-1))
        }

        private fun touch_move(x: Float, y: Float) {
            val dx = Math.abs(x - mX)
            val dy = Math.abs(y - mY)
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
                mX = x
                mY = y

                circlePath.reset()
                circlePath.addCircle(mX, mY, 30f, Path.Direction.CW)
            }
        }

        private fun touch_up() {
            mPath.lineTo(mX, mY)
            circlePath.reset()
            mCanvas!!.drawPath(mPath, mPaint!!)
            mPath.reset()
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            val x = event.x
            val y = event.y

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    touch_start(x, y)
                    invalidate()
                }
                MotionEvent.ACTION_MOVE -> {
                    touch_move(x, y)
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    touch_up()
                    invalidate()
                }
            }
            return true
        }
    }
}

