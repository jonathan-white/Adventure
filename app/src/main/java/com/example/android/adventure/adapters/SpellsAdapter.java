package com.example.android.adventure.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.adventure.R;
import com.example.android.adventure.utils.Spell;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Spell}.
 */
public class SpellsAdapter extends RecyclerView.Adapter<SpellsAdapter.SpellViewHolder> {

    private final Comparator<Spell> mComparator;
    private final LayoutInflater mInflater;

    public SpellsAdapter(Context context, Comparator<Spell> comparator) {
        mInflater = LayoutInflater.from(context);
        mComparator = comparator;
    }

    private final SortedList<Spell> mSortedList = new SortedList<>(Spell.class, new SortedList.Callback<Spell>() {
        @Override
        public int compare(Spell o1, Spell o2) {
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
        public boolean areContentsTheSame(Spell oldItem, Spell newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(Spell item1, Spell item2) {
            return item1.getTitle() == item2.getTitle();
        }
    });

    public void add(Spell spell) {
        mSortedList.add(spell);
    }

    public void remove(Spell spell) {
        mSortedList.remove(spell);
    }

    public void add(ArrayList<Spell> spells) {
        mSortedList.addAll(spells);
    }

    public void remove(ArrayList<Spell> spells) {
        mSortedList.beginBatchedUpdates();
        for (Spell spell : spells) {
            mSortedList.remove(spell);
        }
        mSortedList.endBatchedUpdates();
    }

    // Replaces all items in the RecyclerView at once. removes everything which is not in the list
    // and adds all items which are missing from the SortedList
    public void replaceAll(ArrayList<Spell> spells) {
        mSortedList.beginBatchedUpdates();
        for (int i = mSortedList.size() - 1; i >= 0; i--) {
            final Spell spell = mSortedList.get(i);
            if (!spells.contains(spell)) {
                mSortedList.remove(spell);
            }
        }
        mSortedList.addAll(spells);
        mSortedList.endBatchedUpdates();
    }

    public ArrayList<Spell> filter(ArrayList<Spell> spells, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final ArrayList<Spell> filteredList = new ArrayList<>();
        for (Spell spell : spells) {
            final String text = spell.getTitle().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredList.add(spell);
            }
        }
        return filteredList;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public SpellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.spell_fragment_item, parent, false);
        return new SpellViewHolder(view);
    }


    //Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SpellViewHolder holder, int position) {
        final Spell spell = mSortedList.get(position);
        holder.bind(spell);
    }


    @Override
    public int getItemCount() {
        return mSortedList == null ? 0 : mSortedList.size();
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

        public void bind(Spell spell) {
            mTitle.setText(spell.getTitle());
            mDescription.setText(spell.getDescription());
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescription.getText() + "'";
        }
    }
}