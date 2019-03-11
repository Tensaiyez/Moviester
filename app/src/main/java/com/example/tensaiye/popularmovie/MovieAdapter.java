package com.example.tensaiye.popularmovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
private List<Movie>movies;
private Context mContext;
private  int num;
private ItemClickListener itemClickListener;

    public class MovieViewHolder extends RecyclerView.ViewHolder  {
    GridLayout moviesLayout;
    TextView movieTitle;
    ImageView imageView;


    public MovieViewHolder(View V){
        super(V);
        moviesLayout=(GridLayout) V.findViewById(R.id.movie_layout);
        movieTitle=(TextView)V.findViewById(R.id.Original_tv);
        imageView=(ImageView)V.findViewById(R.id.list_movie_image);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null)
                    itemClickListener.onItemClick(v,getAdapterPosition());
            }
        });
    }



    }
public MovieAdapter(List<Movie>movies,int num,Context context){
    this.movies=movies;
    this.num=num;
    this.mContext=context;
}
    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view=LayoutInflater.from(viewGroup.getContext()).inflate(num,viewGroup,false);
    return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder movieViewHolder, int i) {



        Picasso.with(mContext)
                .load(movies.get(i).getPosterImage())
                .into(movieViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
    public void setOnClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    public interface ItemClickListener{
        void onItemClick(View v,int position);
    }
}
