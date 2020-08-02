package com.example.android.adventure.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.android.adventure.R;
import com.example.android.adventure.adapters.CantripsAdapter;
import com.example.android.adventure.utils.Cantrip;
import com.example.android.adventure.utils.CantripsViewModel;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A fragment representing a list of Items.
 */
public class CantripFragment extends Fragment {

    private ArrayList<Cantrip> mCantrips;
    private CantripsAdapter mAdapter;
    private RecyclerView recyclerView;

    private static final Comparator<Cantrip> COMPARATOR = new Comparator<Cantrip>() {
        @Override
        public int compare(Cantrip o1, Cantrip o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CantripFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cantrip_fragment_item_list, container, false);

        // Setup a ViewModel using the CantripsViewModel class
       CantripsViewModel cantripsList = new ViewModelProvider(this).get(CantripsViewModel.class);

        Context context = view.getContext();
        mAdapter = new CantripsAdapter(context, COMPARATOR);
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdapter);

        // Fetch the list of cantrips from the server and display them within the RecyclerView
        cantripsList.getCantrips().observe(getViewLifecycleOwner(), serverCantrips -> {
            mCantrips = serverCantrips;

            // Set the adapter
            mAdapter.add(mCantrips);

        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Reset the searchView.setOnQueryTextListener when the Fragment is active
        SearchView searchView = getActivity().findViewById(R.id.menu_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SEARCH_CANTRIPS", newText);
                ArrayList<Cantrip> filteredModelList = filter(mCantrips, newText);
                mAdapter.replaceAll(filteredModelList);
                recyclerView.scrollToPosition(0);
                return true;
            }
        });
    }

    public ArrayList<Cantrip> filter(ArrayList<Cantrip> cantrips, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final ArrayList<Cantrip> filteredList = new ArrayList<>();
        for (Cantrip cantrip : cantrips) {
            final String title = cantrip.getTitle().toLowerCase();
            final String summary = cantrip.getDescription().toLowerCase();
            if (title.contains(lowerCaseQuery) || summary.contains(lowerCaseQuery)) {
                filteredList.add(cantrip);
            }
        }
        return filteredList;
    }
}