package com.example.android.adventure.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.adventure.R;
import com.example.android.adventure.utils.Spell;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Spell}.
 */
public class SpellsAdapter extends RecyclerView.Adapter<SpellsAdapter.SpellViewHolder> {

    private final ArrayList<Spell> mValues;

    public SpellsAdapter(ArrayList<Spell> items) {
        mValues = items;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public SpellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spell_fragment_item, parent, false);

        return new SpellViewHolder(view);
    }

    //Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final SpellViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitle.setText(mValues.get(position).getTitle());
        holder.mDescription.setText(mValues.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    /**
     * Provide a reference to the type of views that you are using (custom SpellViewHolder)
     */
    public static class SpellViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mDescription;
        public Spell mItem;

        public SpellViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = view.findViewById(R.id.item_title);
            mDescription = view.findViewById(R.id.item_description);

            // Define click listener for the ViewHolder's view.
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                     TODO: perform an action once a Spell has been clicked (i.e. display another
                     fragment)
                    */
                    Log.d("SpellsAdapter", "Spell '" + mTitle.getText() + "' clicked.");
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescription.getText() + "'";
        }
    }
}