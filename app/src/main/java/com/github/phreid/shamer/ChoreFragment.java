package com.github.phreid.shamer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

public class ChoreFragment extends Fragment {
    private Chore mChore;
    private EditText mEditText;
    private Button mButton;
    private CheckBox mCheckBox;
    private static final String ARG_CHORE_ID = "chore_id";

    public static ChoreFragment newInstance(UUID choreId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHORE_ID, choreId);

        ChoreFragment fragment = new ChoreFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID choreId = (UUID) getArguments().getSerializable(ARG_CHORE_ID);
        mChore = ChoreBank.get(getActivity()).getChore(choreId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chore, container, false);

        mEditText = view.findViewById(R.id.title_edit_text);
        mEditText.setText(mChore.getTitle());
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mChore.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        mButton = view.findViewById(R.id.chore_detail_button);
        mButton.setText(mChore.getDate().toString());
        mButton.setEnabled(false);

        mCheckBox = view.findViewById(R.id.chore_finished_checkbox);
        mCheckBox.setChecked(mChore.isFinished());
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mChore.setFinished(isChecked);
            }
        });

        return view;
    }
}
