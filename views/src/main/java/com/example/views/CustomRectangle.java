package com.example.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by micha on 2/19/2018.
 */

public class CustomRectangle extends View {

    public static final String TAG = MainActivity.class.getSimpleName();

    private int basicWidth,basicHeight,viewWidth,viewHeight,color;


    public CustomRectangle(Context context) {
        super(context);
        init(context,null,0,0);
    }

    public CustomRectangle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public CustomRectangle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomRectangle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs,defStyleAttr,defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRectangleView, defStyleAttr, defStyleRes);
        basicWidth = typedArray.getInt(R.styleable.CustomRectangleView_rectWidth,10);
        basicHeight = typedArray.getInt(R.styleable.CustomRectangleView_rectHeight, 10);
        color = typedArray.getColor(R.styleable.CustomRectangleView_rectColor, Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        Rect rect = new Rect(0, 0, basicWidth, basicHeight);
        canvas.drawRect(rect,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //        setup the maximum size of the view
        int desiredWidth = 400;
        int desiredHeight = 400;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            viewWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            viewWidth = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            viewWidth = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            viewHeight = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            viewHeight = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            viewHeight = desiredHeight;
        }

        setMeasuredDimension(viewWidth, viewHeight);
    }
}
