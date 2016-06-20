package com.polamrapps.imageloader;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

/**
 * Created by Rajareddy on 17/06/16.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {

    public TextView imageInfo;
    public ImageView imageView;
    public ViewAnimator viewAnimator;

    public ImageViewHolder(View view) {
        super(view);
        imageInfo = (TextView) view.findViewById(R.id.imageInfo);
        viewAnimator = (ViewAnimator) view.findViewById(R.id.viewFlipper);
        imageView = (ImageView) view.findViewById(R.id.imageView);
    }
}
