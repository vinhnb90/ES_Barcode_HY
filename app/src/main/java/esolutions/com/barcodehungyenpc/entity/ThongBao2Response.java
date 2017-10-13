package esolutions.com.barcodehungyenpc.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VinhNB on 8/31/2017.
 */
public class ThongBao2Response extends ResponseSoap {
    @SerializedName("thongbao")
    @Expose
    private transient String thongbao;

    public String getThongbao() {
        return thongbao;
    }

    public void setThongbao(String thongbao) {
        this.thongbao = thongbao;
    }
}
