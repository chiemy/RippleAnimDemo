package com.chiemy.demo.rippleanim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class MainActivity extends AppCompatActivity {
    private HollowCircleView hollowCircleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hollowCircleView = (HollowCircleView) findViewById(R.id.holllowCircleView);
        final AnimatorSet animatorSet = new AnimatorSet();

        final Animator animator = createAnimator(300, 1.5f, 1f);
        final Animator animator0 = createAnimator(100, 1f, 1.5f);
        final Animator animator1 = createAnimator(100, 1.5f, 1f);
        final Animator animator2 = createAnimator(300, 1f, 3f);
        animator1.setInterpolator(new AccelerateInterpolator());
        animatorSet.playSequentially(animator, animator0, animator1, animator2);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorSet.start();
            }
        });
    }

    private Animator createAnimator(long duration, float startValue, float endValue) {
        final ValueAnimator animator = ValueAnimator.ofFloat(startValue, endValue);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                hollowCircleView.setScaleX((float) animation.getAnimatedValue());
                hollowCircleView.setScaleY((float) animation.getAnimatedValue());
            }
        });
        return animator;
    }
}
