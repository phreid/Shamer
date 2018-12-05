package com.github.phreid.shamer;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChoreBank {
    private static ChoreBank sChoreBank;
    private List<Chore> mChoreList;

    public static ChoreBank get(Context context) {
        if (sChoreBank == null)
            sChoreBank = new ChoreBank(context);

        return sChoreBank;
    }

    private ChoreBank(Context context) {
        mChoreList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Chore chore = new Chore();
            chore.setTitle("Chore #: " + i);
            chore.setFinished(i % 2 == 0);
            chore.setUrgent(i % 3 == 0);
            mChoreList.add(chore);
        }
        //
    }

    public List<Chore> getChoreList() {
        return mChoreList;
    }

    public Chore getChore(UUID choreId) {
        for (Chore c : mChoreList) {
            if (c.getId().equals(choreId))
                return c;
        }

        return null;
    }

}
