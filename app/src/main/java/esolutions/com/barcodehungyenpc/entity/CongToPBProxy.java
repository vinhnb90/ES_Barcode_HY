package esolutions.com.barcodehungyenpc.entity;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import esolutions.com.barcodehungyenpc.database.SqlQuery;
import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class CongToPBProxy extends CursorItemProxy {

    private CongToPB mCongToPB;

    //cờ đánh dấu nếu = true thì cần refreshListKD lại dữ liệu

    public CongToPBProxy(@NonNull Cursor mCursor, int mIndex) {
        super(mCursor, mIndex);
        mCongToPB = new CongToPB();
    }

    public int getCHON() {
        if (mCongToPB.getCHON() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String CHON = SqlQuery.TBL_CTO_PB.CHON.getNameCollumn();
            mCongToPB.setCHON(cursor.getInt(cursor.getColumnIndex(CHON)));
        }
        return mCongToPB.getCHON();
    }

    public int getID() {
        if (mCongToPB.getID_TBL_CTO_PB() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String ID = SqlQuery.TBL_CTO_PB.ID_TBL_CTO_PB.getNameCollumn();
            mCongToPB.setID_TBL_CTO_PB(cursor.getInt(cursor.getColumnIndex(ID)));
        }
        return mCongToPB.getID_TBL_CTO_PB();
    }

    public int getSTT() {
        if (mCongToPB.getSTT() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String STT =  SqlQuery.TBL_CTO_PB.STT.getNameCollumn();
            mCongToPB.setSTT(cursor.getInt(cursor.getColumnIndex(STT)));
        }
        return mCongToPB.getSTT();
    }

    public String getMA_CTO() {
        if (TextUtils.isEmpty(mCongToPB.getMA_CTO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_CTO =  SqlQuery.TBL_CTO_PB.MA_CTO.getNameCollumn();
            mCongToPB.setMA_CTO(cursor.getString(cursor.getColumnIndex(MA_CTO)));
        }
        return mCongToPB.getMA_CTO();
    }
    public String getSO_CTO() {
        if (TextUtils.isEmpty(mCongToPB.getSO_CTO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_CTO =  SqlQuery.TBL_CTO_PB.SO_CTO.getNameCollumn();
            mCongToPB.setSO_CTO(cursor.getString(cursor.getColumnIndex(SO_CTO)));
        }
        return mCongToPB.getSO_CTO();
    }
    public String getMA_DVIQLY() {
        if (TextUtils.isEmpty(mCongToPB.getMA_DVIQLY())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_DVIQLY =  SqlQuery.TBL_CTO_PB.MA_DVIQLY.getNameCollumn();
            mCongToPB.setMA_DVIQLY(cursor.getString(cursor.getColumnIndex(MA_DVIQLY)));
        }
        return mCongToPB.getMA_DVIQLY();
    }


    public String getMA_CLOAI() {
        if (TextUtils.isEmpty(mCongToPB.getMA_CLOAI())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String CLOAI =  SqlQuery.TBL_CTO_PB.MA_CLOAI.getNameCollumn();
            mCongToPB.setMA_CLOAI(cursor.getString(cursor.getColumnIndex(CLOAI)));
        }
        return mCongToPB.getMA_CLOAI();
    }

    public String getNGAY_NHAP_HT() {
        if (TextUtils.isEmpty(mCongToPB.getNGAY_NHAP_HT())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_NHAP_HT =  SqlQuery.TBL_CTO_PB.NGAY_NHAP_HT.getNameCollumn();
            mCongToPB.setNGAY_NHAP_HT(cursor.getString(cursor.getColumnIndex(NGAY_NHAP_HT)));
        }
        return mCongToPB.getNGAY_NHAP_HT();
    }

    public String getNAM_SX() {
        if (TextUtils.isEmpty(mCongToPB.getNAM_SX())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NAM_SX =  SqlQuery.TBL_CTO_PB.NAM_SX.getNameCollumn();
            mCongToPB.setNAM_SX(cursor.getString(cursor.getColumnIndex(NAM_SX)));
        }
        return mCongToPB.getNAM_SX();
    }

    public String getLOAI_SOHUU() {
        if (TextUtils.isEmpty(mCongToPB.getLOAI_SOHUU())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String LOAI_SOHUU =  SqlQuery.TBL_CTO_PB.LOAI_SOHUU.getNameCollumn();
            mCongToPB.setLOAI_SOHUU(cursor.getString(cursor.getColumnIndex(LOAI_SOHUU)));
        }
        return mCongToPB.getLOAI_SOHUU();
    }
    public String getTEN_SOHUU() {
        if (TextUtils.isEmpty(mCongToPB.getTEN_SOHUU())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TEN_SOHUU =  SqlQuery.TBL_CTO_PB.TEN_SOHUU.getNameCollumn();
            mCongToPB.setTEN_SOHUU(cursor.getString(cursor.getColumnIndex(TEN_SOHUU)));
        }
        return mCongToPB.getTEN_SOHUU();
    }


    public String getNGAY_BDONG() {
        if (TextUtils.isEmpty(mCongToPB.getNGAY_BDONG())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_BDONG =  SqlQuery.TBL_CTO_PB.NGAY_BDONG.getNameCollumn();
            mCongToPB.setNGAY_BDONG(cursor.getString(cursor.getColumnIndex(NGAY_BDONG)));
        }
        return mCongToPB.getNGAY_BDONG();
    }


    public String getMA_BDONG() {
        if (TextUtils.isEmpty(mCongToPB.getMA_BDONG())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_BDONG =  SqlQuery.TBL_CTO_PB.MA_BDONG.getNameCollumn();
            mCongToPB.setMA_BDONG(cursor.getString(cursor.getColumnIndex(MA_BDONG)));
        }
        return mCongToPB.getMA_BDONG();
    }


    public String getNGAY_BDONG_HTAI() {
        if (TextUtils.isEmpty(mCongToPB.getNGAY_BDONG_HTAI())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_BDONG_HTAI =  SqlQuery.TBL_CTO_PB.NGAY_BDONG_HTAI.getNameCollumn();
            mCongToPB.setNGAY_BDONG_HTAI(cursor.getString(cursor.getColumnIndex(NGAY_BDONG_HTAI)));
        }
        return mCongToPB.getNGAY_BDONG_HTAI();
    }

    public String getSO_PHA() {
        if (TextUtils.isEmpty(mCongToPB.getSO_PHA())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_PHA =  SqlQuery.TBL_CTO_PB.SO_PHA.getNameCollumn();
            mCongToPB.setSO_PHA(cursor.getString(cursor.getColumnIndex(SO_PHA)));
        }
        return mCongToPB.getSO_PHA();
    }

    public String getSO_DAY() {
        if (TextUtils.isEmpty(mCongToPB.getSO_DAY())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_DAY =  SqlQuery.TBL_CTO_PB.SO_DAY.getNameCollumn();
            mCongToPB.setSO_DAY(cursor.getString(cursor.getColumnIndex(SO_DAY)));
        }
        return mCongToPB.getSO_DAY();
    }

    public String getDONG_DIEN() {
        if (TextUtils.isEmpty(mCongToPB.getDONG_DIEN())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String DONG_DIEN =  SqlQuery.TBL_CTO_PB.DONG_DIEN.getNameCollumn();
            mCongToPB.setDONG_DIEN(cursor.getString(cursor.getColumnIndex(DONG_DIEN)));
        }
        return mCongToPB.getDONG_DIEN();
    }

    public String getVH_CONG() {
        if (TextUtils.isEmpty(mCongToPB.getVH_CONG())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String VH_CONG =  SqlQuery.TBL_CTO_PB.VH_CONG.getNameCollumn();
            mCongToPB.setVH_CONG(cursor.getString(cursor.getColumnIndex(VH_CONG)));
        }
        return mCongToPB.getVH_CONG();
    }


    public String getDIEN_AP() {
        if (TextUtils.isEmpty(mCongToPB.getDIEN_AP())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String DIEN_AP =  SqlQuery.TBL_CTO_PB.DIEN_AP.getNameCollumn();
            mCongToPB.setDIEN_AP(cursor.getString(cursor.getColumnIndex(DIEN_AP)));
        }
        return mCongToPB.getDIEN_AP();
    }

    public String getHS_NHAN() {
        if (TextUtils.isEmpty(mCongToPB.getHS_NHAN())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String HS_NHAN =  SqlQuery.TBL_CTO_PB.HS_NHAN.getNameCollumn();
            mCongToPB.setHS_NHAN(cursor.getString(cursor.getColumnIndex(HS_NHAN)));
        }
        return mCongToPB.getHS_NHAN();
    }

    public String getNGAY_KDINH() {
        if (TextUtils.isEmpty(mCongToPB.getNGAY_KDINH())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_KDINH =  SqlQuery.TBL_CTO_PB.NGAY_KDINH.getNameCollumn();
            mCongToPB.setNGAY_KDINH(cursor.getString(cursor.getColumnIndex(NGAY_KDINH)));
        }
        return mCongToPB.getNGAY_KDINH();
    }

    public String getCHISO_THAO() {
        if (TextUtils.isEmpty(mCongToPB.getCHISO_THAO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String CHISO_THAO =  SqlQuery.TBL_CTO_PB.CHISO_THAO.getNameCollumn();
            mCongToPB.setCHISO_THAO(cursor.getString(cursor.getColumnIndex(CHISO_THAO)));
        }
        return mCongToPB.getCHISO_THAO();
    }

    public String getNGAY_NHAP() {
        if (TextUtils.isEmpty(mCongToPB.getNGAY_NHAP())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_NHAP =  SqlQuery.TBL_CTO_PB.NGAY_NHAP.getNameCollumn();
            mCongToPB.setNGAY_NHAP(cursor.getString(cursor.getColumnIndex(NGAY_NHAP)));
        }
        return mCongToPB.getNGAY_NHAP();
    }


    public String getHSN() {
        if (TextUtils.isEmpty(mCongToPB.getHSN())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String HSN =  SqlQuery.TBL_CTO_PB.HSN.getNameCollumn();
            mCongToPB.setHSN(cursor.getString(cursor.getColumnIndex(HSN)));
        }
        return mCongToPB.getHSN();
    }


    public String getNGAY_NHAP_MTB() {
        if (TextUtils.isEmpty(mCongToPB.getNGAY_NHAP_MTB())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_NHAP_MTB =  SqlQuery.TBL_CTO_PB.NGAY_NHAP_MTB.getNameCollumn();
            mCongToPB.setNGAY_NHAP_MTB(cursor.getString(cursor.getColumnIndex(NGAY_NHAP_MTB)));
        }
        return mCongToPB.getNGAY_NHAP_MTB();
    }

    public int getTRANG_THAI_GHIM() {
        if (mCongToPB.getTRANG_THAI_GHIM() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TRANG_THAI_GHIM =  SqlQuery.TBL_CTO_PB.TRANG_THAI_GHIM.getNameCollumn();
            mCongToPB.setTRANG_THAI_GHIM(cursor.getInt(cursor.getColumnIndex(TRANG_THAI_GHIM)))
            ;
        }
        return mCongToPB.getTRANG_THAI_GHIM();
    }

    public int getTRANG_THAI_CHON() {
        if (mCongToPB.getTRANG_THAI_CHON() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TRANG_THAI_CHON =  SqlQuery.TBL_CTO_PB.TRANG_THAI_CHON.getNameCollumn();
            mCongToPB.setTRANG_THAI_CHON(cursor.getInt(cursor.getColumnIndex(TRANG_THAI_CHON)));
            ;
        }
        return mCongToPB.getTRANG_THAI_CHON();
    }


//    public String getmTaiKhoan() {
//        if (TextUtils.isEmpty(mCongToPB.getmTaiKhoan())) {
//            Cursor cursor = getmCursor();
//            cursor.moveToPosition(getmIndex());
//
//            mCongToPB.setmTaiKhoan(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.TaiKhoan.getNameCollumn())));
//        }
//        return mCongToPB.getmTaiKhoan();
//    }

}
