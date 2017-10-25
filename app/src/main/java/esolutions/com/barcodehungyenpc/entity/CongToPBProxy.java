package esolutions.com.barcodehungyenpc.entity;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.apache.commons.lang3.StringUtils;

import esolutions.com.barcodehungyenpc.database.SqlQuery;

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

    //thêm
    /* LOAISOHUU("LOAISOHUU"),
    NGAY_NHAP_HTHI("NGAY_NHAP_HTHI"),
    SO_BBAN_KDINH("SO_BBAN_KDINH"),
    MA_NVIENKDINH("MA_NVIENKDINH"),
    NGAY_KDINH_HTHI("NGAY_KDINH_HTHI")*/
    public String getLOAISOHUU() {
        if (StringUtils.isEmpty(mCongToPB.getID_BBAN_KHO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String LOAISOHUU =  SqlQuery.TBL_CTO_PB.LOAISOHUU.getNameCollumn();
            mCongToPB.setLOAISOHUU(cursor.getString(cursor.getColumnIndex(LOAISOHUU)));
        }
        return mCongToPB.getLOAISOHUU();
    }

    public String getNGAY_NHAP_HTHI() {
        if (StringUtils.isEmpty(mCongToPB.getNGAY_NHAP_HTHI())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_NHAP_HTHI =  SqlQuery.TBL_CTO_PB.NGAY_NHAP_HTHI.getNameCollumn();
            mCongToPB.setNGAY_NHAP_HTHI(cursor.getString(cursor.getColumnIndex(NGAY_NHAP_HTHI)));
        }
        return mCongToPB.getNGAY_NHAP_HTHI();
    }

    public String getSO_BBAN_KDINH() {
        if (StringUtils.isEmpty(mCongToPB.getSO_BBAN_KDINH())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_BBAN_KDINH =  SqlQuery.TBL_CTO_PB.SO_BBAN_KDINH.getNameCollumn();
            mCongToPB.setSO_BBAN_KDINH(cursor.getString(cursor.getColumnIndex(SO_BBAN_KDINH)));
        }
        return mCongToPB.getSO_BBAN_KDINH();
    }

    public String getMA_NVIENKDINH() {
        if (StringUtils.isEmpty(mCongToPB.getMA_NVIENKDINH())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_NVIENKDINH =  SqlQuery.TBL_CTO_PB.MA_NVIENKDINH.getNameCollumn();
            mCongToPB.setMA_NVIENKDINH(cursor.getString(cursor.getColumnIndex(MA_NVIENKDINH)));
        }
        return mCongToPB.getMA_NVIENKDINH();
    }

    public String getNGAY_KDINH_HTHI() {
        if (StringUtils.isEmpty(mCongToPB.getNGAY_KDINH_HTHI())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_KDINH_HTHI =  SqlQuery.TBL_CTO_PB.NGAY_KDINH_HTHI.getNameCollumn();
            mCongToPB.setNGAY_KDINH_HTHI(cursor.getString(cursor.getColumnIndex(NGAY_KDINH_HTHI)));
        }
        return mCongToPB.getNGAY_KDINH_HTHI();
    }


    public String getID_BBAN_KHO() {
        if (StringUtils.isEmpty(mCongToPB.getID_BBAN_KHO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String ID_BBAN_KHO =  SqlQuery.TBL_CTO_PB.ID_BBAN_KHO.getNameCollumn();
            mCongToPB.setID_BBAN_KHO(cursor.getString(cursor.getColumnIndex(ID_BBAN_KHO)));
        }
        return mCongToPB.getID_BBAN_KHO();
    }

    public String getMA_NVIEN() {
        if (StringUtils.isEmpty(mCongToPB.getMA_NVIEN())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_NVIEN =  SqlQuery.TBL_CTO_PB.MA_NVIEN.getNameCollumn();
            mCongToPB.setMA_NVIEN(cursor.getString(cursor.getColumnIndex(MA_NVIEN)));
        }
        return mCongToPB.getMA_NVIEN();
    }


    public String getSO_BBAN() {
        if (StringUtils.isEmpty(mCongToPB.getSO_BBAN())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_BBAN =  SqlQuery.TBL_CTO_PB.SO_BBAN.getNameCollumn();
            mCongToPB.setSO_BBAN(cursor.getString(cursor.getColumnIndex(SO_BBAN)));
        }
        return mCongToPB.getSO_BBAN();
    }

    public String getID_BBAN_KDINH() {
        if (StringUtils.isEmpty(mCongToPB.getID_BBAN_KDINH())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String ID_BBAN_KDINH =  SqlQuery.TBL_CTO_PB.ID_BBAN_KDINH.getNameCollumn();
            mCongToPB.setID_BBAN_KDINH(cursor.getString(cursor.getColumnIndex(ID_BBAN_KDINH)));
        }
        return mCongToPB.getID_BBAN_KDINH();
    }


    public String getNGAY_GUIKD() {
        if (StringUtils.isEmpty(mCongToPB.getNGAY_GUIKD())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_GUIKD =  SqlQuery.TBL_CTO_PB.NGAY_GUIKD.getNameCollumn();
            mCongToPB.setNGAY_GUIKD(cursor.getString(cursor.getColumnIndex(NGAY_GUIKD)));
        }
        return mCongToPB.getNGAY_GUIKD();
    }


    public String getLOAI_CTO() {
        if (StringUtils.isEmpty(mCongToPB.getLOAI_CTO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String LOAI_CTO =  SqlQuery.TBL_CTO_PB.LOAI_CTO.getNameCollumn();
            mCongToPB.setLOAI_CTO(cursor.getString(cursor.getColumnIndex(LOAI_CTO)));
        }
        return mCongToPB.getLOAI_CTO();
    }

    public String getSO_CSO() {
        if (StringUtils.isEmpty(mCongToPB.getSO_CSO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_CSO =  SqlQuery.TBL_CTO_PB.SO_CSO.getNameCollumn();
            mCongToPB.setSO_CSO(cursor.getString(cursor.getColumnIndex(SO_CSO)));
        }
        return mCongToPB.getSO_CSO();
    }

    public String getMA_HANG() {
        if (StringUtils.isEmpty(mCongToPB.getMA_HANG())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_HANG =  SqlQuery.TBL_CTO_PB.MA_HANG.getNameCollumn();
            mCongToPB.setMA_HANG(cursor.getString(cursor.getColumnIndex(MA_HANG)));
        }
        return mCongToPB.getMA_HANG();
    }

    public String getCAP_CXAC() {
        if (StringUtils.isEmpty(mCongToPB.getCAP_CXAC())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String CAP_CXAC =  SqlQuery.TBL_CTO_PB.CAP_CXAC.getNameCollumn();
            mCongToPB.setCAP_CXAC(cursor.getString(cursor.getColumnIndex(CAP_CXAC)));
        }
        return mCongToPB.getCAP_CXAC();
    }


    public String getMA_NUOC() {
        if (StringUtils.isEmpty(mCongToPB.getMA_NUOC())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_NUOC =  SqlQuery.TBL_CTO_PB.MA_NUOC.getNameCollumn();
            mCongToPB.setMA_NUOC(cursor.getString(cursor.getColumnIndex(MA_NUOC)));
        }
        return mCongToPB.getMA_NUOC();
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


    public int getID_TBL_CTO_PB() {
        if (mCongToPB.getID_TBL_CTO_PB() == -1) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String ID_TBL_CTO_PB = SqlQuery.TBL_CTO_PB.ID_TBL_CTO_PB.getNameCollumn();
            mCongToPB.setID_TBL_CTO_PB(cursor.getInt(cursor.getColumnIndex(ID_TBL_CTO_PB)));
        }
        return mCongToPB.getID_TBL_CTO_PB();
    }

    public String getMA_CTO() {
        if (StringUtils.isEmpty(mCongToPB.getMA_CTO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_CTO =  SqlQuery.TBL_CTO_PB.MA_CTO.getNameCollumn();
            mCongToPB.setMA_CTO(cursor.getString(cursor.getColumnIndex(MA_CTO)));
        }
        return mCongToPB.getMA_CTO();
    }
    public String getSO_CTO() {
        if (StringUtils.isEmpty(mCongToPB.getSO_CTO())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_CTO =  SqlQuery.TBL_CTO_PB.SO_CTO.getNameCollumn();
            mCongToPB.setSO_CTO(cursor.getString(cursor.getColumnIndex(SO_CTO)));
        }
        return mCongToPB.getSO_CTO();
    }
    public String getMA_DVIQLY() {
        if (StringUtils.isEmpty(mCongToPB.getMA_DVIQLY())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_DVIQLY =  SqlQuery.TBL_CTO_PB.MA_DVIQLY.getNameCollumn();
            mCongToPB.setMA_DVIQLY(cursor.getString(cursor.getColumnIndex(MA_DVIQLY)));
        }
        return mCongToPB.getMA_DVIQLY();
    }


    public String getMA_CLOAI() {
        if (StringUtils.isEmpty(mCongToPB.getMA_CLOAI())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String CLOAI =  SqlQuery.TBL_CTO_PB.MA_CLOAI.getNameCollumn();
            mCongToPB.setMA_CLOAI(cursor.getString(cursor.getColumnIndex(CLOAI)));
        }
        return mCongToPB.getMA_CLOAI();
    }

    public String getNAM_SX() {
        if (StringUtils.isEmpty(mCongToPB.getNAM_SX())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NAM_SX =  SqlQuery.TBL_CTO_PB.NAM_SX.getNameCollumn();
            mCongToPB.setNAM_SX(cursor.getString(cursor.getColumnIndex(NAM_SX)));
        }
        return mCongToPB.getNAM_SX();
    }

    public String getLOAI_SOHUU() {
        if (StringUtils.isEmpty(mCongToPB.getLOAI_SOHUU())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String LOAI_SOHUU =  SqlQuery.TBL_CTO_PB.LOAI_SOHUU.getNameCollumn();
            mCongToPB.setLOAI_SOHUU(cursor.getString(cursor.getColumnIndex(LOAI_SOHUU)));
        }
        return mCongToPB.getLOAI_SOHUU();
    }

    public String getNGAY_BDONG() {
        if (StringUtils.isEmpty(mCongToPB.getNGAY_BDONG())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_BDONG =  SqlQuery.TBL_CTO_PB.NGAY_BDONG.getNameCollumn();
            mCongToPB.setNGAY_BDONG(cursor.getString(cursor.getColumnIndex(NGAY_BDONG)));
        }
        return mCongToPB.getNGAY_BDONG();
    }


    public String getMA_BDONG() {
        if (StringUtils.isEmpty(mCongToPB.getMA_BDONG())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String MA_BDONG =  SqlQuery.TBL_CTO_PB.MA_BDONG.getNameCollumn();
            mCongToPB.setMA_BDONG(cursor.getString(cursor.getColumnIndex(MA_BDONG)));
        }
        return mCongToPB.getMA_BDONG();
    }


    public String getSO_PHA() {
        if (StringUtils.isEmpty(mCongToPB.getSO_PHA())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_PHA =  SqlQuery.TBL_CTO_PB.SO_PHA.getNameCollumn();
            mCongToPB.setSO_PHA(cursor.getString(cursor.getColumnIndex(SO_PHA)));
        }
        return mCongToPB.getSO_PHA();
    }

    public String getSO_DAY() {
        if (StringUtils.isEmpty(mCongToPB.getSO_DAY())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_DAY =  SqlQuery.TBL_CTO_PB.SO_DAY.getNameCollumn();
            mCongToPB.setSO_DAY(cursor.getString(cursor.getColumnIndex(SO_DAY)));
        }
        return mCongToPB.getSO_DAY();
    }

    public String getDONG_DIEN() {
        if (StringUtils.isEmpty(mCongToPB.getDONG_DIEN())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String DONG_DIEN =  SqlQuery.TBL_CTO_PB.DONG_DIEN.getNameCollumn();
            mCongToPB.setDONG_DIEN(cursor.getString(cursor.getColumnIndex(DONG_DIEN)));
        }
        return mCongToPB.getDONG_DIEN();
    }

    public String getVH_CONG() {
        if (StringUtils.isEmpty(mCongToPB.getVH_CONG())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String VH_CONG =  SqlQuery.TBL_CTO_PB.VH_CONG.getNameCollumn();
            mCongToPB.setVH_CONG(cursor.getString(cursor.getColumnIndex(VH_CONG)));
        }
        return mCongToPB.getVH_CONG();
    }


    public String getDIEN_AP() {
        if (StringUtils.isEmpty(mCongToPB.getDIEN_AP())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String DIEN_AP =  SqlQuery.TBL_CTO_PB.DIEN_AP.getNameCollumn();
            mCongToPB.setDIEN_AP(cursor.getString(cursor.getColumnIndex(DIEN_AP)));
        }
        return mCongToPB.getDIEN_AP();
    }


    public String getNGAY_KDINH() {
        if (StringUtils.isEmpty(mCongToPB.getNGAY_KDINH())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_KDINH =  SqlQuery.TBL_CTO_PB.NGAY_KDINH.getNameCollumn();
            mCongToPB.setNGAY_KDINH(cursor.getString(cursor.getColumnIndex(NGAY_KDINH)));
        }
        return mCongToPB.getNGAY_KDINH();
    }

    public String getNGAY_NHAP() {
        if (StringUtils.isEmpty(mCongToPB.getNGAY_NHAP())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String NGAY_NHAP =  SqlQuery.TBL_CTO_PB.NGAY_NHAP.getNameCollumn();
            mCongToPB.setNGAY_NHAP(cursor.getString(cursor.getColumnIndex(NGAY_NHAP)));
        }
        return mCongToPB.getNGAY_NHAP();
    }

    public String getNGAY_NHAP_MTB() {
        if (StringUtils.isEmpty(mCongToPB.getNGAY_NHAP_MTB())) {

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

    public String getSO_PBCT_MTB() {
        if (StringUtils.isEmpty(mCongToPB.getSO_PBCT_MTB())) {

            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());
            String SO_PBCT_MTB =  SqlQuery.TBL_CTO_PB.SO_PBCT_MTB.getNameCollumn();
            mCongToPB.setSO_PBCT_MTB(cursor.getString(cursor.getColumnIndex(SO_PBCT_MTB)));
        }
        return mCongToPB.getSO_PBCT_MTB();
    }


    public String getMA_DVIQLY_CAPDUOI() {
        if (StringUtils.isEmpty(mCongToPB.getMA_DVIQLY_CAPDUOI())) {
            Cursor cursor = getmCursor();
            cursor.moveToPosition(getmIndex());

            mCongToPB.setMA_DVIQLY_CAPDUOI(cursor.getString(cursor.getColumnIndex(SqlQuery.TBL_CTO_PB.MA_DVIQLY_CAPDUOI.getNameCollumn())));
        }
        return mCongToPB.getMA_DVIQLY_CAPDUOI();
    }

}
