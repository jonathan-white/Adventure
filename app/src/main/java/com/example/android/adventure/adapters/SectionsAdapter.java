package com.example.android.adventure.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android.adventure.fragments.CantripFragment;
import com.example.android.adventure.fragments.RitualFragment;
import com.example.android.adventure.fragments.SpellFragment;

/**
 * A [FragmentStateAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;

    public SectionsAdapter(FragmentActivity fm) {
        super(fm);
    }

    // Determine which Fragments to display depending on the current position
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new SpellFragment();
        } else if (position == 1) {
            return new CantripFragment();
        } else {
            return new RitualFragment();
        }
    }

    // Set the total number of pages/tabs
    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}