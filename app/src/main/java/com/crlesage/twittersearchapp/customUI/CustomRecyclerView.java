package com.crlesage.twittersearchapp.customUI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.crlesage.twittersearchapp.interfaces.Constants;

/**
 * Created by Chris on 7/12/2016.
 * Custom RecyclerView for changing the Fling speed, to slow down scrolling in the twitter feed
 * preview. This helps with image loading as well as performance over-all, just giving a "cleaner"
 * and better feel.
 */
public class CustomRecyclerView extends RecyclerView {

    Context mContext;

    public CustomRecyclerView(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {

        velocityY *= Constants.FLING_SCALE_DOWN;

        return super.fling(velocityX, velocityY);
    }
}