package esolutions.com.barcodehungyenpc.entity;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import esolutions.com.barcodehungyenpc.database.SqlQuery;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class CongToProxy extends CursorItemProxy {
    private CongTo mCongTo;

    //cờ đánh dấu nếu = true thì cần refresh lại dữ liệu

    public CongToProxy(@NonNull Cursor mCursor, int mIndex) {
        super(mCursor, mIndex);
        mCongTo = new CongTo();
    }

    public int getmIdCto() {
        if(mCongTo.getmIdCto()==-1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            mCongTo.setmIdCto(cursor.getInt(cursor.getColumnIndex(SqlQuery.TBL_CONG_TO.IdCongTo.getNameCollumn())));
        }
        return mCongTo.getmIdCto();
    }

    public String getMaDLuc() {
        if (TextUtils.isEmpty(mCongTo.getmMaDLuc())) {


            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setmMaDLuc(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CONG_TO.MaDienLuc.getNameCollumn())));
        }
        return mCongTo.getmMaDLuc();
    }

    public String getmChiSoThao() {
        if (TextUtils.isEmpty(mCongTo.getmChiSoThao())) {


            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setmChiSoThao(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CONG_TO.ChiSoThao.getNameCollumn())));
        }
        return mCongTo.getmChiSoThao();
    }

    public String getmMaChungLoai() {
        if (TextUtils.isEmpty(mCongTo.getmMaChungLoai())) {


            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setmMaChungLoai(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CONG_TO.MaChungLoai.getNameCollumn())));
        }
        return mCongTo.getmMaChungLoai();
    }

    public String getmMaCto() {
        if (TextUtils.isEmpty(mCongTo.getmMaCto())) {


            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setmMaCto(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CONG_TO.MaCto.getNameCollumn())));
        }
        return mCongTo.getmMaCto();
    }

    public String getmNamSX() {
        if (TextUtils.isEmpty(mCongTo.getmNamSX())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setmNamSX(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CONG_TO.NamSX.getNameCollumn())));
        }
        return mCongTo.getmNamSX();
    }

    public String getmSoCto() {
        if (TextUtils.isEmpty(mCongTo.getmSoCto())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setmSoCto(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CONG_TO.SoCto.getNameCollumn())));
        }
        return mCongTo.getmSoCto();
    }

    public int getmGhimCto() {
        if(mCongTo.getmGhimCto()==-1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            mCongTo.setmGhimCto(cursor.getInt(cursor.getColumnIndex(SqlQuery.TBL_CONG_TO.GhimCto.getNameCollumn())));
        }
        return mCongTo.getmGhimCto();
    }

    public String getmTaiKhoan() {
        if (TextUtils.isEmpty(mCongTo.getmTaiKhoan())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setmTaiKhoan(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CONG_TO.TaiKhoan.getNameCollumn())));
        }
        return mCongTo.getmTaiKhoan();
    }

    public int getmKieuCongTo() {
        if(mCongTo.getmKieuCongTo()==-1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            mCongTo.setmKieuCongTo(cursor.getInt(cursor.getColumnIndex(SqlQuery.TBL_CONG_TO.KieuCongToPhanBo.getNameCollumn())));
        }
        return mCongTo.getmKieuCongTo();
    }
}
