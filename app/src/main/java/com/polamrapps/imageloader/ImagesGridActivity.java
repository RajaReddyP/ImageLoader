package com.polamrapps.imageloader;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ImagesGridActivity extends AppCompatActivity {

    private static String IMAGES_URL = "https://api.flickr.com/services/rest/?&method=flickr.people.getPublicPhotos&api_key=fba686731e0535b04ab1e842e9461988&user_id=144234469@N08&format=json&per_page=50";
    private RecyclerView mRecyclerView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_grid);

        mContext = this;

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        if(mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        if(isNetworkConnected())
            new GetImages().execute();
        else
            Toast.makeText(mContext, "No Internet", Toast.LENGTH_SHORT).show();
    }

    /* this method will check internet connection */
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    // it will return images from the flickr (available to public )
    private class GetImages extends AsyncTask<Void,ArrayList<ImageObject>,ArrayList<ImageObject>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("Loading..");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<ImageObject> list) {
            super.onPostExecute(list);
            if(progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();

            if(list != null && list.size() > 0) {
                ImagesAdapter mAdapter = new ImagesAdapter(mContext, list);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                Utils.show("empty array");
            }
        }

        @Override
        protected ArrayList<ImageObject> doInBackground(Void... voids) {
            try {
                URL url = new URL(IMAGES_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                String data = stringBuilder.toString();
                ArrayList<ImageObject> array = parsingData(data);
                Log.i("data=", "" +stringBuilder.toString());
                return array;
            } catch (Exception e) {
                Utils.show("Exception : "+e);
            }
            return null;
        }
    }

    // parsing JSON data
    private ArrayList<ImageObject> parsingData(String data) {
        ArrayList<ImageObject> arrayList = new ArrayList<>();
        try {
            if(data != null) {
                String _data = data.replace("jsonFlickrApi(","");
                _data = _data.replace(")","");
                JSONObject dataObject = new JSONObject(_data);
                if(dataObject.has("photos")) {
                    JSONObject photosObject = dataObject.getJSONObject("photos");
                    JSONArray photoArray = photosObject.getJSONArray("photo");
                    for (int i = 0; i < photoArray.length(); i++) {
                        JSONObject object = photoArray.getJSONObject(i);
                        String imageUrl = generateImageUrl(object);
                        ImageObject imageObject = new ImageObject(object.getString("id"), object.getString("title"), imageUrl);
                        arrayList.add(imageObject);
                    }
                }
            } else {
                Utils.show(" data null");
            }
        }catch (Exception e) {
            Utils.show("Exception : "+e);
        }
        return arrayList;
    }

    // creating image url from the Json Object.
    private String generateImageUrl(JSONObject object) {
        String finalUrl="";
        try {
            finalUrl = "http://farm" + object.getString("farm") + ".static.flickr.com/" + object.getString("server") + "/" + object.getString("id") + "_" + object.getString("secret") + ".jpg";
        }catch(Exception e ){
            Utils.show("Error : "+e);
        }
        return finalUrl;
    }
}
