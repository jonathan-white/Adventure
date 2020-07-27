package com.example.android.adventure.activities;

import android.os.Bundle;

import com.example.android.adventure.R;
import com.example.android.adventure.utils.Spell;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import com.example.android.adventure.adapters.SectionsPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        ArrayList<Spell> spells = new ArrayList<>();
        spells.add(new Spell("Abjuration",
                "Send a single individual from another plane back to its home realm",
                "Near", "Instant",true,"Summoning"));
        spells.add(new Spell("Banish the Dead",
                "Any undead creatures flee from the caster",
                "Near", "10 minutes",true,"Necromancy"));
        spells.add(new Spell("Abjuration",
                "Send a single individual from another plane back to its home realm",
                "Near", "Instant",true,"Summoning"));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onSearchRequested();

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}