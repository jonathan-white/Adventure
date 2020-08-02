package com.example.android.adventure.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import com.example.android.adventure.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import com.example.android.adventure.adapters.SectionsAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @StringRes
    private static final int[] TAB_TITLES = new int[] {R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    SectionsAdapter sectionsAdapter;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup a ViewPager that uses the SectionsAdapter
        sectionsAdapter = new SectionsAdapter(this);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsAdapter);

        // Setup a TabLayout that can sit atop the ViewPager
        TabLayout tabs = findViewById(R.id.tabs);

        // Setup a TabLayoutMediator instance and set the Tab header Text
        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))).attach();

        // Setup click listener for the Floating Action Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("CLOSE", v -> Log.d("SNACKBAR", "You've clicked the snackbar")).show());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Get the SearchView and set the Searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SEARCHBOX_SUBMIT",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SEARCHBOX_CHANGE",newText);
                // sectionsAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}