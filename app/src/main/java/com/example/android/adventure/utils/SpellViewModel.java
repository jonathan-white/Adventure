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

public class SpellViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Spell>> spells;
    public LiveData<ArrayList<Spell>> getSpells() {
        if (spells == null) {
            spells = new MutableLiveData<ArrayList<Spell>>();
            loadSpells();
        }
        return spells;
    }

    private void loadSpells() {
        // Access a Cloud Firestore instance from your Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        Log.d("LOADING_SPELLS", "Starting DB Connection");

        ArrayList listOfSpells = new ArrayList<>();

        // Fetch data from Firestore
        db.collection("spells")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Spell spell = document.toObject(Spell.class);
//                                Log.d("LOADING_SPELLS", "adding doc data: " + document.getData());
                                listOfSpells.add(spell);
//                                Log.d("FIREBASE_DATA", document.getId() + " => " + document.getData());
                            }
                            Log.d("LOADING_SPELLS", "spells data after load: " + listOfSpells.toString());
                            spells.setValue(listOfSpells);
                        } else {
                            Log.w("FIREBASE_DATA", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

//    @NonNull
//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
