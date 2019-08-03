package com.example.tensaiye.popularmovie.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tensaiye.popularmovie.Models.Credit;
import com.example.tensaiye.popularmovie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private List<Credit> castList;
    private Context context;


    public class CastViewHolder extends RecyclerView.ViewHolder{
    public TextView CastName;
    public CircleImageView CastProfilePic;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            CastName=itemView.findViewById(R.id.CastName);
            CastProfilePic=itemView.findViewById(R.id.CastProfile);
        }
    }
    public CastAdapter(List<Credit> castList, Context context) {
        this.context = context;

        this.castList = castList;

    }
    @NonNull
    @Override
    public CastAdapter.CastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cast, viewGroup, false);


        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastAdapter.CastViewHolder castViewHolder, int i) {

        Credit credit=castList.get(i);
        castViewHolder.CastName.setText(credit.getName());
        Picasso.with(context).load(credit.getProfilePic()).noFade().into(castViewHolder.CastProfilePic);

    }

    @Override
    public int getItemCount() {
        return castList.size();
    }
}
