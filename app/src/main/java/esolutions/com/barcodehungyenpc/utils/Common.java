package esolutions.com.barcodehungyenpc.utils;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.view.MainKiemDinhActivity;

import static android.support.v4.app.ActivityCompat.requestPermissions;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;
import static esolutions.com.barcodehungyenpc.database.SqlHelper.DB_NAME;
import static esolutions.com.barcodehungyenpc.database.SqlHelper.PATH_FOLDER_DB;

/**
 * Created by VinhNB on 8/9/2017.
 */

public class Common {

    public static final int TIME_DELAY_ANIM = 150;

    public static void runAnimationClickView(final View view, int idAnimation, int timeDelayAnim) {
        if (view == null)
            return;
        if (idAnimation <= 0)
            return;

        Animation animation = AnimationUtils.loadAnimation(view.getContext(), idAnimation);
        if (timeDelayAnim > 0)
            animation.setDuration(timeDelayAnim);

        view.startAnimation(animation);
    }

    //region search tiếng việt
    private static char[] SPECIAL_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò',
            'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê',
            'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ',
            'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ',
            'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ',
            'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế',
            'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị',
            'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ',
            'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ',
            'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự',};

    private static char[] REPLACEMENTS = {'A', 'A', 'A', 'A', 'E', 'E', 'E',
            'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a',
            'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
            'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I',
            'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u',};

    public static String removeAccent(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public static char removeAccent(char ch) {

        int index = Arrays.binarySearch(SPECIAL_CHARACTERS, ch);
        if (index >= 0) {
            ch = REPLACEMENTS[index];
        }
        return ch;
    }

    public static boolean isExistDB() {
        File programDbDirectory = new File(PATH_FOLDER_DB);
        if (!programDbDirectory.exists()) {
            programDbDirectory.mkdirs();
        }

        File db = new File(PATH_FOLDER_DB + File.separator + DB_NAME);
        if (!db.exists()) {
            return false;
        } else
            return true;
    }
    //endregion

    //region Date time
    public enum DATE_TIME_TYPE {
        HHmmss,
        yyyyMMdd,
        yyyyMMddHHmmssSSS,
        MMyyyy,
        ddMMyyyy,
        ddMMyyyyHHmmss,
        FULL;

        @Override
        public String toString() {
            if (this == HHmmss)
                return "HHmmss";
            if (this == yyyyMMdd)
                return "yyyyMMdd";
            if (this == yyyyMMddHHmmssSSS)
                return "yyyyMMddHHmmssSSS";
            if (this == MMyyyy)
                return "MM/yyyy";
            if (this == ddMMyyyy)
                return "dd/MM/yyyy";
            if (this == ddMMyyyyHHmmss)
                return "dd/MM/yyyy HH:mm:ss";
            if (this == FULL)
                return "yyyy-MM-dd HH:mm:ss";
            return super.toString();
        }
    }
    //endregion

    public enum KIEU_CHUONG_TRINH {
        PHAN_BO(0, "Chức năng phân bổ"),
        KIEM_DINH(1, "Chức năng kiểm định");

        private int code;
        private String name;

        KIEU_CHUONG_TRINH(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    public enum MENU_BOTTOM_KD {
        ALL(0, "Danh sách thiết bị"),
        DS_GHIM(1, "Danh sách chọn"),
        LICH_SU(2, "Lịch sử");

        private int code;
        private String name;

        MENU_BOTTOM_KD(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }


    public enum TRANG_THAI_GHIM {
        CHUA_GHIM(0),
        DA_GHIM(1);

        private int code;

        TRANG_THAI_GHIM(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public enum TRANG_THAI_CHON {
        CHUA_CHON(0),
        DA_CHON(1);

        private int code;

        TRANG_THAI_CHON(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public enum MESSAGE {
        ex01("ex01", "File dữ liệu không tồn tại trong bộ nhớ sdcard."),
        ex02("ex02", "Gặp vấn đề khi lấy dữ liệu trong máy tính bảng."),
        ex03("ex03", "Không để trống"),
        ex04("ex04", "Tham số truyền vào request soap không tương ứng với số lượng tham số yêu cầu!"),
        ex05("ex05", "Xảy ra lỗi trong quá trình kết nối tới máy chủ! Xin kiểm tra lại kết nối wifi!"),
        ex06("ex06", "Không nhận được dữ liệu trả về từ máy chủ!"),
        ex07("ex07", "Chưa có kết nối internet, vui lòng kiểm tra lại!"),
        ex08("ex08", "Vui lòng cấu hình địa chỉ máy chủ!"),
        ex09("ex09", "Vui lòng cấu hình mã đơn vị điện lực!"),
        ex10("ex10", "Gặp ván đề khi xóa dữ liệu!."),
        ex11("ex11", "Đã có sẵn thông tin thiết bị trong dữ liệu!."),
        ex12("ex12", "Thành công"),
        ex13("ex13", "Thiết bị không hỗ trợ bluetooth!"),
        ex14("ex14", "Kết nối thiết bị qua bluetooth thành công!"),
        ex15("ex15", "Kết nối thiết bị qua bluetooth thất bại!"),
        ex16("ex16", "Kết nối bluetooth với thiết bị đã ngắt!"),

        ex17("ex17", "Không tìm thấy thiết bị nào!"),
        ex18("ex18", "Ngắt kết nối bluetooth!"),
        ex19("ex19", "Đang quét...!"),
        ex20("ex20", "Dữ liệu nhập rỗng...!"),

        ex21("ex21", "Thao tác tìm kiếm phiên trước chưa kết thúc...!"),
        ex22("ex22", "Chưa có thiết bị nào được chọn để gửi kiểm định...!"),
        ex23("ex23", "Thao tác tải đơn vị phiên trước chưa kết thúc...!"),

        ex("ex", "Gặp vấn đề không xác định.");

        private String code, content;

        MESSAGE(String code, String content) {
            this.code = code;
            this.content = content;
        }

        public String getCode() {
            return code;
        }

        public String getContent() {
            return content;
        }
    }

    public static String getDateTimeNow(Common.DATE_TIME_TYPE formatDate) {
        SimpleDateFormat df = new SimpleDateFormat(formatDate.toString());
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df.format(Calendar.getInstance().getTime());
    }

    public static String convertDateToDate(String time, DATE_TIME_TYPE typeDefault, DATE_TIME_TYPE typeConvert) {
        if (time == null || time.trim().isEmpty())
            return "";

        if (typeDefault != DATE_TIME_TYPE.FULL) {
            time = time.replaceAll("-", "");
            for (int i = time.length(); i <= 17; i++) {
                time += "0";
            }
        }

        long longDate = Common.convertDateToLong(time, typeDefault);

        String dateByDefaultType = Common.convertLongToDate(longDate, typeConvert);
        return dateByDefaultType;
    }

    public static long convertDateToLong(String date, DATE_TIME_TYPE typeDefault) {
        SimpleDateFormat formatter = new SimpleDateFormat(typeDefault.toString());
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date dateParse;
        try {
            dateParse = (Date) formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        return dateParse.getTime();
    }

    public static String convertLongToDate(long time, Common.DATE_TIME_TYPE format) {
        if (time < 0)
            return null;
        if (format == null)
            return null;

        SimpleDateFormat df2 = new SimpleDateFormat(format.toString());
        df2.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date(time);
        return df2.format(date);
    }

    public static String convertDateSQLToDateUI(String time) {
        if (TextUtils.isEmpty(time))
            return "";

        //convert dang yyyymmdd sang dạng dd/mm/yyyy
        String d = time.substring(6, 8);
        String m = time.substring(4, 6);
        String y = time.substring(0, 4);

        return d + "/" + m + "/" + y;
    }


    public static String convertDateUIToDateSQL(String time) {
        if (TextUtils.isEmpty(time))
            return "";

        //convert dang dd/mm/yyyy sang dạng yyyymmdd
        String y = time.substring(6, 10);
        String m = time.substring(3, 5);
        String d = time.substring(0, 2);

        return y + m + d;
    }


    public static void showFolder(ContextWrapper ctx) throws Exception {
        if (ctx == null)
            return;

        if (!Common.isExistDB())
            throw new FileNotFoundException(MESSAGE.ex01.getContent());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

            // Load root folder
            File rootFolder = new File(PATH_FOLDER_DB);
            String[] allFilesRoot = rootFolder.list();
            for (int i = 0; i < allFilesRoot.length; i++) {
                allFilesRoot[i] = PATH_FOLDER_DB + allFilesRoot[i];
            }
            if (allFilesRoot != null)
                MediaScannerConnection.scanFile(ctx, allFilesRoot, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            public void onScanCompleted(String path, Uri uri) {
                                Log.d("ExternalStorage", "Scanned " + path + ":");
                                Log.d("ExternalStorage", "uri=" + uri);
                            }
                        });
        } else {
            ctx.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri
                    .parse("file://" + PATH_FOLDER_DB)));
        }
    }

    public static boolean isNetworkConnected(Context context) throws Exception {
        if (context == null)
            return false;
        int[] networkTypes = {ConnectivityManager.TYPE_MOBILE,
                ConnectivityManager.TYPE_WIFI};
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            for (int networkType : networkTypes) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null &&
                        activeNetworkInfo.getType() == networkType)
                    return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    public static void showAlertDialog(Context context, final MainKiemDinhActivity.OnClickButtonAlertDialog onClickButtonAlertDialog, String title, String message) throws Exception {
        try {
            final Dialog dialogConfig = new Dialog(context);
            dialogConfig.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogConfig.setContentView(R.layout.dialog_alert);
            dialogConfig.setCanceledOnTouchOutside(true);
            dialogConfig.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            dialogConfig.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialogConfig.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            Window window = dialogConfig.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

            final TextView etTitle = (TextView) dialogConfig.findViewById(R.id.tv_title_dialog);
            final TextView etMessage = (TextView) dialogConfig.findViewById(R.id.tv_message_dialog);
            final Button btCancel = (Button) dialogConfig.findViewById(R.id.btn_dialog_cancel);
            final Button btOk = (Button) dialogConfig.findViewById(R.id.btn_dialog_ok);

            etTitle.setText(title);
            etMessage.setText(message);

            //catch click
            btOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        onClickButtonAlertDialog.doClickYes();
                    } catch (Exception e) {
                        throw e;
                    } finally {
                        dialogConfig.cancel();
                    }
                }
            });


            btCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickButtonAlertDialog.doClickNo();
                    dialogConfig.cancel();
                }
            });

            dialogConfig.show();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final int REQUEST_CODE_PERMISSION = 100;

    public static boolean checkPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(activity.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(activity, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE
                }, REQUEST_CODE_PERMISSION);
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public static void hideKeyboard(Activity activity, View view) {
        // Check if no view has focus:
//        View view = activity.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null)
//            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void disableSoftInputFromAppearing(EditText editText) {
        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View f = activity.getCurrentFocus();
        if (null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom(f.getClass()))
            imm.hideSoftInputFromWindow(f.getWindowToken(), 0);
        else
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
