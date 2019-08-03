package com.example.tensaiye.popularmovie.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Trailer implements Parcelable {
    public static final String trailerVideoPath="https://www.youtube.com/watch?v=";
    @SerializedName("key")
    private String Key;
    @SerializedName("name")
    private String Name;
    @SerializedName("site")
    private String Site;
    @SerializedName("size")
    private String Size;
    @SerializedName("type")
    private String Type;
    @SerializedName("id")
    private String Id;

    public Trailer(Parcel in){
        this.Key=in.readString();
        this.Name=in.readString();
        this.Site=in.readString();
        this.Size=in.readString();
        this.Type=in.readString();
        this.Id=in.readString();


    }
    public String getKey() {
        return trailerVideoPath+Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        this.Size = size;
    }

    public String getType() {
        return Type;
    }

    public void Type(String Type) {
        this.Type = Type;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(Key);
        parcel.writeString(Name);
        parcel.writeString(Size);
        parcel.writeString(Site);
        parcel.writeString(Type);
        parcel.writeString(Id);
    }
    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {

        @Override
        public Trailer createFromParcel(Parcel source) {
            return new Trailer(source);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };
}
