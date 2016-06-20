package com.polamrapps.imageloader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rajareddy on 17/06/16.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private ArrayList<ImageObject> _dataSet;
    private Context mContext;

    public ImagesAdapter(Context context, ArrayList<ImageObject> array) {
        mContext = context;
        _dataSet = array;
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
        ImageObject imageObject = _dataSet.get(position);
        holder.imageInfo.setText("Image ID : "+imageObject.getImageId());
        Utils.show("image url : "+imageObject.getImageUrl());
        Picasso.with(mContext).load(imageObject.getImageUrl()).into(holder.imageView);
//        holder.viewAnimator.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("view ", " on click "+position);
//                AnimationFactory.flipTransition(holder.viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);
//            }
//        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("view ", " on click "+position);
                AnimationFactory.flipTransition(holder.viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);
            }
        });
        holder.imageInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("view ", " on click "+position);
                AnimationFactory.flipTransition(holder.viewAnimator, AnimationFactory.FlipDirection.RIGHT_LEFT);
            }
        });
    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_card, parent, false);
        ImageViewHolder viewHolder = new ImageViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return _dataSet.size();
    }
}
