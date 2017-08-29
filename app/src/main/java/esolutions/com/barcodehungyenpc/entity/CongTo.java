package esolutions.com.barcodehungyenpc.entity;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class CongTo {
    private int mIdCto;
    private String mMaDLuc;
    private String mMaChungLoai;
    private String mNamSX;
    private String mSoCto;
    private String mMaCto;
    private String mChiSoThao;

    private int mGhimCto;
    private String mTaiKhoan;

    private String mNgayGhiDuLieu;
    private int mKieuCongTo;

    public CongTo() {
        mIdCto = -1;
        mGhimCto = -1;
        mKieuCongTo = -1;
    }

    public void setmIdCto(int mIdCto) {
        this.mIdCto = mIdCto;
    }

    public int getmIdCto() {
        return mIdCto;
    }

    public String getmMaDLuc() {
        return mMaDLuc;
    }

    public void setmMaDLuc(String mMaDLuc) {
        this.mMaDLuc = mMaDLuc;
    }

    public String getmMaChungLoai() {
        return mMaChungLoai;
    }

    public void setmMaChungLoai(String mMaChungLoai) {
        this.mMaChungLoai = mMaChungLoai;
    }

    public String getmNamSX() {
        return mNamSX;
    }

    public void setmNamSX(String mNamSX) {
        this.mNamSX = mNamSX;
    }

    public String getmSoCto() {
        return mSoCto;
    }

    public void setmSoCto(String mSoCto) {
        this.mSoCto = mSoCto;
    }

    public String getmMaCto() {
        return mMaCto;
    }

    public void setmMaCto(String mMaCto) {
        this.mMaCto = mMaCto;
    }

    public String getmChiSoThao() {
        return mChiSoThao;
    }

    public void setmChiSoThao(String mChiSoThao) {
        this.mChiSoThao = mChiSoThao;
    }

    public int getmGhimCto() {
        return mGhimCto;
    }

    public void setmGhimCto(int mGhimCto) {
        this.mGhimCto = mGhimCto;
    }

    public String getmTaiKhoan() {
        return mTaiKhoan;
    }

    public void setmTaiKhoan(String mTaiKhoan) {
        this.mTaiKhoan = mTaiKhoan;
    }

    public String getmNgayGhiDuLieu() {
        return mNgayGhiDuLieu;
    }

    public void setmNgayGhiDuLieu(String mNgayGhiDuLieu) {
        this.mNgayGhiDuLieu = mNgayGhiDuLieu;
    }

    public int getmKieuCongTo() {
        return mKieuCongTo;
    }

    public void setmKieuCongTo(int mKieuCongTo) {
        this.mKieuCongTo = mKieuCongTo;
    }
}
