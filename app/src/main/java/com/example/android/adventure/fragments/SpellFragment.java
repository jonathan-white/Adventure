package com.example.android.adventure.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedListAdapterCallback;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.example.android.adventure.R;
import com.example.android.adventure.adapters.SpellsAdapter;
import com.example.android.adventure.utils.Spell;
import com.example.android.adventure.utils.SpellsViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class SpellFragment extends Fragment {

    private ArrayList<Spell> mSpells;
    private RecyclerView recyclerView;
    private SpellsAdapter mAdapter;

    private static final Comparator<Spell> COMPARATOR = new Comparator<Spell>() {
        @Override
        public int compare(Spell o1, Spell o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SpellFragment() {
    }

    /**
     *  Override of the onCreateView method to inflate the view using a RecyclerView. The view is
     *  populated using a ViewModel which fetches data from a firebase server
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the view using the spell_fragment_item_list (RecyclerView)
        View view = inflater.inflate(R.layout.spell_fragment_item_list, container, false);

        // Setup a ViewModel using the SpellsViewModel class
        SpellsViewModel spellsList = new ViewModelProvider(this).get(SpellsViewModel.class);

        Context context = view.getContext();
        mAdapter = new SpellsAdapter(context, COMPARATOR);
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdapter);

        // Fetch the list of spells from the server and display them within the RecyclerView
        spellsList.getSpells().observe(getViewLifecycleOwner(), serverSpells -> {
            mSpells = serverSpells;

            // Set the adapter
            mAdapter.add(mSpells);

        });

        SearchView searchView = getActivity().findViewById(R.id.menu_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SEARCH_SPELLS", newText);
                mAdapter.replaceAll(mAdapter.filter(mSpells, newText));
                return true;
            }
        });

        return view;
    }

}
