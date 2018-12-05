package com.github.phreid.shamer;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class ChoreActivity extends SingleFragmentActivity {
    private static final String EXTRA_CHORE_ID =
            "com.github.phreid.shamer.chore_id";

    @Override
    protected Fragment createFragment() {
        UUID choreId = (UUID) getIntent().getSerializableExtra(EXTRA_CHORE_ID);

        return ChoreFragment.newInstance(choreId);
    }

    public static Intent newIntent(Context context, UUID choreId) {
        Intent intent = new Intent(context, ChoreActivity.class);
        intent.putExtra(EXTRA_CHORE_ID, choreId);
        return intent;
    }
}
