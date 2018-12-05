package com.github.phreid.shamer;

import android.support.v4.app.Fragment;

public class ChoreListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ChoreListFragment();
    }
}
