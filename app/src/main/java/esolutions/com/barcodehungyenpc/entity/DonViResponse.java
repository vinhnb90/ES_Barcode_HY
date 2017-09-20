package esolutions.com.barcodehungyenpc.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VinhNB on 8/31/2017.
 */

public class DonViResponse extends ResponseSoap {
    @SerializedName("MA_DVIQLY")
    @Expose
    private String MA_DVIQLY;

    public String getMA_DVIQLY() {
        return MA_DVIQLY;
    }

    public void setMA_DVIQLY(String MA_DVIQLY) {
        this.MA_DVIQLY = MA_DVIQLY;
    }
}
