package com.example.tensaiye.popularmovie.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tensaiye.popularmovie.R;
import com.example.tensaiye.popularmovie.Models.Trailer;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    private List<Trailer> trailerList;
    private Context context;
    private String poster;


    public class TrailerViewHolder extends RecyclerView.ViewHolder{
        public TextView mTrailerTitle;
        public ImageView mTrailerImage;


        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTrailerTitle=itemView.findViewById(R.id.TrailerTitle_tv);
            mTrailerImage=itemView.findViewById(R.id.TrailerImage);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        String TrailerKey=trailerList.get(position).getKey();
                        Log.d("URL",TrailerKey);
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(TrailerKey));
                        intent.putExtra("Key",TrailerKey);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
    public TrailerAdapter(List<Trailer>trailerList,Context context,String poster){
        this.context=context;
        this.trailerList=trailerList;
        this.poster=poster;
    }
    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer,viewGroup,false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder trailerViewHolder, int i) {
       Trailer trailer=trailerList.get(i);
        Picasso.with(context).load(poster).into(trailerViewHolder.mTrailerImage);
        trailerViewHolder.mTrailerTitle.setText(trailer.getName());
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

}
