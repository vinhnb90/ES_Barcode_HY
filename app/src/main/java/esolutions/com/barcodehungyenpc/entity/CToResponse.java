package esolutions.com.barcodehungyenpc.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VinhNB on 8/31/2017.
 */

public class CToResponse extends ResponseSoap {
    @SerializedName("CHON")
    @Expose
    private int CHON;
    @SerializedName("STT")
    @Expose
    private int STT;
    @SerializedName("MA_CTO")
    @Expose
    private String MA_CTO;
    @SerializedName("SO_CTO")
    @Expose
    private String SO_CTO;
    @SerializedName("MA_DVIQLY")
    @Expose
    private String MA_DVIQLY;
    @SerializedName("MA_CLOAI")
    @Expose
    private String MA_CLOAI;
    @SerializedName("NGAY_NHAP_HT")
    @Expose
    private String NGAY_NHAP_HT;
    @SerializedName("NAM_SX")
    @Expose
    private String NAM_SX;
    @SerializedName("LOAI_SOHUU")
    @Expose
    private String LOAI_SOHUU;
    @SerializedName("TEN_SOHUU")
    @Expose
    private String TEN_SOHUU;
    @SerializedName("MA_BDONG")
    @Expose
    private String MA_BDONG;
    @SerializedName("NGAY_BDONG")
    @Expose
    private String NGAY_BDONG;
    @SerializedName("NGAY_BDONG_HTAI")
    @Expose
    private String NGAY_BDONG_HTAI;
    @SerializedName("SO_PHA")
    @Expose
    private String SO_PHA;
    @SerializedName("SO_DAY")
    @Expose
    private String SO_DAY;
    @SerializedName("DONG_DIEN")
    @Expose
    private String DONG_DIEN;
    @SerializedName("VH_CONG")
    @Expose
    private String VH_CONG;
    @SerializedName("DIEN_AP")
    @Expose
    private String DIEN_AP;
    @SerializedName("HS_NHAN")
    @Expose
    private String HS_NHAN;
    @SerializedName("NGAY_KDINH")
    @Expose
    private String NGAY_KDINH;
    @SerializedName("CHISO_THAO")
    @Expose
    private String CHISO_THAO;
    @SerializedName("HSN")
    @Expose
    private String HSN;

    @SerializedName("NGAY_NHAP")
    @Expose
    private String NGAY_NHAP;
    public int getCHON() {
        return CHON;
    }

    public void setCHON(int CHON) {
        this.CHON = CHON;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
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

    public String getMA_DVIQLY() {
        return MA_DVIQLY;
    }

    public void setMA_DVIQLY(String MA_DVIQLY) {
        this.MA_DVIQLY = MA_DVIQLY;
    }

    public String getMA_CLOAI() {
        return MA_CLOAI;
    }

    public void setMA_CLOAI(String MA_CLOAI) {
        this.MA_CLOAI = MA_CLOAI;
    }

    public String getNGAY_NHAP_HT() {
        return NGAY_NHAP_HT;
    }

    public void setNGAY_NHAP_HT(String NGAY_NHAP_HT) {
        this.NGAY_NHAP_HT = NGAY_NHAP_HT;
    }

    public String getNAM_SX() {
        return NAM_SX;
    }

    public void setNAM_SX(String NAM_SX) {
        this.NAM_SX = NAM_SX;
    }

    public String getLOAI_SOHUU() {
        return LOAI_SOHUU;
    }

    public void setLOAI_SOHUU(String LOAI_SOHUU) {
        this.LOAI_SOHUU = LOAI_SOHUU;
    }

    public String getTEN_SOHUU() {
        return TEN_SOHUU;
    }

    public void setTEN_SOHUU(String TEN_SOHUU) {
        this.TEN_SOHUU = TEN_SOHUU;
    }

    public String getMA_BDONG() {
        return MA_BDONG;
    }

    public void setMA_BDONG(String MA_BDONG) {
        this.MA_BDONG = MA_BDONG;
    }

    public String getNGAY_BDONG() {
        return NGAY_BDONG;
    }

    public void setNGAY_BDONG(String NGAY_BDONG) {
        this.NGAY_BDONG = NGAY_BDONG;
    }

    public String getNGAY_BDONG_HTAI() {
        return NGAY_BDONG_HTAI;
    }

    public void setNGAY_BDONG_HTAI(String NGAY_BDONG_HTAI) {
        this.NGAY_BDONG_HTAI = NGAY_BDONG_HTAI;
    }

    public String getSO_PHA() {
        return SO_PHA;
    }

    public void setSO_PHA(String SO_PHA) {
        this.SO_PHA = SO_PHA;
    }

    public String getSO_DAY() {
        return SO_DAY;
    }

    public void setSO_DAY(String SO_DAY) {
        this.SO_DAY = SO_DAY;
    }

    public String getDONG_DIEN() {
        return DONG_DIEN;
    }

    public void setDONG_DIEN(String DONG_DIEN) {
        this.DONG_DIEN = DONG_DIEN;
    }

    public String getVH_CONG() {
        return VH_CONG;
    }

    public void setVH_CONG(String VH_CONG) {
        this.VH_CONG = VH_CONG;
    }

    public String getDIEN_AP() {
        return DIEN_AP;
    }

    public void setDIEN_AP(String DIEN_AP) {
        this.DIEN_AP = DIEN_AP;
    }

    public String getHS_NHAN() {
        return HS_NHAN;
    }

    public void setHS_NHAN(String HS_NHAN) {
        this.HS_NHAN = HS_NHAN;
    }

    public String getNGAY_KDINH() {
        return NGAY_KDINH;
    }

    public void setNGAY_KDINH(String NGAY_KDINH) {
        this.NGAY_KDINH = NGAY_KDINH;
    }

    public String getCHISO_THAO() {
        return CHISO_THAO;
    }

    public void setCHISO_THAO(String CHISO_THAO) {
        this.CHISO_THAO = CHISO_THAO;
    }

    public String getHSN() {
        return HSN;
    }

    public void setHSN(String HSN) {
        this.HSN = HSN;
    }

    public String getNGAY_NHAP() {
        return NGAY_NHAP;
    }

    public void setNGAY_NHAP(String NGAY_NHAP) {
        this.NGAY_NHAP = NGAY_NHAP;
    }
}