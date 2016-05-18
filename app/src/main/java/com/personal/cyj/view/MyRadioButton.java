package com.personal.cyj.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.RadioButton;

import com.personal.cyj.cyjapplication.R;

/**
 * Created by Cyj on 2016/5/17.
 */
public class MyRadioButton extends RadioButton {
    private Context context;

    private Drawable mButtonDrawable;
    private int mButtonResource;

    int res,resOn;
    public MyRadioButton(Context context) {
        super(context);
        this.context = context;
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyRadioSrc);
        res = a.getResourceId(R.styleable.MyRadioSrc_img, 0);
        resOn = a.getResourceId(R.styleable.MyRadioSrc_imgOn, 0);
        setButtonDrawable(res);
    }


    @Override
    public void setButtonDrawable(int resid) {
        if (resid != 0 && resid == mButtonResource) {
            return;
        }

        mButtonResource = resid;

        Drawable d = null;
        if (mButtonResource != 0) {
            d = getResources().getDrawable(mButtonResource);
        }
        setButtonDrawable(d);
    }

    @Override
    public void setButtonDrawable(Drawable d) {
        if (d != null) {
            if (mButtonDrawable != null) {
                mButtonDrawable.setCallback(null);
                unscheduleDrawable(mButtonDrawable);
            }
            d.setCallback(this);
            d.setState(getDrawableState());
            d.setVisible(getVisibility() == VISIBLE, false);
            mButtonDrawable = d;
            mButtonDrawable.setState(null);
            setMinHeight(mButtonDrawable.getIntrinsicHeight());
        }

        refreshDrawableState();
    }

    public void isCheck(boolean isCheck){
        if(isCheck){
            setButtonDrawable(resOn);
        }else {
            setButtonDrawable(res);
        }
    }

    // 核心代码部分
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final Drawable buttonDrawable = mButtonDrawable;
        if (buttonDrawable != null) {
            final int verticalGravity = getGravity()
                    & Gravity.VERTICAL_GRAVITY_MASK;
            final int height = buttonDrawable.getIntrinsicHeight()-40;
            final int width = buttonDrawable.getIntrinsicWidth()-40;

            int y = 0;

            switch (verticalGravity) {
                case Gravity.BOTTOM:
                    y = getHeight() - height;
                    break;
                case Gravity.CENTER_VERTICAL:
                    y = (getHeight() - height) / 2;
                    break;
            }

            int x = 0;
            x = (getWidth() - width) / 2;

            buttonDrawable.setBounds(x, y, x + width, y + height);
            buttonDrawable.draw(canvas);
        }
    }
}