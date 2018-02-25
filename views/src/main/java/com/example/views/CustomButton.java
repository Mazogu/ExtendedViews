package com.example.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by micha on 2/22/2018.
 */

public class CustomButton extends Button {

    private int width,height,color;


    public CustomButton(Context context) {
        super(context);
        init(context,null,0,0);
    }

    public CustomButton(Context context, AttributeSet attr){
        super(context, attr);
        init(context,attr,0,0);
    }

    public CustomButton(Context context, AttributeSet attributeSet,int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
        init(context,attributeSet,0,0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomButton(Context context, AttributeSet attr, int defSyleAttr, int defStyleRes){
        super(context,attr,defSyleAttr,defStyleRes);
        init(context,attr,defSyleAttr,defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        Rect rect = new Rect(0,0,width,height);
        canvas.drawRect(rect,paint);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyleAttr, int defStyleRes){
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.CustomButton, defStyleAttr, defStyleRes);
        width = typedArray.getIndex(R.styleable.CustomButton_inner_width);
        height = typedArray.getIndex(R.styleable.CustomButton_inner_height);
        color = typedArray.getColor(R.styleable.CustomButton_button_color, Color.RED);
    }


}
