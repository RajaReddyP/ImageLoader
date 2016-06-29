package com.polamrapps.imageloader;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

/**
 * Created by Rajareddy on 17/06/16.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static ClickListeners mClick;
    public TextView imageInfo;
    public ImageView imageView;
    public ViewAnimator viewAnimator;
    public Button button;

    public ImageViewHolder(View view) {
        super(view);
        imageInfo = (TextView) view.findViewById(R.id.imageInfo);
        viewAnimator = (ViewAnimator) view.findViewById(R.id.viewFlipper);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        button = (Button) view.findViewById(R.id.btnView);

        button.setOnClickListener(this);
        imageView.setOnClickListener(this);
        imageInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageInfo:
                mClick.onTextInfoClick(viewAnimator, getAdapterPosition(), view);
                break;
            case R.id.imageView:
                mClick.onImageViewClick(viewAnimator,getAdapterPosition(),view);
                break;
            case R.id.btnView:
                mClick.onButtonClick(viewAnimator,getAdapterPosition(),view);
                break;
        }
    }

    public static void setOnClickListeners(ClickListeners listeners) {
        mClick = listeners;
    }

    public interface ClickListeners {
        void onButtonClick(ViewAnimator mViewAnimator, int position, View v);
        void onTextInfoClick( ViewAnimator mViewAnimator, int position, View v);
        void onImageViewClick(ViewAnimator mViewAnimator, int position, View v);
    }
}
