package esolutions.com.barcodehungyenpc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class SqlQuery {

    //region TBL_CONG_TO query
    public enum TBL_CONG_TO {
        IdCongTo("IdCongTo"),
        MaDienLuc("MaDienLuc"),
        MaChungLoai("MaChungLoai"),
        NamSX("NamSX"),
        SoCto("SoCto"),
        MaCto("MaCto"),
        ChiSoThao("ChiSoThao"),

        GhimCto("GhimCongTo"),
        TaiKhoan("TaiKhoan"),
        NgayGhiDuLieu("NgayGhiDuLieu"),
        //Kiểu công tơ phân bổ = 0, Kiểu công tơ kiểm định = 1
        KieuCongToPhanBo("KieuCongToPhanBo");


        private String mNameCollumn;

        TBL_CONG_TO(String mNameCollumn) {
            this.mNameCollumn = mNameCollumn;
        }

        public String getNameCollumn() {
            return mNameCollumn;
        }

        public static String getNameTable() {
            return "TBL_CONG_TO";
        }
    }

    public static String getCreateTblCongTo() {
        return "CREATE TABLE IF NOT EXISTS " + TBL_CONG_TO.getNameTable() + " (" +
                TBL_CONG_TO.IdCongTo.getNameCollumn() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TBL_CONG_TO.MaDienLuc.getNameCollumn() + " TEXT, " +
                TBL_CONG_TO.MaChungLoai.getNameCollumn() + " TEXT, " +
                TBL_CONG_TO.NamSX.getNameCollumn() + " TEXT, " +
                TBL_CONG_TO.SoCto.getNameCollumn() + " TEXT, " +
                TBL_CONG_TO.MaCto.getNameCollumn() + " TEXT, " +
                TBL_CONG_TO.ChiSoThao.getNameCollumn() + " TEXT, " +
                TBL_CONG_TO.GhimCto.getNameCollumn() + " INTEGER DEFAULT 0, " +
                TBL_CONG_TO.TaiKhoan.getNameCollumn() + " TEXT, " +
                TBL_CONG_TO.NgayGhiDuLieu.getNameCollumn() + " TEXT, " +
                TBL_CONG_TO.KieuCongToPhanBo.getNameCollumn() + " INTEGER DEFAULT 0 " +
                ");";
    }

    public static String getDropTblCongTo() {
        return "DROP TABLE IF EXISTS " + TBL_CONG_TO.getNameTable() + " ;";
    }

    public static String getInsertCongTo() {
        return "INSERT INTO " + TBL_CONG_TO.getNameTable() + " (" +
                TBL_CONG_TO.MaDienLuc.getNameCollumn() + ", " +
                TBL_CONG_TO.MaChungLoai.getNameCollumn() + ", " +
                TBL_CONG_TO.NamSX.getNameCollumn() + ", " +
                TBL_CONG_TO.SoCto.getNameCollumn() + ", " +
                TBL_CONG_TO.MaCto.getNameCollumn() + ", " +
                TBL_CONG_TO.ChiSoThao.getNameCollumn() + ", " +
                TBL_CONG_TO.GhimCto.getNameCollumn() + ", " +
                TBL_CONG_TO.TaiKhoan.getNameCollumn() + ", " +
                TBL_CONG_TO.NgayGhiDuLieu.getNameCollumn() + ", " +
                TBL_CONG_TO.KieuCongToPhanBo.getNameCollumn() +
                ") " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                ";"
                ;
    }

  /*  public static String getSelectCongTo() {
        return "SELECT " +
                TBL_CONG_TO.IdCongTo.getNameCollumn() + ", " +
                TBL_CONG_TO.MaDienLuc.getNameCollumn() + ", " +
                TBL_CONG_TO.MaChungLoai.getNameCollumn() + ", " +
                TBL_CONG_TO.NamSX.getNameCollumn() + ", " +
                TBL_CONG_TO.SoCto.getNameCollumn() + ", " +
                TBL_CONG_TO.MaCto.getNameCollumn() + ", " +
                TBL_CONG_TO.ChiSoThao.getNameCollumn() + ", " +
                TBL_CONG_TO.GhimCto.getNameCollumn() + ", " +
                TBL_CONG_TO.TaiKhoan.getNameCollumn() + ", " +
                TBL_CONG_TO.NgayGhiDuLieu.getNameCollumn() +
                " FROM " +
                TBL_CONG_TO.getNameTable() +
                " WHERE " +
                TBL_CONG_TO.TaiKhoan.getNameCollumn() +
                " = ? "
                ;
    }*/

    public static String getSelectCongTo() {
        return "SELECT " +
                TBL_CONG_TO.IdCongTo.getNameCollumn() + ", " +
                TBL_CONG_TO.MaDienLuc.getNameCollumn() + ", " +
                TBL_CONG_TO.MaChungLoai.getNameCollumn() + ", " +
                TBL_CONG_TO.NamSX.getNameCollumn() + ", " +
                TBL_CONG_TO.SoCto.getNameCollumn() + ", " +
                TBL_CONG_TO.MaCto.getNameCollumn() + ", " +
                TBL_CONG_TO.ChiSoThao.getNameCollumn() + ", " +
                TBL_CONG_TO.GhimCto.getNameCollumn() + ", " +
                TBL_CONG_TO.TaiKhoan.getNameCollumn() + ", " +
                TBL_CONG_TO.NgayGhiDuLieu.getNameCollumn() +
                " FROM " +
                TBL_CONG_TO.getNameTable() +
                " WHERE " +
                TBL_CONG_TO.TaiKhoan.getNameCollumn() +
                " = ? " +
                " AND " +
                TBL_CONG_TO.KieuCongToPhanBo.getNameCollumn() +
                " = ? "
                ;
    }

    public static String getUpdateGhimCto() {
        return "UPDATE " +
                TBL_CONG_TO.getNameTable() +
                " SET " +
                TBL_CONG_TO.GhimCto.getNameCollumn() +
                " = ? " +
                " WHERE " +
                TBL_CONG_TO.IdCongTo.getNameCollumn() +
                " = ? AND " +
                TBL_CONG_TO.TaiKhoan.getNameCollumn() +
                " = ? AND " +
                TBL_CONG_TO.KieuCongToPhanBo.getNameCollumn() +
                " = ?"
                ;
    }

    public static String getDeleteAllCongTo() {
        return "DELETE FROM " +
                TBL_CONG_TO.getNameTable() +
                " WHERE " +
                TBL_CONG_TO.TaiKhoan.getNameCollumn() +
                " = ?"
                ;
    }

    public static String getSelectCongToGhim() {
        return "SELECT " +
                TBL_CONG_TO.IdCongTo.getNameCollumn() + ", " +
                TBL_CONG_TO.MaDienLuc.getNameCollumn() + ", " +
                TBL_CONG_TO.MaChungLoai.getNameCollumn() + ", " +
                TBL_CONG_TO.NamSX.getNameCollumn() + ", " +
                TBL_CONG_TO.SoCto.getNameCollumn() + ", " +
                TBL_CONG_TO.MaCto.getNameCollumn() + ", " +
                TBL_CONG_TO.ChiSoThao.getNameCollumn() + ", " +
                TBL_CONG_TO.GhimCto.getNameCollumn() + ", " +
                TBL_CONG_TO.TaiKhoan.getNameCollumn() + ", " +
                TBL_CONG_TO.NgayGhiDuLieu.getNameCollumn() +
                " FROM " +
                TBL_CONG_TO.getNameTable() +
                " WHERE " +
                TBL_CONG_TO.GhimCto.getNameCollumn() +
                " = 1 AND " +
                TBL_CONG_TO.TaiKhoan.getNameCollumn() +
                " = ? AND " +
                TBL_CONG_TO.KieuCongToPhanBo.getNameCollumn() +
                " = ? "
                ;
    }

    public static String getDeleteCongTo() {
        return "DELETE FROM " +
                TBL_CONG_TO.getNameTable() +
                " WHERE " +
                TBL_CONG_TO.IdCongTo.getNameCollumn()
                + " = ?  AND " +
                TBL_CONG_TO.TaiKhoan.getNameCollumn() +
                " = ?"
                ;
    }
    //endregion
}
