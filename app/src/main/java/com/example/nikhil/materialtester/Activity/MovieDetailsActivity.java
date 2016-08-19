package com.example.nikhil.materialtester.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikhil.materialtester.Movie;
import com.example.nikhil.materialtester.R;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        final Movie movie = intent.getParcelableExtra("Movie");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(movie.getOriginalTitle());
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


//
        ImageView poster = (ImageView) findViewById(R.id.poster1);
//        TextView releaseDate = (TextView) findViewById(R.id.Release_write);
//        TextView rating = (TextView) findViewById(R.id.Rating_write);
//        TextView title = (TextView) findViewById(R.id.Title_write);
        TextView overview = (TextView) findViewById(R.id.overview_new);
        ImageView backdrop = (ImageView) findViewById(R.id.backdrop1);
//
        Picasso.with(this)
                .load(movie.getPosterPath())
                .into(poster);

        Picasso.with(this)
                .load(movie.getBackdrop())
                .into(backdrop);

//        releaseDate.setText(movie.getReleaseDate());
//
//        rating.setText(movie.getVoteAverage());
//
//        title.setText(movie.getOriginalTitle());
//
        overview.setText(movie.getOverview());




    }

}
