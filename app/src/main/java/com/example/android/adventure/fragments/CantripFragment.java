package com.example.android.adventure.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.adventure.R;
import com.example.android.adventure.adapters.CantripRecyclerViewAdapter;
import com.example.android.adventure.utils.Cantrip;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class CantripFragment extends Fragment {

    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CantripFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cantrip_fragment_item_list, container, false);

        // Create a list of words
        final ArrayList<Cantrip> cantrips = new ArrayList<>();
        cantrips.add(new Cantrip("Beast Ken", "You can speak with animals and attempt to call them"));
        cantrips.add(new Cantrip("Blessing", "Give +2 to an ally for a specific task (smite your enemies, avoid those arrows)"));
        cantrips.add(new Cantrip("Conjure Sound", "Create sounds, but not speech."));
        cantrips.add(new Cantrip("Druid's Touch", "cause a cutting or seed to sprout and grow at a prodigious pace"));
        cantrips.add(new Cantrip("Glamour Weaving", "create a small silent illusion"));

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new CantripRecyclerViewAdapter(cantrips));
        }
        return view;
    }
}