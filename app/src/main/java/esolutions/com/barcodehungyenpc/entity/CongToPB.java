package esolutions.com.barcodehungyenpc.entity;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class CongToPB {
    private String ID_BBAN_KHO;
    private String NGAY_NHAP_HTHONG;//
    private String MA_NVIEN;
    private String SO_BBAN;
    private String ID_BBAN_KDINH;
    private String NGAY_GUIKD;
    private String NGAY_KDINH_TH;
    private String LOAI_CTO;
    private String SO_CSO;
    private String MA_HANG;
    private String CAP_CXAC;
    private String MA_NUOC;
    private String ACTION;



    private int CHON;
    private String HS_NHAN;
    private String MA_DVIQLY;
    private String NAM_SX;
    private String MA_CTO;
    private String SO_CTO;
    private String LOAI_SOHUU;
    private String MA_CLOAI;
    private String NGAY_BDONG;
    private String MA_BDONG;
    private String NGAY_NHAP;
    private String NGAY_KDINH;
    private String SO_DAY;
    private String VH_CONG;
    private String SO_PHA;
    private String DIEN_AP;
    private String DONG_DIEN;


    private int ID_TBL_CTO_PB;
    private String NGAY_NHAP_MTB;
    private int TRANG_THAI_GHIM;
    private int TRANG_THAI_CHON;
    //Session đánh dấu phiên tải xuống và up lên của các công tơ

    public CongToPB() {
        ID_TBL_CTO_PB = -1;
        TRANG_THAI_GHIM = -1;
        TRANG_THAI_CHON = -1;
        CHON = -1;
    }

    public String getID_BBAN_KHO() {
        return ID_BBAN_KHO;
    }

    public void setID_BBAN_KHO(String ID_BBAN_KHO) {
        this.ID_BBAN_KHO = ID_BBAN_KHO;
    }

    public String getNGAY_NHAP_HTHONG() {
        return NGAY_NHAP_HTHONG;
    }

    public void setNGAY_NHAP_HTHONG(String NGAY_NHAP_HTHONG) {
        this.NGAY_NHAP_HTHONG = NGAY_NHAP_HTHONG;
    }

    public String getMA_NVIEN() {
        return MA_NVIEN;
    }

    public void setMA_NVIEN(String MA_NVIEN) {
        this.MA_NVIEN = MA_NVIEN;
    }

    public String getSO_BBAN() {
        return SO_BBAN;
    }

    public void setSO_BBAN(String SO_BBAN) {
        this.SO_BBAN = SO_BBAN;
    }

    public String getID_BBAN_KDINH() {
        return ID_BBAN_KDINH;
    }

    public void setID_BBAN_KDINH(String ID_BBAN_KDINH) {
        this.ID_BBAN_KDINH = ID_BBAN_KDINH;
    }

    public String getNGAY_GUIKD() {
        return NGAY_GUIKD;
    }

    public void setNGAY_GUIKD(String NGAY_GUIKD) {
        this.NGAY_GUIKD = NGAY_GUIKD;
    }

    public String getNGAY_KDINH_TH() {
        return NGAY_KDINH_TH;
    }

    public void setNGAY_KDINH_TH(String NGAY_KDINH_TH) {
        this.NGAY_KDINH_TH = NGAY_KDINH_TH;
    }

    public String getLOAI_CTO() {
        return LOAI_CTO;
    }

    public void setLOAI_CTO(String LOAI_CTO) {
        this.LOAI_CTO = LOAI_CTO;
    }

    public String getSO_CSO() {
        return SO_CSO;
    }

    public void setSO_CSO(String SO_CSO) {
        this.SO_CSO = SO_CSO;
    }

    public String getMA_HANG() {
        return MA_HANG;
    }

    public void setMA_HANG(String MA_HANG) {
        this.MA_HANG = MA_HANG;
    }

    public String getCAP_CXAC() {
        return CAP_CXAC;
    }

    public void setCAP_CXAC(String CAP_CXAC) {
        this.CAP_CXAC = CAP_CXAC;
    }

    public String getMA_NUOC() {
        return MA_NUOC;
    }

    public void setMA_NUOC(String MA_NUOC) {
        this.MA_NUOC = MA_NUOC;
    }

    public String getACTION() {
        return ACTION;
    }

    public void setACTION(String ACTION) {
        this.ACTION = ACTION;
    }

    public int getCHON() {
        return CHON;
    }

    public void setCHON(int CHON) {
        this.CHON = CHON;
    }

    public String getHS_NHAN() {
        return HS_NHAN;
    }

    public void setHS_NHAN(String HS_NHAN) {
        this.HS_NHAN = HS_NHAN;
    }

    public String getMA_DVIQLY() {
        return MA_DVIQLY;
    }

    public void setMA_DVIQLY(String MA_DVIQLY) {
        this.MA_DVIQLY = MA_DVIQLY;
    }

    public String getNAM_SX() {
        return NAM_SX;
    }

    public void setNAM_SX(String NAM_SX) {
        this.NAM_SX = NAM_SX;
    }

    public String getMA_CTO() {
        return MA_CTO;
    }

    public void setMA_CTO(String MA_CTO) {
        this.MA_CTO = MA_CTO;
    }

    public String getSO_CTO() {
        return SO_CTO;
    }

    public void setSO_CTO(String SO_CTO) {
        this.SO_CTO = SO_CTO;
    }

    public String getLOAI_SOHUU() {
        return LOAI_SOHUU;
    }

    public void setLOAI_SOHUU(String LOAI_SOHUU) {
        this.LOAI_SOHUU = LOAI_SOHUU;
    }

    public String getMA_CLOAI() {
        return MA_CLOAI;
    }

    public void setMA_CLOAI(String MA_CLOAI) {
        this.MA_CLOAI = MA_CLOAI;
    }

    public String getNGAY_BDONG() {
        return NGAY_BDONG;
    }

    public void setNGAY_BDONG(String NGAY_BDONG) {
        this.NGAY_BDONG = NGAY_BDONG;
    }

    public String getMA_BDONG() {
        return MA_BDONG;
    }

    public void setMA_BDONG(String MA_BDONG) {
        this.MA_BDONG = MA_BDONG;
    }

    public String getNGAY_NHAP() {
        return NGAY_NHAP;
    }

    public void setNGAY_NHAP(String NGAY_NHAP) {
        this.NGAY_NHAP = NGAY_NHAP;
    }

    public String getNGAY_KDINH() {
        return NGAY_KDINH;
    }

    public void setNGAY_KDINH(String NGAY_KDINH) {
        this.NGAY_KDINH = NGAY_KDINH;
    }

    public String getSO_DAY() {
        return SO_DAY;
    }

    public void setSO_DAY(String SO_DAY) {
        this.SO_DAY = SO_DAY;
    }

    public String getVH_CONG() {
        return VH_CONG;
    }

    public void setVH_CONG(String VH_CONG) {
        this.VH_CONG = VH_CONG;
    }

    public String getSO_PHA() {
        return SO_PHA;
    }

    public void setSO_PHA(String SO_PHA) {
        this.SO_PHA = SO_PHA;
    }

    public String getDIEN_AP() {
        return DIEN_AP;
    }

    public void setDIEN_AP(String DIEN_AP) {
        this.DIEN_AP = DIEN_AP;
    }

    public String getDONG_DIEN() {
        return DONG_DIEN;
    }

    public void setDONG_DIEN(String DONG_DIEN) {
        this.DONG_DIEN = DONG_DIEN;
    }

    public int getID_TBL_CTO_PB() {
        return ID_TBL_CTO_PB;
    }

    public void setID_TBL_CTO_PB(int ID_TBL_CTO_PB) {
        this.ID_TBL_CTO_PB = ID_TBL_CTO_PB;
    }

    public String getNGAY_NHAP_MTB() {
        return NGAY_NHAP_MTB;
    }

    public void setNGAY_NHAP_MTB(String NGAY_NHAP_MTB) {
        this.NGAY_NHAP_MTB = NGAY_NHAP_MTB;
    }

    public int getTRANG_THAI_GHIM() {
        return TRANG_THAI_GHIM;
    }

    public void setTRANG_THAI_GHIM(int TRANG_THAI_GHIM) {
        this.TRANG_THAI_GHIM = TRANG_THAI_GHIM;
    }

    public int getTRANG_THAI_CHON() {
        return TRANG_THAI_CHON;
    }

    public void setTRANG_THAI_CHON(int TRANG_THAI_CHON) {
        this.TRANG_THAI_CHON = TRANG_THAI_CHON;
    }
}
