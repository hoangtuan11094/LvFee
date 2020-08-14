package com.lvfree.lvfee.view;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

public class BallScaleIndicator extends Indicator {

    float scale=1;
    int alpha=255;
    private static final int DEFAULT_COLOR = Color.parseColor("#FF4282");
    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing=4;
        Path path = new Path();
        paint.setShader(null);

        float width = getWidth()-5;
        float height = getHeight()-5;

        // Starting point
        path.moveTo(width / 2, height / 5);

        // Upper left path
        path.cubicTo(5 * width / 14, 0,
                0, height / 15,
                width / 28, 2 * height / 5);

        // Lower left path
        path.cubicTo(width / 14, 2 * height / 3,
                3 * width / 7, 5 * height / 6,
                width / 2, height);

        // Lower right path
        path.cubicTo(4 * width / 7, 5 * height / 6,
                13 * width / 14, 2 * height / 3,
                27 * width / 28, 2 * height / 5);

        // Upper right path
        path.cubicTo(width, height / 15,
                9 * width / 14, 0,
                width / 2, height / 5);

        paint.setColor(DEFAULT_COLOR);
        //mBorderPaint.setStyle(Paint.Style.FILL);
        paint.setAlpha(alpha);
        canvas.scale(scale,scale,getWidth()/2,getHeight()/2);
        canvas.drawPath(path, paint);
        //paint.setAlpha(alpha);
        //canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-circleSpacing,paint);
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators=new ArrayList<>();
        ValueAnimator scaleAnim=ValueAnimator.ofFloat(0,1);
        scaleAnim.setInterpolator(new LinearInterpolator());
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scale = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

//        ValueAnimator alphaAnim=ValueAnimator.ofInt(255, 0);
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
