package esolutions.com.barcodehungyenpc.entity;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class DienLuc {
    private int ID;
    private String MA_DVIQLY;

    public DienLuc() {
        ID = -1;
    }

    public DienLuc(String MA_DVIQLY) {
        this.MA_DVIQLY = MA_DVIQLY;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMA_DVIQLY() {
        return MA_DVIQLY;
    }

    public void setMA_DVIQLY(String MA_DVIQLY) {
        this.MA_DVIQLY = MA_DVIQLY;
    }
}
