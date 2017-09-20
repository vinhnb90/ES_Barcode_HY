package esolutions.com.barcodehungyenpc.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import esolutions.com.barcodehungyenpc.entity.CongTo;
import esolutions.com.barcodehungyenpc.entity.CongToProxy;
import esolutions.com.barcodehungyenpc.entity.DienLuc;
import esolutions.com.barcodehungyenpc.entity.DienLucProxy;
import esolutions.com.barcodehungyenpc.utils.Common;

import static android.content.ContentValues.TAG;

/**
 * Created by VinhNB on 8/8/2017.
 * Sau khi có database được khởi tạo (SQL Helper) và được kết nối (SqlConnect)
 * sẽ được sử dụng để query filterDataReal (SqlDAO) với thông tin các lệnh (SqlQuery)
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
//    public List<CongToProxy> getAllCongTo(Common.KIEU_CHUONG_TRINH mKieuChuongTrinh) throws Exception {
//        if (!Common.isExistDB())
//            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());
//
//        String[] args = SqlDAO.build(
//                MainKiemDinhActivity.sTaiKhoan
//        );
//        List<CongToProxy> listCongToProxies = new ArrayList<>();
//
//        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getSelectTBL_CTO_PB(), args);
//
//        if (cursor == null) {
//            Log.d(TAG, "getAllCongTo: null cursor");
//            return listCongToProxies;
//        }
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            listCongToProxies.add(new CongToProxy(cursor, cursor.getPosition()));
//            cursor.moveToNext();
//        }
//
//        if (listCongToProxies.isEmpty())
//            closeCursor(cursor);
//        return listCongToProxies;
//    }

    public List<CongToProxy> getByDateAllCongToPB(String dateSQL) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
                dateSQL
        );
        List<CongToProxy> listCongToProxies = new ArrayList<>();

        Cursor cursor = null;
        cursor = mSqLiteDatabase.rawQuery(SqlQuery.getAllCongToByDatePB(), args);

        if (cursor == null) {
            Log.d(TAG, "getAllCongTo: null cursor");
            return listCongToProxies;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listCongToProxies.add(new CongToProxy(cursor, cursor.getPosition(), Common.KIEU_CHUONG_TRINH.PHAN_BO));
            cursor.moveToNext();
        }

        if (listCongToProxies.isEmpty())
            closeCursor(cursor);
        return listCongToProxies;
    }

    public List<CongToProxy> getByDateAllCongToKD(String dateSQL) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
                dateSQL
        );
        List<CongToProxy> listCongToProxies = new ArrayList<>();

        Cursor cursor = null;
        cursor = mSqLiteDatabase.rawQuery(SqlQuery.getAllCongToByDateKD(), args);

        if (cursor == null) {
            Log.d(TAG, "getAllCongTo: null cursor");
            return listCongToProxies;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listCongToProxies.add(new CongToProxy(cursor, cursor.getPosition(), Common.KIEU_CHUONG_TRINH.PHAN_BO));
            cursor.moveToNext();
        }

        if (listCongToProxies.isEmpty())
            closeCursor(cursor);
        return listCongToProxies;
    }

    public void insertTBL_CTO_GUI_KD(CongTo congTo) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
                congTo.getMA_DVIQLY(),
                congTo.getCLOAI(),
                congTo.getNAMSX(),
                congTo.getSO_CTO(),
                congTo.getMA_CTO(),
                congTo.getCHISO_THAO(),
//                congTo.getTRANG_THAI_GHIM(),
//                MainKiemDinhActivity.sTaiKhoan,
                congTo.getNGAY_NHAP(),
                congTo.getTRANG_THAI_GHIM(),
                congTo.getTRANG_THAI_CHON()
        );

        mSqLiteDatabase.execSQL(SqlQuery.getInsertTBL_CTO_GUI_KD(), args);
    }

    public void insertTBL_CTO_PB(CongTo congTo) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
                congTo.getMA_DVIQLY(),
                congTo.getCLOAI(),
                congTo.getNAMSX(),
                congTo.getSO_CTO(),
                congTo.getMA_CTO(),
                congTo.getCHISO_THAO(),
//                congTo.getTRANG_THAI_GHIM(),
//                MainKiemDinhActivity.sTaiKhoan,
                congTo.getNGAY_NHAP()
//                ,
//                congTo.getmKieuCongTo()

        );

        mSqLiteDatabase.execSQL(SqlQuery.getInsertTBL_CTO_PB(), args);
    }

    public void insertDumpListCongTo(List<CongTo> congToList, Common.KIEU_CHUONG_TRINH type) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        mSqLiteDatabase.beginTransaction();
        for (CongTo congTo : congToList) {
            if (type == Common.KIEU_CHUONG_TRINH.KIEM_DINH)
                insertTBL_CTO_GUI_KD(congTo);
            else
                insertTBL_CTO_PB(congTo);
        }
        mSqLiteDatabase.setTransactionSuccessful();
        mSqLiteDatabase.endTransaction();
    }

    public void deleteAllCongTo() throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
//                MainKiemDinhActivity.sTaiKhoan
        );
        mSqLiteDatabase.rawQuery(SqlQuery.getDeleteAllTBL_CTO_PB(), args);
    }

    public void updateGhimCtoPB(int idCto, int statusGhimCto) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
                statusGhimCto,
                idCto
        );

        mSqLiteDatabase.execSQL(SqlQuery.getUpdateGhimCtoPB(), args);
    }

    public void updateGhimCtoKD(int idCto, int statusGhimCto) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
                statusGhimCto,
                idCto
        );

        mSqLiteDatabase.execSQL(SqlQuery.getUpdateGhimCtoKD(), args);
    }

    public void updateChonCtoPB(int idCto, int statusChonCto) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
                statusChonCto,
                idCto
        );

        mSqLiteDatabase.execSQL(SqlQuery.getUpdateChonCtoPB(), args);
    }

    public void updateChonCtoKD(int idCto, int statusChonCto) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
                statusChonCto,
                idCto
        );

        mSqLiteDatabase.execSQL(SqlQuery.getUpdateChonCtoKD(), args);
    }

    public String selectDienLuc(String maDienLuc) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        if (TextUtils.isEmpty(maDienLuc))
            return "";

        return "";
    }

    public List<CongToProxy> getByDateAllCongToGhimPB(String dateSQL) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
                dateSQL
        );
        List<CongToProxy> listCongToProxies = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getByDateSelectCongToGhimPB(), args);

        if (cursor == null) {
            Log.d(TAG, "getAllCongTo: null cursor");
            return listCongToProxies;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listCongToProxies.add(new CongToProxy(cursor, cursor.getPosition(), Common.KIEU_CHUONG_TRINH.PHAN_BO));
            cursor.moveToNext();
        }

        if (listCongToProxies.isEmpty())
            closeCursor(cursor);
        return listCongToProxies;
    }

    public List<CongToProxy> getByDateAllCongToGhimKD(String dateSQL) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
                dateSQL
        );
        List<CongToProxy> listCongToProxies = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getByDateSelectCongToGhimKD(), args);

        if (cursor == null) {
            Log.d(TAG, "getAllCongTo: null cursor");
            return listCongToProxies;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listCongToProxies.add(new CongToProxy(cursor, cursor.getPosition(), Common.KIEU_CHUONG_TRINH.KIEM_DINH));
            cursor.moveToNext();
        }

        if (listCongToProxies.isEmpty())
            closeCursor(cursor);
        return listCongToProxies;
    }

    public void deleteCongToPB(int idCongTo) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = build(
                idCongTo
        );

        mSqLiteDatabase.execSQL(SqlQuery.getDeleteKD(), args);
    }

    public void deleteCongToKD(int idCongTo) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = build(
                idCongTo
        );

        mSqLiteDatabase.execSQL(SqlQuery.getDeleteKD(), args);
    }


    public boolean checkExistTBL_CTO_GUI_KD(String MA_CTO) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = build(
                MA_CTO
        );
        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getCheckExistTBL_CTO_GUI_KD(), args);
        if (cursor == null) {
            throw new Exception(Common.MESSAGE.ex02.getContent());
        }

        cursor.moveToFirst();
        int count = cursor.getInt(0);

        return (count > 0) ? true : false;
    }

    public boolean checkExistByDateTBL_CTO_GUI_KD(String MA_CTO, String dateSQL) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = build(
                MA_CTO,
                dateSQL
        );
        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getCheckExistByDateTBL_CTO_GUI_KD(), args);
        if (cursor == null) {
            throw new Exception(Common.MESSAGE.ex02.getContent());
        }

        cursor.moveToFirst();
        int count = cursor.getInt(0);

        return (count > 0) ? true : false;
    }


    public boolean checkExistTBL_CTO_PB(String MA_CTO) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = build(
                MA_CTO
        );
        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getCheckExistTBL_CTO_PB(), args);
        if (cursor == null) {
            throw new Exception(Common.MESSAGE.ex02.getContent());
        }

        cursor.moveToFirst();
        int count = cursor.getInt(0);

        return (count > 0) ? true : false;
    }

    //endregion

    //region TBL_DIENLUC
    public List<DienLucProxy> getAllTBL_DIENLUC() throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
        );
        List<DienLucProxy> dienLucProxies = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getSelectDienLuc(), args);

        if (cursor == null) {
            Log.d(TAG, "getAllCongTo: null cursor");
            return dienLucProxies;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            dienLucProxies.add(new DienLucProxy(cursor, cursor.getPosition()));
            cursor.moveToNext();
        }

        if (dienLucProxies.isEmpty())
            closeCursor(cursor);
        return dienLucProxies;
    }

    public boolean checkExistTBL_DIENLUC(String MA_DVIQLY) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = build(
                MA_DVIQLY
        );
        Cursor cursor = mSqLiteDatabase.rawQuery(SqlQuery.getCheckExistTBL_DIENLUC(), args);
        if (cursor == null) {
            throw new Exception(Common.MESSAGE.ex02.getContent());
        }

        cursor.moveToFirst();
        int count = cursor.getInt(0);

        return (count > 0) ? true : false;
    }

    public void insertTBL_DIENLUC(DienLuc dienLuc) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());

        String[] args = SqlDAO.build(
                dienLuc.getMA_DVIQLY()
        );

        mSqLiteDatabase.execSQL(SqlQuery.getInsertTBL_DIENLUC(), args);
    }

    public List<CongToProxy> getByDateAllCongToGhimAndChonKD(String dateSQL) throws Exception {
        if (!Common.isExistDB())
            throw new FileNotFoundException(Common.MESSAGE.ex01.getContent());
        List<CongToProxy> listCongToProxies = new ArrayList<>();

        String[] args = SqlDAO.build(
                dateSQL
        );
        Cursor cursor = null;
        cursor = mSqLiteDatabase.rawQuery(SqlQuery.getByDateAllCongToGhimAndChonKD(), args);

        if (cursor == null) {
            Log.d(TAG, "getAllCongTo: null cursor");
            return listCongToProxies;
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listCongToProxies.add(new CongToProxy(cursor, cursor.getPosition(), Common.KIEU_CHUONG_TRINH.PHAN_BO));
            cursor.moveToNext();
        }

        if (listCongToProxies.isEmpty())
            closeCursor(cursor);
        return listCongToProxies;
    }
    //endregion
}
