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
import com.example.android.adventure.adapters.CantripsAdapter;
import com.example.android.adventure.utils.Cantrip;
import com.example.android.adventure.utils.CantripsViewModel;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class CantripFragment extends Fragment {

    private ArrayList<Cantrip> cantrips;

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

        // Setup a ViewModel using the CantripsViewModel class
       CantripsViewModel cantripsList = new ViewModelProvider(this).get(CantripsViewModel.class);

        // Fetch the list of cantrips from the server and display them within the RecyclerView
        cantripsList.getCantrips().observe(getViewLifecycleOwner(), serverCantrips -> {
            cantrips = serverCantrips;

            // Set the adapter
            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(new CantripsAdapter(cantrips));
            }

        });
        return view;
    }
}