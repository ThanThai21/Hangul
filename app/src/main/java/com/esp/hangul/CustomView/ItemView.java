package com.esp.hangul.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.esp.hangul.R;

public class ItemView extends ImageView {

    private int backgroundColor;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemView, 0, 0);
        backgroundColor = typedArray.getColor(R.styleable.ItemView_circleColor, Color.BLUE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(backgroundColor);
        canvas.drawCircle(getHeight()/2, getWidth()/2, getWidth()/2, paint);
        super.onDraw(canvas);

    }
}
