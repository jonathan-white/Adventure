package com.example.android.adventure.utils;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RitualsViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Ritual>> rituals;

    /**
     * Getter method used to allow other classes to access the list of rituals
     * @return
     */
    public LiveData<ArrayList<Ritual>> getRituals() {
        if (rituals == null) {
            rituals = new MutableLiveData<>();
            loadRituals();
        }
        return rituals;
    }

    private void loadRituals() {
        // Setup a Cloud Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Add Firebase settings to persist the data when working offline. This is critical to
        // prevent the app from re-fetching data it already has
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        // Setup a temp ArrayList which will hold the list of rituals
        ArrayList listOfRituals = new ArrayList<>();

        // Fetch rituals from Firebase
        db.collection("rituals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // If the OnCompleteListener is successful, cycle through each doc
                            // and add them to the rituals ArrayList
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // instantiate a Ritual object with the fetched document's info
                                Ritual ritual = document.toObject(Ritual.class);
                                listOfRituals.add(ritual);
                            }

                            //Sort the list by the Title
                            Collections.sort(listOfRituals, new Comparator<Ritual>() {
                                @Override
                                public int compare(Ritual o1, Ritual o2) {
                                    return o1.getTitle().compareTo(o2.getTitle());
                                }
                            });
                            // once all rituals have been added ot listOfRituals, store those details
                            // into the rituals MutableLiveData variable
                            rituals.setValue(listOfRituals);
                        } else {
                            Log.w("FIREBASE_DATA", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
