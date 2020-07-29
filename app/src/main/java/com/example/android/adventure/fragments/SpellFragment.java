package com.example.android.adventure.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.sync.SyncConfiguration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.adventure.R;
import com.example.android.adventure.adapters.SpellRecyclerViewAdapter;
import com.example.android.adventure.utils.Spell;

import java.util.ArrayList;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A fragment representing a list of Items.
 */
public class SpellFragment extends Fragment {

    private int mColumnCount = 1;
    public static User user;
    public static Realm realm;

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

        /**
         * Start of Realm details
         */
        String appID = "osa-jcobl";
        App app = new App(new AppConfiguration.Builder(appID)
                .build());
        Credentials credentials = Credentials.anonymous();

        app.loginAsync(credentials, it -> {
            if (it.isSuccess()) {
                Log.v("QUICKSTART", "Successfully authenticated anonymously.");
                user = app.currentUser();

                String partitionValue = "osa";
                SyncConfiguration config = new SyncConfiguration.Builder(user, partitionValue)
                        .waitForInitialRemoteData()
                        .build();

                // Sync all realm changes via a new instance, and when that instance has been successfully
                // created connect it to an on-screen list (a recycler view)
                Realm.getInstanceAsync(config, new Realm.Callback() {
                    @Override
                    @ParametersAreNonnullByDefault
                    public void onSuccess(Realm _realm) {
                        // since this realm should live exactly as long as this activity, assign the realm
                        // to a member variable
                        realm = _realm;
                        Log.v("QUICKSTART", "Successfully instantiated realm!");
                    }
                });

            } else {
                Log.e("QUICKSTART", it.getError().toString());
            }
        });


        /**
         * End of Realm details
         */

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

            recyclerView.setAdapter(new SpellRecyclerViewAdapter(spells));
        }
        return view;
    }
}