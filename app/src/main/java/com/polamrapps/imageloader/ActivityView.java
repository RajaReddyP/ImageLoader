package com.polamrapps.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        TextView imageInfoTV = (TextView) findViewById(R.id.imageViewInfo);

        if(getIntent() != null) {
            String imageUrl  = getIntent().getStringExtra("ImageUrl");
            String imageInfo = getIntent().getStringExtra("ImageInfo");

            Picasso.with(this).load(imageUrl).into(image);
            imageInfoTV.setText(imageInfo);
        }


    }
}
