package com.chiemy.demo.rippleanim;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created: chiemy
 * Date: 16/10/8
 * Description:
 */

public class CircleImageView extends ImageView {

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);

    }

    public class CircleShader extends ShaderHelper{
        private float center;
        private float bitmapCenterX;
        private float bitmapCenterY;
        private float borderRadius;
        private int bitmapRadius;

        public CircleShader() {
        }

        @Override
        public void init(Context context, AttributeSet attrs, int defStyle) {
            super.init(context, attrs, defStyle);
            if(attrs != null){
                TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularImageView, defStyle, 0);
                bitmapRadius = typedArray.getDimensionPixelOffset(R.styleable.CircularImageView_radius, 0);
                typedArray.recycle();
            }
            square = true;
        }

        @Override
        public void draw(Canvas canvas, Paint imagePaint, Paint borderPaint) {
            canvas.drawCircle(center, center, borderRadius, borderPaint);
            canvas.save();
            canvas.concat(matrix);
            canvas.drawCircle(bitmapCenterX, bitmapCenterY, bitmapRadius, imagePaint);
            canvas.restore();
        }

        @Override
        public void onSizeChanged(int width, int height) {
            super.onSizeChanged(width, height);
            center = Math.round(viewWidth / 2f);
            //borderRadius = Math.round((viewWidth - borderWidth) / 2f);
        }

        @Override
        public void calculate(int bitmapWidth, int bitmapHeight,
                              float width, float height,
                              float scale,
                              float translateX, float translateY) {
            float min = Math.min(width, height);
            bitmapCenterX = Math.round(bitmapWidth / 2f);
            bitmapCenterY = Math.round(bitmapHeight / 2f);
            if (bitmapRadius <= 0) {
                bitmapRadius = Math.round(min / scale / 2f + 0.5f);
            } else {
                bitmapRadius = Math.round(bitmapRadius / scale / 2f + 0.5f);
                bitmapRadius = Math.min((int) min, bitmapRadius);
            }
        }

        public void setBitmapRadius(int bitmapRadius) {
            this.bitmapRadius = bitmapRadius;
        }

        @Override
        public void reset() {
            bitmapRadius = 0;
            bitmapCenterX = 0;
            bitmapCenterY = 0;
        }

    }
}
