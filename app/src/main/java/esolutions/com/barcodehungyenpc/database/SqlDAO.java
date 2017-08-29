package esolutions.com.barcodehungyenpc.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import esolutions.com.barcodehungyenpc.entity.CongTo;
import esolutions.com.barcodehungyenpc.entity.CongToProxy;
import esolutions.com.barcodehungyenpc.utils.Common;
import esolutions.com.barcodehungyenpc.view.MainActivity;

import static android.content.ContentValues.TAG;

/**
 * Created by VinhNB on 8/8/2017.
 * Sau khi có database được khởi tạo (SQL Helper) và được kết nối (SqlConnect)
 * sẽ được sử dụng để query data (SqlDAO) với thông tin các lệnh (SqlQuery)
 */

public class SqlDAO {
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;

    public SqlDAO(SQLiteDatabase mSqLiteDatabase, Context mContext) {
        this.mSqLiteDatabase = mSqLiteDatabase;
        this.mContext = mContext;
    }

    public static String[] build(Object... values) {
        String[] arr = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Boolean) {
                boolean value = (Boolean) values[i];
                arr[i] = String.valueOf(value ? 1 : 0);
            } else {
                arr[i] = String.valueOf(values[i]);
            }
        }
        return arr;
    }

    public void closeCursor(final Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }

    //region Tbl_CongTo
    public List<CongToProxy> getAllCongTo() {
        String[] args = SqlDAO.build(
                MainActivity.sTaiKhoan
        );
        List<CongToProxy> listCongToProxies = new ArrayList<>();

        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getSelectCongTo(), args);

        if (cursor == null) {
            Log.d(TAG, "getAllCongTo: null cursor");
            return listCongToProxies;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listCongToProxies.add(new CongToProxy(cursor, cursor.getPosition()));
            cursor.moveToNext();
        }

        if (listCongToProxies.isEmpty())
            closeCursor(cursor);
        return listCongToProxies;
    }

    public List<CongToProxy> getAllCongTo(Common.KIEU_CONG_TO type) {
        String[] args = SqlDAO.build(
                MainActivity.sTaiKhoan,
                type.getCode()
        );
        List<CongToProxy> listCongToProxies = new ArrayList<>();

        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getSelectCongTo(), args);

        if (cursor == null) {
            Log.d(TAG, "getAllCongTo: null cursor");
            return listCongToProxies;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listCongToProxies.add(new CongToProxy(cursor, cursor.getPosition()));
            cursor.moveToNext();
        }

        if (listCongToProxies.isEmpty())
            closeCursor(cursor);
        return listCongToProxies;
    }


    public void insertCongTo(CongTo congTo) {
        String[] args = SqlDAO.build(
                congTo.getmMaDLuc(),
                congTo.getmMaChungLoai(),
                congTo.getmNamSX(),
                congTo.getmSoCto(),
                congTo.getmMaCto(),
                congTo.getmChiSoThao(),
                congTo.getmGhimCto(),
                MainActivity.sTaiKhoan,
                congTo.getmNgayGhiDuLieu(),
                congTo.getmKieuCongTo()

        );

        mSqLiteDatabase.execSQL(SqlQuery.getInsertCongTo(), args);
    }

    public void insertListCongTo(List<CongTo> congToList) {
        mSqLiteDatabase.beginTransaction();
        for (CongTo congTo : congToList) {
            insertCongTo(congTo);
        }
        mSqLiteDatabase.setTransactionSuccessful();
        mSqLiteDatabase.endTransaction();
    }

    public void deleteAllCongTo() {
        String[] args = SqlDAO.build(
                MainActivity.sTaiKhoan
        );
        mSqLiteDatabase.rawQuery(SqlQuery.getDeleteAllCongTo(), args);
    }

    public void updateGhimCto(int idCto, Common.KIEU_CONG_TO kieuCongTo, int statusGhimCto) {
        String[] args = SqlDAO.build(
                statusGhimCto,
                idCto,
                MainActivity.sTaiKhoan,
                kieuCongTo.getCode()
        );

        mSqLiteDatabase.execSQL(SqlQuery.getUpdateGhimCto(), args);
    }

    public String selectDienLuc(String maDienLuc) {
        if (TextUtils.isEmpty(maDienLuc))
            return "";

        return "";
    }

    public List<CongToProxy> getAllCongToGhim() {
        String[] args = SqlDAO.build(
                MainActivity.sTaiKhoan
        );
        List<CongToProxy> listCongToProxies = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getSelectCongToGhim(), args);

        if (cursor == null) {
            Log.d(TAG, "getAllCongTo: null cursor");
            return listCongToProxies;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listCongToProxies.add(new CongToProxy(cursor, cursor.getPosition()));
            cursor.moveToNext();
        }

        if (listCongToProxies.isEmpty())
            closeCursor(cursor);
        return listCongToProxies;
    }

    public List<CongToProxy> getAllCongToGhim(Common.KIEU_CONG_TO kieuCongTo) {
        String[] args = SqlDAO.build(
                MainActivity.sTaiKhoan,
                kieuCongTo.getCode()
        );
        List<CongToProxy> listCongToProxies = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getSelectCongToGhim(), args);

        if (cursor == null) {
            Log.d(TAG, "getAllCongTo: null cursor");
            return listCongToProxies;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listCongToProxies.add(new CongToProxy(cursor, cursor.getPosition()));
            cursor.moveToNext();
        }

        if (listCongToProxies.isEmpty())
            closeCursor(cursor);
        return listCongToProxies;
    }

    public void deleteCongTo(int idCongTo) {
        String[] args = build(
                idCongTo,
                MainActivity.sTaiKhoan);

        mSqLiteDatabase.execSQL(SqlQuery.getDeleteCongTo(), args);
    }
    //endregion


}
