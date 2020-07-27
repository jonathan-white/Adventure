package com.example.android.adventure.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ListView;
import android.widget.Toast;

import com.example.android.adventure.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SearchableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        // TODO: Handle Intent from Search Dialog/Search Widget
        // Get the intent, verify the action and get the query
        Intent queryIntent = getIntent();
        if (Intent.ACTION_SEARCH.equals(queryIntent.getAction())) {
            String query = queryIntent.getStringExtra(SearchManager.QUERY);
            //doMySearch(query);
            Toast toast = Toast.makeText(SearchableActivity.this, query, Toast.LENGTH_SHORT);
            toast.show();
        }

        // TODO: depending on the results display data in different RecyclerViewAdapters?
    }

}