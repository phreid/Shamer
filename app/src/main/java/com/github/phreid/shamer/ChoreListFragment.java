package com.github.phreid.shamer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ChoreListFragment extends Fragment {
    private RecyclerView mChoreRecyclerView;
    private ChoreAdapter mChoreAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chore_list, container, false);

        mChoreRecyclerView = view.findViewById(R.id.chore_recycler_view);
        mChoreRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        updateUI();
    }

    private void updateUI() {
        ChoreBank choreBank = ChoreBank.get(getActivity());
        List<Chore> chores = choreBank.getChoreList();

        if (mChoreAdapter == null) {
            mChoreAdapter = new ChoreAdapter(chores);
            mChoreRecyclerView.setAdapter(mChoreAdapter);
        } else {
            mChoreAdapter.notifyDataSetChanged();
        }

    }

    private class ChoreViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;
        TextView mDateTextView;
        ImageView mFinishedImageView;
        Chore mChore;

        ChoreViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.chore_title_text);
            mDateTextView = itemView.findViewById(R.id.chore_date_text);
            mFinishedImageView = itemView.findViewById(R.id.chore_finished_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = ChoreActivity.newIntent(getActivity(),
                            mChore.getId());
                    startActivity(intent);
                }
            });
        }

        void bind(Chore chore) {
            mChore = chore;
            mTitleTextView.setText(chore.getTitle());
            mFinishedImageView.setVisibility(chore.isFinished() ? View.VISIBLE : View.GONE);

            String dateString = DateFormat.getDateFormat(getActivity()).format(chore.getDate());

            mDateTextView.setText(dateString);
        }
    }

    /*private class UrgentChoreViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;
        TextView mDateTextView;
        Chore mChore;

        UrgentChoreViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.chore_title_text);
            mDateTextView = itemView.findViewById(R.id.chore_date_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "URGENT: " + mChore.getTitle(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }

        void bind(Chore chore) {
            mChore = chore;
            mTitleTextView.setText(chore.getTitle());
            mDateTextView.setText(chore.getDate().toString());
        }
    }*/

    private class ChoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<Chore> mChoreList;

        ChoreAdapter(List<Chore> chores) {
            mChoreList = chores;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            /*if (i == R.layout.list_urgent_chore_item) {
                return new ChoreViewHolder(inflater.inflate(
                        R.layout.list_urgent_chore_item, viewGroup, false
                ));
            }*/

            return new ChoreViewHolder(inflater.inflate(
                    R.layout.list_chore_item, viewGroup, false
            ));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
            Chore chore = mChoreList.get(i);
            int itemType = getItemViewType(i);

            if (itemType == R.layout.list_chore_item)
                ((ChoreViewHolder) holder).bind(chore);

            /*if (itemType == R.layout.list_urgent_chore_item)
                ((UrgentChoreViewHolder) holder).bind(chore);*/
        }

        @Override
        public int getItemCount() {
            return mChoreList.size();
        }

        @Override
        public int getItemViewType(int position) {
            Chore chore = mChoreList.get(position);

            /*if (chore.isUrgent())
                return R.layout.list_urgent_chore_item;
            else*/
                return R.layout.list_chore_item;
        }
    }
}
