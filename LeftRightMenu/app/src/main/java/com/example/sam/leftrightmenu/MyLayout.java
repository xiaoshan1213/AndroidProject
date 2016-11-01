package com.example.sam.leftrightmenu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by sam on 2016/2/16.
 */
public class MyLayout extends RelativeLayout {

    private Context context;
    private FrameLayout leftMenu;
    private FrameLayout middleMenu;
    private FrameLayout rightMennu;
    private FrameLayout middleMask;
    private Scroller mScroller;
    @android.support.annotation.IdRes static final int LEFT_ID = 0xaabbcc;
    @android.support.annotation.IdRes static final int MIDDLE_ID=0xaaccbb;
    @android.support.annotation.IdRes static final int RIGHT_ID=0xccbbaa;

    public MyLayout(Context context) {
        super(context);
        initView(context);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){
        this.context=context;
        mScroller=new Scroller(context,new DecelerateInterpolator());
        leftMenu=new FrameLayout(context);
        middleMenu=new FrameLayout(context);
        rightMennu=new FrameLayout(context);
        middleMask=new FrameLayout(context);
        leftMenu.setBackgroundColor(Color.RED);
        middleMenu.setBackgroundColor(Color.BLUE);
        rightMennu.setBackgroundColor(Color.RED);
        middleMask.setBackgroundColor(0x88000000);
        leftMenu.setId(LEFT_ID);
        middleMenu.setId(MIDDLE_ID);
        rightMennu.setId(RIGHT_ID);
        this.addView(leftMenu);
        this.addView(middleMenu);
        this.addView(rightMennu);
        this.addView(middleMask);
        middleMask.setAlpha(0);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        int curX=Math.abs(getScrollX());
        float scale=curX/(float)leftMenu.getMeasuredWidth();
        middleMask.setAlpha(scale);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        middleMenu.measure(widthMeasureSpec, heightMeasureSpec);
        middleMask.measure(widthMeasureSpec, heightMeasureSpec);
        int realWidth=MeasureSpec.getSize(widthMeasureSpec);
        int tempWidthMeasureSpec=MeasureSpec.makeMeasureSpec((int)(realWidth*0.8f),MeasureSpec.EXACTLY);
        leftMenu.measure(tempWidthMeasureSpec, heightMeasureSpec);
        rightMennu.measure(tempWidthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        middleMenu.layout(l, t, r, b);
        middleMask.layout(l,t,r,b);
        leftMenu.layout(l-leftMenu.getMeasuredWidth(),t,r,b);
        rightMennu.layout(l+middleMenu.getMeasuredWidth(),t,r+rightMennu.getMeasuredWidth(),b);
    }

    private boolean isTestComplete;
    private boolean isLeftRightEv;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isTestComplete){
            getEventType(ev);
            return true;
        }
        if(isLeftRightEv){
            switch (ev.getActionMasked()){
                case MotionEvent.ACTION_MOVE:
                    int curScrollX=getScrollX(); //
                    int dis_x=(int)(ev.getX()-point.x);
                    int expectX=curScrollX-dis_x;
                    int finalX=0;
                    if(expectX<0){ //left
                        finalX=Math.max(expectX, -leftMenu.getMeasuredWidth()); //负数
                    }else{
                        finalX=Math.min(expectX,rightMennu.getMeasuredWidth()); //正数
                    }
                    scrollTo(finalX,0);
                    point.x= (int) ev.getX();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    curScrollX=getScrollX();
                    if(Math.abs(curScrollX)>leftMenu.getMeasuredWidth()>>1){
                        if(curScrollX<0){
                            mScroller.startScroll(curScrollX,0,-leftMenu.getMeasuredWidth()-curScrollX,0,200);
                        }else {
                            mScroller.startScroll(curScrollX,0,rightMennu.getMeasuredWidth()-curScrollX,0,200);
                        }
                    }else {
                        mScroller.startScroll(curScrollX,0,-curScrollX,0,200);
                    }
                    invalidate();//重绘
                    isLeftRightEv=false;
                    isTestComplete=false;
                    break;
            }
        }else {
            switch (ev.getActionMasked()){
                case MotionEvent.ACTION_DOWN:
                    isLeftRightEv=false;
                    isTestComplete=false;
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(!mScroller.computeScrollOffset()){
            return;
        }
        int tempX=mScroller.getCurrX();
        scrollTo(tempX,0);
    }

    private Point point=new Point();
    private static final int TEST_DIS=20;
    private void getEventType(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                point.x= (int) ev.getX();
                point.y= (int) ev.getY();
                super.dispatchTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx= (int) Math.abs(ev.getX()-point.x);
                int dy=(int)Math.abs(ev.getY()-point.y);
                if(dx>=TEST_DIS && dx>=dy){ //left-right
                    isLeftRightEv=true;
                    isTestComplete=true;
                    point.x= (int) ev.getX();
                    point.y= (int) ev.getY();
                }else if(dy>=TEST_DIS && dy>dx){ //up-down
                    isLeftRightEv=false;
                    isTestComplete=true;
                    point.x= (int) ev.getX();
                    point.y= (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: //screen edge
                super.dispatchTouchEvent(ev);
                isLeftRightEv=false;
                isTestComplete=false;
                break;
        }
    }
}
