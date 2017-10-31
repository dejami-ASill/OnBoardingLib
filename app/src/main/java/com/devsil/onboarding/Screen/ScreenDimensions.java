package com.devsil.onboarding.Screen;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by devsil on 10/31/2017.
 */

public class ScreenDimensions {

    public static final float BASE_WIDTH_DP = 360.0f;

    public static ScreenDimensions getBaseDimensions(Context context){

        return new ScreenDimensions(context);
    }


    public final int width;
    public final int height;
    public final float oneDP;
    public final int statusbarHeight;
    public final Orientation orientation;

    public int size(final float originalDp){
        int toReturn = (int) (originalDp * oneDP);
        if(toReturn < 1 && originalDp > 0){
            toReturn = 1;
        }
        return toReturn;
    }

    public float sizePrecise(final float originalDp){
        return originalDp * oneDP;
    }

    public float sizeByHeightPrecise(final float originalDp){
        return originalDp * height / BASE_WIDTH_DP;
    }

    public int sizeByHeight(final float originalDp){
        int toReturn = (int) (originalDp * height / BASE_WIDTH_DP);
        if(toReturn < 1 && originalDp > 0){
            toReturn = 1;
        }
        return toReturn;
    }


    public void setTextSize(final TextView view, final float size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, size(size));
    }

    protected ScreenDimensions(Context context){
        final WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        oneDP = width / BASE_WIDTH_DP;

        final int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusbarHeight = context.getResources().getDimensionPixelSize(resourceId);
        } else {
            statusbarHeight = 0;
        }

        if(width > height){
            orientation = Orientation.LANDSCAPE;
        } else if(height > width){
            orientation = Orientation.PORTRAIT;
        } else {
            orientation = Orientation.SQUARE;
        }

    }

    public static enum Orientation{
        PORTRAIT, LANDSCAPE, SQUARE;
    }

}

