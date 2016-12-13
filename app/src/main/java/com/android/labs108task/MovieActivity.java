package com.android.labs108task;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapter.CustomAdapter;
import networking.GsonRequestVolley;
import networking.URL;

public class MovieActivity extends AppCompatActivity {
    private RequestQueue queue;
    private ListView listView;
    private CustomAdapter movieAdapter;
    private ArrayList<Movies> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movietable);
        queue = Volley.newRequestQueue(this);
        GsonRequestVolley<MovieDetails> gsonReq = new GsonRequestVolley<MovieDetails>(URL.movieJsonUrl, MovieDetails.class, null, new Response.Listener<MovieDetails>() {
            @Override
            public void onResponse(MovieDetails movieDetails) {
                movies = movieDetails.getMovies();
                movieAdapter = new CustomAdapter(movies, getApplicationContext());
                listView = (ListView)findViewById(R.id.listMovies);
                View header = (View)getLayoutInflater().inflate(R.layout.header_item, null);
                listView.addHeaderView(header);
                listView.setAdapter(movieAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(gsonReq);
    }
 }
