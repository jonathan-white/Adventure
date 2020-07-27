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
import com.example.android.adventure.adapters.RitualRecyclerViewAdapter;
import com.example.android.adventure.utils.Ritual;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class RitualFragment extends Fragment {

    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RitualFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ritual_fragment_item_list, container, false);

        // Create a list of words
        final ArrayList<Ritual> rituals = new ArrayList<>();
        rituals.add(new Ritual("Arcane Experiment",
                "Determine the properties of a magic item. Requires alchemist kit.",
                "Near", "Instant",false));
        rituals.add(new Ritual("Bind Familiar",
                "Obtain a small magical animal companion.  Requires burn a silver plate, the droppings of the sort of animal she wishes to attract, along with incense, rare herbs, and some food appropriate to the type of animal",
                "Far", "Permanent",false));

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new RitualRecyclerViewAdapter(rituals));
        }
        return view;
    }
}