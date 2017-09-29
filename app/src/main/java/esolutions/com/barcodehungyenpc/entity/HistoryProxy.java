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

    //cờ đánh dấu nếu = true thì cần refreshListKD lại dữ liệu

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

    public String getTYPE_TBL_CTO() {
        if (TextUtils.isEmpty(mHistory.getTYPE_TBL_CTO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TYPE_TBL_CTO = SqlQuery.TBL_HISTORY.TYPE_TBL_CTO.getNameCollumn();
            mHistory.setTYPE_TBL_CTO(cursor.getString(cursor.getColumnIndex(TYPE_TBL_CTO)));
        }
        return mHistory.getTYPE_SESSION();
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

    public String getTYPE_RESULT() {
        if (TextUtils.isEmpty(mHistory.getTYPE_RESULT())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TYPE_RESULT = SqlQuery.TBL_HISTORY.TYPE_RESULT.getNameCollumn();
            mHistory.setTYPE_RESULT(cursor.getString(cursor.getColumnIndex(TYPE_RESULT)));
        }
        return mHistory.getTYPE_RESULT();
    }

    public String getINFO_SEARCH() {
        if (TextUtils.isEmpty(mHistory.getINFO_SEARCH())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String INFO_SEARCH = SqlQuery.TBL_HISTORY.INFO_SEARCH.getNameCollumn();
            mHistory.setINFO_SEARCH(cursor.getString(cursor.getColumnIndex(INFO_SEARCH)));
        }
        return mHistory.getINFO_SEARCH();
    }


}
