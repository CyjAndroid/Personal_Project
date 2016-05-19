package com.personal.cyj.viewgroup;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by Cyj on 2016/5/19.
 */
public class DragLayout extends FrameLayout {
    private ViewDragHelper helper;
    private boolean isSliding = true;
    private int dragRange;
    private int height;
    private ViewGroup drag,main;
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return isSliding;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if(child == main && left < 0){
                return 0;
            }else if(left>dragRange){
                return dragRange;
            }
            return left;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return 1;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if(changedView == drag){
                if(left<=0)
                    main.layout(main.getLeft()+dx,0,main.getRight()-dx,height);
                else
                    drag.layout(0,0,main.getRight(),height);
            }else {
                if(left==0)
                    drag.layout(0,0,main.getRight(),height);
            }
            invalidate(); // 解决2.3.3 上无法拖动的bug
        }
    };

    public DragLayout(Context context) {
        this(context,null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        helper = ViewDragHelper.create(this,callback);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        helper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return helper.shouldInterceptTouchEvent(ev);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = getMeasuredWidth();
        height = getMeasuredHeight();
        dragRange = width/3;

        if(getChildCount()!=2){
            throw new RuntimeException("You must have at least 2 child views");
        }
        drag = (ViewGroup) getChildAt(0);
        main = (ViewGroup) getChildAt(1);

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) new FrameLayout.LayoutParams(dragRange,height);
        Log.i("tag",dragRange+"--"+height);
        drag.setLayoutParams(params);
    }

    public void isSliding(boolean isSliding){
        this.isSliding = isSliding;
    }
}
