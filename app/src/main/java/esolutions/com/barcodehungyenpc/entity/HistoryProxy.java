package esolutions.com.barcodehungyenpc.entity;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import esolutions.com.barcodehungyenpc.database.SqlQuery;
import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class HistoryProxy extends CursorItemProxy {

    private History mHistory;
    private Common.KIEU_CHUONG_TRINH mKieuChuongTrinh;

    //cờ đánh dấu nếu = true thì cần refresh lại dữ liệu

    public HistoryProxy(@NonNull Cursor mCursor, int mIndex, Common.KIEU_CHUONG_TRINH mKieuChuongTrinh) {
        super(mCursor, mIndex);
        mHistory = new History();
        this.mKieuChuongTrinh = mKieuChuongTrinh;
    }

    public int getID_TBL_HISTORY() {
        if (mHistory.getID_TBL_HISTORY() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String ID_TBL_HISTORY = SqlQuery.TBL_HISTORY.ID_TBL_HISTORY.getNameCollumn();
            mHistory.setID_TBL_HISTORY(cursor.getInt(cursor.getColumnIndex(ID_TBL_HISTORY)));
        }
        return mHistory.getID_TBL_HISTORY();
    }


    public int getID_TBL_CTO() {
        if (mHistory.getID_TBL_CTO() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String ID_TBL_CTO = SqlQuery.TBL_HISTORY.ID_TBL_CTO.getNameCollumn();
            mHistory.setID_TBL_CTO(cursor.getInt(cursor.getColumnIndex(ID_TBL_CTO)));
        }
        return mHistory.getID_TBL_CTO();
    }

    public int getTYPE_TBL_CTO() {
        if (mHistory.getTYPE_TBL_CTO() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TYPE_TBL_CTO = SqlQuery.TBL_HISTORY.TYPE_TBL_CTO.getNameCollumn();
            mHistory.setTYPE_TBL_CTO(cursor.getInt(cursor.getColumnIndex(TYPE_TBL_CTO)));
        }
        return mHistory.getTYPE_TBL_CTO();
    }


    public String getTYPE_SESSION() {
        if (TextUtils.isEmpty(mHistory.getTYPE_SESSION())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TYPE_SESSION = SqlQuery.TBL_HISTORY.TYPE_SESSION.getNameCollumn();
            mHistory.setTYPE_SESSION(cursor.getString(cursor.getColumnIndex(TYPE_SESSION)));
        }
        return mHistory.getTYPE_SESSION();
    }

    public String getDATE_SESSION() {
        if (TextUtils.isEmpty(mHistory.getDATE_SESSION())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String DATE_SESSION = SqlQuery.TBL_HISTORY.DATE_SESSION.getNameCollumn();
            mHistory.setDATE_SESSION(cursor.getString(cursor.getColumnIndex(DATE_SESSION)));
        }
        return mHistory.getDATE_SESSION();
    }

}
