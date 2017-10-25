package esolutions.com.barcodehungyenpc.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VinhNB on 9/29/2017.
 */

public class DviPBCapDuoiResponse extends ResponseSoap {
    @SerializedName("MA_DVIQLY")
    @Expose
    private String MA_DVIQLY;

    @SerializedName("TEN_DVIQLY")
    @Expose
    private String TEN_DVIQLY;

    @SerializedName("SEARCH")
    @Expose
    private String SEARCH;


    public String getMA_DVIQLY() {
        return MA_DVIQLY;
    }

    public void setMA_DVIQLY(String MA_DVIQLY) {
        this.MA_DVIQLY = MA_DVIQLY;
    }

    public String getTEN_DVIQLY() {
        return TEN_DVIQLY;
    }

    public void setTEN_DVIQLY(String TEN_DVIQLY) {
        this.TEN_DVIQLY = TEN_DVIQLY;
    }

    public String getSEARCH() {
        return SEARCH;
    }

    public void setSEARCH(String SEARCH) {
        this.SEARCH = SEARCH;
    }
}
