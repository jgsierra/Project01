package com.example.gsierra.project01.Fragments;


import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.example.gsierra.project01.R;


public  class ConfiguracionActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addPreferencesFromResource(R.xml.configuraciones);
    }
}
