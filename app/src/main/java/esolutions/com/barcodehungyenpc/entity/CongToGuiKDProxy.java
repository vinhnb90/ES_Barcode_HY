package esolutions.com.barcodehungyenpc.entity;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.apache.commons.lang3.StringUtils;

import esolutions.com.barcodehungyenpc.database.SqlQuery;
import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class CongToGuiKDProxy extends CursorItemProxy {

    private CongToGuiKD mCongToGuiKD;

    //cờ đánh dấu nếu = true thì cần refreshListKD lại dữ liệu

    public CongToGuiKDProxy(@NonNull Cursor mCursor, int mIndex) {
        super(mCursor, mIndex);
        mCongToGuiKD = new CongToGuiKD();
    }

    public int getCHON() {
        if (mCongToGuiKD.getCHON() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String CHON = SqlQuery.TBL_CTO_GUI_KD.CHON.getNameCollumn();
            mCongToGuiKD.setCHON(cursor.getInt(cursor.getColumnIndex(CHON)));
        }
        return mCongToGuiKD.getCHON();
    }

    public int getID_TBL_CTO_GUI_KD() {
        if (mCongToGuiKD.getID_TBL_CTO_GUI_KD() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String ID_TBL_CTO_GUI_KD = SqlQuery.TBL_CTO_GUI_KD.ID_TBL_CTO_GUI_KD.getNameCollumn();
            mCongToGuiKD.setID_TBL_CTO_GUI_KD(cursor.getInt(cursor.getColumnIndex(ID_TBL_CTO_GUI_KD)));
        }
        return mCongToGuiKD.getID_TBL_CTO_GUI_KD();
    }

    public int getSTT() {
        if (mCongToGuiKD.getSTT() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String STT =  SqlQuery.TBL_CTO_GUI_KD.STT.getNameCollumn();
            mCongToGuiKD.setSTT(cursor.getInt(cursor.getColumnIndex(STT)));
        }
        return mCongToGuiKD.getSTT();
    }

    public String getMA_CTO() {
        if (StringUtils.isEmpty(mCongToGuiKD.getMA_CTO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_CTO =  SqlQuery.TBL_CTO_GUI_KD.MA_CTO.getNameCollumn();
            mCongToGuiKD.setMA_CTO(cursor.getString(cursor.getColumnIndex(MA_CTO)));
        }
        return mCongToGuiKD.getMA_CTO();
    }
    public String getSO_CTO() {
        if (StringUtils.isEmpty(mCongToGuiKD.getSO_CTO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_CTO =  SqlQuery.TBL_CTO_GUI_KD.SO_CTO.getNameCollumn();
            mCongToGuiKD.setSO_CTO(cursor.getString(cursor.getColumnIndex(SO_CTO)));
        }
        return mCongToGuiKD.getSO_CTO();
    }
    public String getMA_DVIQLY() {
        if (StringUtils.isEmpty(mCongToGuiKD.getMA_DVIQLY())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_DVIQLY =  SqlQuery.TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn();
            mCongToGuiKD.setMA_DVIQLY(cursor.getString(cursor.getColumnIndex(MA_DVIQLY)));
        }
        return mCongToGuiKD.getMA_DVIQLY();
    }


    public String getMA_CLOAI() {
        if (StringUtils.isEmpty(mCongToGuiKD.getMA_CLOAI())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String CLOAI =  SqlQuery.TBL_CTO_GUI_KD.MA_CLOAI.getNameCollumn();
            mCongToGuiKD.setMA_CLOAI(cursor.getString(cursor.getColumnIndex(CLOAI)));
        }
        return mCongToGuiKD.getMA_CLOAI();
    }

    public String getNGAY_NHAP_HT() {
        if (StringUtils.isEmpty(mCongToGuiKD.getNGAY_NHAP_HT())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_NHAP_HT =  SqlQuery.TBL_CTO_GUI_KD.NGAY_NHAP_HT.getNameCollumn();
            mCongToGuiKD.setNGAY_NHAP_HT(cursor.getString(cursor.getColumnIndex(NGAY_NHAP_HT)));
        }
        return mCongToGuiKD.getNGAY_NHAP_HT();
    }

    public String getNAM_SX() {
        if (StringUtils.isEmpty(mCongToGuiKD.getNAM_SX())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NAM_SX =  SqlQuery.TBL_CTO_GUI_KD.NAM_SX.getNameCollumn();
            mCongToGuiKD.setNAM_SX(cursor.getString(cursor.getColumnIndex(NAM_SX)));
        }
        return mCongToGuiKD.getNAM_SX();
    }

    public String getLOAI_SOHUU() {
        if (StringUtils.isEmpty(mCongToGuiKD.getLOAI_SOHUU())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String LOAI_SOHUU =  SqlQuery.TBL_CTO_GUI_KD.LOAI_SOHUU.getNameCollumn();
            mCongToGuiKD.setLOAI_SOHUU(cursor.getString(cursor.getColumnIndex(LOAI_SOHUU)));
        }
        return mCongToGuiKD.getLOAI_SOHUU();
    }
    public String getTEN_SOHUU() {
        if (StringUtils.isEmpty(mCongToGuiKD.getTEN_SOHUU())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TEN_SOHUU =  SqlQuery.TBL_CTO_GUI_KD.TEN_SOHUU.getNameCollumn();
            mCongToGuiKD.setTEN_SOHUU(cursor.getString(cursor.getColumnIndex(TEN_SOHUU)));
        }
        return mCongToGuiKD.getTEN_SOHUU();
    }


    public String getNGAY_BDONG() {
        if (StringUtils.isEmpty(mCongToGuiKD.getNGAY_BDONG())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_BDONG =  SqlQuery.TBL_CTO_GUI_KD.NGAY_BDONG.getNameCollumn();
            mCongToGuiKD.setNGAY_BDONG(cursor.getString(cursor.getColumnIndex(NGAY_BDONG)));
        }
        return mCongToGuiKD.getNGAY_BDONG();
    }


    public String getMA_BDONG() {
        if (StringUtils.isEmpty(mCongToGuiKD.getMA_BDONG())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_BDONG =  SqlQuery.TBL_CTO_GUI_KD.MA_BDONG.getNameCollumn();
            mCongToGuiKD.setMA_BDONG(cursor.getString(cursor.getColumnIndex(MA_BDONG)));
        }
        return mCongToGuiKD.getMA_BDONG();
    }


    public String getNGAY_BDONG_HTAI() {
        if (StringUtils.isEmpty(mCongToGuiKD.getNGAY_BDONG_HTAI())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_BDONG_HTAI =  SqlQuery.TBL_CTO_GUI_KD.NGAY_BDONG_HTAI.getNameCollumn();
            mCongToGuiKD.setNGAY_BDONG_HTAI(cursor.getString(cursor.getColumnIndex(NGAY_BDONG_HTAI)));
        }
        return mCongToGuiKD.getNGAY_BDONG_HTAI();
    }

    public String getSO_PHA() {
        if (StringUtils.isEmpty(mCongToGuiKD.getSO_PHA())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_PHA =  SqlQuery.TBL_CTO_GUI_KD.SO_PHA.getNameCollumn();
            mCongToGuiKD.setSO_PHA(cursor.getString(cursor.getColumnIndex(SO_PHA)));
        }
        return mCongToGuiKD.getSO_PHA();
    }

    public String getSO_DAY() {
        if (StringUtils.isEmpty(mCongToGuiKD.getSO_DAY())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_DAY =  SqlQuery.TBL_CTO_GUI_KD.SO_DAY.getNameCollumn();
            mCongToGuiKD.setSO_DAY(cursor.getString(cursor.getColumnIndex(SO_DAY)));
        }
        return mCongToGuiKD.getSO_DAY();
    }

    public String getDONG_DIEN() {
        if (StringUtils.isEmpty(mCongToGuiKD.getDONG_DIEN())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String DONG_DIEN =  SqlQuery.TBL_CTO_GUI_KD.DONG_DIEN.getNameCollumn();
            mCongToGuiKD.setDONG_DIEN(cursor.getString(cursor.getColumnIndex(DONG_DIEN)));
        }
        return mCongToGuiKD.getDONG_DIEN();
    }

    public String getVH_CONG() {
        if (StringUtils.isEmpty(mCongToGuiKD.getVH_CONG())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String VH_CONG =  SqlQuery.TBL_CTO_GUI_KD.VH_CONG.getNameCollumn();
            mCongToGuiKD.setVH_CONG(cursor.getString(cursor.getColumnIndex(VH_CONG)));
        }
        return mCongToGuiKD.getVH_CONG();
    }


    public String getDIEN_AP() {
        if (StringUtils.isEmpty(mCongToGuiKD.getDIEN_AP())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String DIEN_AP =  SqlQuery.TBL_CTO_GUI_KD.DIEN_AP.getNameCollumn();
            mCongToGuiKD.setDIEN_AP(cursor.getString(cursor.getColumnIndex(DIEN_AP)));
        }
        return mCongToGuiKD.getDIEN_AP();
    }

    public String getHS_NHAN() {
        if (StringUtils.isEmpty(mCongToGuiKD.getHS_NHAN())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String HS_NHAN =  SqlQuery.TBL_CTO_GUI_KD.HS_NHAN.getNameCollumn();
            mCongToGuiKD.setHS_NHAN(cursor.getString(cursor.getColumnIndex(HS_NHAN)));
        }
        return mCongToGuiKD.getHS_NHAN();
    }

    public String getNGAY_KDINH() {
        if (StringUtils.isEmpty(mCongToGuiKD.getNGAY_KDINH())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_KDINH =  SqlQuery.TBL_CTO_GUI_KD.NGAY_KDINH.getNameCollumn();
            mCongToGuiKD.setNGAY_KDINH(cursor.getString(cursor.getColumnIndex(NGAY_KDINH)));
        }
        return mCongToGuiKD.getNGAY_KDINH();
    }

    public String getCHISO_THAO() {
        if (StringUtils.isEmpty(mCongToGuiKD.getCHISO_THAO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String CHISO_THAO =  SqlQuery.TBL_CTO_GUI_KD.CHISO_THAO.getNameCollumn();
            mCongToGuiKD.setCHISO_THAO(cursor.getString(cursor.getColumnIndex(CHISO_THAO)));
        }
        return mCongToGuiKD.getCHISO_THAO();
    }

    public String getNGAY_NHAP() {
        if (StringUtils.isEmpty(mCongToGuiKD.getNGAY_NHAP())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_NHAP =  SqlQuery.TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn();
            mCongToGuiKD.setNGAY_NHAP(cursor.getString(cursor.getColumnIndex(NGAY_NHAP)));
        }
        return mCongToGuiKD.getNGAY_NHAP();
    }


    public String getHSN() {
        if (StringUtils.isEmpty(mCongToGuiKD.getHSN())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String HSN =  SqlQuery.TBL_CTO_GUI_KD.HSN.getNameCollumn();
            mCongToGuiKD.setHSN(cursor.getString(cursor.getColumnIndex(HSN)));
        }
        return mCongToGuiKD.getHSN();
    }


    public String getNGAY_NHAP_MTB() {
        if (StringUtils.isEmpty(mCongToGuiKD.getNGAY_NHAP_MTB())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_NHAP_MTB =  SqlQuery.TBL_CTO_GUI_KD.NGAY_NHAP_MTB.getNameCollumn();
            mCongToGuiKD.setNGAY_NHAP_MTB(cursor.getString(cursor.getColumnIndex(NGAY_NHAP_MTB)));
        }
        return mCongToGuiKD.getNGAY_NHAP_MTB();
    }

    public int getTRANG_THAI_GHIM() {
        if (mCongToGuiKD.getTRANG_THAI_GHIM() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TRANG_THAI_GHIM =  SqlQuery.TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn();
            mCongToGuiKD.setTRANG_THAI_GHIM(cursor.getInt(cursor.getColumnIndex(TRANG_THAI_GHIM)))
            ;
        }
        return mCongToGuiKD.getTRANG_THAI_GHIM();
    }

    public int getTRANG_THAI_CHON() {
        if (mCongToGuiKD.getTRANG_THAI_CHON() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String TRANG_THAI_CHON =  SqlQuery.TBL_CTO_GUI_KD.TRANG_THAI_CHON.getNameCollumn();
            mCongToGuiKD.setTRANG_THAI_CHON(cursor.getInt(cursor.getColumnIndex(TRANG_THAI_CHON)));
            ;
        }
        return mCongToGuiKD.getTRANG_THAI_CHON();
    }


//    public String getmTaiKhoan() {
//        if (StringUtils.isEmpty(mCongToPB.getmTaiKhoan())) {
//            Cursor cursor = getmCursor();
//            cursor.moveToPosition(getmIndex());
//
//            mCongToPB.setmTaiKhoan(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_GUI_KD.TaiKhoan.getNameCollumn())));
//        }
//        return mCongToPB.getmTaiKhoan();
//    }

}
