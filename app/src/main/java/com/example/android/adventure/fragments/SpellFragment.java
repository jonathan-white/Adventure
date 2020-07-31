package com.example.android.adventure.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.adventure.R;
import com.example.android.adventure.adapters.SpellRecyclerViewAdapter;
import com.example.android.adventure.utils.Spell;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

        // Access a Cloud Firestore instance from your Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Fetch data from Firestore
        db.collection("spells")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("FIREBASE_DATA", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("FIREBASE_DATA", "Error getting documents.", task.getException());
                        }
                    }
                });

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
                "Close", "1 minute",false,"Enchantment"));
        spells.add(new Spell("Beast Shape",
                "the mage can take the form of a common animal that they have seen creatures that can fly or have a swim movement are excluded",
                "Self", "1 hour",false,"Summoning"));
        spells.add(new Spell("Burning Hands",
                "Causes fire damage(1d10)  to a target. Save for 1/2 damage",
                "Near", "Instant",true,"Offense"));
        spells.add(new Spell("Call the Swarm",
                "small animals surround a target causing 1 hp damage per round, Save for zero damage",
                "Near", "Concentration",true,"Summoning"));
        spells.add(new Spell("Commanding Word",
                "Compels target to follow stated course of action.  Must be a single word.  Can gesture.",
                "Near", "1 round",true,"Enchantment"));
        spells.add(new Spell("Conjure Darkness",
                "Entire area in near range becomes dark",
                "Near", "3 rounds/level",false,"Illusion"));
        spells.add(new Spell("Counter Spell",
                "Caster has disadvantage on spell casting for 1 round",
                "Near", "1 round",true,"Defense"));
        spells.add(new Spell("Dark Sight",
                "Caster can see up to far range  in the dark",
                "Self", "1 hour",false,"Divination"));

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(new SpellRecyclerViewAdapter(spells));
        }
        return view;
    }
}