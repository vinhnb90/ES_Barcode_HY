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
    private int TRANG_THAI_GHIM;
    private int TRANG_THAI_CHON;

    public CongTo() {
        STT = -1;
        TRANG_THAI_GHIM = -1;
        TRANG_THAI_CHON = -1;
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

    public int getTRANG_THAI_GHIM() {
        return TRANG_THAI_GHIM;
    }

    public void setTRANG_THAI_GHIM(int TRANG_THAI_GHIM) {
        this.TRANG_THAI_GHIM = TRANG_THAI_GHIM;
    }

    public String getNGAY_NHAP() {
        return NGAY_NHAP;
    }

    public void setNGAY_NHAP(String NGAY_NHAP) {
        this.NGAY_NHAP = NGAY_NHAP;
    }

    public int getTRANG_THAI_CHON() {
        return TRANG_THAI_CHON;
    }

    public void setTRANG_THAI_CHON(int TRANG_THAI_CHON) {
        this.TRANG_THAI_CHON = TRANG_THAI_CHON;
    }

    //    public int getmKieuCongTo() {
//        return mKieuCongTo;
//    }
//
//    public void setmKieuCongTo(int mKieuCongTo) {
//        this.mKieuCongTo = mKieuCongTo;
//    }
}
