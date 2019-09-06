package com.example.tensaiye.popularmovie.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.tensaiye.popularmovie.Activity.DetailActivity;
import com.example.tensaiye.popularmovie.Activity.MainActivity;
import com.example.tensaiye.popularmovie.GlideApp;
import com.example.tensaiye.popularmovie.Models.Movie;
import com.example.tensaiye.popularmovie.R;
import com.squareup.picasso.Picasso;
import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
private List<Movie>movies;
private Context mContext;
private  int layoutID;



    public class MovieViewHolder extends RecyclerView.ViewHolder  {
    GridLayout moviesLayout;

    ImageView imageView;


    public MovieViewHolder(View V){
        super(V);
        moviesLayout=(GridLayout) V.findViewById(R.id.movie_layout);
        imageView=(ImageView)V.findViewById(R.id.list_movie_image);


    }
    }
public MovieAdapter(List<Movie>movies,int layoutID,Context context){
    this.movies=movies;
    this.layoutID =layoutID;
    this.mContext=context;
}
    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view=LayoutInflater.from(viewGroup.getContext()).inflate(layoutID,viewGroup,false);
    return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.MovieViewHolder movieViewHolder, int i) {

        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DetailActivity.class);

                intent.putExtra("id",movies.get(i).getId());
                intent.putExtra("original_name", movies.get(i).getOriginalName());
                intent.putExtra("release_date", movies.get(i).getReleaseDate());
                intent.putExtra("poster_image", movies.get(i).getPosterImage());
                intent.putExtra("overview", movies.get(i).getOverView());
                intent.putExtra("user_rating", movies.get(i).getUserRating());
                intent.putExtra("backdrop_path",movies.get(i).getBackdrop());
                intent.putExtra("vote_count",movies.get(i).getVote());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                movieViewHolder.imageView.setTransitionName("Transition");
                Pair<View, String> pair1 = Pair.create((View) movieViewHolder.imageView, movieViewHolder.imageView.getTransitionName());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, pair1);
                mContext.startActivity(intent, optionsCompat.toBundle());


            }
        });


//movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        Intent intent=new Intent(mContext,DetailActivity.class);
//        movieViewHolder.imageView.setTransitionName("poster_transition");
//        Pair<View, String> pair1 = Pair.create((View) movieViewHolder.imageView, movieViewHolder.imageView.getTransitionName());
//
//        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, pair1);
//        mContext.startActivity(intent, optionsCompat.toBundle());
//    }
//});
        GlideApp.with(mContext).asBitmap().dontTransform().load(movies.get(i).getPosterImage()).diskCacheStrategy(DiskCacheStrategy.ALL).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(movieViewHolder.imageView);

//        Picasso.with(mContext)
//                .load(movies.get(i).getPosterImage())
//                .into(movieViewHolder.imageView);


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}
