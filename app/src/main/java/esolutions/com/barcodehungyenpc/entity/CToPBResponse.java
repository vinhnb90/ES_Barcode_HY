package esolutions.com.barcodehungyenpc.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VinhNB on 8/31/2017.
 */

public class CToPBResponse extends ResponseSoap {
    @SerializedName("CHON")
    @Expose
    private int CHON;

    @SerializedName("MA_DVIQLY")
    @Expose
    private String MA_DVIQLY;

    @SerializedName("NAM_SX")
    @Expose
    private String NAM_SX;

    @SerializedName("MA_CTO")
    @Expose
    private String MA_CTO;

    @SerializedName("SO_CTO")
    @Expose
    private String SO_CTO;

    @SerializedName("LOAI_SOHUU")
    @Expose
    private int LOAI_SOHUU;

    @SerializedName("LOAISOHUU")
    @Expose
    private String LOAISOHUU;

    @SerializedName("MA_CLOAI")
    @Expose
    private int MA_CLOAI;

    @SerializedName("NGAY_BDONG")
    @Expose
    private String NGAY_BDONG;

    @SerializedName("MA_BDONG")
    @Expose
    private String MA_BDONG;

    @SerializedName("ID_BBAN_KHO")
    @Expose
    private int ID_BBAN_KHO;

    @SerializedName("NGAY_NHAP")
    @Expose
    private String NGAY_NHAP;

    @SerializedName("NGAY_NHAP_HTHI")
    @Expose
    private String NGAY_NHAP_HTHI;

    @SerializedName("MA_NVIEN")
    @Expose
    private String MA_NVIEN;

    @SerializedName("SO_BBAN")
    @Expose
    private String SO_BBAN;

    @SerializedName("HS_NHAN")
    @Expose
    private double HS_NHAN;

    @SerializedName("ID_BBAN_KDINH")
    @Expose
    private int ID_BBAN_KDINH;

    @SerializedName("SO_BBAN_KDINH")
    @Expose
    private String SO_BBAN_KDINH;

    @SerializedName("MA_NVIENKDINH")
    @Expose
    private String MA_NVIENKDINH;

    @SerializedName("NGAY_GUIKD")
    @Expose
    private String NGAY_GUIKD;

    @SerializedName("NGAY_KDINH")
    @Expose
    private String NGAY_KDINH;

    @SerializedName("NGAY_KDINH_HTHI")
    @Expose
    private String NGAY_KDINH_HTHI;

    @SerializedName("VH_CONG")
    @Expose
    private String VH_CONG;

    @SerializedName("SO_PHA")
    @Expose
    private int SO_PHA;

    @SerializedName("LOAI_CTO")
    @Expose
    private String LOAI_CTO;

    @SerializedName("SO_DAY")
    @Expose
    private int SO_DAY;

    @SerializedName("SO_CSO")
    @Expose
    private int SO_CSO;

    @SerializedName("DIEN_AP")
    @Expose
    private String DIEN_AP;

    @SerializedName("MA_HANG")
    @Expose
    private String MA_HANG;

    @SerializedName("CAP_CXAC")
    @Expose
    private String CAP_CXAC;

    @SerializedName("MA_NUOC")
    @Expose
    private String MA_NUOC;

    @SerializedName("DONG_DIEN")
    @Expose
    private String DONG_DIEN;

    public int getCHON() {
        return CHON;
    }

    public void setCHON(int CHON) {
        this.CHON = CHON;
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

    public int getLOAI_SOHUU() {
        return LOAI_SOHUU;
    }

    public void setLOAI_SOHUU(int LOAI_SOHUU) {
        this.LOAI_SOHUU = LOAI_SOHUU;
    }

    public String getLOAISOHUU() {
        return LOAISOHUU;
    }

    public void setLOAISOHUU(String LOAISOHUU) {
        this.LOAISOHUU = LOAISOHUU;
    }

    public int getMA_CLOAI() {
        return MA_CLOAI;
    }

    public void setMA_CLOAI(int MA_CLOAI) {
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

    public int getID_BBAN_KHO() {
        return ID_BBAN_KHO;
    }

    public void setID_BBAN_KHO(int ID_BBAN_KHO) {
        this.ID_BBAN_KHO = ID_BBAN_KHO;
    }

    public String getNGAY_NHAP() {
        return NGAY_NHAP;
    }

    public void setNGAY_NHAP(String NGAY_NHAP) {
        this.NGAY_NHAP = NGAY_NHAP;
    }

    public String getNGAY_NHAP_HTHI() {
        return NGAY_NHAP_HTHI;
    }

    public void setNGAY_NHAP_HTHI(String NGAY_NHAP_HTHI) {
        this.NGAY_NHAP_HTHI = NGAY_NHAP_HTHI;
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

    public double getHS_NHAN() {
        return HS_NHAN;
    }

    public void setHS_NHAN(double HS_NHAN) {
        this.HS_NHAN = HS_NHAN;
    }

    public int getID_BBAN_KDINH() {
        return ID_BBAN_KDINH;
    }

    public void setID_BBAN_KDINH(int ID_BBAN_KDINH) {
        this.ID_BBAN_KDINH = ID_BBAN_KDINH;
    }

    public String getSO_BBAN_KDINH() {
        return SO_BBAN_KDINH;
    }

    public void setSO_BBAN_KDINH(String SO_BBAN_KDINH) {
        this.SO_BBAN_KDINH = SO_BBAN_KDINH;
    }

    public String getMA_NVIENKDINH() {
        return MA_NVIENKDINH;
    }

    public void setMA_NVIENKDINH(String MA_NVIENKDINH) {
        this.MA_NVIENKDINH = MA_NVIENKDINH;
    }

    public String getNGAY_GUIKD() {
        return NGAY_GUIKD;
    }

    public void setNGAY_GUIKD(String NGAY_GUIKD) {
        this.NGAY_GUIKD = NGAY_GUIKD;
    }

    public String getNGAY_KDINH() {
        return NGAY_KDINH;
    }

    public void setNGAY_KDINH(String NGAY_KDINH) {
        this.NGAY_KDINH = NGAY_KDINH;
    }

    public String getNGAY_KDINH_HTHI() {
        return NGAY_KDINH_HTHI;
    }

    public void setNGAY_KDINH_HTHI(String NGAY_KDINH_HTHI) {
        this.NGAY_KDINH_HTHI = NGAY_KDINH_HTHI;
    }

    public String getVH_CONG() {
        return VH_CONG;
    }

    public void setVH_CONG(String VH_CONG) {
        this.VH_CONG = VH_CONG;
    }

    public int getSO_PHA() {
        return SO_PHA;
    }

    public void setSO_PHA(int SO_PHA) {
        this.SO_PHA = SO_PHA;
    }

    public String getLOAI_CTO() {
        return LOAI_CTO;
    }

    public void setLOAI_CTO(String LOAI_CTO) {
        this.LOAI_CTO = LOAI_CTO;
    }

    public int getSO_DAY() {
        return SO_DAY;
    }

    public void setSO_DAY(int SO_DAY) {
        this.SO_DAY = SO_DAY;
    }

    public int getSO_CSO() {
        return SO_CSO;
    }

    public void setSO_CSO(int SO_CSO) {
        this.SO_CSO = SO_CSO;
    }

    public String getDIEN_AP() {
        return DIEN_AP;
    }

    public void setDIEN_AP(String DIEN_AP) {
        this.DIEN_AP = DIEN_AP;
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

    public String getDONG_DIEN() {
        return DONG_DIEN;
    }

    public void setDONG_DIEN(String DONG_DIEN) {
        this.DONG_DIEN = DONG_DIEN;
    }
}
