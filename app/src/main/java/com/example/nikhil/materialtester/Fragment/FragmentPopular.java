package com.example.nikhil.materialtester.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nikhil.materialtester.Activity.MovieDetailsActivity;
import com.example.nikhil.materialtester.Activity.SubActivity;
import com.example.nikhil.materialtester.Movie;
import com.example.nikhil.materialtester.PopularAdapter;
import com.example.nikhil.materialtester.R;
import com.example.nikhil.materialtester.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentPopular extends Fragment implements PopularAdapter.ClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String STATE_MOVIE = "state_movies";


    private String mParam1;
    private String mParam2;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    private RecyclerView listMovieHits;
    private PopularAdapter adapter;
    private ArrayList<Movie> ListMovies =new ArrayList<Movie>();
    public FragmentPopular() {
        // Required empty public constructor
    }

    public static FragmentPopular newInstance(String param1, String param2) {
        FragmentPopular fragment = new FragmentPopular();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        volleySingleton = volleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        listMovieHits = (RecyclerView) view.findViewById(R.id.recycler_popular);
        listMovieHits.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new PopularAdapter(getActivity());
        adapter.setClickListener(this);
        listMovieHits.setAdapter(adapter);
        if(savedInstanceState != null){
            ListMovies=savedInstanceState.getParcelableArrayList(STATE_MOVIE);
            adapter.setMoviesList(ListMovies);
        }
        else {
            sendJsonRequest();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_MOVIE,ListMovies);

    }

    private void sendJsonRequest(){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://api.themoviedb.org/3/movie/popular?api_key=609bbb466b647591bcd182c19afd5a2d",
                null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ListMovies =parseJSONResponse(response);
                    adapter.setMoviesList(ListMovies);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);

    }

    public ArrayList<Movie> parseJSONResponse(JSONObject response) throws JSONException {
        ArrayList<Movie> data = new ArrayList<Movie>();
        if (response == null || response.length() == 0) {
            return data;
        }

        JSONArray results = response.getJSONArray("results");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < results.length(); i++) {
            Movie current = new Movie();


            JSONObject jsonObject = results.getJSONObject(i);

            current.setPosterPath(jsonObject.getString("poster_path"));
            current.setOverview(jsonObject.optString("overview"));
            current.setOriginalTitle(jsonObject.optString("original_title"));
            current.setReleaseDate(jsonObject.optString("release_date"));
            current.setVoteAverage(Float.parseFloat(jsonObject.optString("vote_average")));
            current.setBackdrop(jsonObject.optString("backdrop_path"));

            data.add(current);
        }

        return data;

    }


    @Override
    public void itemClicked(View view, int position) {

        Intent intent =new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putParcelableArrayListExtra("Movie",ListMovies);
        startActivity(intent);

    }
}


