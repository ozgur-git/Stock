package com.ozgs.borsa.utilities

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.view.NestedScrollingParent
import androidx.recyclerview.widget.RecyclerView

open class CustomRecycleView : RecyclerView, NestedScrollingParent {

    private var nestedScrollTarget: View? = null
    private var nestedScrollTargetIsBeingDragged = true
    private var nestedScrollTargetWasUnableToScroll = false
    private var skipsTouchInterception = false


    constructor(context: Context) :
            super(context)


    constructor(context: Context, attrs: AttributeSet?) :
            super(context, attrs)


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val temporarilySkipsInterception = nestedScrollTarget != null
        if (temporarilySkipsInterception) {
            // If a descendent view is scrolling we set a flag to temporarily skip our onInterceptTouchEvent implementation
            skipsTouchInterception = true
        }

        var handled = super.dispatchTouchEvent(ev)

        if (temporarilySkipsInterception) {
            skipsTouchInterception = false

            if (!handled || nestedScrollTargetWasUnableToScroll) {
                handled = super.dispatchTouchEvent(ev)
            }
        }

        return handled
    }


    override fun onInterceptTouchEvent(e: MotionEvent) =
        !skipsTouchInterception && super.onInterceptTouchEvent(e)


    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        if (target === nestedScrollTarget && !nestedScrollTargetIsBeingDragged) {
            if (dyConsumed != 0) {
                nestedScrollTargetIsBeingDragged = true
                nestedScrollTargetWasUnableToScroll = false
            } else if (dyConsumed == 0 && dyUnconsumed != 0) {
                nestedScrollTargetWasUnableToScroll = true
                target.parent?.requestDisallowInterceptTouchEvent(false)
            }
        }
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int) {
        if (axes and View.SCROLL_AXIS_VERTICAL != 0) {
            nestedScrollTarget = target
            nestedScrollTargetIsBeingDragged = false
            nestedScrollTargetWasUnableToScroll = false
        }

        super.onNestedScrollAccepted(child, target, axes)
    }


    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int) =
        (nestedScrollAxes and View.SCROLL_AXIS_VERTICAL != 0)


    override fun onStopNestedScroll(child: View) {
        nestedScrollTarget = null
        nestedScrollTargetIsBeingDragged = false
        nestedScrollTargetWasUnableToScroll = false
    }

}