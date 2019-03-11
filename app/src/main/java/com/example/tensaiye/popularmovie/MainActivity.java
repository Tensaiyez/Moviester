package com.example.tensaiye.popularmovie;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tensaiye.popularmovie.API.RetrofitService;
import com.example.tensaiye.popularmovie.API.ServiceInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static final String Tag = MainActivity.class.getSimpleName();
    private MovieAdapter mAdapter;

    private List<Movie> movies;
    private int SpinnerPos = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.spinner_m);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SortArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        if (savedInstanceState == null || !savedInstanceState.containsKey(Constants.MOVIEBUNDLE)) {
            if (isOnline()) {
                Fetch(Constants.Popular);
                spinner.setOnItemSelectedListener(this);
            } else if (!isOnline()) {
                Toast.makeText(this, "No Internet Connection...Please Connect To The Internet", Toast.LENGTH_SHORT).show();
            }


        } else {
            movies = savedInstanceState.getParcelableArrayList(Constants.MOVIEBUNDLE);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_xml, menu);
        return true;
    }


    public void Fetch(String sort) {
        ServiceInterface serviceInterface = RetrofitService.getRetrofit().create(ServiceInterface.class);
        Call<Basicmovie> call = serviceInterface.getMovies(sort,Constants.API_KEY);
        call.enqueue(new Callback<Basicmovie>() {
            @Override
            public void onResponse(Call<Basicmovie> call, Response<Basicmovie> response) {
                final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.MovieList);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                movies = response.body().getResults();
                mAdapter = new MovieAdapter(movies, R.layout.moviecard, getApplicationContext());
                recyclerView.setAdapter(mAdapter);
                mAdapter.setOnClickListener(new MovieAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("original_name", movies.get(position).getOriginalName());
                        intent.putExtra("release_date", movies.get(position).getReleaseDate());
                        intent.putExtra("poster_image", movies.get(position).getPosterImage());
                        intent.putExtra("overview", movies.get(position).getOverView());
                        intent.putExtra("user_rating", movies.get(position).getUserRating());
                        intent.putExtra("backdrop_path",movies.get(position).getBackdrop());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<Basicmovie> call, Throwable t) {
                Log.e(Tag, t.toString());
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String word = parent.getItemAtPosition(position).toString();
        if(word.equals(Constants.Popular_xml_Val)){
            Fetch(Constants.Popular);
        }
        else if(word.equals(Constants.TopRated_xml_Val)){
            Fetch(Constants.TopRated);
        }

        if (SpinnerPos == position) {
            Fetch(Constants.Popular);
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // The method below checks if the phone is connected to a network.
    //https://developer.android.com/training/monitoring-device-state/connectivity-monitoring#java This site has helped me find a way in which to check the Network Status of the app.
    public boolean isOnline() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkStatus= connectivityManager.getActiveNetworkInfo();
        return networkStatus != null;


    }
}





