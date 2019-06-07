package com.test.view;

import android.content.Context;
import android.gesture.GestureOverlayView;
import android.gesture.GesturePoint;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.test.model.GPoint2D;
import com.test.model.Hanzi;

import java.util.ArrayList;
import java.util.List;

public class HanziView extends GestureOverlayView implements GestureOverlayView.OnGestureListener {

    private Hanzi hanzi;
    private int wrongTimes;

    public HanziView(Context context) {
        super(context);
        init();
    }

    public HanziView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public HanziView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        addOnGestureListener(this);
        wrongTimes = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (hanzi != null) {
            hanzi.draw(canvas);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    // 使用这个方法来更新汉字
    public void setCharacter(String word) {
        int mWidth = this.getMeasuredWidth();
        if (hanzi == null) {
            hanzi = new Hanzi(getContext());
        }
        hanzi.setCharacter(this, word, mWidth);
    }

    // 这个方法提供给手势事件调用
    // 当用户写错了笔画的时候，给出一些提示（prompt）
    public void prompt() {
        hanzi.prompt(this);
    }

    // 这个方法提供给手势事件调用
    // 当用户写对了笔画的时候，将这个字涂黑
    public void advance() {
        hanzi.finishOneStroke(this);
    }

    public void animateStrokes() {
        hanzi.animateStroke(this);
    }

    public void resetHanzi() {
        hanzi.reset(this);
    }

    public boolean hanziFinish() {
        return hanzi.isFinish();
    }

    @Override
    public void onGesture(GestureOverlayView overlay, MotionEvent event) {

    }

    @Override
    public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {

    }

    @Override
    public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
        List<GPoint2D> userStroke = new ArrayList<>();
        for (GesturePoint point : overlay.getCurrentStroke()) {
            GPoint2D newPoint = new GPoint2D(point.x, point.y);
            userStroke.add(newPoint);
        }
        if (hanzi.strokeMatch(userStroke)) {
            hanzi.finishOneStroke(this);
            wrongTimes = 0;
        }
        else {
            wrongTimes = wrongTimes + 1;
            if (wrongTimes > 2) {
                hanzi.prompt(this);
                wrongTimes = wrongTimes - 1;
            }
        }
    }

    @Override
    public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {

    }
}