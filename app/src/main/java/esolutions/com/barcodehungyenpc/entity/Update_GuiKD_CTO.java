package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;
import java.util.Vector;

public class Update_GuiKD_CTO implements KvmSerializable {
    /**
     *
     */
    private int CHON;
    private int STT;
    private String MA_CTO;

    private String SO_CTO;
    private String MA_CLOAI;
    private String NGAY_NHAP_HT;
    private String NGAY_NHAP;
    private String NAM_SX;
    private String LOAI_SOHUU;
    private String TEN_SOHUU;
    private String MA_BDONG;
    private String NGAY_BDONG;
    private String NGAY_BDONG_HTAI;
    private String SO_PHA;
    private String SO_DAY;
    private String DONG_DIEN;
    private String VH_CONG;
    private String DIEN_AP;
    private String HS_NHAN;
    private String NGAY_KDINH;
    private String CHISO_THAO;
    private String HSN;
    private String NGAY_GUI_GKDCT_MTB;

    public Update_GuiKD_CTO(int CHON, int STT, String MA_CTO, String SO_CTO, String MA_CLOAI, String NGAY_NHAP_HT, String NGAY_NHAP, String NAM_SX, String LOAI_SOHUU, String TEN_SOHUU, String MA_BDONG, String NGAY_BDONG, String NGAY_BDONG_HTAI, String SO_PHA, String SO_DAY, String DONG_DIEN, String VH_CONG, String DIEN_AP, String HS_NHAN, String NGAY_KDINH, String CHISO_THAO, String HSN, String NGAY_GUI_GKDCT_MTB) {
        this.CHON = CHON;
        this.STT = STT;
        this.MA_CTO = MA_CTO;
        this.SO_CTO = SO_CTO;
        this.MA_CLOAI = MA_CLOAI;
        this.NGAY_NHAP_HT = NGAY_NHAP_HT;
        this.NGAY_NHAP = NGAY_NHAP;
        this.NAM_SX = NAM_SX;
        this.LOAI_SOHUU = LOAI_SOHUU;
        this.TEN_SOHUU = TEN_SOHUU;
        this.MA_BDONG = MA_BDONG;
        this.NGAY_BDONG = NGAY_BDONG;
        this.NGAY_BDONG_HTAI = NGAY_BDONG_HTAI;
        this.SO_PHA = SO_PHA;
        this.SO_DAY = SO_DAY;
        this.DONG_DIEN = DONG_DIEN;
        this.VH_CONG = VH_CONG;
        this.DIEN_AP = DIEN_AP;
        this.HS_NHAN = HS_NHAN;
        this.NGAY_KDINH = NGAY_KDINH;
        this.CHISO_THAO = CHISO_THAO;
        this.HSN = HSN;
        this.NGAY_GUI_GKDCT_MTB = NGAY_GUI_GKDCT_MTB;
    }

    @Override
    public Object getProperty(int index) {
        switch (index) {
            case 0:
                return CHON;
            case 1:
                return STT;
            case 2:
                return MA_CTO;
            case 3:
                return SO_CTO;
            case 4:
                return MA_CLOAI;
            case 5:
                return NGAY_NHAP_HT;
            case 6:
                return NGAY_NHAP;
            case 7:
                return NAM_SX;
            case 8:
                return LOAI_SOHUU;
            case 9:
                return TEN_SOHUU;
            case 10:
                return MA_BDONG;
            case 11:
                return NGAY_BDONG;
            case 12:
                return NGAY_BDONG_HTAI;
            case 13:
                return SO_PHA;
            case 14:
                return SO_DAY;
            case 15:
                return DONG_DIEN;
            case 16:
                return VH_CONG;
            case 17:
                return DIEN_AP;
            case 18:
                return HS_NHAN;
            case 19:
                return NGAY_KDINH;
            case 20:
                return CHISO_THAO;
            case 21:
                return HSN;
            case 22:
                return NGAY_GUI_GKDCT_MTB;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 23;
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        info.type = PropertyInfo.STRING_CLASS;
        switch (index) {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "CHON";
                break;
            case 1:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "STT";
                break;
            case 2:
                info.name = "MA_CTO";
                break;
            case 3:
                info.name = "SO_CTO";
                break;
            case 4:
                info.name = "MA_CLOAI";
                break;
            case 5:
                info.name = "NGAY_NHAP_HT";
                break;
            case 6:
                info.name = "NGAY_NHAP";
                break;
            case 7:
                info.name = "NAM_SX";
                break;
            case 8:
                info.name = "LOAI_SOHUU";
                break;
            case 9:
                info.name = "TEN_SOHUU";
                break;
            case 10:
                info.name = "MA_BDONG";
                break;
            case 11:
                info.name = "NGAY_BDONG";
                break;
            case 12:
                info.name = "NGAY_BDONG_HTAI";
                break;
            case 13:
                info.name = "SO_PHA";
                break;
            case 14:
                info.name = "SO_DAY";
                break;
            case 15:
                info.name = "DONG_DIEN";
                break;
            case 16:
                info.name = "VH_CONG";
                break;
            case 17:
                info.name = "DIEN_AP";
                break;
            case 18:
                info.name = "HS_NHAN";
                break;
            case 19:
                info.name = "NGAY_KDINH";
                break;
            case 20:
                info.name = "CHISO_THAO";
                break;
            case 21:
                info.name = "HSN";
                break;
            case 22:
                info.name = "NGAY_GUI_GKDCT_MTB";
                break;
            default:
                break;
        }
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
            case 0:
                CHON = ((Integer) value).intValue();
                break;
            case 1:
                STT = ((Integer) value).intValue();
                break;
            case 2:
                MA_CTO = value.toString();
                break;
            case 3:
                SO_CTO = value.toString();
                break;
            case 4:
                MA_CLOAI = value.toString();
                break;
            case 5:
                NGAY_NHAP_HT = value.toString();
                break;
            case 6:
                NGAY_NHAP = value.toString();
                break;
            case 7:
                NAM_SX = value.toString();
                break;
            case 8:
                LOAI_SOHUU = value.toString();
                break;
            case 9:
                TEN_SOHUU = value.toString();
                break;
            case 10:
                MA_BDONG = value.toString();
                break;
            case 11:
                NGAY_BDONG = value.toString();
                break;
            case 12:
                NGAY_BDONG_HTAI = value.toString();
                break;
            case 13:
                SO_PHA = value.toString();
                break;
            case 14:
                SO_DAY = value.toString();
                break;
            case 15:
                DONG_DIEN = value.toString();
                break;
            case 16:
                VH_CONG = value.toString();
                break;
            case 17:
                DIEN_AP = value.toString();
                break;
            case 18:
                HS_NHAN = value.toString();
                break;
            case 19:
                NGAY_KDINH = value.toString();
                break;
            case 20:
                CHISO_THAO = value.toString();
                break;
            case 21:
                HSN = value.toString();
                break;
            case 22:
                NGAY_GUI_GKDCT_MTB = value.toString();
                break;
            default:
                break;
        }
    }

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

    public String getNGAY_NHAP() {
        return NGAY_NHAP;
    }

    public void setNGAY_NHAP(String NGAY_NHAP) {
        this.NGAY_NHAP = NGAY_NHAP;
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

    public String getNGAY_GUI_GKDCT_MTB() {
        return NGAY_GUI_GKDCT_MTB;
    }

    public void setNGAY_GUI_GKDCT_MTB(String NGAY_GUI_GKDCT_MTB) {
        this.NGAY_GUI_GKDCT_MTB = NGAY_GUI_GKDCT_MTB;
    }
}