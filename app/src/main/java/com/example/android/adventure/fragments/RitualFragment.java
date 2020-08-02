package com.example.android.adventure.fragments;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.adventure.R;
import com.example.android.adventure.adapters.RitualsAdapter;
import com.example.android.adventure.utils.Ritual;
import com.example.android.adventure.utils.RitualsViewModel;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class RitualFragment extends Fragment {

    private ArrayList<Ritual> rituals;
    private RitualsAdapter mAdapter;

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

        // Setup a ViewModel using the RitualsViewModel class
        RitualsViewModel ritualsList = new ViewModelProvider(this).get(RitualsViewModel.class);

        mAdapter = new RitualsAdapter();

        // Fetch the list of rituals from the server and display them within the RecyclerView
        ritualsList.getRituals().observe(getViewLifecycleOwner(), serverRituals -> {
            rituals = serverRituals;

            // Set the adapter
            mAdapter = new RitualsAdapter(rituals);
            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(mAdapter);
            }

        });

        SearchView searchView = getActivity().findViewById(R.id.menu_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SEARCH_RITUALS", newText);
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return view;
    }
}