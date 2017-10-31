package com.devsil.onboarding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.devsil.onboarding.Onboarding.OnboardingCallback;
import com.devsil.onboarding.Onboarding.OnboardingDialog;

public class MainActivity extends AppCompatActivity implements OnboardingCallback{

    private TextView mTextMessage;
    private BottomNavigationView mBottomNavView;


    private OnboardingDialog mDialog;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onResume(){
        super.onResume();

        // TODO launch OB Dialog to start the demo

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        mBottomNavView = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mDialog = new OnboardingDialog(this);


    }

    @Override
    public void onProgress(int step) {
        // TODO MOVE TO THE NEXT
    }
}
