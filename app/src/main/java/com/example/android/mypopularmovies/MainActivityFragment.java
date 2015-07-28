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
import android.app.Activity;
import android.view.View.OnClickListener;

import android.widget.Button;

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
import android.R.integer;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public ArrayAdapter<String> pMoviesAdapter;
    private final static String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private static String[] Movieslist = {};
    private static GridView movieGrid;
    private static String[] movieParams = new String[2];

    public MainActivityFragment() {
    }

    private static ImageView imageView0, imageView1, imageView2, imageView3, imageView4, imageView5;

    //Picasso.with(this).load()
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        /* Add this line in order for this fragment to handle menu events.*/
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        pMoviesAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_movies,
                R.id.grid_item_movies_imageview,
                new ArrayList<String>());

        // new FetchMoviesTask().execute();
        // setContentView(R.layout.list_item_movies);
        movieGrid = (GridView) rootView.findViewById(R.id.gridView_Movies);
        ImageView image1 = (ImageView) rootView.findViewById(R.id.imageView_image1);
        image1.setImageResource(R.drawable.image1);
        ImageView image2 = (ImageView) rootView.findViewById(R.id.imageView_image2);
        image1.setImageResource(R.drawable.image2);
       /* ImageView image3 = (ImageView) rootView.findViewById(R.id.imageView_image3);
        image1.setImageResource(R.drawable.image3);
        ImageView image4 = (ImageView) rootView.findViewById(R.id.imageView_image4);
        image1.setImageResource(R.drawable.image4);
        ImageView image5 = (ImageView) rootView.findViewById(R.id.imageView_image5);
        image1.setImageResource(R.drawable.image5);
        ImageView image6 = (ImageView) rootView.findViewById(R.id.imageView_image6);
        image1.setImageResource(R.drawable.image6);*/

        movieGrid.setAdapter(pMoviesAdapter);
        movieGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                String Movies = pMoviesAdapter.getItem(position);
                Toast.makeText(getActivity(), Movies, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, Movies);
                startActivity(intent);
            }
        });
        return rootView;
    }

    // @Override
    public class FetchMoviesGrid extends MainActivityFragment {
        private Context mContext;

        public FetchMoviesGrid(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.image1, R.drawable.image2,
                //R.drawable.image3, R.drawable.image4,
               // R.drawable.image5, R.drawable.image6,

        };
    }




  /*  private void updateMoviesList() {
        FetchMoviesTask moviesTask = new FetchMoviesTask();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String location = prefs.getString(getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));
       moviesTask.execute(location);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMoviesList();
    }
*/
   /* public class pMoviesAdapter extends ArrayAdapter {
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

}*/
}