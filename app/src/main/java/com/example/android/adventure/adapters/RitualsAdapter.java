package com.example.android.adventure.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.android.adventure.R;
import com.example.android.adventure.utils.Ritual;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Ritual}.
 */
public class RitualsAdapter extends RecyclerView.Adapter<RitualsAdapter.ViewHolder> implements Filterable {

    private final ArrayList<Ritual> mOriginalList;
    private ArrayList<Ritual> mList;

    public RitualsAdapter() {
        mOriginalList = new ArrayList<>();
        mList = new ArrayList<>();
    }

    public RitualsAdapter(ArrayList<Ritual> items) {
        mOriginalList = items;
        mList = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ritual_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mList.get(position);
        holder.mTitle.setText(mList.get(position).getTitle());
        holder.mDescription.setText(mList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mList = (ArrayList<Ritual>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<Ritual> filteredResults = null;
                if (constraint.length() == 0) {
                    filteredResults = mOriginalList;
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    protected ArrayList<Ritual> getFilteredResults(String query) {
        ArrayList<Ritual> results = new ArrayList<>();
        for (Ritual item : mOriginalList) {
            if (item.getTitle().toLowerCase().contains(query)) {
                results.add(item);
            }
        }
        return results;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mDescription;
        public Ritual mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = view.findViewById(R.id.item_title);
            mDescription = view.findViewById(R.id.item_description);

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