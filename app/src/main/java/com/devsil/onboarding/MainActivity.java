package com.devsil.onboarding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.devsil.onboarding.Onboarding.BounceInterpolator;
import com.devsil.onboarding.Onboarding.OnboardingCallback;
import com.devsil.onboarding.Onboarding.OnboardingDialog;


/**
 *
 *
 * Since the main point of this project is to demonstrate the Onboarding Dialog (See AbstractOnBoardingDialog)
 * This is a very simply activity that doesn't do much besides demonstrate how the OnBoardingDialog can be used to direct your user
 * and teach them the different functionality of the application in a few and pretty way.
 */

public class MainActivity extends AppCompatActivity{

    private static final String TAG = ".Debug.MainActivity";

    private Button mBeginBtn;


    private ImageView mStepOneImage;
    private ImageView mStepTwoImage;
    private ImageView mStepThreeImage;


    private OnboardingDialog mDialog;


    private int mStepSeen = 0;

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBeginBtn = (Button) findViewById(R.id.begin_btn);
        mBeginBtn.setOnClickListener(BEGIN_CLICK);


        mStepOneImage = (ImageView)findViewById(R.id.btn_center);
        mStepOneImage.setOnClickListener(STEP_ONE_CLICK);
        mStepOneImage.setColorFilter(getColor(R.color.colorAccent));

        mStepTwoImage = (ImageView)findViewById(R.id.btn_left);
        mStepTwoImage.setOnClickListener(STEP_TWO_CLICK);
        mStepTwoImage.setColorFilter(getColor(R.color.colorAccent));


        mStepThreeImage = (ImageView)findViewById(R.id.btn_right);
        mStepThreeImage.setOnClickListener(STEP_THREE_CLICK);
        mStepThreeImage.setColorFilter(getColor(R.color.colorAccent));


    }


    private void showStepOne(){
        mDialog = new OnboardingDialog(this);
        mDialog.setTitleBar(true);
        mDialog.setHeaderText(getString(R.string.onboarding_step_one_header));
        mDialog.setContentText(getString(R.string.onboarding_step_one_content));

        Animation animation = buildBounceAnimation(true);
        animation.setStartTime(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(!mDialog.isShowing()) {
                    mDialog.setTarget(mStepOneImage);
                    mDialog.show();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        mStepOneImage.startAnimation(animation);
    }

    private void showStepTwo(){
        mDialog = new OnboardingDialog(this);
        mDialog.setTitleBar(true);
        mDialog.setHeaderText(getString(R.string.onboarding_step_two_header));


        Animation animation = buildBounceAnimation(true);
        animation.setStartTime(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(!mDialog.isShowing()) {
                    mDialog.setTarget(mStepTwoImage);
                    mDialog.show();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        mStepTwoImage.startAnimation(animation);
    }

    private void showStepThree(){
        mDialog = new OnboardingDialog(this);
        mDialog.setTitleBar(true);
        mDialog.setHeaderText(getString(R.string.onboarding_step_three_header));

        Animation animation = buildBounceAnimation(true);
        animation.setStartTime(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(!mDialog.isShowing()) {
                    mDialog.setTarget(mStepThreeImage);
                    mDialog.show();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        mStepThreeImage.startAnimation(animation);
    }

    /**
     *
     * This is just a simple method for building our predefined animation. Feel free to use whatever you want.
     *
     * @param doRepeat - whether or not the animation should be repeated indefinitely
     * @return - The built animation to attach to your target view
     */
    private Animation buildBounceAnimation(boolean doRepeat){
        Animation animation;
        if(doRepeat) {
            animation = AnimationUtils.loadAnimation(this, R.anim.bounce_repeating);
        }
        else{
            animation = AnimationUtils.loadAnimation(this, R.anim.bounce_single);
        }
        BounceInterpolator interpolator = new BounceInterpolator();
        animation.setInterpolator(interpolator);
        return animation;
    }

    private View.OnClickListener BEGIN_CLICK = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mStepSeen == 0) {
                showStepOne();
            }
            else if(mStepSeen == 1){
                showStepTwo();
            }
            else if(mStepSeen == 2){
                showStepThree();
            }
        }
    };


    private View.OnClickListener STEP_ONE_CLICK = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mStepOneImage.clearAnimation();
            mStepSeen = 1;
            mBeginBtn.setText(getString(R.string.next_text));
        }
    };

    private View.OnClickListener STEP_TWO_CLICK = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mStepTwoImage.clearAnimation();
            mStepSeen = 2;
            mBeginBtn.setText(getString(R.string.one_more));
        }
    };

    private View.OnClickListener STEP_THREE_CLICK = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mStepThreeImage.clearAnimation();
            mStepSeen = 0;
            mBeginBtn.setText(getString(R.string.title_lets_begin));
        }
    };

}
