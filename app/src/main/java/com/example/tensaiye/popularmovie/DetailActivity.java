package com.example.tensaiye.popularmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {



    private Movie movies;


    TextView mTitle;

    TextView mUserRating;

    TextView mReleaseDate;

    TextView mDescription;
    String title;
    String releaseDate;
    String userRating;
    String overView;
    String poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        if (savedInstanceState == null || !savedInstanceState.containsKey(Constants.BUNDLE_KEY)) {
            Intent intent = getIntent();
            if (intent.hasExtra("original_name")) {
                title = intent.getStringExtra("original_name");
                releaseDate = intent.getStringExtra("release_date");
                userRating = intent.getStringExtra("user_rating");
                overView = intent.getStringExtra("overview");
                poster = intent.getStringExtra("backdrop_path");
                populateUI(movies);

            }
        }

    }


    private void populateUI(Movie movie) {

        ImageView Imageshown = findViewById(R.id.Poster_tv);

        mTitle = (TextView) findViewById(R.id.Original_tv);
        mDescription = (TextView) findViewById(R.id.Overview_tv);
        mUserRating = (TextView) findViewById(R.id.Vote_tv);
        mReleaseDate = (TextView) findViewById(R.id.Release_tv);


        mTitle.setText(title);
        mDescription.setText(overView);
        mUserRating.setText(userRating);
        mReleaseDate.setText(releaseDate);
        Picasso.with(this).load(poster).into(Imageshown);
    }

}
