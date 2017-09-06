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

    public int getSTT() {
        if(mCongTo.getSTT()==-1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            mCongTo.setSTT(cursor.getInt(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.STT.getNameCollumn())));
        }
        return mCongTo.getSTT();
    }

    public String getMaDLuc() {
        if (TextUtils.isEmpty(mCongTo.getMA_DVIQLY())) {


            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setMA_DVIQLY(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.MA_DVIQLY.getNameCollumn())));
        }
        return mCongTo.getMA_DVIQLY();
    }

    public String getmChiSoThao() {
        if (TextUtils.isEmpty(mCongTo.getCHISO_THAO())) {


            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setCHISO_THAO(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.CHISO_THAO.getNameCollumn())));
        }
        return mCongTo.getCHISO_THAO();
    }

    public String getmMaChungLoai() {
        if (TextUtils.isEmpty(mCongTo.getCLOAI())) {


            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setCLOAI(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.CLOAI.getNameCollumn())));
        }
        return mCongTo.getCLOAI();
    }

    public String getmMaCto() {
        if (TextUtils.isEmpty(mCongTo.getMA_CTO())) {


            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setMA_CTO(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.MA_CTO.getNameCollumn())));
        }
        return mCongTo.getMA_CTO();
    }

    public String getmNamSX() {
        if (TextUtils.isEmpty(mCongTo.getNAMSX())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setNAMSX(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.NAMSX.getNameCollumn())));
        }
        return mCongTo.getNAMSX();
    }

    public String getmSoCto() {
        if (TextUtils.isEmpty(mCongTo.getSO_CTO())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setSO_CTO(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.SO_CTO.getNameCollumn())));
        }
        return mCongTo.getSO_CTO();
    }

    public String getNGAY_NHAP() {
        if (TextUtils.isEmpty(mCongTo.getNGAY_NHAP())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongTo.setNGAY_NHAP(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.NGAY_NHAP.getNameCollumn())));
        }
        return mCongTo.getNGAY_NHAP();
    }


//    public int getmGhimCto() {
//        if(mCongTo.getmGhimCto()==-1) {
//            Cursor cursor = getmCursor();
//            cursor.moveToPosition(getmIndex());
//            mCongTo.setmGhimCto(cursor.getInt(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.GhimCto.getNameCollumn())));
//        }
//        return mCongTo.getmGhimCto();
//    }

//    public String getmTaiKhoan() {
//        if (TextUtils.isEmpty(mCongTo.getmTaiKhoan())) {
//            Cursor cursor = getmCursor();
//            cursor.moveToPosition(getmIndex());
//
//            mCongTo.setmTaiKhoan(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.TaiKhoan.getNameCollumn())));
//        }
//        return mCongTo.getmTaiKhoan();
//    }

//    public int getmKieuCongTo() {
//        if(mCongTo.getmKieuCongTo()==-1) {
//            Cursor cursor = getmCursor();
//            cursor.moveToPosition(getmIndex());
//            mCongTo.setmKieuCongTo(cursor.getInt(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.KieuCongToPhanBo.getNameCollumn())));
//        }
//        return mCongTo.getmKieuCongTo();
//    }
}
