package com.devsil.onboarding.Onboarding;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.devsil.onboarding.R;
import com.devsil.onboarding.Screen.ScreenDimensions;

/**
 * Created by devsil on 10/31/2017.
 */


/**
 * This is an extended Alert Dialog meant to be extended by your specific Dialog.
 *
 * This class keeps track of colors/target views/ Rects and which or not you want to pass the buttons click along back to your activity/fragment.
 *
 * Please see OnboardingRelativeLayout.java for more information on how this is done.
 */
public class AbstractOnboardingDialog extends AlertDialog {
    protected static final String TAG  = "Debug.OnBoardDialog";


    protected ScreenDimensions dim;

    String mHeaderText;
    int mHeaderColor = -1;
    int mHeaderSize = -1;
    String mContentText;
    int mContentColor = -1;
    int mContentTextSize = -1;


    boolean mUseTargetPadding = false;
    private View mTarget;
    private boolean mUseTargetClick = true;
    protected int mTargetDefaultColor = -1;
    Rect mTargetPosition;

    private View mTarget2;
    private boolean mUseTarget2Click = true;
    protected int mTarget2DefaultColor = -1;
    Rect mTarget2Position;

    private View mTarget3;
    protected int mTarget3DefaultColor = -1;
    Rect mTarget3Position;

    private View mTarget4;
    protected int mTarget4DefaultColor = -1;
    Rect mTarget4Position;

    private View mSubTarget;
    private Rect mSubTargetPosition;

    private View mSubTarget2;
    private Rect mSubTargetPosition2;
    private View mSubTarget3;
    private Rect mSubTargetPosition3;
    boolean mShowingTitleBar = true;
    int mTargetShape = OnboardingRelativeLayout.RECT;
    private int mGlowColor = -1;
    private int mTargetBackgroundColor = -1;
    private int mTargetForgroundColor = -1;

    boolean mDismissOnTouch = false;

    int mCurrentStep;

    private OnDismissedCallback mCallback;

    public OnboardingCallback mListener ;

    int mAnimationResId = 0;
    String mAnimationAssetPath = null;


    boolean mShowButton = false;
    boolean mShowButton2 = false;
    View.OnClickListener mBtnOneListener = null;
    View.OnClickListener mBtnTwoListener = null;

    Handler mHandler = null;
    Runnable mDismissRunnable = new Runnable() {
        @Override
        public void run() {
            cancel();
        }
    };
    long mDismissTime = 0;

    protected boolean mShowSkip = false;
    protected View.OnClickListener mSkipListener = null;

    protected int mGravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

    public AbstractOnboardingDialog(Context context) {
        super(context);
        dim = ScreenDimensions.getBaseDimensions(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setBackgroundDrawable(null);

        getWindow().getAttributes().gravity = mGravity;
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.getWindow().setWindowAnimations(R.style.dialog_fade_animation);
        setCanceledOnTouchOutside(false);

    }

    public void showSkipButton(boolean showSkip){
        mShowSkip = showSkip;
    }

    public void showSkipButton(boolean showSkip, View.OnClickListener skipListener){
        mShowSkip = showSkip;
        mSkipListener = skipListener;
    }

    public void setWindowGravity(int gravity){
        this.mGravity = gravity;
    }

//    /**
//     *
//     * @param listener - OnboardingSteps.OnBoardingListener acts as a dialog dismiss listener.
//     *                 Does not have to be set. If it isnt set the calling activity will not receive dialog dismiss
//     */
    public void setOnBoardingListener(OnboardingCallback listener){
        this.mListener = listener;
    }

    /**
     *
     * @param showTitleBar - determine if the screen has the status bar enabled or if its full screen:
     *                     @value true if activity is not "full screen"
     *                     @value false if activity uses full screen mode
     */
    public void setTitleBar(boolean showTitleBar){
        mShowingTitleBar = showTitleBar;
    }

    public void setTargetShape(int shape){
        mTargetShape = shape;
    }

    public void setDismissCallBack(OnDismissedCallback callBack){
        mCallback = callBack;
    }


    public void setTargetBackgroundColor(int color){
        mTargetBackgroundColor = color;
    }

    public void setTargetForgroundColor(int color){
        mTargetForgroundColor = color;
    }

    public void setHeaderText(String stringone){
        this.mHeaderText = stringone;
    }

    public void setHeaderText(String stringone, int color, int size){
        this.mHeaderText = stringone;
        this.mHeaderColor = color;
        this.mHeaderSize = size;
    }

    /**
     *
     * @param stringTwo
     *  If this isn't set the TextView containing it is set to GONE
     */
    public void setContentText(String stringTwo){
        this.mContentText = stringTwo;
    }

    public void setContentText(String stringTwo, int color){
        this.mContentText = stringTwo;
        this.mContentColor = color;
    }

    public void setContentText(String stringTwo , int color, int size){
        this.mContentText = stringTwo;
        this.mContentColor = color;
        this.mContentTextSize = size;
    }

    /**
     *
     * @param view - Target view for a hole to be cut in the background view of this dialog
     *             Uses the Views Global Visible Rect
     */
    public void setTarget(View view){

        mTarget = view;
        mTargetPosition = new Rect();

        mTarget.getGlobalVisibleRect(mTargetPosition);
        Log.d(TAG,"Target Rect: " +mTargetPosition.toString());

        if((mTarget instanceof TextView) && mGlowColor != -1 ){
            ((TextView) mTarget).setShadowLayer(10,0,0,mGlowColor);
        }

        if((mTarget instanceof TextView) && mTargetBackgroundColor != -1 ){
            ((TextView) mTarget).setBackgroundColor(mTargetBackgroundColor);
        }

        if(mTarget instanceof TextView && mTargetForgroundColor != -1) {
            ((TextView) mTarget).setTextColor(mTargetForgroundColor);
        }
    }
    /**
     *
     * @param view - Target view for a hole to be cut in the background view of this dialog
     *             Uses the Views Global Visible Rect
     * @param passClick - Determines whether the click is allowed through the Onboarding Dialog
     */
    public void setTarget(View view, boolean passClick){

        mTarget = view;
        mUseTargetClick = passClick;
        mTargetPosition = new Rect();

        mTarget.getGlobalVisibleRect(mTargetPosition);

        if((mTarget instanceof TextView) && mGlowColor != -1 ){
            ((TextView) mTarget).setShadowLayer(10,0,0,mGlowColor);
        }

        if((mTarget instanceof TextView) && mTargetBackgroundColor != -1 ){
            ((TextView) mTarget).setBackgroundColor(mTargetBackgroundColor);
        }

        if(mTarget instanceof TextView && mTargetForgroundColor != -1) {
            ((TextView) mTarget).setTextColor(mTargetForgroundColor);
        }
    }

    /**
     *
     * @param view - Target view for a hole to be cut in the background view of this dialog
     *             Uses the Views Global Visible Rect
     */
    public void setTarget2(View view){
        mTarget2 = view;
        mTarget2Position = new Rect();


        mTarget2.getGlobalVisibleRect(mTarget2Position);

        if((mTarget2 instanceof TextView) && mGlowColor != -1 ){
            ((TextView) mTarget2).setShadowLayer(10,0,0,mGlowColor);
        }

        if((mTarget2 instanceof TextView) && mTargetBackgroundColor != -1 ){
            ((TextView) mTarget2).setBackgroundColor(mTargetBackgroundColor);
        }

        if((mTarget2 instanceof TextView) && mTargetForgroundColor != -1){
            ((TextView) mTarget2).setTextColor(mTargetForgroundColor);
        }
    }

    /**
     *
     * @param view - Target view for a hole to be cut in the background view of this dialog
     *             Uses the Views Global Visible Rect
     * @param passClick - Determines whether the click is allowed through the Onboarding Dialog
     */
    public void setTarget2(View view, boolean passClick){
        mTarget2 = view;
        mUseTarget2Click = passClick;
        mTarget2Position = new Rect();


        mTarget2.getGlobalVisibleRect(mTarget2Position);

        if((mTarget2 instanceof TextView) && mGlowColor != -1 ){
            ((TextView) mTarget2).setShadowLayer(10,0,0,mGlowColor);
        }

        if((mTarget2 instanceof TextView) && mTargetBackgroundColor != -1 ){
            ((TextView) mTarget2).setBackgroundColor(mTargetBackgroundColor);
        }

        if((mTarget2 instanceof TextView) && mTargetForgroundColor != -1){
            ((TextView) mTarget2).setTextColor(mTargetForgroundColor);
        }
    }

    /**
     *
     * @param use - uses a constant padding to add to the hole cut-out : Constant is 20 pixels
     */
    public void useTargetPadding(boolean use){
        this.mUseTargetPadding = use;
    }


    /**
     *
     * @param view - Target view for a hole to be cut in the background view of this dialog
     *             Uses the Views Global Visible Rect
     */
    public void setTarget3(View view){
        mTarget3 = view;
        mTarget3Position = new Rect();


        mTarget3.getGlobalVisibleRect(mTarget3Position);

        if((mTarget3 instanceof TextView) && mGlowColor != -1 ){
            ((TextView) mTarget3).setShadowLayer(10,0,0,mGlowColor);
        }

        if((mTarget3 instanceof TextView) && mTargetBackgroundColor != -1 ){
            ((TextView) mTarget3).setBackgroundColor(mTargetBackgroundColor);
        }

        if((mTarget3 instanceof TextView) && mTargetForgroundColor != -1 ){
            ((TextView) mTarget3).setTextColor(mTargetForgroundColor);
        }

    }


    /**
     *
     * @param view - Target view for a hole to be cut in the background view of this dialog
     *             Uses the Views Global Visible Rect
     */
    public void setTarget4(View view){
        mTarget4 = view;
        mTarget4Position = new Rect();

        mTarget4.getGlobalVisibleRect(mTarget4Position);

        if((mTarget4 instanceof TextView) && mGlowColor != -1 ){
            ((TextView) mTarget4).setShadowLayer(10,0,0,mGlowColor);
        }

        if((mTarget4 instanceof TextView) && mTargetBackgroundColor != -1 ){
            ((TextView) mTarget4).setBackgroundColor(mTargetBackgroundColor);
        }

        if((mTarget4 instanceof TextView) && mTargetForgroundColor != -1 ){
            ((TextView) mTarget4).setTextColor(mTargetForgroundColor);
        }
    }

    /**
     *
     * @param view - Allows for touch detection of a view inside a larger target view.
     */
    public void addSubTarget(View view){
        mSubTarget = view;
        mSubTargetPosition = new Rect();


        mSubTarget.getGlobalVisibleRect(mSubTargetPosition);
    }
    /**
     *
     * @param view - Allows for touch detection of a view inside a larger target view.
     */
    public void addSubTarget2(View view){
        mSubTarget2 = view;
        mSubTargetPosition2 = new Rect();


        mSubTarget2.getGlobalVisibleRect(mSubTargetPosition2);
    }
    /**
     *
     * @param view - Allows for touch detection of a view inside a larger target view.
     */
    public void addSubTarget3(View view){
        mSubTarget3 = view;
        mSubTargetPosition3 = new Rect();


        mSubTarget3.getGlobalVisibleRect(mSubTargetPosition3);
    }

    public void setGlowColor(int color){
        mGlowColor = color;
    }

    public void setOnboardingStep(int step){
        this.mCurrentStep = step;
    }

    public int getOnboardingStep(int step){
        return mCurrentStep;
    }


    public void setCancelable(boolean cancelable){
        setCanceledOnTouchOutside(cancelable);
    }


    public View.OnTouchListener TOUCH_LISTENER = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_UP){
                if(mDismissOnTouch){
                    cancel();
                }

                if(mSubTarget != null && inRectBounds(mSubTargetPosition, (int)event.getRawX(), (int) event.getRawY())){
                    cancel();
                    mSubTarget.performClick();
                }

                if(mSubTarget2 != null && inRectBounds(mSubTargetPosition2, (int)event.getRawX(), (int) event.getRawY())){
                    cancel();
                    mSubTarget2.performClick();
                }

                if(mSubTarget3 != null && inRectBounds(mSubTargetPosition3, (int)event.getRawX(), (int) event.getRawY())){
                    cancel();
                    mSubTarget3.performClick();
                }


                if( mUseTargetClick && inRectBounds(mTargetPosition, (int)event.getRawX(), (int)event.getRawY())){
                    if(mTarget != null){
                        cancel();
                        mTarget.performClick();
                    }
                }

                if(mUseTarget2Click && inRectBounds(mTarget2Position, (int) event.getRawX(), (int)event.getRawY())){
                    if(mTarget2 != null){
                        cancel();
                        mTarget2.performClick();
                    }
                }

                if(inRectBounds(mTarget3Position, (int)event.getRawX(), (int)event.getRawY())){
                    if(mTarget3 != null){
                        cancel();
                        mTarget3.performClick();
                    }
                }

                if(inRectBounds(mTarget4Position, (int)event.getRawX(), (int)event.getRawY())){
                    if(mTarget4 != null){
                        cancel();
                        mTarget4.performClick();
                    }
                }
            }

            return true;
        }
    };


    private boolean inRectBounds(Rect rect, int x, int y){
        if(rect == null){
            return false;
        }
        return rect.contains(x, y);
    }

    public void setAnimationResId(int id){
        mAnimationResId = id;
    }

    public void setAnimationAssetPath(String path){
        this.mAnimationAssetPath = path;
    }


    public void dismiss(){
        super.dismiss();
    }

    public void setDismissOnTouch(boolean dismiss){
        this.mDismissOnTouch = dismiss;
    }



    public void displayOkButton(boolean show){
        mShowButton = show;
    }

    public void showYesAndNoOption(boolean show){
        mShowButton = show;
        mShowButton2 = show;
    }


    public void setBtnOneListener(View.OnClickListener listener){
        this.mBtnOneListener = listener;
    }

    public void setBtnTwoListener(View.OnClickListener listener){
        this.mBtnTwoListener = listener;
    }




    @Override
    public void cancel(){
        if(!isShowing()){
            return;
        }
        try{
            super.dismiss();
            if(mListener != null && mCurrentStep > -1){
//                OnboardingSteps.stepCompleted(getContext(), mCurrentStep, Common.getUserId(), Common.getWeddingId());
                mListener.onProgress(mCurrentStep);
            }
            else if(mCurrentStep != -1){
//                OnboardingSteps.stepCompleted(getContext(), mCurrentStep, Common.getUserId(), Common.getWeddingId());
            }


            if(mCallback != null){
                mCallback.onDismissed();
            }

            if(mHandler != null){
                mHandler.removeCallbacks(mDismissRunnable);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed(){
        /// Trapped you now fucker, there is no escape.
    }


    public interface OnDismissedCallback{
        void onDismissed();
    }

    public void setDismissTimer(long time){
        mHandler = new Handler();
        mDismissTime = time;
    }
}
