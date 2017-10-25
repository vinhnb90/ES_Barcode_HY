package esolutions.com.barcodehungyenpc.entity;

import android.database.Cursor;
import android.support.annotation.NonNull;

import org.apache.commons.lang3.StringUtils;

import esolutions.com.barcodehungyenpc.database.SqlQuery;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class DviPBCapDuoiProxy extends CursorItemProxy {
    private DviPBCapDuoi mDviCapDuoiPB;

    //cờ đánh dấu nếu = true thì cần refreshListKD lại dữ liệu

    public DviPBCapDuoiProxy(@NonNull Cursor mCursor, int mIndex) {
        super(mCursor, mIndex);
        mDviCapDuoiPB = new DviPBCapDuoi();
    }

    public int getID() {
        if (mDviCapDuoiPB.getID() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            mDviCapDuoiPB.setID(cursor.getInt(cursor.getColumnIndex(SqlQuery.TBL_DIENLUC_PB.ID_TBL_DIENLUC_PB.getNameCollumn())));
        }
        return mDviCapDuoiPB.getID();
    }

    public String getMA_DVIQLY_CAPTREN() {
        if (StringUtils.isEmpty(mDviCapDuoiPB.getMA_DVIQLY_CAPTREN())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mDviCapDuoiPB.setMA_DVIQLY_CAPTREN(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_DIENLUC_PB.MA_DVIQLY_CAPTREN.getNameCollumn())));
        }
        return mDviCapDuoiPB.getMA_DVIQLY_CAPTREN();
    }

    public String getMA_DVIQLY_CAPDUOI() {
        if (StringUtils.isEmpty(mDviCapDuoiPB.getMA_DVIQLY_CAPDUOI())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mDviCapDuoiPB.setMA_DVIQLY_CAPDUOI(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_DIENLUC_PB.MA_DVIQLY_CAPDUOI.getNameCollumn())));
        }
        return mDviCapDuoiPB.getMA_DVIQLY_CAPDUOI();
    }

    public String getSEARCH() {
        if (StringUtils.isEmpty(mDviCapDuoiPB.getSEARCH())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mDviCapDuoiPB.setSEARCH(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_DIENLUC_PB.SEARCH.getNameCollumn())));
        }
        return mDviCapDuoiPB.getSEARCH();
    }

    @Override
    public String toString() {
        return this.getSEARCH();
    }
}
