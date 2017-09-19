package esolutions.com.barcodehungyenpc.entity;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import esolutions.com.barcodehungyenpc.database.SqlQuery;
import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class CongToProxy extends CursorItemProxy {
    private CongTo mCongTo;
    private Common.KIEU_CHUONG_TRINH mKieuChuongTrinh;

    //cờ đánh dấu nếu = true thì cần refresh lại dữ liệu

    public CongToProxy(@NonNull Cursor mCursor, int mIndex, Common.KIEU_CHUONG_TRINH mKieuChuongTrinh) {
        super(mCursor, mIndex);
        mCongTo = new CongTo();
        this.mKieuChuongTrinh = mKieuChuongTrinh;
    }

    public int getSTT() {
        if (mCongTo.getSTT() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String STT = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) ? SqlQuery.TBL_CTO_PB.STT.getNameCollumn() : SqlQuery.TBL_CTO_GUI_KD.STT.getNameCollumn();
            mCongTo.setSTT(cursor.getInt(cursor.getColumnIndex(STT)));
        }
        return mCongTo.getSTT();
    }

    public String getMaDLuc() {
        if (TextUtils.isEmpty(mCongTo.getMA_DVIQLY())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_DVIQLY = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) ? SqlQuery.TBL_CTO_PB.MA_DVIQLY.getNameCollumn() : SqlQuery.TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn();
            mCongTo.setMA_DVIQLY(cursor.getString(cursor.getColumnIndex(MA_DVIQLY)));
        }
        return mCongTo.getMA_DVIQLY();
    }

    public String getmChiSoThao() {
        if (TextUtils.isEmpty(mCongTo.getCHISO_THAO())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String CHISO_THAO = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) ? SqlQuery.TBL_CTO_PB.CHISO_THAO.getNameCollumn() : SqlQuery.TBL_CTO_GUI_KD.CHISO_THAO.getNameCollumn();
            mCongTo.setCHISO_THAO(cursor.getString(cursor.getColumnIndex(CHISO_THAO)));
        }
        return mCongTo.getCHISO_THAO();
    }

    public String getmMaChungLoai() {
        if (TextUtils.isEmpty(mCongTo.getCLOAI())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            String CLOAI = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) ? SqlQuery.TBL_CTO_PB.CLOAI.getNameCollumn() : SqlQuery.TBL_CTO_GUI_KD.CLOAI.getNameCollumn();
            mCongTo.setCLOAI(cursor.getString(cursor.getColumnIndex(CLOAI)));
        }
        return mCongTo.getCLOAI();
    }

    public String getmMaCto() {
        if (TextUtils.isEmpty(mCongTo.getMA_CTO())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            String MA_CTO = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) ? SqlQuery.TBL_CTO_PB.MA_CTO.getNameCollumn() : SqlQuery.TBL_CTO_GUI_KD.MA_CTO.getNameCollumn();
            mCongTo.setMA_CTO(cursor.getString(cursor.getColumnIndex(MA_CTO)));
        }
        return mCongTo.getMA_CTO();
    }

    public String getmNamSX() {
        if (TextUtils.isEmpty(mCongTo.getNAMSX())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            String NAMSX = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) ? SqlQuery.TBL_CTO_PB.NAMSX.getNameCollumn() : SqlQuery.TBL_CTO_GUI_KD.NAMSX.getNameCollumn();
            mCongTo.setNAMSX(cursor.getString(cursor.getColumnIndex(NAMSX)));
        }
        return mCongTo.getNAMSX();
    }

    public String getmSoCto() {
        if (TextUtils.isEmpty(mCongTo.getSO_CTO())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_CTO = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) ? SqlQuery.TBL_CTO_PB.SO_CTO.getNameCollumn() : SqlQuery.TBL_CTO_GUI_KD.SO_CTO.getNameCollumn();
            mCongTo.setSO_CTO(cursor.getString(cursor.getColumnIndex(SO_CTO)));
        }
        return mCongTo.getSO_CTO();
    }

    public String getNGAY_NHAP() {
        if (TextUtils.isEmpty(mCongTo.getNGAY_NHAP())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_NHAP = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) ? SqlQuery.TBL_CTO_PB.NGAY_NHAP.getNameCollumn() : SqlQuery.TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn();
            mCongTo.setNGAY_NHAP(cursor.getString(cursor.getColumnIndex(NGAY_NHAP)));
        }
        return mCongTo.getNGAY_NHAP();
    }


    public int getTRANG_THAI_GHIM() {
        if (mCongTo.getTRANG_THAI_GHIM() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TRANG_THAI_GHIM = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) ? SqlQuery.TBL_CTO_PB.TRANG_THAI_GHIM.getNameCollumn() : SqlQuery.TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn();
            mCongTo.setTRANG_THAI_GHIM(cursor.getInt(cursor.getColumnIndex(TRANG_THAI_GHIM)))
            ;
        }
        return mCongTo.getTRANG_THAI_GHIM();
    }

    public int getTRANG_THAI_CHON() {
        if (mCongTo.getTRANG_THAI_CHON() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TRANG_THAI_CHON = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) ? SqlQuery.TBL_CTO_PB.TRANG_THAI_CHON.getNameCollumn() : SqlQuery.TBL_CTO_GUI_KD.TRANG_THAI_CHON.getNameCollumn();
            mCongTo.setTRANG_THAI_CHON(cursor.getInt(cursor.getColumnIndex(TRANG_THAI_CHON)));
            ;
        }
        return mCongTo.getTRANG_THAI_CHON();
    }


//    public String getmTaiKhoan() {
//        if (TextUtils.isEmpty(mCongTo.getmTaiKhoan())) {
//            Cursor cursor = getmCursor();
//            cursor.moveToPosition(getmIndex());
//
//            mCongTo.setmTaiKhoan(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.TaiKhoan.getNameCollumn())));
//        }
//        return mCongTo.getmTaiKhoan();
//    }

}
