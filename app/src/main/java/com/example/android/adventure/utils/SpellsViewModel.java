package com.example.android.adventure.utils;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpellsViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Spell>> spells;

    /**
     * Getter method used to allow other classes to access the list of spells
     * @return
     */
    public LiveData<ArrayList<Spell>> getSpells() {
        if (spells == null) {
            spells = new MutableLiveData<ArrayList<Spell>>();
            loadSpells();
        }
        return spells;
    }

    private void loadSpells() {
        // Setup a Cloud Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Add Firebase settings to persist the data when working offline. This is critical to
        // prevent the app from re-fetching data it already has
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        // Setup a temp ArrayList which will hold the list of spells
        ArrayList listOfSpells = new ArrayList<>();

        // Fetch spells from Firebase
        db.collection("spells")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // If the OnCompleteListener is successful, cycle through each doc
                            // and add them to the spells ArrayList
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // instantiate a Spells object with the fetched document's info
                                Spell spell = document.toObject(Spell.class);
                                listOfSpells.add(spell);
                            }
                            // once all spells have been added ot listOfSpells, store those details
                            // into the spells MutableLiveData variable
                            spells.setValue(listOfSpells);
                        } else {
                            Log.w("FIREBASE_DATA", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
