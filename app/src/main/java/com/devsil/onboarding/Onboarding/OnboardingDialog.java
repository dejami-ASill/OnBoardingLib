package com.devsil.onboarding.Onboarding;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.devsil.onboarding.R;

/**
 * Created by devsil on 10/31/2017.
 */

public class OnboardingDialog extends AbstractOnboardingDialog {

    private OnboardingRelativeLayout rlMain;

    private TextView mHeaderView;
    private TextView mContentView;
    private TextView mSkipText;


    public OnboardingDialog(Context context) {
        super(context);
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_onboarding);

        rlMain = (OnboardingRelativeLayout)findViewById(R.id.modal_main);
        rlMain.setBackgroundEraser(getContext().getColor(R.color.white_95));


        mHeaderView = (TextView)findViewById(R.id.text_1);
        mContentView = (TextView)findViewById(R.id.text_2);
        mSkipText = (TextView)findViewById(R.id.skip_text);

    }


    @Override
    public void onStart(){
        super.onStart();

        if(mTargetPosition != null) {
            rlMain.setTitlebar(mShowingTitleBar);
            rlMain.setEraserShape(mTargetShape);
            rlMain.setRect(mTargetPosition);
            rlMain.setListener(TOUCH_LISTENER);
        }

        if(mHeaderText != null){
            mHeaderView.setText(mHeaderText);
        }

        if(mContentText != null){
            mContentView.setText(mContentText);
        }


        rlMain.useTargetPadding(mUseTargetPadding);

    }

}
