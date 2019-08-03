package com.example.tensaiye.popularmovie.Activity;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tensaiye.popularmovie.API.RetrofitService;
import com.example.tensaiye.popularmovie.API.ServiceInterface;
import com.example.tensaiye.popularmovie.Adapters.FavoriteAdapter;
import com.example.tensaiye.popularmovie.Adapters.MovieAdapter;
import com.example.tensaiye.popularmovie.Models.Basicmovie;
import com.example.tensaiye.popularmovie.Constants;
import com.example.tensaiye.popularmovie.Database.FavoriteEntry;
import com.example.tensaiye.popularmovie.Database.MovieDatabase;
import com.example.tensaiye.popularmovie.ViewModel.MainViewModel;
import com.example.tensaiye.popularmovie.Models.Movie;
import com.example.tensaiye.popularmovie.R;
import com.example.tensaiye.popularmovie.Models.Review;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{


    private static final String TAG = MainActivity.class.getSimpleName();
    private MovieAdapter mAdapter;
    private FavoriteAdapter favoriteAdapter;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    TextView popular_tv;
    TextView Toolbar_tv;
    CardView cardView;
    CardView cardView2;
    TextView Highest_tv;
    TextView Upcoming_tv;
    Toolbar myToolbar;
    BottomNavigationView bottomNavigationView;

    NestedScrollView nestedScrollView;
    private List<Movie> movies;
    private List<Review> reviewList;
    private MovieDatabase mDb;
    private int SpamCount = 2;
    int gridOrientation = GridLayoutManager.HORIZONTAL;
    private String RETREIVE = "retreive";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.MovieList);
        recyclerView2 = (RecyclerView) findViewById(R.id.MovieList2);
        recyclerView3 = (RecyclerView) findViewById(R.id.MovieList3);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nested);
        Toolbar_tv = MainActivity.this.findViewById(R.id.toolbar_title);
        Toolbar_tv.setText(Constants.Home);
        popular_tv = MainActivity.this.findViewById(R.id.PopularTitle);
        cardView = findViewById(R.id.title_main_cv);
        cardView2 = findViewById(R.id.title_main2_cv);
        Highest_tv = MainActivity.this.findViewById(R.id.HighestTitle);
        Upcoming_tv = MainActivity.this.findViewById(R.id.UpcomingTitle);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (savedInstanceState == null || !savedInstanceState.containsKey(Constants.MOVIEBUNDLE)) {
            if (isOnline()) {
                FetchFromTMDBOne(Constants.Popular);
                FetchFromTMDBTwo(Constants.TopRated);
                FetchFromTMDBThree(Constants.Upcoming);


            } else if (!isOnline()) {
                Toast.makeText(this, "No Internet Connection...Please Connect To The Internet", Toast.LENGTH_SHORT).show();
            }


        } else {
            movies = savedInstanceState.getParcelableArrayList(Constants.MOVIEBUNDLE);
        }
        mDb = MovieDatabase.getInstance(getApplicationContext());
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home_bottom:
                        DisplayHome();
                        return true;
                    case R.id.favorite_bottom:
                        DisplayFavorites();
                        return true;
                }
                return true;
            }
        });
        defaults();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_xml, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Popular_mv:
                nestedScrollView.fullScroll(View.FOCUS_UP);
                FetchFromTMDBOne(Constants.Popular);
                popular_tv.setVisibility(View.GONE);
                Toolbar_tv.setText(Constants.Popular_xml_Val);
                recyclerView2.setVisibility(View.GONE);
                recyclerView3.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                gridOrientation = GridLayoutManager.VERTICAL;
                SpamCount = 3;
                this.setTheme(R.style.OverlayPrimaryColorRed);
                return true;
            case R.id.TopRated_mv:
                nestedScrollView.fullScroll(View.FOCUS_UP);
                FetchFromTMDBOne(Constants.TopRated);
                popular_tv.setVisibility(View.GONE);
                Toolbar_tv.setText(Constants.TopRated_xml_Val);
                recyclerView2.setVisibility(View.GONE);
                recyclerView3.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                gridOrientation = GridLayoutManager.VERTICAL;
                SpamCount = 3;
                return true;
            case R.id.Upcoming_mv:
                nestedScrollView.fullScroll(View.FOCUS_UP);
                FetchFromTMDBOne(Constants.Upcoming);
                popular_tv.setVisibility(View.GONE);
                Toolbar_tv.setText(Constants.Upcoming_xml_Val);
                recyclerView2.setVisibility(View.GONE);
                recyclerView3.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                gridOrientation = GridLayoutManager.VERTICAL;
                SpamCount = 3;
                return true;
            case R.id.ThemeSetting:
                Intent startSettingActivity= new Intent(this,SettingActivity.class);
                startActivity(startSettingActivity);


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void FetchFromTMDBOne(String sort) {

        RetrofitService retrofitService = new RetrofitService();
        ServiceInterface serviceInterface = retrofitService.getRetrofit().create(ServiceInterface.class);
        Call<Basicmovie> call = serviceInterface.getMovies(sort, Constants.API_KEY);
        call.enqueue(new Callback<Basicmovie>() {
            @Override
            public void onResponse(Call<Basicmovie> call, Response<Basicmovie> response) {
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, SpamCount, gridOrientation, false));
                movies = response.body().getResults();
                mAdapter = new MovieAdapter(movies, R.layout.moviecard, getApplicationContext());
                recyclerView.setAdapter(mAdapter);

            }


            @Override
            public void onFailure(Call<Basicmovie> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });


    }
public void defaults(){
    SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

    myToolbar.setBackgroundColor( sharedPreferences.getInt("red_theme", ContextCompat.getColor(this,R.color.red)));
    sharedPreferences.registerOnSharedPreferenceChangeListener(this);
//    if (Build.VERSION.SDK_INT >= 21) {
//        getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.grey));
//        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.red_200));
//    }
//    bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this,R.color.red));
//    bottomNavigationView.setItemBackgroundResource(R.color.red);
//    myToolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.red));
//    Toolbar_tv.setTextColor(ContextCompat.getColor(this,R.color.white));

  }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }
    public void FetchFromTMDBTwo(String sort) {
        RetrofitService retrofitService = new RetrofitService();
        ServiceInterface serviceInterface = retrofitService.getRetrofit().create(ServiceInterface.class);
        Call<Basicmovie> call = serviceInterface.getMovies(sort, Constants.API_KEY);
        call.enqueue(new Callback<Basicmovie>() {
            @Override
            public void onResponse(Call<Basicmovie> call, Response<Basicmovie> response) {
                recyclerView2.setLayoutManager(new GridLayoutManager(MainActivity.this, SpamCount, GridLayoutManager.HORIZONTAL, false));

                movies = response.body().getResults();
                mAdapter = new MovieAdapter(movies, R.layout.moviecard, getApplicationContext());
                recyclerView2.setAdapter(mAdapter);

            }


            @Override
            public void onFailure(Call<Basicmovie> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });


    }

    public void FetchFromTMDBThree(String sort) {

        RetrofitService retrofitService = new RetrofitService();
        ServiceInterface serviceInterface = retrofitService.getRetrofit().create(ServiceInterface.class);
        Call<Basicmovie> call = serviceInterface.getMovies(sort, Constants.API_KEY);
        call.enqueue(new Callback<Basicmovie>() {
            @Override
            public void onResponse(Call<Basicmovie> call, Response<Basicmovie> response) {
                recyclerView3.setLayoutManager(new GridLayoutManager(MainActivity.this, SpamCount, GridLayoutManager.HORIZONTAL, false));
                movies = response.body().getResults();
                mAdapter = new MovieAdapter(movies, R.layout.moviecard, getApplicationContext());
                recyclerView3.setAdapter(mAdapter);

            }


            @Override
            public void onFailure(Call<Basicmovie> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });


    }

    public void DisplayHome() {

        FetchFromTMDBOne(Constants.Popular);
        nestedScrollView.fullScroll(View.FOCUS_UP);
        recyclerView.scrollToPosition(View.FOCUS_UP);
        recyclerView2.scrollToPosition(View.FOCUS_UP);
        recyclerView3.scrollToPosition(View.FOCUS_UP);
        popular_tv.setVisibility(View.VISIBLE);
        popular_tv.setText(Constants.Popular_xml_Val);
        Toolbar_tv.setText(Constants.Home);
        Highest_tv.setVisibility(View.VISIBLE);
        Upcoming_tv.setVisibility(View.VISIBLE);
        recyclerView2.setVisibility(View.VISIBLE);
        recyclerView3.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
        cardView2.setVisibility(View.VISIBLE);
        gridOrientation = GridLayoutManager.HORIZONTAL;
        SpamCount = 2;

    }

    public void DisplayFavorites() {
        FetchFromDatabase();
        popular_tv.setVisibility(View.GONE);
        Toolbar_tv.setText(Constants.Favorite_xml_Val);
        Highest_tv.setVisibility(View.INVISIBLE);
        Upcoming_tv.setVisibility(View.INVISIBLE);
        recyclerView2.setVisibility(View.GONE);
        recyclerView3.setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);
        cardView2.setVisibility(View.GONE);
        nestedScrollView.fullScroll(View.FOCUS_UP);
    }

    public void FetchFromDatabase() {
        List<FavoriteEntry> favEntry = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(favEntry, getApplicationContext(), movies);
        setViewModel();

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(favoriteAdapter);
    }

    private void setViewModel() {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getFavorites().observe(this, new Observer<List<FavoriteEntry>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteEntry> favoriteEntries) {
                Log.d(TAG, "Updataing list of favorites from ViewModel ");
                favoriteAdapter.setFavoriteList(favoriteEntries);
            }
        });


    }

    // The method below checks if the phone is connected to a network.
    //https://developer.android.com/training/monitoring-device-state/connectivity-monitoring#java This site has helped me find a way in which to check the Network Status of the app.
    public boolean isOnline() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkStatus = connectivityManager.getActiveNetworkInfo();
        return networkStatus != null;

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.redTheme))) {
            myToolbar.setBackgroundColor(sharedPreferences.getInt("red_theme", ContextCompat.getColor(this, R.color.red)));
        }
    }
}





