package com.example.android.adventure.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.adventure.R;
import com.example.android.adventure.utils.Ritual;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Ritual}.
 */
public class RitualsAdapter extends RecyclerView.Adapter<RitualsAdapter.ViewHolder> {

    private final ArrayList<Ritual> mValues;

    public RitualsAdapter(ArrayList<Ritual> items) { mValues = items; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ritual_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitle.setText(mValues.get(position).getTitle());
        holder.mDescription.setText(mValues.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mDescription;
        public Ritual mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = view.findViewById(R.id.item_number);
            mDescription = view.findViewById(R.id.content);

            // Define click listener for the ViewHolder's view.
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                     TODO: perform an action once a Ritual has been clicked (i.e. display another
                     fragment)
                    */
                    Log.d("RitualsAdapter", "Ritual '" + mTitle.getText() + "' clicked.");
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescription.getText() + "'";
        }
    }
}