package esolutions.com.barcodehungyenpc.entity;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import esolutions.com.barcodehungyenpc.database.SqlQuery;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class DienLucProxy extends CursorItemProxy {
    private DienLuc mDienLuc;

    //cờ đánh dấu nếu = true thì cần refresh lại dữ liệu

    public DienLucProxy(@NonNull Cursor mCursor, int mIndex) {
        super(mCursor, mIndex);
        mDienLuc = new DienLuc();
    }

    public int getID() {
        if(mDienLuc.getID()==-1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            mDienLuc.setID(cursor.getInt(cursor.getColumnIndex(SqlQuery.TBL_DIENLUC.ID_TBL_DIENLUC.getNameCollumn())));
        }
        return mDienLuc.getID();
    }

    public String getMA_DVIQLY() {
        if (TextUtils.isEmpty(mDienLuc.getMA_DVIQLY())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mDienLuc.setMA_DVIQLY(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_DIENLUC.MA_DVIQLY.getNameCollumn())));
        }
        return mDienLuc.getMA_DVIQLY();
    }
}
