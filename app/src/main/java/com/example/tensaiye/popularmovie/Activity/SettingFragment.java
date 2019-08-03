package com.example.tensaiye.popularmovie.Activity;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.tensaiye.popularmovie.R;

public class SettingFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
addPreferencesFromResource(R.xml.pref_visualizer);
    }
}
