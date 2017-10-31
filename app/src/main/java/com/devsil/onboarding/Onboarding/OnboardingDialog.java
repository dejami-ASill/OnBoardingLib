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


        mHeaderView = (TextView)findViewById(R.id.text_1);
        mContentView = (TextView)findViewById(R.id.text_2);
        mSkipText = (TextView)findViewById(R.id.skip_text);

    }
}
