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
import java.util.Comparator;

/**
 * A fragment representing a list of Items.
 */
public class RitualFragment extends Fragment {

    private ArrayList<Ritual> mRituals;
    private RitualsAdapter mAdapter;
    private RecyclerView recyclerView;

    private static final Comparator<Ritual> COMPARATOR = new Comparator<Ritual>() {
        @Override
        public int compare(Ritual o1, Ritual o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RitualFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ritual_fragment_item_list, container, false);

        // Setup a ViewModel using the RitualsViewModel class
        RitualsViewModel ritualsList = new ViewModelProvider(this).get(RitualsViewModel.class);

        Context context = view.getContext();
        mAdapter = new RitualsAdapter(context, COMPARATOR);
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdapter);

        // Fetch the list of rituals from the server and display them within the RecyclerView
        ritualsList.getRituals().observe(getViewLifecycleOwner(), serverRituals -> {
            mRituals = serverRituals;

            // Set the adapter
            mAdapter.add(mRituals);
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
                Log.d("SEARCH_RITUALS", newText);
                ArrayList<Ritual> filteredModelList = filter(mRituals, newText);
                mAdapter.replaceAll(filteredModelList);
                recyclerView.scrollToPosition(0);
                return true;
            }
        });
    }

    public ArrayList<Ritual> filter(ArrayList<Ritual> rituals, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final ArrayList<Ritual> filteredList = new ArrayList<>();
        for (Ritual ritual : rituals) {
            final String title = ritual.getTitle().toLowerCase();
            final String summary = ritual.getDescription().toLowerCase();
            if (title.contains(lowerCaseQuery) || summary.contains(lowerCaseQuery)) {
                filteredList.add(ritual);
            }
        }
        return filteredList;
    }
}