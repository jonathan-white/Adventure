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
import com.example.android.adventure.utils.Ritual;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Ritual}.
 */
public class RitualsAdapter extends RecyclerView.Adapter<RitualsAdapter.RitualViewHolder> {

    private final Comparator<Ritual> mComparator;
    private final LayoutInflater mInflater;

    private final SortedList<Ritual> mSortedList = new SortedList<>(Ritual.class, new SortedList.Callback<Ritual>() {
        @Override
        public int compare(Ritual o1, Ritual o2) {
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
        public boolean areContentsTheSame(Ritual oldItem, Ritual newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(Ritual item1, Ritual item2) {
            return item1.getTitle() == item2.getTitle();
        }
    });

    public RitualsAdapter(Context context, Comparator<Ritual> comparator) {
        mInflater = LayoutInflater.from(context);
        mComparator = comparator;
    }

    public void add(Ritual ritual) {
        mSortedList.add(ritual);
    }

    public void remove(Ritual ritual) {
        mSortedList.remove(ritual);
    }

    public void add(ArrayList<Ritual> rituals) { mSortedList.addAll(rituals); }

    public void remove(ArrayList<Ritual> rituals) {
        mSortedList.beginBatchedUpdates();
        for (Ritual ritual : rituals) {
            mSortedList.remove(ritual);
        }
        mSortedList.endBatchedUpdates();
    }

    // Replaces all items in the RecyclerView at once. removes everything which is not in the list
    // and adds all items which are missing from the SortedList
    public void replaceAll(ArrayList<Ritual> rituals) {
        mSortedList.beginBatchedUpdates();
        for (int i = mSortedList.size() - 1; i >= 0; i--) {
            final Ritual ritual = mSortedList.get(i);
            if (!rituals.contains(ritual)) {
                mSortedList.remove(ritual);
            }
        }
        mSortedList.addAll(rituals);
        mSortedList.endBatchedUpdates();
    }

    @NonNull
    @Override
    public RitualViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.ritual_fragment_item, parent, false);
        return new RitualViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RitualViewHolder holder, int position) {
        final Ritual ritual = mSortedList.get(position);
        holder.bind(ritual);
    }

    @Override
    public int getItemCount() {
        return mSortedList == null ? 0 : mSortedList.size();
    }

    /**
     * Provide a reference to the type of views that you are using (custom RitualViewHolder)
     */
    public static class RitualViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mDescription;
        public Ritual mItem;

        public RitualViewHolder(View view) {
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

        public void bind(Ritual ritual) {
            mTitle.setText(ritual.getTitle());
            mDescription.setText(ritual.getDescription());
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescription.getText() + "'";
        }
    }
}