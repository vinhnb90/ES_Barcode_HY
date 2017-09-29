package esolutions.com.barcodehungyenpc.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import org.ksoap2.serialization.PropertyInfo;

import java.util.ArrayList;
import java.util.List;

import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.database.SqlConnect;
import esolutions.com.barcodehungyenpc.database.SqlDAO;
import esolutions.com.barcodehungyenpc.entity.CToKDResponse;
import esolutions.com.barcodehungyenpc.entity.CToPBResponse;
import esolutions.com.barcodehungyenpc.entity.CongToGuiKDProxy;
import esolutions.com.barcodehungyenpc.entity.CongToGuiKD;
import esolutions.com.barcodehungyenpc.entity.CongToPB;
import esolutions.com.barcodehungyenpc.entity.CongToPBProxy;
import esolutions.com.barcodehungyenpc.entity.History;
import esolutions.com.barcodehungyenpc.entity.HistoryProxy;
import esolutions.com.barcodehungyenpc.entity.ThongBaoResponse;
import esolutions.com.barcodehungyenpc.entity.Update_GuiKD_CTO;
import esolutions.com.barcodehungyenpc.entity.Update_GuiKD_CTO_MTBResponse;
import esolutions.com.barcodehungyenpc.model.DsCongToAdapter;
import esolutions.com.barcodehungyenpc.model.DsHistoryAdapter;
import esolutions.com.barcodehungyenpc.utils.Common;
import esolutions.com.barcodehungyenpc.utils.SharePrefManager;
import esolutions.com.barcodehungyenpc.utils.SoapXML;
import esolutions.com.barcodehungyenpc.utils.SoapXML.METHOD;

import static esolutions.com.barcodehungyenpc.utils.Common.TIME_DELAY_ANIM;
import static esolutions.com.barcodehungyenpc.utils.Common.convertDateSQLToDateUI;
import static esolutions.com.barcodehungyenpc.utils.Common.getDateTimeNow;
import static esolutions.com.barcodehungyenpc.view.DangNhapActivity.KEY_PREF_KEYBOARD;
import static esolutions.com.barcodehungyenpc.view.DangNhapActivity.PARAM_DVI;
import static esolutions.com.barcodehungyenpc.view.DangNhapActivity.PARAM_POS_PROGRAME;
import static esolutions.com.barcodehungyenpc.view.DangNhapActivity.PARAM_SERVER_URL;
import static esolutions.com.barcodehungyenpc.view.DangNhapActivity.PARAM_USER;
import static esolutions.com.barcodehungyenpc.view.DangNhapActivity.PREF_CONFIG;

public class MainKiemDinhActivity
        extends BaseActivity
        implements
        DatePickerDialog.OnDateSetListener,
        DsHistoryAdapter.OnDsHistoryAdapterIteraction,
        DsCongToAdapter.OnDsCtoAdapterIteraction {

    private LinearLayout mLLSearchOnline;
    private EditText mEtSearchOnline, mEtSearchLocal;
    private TextView mTvThongKeCto, mTvDate;
    private ImageButton mBtnDate, mBtnClearSearchOnline, mBtnClearSearchLocal, mBtnSearchOnline;
    private ProgressBar mPbarSearchOnline;
    private CoordinatorLayout mCoordinatorLayout;
    private CheckBox mCbSearchBBan;
    private Button mDeleteAll;
    //upload
    private RelativeLayout mRvUpload;
    private ProgressBar mPbarUpload;
    private Button mBtnUpload;
    private TextView mTvCountCtoUpload;
    private TextView mTvStatusUpload;
    private FloatingActionButton mFab;

    private SharePrefManager mPrefManager;

    private Handler mUiHandler = new Handler();

    private static final String TAG = MainKiemDinhActivity.class.getName();

    private RelativeLayout mRlMain;
    private RecyclerView mRvCto;
    public BottomBar mNavigation;
    public static String sMaDLuc, sTaiKhoan;
    private boolean isSearchOnline;
    private String mMatKhau;
    private String time;
    private boolean triggerListeners = true;
    private List<CongToGuiKDProxy> mListCtoKD = new ArrayList<>();
    private List<CongToGuiKDProxy> mListUploadCtoKD = new ArrayList<>();
    private List<Update_GuiKD_CTO> mListDataUploadGKD = new ArrayList<>();

    private List<CongToPBProxy> mListCtoPB = new ArrayList<>();

    private List<HistoryProxy> mListHistory = new ArrayList<>();

    private int mCountUploadFinish = 0, mCountUploadSuccess = 0;


    private Handler mHandlerUploadKD = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (TextUtils.isEmpty(time))
                time = getDateTimeNow(Common.DATE_TIME_TYPE.ddMMyyyyHHmmss);

            mTvStatusUpload.setText((mCountUploadSuccess) + "/" + mListUploadCtoKD.size());

            if (Integer.parseInt(msg.obj.toString()) == 0) {
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, Common.MESSAGE.ex26.getContent(), Snackbar.LENGTH_LONG);
                snackbar.show();
                mBtnUpload.setVisibility(View.VISIBLE);
                mPbarUpload.setVisibility(View.GONE);
                time = "";
                return;
            }

            SoapXML.AsyncSoapUpload.AsyncSoapCallBackUpload<List<Update_GuiKD_CTO_MTBResponse>, ThongBaoResponse> callBackUpload = new SoapXML.AsyncSoapUpload.AsyncSoapCallBackUpload<List<Update_GuiKD_CTO_MTBResponse>, ThongBaoResponse>() {

                @Override
                public void onPre(SoapXML.AsyncSoapUpload soap) {
                    mBtnUpload.setVisibility(View.GONE);
                    mPbarUpload.setVisibility(View.VISIBLE);
                }

                @Override
                public void onUpdate(String message) {
                    mBtnUpload.setVisibility(View.VISIBLE);
                    mPbarUpload.setVisibility(View.GONE);
                    Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    if (mCountUploadFinish > 0) {
                        mCountUploadFinish--;
                    }

                    Message msg = mHandlerUploadKD.obtainMessage();
                    msg.obj = mCountUploadFinish;
                    mHandlerUploadKD.sendMessage(msg);

                    try {
                        //insert history
                        History history = new History();
                        history.setID_TBL_CTO(0);
                        history.setTYPE_RESULT(Common.TYPE_RESULT.ERROR.getCode());
                        history.setTYPE_SESSION(Common.TYPE_SESSION.UPLOAD.getCode());
                        history.setTYPE_TBL_CTO(mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH ? Common.TYPE_TBL_CTO.KD.getCode() : Common.TYPE_TBL_CTO.PB.getCode());
                        //convert time to date SQL
                        long dateSql = Common.convertDateToLong(time, Common.DATE_TIME_TYPE.ddMMyyyyHHmmss);
                        history.setDATE_SESSION(String.valueOf(dateSql));
                        history.setINFO_SEARCH("");
                        mSqlDAO.insertTBL_HISTORY(history);
                    } catch (Exception e) {
                        snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }

                }

                @Override
                public void onPostData(List<Update_GuiKD_CTO_MTBResponse> dataResponse) {
                    if (dataResponse == null)
                        return;

                    try {
                        doProcessAfterUploadGuiKD(dataResponse);
                        if (mCountUploadFinish > 0) {
                            mCountUploadFinish--;
                        }

                    } catch (Exception e) {
                        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);

                        snackbar.show();
                        e.printStackTrace();
                    } finally {
                        Message msg = mHandlerUploadKD.obtainMessage();
                        msg.obj = mCountUploadFinish;
                        mHandlerUploadKD.sendMessage(msg);
                    }
                }

                @Override
                public void onPostMessageSever(ThongBaoResponse errorResponse) {
                    if (mCountUploadFinish > 0)
                        mCountUploadFinish--;
                    onUpdate(errorResponse.getThongbao());
                }

                @Override
                public PropertyInfo setupProInfo(METHOD method) {

                    PropertyInfo pi = new PropertyInfo();
                    pi.setName(method.getNameParams()[0]);
                    pi.setValue(mListDataUploadGKD.get(mCountUploadFinish - 1));
                    pi.setType(Update_GuiKD_CTO.class);
                    return pi;
                }
            };

            try {
                soapUpload = new SoapXML.AsyncSoapUpload(
                        Update_GuiKD_CTO_MTBResponse.class,
                        ThongBaoResponse.class,
                        "thongbao",
                        callBackUpload,
                        METHOD.Update_GuiKD_CTO_MTB,
                        SoapXML.getURL(mURL)
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            soapUpload.execute();
        }
    };

    private void doProcessAfterUploadGuiKD(List<Update_GuiKD_CTO_MTBResponse> dataResponse) throws Exception {
        //lấy trường chọn
        for (Update_GuiKD_CTO_MTBResponse response :
                dataResponse) {

            int CHON = response.getCHON();
            //update CHON
            mSqlDAO.updateChonCtoKD(mListUploadCtoKD.get(mCountUploadFinish - 1).getID(), CHON);

            //nếu CHON == 1 thì bỏ ghim, nếu CHON == 2 hoặc 0 thì giữ nguyên ghim
//            if(CHON == Common.CHON.GUI_THANH_CONG.getCode())
//            {
//                mSqlDAO.updateGhimCtoKD(mListUploadCtoKD.get(mCountUploadFinish - 1).getID(), Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode());
//            }

//            mSqlDAO.updateTRANG_THAI_CHONCto(mListUploadCtoKD.get(mCountUploadFinish - 1).getID(), Common.TRANG_THAI_CHON.CHUA_CHON.getCode());
            if (menuBottom == Common.MENU_BOTTOM_KD.DS_GHIM) {
                mListCtoKD.clear();
                mListCtoKD = mSqlDAO.getByDateAllCongToGhimKD(Common.convertDateUIToDateSQL(mDate));
            }

            //insert history
            int id = mListUploadCtoKD.get(mCountUploadFinish - 1).getID();
            if (id != 0) {
                History history = new History();
                history.setID_TBL_CTO(id);
                history.setTYPE_RESULT(Common.TYPE_RESULT.SUCCESS.getCode());
                history.setTYPE_SESSION(Common.TYPE_SESSION.UPLOAD.getCode());
                history.setTYPE_TBL_CTO(mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH ? Common.TYPE_TBL_CTO.KD.getCode() : Common.TYPE_TBL_CTO.PB.getCode());

                //convert time to date SQL
                long dateSql = Common.convertDateToLong(time, Common.DATE_TIME_TYPE.ddMMyyyyHHmmss);
                history.setDATE_SESSION(String.valueOf(dateSql));
                mSqlDAO.insertTBL_HISTORY(history);
            }

            fillDataReyclerFull();
            mRvCto.invalidate();
        }

        if (mCountUploadSuccess < mListDataUploadGKD.size())
            mCountUploadSuccess++;
    }


    private DsCongToAdapter mCtoAdapter = null;
    private DsHistoryAdapter mHistoryAdapter;
    private SQLiteDatabase mDatabase;
    private SqlDAO mSqlDAO;
    private Common.KIEU_CHUONG_TRINH mKieuChuongTrinh;
    private Common.MENU_BOTTOM_KD menuBottom;
    private String mDate;
    Bundle savedInstanceState;

    SoapXML.AsyncSoap<List<CToKDResponse>, ThongBaoResponse> soapSearchCto = null;
    SoapXML.AsyncSoapUpload soapUpload = null;
    private static boolean isLoadedFolder = false;

    //bundle
    private String mStringDvi, mURL, mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kiem_dinh);
        this.savedInstanceState = savedInstanceState;
//        super.hideBar();

        if (Common.checkPermission(this)) {
            return;
        }

        try {
            //lay data
            getBundle();
            initView();
            handleListener();
            setAction(savedInstanceState);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //check file config
        Boolean isShowKeyboard = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).getBoolean(KEY_PREF_KEYBOARD, false);
        if (isShowKeyboard) {
            mEtSearchOnline.setInputType(InputType.TYPE_NULL);
            mEtSearchOnline.setRawInputType(InputType.TYPE_CLASS_TEXT);
            mEtSearchOnline.setTextIsSelectable(true);
            mEtSearchLocal.setInputType(InputType.TYPE_NULL);
            mEtSearchLocal.setRawInputType(InputType.TYPE_CLASS_TEXT);
            mEtSearchLocal.setTextIsSelectable(true);
        } else {
            mEtSearchOnline.setInputType(InputType.TYPE_CLASS_TEXT);
            mEtSearchLocal.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == BarcodeActivity.RESULT_CODE && requestCode == BarcodeActivity.REQUEST_CODE && data != null) {
//            mEtSearchOnline.setText(data.getStringExtra(BarcodeActivity.PARAM_BARCODE));
//        }
    }

    //region DsHistoryAdapter.OnDsHistoryAdapterIteraction
    @Override
    public void clickBtnHistoryChiTiet(int pos) {
        if (pos >= mListHistory.size())
            return;

        showDialogDetailHistory(pos);
    }

    private void showDialogDetailHistory(int pos) {
        String DATE_SESSION = mListHistory.get(pos).getDATE_SESSION();
        Common.TYPE_SESSION typeSession = Common.TYPE_SESSION.findNameBy(mListHistory.get(pos).getTYPE_SESSION());

        final Dialog dialogConfig = new Dialog(this);
        dialogConfig.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogConfig.setContentView(R.layout.dialog_detail_history);
        dialogConfig.setCanceledOnTouchOutside(true);
        dialogConfig.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        dialogConfig.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialogConfig.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialogConfig.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        final RecyclerView recyclerView = (RecyclerView) dialogConfig.findViewById(R.id.rv_history_detail);
        mListCtoKD.clear();
        try {
            mListCtoKD = mSqlDAO.getByDateAllCongToKDByDATE_SESSIONByTYPE_RESULT(DATE_SESSION, typeSession);
        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
            e.printStackTrace();
            return;
        }

        try {
            DsCongToAdapter dsCongToAdapter = new DsCongToAdapter(this, mListCtoKD, mListCtoPB, mKieuChuongTrinh);
            dsCongToAdapter.setHistoryAdapter(true);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(dsCongToAdapter);
            recyclerView.invalidate();

        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
            e.printStackTrace();
            return;
        }
        dialogConfig.show();

    }

//    @Override
//    public int getCountCtoByDateByRESULT(String date_session, Common.TYPE_TBL_CTO typeTblCto, Common.TYPE_SESSION typeSession, Common.TYPE_RESULT typeResult) {
//        if (TextUtils.isEmpty(date_session))
//            return 0;
//
//        int count = 0;
//        try {
//            count = mSqlDAO.countByDateSessionHistoryCtoByRESULT(date_session, typeTblCto, typeSession, typeResult);
//        } catch (Exception e) {
//            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
//            snackbar.show();
//            e.printStackTrace();
//        }
//        return count;
//    }
    //endregion

    public interface OnClickButtonAlertDialog {
        void doClickYes();

        void doClickNo();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Common.REQUEST_CODE_PERMISSION: {
                if (grantResults.length == 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED
                        || grantResults[1] != PackageManager.PERMISSION_GRANTED
                        || grantResults[2] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainKiemDinhActivity.this, "Unable to show permission required", Toast.LENGTH_LONG).show();
                    Log.e(getClass().getName(), "Unable to show permission required");
                } else {
                    try {
                        //lay data
                        getBundle();
                        initView();
                        handleListener();
                        setAction(savedInstanceState);
                    } catch (Exception e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                return;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_config:
                try {
                    showDialogConfig();
                } catch (Exception e) {
                    Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    e.printStackTrace();
                }
                break;

            case R.id.menu_camera:
                processCamera();
                break;

            case R.id.menu_barcode_bluetooth:
                processBluetooth();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void processCamera() {
//        Intent intent = new Intent(MainKiemDinhActivity.this, BarcodeActivity.class);
////        startActivityForResult(intent, BarcodeActivity.REQUEST_CODE);
//        startActivity(intent);

        BarcodeScannerDialog dialog = new BarcodeScannerDialog(this, new BarcodeScannerDialog.OnResultListener() {
            @Override
            public void onResult(String text) {
                mEtSearchOnline.setText(text);
            }
        });
        dialog.show();
    }

    private void showDialogConfig() throws Exception {
        final Dialog dialogConfig = new Dialog(this);
        dialogConfig.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogConfig.setContentView(R.layout.dialog_config);
        dialogConfig.setCanceledOnTouchOutside(true);
        dialogConfig.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        dialogConfig.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialogConfig.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialogConfig.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

//        final EditText etURL = (EditText) dialogConfig.findViewById(R.id.et_url);
//        final EditText etDvi = (EditText) dialogConfig.findViewById(R.id.aet_dvi);
        final Button btSave = (Button) dialogConfig.findViewById(R.id.btn_save_config);

        final ImageButton mBtnListDvi = (ImageButton) dialogConfig.findViewById(R.id.ibtn_auto_dvi);
        final AutoCompleteTextView mAutoEtDvi = (AutoCompleteTextView) dialogConfig.findViewById(R.id.aet_dvi);
        final Switch swtShowKeyboard = (Switch) dialogConfig.findViewById(R.id.swt_keyboard);

        //fill Data
        if (mPrefManager == null)
            mPrefManager = SharePrefManager.getInstance(this);

        //check setup keyboard, ban đầu là show
        final boolean isShowKeyboard = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).getBoolean(KEY_PREF_KEYBOARD, false);
        swtShowKeyboard.setChecked(isShowKeyboard);

//        //check url
//        String URL = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).getString(KEY_PREF_SERVER_URL, "");
//        etURL.setText(URL);
//
//        //check dvi
//        String dvi = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).getString(KEY_PREF_MA_DIEN_LUC, "");
//        mAutoEtDvi.setText(dvi);
//
//        //set Adapter autoComplex dvi
//        mAutoEtDvi.setThreshold(2);
//        mAutoEtDvi.setAdapter(initAdapterDvi());
//        mAutoEtDvi.invalidate();
//
//        mBtnListDvi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mAutoEtDvi.showDropDown();
//            }
//        });
//
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //true nếu ẩn bàn phím, nếu bật ẩn thì checked = true
                    Boolean isHideKeyboard = swtShowKeyboard.isChecked();
//
//                    String dvi = etDvi.getText().toString();
//                    String url = etURL.getText().toString();
//
//                    //check
//                    if (TextUtils.isEmpty(url)) {
//                        etURL.setError(Common.MESSAGE.ex03.getContent());
//                        etURL.setFocusable(true);
//                        return;
//                    }
//
//                    if (TextUtils.isEmpty(dvi)) {
//                        etDvi.setError(Common.MESSAGE.ex03.getContent());
//                        etDvi.setFocusable(true);
//                        return;
//                    }

                    mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE)
                            .edit()
                            .putBoolean(DangNhapActivity.KEY_PREF_KEYBOARD, isHideKeyboard)
//                            .putString(KEY_PREF_MA_DIEN_LUC, dvi)
//                            .putString(KEY_PREF_SERVER_URL, url)
                            .commit();

                    //check nếu dvi mơi thì thêm vào database và làm mới lại auto et dvi
                    //lưu shared pref
//                    boolean isHasDviExits = false;
//
//                    isHasDviExits = mSqlDAO.checkExistTBL_DIENLUC(dvi);
//
//                    if (!isHasDviExits) {
//                        DienLuc dienLuc = new DienLuc();
//                        dienLuc.setMA_DVIQLY(dvi);
//
//                        mSqlDAO.insertTBL_DIENLUC(dienLuc);
//
//                        //set Adapter autoComplex dvi
//                        mAutoEtDvi.setThreshold(1);
//                        mAutoEtDvi.setAdapter(initAdapterDvi());
//                        mAutoEtDvi.invalidate();
//                    }
                    Snackbar snackbar = Snackbar
                            .make(mCoordinatorLayout, Common.MESSAGE.ex12.getContent(), Snackbar.LENGTH_LONG);

                    snackbar.show();
                    dialogConfig.dismiss();
                } catch (Exception e) {
                    Snackbar snackbar = Snackbar
                            .make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);

                    snackbar.show();
                    dialogConfig.dismiss();
                }
            }
        });

        dialogConfig.show();
    }

    @Override
    protected void initView() {
        SpannableString s = new SpannableString(mKieuChuongTrinh.getName());
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, mKieuChuongTrinh.getName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        getSupportActionBar().setElevation(0);

        mRvCto = (RecyclerView) findViewById(R.id.rv_ds_cto);
        mNavigation = (BottomBar) findViewById(R.id.nav_bottom_menu);

        mEtSearchOnline = (EditText) findViewById(R.id.et_search_type);
        mEtSearchLocal = (EditText) findViewById(R.id.et_search_type2);
        mLLSearchOnline = (LinearLayout) findViewById(R.id.ll_search);

        mTvThongKeCto = (TextView) findViewById(R.id.et_thongKe);
        mTvDate = (TextView) findViewById(R.id.et_ngay);
        mBtnDate = (ImageButton) findViewById(R.id.ibtn_search_date);
        mBtnClearSearchOnline = (ImageButton) findViewById(R.id.ibtn_clear_search_type);
        mBtnClearSearchLocal = (ImageButton) findViewById(R.id.ibtn_clear_search_type2);
        mBtnSearchOnline = (ImageButton) findViewById(R.id.ibtn_search_online);
        mPbarSearchOnline = (ProgressBar) findViewById(R.id.pbar_search_online);
        mCbSearchBBan = (CheckBox) findViewById(R.id.cb_search_by_id_bban);
        mDeleteAll = (Button) findViewById(R.id.btn_clear_all);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.cl_main);

        //upload
        mRvUpload = (RelativeLayout) findViewById(R.id.rl_upload_data);
        mPbarUpload = (ProgressBar) findViewById(R.id.pbar_upload);
        mBtnUpload = (Button) findViewById(R.id.btn_upload);
        mTvCountCtoUpload = (TextView) findViewById(R.id.tv_count_upload);
        mTvStatusUpload = (TextView) findViewById(R.id.tv_date_upload);
        mFab = (FloatingActionButton) findViewById(R.id.fab_upload);

        //init recycler dsCto
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvCto.setLayoutManager(layoutManager);
        mRvCto.setHasFixedSize(true);

        //
        if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
            mCbSearchBBan.setVisibility(View.GONE);
        }

        if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) {
            mCbSearchBBan.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void setAction(final Bundle savedInstanceState) {
        try {
            //set share pref
            mPrefManager = SharePrefManager.getInstance(this);
            DangNhapActivity.checkSharePreference(mPrefManager);

            //set Data
            //0 là Phân bổ 1 là đăng nhập
            menuBottom = Common.MENU_BOTTOM_KD.ALL;
            //init Data

            mDate = getDateTimeNow(Common.DATE_TIME_TYPE.ddMMyyyy);
            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH)
                mListCtoKD = mSqlDAO.getByDateAllCongToKD(Common.convertDateUIToDateSQL(mDate));
            else
                mListCtoPB = mSqlDAO.getByDateAllCongToPB(Common.convertDateUIToDateSQL(mDate));

            // setup kiểu mListDataUploadGKD công tơ và refersh lại text thống kê
            searchLocal(mDate);

            mEtSearchLocal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (hasFocus && isSearchOnline) {
                        isSearchOnline = false;

                        //set text
                        mEtSearchOnline.setHint(mEtSearchOnline.getText().toString());
                        mEtSearchOnline.setText("");
                        mEtSearchOnline.setFocusable(true);
                        mEtSearchOnline.requestFocus();
                    }
                }
            });

            mEtSearchOnline.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                    if (event != null && event.getAction() != KeyEvent.ACTION_DOWN) {
                        return false;
                    } else if (event == null
                            || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        //bắt sự kiện enter khi quét xong barcode từ máy
                        mEtSearchOnline.requestFocus();

                        //Search online
                        String textSearch = mEtSearchOnline.getText().toString();
                        if (TextUtils.isEmpty(textSearch)) {
                            Snackbar snackbar = Snackbar
                                    .make(mCoordinatorLayout, Common.MESSAGE.ex20.getContent(), Snackbar.LENGTH_LONG);

                            snackbar.show();
                            return false;
                        }

                        //bật tiếng bip khi kết nối
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                        r.play();

                        //dùng làm cờ phân biệt việc forcus tự động mEtLocalSearch
                        isSearchOnline = true;

                        //search online tự động
                        try {
                            searchOnline(textSearch);
                        } catch (Exception e) {
                            Snackbar snackbar = Snackbar
                                    .make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                            snackbar.show();
                            e.printStackTrace();
                        }
                    }

                    return false;
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setTextCountCtoAndDate(int totalCto) {
        mTvThongKeCto.setText(String.valueOf(totalCto) + " thiết bị");
        mTvDate.setText(mDate);
    }

    @Override
    protected void handleListener() {
        try {
            mDatabase = SqlConnect.getInstance(this).open();
            mSqlDAO = new SqlDAO(mDatabase, this);

            //hiển thị folder trên sdcard
            if (!isLoadedFolder) {
                Common.showFolder(this);
                isLoadedFolder = !isLoadedFolder;
            }

            mTvDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (menuBottom == Common.MENU_BOTTOM_KD.ALL) {
                            mListCtoKD.clear();
                            mListCtoPB.clear();

                            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH)
                                mListCtoKD = mSqlDAO.getByDateAllCongToKD(Common.convertDateUIToDateSQL(mDate));
                            else
                                mListCtoPB = mSqlDAO.getByDateAllCongToPB(Common.convertDateUIToDateSQL(mDate));

                            fillDataReyclerFull();
                        }

                        if (menuBottom == Common.MENU_BOTTOM_KD.DS_GHIM) {
                            mListCtoKD.clear();
                            mListCtoPB.clear();

                            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH)
                                mListCtoKD = mSqlDAO.getByDateAllCongToKD(Common.convertDateUIToDateSQL(mDate));
                            else
                                mListCtoPB = mSqlDAO.getByDateAllCongToPB(Common.convertDateUIToDateSQL(mDate));

                            fillDataReyclerFull();
                        }

                        //TODO here
                        if (menuBottom == Common.MENU_BOTTOM_KD.LICH_SU) {

                            String TYPE_TBL_CTO = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? Common.TYPE_TBL_CTO.KD.getCode() : Common.TYPE_TBL_CTO.PB.getCode();

                            mListHistory.clear();
                            mListHistory = mSqlDAO.getBydateALLHistoryCto(mDate, TYPE_TBL_CTO, Common.DATE_TIME_TYPE.ddMMyyyyHHmmss, mKieuChuongTrinh);
                            fillDataRecylerHistory();
                        }


                    } catch (Exception e) {
                        Snackbar snackbar = Snackbar
                                .make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);

                        snackbar.show();
                        e.printStackTrace();
                    }
                }
            });

            mEtSearchLocal.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override


                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    searchLocalOnDate(charSequence);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            mBtnClearSearchOnline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEtSearchOnline.setText("");
                    mEtSearchOnline.requestFocus();
                    mEtSearchOnline.setFocusable(true);
                }
            });

            mBtnClearSearchLocal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEtSearchLocal.setText("");
                    mEtSearchLocal.requestFocus();
                    mEtSearchLocal.setFocusable(true);
                }
            });

            mBtnSearchOnline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (TextUtils.isEmpty(mEtSearchOnline.getText().toString())) {
                            mEtSearchOnline.setError(Common.MESSAGE.ex03.getContent());
                            return;
                        }

                        //TODO mark 1
                        searchOnline(mEtSearchOnline.getText().toString());
                    } catch (Exception e) {
                        Snackbar snackbar = Snackbar
                                .make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);

                        snackbar.show();
                        e.printStackTrace();
                    }
                }
            });

            mBtnDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Common.runAnimationClickView(view, R.anim.scale_view_pull, Common.TIME_DELAY_ANIM);
                    showDialogChooseDate();
                }
            });

            //set menu bottom
            mNavigation.setOnTabSelectListener(new OnTabSelectListener() {
                @Override
                public void onTabSelected(@IdRes int tabId) {
                    try {
                        //clear textSearch
                        mEtSearchOnline.setText("");

                        switch (tabId) {
                            case R.id.nav_bottom_ds_thietbi:
                                //show ll search
                                if (mLLSearchOnline.getVisibility() == View.GONE)
                                    mLLSearchOnline.setVisibility(View.VISIBLE);

//                                //init Data
                                menuBottom = Common.MENU_BOTTOM_KD.ALL;
                                if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                                    mListCtoKD.clear();
                                    mListCtoKD = mSqlDAO.getByDateAllCongToKD(Common.convertDateUIToDateSQL(mTvDate.getText().toString()));
                                }

                                if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) {
                                    mListCtoPB.clear();
                                    mListCtoPB = mSqlDAO.getByDateAllCongToPB(Common.convertDateUIToDateSQL(mTvDate.getText().toString()));
                                }

                                //hide fab
                                if (mFab.getVisibility() == View.VISIBLE) {
                                    mFab.hide(true);
                                    mFab.setVisibility(View.GONE);
                                }
                                fillDataReyclerFull();
                                break;

                            case R.id.nav_bottom_ds_chon:
//                                //init Data
                                menuBottom = Common.MENU_BOTTOM_KD.DS_GHIM;
                                mListCtoKD.clear();
                                mListCtoKD = mSqlDAO.getByDateAllCongToGhimKD(Common.convertDateUIToDateSQL(mTvDate.getText().toString()));

                                //show fab
                                if (mFab.getVisibility() == View.GONE) {
                                    mFab.setVisibility(View.INVISIBLE);
                                }
                                mFab.setImageResource(R.mipmap.ic_upload_white);
                                mFab.show(true);

                                //hide search online
                                if (mLLSearchOnline.getVisibility() == View.VISIBLE) {
                                    mLLSearchOnline.setVisibility(View.GONE);
                                }
                                fillDataReyclerFull();
                                break;

                            case R.id.nav_bottom_lichsu:
                                menuBottom = Common.MENU_BOTTOM_KD.LICH_SU;

                                //show fab
                                if (mFab.getVisibility() == View.VISIBLE) {
                                    mFab.setVisibility(View.GONE);
                                }
                                mFab.setImageResource(R.mipmap.ic_upload_white);
                                mFab.show(true);

                                //hide search online
                                if (mLLSearchOnline.getVisibility() == View.VISIBLE) {
                                    mLLSearchOnline.setVisibility(View.GONE);
                                }

                                mListHistory.clear();

                                String TYPE_TBL_CTO = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? Common.TYPE_TBL_CTO.KD.getCode() : Common.TYPE_TBL_CTO.PB.getCode();
                                mListHistory = mSqlDAO.getBydateALLHistoryCto(mDate, TYPE_TBL_CTO, Common.DATE_TIME_TYPE.ddMMyyyyHHmmss, mKieuChuongTrinh);
                                fillDataRecylerHistory();
                                break;
                        }


                    } catch (Exception e) {
                        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, Common.MESSAGE.ex02.getContent(), Snackbar.LENGTH_LONG);
                        snackbar.show();
//                        Toast.makeText(MainKiemDinhActivity.this, Common.MESSAGE.ex02.getContent(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        return;
                    }
                }
            });

            //button fab
            mFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //click fab
                    try {
                        if (mRvUpload.getVisibility() == View.GONE) {
                            //bật form upload
                            mFab.hide(true);
                            mFab.setImageResource(R.mipmap.ic_arrow_left);
                            mFab.show(true);

                            Common.runAnimationClickView(mRvUpload, R.anim.bottom_up, TIME_DELAY_ANIM);
                            mRvUpload.setVisibility(View.VISIBLE);
                            mNavigation.setVisibility(View.GONE);

                            //set tự động trên khung upload
                            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRvCto.getLayoutParams();
                            params.addRule(RelativeLayout.ABOVE, R.id.rl_upload_data);
                            mRvCto.setLayoutParams(params);

                            //TODO mark 2
                            //chuẩn bị dữ liệu upload
                            prepareDataUpload();

                        } else {
                            //tắt form upload
                            mFab.hide(true);
                            mFab.setImageResource(R.mipmap.ic_upload_white);
                            mFab.show(true);
                            Common.runAnimationClickView(mRvUpload, R.anim.bottom_down, TIME_DELAY_ANIM);
                            mRvUpload.setVisibility(View.GONE);
                            mNavigation.setVisibility(View.VISIBLE);
                            //set tự động trên khung bottom menu
                            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRvCto.getLayoutParams();
                            params.addRule(RelativeLayout.ABOVE, mNavigation.getId());
                            mRvCto.setLayoutParams(params);
                        }
                    } catch (Exception e) {
                        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        e.printStackTrace();
                    }
                }
            });

            mBtnUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        //TODO mark3
                        upload();
                    } catch (Exception e) {
                        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        e.printStackTrace();
                    }
                }
            });

            mDeleteAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (menuBottom == Common.MENU_BOTTOM_KD.ALL) {
                            //xóa tất cả row
                            mListCtoKD.clear();
                            mListCtoPB.clear();
                            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                                mSqlDAO.deleteByDateAllCongToKD(Common.convertDateUIToDateSQL(mDate));
                            }

                            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) {
                                mSqlDAO.deleteByDateAllCongToPB(Common.convertDateUIToDateSQL(mDate));
                            }

                            fillDataReyclerFull();
                        }

                        if (menuBottom == Common.MENU_BOTTOM_KD.DS_GHIM) {
                            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                                //bỏ tất cả các cto ghim nếu nó gửi lên THÀNH CÔNG
                                mSqlDAO.updateGhimCtoKDUploadSuccess(Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode());
                                mListCtoKD.clear();
                                mListCtoKD = mSqlDAO.getByDateAllCongToGhimKD(Common.convertDateUIToDateSQL(mDate));

                            }

                            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) {
                                mSqlDAO.updateGhimCtoPBUploadSuccess(Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode());
                                mListCtoPB.clear();
                                mListCtoPB = mSqlDAO.getByDateAllCongToGhimPB(Common.convertDateUIToDateSQL(mDate));
                            }

                            fillDataReyclerFull();
                        }

                        if (menuBottom == Common.MENU_BOTTOM_KD.LICH_SU) {

                            Common.TYPE_TBL_CTO typeTblCto = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? Common.TYPE_TBL_CTO.KD : Common.TYPE_TBL_CTO.PB;
                            mSqlDAO.getByDateDeleteAllHistory(mDate, typeTblCto);
                            mListHistory.clear();
                            mListHistory = mSqlDAO.getBydateALLHistoryCto(mDate, typeTblCto.getCode(), Common.DATE_TIME_TYPE.ddMMyyyyHHmmss, mKieuChuongTrinh);

                            fillDataRecylerHistory();
                        }

                    } catch (Exception e) {
                        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
            e.printStackTrace();
        }
    }


    private void fillDataRecylerHistory() throws Exception {
//        mRvCto.removeAllViews();
        int mCountCto = mListHistory.size();
        if (mHistoryAdapter == null) {
            mHistoryAdapter = new DsHistoryAdapter(this, mListHistory, mKieuChuongTrinh);
            mHistoryAdapter.setMenuBottom(menuBottom);
            mRvCto.setAdapter(mHistoryAdapter);
        } else {
            mHistoryAdapter.setMenuBottom(menuBottom);
            mHistoryAdapter.refresh(mListHistory);

        }

        if (mRvCto.getAdapter() instanceof DsHistoryAdapter && (menuBottom == Common.MENU_BOTTOM_KD.ALL || menuBottom == Common.MENU_BOTTOM_KD.DS_GHIM)) {
            mRvCto.removeAllViews();
            mRvCto.swapAdapter(mCtoAdapter, true);
        }

        if (mRvCto.getAdapter() instanceof DsCongToAdapter && menuBottom == Common.MENU_BOTTOM_KD.LICH_SU) {
            mRvCto.removeAllViews();
            mRvCto.swapAdapter(mCtoAdapter, true);
        }

//        mRvCto.swapAdapter(mHistoryAdapter, true);
        mRvCto.invalidate();

        //set text thống kê cto theo số item adapter
        setTextCountCtoAndDate(mCountCto);
    }

    private void upload() throws Exception {
        prepareDataUpload();

        if (mListUploadCtoKD.size() == 0)
            throw new Exception(Common.MESSAGE.ex22.getContent());

        //check thread hiện tại
        if (soapSearchCto != null) {
            if (soapSearchCto.getStatus() == AsyncTask.Status.RUNNING || soapSearchCto.getStatus() == AsyncTask.Status.PENDING) {
                throw new Exception(Common.MESSAGE.ex23.getContent());
            }
        }

        //check url
        if (TextUtils.isEmpty(mURL)) {
            showDialogConfig();
            throw new Exception(Common.MESSAGE.ex08.getContent());
        }

        //check dvi
        if (TextUtils.isEmpty(mStringDvi)) {
            showDialogConfig();
            throw new Exception(Common.MESSAGE.ex09.getContent());
        }

//        String[] requestParams = new String[]{"entity"};


        if (!Common.isNetworkConnected(this)) {
            throw new Exception(Common.MESSAGE.ex07.getContent());
        }

        mListDataUploadGKD.clear();
        mListDataUploadGKD = setupDataCtoGuiKDUpload();
        mCountUploadFinish = mListDataUploadGKD.size();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                while (mCountUploadFinish != 0) {
                Message msg = mHandlerUploadKD.obtainMessage();
                msg.obj = mCountUploadFinish;
                mHandlerUploadKD.sendMessage(msg);
//                }
            }
        });

        thread.start();


//        MainKiemDinhActivity.this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                    mPbarUpload.setVisibility(View.GONE);
//                    mBtnUpload.setVisibility(View.VISIBLE);
//                    mTvStatusUpload.setText("CÓ LỖI");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    private List<Update_GuiKD_CTO> setupDataCtoGuiKDUpload() {
        ArrayList<Update_GuiKD_CTO> listUpload = new ArrayList<>();
        if (mListUploadCtoKD.size() == 0)
            return listUpload;
        for (CongToGuiKDProxy congToGuiKDProxy : mListUploadCtoKD) {
            Update_GuiKD_CTO congToUpload = new Update_GuiKD_CTO(
                    Common.CHON.CHUA_GUI.getCode(),
                    congToGuiKDProxy.getSTT(),
                    congToGuiKDProxy.getMA_CTO(),
                    congToGuiKDProxy.getSO_CTO(),
                    congToGuiKDProxy.getMA_CLOAI(),
                    congToGuiKDProxy.getNGAY_NHAP_HT(),
                    congToGuiKDProxy.getNGAY_NHAP(),
                    congToGuiKDProxy.getNAM_SX(),
                    congToGuiKDProxy.getLOAI_SOHUU(),
                    congToGuiKDProxy.getTEN_SOHUU(),
                    congToGuiKDProxy.getMA_BDONG(),
                    congToGuiKDProxy.getNGAY_BDONG(),
                    congToGuiKDProxy.getNGAY_BDONG_HTAI(),
                    congToGuiKDProxy.getSO_PHA(),
                    congToGuiKDProxy.getSO_DAY(),
                    congToGuiKDProxy.getDONG_DIEN(),
                    congToGuiKDProxy.getVH_CONG(),
                    congToGuiKDProxy.getDIEN_AP(),
                    congToGuiKDProxy.getHS_NHAN(),
                    congToGuiKDProxy.getNGAY_KDINH(),
                    congToGuiKDProxy.getCHISO_THAO(),
                    congToGuiKDProxy.getHSN(),
                    Common.convertDateSQLToDateUI(congToGuiKDProxy.getNGAY_NHAP_MTB()));
            listUpload.add(congToUpload);

        }
        return listUpload;
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();

        mStringDvi = bundle.getString(PARAM_DVI, "");
        mUser = bundle.getString(PARAM_USER, "");
        mURL = bundle.getString(PARAM_SERVER_URL, "");

        int mPosPrograme = bundle.getInt(PARAM_POS_PROGRAME);

        if (mPosPrograme == Common.KIEU_CHUONG_TRINH.KIEM_DINH.getCode()) {
            mKieuChuongTrinh = Common.KIEU_CHUONG_TRINH.KIEM_DINH;
        }

        if (mPosPrograme == Common.KIEU_CHUONG_TRINH.PHAN_BO.getCode()) {
            mKieuChuongTrinh = Common.KIEU_CHUONG_TRINH.PHAN_BO;
        }
    }

    //TODO mark 4
    private void searchOnline(String searchText) throws Exception {
        //check thread hiện tại
        if (soapSearchCto != null) {
            if (soapSearchCto.getStatus() == AsyncTask.Status.RUNNING || soapSearchCto.getStatus() == AsyncTask.Status.PENDING) {
                mEtSearchOnline.setText("");
                throw new Exception(Common.MESSAGE.ex21.getContent());
            }
        }

//        if (!mCbSearchBBan.isChecked()) {
//            int idIfHasExistCto = 0;
//            //check data local với text search và date
//            String date = Common.convertDateUIToDateSQL(mDate);
//            idIfHasExistCto = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mSqlDAO.checkExistByDateTBL_CTO_GUI_KDreturnID(searchText, date) : mSqlDAO.checkExistByDateTBL_CTO_GUI_PBreturnID(searchText, date);
//            //nếu có thì load và không tải
//            if (idIfHasExistCto > 0) {
//                mEtSearchOnline.setText("");
//                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, Common.MESSAGE.ex11.getContent(), Snackbar.LENGTH_LONG);
//                snackbar.show();
//                return;
//            }
//        }

        //check url
        if (TextUtils.isEmpty(mURL)) {
            showDialogConfig();
            throw new Exception(Common.MESSAGE.ex08.getContent());
        }

        //check dvi
        if (TextUtils.isEmpty(mStringDvi)) {
            showDialogConfig();
            throw new Exception(Common.MESSAGE.ex09.getContent());
        }


        String[] requestParams = new String[0];
        if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH)
            requestParams = new String[]{mStringDvi, searchText};


        Common.CHECK check = (mCbSearchBBan.isChecked()) ? Common.CHECK.SEARCH_THEO_BBAN : Common.CHECK.SEARCH_THEO_CONG_TO;

        if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO)
            requestParams = new String[]{mStringDvi, searchText, check.getCode()};


        if (!Common.isNetworkConnected(this)) {
            throw new Exception(Common.MESSAGE.ex07.getContent());
        }

        SoapXML.AsyncSoap.AsyncSoapCallBack<List<CToPBResponse>, ThongBaoResponse> callBackCToPB = new SoapXML.AsyncSoap.AsyncSoapCallBack<List<CToPBResponse>, ThongBaoResponse>() {
            @Override
            public void onPre(SoapXML.AsyncSoap soap) {
                //show progress bar
                showProgresbar(true);
                mEtSearchOnline.setEnabled(false);
            }

            @Override
            public void onUpdate(String message) {
                //ẩn progress bar
                showProgresbar(false);
                mEtSearchOnline.setEnabled(true);
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
                snackbar.show();
                try {
                    //insert history
                    History history = new History();
                    history.setID_TBL_CTO(0);
                    history.setTYPE_RESULT(Common.TYPE_RESULT.ERROR.getCode());
                    history.setTYPE_SESSION(Common.TYPE_SESSION.DOWNLOAD.getCode());
                    history.setTYPE_TBL_CTO(mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH ? Common.TYPE_TBL_CTO.KD.getCode() : Common.TYPE_TBL_CTO.PB.getCode());

                    //convert time to date SQL
                    long dateSql = Common.convertDateToLong(time, Common.DATE_TIME_TYPE.ddMMyyyyHHmmss);
                    history.setDATE_SESSION(String.valueOf(dateSql));

                    history.setDATE_SESSION(Common.getDateTimeNow(Common.DATE_TIME_TYPE.ddMMyyyyHHmmss));
                    history.setINFO_SEARCH(mEtSearchOnline.getText().toString());
                    mSqlDAO.insertTBL_HISTORY(history);
                } catch (Exception e) {
                    snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }

            @Override
            public void onPostData(List<CToPBResponse> dataResponse) {
                Log.d(TAG, "onPostData: ");
                //ẩn progress bar
                showProgresbar(false);
                mEtSearchOnline.setEnabled(true);
                //Xử lý kết quả
                try {
                    doProcessAfterSearchOnlinePBWithIDBBan(dataResponse);
                } catch (Exception e) {
                    Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onPostMessageSever(String errorResponse) {
                Log.d(TAG, "onPostMessageSever: ");
                onUpdate(errorResponse);
            }
        };


        SoapXML.AsyncSoap.AsyncSoapCallBack<List<CToKDResponse>, ThongBaoResponse> callBackCToKD = new SoapXML.AsyncSoap.AsyncSoapCallBack<List<CToKDResponse>, ThongBaoResponse>() {
            @Override
            public void onPre(SoapXML.AsyncSoap soap) {
                //show progress bar
                showProgresbar(true);
                mEtSearchOnline.setEnabled(false);
            }

            @Override
            public void onUpdate(String message) {
                //ẩn progress bar
                showProgresbar(false);
                mEtSearchOnline.setEnabled(true);
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
                snackbar.show();
                try {
                    //insert history
                    History history = new History();
                    history.setID_TBL_CTO(0);
                    history.setTYPE_RESULT(Common.TYPE_RESULT.ERROR.getCode());
                    history.setTYPE_SESSION(Common.TYPE_SESSION.DOWNLOAD.getCode());
                    history.setTYPE_TBL_CTO(mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH ? Common.TYPE_TBL_CTO.KD.getCode() : Common.TYPE_TBL_CTO.PB.getCode());

                    //convert time to date SQL
                    long dateSql = Common.convertDateToLong(time, Common.DATE_TIME_TYPE.ddMMyyyyHHmmss);
                    history.setDATE_SESSION(String.valueOf(dateSql));

                    history.setDATE_SESSION(Common.getDateTimeNow(Common.DATE_TIME_TYPE.ddMMyyyyHHmmss));
                    history.setINFO_SEARCH(mEtSearchOnline.getText().toString());
                    mSqlDAO.insertTBL_HISTORY(history);
                } catch (Exception e) {
                    snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }

            @Override
            public void onPostData(List<CToKDResponse> dataResponse) {
                Log.d(TAG, "onPostData: ");
                //ẩn progress bar
                showProgresbar(false);
                mEtSearchOnline.setEnabled(true);
                //Xử lý kết quả
                try {
                    doProcessAfterSearchOnline(dataResponse);
                } catch (Exception e) {
                    Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onPostMessageSever(String errorResponse) {
                Log.d(TAG, "onPostMessageSever: ");
                onUpdate(errorResponse);
            }

//            @Override
//            public void onPostMessageSever(ThongBaoResponse errorResponse) {
//                Log.d(TAG, "onPostMessageSever: ");
//                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, errorResponse.getThongbao(), Snackbar.LENGTH_LONG);
//                snackbar.show();
//            }
        };
        if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
            soapSearchCto = new SoapXML.AsyncSoap(
                    CToKDResponse.class,
                    ThongBaoResponse.class,
                    "thongbao",
                    callBackCToKD,
                    METHOD.CTO_KD.getNameMethod(),
                    SoapXML.getURL(mURL),
                    METHOD.CTO_KD.getNameParams()
            );
        }

        if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) {
            if (mCbSearchBBan.isChecked()) {
                soapSearchCto = new SoapXML.AsyncSoap(
                        CToPBResponse.class,
                        ThongBaoResponse.class,
                        "thongbao",
                        callBackCToPB,
                        METHOD.CTO_PB.getNameMethod(),
                        SoapXML.getURL(mURL),
                        METHOD.CTO_PB.getNameParams()
                );

            } else
                soapSearchCto = new SoapXML.AsyncSoap(
                        CToKDResponse.class,
                        ThongBaoResponse.class,
                        "thongbao",
                        callBackCToKD,
                        METHOD.CTO_PB.getNameMethod(),
                        SoapXML.getURL(mURL),
                        METHOD.CTO_PB.getNameParams()
                );
        }
        time = getDateTimeNow(Common.DATE_TIME_TYPE.ddMMyyyyHHmmss);
        soapSearchCto.execute(requestParams);

    }

    private void doProcessAfterSearchOnlinePBWithIDBBan(List<CToPBResponse> listResponse) throws Exception {
        if (listResponse == null)
            throw new Exception(Common.MESSAGE.ex06.getContent());

        for (CToPBResponse cToPBResponse : listResponse) {
            //lấy các dữ liệu cần thiết ghi vào database
            int id = 0;

            CongToPB congToPB = new CongToPB();
            congToPB.setCHON(cToPBResponse.getCHON());
//            congToPB.setSTT(cToPBResponse.getSTT());
            congToPB.setMA_CTO(cToPBResponse.getMA_CTO());
            congToPB.setSO_CTO(cToPBResponse.getSO_CTO());
            congToPB.setMA_DVIQLY(cToPBResponse.getMA_DVIQLY());
            congToPB.setMA_CLOAI(cToPBResponse.getMA_CLOAI());
            congToPB.setNGAY_NHAP_HT(cToPBResponse.getNGAY_NHAP_HTHONG());
            congToPB.setNAM_SX(cToPBResponse.getNAM_SX());
            congToPB.setLOAI_SOHUU(cToPBResponse.getLOAI_SOHUU());
//            congToPB.setTEN_SOHUU(cToPBResponse.getTEN_SOHUU());

            congToPB.setMA_BDONG(cToPBResponse.getMA_BDONG());
            congToPB.setNGAY_BDONG(cToPBResponse.getNGAY_BDONG());
//            congToPB.setNGAY_BDONG_HTAI(cToPBResponse.getNGAY_BDONG_HTAI());
            congToPB.setSO_PHA(cToPBResponse.getSO_PHA());
            congToPB.setSO_DAY(cToPBResponse.getSO_DAY());
            congToPB.setDONG_DIEN(cToPBResponse.getDONG_DIEN());
            congToPB.setVH_CONG(cToPBResponse.getVH_CONG());

            congToPB.setDIEN_AP(cToPBResponse.getDIEN_AP());
            congToPB.setHS_NHAN(cToPBResponse.getHS_NHAN());
            congToPB.setNGAY_KDINH(cToPBResponse.getNGAY_KDINH());
//            congToPB.setCHISO_THAO(cToPBResponse.getCHISO_THAO());
//            congToPB.setHSN(cToPBResponse.getHSN());
            congToPB.setNGAY_NHAP(Common.convertDateToDate(cToPBResponse.getNGAY_NHAP(), Common.DATE_TIME_TYPE.yyyyMMddHHmmssSSZ, Common.DATE_TIME_TYPE.ddMMyyyy));
            congToPB.setNGAY_NHAP_MTB(Common.getDateTimeNow(Common.DATE_TIME_TYPE.yyyyMMdd));
            congToPB.setTRANG_THAI_GHIM(Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode());
            congToPB.setTRANG_THAI_CHON(Common.TRANG_THAI_CHON.CHUA_CHON.getCode());

            int idIfHasExistCto = 0;
            //check data local với text search và date
            String date = Common.convertDateUIToDateSQL(mDate);
            idIfHasExistCto = mSqlDAO.checkExistByDateTBL_CTO_GUI_PBreturnID(cToPBResponse.getMA_CTO(), date);
            if (idIfHasExistCto > 0) {
                //nếu có thì xóa cũ và insert mới
                mSqlDAO.deleteCongToPB(idIfHasExistCto);
            }

            id = mSqlDAO.insertTBL_CTO_PB(congToPB);

            //insert history
            if (id != 0) {
                History history = new History();
                history.setID_TBL_CTO(id);
                history.setTYPE_RESULT(Common.TYPE_RESULT.SUCCESS.getCode());
                history.setTYPE_SESSION(Common.TYPE_SESSION.DOWNLOAD.getCode());
                history.setTYPE_TBL_CTO(mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH ? Common.TYPE_TBL_CTO.KD.getCode() : Common.TYPE_TBL_CTO.PB.getCode());

                //convert time to date SQL
                long dateSql = Common.convertDateToLong(time, Common.DATE_TIME_TYPE.ddMMyyyyHHmmss);
                history.setDATE_SESSION(String.valueOf(dateSql));
                history.setINFO_SEARCH(mEtSearchOnline.getText().toString());
                mSqlDAO.insertTBL_HISTORY(history);
            }
        }


        //làm mới lại mListDataUploadGKD với việc search local
        searchLocalSessionSuccess(time, Common.TYPE_SESSION.DOWNLOAD, Common.TYPE_RESULT.SUCCESS);
//        searchLocal(mEtSearchOnline.getText().toString());
    }

    private void searchLocalSessionSuccess(String time, Common.TYPE_SESSION TYPE_SESSION, Common.TYPE_RESULT TYPE_RESULT) {
        long dateSql = Common.convertDateToLong(time, Common.DATE_TIME_TYPE.ddMMyyyyHHmmss);

        try {
            if (menuBottom == Common.MENU_BOTTOM_KD.ALL) {
                mListCtoKD.clear();
                if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                    mListCtoKD = mSqlDAO.getByDateAllCongToKDByDATE_SESSIONByTYPE_RESULT(String.valueOf(dateSql), TYPE_SESSION, TYPE_RESULT);
                } else {
                    mListCtoPB = mSqlDAO.getByDateAllCongToPBByDATE_SESSIONByTYPE_RESULT(String.valueOf(dateSql), TYPE_SESSION, TYPE_RESULT);
                }
            }

            fillDataReyclerLocal(mListCtoKD, mListCtoPB);
        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
            e.printStackTrace();
            Log.e(MainKiemDinhActivity.class.getName(), "interactionDataCongToSearch: " + e.getMessage());
        }
    }

    private void doProcessAfterSearchOnline(List<CToKDResponse> listResponse) throws Exception {
        if (listResponse == null)
            throw new Exception(Common.MESSAGE.ex06.getContent());

        for (CToKDResponse cToKDResponse : listResponse) {
            //lấy các dữ liệu cần thiết ghi vào database
            int id = 0;
            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                CongToGuiKD congToGuiKD = new CongToGuiKD();
                congToGuiKD.setCHON(cToKDResponse.getCHON());
                congToGuiKD.setSTT(cToKDResponse.getSTT());
                congToGuiKD.setMA_CTO(cToKDResponse.getMA_CTO());
                congToGuiKD.setSO_CTO(cToKDResponse.getSO_CTO());
                congToGuiKD.setMA_DVIQLY(mStringDvi);
                congToGuiKD.setMA_CLOAI(cToKDResponse.getMA_CLOAI());
                congToGuiKD.setNGAY_NHAP_HT(cToKDResponse.getNGAY_NHAP_HT());
                congToGuiKD.setNAM_SX(cToKDResponse.getNAM_SX());
                congToGuiKD.setLOAI_SOHUU(cToKDResponse.getLOAI_SOHUU());
                congToGuiKD.setTEN_SOHUU(cToKDResponse.getTEN_SOHUU());

                congToGuiKD.setMA_BDONG(cToKDResponse.getMA_BDONG());
                congToGuiKD.setNGAY_BDONG(cToKDResponse.getNGAY_BDONG());
                congToGuiKD.setNGAY_BDONG_HTAI(cToKDResponse.getNGAY_BDONG_HTAI());
                congToGuiKD.setSO_PHA(cToKDResponse.getSO_PHA());
                congToGuiKD.setSO_DAY(cToKDResponse.getSO_DAY());
                congToGuiKD.setDONG_DIEN(cToKDResponse.getDONG_DIEN());
                congToGuiKD.setVH_CONG(cToKDResponse.getVH_CONG());

                congToGuiKD.setDIEN_AP(cToKDResponse.getDIEN_AP());
                congToGuiKD.setHS_NHAN(cToKDResponse.getHS_NHAN());
                congToGuiKD.setNGAY_KDINH(cToKDResponse.getNGAY_KDINH());
                congToGuiKD.setCHISO_THAO(cToKDResponse.getCHISO_THAO());
                congToGuiKD.setHSN(cToKDResponse.getHSN());
                congToGuiKD.setNGAY_NHAP(Common.convertDateToDate(cToKDResponse.getNGAY_NHAP(), Common.DATE_TIME_TYPE.yyyyMMddHHmmssSSZ, Common.DATE_TIME_TYPE.ddMMyyyy));
                congToGuiKD.setNGAY_NHAP_MTB(Common.getDateTimeNow(Common.DATE_TIME_TYPE.yyyyMMdd));
                congToGuiKD.setTRANG_THAI_GHIM(Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode());
                congToGuiKD.setTRANG_THAI_CHON(Common.TRANG_THAI_CHON.CHUA_CHON.getCode());

                int idIfHasExistCto = 0;
                //check data local với text search và date
                String date = Common.convertDateUIToDateSQL(mDate);
                idIfHasExistCto = mSqlDAO.checkExistByDateTBL_CTO_GUI_KDreturnID(cToKDResponse.getMA_CTO(), date);
                if (idIfHasExistCto > 0) {
                    //nếu có thì xóa cũ và insert mới
                    mSqlDAO.deleteCongToPB(idIfHasExistCto);
                }

                id = mSqlDAO.insertTBL_CTO_GUI_KD(congToGuiKD);
            } else {
                CongToPB congToPB = new CongToPB();
                congToPB.setCHON(cToKDResponse.getCHON());
                congToPB.setSTT(cToKDResponse.getSTT());
                congToPB.setMA_CTO(cToKDResponse.getMA_CTO());
                congToPB.setSO_CTO(cToKDResponse.getSO_CTO());
                congToPB.setMA_DVIQLY(mStringDvi);
                congToPB.setMA_CLOAI(cToKDResponse.getMA_CLOAI());
                congToPB.setNGAY_NHAP_HT(cToKDResponse.getNGAY_NHAP_HT());
                congToPB.setNAM_SX(cToKDResponse.getNAM_SX());
                congToPB.setLOAI_SOHUU(cToKDResponse.getLOAI_SOHUU());
                congToPB.setTEN_SOHUU(cToKDResponse.getTEN_SOHUU());

                congToPB.setMA_BDONG(cToKDResponse.getMA_BDONG());
                congToPB.setNGAY_BDONG(cToKDResponse.getNGAY_BDONG());
                congToPB.setNGAY_BDONG_HTAI(cToKDResponse.getNGAY_BDONG_HTAI());
                congToPB.setSO_PHA(cToKDResponse.getSO_PHA());
                congToPB.setSO_DAY(cToKDResponse.getSO_DAY());
                congToPB.setDONG_DIEN(cToKDResponse.getDONG_DIEN());
                congToPB.setVH_CONG(cToKDResponse.getVH_CONG());

                congToPB.setDIEN_AP(cToKDResponse.getDIEN_AP());
                congToPB.setHS_NHAN(cToKDResponse.getHS_NHAN());
                congToPB.setNGAY_KDINH(cToKDResponse.getNGAY_KDINH());
                congToPB.setCHISO_THAO(cToKDResponse.getCHISO_THAO());
                congToPB.setHSN(cToKDResponse.getHSN());
                congToPB.setNGAY_NHAP(Common.convertDateToDate(cToKDResponse.getNGAY_NHAP(), Common.DATE_TIME_TYPE.yyyyMMddHHmmssSSZ, Common.DATE_TIME_TYPE.ddMMyyyy));
                congToPB.setNGAY_NHAP_MTB(Common.getDateTimeNow(Common.DATE_TIME_TYPE.yyyyMMdd));
                congToPB.setTRANG_THAI_GHIM(Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode());
                congToPB.setTRANG_THAI_CHON(Common.TRANG_THAI_CHON.CHUA_CHON.getCode());

                int idIfHasExistCto = 0;
                //check data local với text search và date
                String date = Common.convertDateUIToDateSQL(mDate);
                idIfHasExistCto = mSqlDAO.checkExistByDateTBL_CTO_GUI_PBreturnID(cToKDResponse.getMA_CTO(), date);
                if (idIfHasExistCto > 0) {
                    //nếu có thì xóa cũ và insert mới
                    mSqlDAO.deleteCongToPB(idIfHasExistCto);
                }

                id = mSqlDAO.insertTBL_CTO_PB(congToPB);
            }
            //insert history
            if (id != 0) {
                History history = new History();
                history.setID_TBL_CTO(id);
                history.setTYPE_RESULT(Common.TYPE_RESULT.SUCCESS.getCode());
                history.setTYPE_SESSION(Common.TYPE_SESSION.DOWNLOAD.getCode());
                history.setTYPE_TBL_CTO(mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH ? Common.TYPE_TBL_CTO.KD.getCode() : Common.TYPE_TBL_CTO.PB.getCode());

                //convert time to date SQL
                long dateSql = Common.convertDateToLong(time, Common.DATE_TIME_TYPE.ddMMyyyyHHmmss);
                history.setDATE_SESSION(String.valueOf(dateSql));
                history.setINFO_SEARCH(mEtSearchOnline.getText().toString());
                mSqlDAO.insertTBL_HISTORY(history);
            }

        }

        //làm mới lại mListDataUploadGKD với việc search local
        searchLocal(mEtSearchOnline.getText().toString());
    }

    //

    private void showProgresbar(boolean isShow) {
        if (isShow) {
            //hiện
            if (mPbarSearchOnline.getVisibility() == View.GONE) {
                mBtnSearchOnline.setVisibility(View.GONE);
                mPbarSearchOnline.setVisibility(View.VISIBLE);
            }

        } else {
            //ẩn progress bar
            if (mPbarSearchOnline.getVisibility() == View.VISIBLE) {
                mBtnSearchOnline.setVisibility(View.VISIBLE);
                mPbarSearchOnline.setVisibility(View.GONE);
            }
        }
    }

    private void showDialogChooseDate() {
        DateTimePickerFragment dateTimePickerFragment = new DateTimePickerFragment();
        dateTimePickerFragment.setListener(this);
        dateTimePickerFragment.show(getSupportFragmentManager(), "DateMonthYearPickerFragment");
    }

    //region DsCongToAdapter.OnDsCtoAdapterIteraction
    @Override
    public void clickBtnGhimRowCto(int pos) {
        int a = 0;
        if (pos >= (a = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListCtoKD.size() : mListCtoPB.size()))
            return;

        try {
            //set filterDataReal GhimCto đồng thời đánh dấu cần refersh lại giá trị công tơ đó
            int ID = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListCtoKD.get(pos).getID() : mListCtoPB.get(pos).getID();

            int statusGhimCto = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListCtoKD.get(pos).getTRANG_THAI_GHIM() : mListCtoPB.get(pos).getTRANG_THAI_GHIM();

            if (statusGhimCto == Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode())
                statusGhimCto = Common.TRANG_THAI_GHIM.DA_GHIM.getCode();
            else {
                statusGhimCto = Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode();
                mSqlDAO.updateTRANG_THAI_CHONCto(ID, Common.TRANG_THAI_CHON.CHUA_CHON.getCode(), mKieuChuongTrinh);
            }

            mSqlDAO.updateGhimCtoKD(ID, statusGhimCto);


            mListCtoKD.clear();
            String dateSQL = Common.convertDateUIToDateSQL(mDate);

            if (menuBottom == Common.MENU_BOTTOM_KD.ALL) {
                mListCtoKD.clear();
                mListCtoKD = mSqlDAO.getByDateAllCongToKD(dateSQL);
            }

            if (menuBottom == Common.MENU_BOTTOM_KD.DS_GHIM) {
                mListCtoKD.clear();
                mListCtoKD = mSqlDAO.getByDateAllCongToGhimKD(dateSQL);

                prepareDataUpload();
            }

            fillDataReyclerFull();
//            ((DsCongToAdapter)mRvCto.getAdapter()).notifyDataSetChanged();
            mRvCto.invalidate();

        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
            e.printStackTrace();
        }
    }

    private void prepareDataUpload() throws Exception {
        String dateSQL = Common.convertDateUIToDateSQL(mDate);
        mListUploadCtoKD.clear();
        mListUploadCtoKD = mSqlDAO.getByDateAllCongToGhimAndChonKD(dateSQL);
        mTvCountCtoUpload.setText(mListUploadCtoKD.size() + "");

    }

    @Override
    public void clickBtnChiTiet(int pos) {
    }

    @Override
    public void clickBtnXoa(final int pos) {
        int size = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListCtoKD.size() : mListCtoPB.size();
        if (pos >= size) {
            return;
        }
        OnClickButtonAlertDialog onClickButtonAlertDialog = new OnClickButtonAlertDialog() {
            @Override
            public void doClickYes() {
                try {
                    int idRowDelete;
                    if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                        CongToGuiKDProxy congToGuiKDProxy = mListCtoKD.get(pos);
                        idRowDelete = mSqlDAO.deleteCongToKD(congToGuiKDProxy.getID());
                    } else {
                        CongToPBProxy congToGuiPBProxy = mListCtoPB.get(pos);
                        idRowDelete = mSqlDAO.deleteCongToPB(congToGuiPBProxy.getID());
                    }


                    if (menuBottom == Common.MENU_BOTTOM_KD.ALL) {
                        //xóa lịch sử
//                        mSqlDAO.getByDateDeleteHistory(idRowDelete);
                        if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                            mListCtoKD.clear();
                            mListCtoKD = mSqlDAO.getByDateAllCongToKD(Common.convertDateUIToDateSQL(mDate));
                        }

                        if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) {
                            mListCtoPB.clear();
                            mListCtoPB = mSqlDAO.getByDateAllCongToPB(Common.convertDateUIToDateSQL(mDate));
                        }
                        fillDataReyclerFull();
                    }


//                    if (menuBottom == Common.MENU_BOTTOM_KD.DS_GHIM) {
//                        mListCtoKD.clear();
//                        mListCtoKD = mSqlDAO.getByDateAllCongToGhimKD(Common.convertDateUIToDateSQL(mDate));
//                        fillDataReyclerFull();
//                    }


                } catch (Exception e) {
                    Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    e.printStackTrace();
                }
            }

            @Override
            public void doClickNo() {
                Toast.makeText(MainKiemDinhActivity.this, "doClickNo", Toast.LENGTH_SHORT).show();
            }
        };

        try {
            Common.showAlertDialog(this, onClickButtonAlertDialog, "Xóa dữ liệu công tơ", "Bạn có chắc muốn xóa công tơ này?");
        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, Common.MESSAGE.ex10.getContent(), Snackbar.LENGTH_LONG);
            snackbar.show();
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void clickBtnChonRowCto(int pos) {
        if (pos >= mListCtoKD.size())
            return;

        try {
            //set filterDataReal GhimCto đồng thời đánh dấu cần refersh lại giá trị công tơ đó
            int ID = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListCtoKD.get(pos).getID() : mListCtoPB.get(pos).getID();

            int statusChonCto = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListCtoKD.get(pos).getTRANG_THAI_CHON() : mListCtoPB.get(pos).getTRANG_THAI_CHON();
            if (statusChonCto == Common.TRANG_THAI_CHON.CHUA_CHON.getCode())
                statusChonCto = Common.TRANG_THAI_CHON.DA_CHON.getCode();
            else
                statusChonCto = Common.TRANG_THAI_CHON.CHUA_CHON.getCode();
            mSqlDAO.updateTRANG_THAI_CHONCto(ID, statusChonCto, mKieuChuongTrinh);


            String dateSQL = Common.convertDateUIToDateSQL(mDate);

            if (menuBottom == Common.MENU_BOTTOM_KD.ALL) {
                if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                    mListCtoKD.clear();
                    mListCtoKD = mSqlDAO.getByDateAllCongToKD(dateSQL);
                }

                if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) {
                    mListCtoPB.clear();
                    mListCtoPB = mSqlDAO.getByDateAllCongToPB(dateSQL);
                }
            }

            if (menuBottom == Common.MENU_BOTTOM_KD.DS_GHIM) {

                if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                    mListCtoKD.clear();
                    mListCtoKD = mSqlDAO.getByDateAllCongToGhimKD(dateSQL);
                }

                if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) {
                    mListCtoPB.clear();
                    mListCtoPB = mSqlDAO.getByDateAllCongToGhimPB(dateSQL);
                }

                prepareDataUpload();

            }

            fillDataReyclerFull();
            mRvCto.invalidate();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //endregion

    public void searchLocal(CharSequence charSearch) {
        try {
            String dateSql = Common.convertDateUIToDateSQL(mDate);
            if (menuBottom == Common.MENU_BOTTOM_KD.ALL) {
                mListCtoKD.clear();
                if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                    mListCtoKD = mSqlDAO.getByDateAllCongToKD(dateSql);
                } else {
                    mListCtoPB = mSqlDAO.getByDateAllCongToPB(dateSql);
                }
            }

            if (menuBottom == Common.MENU_BOTTOM_KD.DS_GHIM) {
                mListCtoKD.clear();
                if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                    mListCtoKD = mSqlDAO.getByDateAllCongToGhimKD(dateSql);
                } else {
                    mListCtoPB = mSqlDAO.getByDateAllCongToGhimPB(dateSql);
                }
            }

            searchLocalOnDate(charSearch);
        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
            e.printStackTrace();
            Log.e(MainKiemDinhActivity.class.getName(), "interactionDataCongToSearch: " + e.getMessage());
        }
    }

    /**
     * sau khi chọn ngày xong thì search trong ngày đó
     *
     * @param charSearch
     */
    public void searchLocalOnDate(CharSequence charSearch) {
        try {
            String query = Common.removeAccent(charSearch.toString().trim().toLowerCase());
            List<CongToGuiKDProxy> dataKD = new ArrayList<>();
            List<CongToPBProxy> dataPB = new ArrayList<>();
            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                for (CongToGuiKDProxy congToGuiKDProxy : mListCtoKD) {
                    if (Common.removeAccent(congToGuiKDProxy.getMA_CTO().toLowerCase()).contains(query)
                            || Common.removeAccent(congToGuiKDProxy.getSO_CTO().toLowerCase()).contains(query)
                            || convertDateSQLToDateUI(congToGuiKDProxy.getNGAY_NHAP_MTB()).contains(query)) {
                        dataKD.add(congToGuiKDProxy);
                    }
                }

                //giữ nguyên dữ liệu, lọc cái cần dùng
                fillDataReyclerLocal(dataKD, dataPB);
            }
            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) {
                for (CongToPBProxy congToPBProxy : mListCtoPB) {
                    if (Common.removeAccent(congToPBProxy.getMA_CTO().toLowerCase()).contains(query)
                            || Common.removeAccent(congToPBProxy.getSO_CTO().toLowerCase()).contains(query)
                            || convertDateSQLToDateUI(congToPBProxy.getNGAY_NHAP_MTB()).contains(query)) {
                        dataPB.add(congToPBProxy);
                    }
                }

                //giữ nguyên dữ liệu, lọc cái cần dùng
                fillDataReyclerLocal(dataKD, dataPB);
            }

        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
            e.printStackTrace();
            Log.e(MainKiemDinhActivity.class.getName(), "interactionDataCongToSearch: " + e.getMessage());
        }
    }

    private void fillDataReyclerFull() throws Exception {
//        mRvCto.removeAllViews();

        int mCountCto = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListCtoKD.size() : mListCtoPB.size();
        if (mCtoAdapter == null) {
            mCtoAdapter = new DsCongToAdapter(this, mListCtoKD, mListCtoPB, mKieuChuongTrinh);
            mCtoAdapter.setMenuBottom(menuBottom);
            mRvCto.setAdapter(mCtoAdapter);
        } else {
            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH)
                mCtoAdapter.refreshListKD(mListCtoKD);
            else
                mCtoAdapter.refreshListPB(mListCtoPB);
            mCtoAdapter.setMenuBottom(menuBottom);
        }

        if (mRvCto.getAdapter() instanceof DsHistoryAdapter && (menuBottom == Common.MENU_BOTTOM_KD.ALL || menuBottom == Common.MENU_BOTTOM_KD.DS_GHIM)) {
            mRvCto.removeAllViews();
            mRvCto.swapAdapter(mCtoAdapter, true);
        }

        if (mRvCto.getAdapter() instanceof DsCongToAdapter && menuBottom == Common.MENU_BOTTOM_KD.LICH_SU) {
            mRvCto.removeAllViews();
            mRvCto.swapAdapter(mCtoAdapter, true);
        }

//        mRvCto.swapAdapter(mCtoAdapter, true);
        mRvCto.invalidate();

        //set text thống kê cto theo số item adapter
        setTextCountCtoAndDate(mCountCto);
//        int mCountCto = 0;
//        mCtoAdapter = new DsCongToAdapter(this, mListCtoKD);
//        ((DsCongToAdapter) mCtoAdapter).setMenuBottom(menuBottom);
//        mRvCto.setAdapter(mCtoAdapter);
//        mCountCto = mListCtoKD.size();
//
//        mRvCto.invalidate();
//
//        //set text thống kê cto theo số item adapter
//        setTextCountCtoAndDate(mCountCto);
    }

    private void fillDataReyclerLocal(List<CongToGuiKDProxy> dataKD, List<CongToPBProxy> dataPB) throws Exception {
        int mCountCto = 0;

        mCountCto = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? dataKD.size() : dataPB.size();
        if (mCtoAdapter == null) {
            mCtoAdapter = new DsCongToAdapter(this, dataKD, dataPB, mKieuChuongTrinh);
            ((DsCongToAdapter) mCtoAdapter).setMenuBottom(menuBottom);
            mRvCto.setAdapter(mCtoAdapter);
        } else {
            if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
                mCtoAdapter.refreshListKD(dataKD);
            } else
                mCtoAdapter.refreshListPB(dataPB);
        }


        mRvCto.invalidate();
        //set text thống kê cto theo số item adapter
        setTextCountCtoAndDate(mCountCto);
    }

    //region DatePickerDialog.OnDateSetListener
    @Override
    public void onDateSet(DatePicker datePicker, int formatedYeah, int formatedMonth, int formatedDate) {
        //TODO formatedMonth start from index 0
        StringBuilder time = new StringBuilder();
        if (formatedDate < 10) {
            time.append("0" + formatedDate);
        } else time.append(formatedDate);
        time.append("/");

        if (formatedMonth < 10) {
            time.append("0" + formatedMonth);
        } else time.append(formatedMonth);
        time.append("/");

        time.append(formatedYeah);

        mDate = time.toString();
        mTvDate.setText(mDate);

        //tìm kiếm local và clear các input field
        mEtSearchOnline.setText("");
        mEtSearchLocal.setText("");
        searchLocal("");
    }

    private void processBluetooth() {
        mEtSearchOnline.setFocusable(true);
        Intent settingsIntent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
        startActivity(settingsIntent);
    }
}
