package esolutions.com.barcodehungyenpc.entity;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by VinhNB on 8/8/2017.
 */

public abstract class CursorItemProxy {
    private Cursor mCursor;
    private int mIndex;

    public CursorItemProxy(@NonNull Cursor mCursor, int mIndex) {
        this.mCursor = mCursor;
        this.mIndex = mIndex;
    }

    public Cursor getmCursor() {
        return mCursor;
    }

    public int getmIndex() {
        return mIndex;
    }
}
