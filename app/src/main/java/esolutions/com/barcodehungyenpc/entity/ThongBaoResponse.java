package esolutions.com.barcodehungyenpc.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VinhNB on 8/31/2017.
 */
public class ThongBaoResponse extends ResponseSoap {
    @SerializedName("Thongbao")
    @Expose
    private transient String Thongbao;

    public String getThongbao() {
        return Thongbao;
    }

    public void setThongbao(String thongbao) {
        this.Thongbao = thongbao;
    }
}
