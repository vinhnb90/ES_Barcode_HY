package esolutions.com.barcodehungyenpc.entity;

/**
 * Created by VinhNB on 10/18/2017.
 */

public class ThongKe {
    private String mSoBBanTamThoi;
    private int mSoLuongCto;

    public ThongKe(String mSoBBanTamThoi, int mSoLuongCto) {
        this.mSoBBanTamThoi = mSoBBanTamThoi;
        this.mSoLuongCto = mSoLuongCto;
    }

    public String getmSoBBanTamThoi() {
        return mSoBBanTamThoi;
    }

    public void setmSoBBanTamThoi(String mSoBBanTamThoi) {
        this.mSoBBanTamThoi = mSoBBanTamThoi;
    }

    public int getmSoLuongCto() {
        return mSoLuongCto;
    }

    public void setmSoLuongCto(int mSoLuongCto) {
        this.mSoLuongCto = mSoLuongCto;
    }
}
