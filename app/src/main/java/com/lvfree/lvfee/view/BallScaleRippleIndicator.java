package com.lvfree.lvfee.view;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

public class BallScaleRippleIndicator extends BallScaleIndicator {


    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        //paint.setStrokeWidth(3);
        super.draw(canvas, paint);
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators=new ArrayList<>();
        ValueAnimator scaleAnim=ValueAnimator.ofFloat(0.5f,1.0f);
        scaleAnim.setInterpolator(new LinearInterpolator());
        scaleAnim.setDuration(650);
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scale = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

//        ValueAnimator alphaAnim=ValueAnimator.ofInt(0, 255);
//        alphaAnim.setInterpolator(new LinearInterpolator());
//        alphaAnim.setDuration(1000);
//        alphaAnim.setRepeatCount(-1);
//        addUpdateListener(alphaAnim,new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                alpha = (int) animation.getAnimatedValue();
//                postInvalidate();
//            }
//        });

        animators.add(scaleAnim);
        //animators.add(alphaAnim);
        return animators;
    }

}
