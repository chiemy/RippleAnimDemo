package com.chiemy.demo.rippleanim;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created: chiemy
 * Date: 16/10/8
 * Description:
 */

public class CircularImageView extends ShaderImageView {
    private CircleShader shader;

    public CircularImageView(Context context) {
        super(context);
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ShaderHelper createImageViewHelper() {
        shader = new CircleShader();
        return shader;
    }

    public void setBitmapRadius(int bitmapRadius) {
        shader.setBitmapRadius(bitmapRadius);
        invalidate();
    }
}
