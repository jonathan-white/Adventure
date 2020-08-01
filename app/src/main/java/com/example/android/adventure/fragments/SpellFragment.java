package com.example.android.adventure.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.adventure.R;
import com.example.android.adventure.adapters.SpellsAdapter;
import com.example.android.adventure.utils.Spell;
import com.example.android.adventure.utils.SpellsViewModel;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class SpellFragment extends Fragment {

    private ArrayList<Spell> spells;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SpellFragment() {
    }

    /**
     *  Override of the onCreateView method to inflate the view using a RecyclerView. The view is
     *  populated using a ViewModel which fetches data from a firebase server
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the view using the spell_fragment_item_list (RecyclerView)
        View view = inflater.inflate(R.layout.spell_fragment_item_list, container, false);

        // Setup a ViewModel using the SpellsViewModel class
        SpellsViewModel spellsList = new ViewModelProvider(this).get(SpellsViewModel.class);

        // Fetch the list of spells from the server and display them within the RecyclerView
        spellsList.getSpells().observe(getViewLifecycleOwner(), serverSpells -> {
            spells = serverSpells;

            // Set the adapter
            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(new SpellsAdapter(spells));
            }

        });

        return view;
    }
}