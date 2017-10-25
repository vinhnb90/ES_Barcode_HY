package esolutions.com.barcodehungyenpc.entity;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class DviPBCapDuoi {
    private int ID;
    private String MA_DVIQLY_CAPTREN;
    private String MA_DVIQLY_CAPDUOI;
    private String SEARCH;

    public DviPBCapDuoi() {
        ID = -1;
    }

    public DviPBCapDuoi(String MA_DVIQLY_CAPTREN, String MA_DVIQLY_CAPDUOI) {
        this.MA_DVIQLY_CAPTREN = MA_DVIQLY_CAPTREN;
        this.MA_DVIQLY_CAPDUOI = MA_DVIQLY_CAPDUOI;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMA_DVIQLY_CAPTREN() {
        return MA_DVIQLY_CAPTREN;
    }

    public void setMA_DVIQLY_CAPTREN(String MA_DVIQLY_CAPTREN) {
        this.MA_DVIQLY_CAPTREN = MA_DVIQLY_CAPTREN;
    }

    public String getMA_DVIQLY_CAPDUOI() {
        return MA_DVIQLY_CAPDUOI;
    }

    public void setMA_DVIQLY_CAPDUOI(String MA_DVIQLY_CAPDUOI) {
        this.MA_DVIQLY_CAPDUOI = MA_DVIQLY_CAPDUOI;
    }

    public String getSEARCH() {
        return SEARCH;
    }

    public void setSEARCH(String SEARCH) {
        this.SEARCH = SEARCH;
    }
}
