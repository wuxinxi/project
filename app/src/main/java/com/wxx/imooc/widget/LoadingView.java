package com.wxx.imooc.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wxx.imooc.R;
import com.wxx.imooc.util.Util;

import java.util.ArrayList;


/**
 * 作者: Tangren on 2017-11-03
 * 包名：com.wxx.view.view
 * 邮箱：996489865@qq.com
 * TODO:等待动画
 */

public class LoadingView extends View {

    private Paint circlePaint;
    private Context mContext;
    private float SCALE = 1.0F;
    private boolean isAnimatorStart = true;
    private int width = 200;
    private int height = 50;

    private float[] scaleFloats = new float[]{
            SCALE, SCALE, SCALE
    };

    private ArrayList<ValueAnimator> animators;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(getResources().getColor(R.color.color_fed952));
        circlePaint.setAntiAlias(true);
        startAnimator();
    }

    private void startAnimator() {
        isAnimatorStart = true;
        animators = onCreateAnimators();
        for (int i = 0; i < animators.size(); i++) {
            ValueAnimator animator = animators.get(i);
            animator.start();
        }
    }

    private ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        int[] delays = new int[]{120, 240, 360};
        for (int i = 0; i < 3; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.3f, 1);
            scaleAnim.setDuration(750);
            scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            animators.add(scaleAnim);
        }
        return animators;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float circleSpacing = Util.dp2px(mContext, 4);
        float radius = (Math.min(getWidth(), getWidth()) - circleSpacing * 8) / 6;
        float x = getWidth() / 2 - (radius * 2 + circleSpacing);
        float y = getHeight() / 2;
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float translateX = x + (radius * 2) * i + circleSpacing;
            canvas.translate(translateX, y);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            canvas.drawCircle(0, 0, radius, circlePaint);
            canvas.restore();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        switch (specMode) {
            //确切大小
            case MeasureSpec.EXACTLY:
                width = getPaddingLeft() + getPaddingRight() + specSize;
                break;
            //指定大小
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
            default:
                break;
        }
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode) {
            //确切大小
            case MeasureSpec.EXACTLY:
                height = getPaddingBottom() + getPaddingTop() + specSize;
                break;
            //指定大小
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
            default:
                break;
        }

        setMeasuredDimension(width, height);
    }

    public void show() {
        this.setVisibility(View.VISIBLE);
        if (!isAnimatorStart) {
            startAnimator();
        }

    }

    public void hide() {
        this.setVisibility(View.GONE);
        if (animators != null) {
            isAnimatorStart = false;
            for (ValueAnimator animator : animators) {
                animator.removeAllUpdateListeners();
                animator.end();
            }
        }
    }
}
