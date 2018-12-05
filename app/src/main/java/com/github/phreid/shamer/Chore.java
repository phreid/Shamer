package com.github.phreid.shamer;

import java.util.Date;
import java.util.UUID;

public class Chore {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mFinished;

    private boolean mIsUrgent;

    public Chore() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setFinished(boolean finished) {
        mFinished = finished;
    }

    public UUID getId() {

        return mId;
    }

    public String getTitle() {

        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public boolean isUrgent() {
        return mIsUrgent;
    }

    public void setUrgent(boolean urgent) {
        mIsUrgent = urgent;
    }

    public boolean isFinished() {
        return mFinished;
    }
}
