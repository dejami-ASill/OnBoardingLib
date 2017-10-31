package com.devsil.onboarding.Onboarding;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.devsil.onboarding.Screen.ScreenDimensions;

/**
 * Created by devsil on 10/31/2017.
 */


/**
 * This is a custom RelativeLayout View that allows for a Rect() to be passed to it.
 * This Rect is meant to be the position of a view you are trying to direct the user towards.
 *
 * This View is typically used to cover the full screen as to block the user from doing anything until they take the desired action.
 * The desired action/button/view Rect can be passed to this class which will "cut" a whole in the background using Paint and PorterDuff Modes
 * so that the desired button can be seen and interacted with.
 */

public class OnboardingRelativeLayout extends RelativeLayout {

    public static final int RECT = 1;
    public static final int CIRCLE = 2;

    private Rect mRect;

    private Rect mRect2;

    private Rect mRect3;

    private Rect mRect4;

    private Paint eraser;
    private int statusBarHeight;

    private int currentEraserShape = 1;
    private boolean mTitleBar = true;

    private boolean mUseTargetPadding = false;

    public OnboardingRelativeLayout(Context context) {
        super(context);
        init();
    }

    public OnboardingRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OnboardingRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void setBackgroundEraser(int color){
        eraser = new Paint();
        eraser.setColor(color);
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    public void setEraserShape(int shape){
        currentEraserShape = shape;
    }

    public void setTitlebar(boolean showTitleBar){
        mTitleBar = showTitleBar;
    }

    private void init(){
        statusBarHeight = ScreenDimensions.getBaseDimensions(getContext()).statusbarHeight;

    }

    public void useTargetPadding(boolean useTargetPadding){
        this.mUseTargetPadding = useTargetPadding;
    }

    public void setListener(View.OnTouchListener listener){
        setOnTouchListener(listener);
    }


    public void setRect(Rect rect){
        if(mTitleBar) {
            mRect = new Rect();
            mRect.left = (rect.left) - (mUseTargetPadding ? 20 : 0);
            mRect.top = rect.top - statusBarHeight + 1;
            mRect.bottom = rect.bottom - statusBarHeight;
            mRect.right = rect.right + (mUseTargetPadding ? 20 : 0);
        }
        else{
            mRect = new Rect();
            mRect.left = rect.left- (mUseTargetPadding ? 20 : 0);
            mRect.top = rect.top + 1;
            mRect.bottom = rect.bottom;
            mRect.right = rect.right + (mUseTargetPadding ? 20 : 0);
        }
    }

    public void setRect2(Rect rect){
        if(mTitleBar) {
            mRect2 = new Rect();
            mRect2.left = rect.left -(mUseTargetPadding ? 20 : 0);
            mRect2.top = rect.top - statusBarHeight +1;
            mRect2.bottom = rect.bottom - statusBarHeight;
            mRect2.right = rect.right + (mUseTargetPadding ? 20 : 0);
        }
        else{
            mRect2 = new Rect();
            mRect2.left = rect.left - (mUseTargetPadding ? 20 : 0);
            mRect2.top = rect.top +1;
            mRect2.bottom = rect.bottom;
            mRect2.right = rect.right + (mUseTargetPadding ? 20 : 0);
        }
    }

    public void setRect3(Rect rect){
        if(mTitleBar) {
            mRect3 = new Rect();
            mRect3.left = rect.left - (mUseTargetPadding ? 20 : 0);
            mRect3.top = rect.top - statusBarHeight +1;
            mRect3.bottom = rect.bottom - statusBarHeight;
            mRect3.right = rect.right + (mUseTargetPadding ? 20 : 0);
        }
        else{
            mRect3 = new Rect();
            mRect3.left = rect.left - (mUseTargetPadding ? 20 : 0);
            mRect3.top = rect.top +1;
            mRect3.bottom = rect.bottom;
            mRect3.right = rect.right + (mUseTargetPadding ? 20 : 0);
        }
    }

    public void setRect4(Rect rect){
        if(mTitleBar) {
            mRect4 = new Rect();
            mRect4.left = rect.left - (mUseTargetPadding ? 20 : 0);
            mRect4.top = rect.top - statusBarHeight +1;
            mRect4.bottom = rect.bottom - statusBarHeight;
            mRect4.right = rect.right + (mUseTargetPadding ? 20 : 0);
        }
        else{
            mRect4 = new Rect();
            mRect4.left = rect.left - (mUseTargetPadding ? 20 : 0);
            mRect4.top = rect.top +1;
            mRect4.bottom = rect.bottom ;
            mRect4.right = rect.right + (mUseTargetPadding ? 20 : 0);
        }

    }


    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if(mRect != null) {
            if(currentEraserShape == RECT) {
                canvas.drawRect(mRect, eraser);
            }
            else if( currentEraserShape == CIRCLE){
                float radius = (mRect.width() > mRect.height()) ? mRect.width()/2f : mRect.height()/2f;
                canvas.drawCircle(mRect.exactCenterX(), mRect.exactCenterY(), radius , eraser);
            }
        }


        if(mRect2 != null) {
            if(currentEraserShape == RECT) {
                canvas.drawRect(mRect2, eraser);
            }
            else if( currentEraserShape == CIRCLE){
                float radius = (mRect2.width() > mRect2.height()) ? mRect2.width()/2f : mRect2.height()/2f;
                canvas.drawCircle(mRect2.exactCenterX(), mRect2.exactCenterY(), radius , eraser);
            }
        }

        if(mRect3 != null) {
            if(currentEraserShape == RECT) {
                canvas.drawRect(mRect3, eraser);
            }
            else if( currentEraserShape == CIRCLE){
                float radius = (mRect3.width() > mRect3.height()) ? mRect3.width()/2f : mRect3.height()/2f;
                canvas.drawCircle(mRect3.exactCenterX(), mRect3.exactCenterY(), radius , eraser);
            }
        }

        if(mRect4 != null) {
            if(currentEraserShape == RECT) {
                canvas.drawRect(mRect4, eraser);
            }
            else if( currentEraserShape == CIRCLE){
                float radius = (mRect4.width() > mRect4.height()) ? mRect4.width()/2f : mRect4.height()/2f;
                canvas.drawCircle(mRect4.exactCenterX(), mRect4.exactCenterY(), radius, eraser);
            }
        }

    }

    private boolean inRectBounds(Rect rect, int x, int y){
        return rect.contains(x, y);
    }

}
