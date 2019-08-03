package com.example.tensaiye.popularmovie.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Credit implements Parcelable {

    public static final String movieImagepath = "https://image.tmdb.org/t/p/";
    public static final String PROFILE_SIZE_W185 = "w185";

    @SerializedName("name")
    private String Name;
    @SerializedName("profile_path")
    private String ProfilePic;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProfilePic() {
        return movieImagepath+PROFILE_SIZE_W185+ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public Credit(Parcel in) {
        this.Name = in.readString();
        this.ProfilePic = in.readString();
    }




    public static final Creator<Credit> CREATOR = new Creator<Credit>() {
        @Override
        public Credit createFromParcel(Parcel in) {
            return new Credit(in);
        }

        @Override
        public Credit[] newArray(int size) {
            return new Credit[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(ProfilePic);
    }
}
