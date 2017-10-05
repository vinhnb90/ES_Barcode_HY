package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class Update_GuiPB_CTO implements KvmSerializable {
    /**
     *
     */
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
    private String NGAY_NHAP_MTB;

    //thêm trường
    private int ID_TBL_CTO_PB;

    public Update_GuiPB_CTO(String ID_BBAN_KHO, String NGAY_NHAP_HTHONG, String MA_NVIEN, String SO_BBAN, String ID_BBAN_KDINH, String NGAY_GUIKD, String NGAY_KDINH_TH, String LOAI_CTO, String SO_CSO, String MA_HANG, String CAP_CXAC, String MA_NUOC, String ACTION, int CHON, String HS_NHAN, String MA_DVIQLY, String NAM_SX, String MA_CTO, String SO_CTO, String LOAI_SOHUU, String MA_CLOAI, String NGAY_BDONG, String MA_BDONG, String NGAY_NHAP, String NGAY_KDINH, String SO_DAY, String VH_CONG, String SO_PHA, String DIEN_AP, String DONG_DIEN, String NGAY_NHAP_MTB, int ID_TBL_CTO_PB) {
        this.ID_BBAN_KHO = ID_BBAN_KHO;
        this.NGAY_NHAP_HTHONG = NGAY_NHAP_HTHONG;
        this.MA_NVIEN = MA_NVIEN;
        this.SO_BBAN = SO_BBAN;
        this.ID_BBAN_KDINH = ID_BBAN_KDINH;
        this.NGAY_GUIKD = NGAY_GUIKD;
        this.NGAY_KDINH_TH = NGAY_KDINH_TH;
        this.LOAI_CTO = LOAI_CTO;
        this.SO_CSO = SO_CSO;
        this.MA_HANG = MA_HANG;
        this.CAP_CXAC = CAP_CXAC;
        this.MA_NUOC = MA_NUOC;
        this.ACTION = ACTION;
        this.CHON = CHON;
        this.HS_NHAN = HS_NHAN;
        this.MA_DVIQLY = MA_DVIQLY;
        this.NAM_SX = NAM_SX;
        this.MA_CTO = MA_CTO;
        this.SO_CTO = SO_CTO;
        this.LOAI_SOHUU = LOAI_SOHUU;
        this.MA_CLOAI = MA_CLOAI;
        this.NGAY_BDONG = NGAY_BDONG;
        this.MA_BDONG = MA_BDONG;
        this.NGAY_NHAP = NGAY_NHAP;
        this.NGAY_KDINH = NGAY_KDINH;
        this.SO_DAY = SO_DAY;
        this.VH_CONG = VH_CONG;
        this.SO_PHA = SO_PHA;
        this.DIEN_AP = DIEN_AP;
        this.DONG_DIEN = DONG_DIEN;
        this.NGAY_NHAP_MTB = NGAY_NHAP_MTB;
        this.ID_TBL_CTO_PB = ID_TBL_CTO_PB;
    }

    @Override
    public Object getProperty(int index) {
        switch (index) {
            case 0:
                return ID_BBAN_KHO;
            case 1:
                return NGAY_NHAP_HTHONG;
            case 2:
                return MA_NVIEN;
            case 3:
                return SO_BBAN;
            case 4:
                return ID_BBAN_KDINH;
            case 5:
                return NGAY_GUIKD;
            case 6:
                return NGAY_KDINH_TH;
            case 7:
                return LOAI_CTO;
            case 8:
                return SO_CSO;
            case 9:
                return MA_HANG;
            case 10:
                return CAP_CXAC;
            case 11:
                return MA_NUOC;
            case 12:
                return ACTION;
            case 13:
                return CHON;
            case 14:
                return HS_NHAN;
            case 15:
                return MA_DVIQLY;
            case 16:
                return NAM_SX;
            case 17:
                return MA_CTO;
            case 18:
                return SO_CTO;
            case 19:
                return LOAI_SOHUU;
            case 20:
                return MA_CLOAI;
            case 21:
                return NGAY_BDONG;
            case 22:
                return MA_BDONG;
            case 23:
                return NGAY_NHAP;
            case 24:
                return NGAY_KDINH;
            case 25:
                return SO_DAY;
            case 26:
                return VH_CONG;
            case 27:
                return SO_PHA;
            case 28:
                return DIEN_AP;
            case 29:
                return DONG_DIEN;
            case 30:
                return NGAY_NHAP_MTB;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 31;
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        info.type = PropertyInfo.STRING_CLASS;
        switch (index) {
            case 0:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "ID_BBAN_KHO";
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "NGAY_NHAP_HTHONG";
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "MA_NVIEN";
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "SO_BBAN";
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "ID_BBAN_KDINH";
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "NGAY_GUIKD";
            case 6:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "NGAY_KDINH_TH";
            case 7:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "LOAI_CTO";
            case 8:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "SO_CSO";
            case 9:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "MA_HANG";
            case 10:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "CAP_CXAC";
            case 11:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "MA_NUOC";
            case 12:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "ACTION";
            case 13:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "CHON";
            case 14:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "HS_NHAN";
            case 15:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "MA_DVIQLY";
            case 16:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "NAM_SX";
            case 17:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "MA_CTO";
            case 18:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "SO_CTO";
            case 19:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "LOAI_SOHUU";
            case 20:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "MA_CLOAI";
            case 21:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "NGAY_BDONG";
            case 22:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "MA_BDONG";
            case 23:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "NGAY_NHAP";
            case 24:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "NGAY_KDINH";
            case 25:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "SO_DAY";
            case 26:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "VH_CONG";
            case 27:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "SO_PHA";
            case 28:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "DIEN_AP";
            case 29:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "DONG_DIEN";
            case 30:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "NGAY_NHAP_MTB";
            default:
                break;
        }
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
            case 0:
                ID_BBAN_KHO = value.toString();
                break;
            case 1:
                NGAY_NHAP_HTHONG = value.toString();
                break;
            case 2:
                MA_NVIEN = value.toString();
                break;
            case 3:
                SO_BBAN = value.toString();
                break;
            case 4:
                ID_BBAN_KDINH = value.toString();
                break;
            case 5:
                NGAY_GUIKD = value.toString();
                break;
            case 6:
                NGAY_KDINH_TH = value.toString();
                break;
            case 7:
                LOAI_CTO = value.toString();
                break;
            case 8:
                SO_CSO = value.toString();
                break;
            case 9:
                MA_HANG = value.toString();
                break;
            case 10:
                CAP_CXAC = value.toString();
                break;
            case 11:
                MA_NUOC = value.toString();
                break;
            case 12:
                ACTION = value.toString();
                break;
            case 13:
                CHON = ((Integer) value).intValue();
                break;
            case 14:
                HS_NHAN = value.toString();
                break;
            case 15:
                MA_DVIQLY = value.toString();
                break;
            case 16:
                NAM_SX = value.toString();
                break;
            case 17:
                MA_CTO = value.toString();
                break;
            case 18:
                SO_CTO = value.toString();
                break;
            case 19:
                LOAI_SOHUU = value.toString();
                break;
            case 20:
                MA_CLOAI = value.toString();
                break;
            case 21:
                NGAY_BDONG = value.toString();
                break;
            case 22:
                MA_BDONG = value.toString();
                break;
            case 23:
                NGAY_NHAP = value.toString();
                break;
            case 24:
                NGAY_KDINH = value.toString();
                break;
            case 25:
                SO_DAY = value.toString();
                break;
            case 26:
                VH_CONG = value.toString();
                break;
            case 27:
                SO_PHA = value.toString();
                break;
            case 28:
                DIEN_AP = value.toString();
                break;
            case 29:
                DONG_DIEN = value.toString();
                break;
            case 30:
                NGAY_NHAP_MTB = value.toString();
                break;
            default:

        }
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

    public String getNGAY_NHAP_MTB() {
        return NGAY_NHAP_MTB;
    }

    public void setNGAY_NHAP_MTB(String NGAY_NHAP_MTB) {
        this.NGAY_NHAP_MTB = NGAY_NHAP_MTB;
    }

    public int getID_TBL_CTO_PB() {
        return ID_TBL_CTO_PB;
    }

    public void setID_TBL_CTO_PB(int ID_TBL_CTO_PB) {
        this.ID_TBL_CTO_PB = ID_TBL_CTO_PB;
    }
}