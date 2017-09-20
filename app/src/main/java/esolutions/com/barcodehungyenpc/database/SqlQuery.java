package esolutions.com.barcodehungyenpc.database;

import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class SqlQuery {

    //region TBL_CTO_PB query
    public enum TBL_CTO_GUI_KD {
        STT("STT"),
        MA_DVIQLY("MA_DVIQLY"),
        CLOAI("CLOAI"),
        NAMSX("NAMSX"),
        SO_CTO("SO_CTO"),
        MA_CTO("MA_CTO"),
        CHISO_THAO("CHISO_THAO"),

        //        GhimCto("GhimCongTo"),
//        TaiKhoan("TaiKhoan"),
        NGAY_NHAP("NGAY_NHAP"),
        //Trạng thái chưa ghim = 0, đã ghim = 1
        TRANG_THAI_GHIM("TRANG_THAI_GHIM"),
        //Trạng thái chưa chọn = 0, đã chọn = 1
        TRANG_THAI_CHON("TRANG_THAI_CHON");

//        KieuCongToPhanBo("KieuCongToPhanBo");


        private String mNameCollumn;

        TBL_CTO_GUI_KD(String mNameCollumn) {
            this.mNameCollumn = mNameCollumn;
        }

        public String getNameCollumn() {
            return mNameCollumn;
        }

        public static String getNameTable() {
            return "TBL_CTO_GUI_KD";
        }
    }

    public enum TBL_CTO_PB {
        STT("STT"),
        MA_DVIQLY("MA_DVIQLY"),
        CLOAI("CLOAI"),
        NAMSX("NAMSX"),
        SO_CTO("SO_CTO"),
        MA_CTO("MA_CTO"),
        CHISO_THAO("CHISO_THAO"),

        //        GhimCto("GhimCongTo"),
//        TaiKhoan("TaiKhoan"),
        NGAY_NHAP("NGAY_NHAP"),
        TRANG_THAI_GHIM("TRANG_THAI_GHIM"),
        //Trạng thái chưa chọn = 0, đã chọn = 1
        TRANG_THAI_CHON("TRANG_THAI_CHON");
        //Kiểu công tơ phân bổ = 0, Kiểu công tơ kiểm định = 1
//        KieuCongToPhanBo("KieuCongToPhanBo");


        private String mNameCollumn;

        TBL_CTO_PB(String mNameCollumn) {
            this.mNameCollumn = mNameCollumn;
        }

        public String getNameCollumn() {
            return mNameCollumn;
        }

        public static String getNameTable() {
            return "TBL_CTO_PB";
        }
    }

    public enum TBL_DIENLUC {
        ID("ID"),
        MA_DVIQLY("MA_DVIQLY"),;


        private String mNameCollumn;

        TBL_DIENLUC(String mNameCollumn) {
            this.mNameCollumn = mNameCollumn;
        }

        public String getNameCollumn() {
            return mNameCollumn;
        }

        public static String getNameTable() {
            return "TBL_DIENLUC";
        }
    }

    public static String getCreateTBL_CTO_GUI_KD() {
        return "CREATE TABLE IF NOT EXISTS " + TBL_CTO_GUI_KD.getNameTable() + " (" +
                TBL_CTO_GUI_KD.STT.getNameCollumn() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.CLOAI.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.NAMSX.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.SO_CTO.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.CHISO_THAO.getNameCollumn() + " TEXT, " +

//                TBL_CTO_GUI_KD.TaiKhoan.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() + " INTEGER DEFAULT 0, " +
                TBL_CTO_GUI_KD.TRANG_THAI_CHON.getNameCollumn() + " INTEGER DEFAULT 0" +
                ");";
    }

    public static String getCreateTBL_CTO_PB() {
        return "CREATE TABLE IF NOT EXISTS " + TBL_CTO_PB.getNameTable() + " (" +
                TBL_CTO_PB.STT.getNameCollumn() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TBL_CTO_PB.MA_DVIQLY.getNameCollumn() + " TEXT, " +
                TBL_CTO_PB.CLOAI.getNameCollumn() + " TEXT, " +
                TBL_CTO_PB.NAMSX.getNameCollumn() + " TEXT, " +
                TBL_CTO_PB.SO_CTO.getNameCollumn() + " TEXT, " +
                TBL_CTO_PB.MA_CTO.getNameCollumn() + " TEXT, " +
                TBL_CTO_PB.CHISO_THAO.getNameCollumn() + " TEXT, " +
//                TBL_CTO_PB.GhimCto.getNameCollumn() + " INTEGER DEFAULT 0, " +
//                TBL_CTO_PB.TaiKhoan.getNameCollumn() + " TEXT, " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() + " TEXT, " +
                TBL_CTO_PB.TRANG_THAI_GHIM.getNameCollumn() + " INTEGER DEFAULT 0, " +
                TBL_CTO_PB.TRANG_THAI_CHON.getNameCollumn() + " INTEGER DEFAULT 0" +
//                ", " +
//                TBL_CTO_PB.KieuCongToPhanBo.getNameCollumn() + " INTEGER DEFAULT 0 " +
                ");";
    }

    public static String getCreateTBL_DIENLUC() {
        return "CREATE TABLE IF NOT EXISTS " + TBL_DIENLUC.getNameTable() + " (" +
                TBL_DIENLUC.ID.getNameCollumn() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TBL_DIENLUC.MA_DVIQLY.getNameCollumn() + " TEXT" +
                ");";
    }

    public static String getDropTBL_CTO_PB() {
        return "DROP TABLE IF EXISTS " + TBL_CTO_PB.getNameTable() + " ;";
    }

    public static String getDropTBL_CTO_GUI_KD() {
        return "DROP TABLE IF EXISTS " + TBL_CTO_GUI_KD.getNameTable() + " ;";
    }

    public static String getDropTBL_DIENLUC() {
        return "DROP TABLE IF EXISTS " + TBL_DIENLUC.getNameTable() + " ;";
    }

    public static String getInsertTBL_CTO_PB() {
        return "INSERT INTO " + TBL_CTO_PB.getNameTable() + " (" +
                TBL_CTO_PB.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_PB.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_PB.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_PB.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.CHISO_THAO.getNameCollumn() + ", " +
//                TBL_CTO_PB.GhimCto.getNameCollumn() + ", " +
//                TBL_CTO_PB.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() +
//                ", " +
//                TBL_CTO_PB.KieuCongToPhanBo.getNameCollumn() +
                ") " + "VALUES (?, ?, ?, ?, ?, ?, ?" +
//                ", ?, ?" +
                ")" +
                ";"
                ;
    }

    public static String getInsertTBL_CTO_GUI_KD() {
        return "INSERT INTO " + TBL_CTO_GUI_KD.getNameTable() + " (" +
                TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.CHISO_THAO.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.TRANG_THAI_CHON.getNameCollumn() +
                ") " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?" +
//                ", ?, ?, ?" +
                ")" +
                ";"
                ;
    }

  /*  public static String getSelectTBL_CTO_PB() {
        return "SELECT " +
                TBL_CTO_PB.STT.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_PB.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_PB.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_PB.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.CHISO_THAO.getNameCollumn() + ", " +
                TBL_CTO_PB.GhimCto.getNameCollumn() + ", " +
                TBL_CTO_PB.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() +
                " FROM " +
                TBL_CTO_PB.getNameTable() +
                " WHERE " +
                TBL_CTO_PB.TaiKhoan.getNameCollumn() +
                " = ? "
                ;
    }*/

    public static String getSelectTBL_CTO_PB() {
        return "SELECT " +
                TBL_CTO_PB.STT.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_PB.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_PB.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_PB.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.CHISO_THAO.getNameCollumn() + ", " +
//                TBL_CTO_PB.GhimCto.getNameCollumn() + ", " +
//                TBL_CTO_PB.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() +
                " FROM " +
                TBL_CTO_PB.getNameTable()
                ;
    }

    public static String getAllCongToByDatePB() {
        return "SELECT " +
                TBL_CTO_PB.STT.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_PB.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_PB.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_PB.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.CHISO_THAO.getNameCollumn() + ", " +
//                TBL_CTO_PB.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() + ", " +
                TBL_CTO_PB.TRANG_THAI_GHIM.getNameCollumn() +
                " FROM " +
                TBL_CTO_PB.getNameTable() +
                " WHERE " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() +
                " = ? "
                ;
    }


//    public static String getSelectTBL_CTO_GUI_KD() {
//        return "SELECT " +
//                TBL_CTO_GUI_KD.STT.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.CLOAI.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.NAMSX.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.SO_CTO.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.CHISO_THAO.getNameCollumn() + ", " +
////                TBL_CTO_GUI_KD.TaiKhoan.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() +
//                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() +
//                " FROM " +
//                TBL_CTO_GUI_KD.getNameTable() +
//                " WHERE " +
//                TBL_CTO_GUI_KD.NGAY_NHAP +
//                " = ? "
//                ;
//    }

    public static String getByDateAllCongToGhimAndChonKD() {
        return "SELECT " +
                TBL_CTO_GUI_KD.STT.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.CHISO_THAO.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.TRANG_THAI_CHON.getNameCollumn() +
                " FROM " +
                TBL_CTO_GUI_KD.getNameTable() +
                " WHERE " +
                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() +
                " = ? " +
                " AND " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM +
                " = " + Common.TRANG_THAI_GHIM.DA_GHIM.getCode() +
                " AND " +
                TBL_CTO_GUI_KD.TRANG_THAI_CHON +
                " = " + Common.TRANG_THAI_CHON.DA_CHON.getCode();
    }

    public static String getAllCongToByDateKD() {
        return "SELECT " +
                TBL_CTO_GUI_KD.STT.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.CHISO_THAO.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.TRANG_THAI_CHON.getNameCollumn() +
                " FROM " +
                TBL_CTO_GUI_KD.getNameTable() +
                " WHERE " +
                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() +
                " = ? "
                ;
    }

    public static String getUpdateGhimCtoPB() {
        return "UPDATE " +
                TBL_CTO_PB.getNameTable() +
                " SET " +
                TBL_CTO_PB.TRANG_THAI_GHIM.getNameCollumn() +
                " = ? " +
                " WHERE " +
                TBL_CTO_PB.STT.getNameCollumn() +
                " = ? "
                ;
    }

    public static String getUpdateGhimCtoKD() {
        return "UPDATE " +
                TBL_CTO_GUI_KD.getNameTable() +
                " SET " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() +
                " = ? " +
                " WHERE " +
                TBL_CTO_GUI_KD.STT.getNameCollumn() +
                " = ? "
                ;
    }


    public static String getUpdateChonCtoPB() {
        return "UPDATE " +
                TBL_CTO_PB.getNameTable() +
                " SET " +
                TBL_CTO_PB.TRANG_THAI_CHON.getNameCollumn() +
                " = ? " +
                " WHERE " +
                TBL_CTO_PB.STT.getNameCollumn() +
                " = ? "
                ;
    }

    public static String getUpdateChonCtoKD() {
        return "UPDATE " +
                TBL_CTO_GUI_KD.getNameTable() +
                " SET " +
                TBL_CTO_GUI_KD.TRANG_THAI_CHON.getNameCollumn() +
                " = ? " +
                " WHERE " +
                TBL_CTO_GUI_KD.STT.getNameCollumn() +
                " = ? "
                ;
    }

    public static String getDeleteAllTBL_CTO_PB() {
        return "DELETE FROM " +
                TBL_CTO_PB.getNameTable()
//                +
//                " WHERE " +
//                TBL_CTO_PB.TaiKhoan.getNameCollumn() +
//                " = ?"
                ;
    }

    public static String getDeleteAllTBL_CTO_GUI_KD() {
        return "DELETE FROM " +
                TBL_CTO_GUI_KD.getNameTable()
//                +
//                " WHERE " +
//                TBL_CTO_GUI_KD.TaiKhoan.getNameCollumn() +
//                " = ?"
                ;
    }

    public static String getByDateSelectCongToGhimPB() {
        return "SELECT " +
                TBL_CTO_PB.STT.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_PB.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_PB.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_PB.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.CHISO_THAO.getNameCollumn() + ", " +
//                TBL_CTO_PB.GhimCto.getNameCollumn() + ", " +
//                TBL_CTO_PB.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() + ", " +
                TBL_CTO_PB.TRANG_THAI_GHIM.getNameCollumn() +
                " FROM " +
                TBL_CTO_PB.getNameTable()
                +
                " WHERE " +
                TBL_CTO_PB.TRANG_THAI_GHIM.getNameCollumn() +
                " = " + Common.TRANG_THAI_GHIM.DA_GHIM.getCode() +
                " AND " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() +
                " = ? "
                ;
    }

    public static String getByDateSelectCongToGhimKD() {
        return "SELECT " +
                TBL_CTO_GUI_KD.STT.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.CHISO_THAO.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.TRANG_THAI_CHON.getNameCollumn() +
                " FROM " +
                TBL_CTO_GUI_KD.getNameTable()
                +
                " WHERE " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() +
                " = " + Common.TRANG_THAI_GHIM.DA_GHIM.getCode() +
                " AND " +
                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() +
                " = ? "
                ;
    }

    public static String getDeletePB() {
        return "DELETE FROM " +
                TBL_CTO_PB.getNameTable() +
                " WHERE " +
                TBL_CTO_PB.STT.getNameCollumn()
                + " = ? "
                ;
    }


    public static String getDeleteKD() {
        return "DELETE FROM " +
                TBL_CTO_GUI_KD.getNameTable() +
                " WHERE " +
                TBL_CTO_GUI_KD.STT.getNameCollumn()
                + " = ? "
                ;
    }


    public static String getCheckExistTBL_CTO_GUI_KD() {
        return "SELECT COUNT(*) FROM " +
                TBL_CTO_GUI_KD.getNameTable()
                +
                " WHERE " +
                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn()
                + " = ?"
                ;
    }

    public static String getCheckExistByDateTBL_CTO_GUI_KD() {
        return "SELECT COUNT(*) FROM " +
                TBL_CTO_GUI_KD.getNameTable() +
                " WHERE " +
                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn()
                + " = ?" +
                " AND " +
                TBL_CTO_GUI_KD.NGAY_NHAP
                + " = ?"
                ;
    }

    public static String getCheckExistTBL_CTO_PB() {
        return "SELECT COUNT(*) FROM " +
                TBL_CTO_PB.getNameTable()
                +
                " WHERE " +
                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn()
                + " = ?"
                ;
    }
    //endregion

    //region TBL_DIENLUC query
    public static String getSelectDienLuc() {
        return "SELECT " +
                TBL_DIENLUC.ID.getNameCollumn() + ", " +
                TBL_DIENLUC.MA_DVIQLY.getNameCollumn() +
                " FROM " +
                TBL_DIENLUC.getNameTable()
                ;
    }

    public static String getCheckExistTBL_DIENLUC() {
        return "SELECT COUNT(*) FROM " +
                TBL_DIENLUC.getNameTable()
                +
                " WHERE " +
                TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn()
                + " = ?"
                ;
    }

    public static String getInsertTBL_DIENLUC() {
        return "INSERT INTO " + TBL_DIENLUC.getNameTable() + " (" +
                TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn() +
                ") " + "VALUES (?)" +
                ";"
                ;
    }
    //endregion
}
