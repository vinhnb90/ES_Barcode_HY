package esolutions.com.barcodehungyenpc.database;

import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class SqlQuery {


    //region TBL_CTO_PB query
    public enum TBL_CTO_GUI_KD {
        CHON("CHON"),
        ID_TBL_CTO_GUI_KD("ID_TBL_CTO"),
        STT("STT"),
        MA_CTO("MA_CTO"),
        SO_CTO("SO_CTO"),
        MA_DVIQLY("MA_DVIQLY"),
        MA_CLOAI("MA_CLOAI"),
        NGAY_NHAP_HT("NGAY_NHAP_HT"),
        NAM_SX("NAM_SX"),

        LOAI_SOHUU("LOAI_SOHUU"),
        TEN_SOHUU("TEN_SOHUU"),
        MA_BDONG("MA_BDONG"),
        NGAY_BDONG("NGAY_BDONG"),
        NGAY_BDONG_HTAI("NGAY_BDONG_HTAI"),
        SO_PHA("SO_PHA"),
        SO_DAY("SO_DAY"),
        DONG_DIEN("DONG_DIEN"),
        VH_CONG("VH_CONG"),
        DIEN_AP("DIEN_AP"),
        HS_NHAN("HS_NHAN"),
        NGAY_KDINH("NGAY_KDINH"),
        CHISO_THAO("CHISO_THAO"),
        HSN("HSN"),
        NGAY_NHAP("NGAY_NHAP"),
        //        GhimCto("GhimCongTo"),
//        TaiKhoan("TaiKhoan"),
        NGAY_NHAP_MTB("NGAY_NHAP_MTB"),
        //Trạng thái chưa ghim = 0, đã ghim = 1
        TRANG_THAI_GHIM("TRANG_THAI_GHIM"),
        //Trạng thái chưa chọn = 0, đã chọn = 1
        TRANG_THAI_CHON("TRANG_THAI_CHON"),
        //Phiên tải xuống
        SESSION_GET("SESSION_GET");


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
        ID_TBL_CTO_PB("ID_TBL_CTO_PB"),
        MA_DVIQLY("MA_DVIQLY"),
        CLOAI("MA_CLOAI"),
        NAMSX("NAM_SX"),
        SO_CTO("SO_CTO"),
        MA_CTO("MA_CTO"),
        CHISO_THAO("CHISO_THAO"),

        //        GhimCto("GhimCongTo"),
//        TaiKhoan("TaiKhoan"),
        NGAY_NHAP("NGAY_NHAP_MTB"),
        TRANG_THAI_GHIM("TRANG_THAI_GHIM"),
        //Trạng thái chưa chọn = 0, đã chọn = 1
        TRANG_THAI_CHON("TRANG_THAI_CHON"),
        //Phiên tải xuống
        SESSION("SESSION_GET");
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
        ID_TBL_DIENLUC("ID_TBL_DIENLUC"),
        MA_DVIQLY("MA_DVIQLY");


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

    public enum TBL_HISTORY {
        ID_TBL_HISTORY("ID_TBL_HISTORY"),
        ID_TBL_CTO("ID_TBL_CTO"),
        TYPE_TBL_CTO("TYPE_TBL_CTO"),
        TYPE_SESSION("TYPE_SESSION"),
        DATE_SESSION("DATE_SESSION");

        private String mNameCollumn;

        TBL_HISTORY(String mNameCollumn) {
            this.mNameCollumn = mNameCollumn;
        }

        public String getNameCollumn() {
            return mNameCollumn;
        }

        public static String getNameTable() {
            return "TBL_HISTORY";
        }
    }

    public static String getCreateTBL_CTO_GUI_KD() {
        return "CREATE TABLE IF NOT EXISTS " + TBL_CTO_GUI_KD.getNameTable() + " (" +
                TBL_CTO_GUI_KD.ID_TBL_CTO_GUI_KD.getNameCollumn() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TBL_CTO_GUI_KD.CHON.getNameCollumn() + " INTEGER, " +
                TBL_CTO_GUI_KD.STT.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.SO_CTO.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.MA_CLOAI.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.NGAY_NHAP_HT.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.NAM_SX.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.LOAI_SOHUU.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.TEN_SOHUU.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.MA_BDONG.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.NGAY_BDONG.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.NGAY_BDONG_HTAI.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.SO_PHA.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.SO_DAY.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.DONG_DIEN.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.VH_CONG.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.DIEN_AP.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.HS_NHAN.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.NGAY_KDINH.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.CHISO_THAO.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.HSN.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() + " TEXT, " +
//                TBL_CTO_GUI_KD.TaiKhoan.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.NGAY_NHAP_MTB.getNameCollumn() + " TEXT, " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() + " INTEGER DEFAULT 0, " +
                TBL_CTO_GUI_KD.TRANG_THAI_CHON.getNameCollumn() + " INTEGER DEFAULT 0, " +
                TBL_CTO_GUI_KD.SESSION_GET.getNameCollumn() + " TEXT" +
                ");";
    }

    public static String getCreateTBL_CTO_PB() {
        return "CREATE TABLE IF NOT EXISTS " + TBL_CTO_PB.getNameTable() + " (" +
                TBL_CTO_PB.ID_TBL_CTO_PB.getNameCollumn() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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
                TBL_CTO_PB.TRANG_THAI_CHON.getNameCollumn() + " INTEGER DEFAULT 0, " +
                TBL_CTO_PB.SESSION.getNameCollumn() + " TEXT" +
//                ", " +
//                TBL_CTO_PB.KieuCongToPhanBo.getNameCollumn() + " INTEGER DEFAULT 0 " +
                ");";
    }

    public static String getCreateTBL_HISTORY() {
        return "CREATE TABLE IF NOT EXISTS " + TBL_HISTORY.getNameTable() + " (" +
                TBL_HISTORY.ID_TBL_HISTORY.getNameCollumn() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TBL_HISTORY.ID_TBL_CTO.getNameCollumn() + " TEXT, " +
                TBL_HISTORY.TYPE_TBL_CTO.getNameCollumn() + " TEXT, " +
                TBL_HISTORY.TYPE_SESSION.getNameCollumn() + " TEXT, " +
                TBL_HISTORY.DATE_SESSION.getNameCollumn() + " TEXT" +
                ");";
    }

    public static String getCreateTBL_DIENLUC() {
        return "CREATE TABLE IF NOT EXISTS " + TBL_DIENLUC.getNameTable() + " (" +
                TBL_DIENLUC.ID_TBL_DIENLUC.getNameCollumn() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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

    public static String getDropTBL_HISTORY() {
        return "DROP TABLE IF EXISTS " + TBL_HISTORY.getNameTable() + " ;";
    }

    public static String getInsertTBL_CTO_PB() {
        return "INSERT INTO " + TBL_CTO_PB.getNameTable() + " (" +
                TBL_CTO_PB.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_PB.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_PB.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_PB.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.CHISO_THAO.getNameCollumn() + ", " +
//                TBL_CTO_PB.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() + ", " +
                TBL_CTO_PB.TRANG_THAI_GHIM.getNameCollumn() + ", " +
                TBL_CTO_PB.TRANG_THAI_CHON.getNameCollumn() + ", " +
                ") " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?" +
                ");"
                ;
    }

    public static String getInsertTBL_CTO_GUI_KD() {
        return "INSERT INTO " + TBL_CTO_GUI_KD.getNameTable() + " (" +
                TBL_CTO_GUI_KD.CHON.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.STT.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn() + ", " +

                TBL_CTO_GUI_KD.MA_CLOAI.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NGAY_NHAP_HT.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NAM_SX.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.LOAI_SOHUU.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.TEN_SOHUU.getNameCollumn() + ", " +

                TBL_CTO_GUI_KD.MA_BDONG.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NGAY_BDONG.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NGAY_BDONG_HTAI.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.SO_PHA.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.SO_DAY.getNameCollumn() + ", " +

                TBL_CTO_GUI_KD.DONG_DIEN.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.VH_CONG.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.DIEN_AP.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.HS_NHAN.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NGAY_KDINH.getNameCollumn() + ", " +

                TBL_CTO_GUI_KD.CHISO_THAO.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.HSN.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.NGAY_NHAP_MTB.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() + ", " +
                TBL_CTO_GUI_KD.TRANG_THAI_CHON.getNameCollumn() +
                ") " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
                ");"
                ;
    }

    public static String getInsertTBL_HISTORY() {
        return "INSERT INTO " + TBL_HISTORY.getNameTable() + " (" +
                TBL_HISTORY.ID_TBL_CTO.getNameCollumn() + ", " +
                TBL_HISTORY.TYPE_TBL_CTO.getNameCollumn() + ", " +
                TBL_HISTORY.TYPE_SESSION.getNameCollumn() + ", " +
                TBL_HISTORY.DATE_SESSION.getNameCollumn() +
                ") " + "VALUES (?, ?, ?, ?" +
                ");"
                ;
    }

  /*  public static String getSelectTBL_CTO_PB() {
        return "SELECT " +
                TBL_CTO_PB.ID_TBL_DIENLUC.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_CLOAI.getNameCollumn() + ", " +
                TBL_CTO_PB.NAM_SX.getNameCollumn() + ", " +
                TBL_CTO_PB.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.CHISO_THAO.getNameCollumn() + ", " +
                TBL_CTO_PB.GhimCto.getNameCollumn() + ", " +
                TBL_CTO_PB.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_PB.NGAY_NHAP_MTB.getNameCollumn() +
                " FROM " +
                TBL_CTO_PB.getNameTable() +
                " WHERE " +
                TBL_CTO_PB.TaiKhoan.getNameCollumn() +
                " = ? "
                ;
    }*/

    public static String getSelectTBL_CTO_PB() {
        return "SELECT " +
                TBL_CTO_PB.ID_TBL_CTO_PB.getNameCollumn() + ", " +
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
                TBL_CTO_PB.ID_TBL_CTO_PB.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_PB.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_PB.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_PB.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.CHISO_THAO.getNameCollumn() + ", " +
//                TBL_CTO_PB.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() + ", " +
                TBL_CTO_PB.TRANG_THAI_GHIM.getNameCollumn() + ", " +
                TBL_CTO_PB.TRANG_THAI_CHON.getNameCollumn() +
                " FROM " +
                TBL_CTO_PB.getNameTable() +
                " WHERE " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() +
                " = ? "
                ;
    }


//    public static String getSelectTBL_CTO_GUI_KD() {
//        return "SELECT " +
//                TBL_CTO_GUI_KD.ID_TBL_DIENLUC.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.MA_DVIQLY.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.MA_CLOAI.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.NAM_SX.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.SO_CTO.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.MA_CTO.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.CHISO_THAO.getNameCollumn() + ", " +
////                TBL_CTO_GUI_KD.TaiKhoan.getNameCollumn() + ", " +
//                TBL_CTO_GUI_KD.NGAY_NHAP_MTB.getNameCollumn() +
//                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() +
//                " FROM " +
//                TBL_CTO_GUI_KD.getNameTable() +
//                " WHERE " +
//                TBL_CTO_GUI_KD.NGAY_NHAP_MTB +
//                " = ? "
//                ;
//    }

    public static String getByDateAllCongToGhimAndChonKD() {
        return "SELECT *" +
                " FROM " +
                TBL_CTO_GUI_KD.getNameTable() +
                " WHERE " +
                TBL_CTO_GUI_KD.NGAY_NHAP_MTB.getNameCollumn() +
                " = ? " +
                " AND " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM +
                " = " + Common.TRANG_THAI_GHIM.DA_GHIM.getCode() +
                " AND " +
                TBL_CTO_GUI_KD.TRANG_THAI_CHON +
                " = " + Common.TRANG_THAI_CHON.DA_CHON.getCode();
    }

    public static String getAllCongToByDateKD() {
        return "SELECT * " +
                " FROM " +
                TBL_CTO_GUI_KD.getNameTable() +
                " WHERE " +
                TBL_CTO_GUI_KD.NGAY_NHAP_MTB.getNameCollumn() +
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
                TBL_CTO_PB.ID_TBL_CTO_PB.getNameCollumn() +
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
                TBL_CTO_GUI_KD.ID_TBL_CTO_GUI_KD.getNameCollumn() +
                " = ? "
                ;
    }
    public static String updateGhimCtoKDUploadSuccess() {
        return "UPDATE " +
                TBL_CTO_GUI_KD.getNameTable() +
                " SET " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() +
                " = ? " +
                " WHERE " +
                TBL_CTO_GUI_KD.CHON.getNameCollumn() +
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
                TBL_CTO_PB.ID_TBL_CTO_PB.getNameCollumn() +
                " = ? "
                ;
    }

    public static String getUpdateChonCtoKD() {
        return "UPDATE " +
                TBL_CTO_GUI_KD.getNameTable() +
                " SET " +
                TBL_CTO_GUI_KD.CHON.getNameCollumn() +
                " = ? " +
                " WHERE " +
                TBL_CTO_GUI_KD.ID_TBL_CTO_GUI_KD.getNameCollumn() +
                " = ? "
                ;
    }

    public static String getUpdateTRANG_THAI_CHONCtoKD() {
        return "UPDATE " +
                TBL_CTO_GUI_KD.getNameTable() +
                " SET " +
                TBL_CTO_GUI_KD.TRANG_THAI_CHON.getNameCollumn() +
                " = ? " +
                " WHERE " +
                TBL_CTO_GUI_KD.ID_TBL_CTO_GUI_KD.getNameCollumn() +
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

    public static String deleteByDateAllCongToKD() {
        return "DELETE FROM " +
                TBL_CTO_GUI_KD.getNameTable()
                +
                " WHERE " +
                TBL_CTO_GUI_KD.NGAY_NHAP.getNameCollumn() +
                " = ?";
    }

    public static String getByDateSelectCongToGhimPB() {
        return "SELECT " +
                TBL_CTO_PB.ID_TBL_CTO_PB.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_DVIQLY.getNameCollumn() + ", " +
                TBL_CTO_PB.CLOAI.getNameCollumn() + ", " +
                TBL_CTO_PB.NAMSX.getNameCollumn() + ", " +
                TBL_CTO_PB.SO_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.MA_CTO.getNameCollumn() + ", " +
                TBL_CTO_PB.CHISO_THAO.getNameCollumn() + ", " +
//                TBL_CTO_PB.TaiKhoan.getNameCollumn() + ", " +
                TBL_CTO_PB.NGAY_NHAP.getNameCollumn() + ", " +
                TBL_CTO_PB.TRANG_THAI_GHIM.getNameCollumn() + ", " +
                TBL_CTO_PB.TRANG_THAI_CHON.getNameCollumn() + ", " +
                TBL_CTO_PB.SESSION.getNameCollumn() +
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
        return "SELECT *" +
                " FROM " +
                TBL_CTO_GUI_KD.getNameTable()
                +
                " WHERE " +
                TBL_CTO_GUI_KD.TRANG_THAI_GHIM.getNameCollumn() +
                " = " + Common.TRANG_THAI_GHIM.DA_GHIM.getCode() +
                " AND " +
                TBL_CTO_GUI_KD.NGAY_NHAP_MTB.getNameCollumn() +
                " = ? "
                ;
    }

    public static String getDeletePB() {
        return "DELETE FROM " +
                TBL_CTO_PB.getNameTable() +
                " WHERE " +
                TBL_CTO_PB.ID_TBL_CTO_PB.getNameCollumn()
                + " = ? "
                ;
    }


    public static String getDeleteKD() {
        return "DELETE FROM " +
                TBL_CTO_GUI_KD.getNameTable() +
                " WHERE " +
                TBL_CTO_GUI_KD.ID_TBL_CTO_GUI_KD.getNameCollumn()
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
                TBL_CTO_GUI_KD.NGAY_NHAP_MTB
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
                TBL_DIENLUC.ID_TBL_DIENLUC.getNameCollumn() + ", " +
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
