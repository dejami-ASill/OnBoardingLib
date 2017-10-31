package com.devsil.onboarding.Onboarding;

import android.view.animation.Interpolator;

/**
 * Created by devsil on 10/31/2017.
 */

public class BounceInterpolator implements Interpolator{

    private double mAmplitude = 1;
    private double mFrequency = 3;

    public BounceInterpolator(){}

    public BounceInterpolator(double amplitude, double frequency){
        mAmplitude = amplitude;
        mFrequency = frequency;
    }


    @Override
    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}