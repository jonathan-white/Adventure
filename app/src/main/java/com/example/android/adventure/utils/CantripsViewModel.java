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

public class CantripsViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Cantrip>> cantrips;

    /**
     * Getter method used to allow other classes to access the list of cantrips
     * @return
     */
    public LiveData<ArrayList<Cantrip>> getCantrips() {
        if (cantrips == null) {
            cantrips = new MutableLiveData<>();
            loadCantrips();
        }
        return cantrips;
    }

    private void loadCantrips() {
        // Setup a Cloud Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Add Firebase settings to persist the data when working offline. This is critical to
        // prevent the app from re-fetching data it already has
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        // Setup a temp ArrayList which will hold the list of cantrips
        ArrayList listOfCantrips = new ArrayList<>();

        // Fetch cantrips from Firebase
        db.collection("cantrips")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // If the OnCompleteListener is successful, cycle through each doc
                            // and add them to the cantrips ArrayList
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // instantiate a Cantrip object with the fetched document's info
                                Cantrip cantrip = document.toObject(Cantrip.class);
                                listOfCantrips.add(cantrip);
                            }

                            //Sort the list by the Title
                            Collections.sort(listOfCantrips, new Comparator<Cantrip>() {
                                @Override
                                public int compare(Cantrip o1, Cantrip o2) {
                                    return o1.getTitle().compareTo(o2.getTitle());
                                }
                            });
                            // once all cantrips have been added ot listOfCantrips, store those details
                            // into the cantrips MutableLiveData variable
                            cantrips.setValue(listOfCantrips);
                        } else {
                            Log.w("FIREBASE_DATA", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
