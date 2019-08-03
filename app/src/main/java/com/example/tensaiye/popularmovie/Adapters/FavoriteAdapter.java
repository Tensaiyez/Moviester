package com.example.tensaiye.popularmovie.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tensaiye.popularmovie.Activity.DetailActivity;
import com.example.tensaiye.popularmovie.Database.FavoriteEntry;
import com.example.tensaiye.popularmovie.Models.Movie;
import com.example.tensaiye.popularmovie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>{
    private List<FavoriteEntry> FavoriteList;
    private List<Movie>movies;
    private Context context;
    private AdapterView.OnItemClickListener mItemClickListener;
    public class FavoriteViewHolder extends RecyclerView.ViewHolder{

        public TextView mTitle;
        public ImageView mPoster;
        public TextView mRating;


            public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.Favorite_Title);
            mPoster=itemView.findViewById(R.id.favorite_image);
            mRating=itemView.findViewById(R.id.Favorite_rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("id",movies.get(position).getId());
                    intent.putExtra("original_name", movies.get(position).getOriginalName());
                    intent.putExtra("release_date", movies.get(position).getReleaseDate());
                    intent.putExtra("poster_image", movies.get(position).getPosterImage());
                    intent.putExtra("overview", movies.get(position).getOverView());
                    intent.putExtra("user_rating", movies.get(position).getUserRating());
                    intent.putExtra("backdrop_path",movies.get(position).getBackdrop());
                    intent.putExtra("vote_count",movies.get(position).getVote());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });


        }
    }
    public FavoriteAdapter(List<FavoriteEntry> FavoriteList, Context context,List<Movie>movies) {
        this.context = context;
        this.movies=movies;
        this.FavoriteList = FavoriteList;
    }
        @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context=viewGroup.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.favorite,viewGroup,false);

        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder favoriteViewHolder, int i) {
    FavoriteEntry favoriteEntry=FavoriteList.get(i);
    favoriteViewHolder.mTitle.setText(favoriteEntry.getTitle());
    favoriteViewHolder.mRating.setText(favoriteEntry.getRating());
        Picasso.with(context)
                .load(FavoriteList.get(i).getPoster())
                .into(favoriteViewHolder.mPoster);


    }

    @Override
    public int getItemCount() {
        return FavoriteList.size();
    }

    public void setFavoriteList(List<FavoriteEntry> favoriteList){
        FavoriteList=favoriteList;
        notifyDataSetChanged();
    }
    public List<FavoriteEntry> getFavoriteList(){
        return FavoriteList;
    }
}
