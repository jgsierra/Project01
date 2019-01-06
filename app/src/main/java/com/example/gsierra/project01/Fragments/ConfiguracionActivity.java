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



//    public static class MainConfiguracionActivity extends PreferenceFragment
//   {
//       @Override
//       public void onCreate(Bundle savedInstanceState) {
//           super.onCreate(savedInstanceState);
//           addPreferencesFromResource(R.xml.configuraciones);
//
//       }
//   }

    //@Override
//    public void onResume() {
//        super.onResume();
//        if (!getUserVisibleHint()) {
//            return;
//        }
//
//        MainActivity mainActivity = (MainActivity) getActivity();
//
//        if (mainActivity != null) {
//
//            mainActivity.hideFloatingActionButton();
//            mainActivity.getSupportActionBar().show();
//
//        }
//    }
}
