package com.example.android.mypopularmovies;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.net.Uri;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.AdapterView;
import android.widget.Toast;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public ArrayAdapter<String> pMoviesAdapter;
    public MainActivityFragment() {
    }
private static ImageView imageView0, imageView1, imageView2, imageView3, imageView4, imageView5;

    //Picasso.with(this).load()

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        pMoviesAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_movies,
                R.id.grid_item_movies_imageview,
                new ArrayList<String>());

        GridView movieGrid = (GridView) rootView.findViewById(R.id.gridView_Movies);
        movieGrid.setAdapter(pMoviesAdapter);
        movieGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String movies = pMoviesAdapter.getItem(position);
                //Context context = MainActivityFragment.mContext;
                // TODO: Set up code for the detail view on the movies List.
            }
        });
        // new FetchMoviesDetails().execute();
        // imageView0 = (ImageView) rootView.findViewById(R.id.imageView_Movies);
        //imageView0.setAdapter(pMoviesAdapter);
        // Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView0);
        //return inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    public class pMoviesAdapter extends ArrayAdapter {
        Context mContext;
        private ArrayList<String> mObjects; //There is < > around MovieDataStructure. The editor hides MovieDataStructure if I include them.
        private int mImageViewResourceId;
        private int mResource;
    public pMoviesAdapter(Context context,int resource, int imageViewResourceId, ArrayList<String> objects){
        super(context,resource,imageViewResourceId,objects);
        this.mContext = context;
        this.mResource = resource;
        this.mImageViewResourceId = imageViewResourceId;
        this.mObjects = objects;
    }
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView;
        //LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            imageView = new ImageView(mContext);

            //imageView = inflater.inflate(mResource, parent, false);

            //ImageView gridImageView = (ImageView) imageView.findViewById(mImageViewResourceId);
            //gridImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext).load("posterUrl").into(imageView);

        return imageView;
    }
}

}
