package com.polamrapps.imageloader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
        final ImageObject imageObject = _dataSet.get(position);
        final String imageVal = mContext.getResources().getString(R.string.image_info);
        if(imageObject.isFlip())
            holder.imageInfo.setText(String.format(imageVal, imageObject.getImageId(), imageObject.getFileName()));
        else {
            Utils.show("image url : " + imageObject.getImageUrl());
            Picasso.with(mContext).load(imageObject.getImageUrl()).error(R.drawable.placeholder).placeholder(R.drawable.placeholder).into(holder.imageView);
        }
//        holder.viewAnimator.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("view ", " on click "+position);
//                AnimationFactory.flipTransition(holder.viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);
//            }
//        });
        //holder.viewAnimator.
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("view ", " on click "+position);
//                AnimationFactory.flipTransition(holder.viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);
//            }
//        });
//        holder.imageInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("view ", " on click "+position);
//                AnimationFactory.flipTransition(holder.viewAnimator, AnimationFactory.FlipDirection.RIGHT_LEFT);
//            }
//        });
//        holder.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, ActivityView.class);
//                intent.putExtra("ImageUrl", imageObject.getImageUrl());
//                //intent.putExtra("ImageInfo", String.format(idsfsmageVal, imageObject.getImageId(), imageObject.getFileName()));
//                intent.putExtra("ImageInfo", "sdfsdfdsgdsgdsgdgdsddsdfdfdsfdsfdsfdslkfjds iwjhf pdslmnvjcnvcsv" +
//                        "cfdsmf dshfioudshf sdsdjfdskvc nvjihcvip" +
//                        "" +
//                        "d'fdsvcjvjdiojg iopopdfpoidsoudsj cvcoijvlcdm doj" +
//                        "sdlfjdsoijdsiopfuds pifudsopifpdskf dgiudiewokr;k ejhpislksd;j dghjidiodhfoi djfj sdkjv dvcdvd" +
//                        "flkdsfoipdsahfudsfds; gb;oiopfidoif doiujpodkfk;j doiudoiuoiewj;wdkjofu hdpo ijp;oskdf;o sdhfp diudoif" +
//                        "sdfdo pgjd9p gdgdj;oidfj;iupip jo;oeijoieufpdiidhg ;dfigjpgkdag9fdugfdfduiodfgd");
//                mContext.startActivity(intent);
//            }
//        });
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
