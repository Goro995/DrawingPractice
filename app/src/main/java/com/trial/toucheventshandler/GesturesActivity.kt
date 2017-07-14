package com.trial.toucheventshandler


import android.app.Activity
import android.gesture.Gesture
import android.gesture.GestureOverlayView
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent


class GesturesActivity : Activity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, GestureOverlayView.OnGesturePerformedListener {

    private var gesture: GestureOverlayView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        gesture = findViewById(R.id.gestures) as GestureOverlayView

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event)
    }

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        return false
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {

        return false
    }

    override fun onDoubleTapEvent(e: MotionEvent): Boolean {

        return false
    }

    override fun onDown(e: MotionEvent): Boolean {

        return false
    }

    override fun onShowPress(e: MotionEvent) {

    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {

        return false
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {

        return false
    }

    override fun onLongPress(e: MotionEvent) {

    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

        return false
    }

    override fun onGesturePerformed(overlay: GestureOverlayView, gesture: Gesture) {

    }
}
