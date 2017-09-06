package esolutions.com.barcodehungyenpc.entity;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class CongTo {
    private int STT;
    private String MA_DVIQLY;
    private String CLOAI;
    private String NAMSX;
    private String SO_CTO;
    private String MA_CTO;
    private String CHISO_THAO;

//    private int mGhimCto;
//    private String mTaiKhoan;

    private String NGAY_NHAP;
//    private int mKieuCongTo;

    public CongTo() {
        STT = -1;
//        mGhimCto = -1;
//        mKieuCongTo = -1;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public int getSTT() {
        return STT;
    }

    public String getMA_DVIQLY() {
        return MA_DVIQLY;
    }

    public void setMA_DVIQLY(String MA_DVIQLY) {
        this.MA_DVIQLY = MA_DVIQLY;
    }

    public String getCLOAI() {
        return CLOAI;
    }

    public void setCLOAI(String CLOAI) {
        this.CLOAI = CLOAI;
    }

    public String getNAMSX() {
        return NAMSX;
    }

    public void setNAMSX(String NAMSX) {
        this.NAMSX = NAMSX;
    }

    public String getSO_CTO() {
        return SO_CTO;
    }

    public void setSO_CTO(String SO_CTO) {
        this.SO_CTO = SO_CTO;
    }

    public String getMA_CTO() {
        return MA_CTO;
    }

    public void setMA_CTO(String MA_CTO) {
        this.MA_CTO = MA_CTO;
    }

    public String getCHISO_THAO() {
        return CHISO_THAO;
    }

    public void setCHISO_THAO(String CHISO_THAO) {
        this.CHISO_THAO = CHISO_THAO;
    }

//    public int getmGhimCto() {
//        return mGhimCto;
//    }
//
//    public void setmGhimCto(int mGhimCto) {
//        this.mGhimCto = mGhimCto;
//    }

//    public String getmTaiKhoan() {
//        return mTaiKhoan;
//    }
//
//    public void setmTaiKhoan(String mTaiKhoan) {
//        this.mTaiKhoan = mTaiKhoan;
//    }

    public String getNGAY_NHAP() {
        return NGAY_NHAP;
    }

    public void setNGAY_NHAP(String NGAY_NHAP) {
        this.NGAY_NHAP = NGAY_NHAP;
    }

//    public int getmKieuCongTo() {
//        return mKieuCongTo;
//    }
//
//    public void setmKieuCongTo(int mKieuCongTo) {
//        this.mKieuCongTo = mKieuCongTo;
//    }
}
