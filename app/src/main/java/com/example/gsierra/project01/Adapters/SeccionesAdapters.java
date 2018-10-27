package com.example.gsierra.project01.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SeccionesAdapters extends FragmentStatePagerAdapter {

    private final List<Fragment> listaFragments = new ArrayList<>();

    private final List<String> listaTitulos = new ArrayList<>();

    public void addFragment(Fragment fragment, String titulo)
    {
        listaFragments.add(fragment);
        listaTitulos.add(titulo);
    }

    public SeccionesAdapters(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listaTitulos.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return listaFragments.get(i);
    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }
}
