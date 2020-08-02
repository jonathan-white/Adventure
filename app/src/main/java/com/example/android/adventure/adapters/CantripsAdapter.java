package com.example.android.adventure.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.adventure.R;
import com.example.android.adventure.utils.Cantrip;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Cantrip}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CantripsAdapter extends RecyclerView.Adapter<CantripsAdapter.CantripViewHolder> {

    private final Comparator<Cantrip> mComparator;
    private final LayoutInflater mInflater;

    private final SortedList<Cantrip> mSortedList = new SortedList<>(Cantrip.class, new SortedList.Callback<Cantrip>() {
        @Override
        public int compare(Cantrip o1, Cantrip o2) {
            return mComparator.compare(o1, o2);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(Cantrip oldItem, Cantrip newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(Cantrip item1, Cantrip item2) {
            return item1.getTitle() == item2.getTitle();
        }
    });

    public CantripsAdapter(Context context, Comparator<Cantrip> comparator) {
        mInflater = LayoutInflater.from(context);
        mComparator = comparator;
    }

    public void add(Cantrip cantrip) {
        mSortedList.add(cantrip);
    }

    public void remove(Cantrip cantrip) {
        mSortedList.remove(cantrip);
    }

    public void add(ArrayList<Cantrip> cantrips) { mSortedList.addAll(cantrips); }

    public void remove(ArrayList<Cantrip> cantrips) {
        mSortedList.beginBatchedUpdates();
        for (Cantrip cantrip : cantrips) {
            mSortedList.remove(cantrip);
        }
        mSortedList.endBatchedUpdates();
    }

    // Replaces all items in the RecyclerView at once. removes everything which is not in the list
    // and adds all items which are missing from the SortedList
    public void replaceAll(ArrayList<Cantrip> cantrips) {
        mSortedList.beginBatchedUpdates();
        for (int i = mSortedList.size() - 1; i >= 0; i--) {
            final Cantrip cantrip = mSortedList.get(i);
            if (!cantrips.contains(cantrip)) {
                mSortedList.remove(cantrip);
            }
        }
        mSortedList.addAll(cantrips);
        mSortedList.endBatchedUpdates();
    }

    @NonNull
    @Override
    public CantripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cantrip_fragment_item, parent, false);
        return new CantripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CantripViewHolder holder, int position) {
        final Cantrip cantrip = mSortedList.get(position);
        holder.bind(cantrip);
    }

    @Override
    public int getItemCount() {
        return mSortedList == null ? 0 : mSortedList.size();
    }

    public static class CantripViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mDescription;
        public Cantrip mItem;

        public CantripViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = view.findViewById(R.id.item_title);
            mDescription = view.findViewById(R.id.item_description);

            // Define click listener for the ViewHolder's view.
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                     TODO: perform an action once a Cantrip has been clicked (i.e. display another
                     fragment)
                    */
                    Log.d("CantripsAdapter", "Cantrip '" + mTitle.getText() + "' clicked.");
                }
            });
        }

        public void bind(Cantrip cantrip) {
            mTitle.setText(cantrip.getTitle());
            mDescription.setText(cantrip.getDescription());
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescription.getText() + "'";
        }
    }
}