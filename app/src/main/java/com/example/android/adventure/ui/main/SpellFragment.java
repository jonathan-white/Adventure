package com.example.android.adventure.ui.main;

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
import com.example.android.adventure.Spell;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class SpellFragment extends Fragment {

    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SpellFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.spell_fragment_item_list, container, false);

        // Create a list of words
        final ArrayList<Spell> spells = new ArrayList<>();
        spells.add(new Spell("Abjuration",
                "Send a single individual from another plane back to its home realm",
                "Near", "Instant",true,"Summoning"));
        spells.add(new Spell("Banish the Dead",
                "Any undead creatures flee from the caster",
                "Near", "10 minutes",true,"Necromancy"));
        spells.add(new Spell("Bar the Way",
                "An ordinary door or gate become un-passable",
                "Close", "1 minute",false,"Enchant"));
        spells.add(new Spell("Beast Shape",
                "the mage can take the form of a common animal that they have seen creatures that can fly or have a swim movement are excluded",
                "Self", "1 hour",false,"Summoning"));

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(new MySpellRecyclerViewAdapter(spells));
        }
        return view;
    }
}