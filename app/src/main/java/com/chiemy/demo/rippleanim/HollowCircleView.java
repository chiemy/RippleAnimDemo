package com.chiemy.demo.rippleanim;

/**
 * Created: chiemy
 * Date: 16/10/8
 * Description:
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

public class HollowCircleView extends View {
    private float radius;

    public HollowCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public HollowCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HollowCircleView(Context context) {
        super(context);
        init(context, null);
    }

    private Paint bgPaint;
    private Paint proPaint;
    private Paint clearPaint;
    private void init(Context context, AttributeSet attrs) {
        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        proPaint = new Paint();
        proPaint.setStyle(Paint.Style.FILL);
        proPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        clearPaint = new Paint();

        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attr = getTypedArray(context, attributeSet, R.styleable.HollowCircleView);
        if (attr == null) {
            return;
        }
        try {
            int colorProgress = attr.getColor(R.styleable.HollowCircleView_circle_out_color, Color.parseColor("#99363636"));
            bgPaint.setColor(colorProgress);
            proPaint.setColor(colorProgress);
            radius = attr.getDimensionPixelOffset(R.styleable.HollowCircleView_circle_radius, -1);
        } finally {
            attr.recycle();
        }
    }

    protected TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }

    private Bitmap bgBitmap;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int min = Math.min(w, h);
        if (radius <= 0
                || 2 * radius > min) {
            radius = min / 2;
        }

        float centerX = w / 2;
        float centerY = h / 2;

        bgBitmap =  Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvasBg = new Canvas(bgBitmap);
        canvasBg.drawRect(0, 0, w, h, bgPaint);

        Bitmap circleBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvasCircle = new Canvas(circleBitmap);
        canvasCircle.drawCircle(centerX, centerY, radius, proPaint);

        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        Canvas canvas = new Canvas(bgBitmap);
        canvas.drawBitmap(circleBitmap, 0, 0, clearPaint);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bgBitmap, 0, 0, null);
    }

}
