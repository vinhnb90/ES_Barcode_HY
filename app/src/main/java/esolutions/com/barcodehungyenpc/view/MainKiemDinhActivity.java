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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.database.SqlConnect;
import esolutions.com.barcodehungyenpc.database.SqlDAO;
import esolutions.com.barcodehungyenpc.entity.CToPBResponse;
import esolutions.com.barcodehungyenpc.entity.CongTo;
import esolutions.com.barcodehungyenpc.entity.CongToProxy;
import esolutions.com.barcodehungyenpc.entity.DienLucProxy;
import esolutions.com.barcodehungyenpc.entity.DonViResponse;
import esolutions.com.barcodehungyenpc.entity.ThongBaoResponse;
import esolutions.com.barcodehungyenpc.model.DsCongToAdapter;
import esolutions.com.barcodehungyenpc.utils.Common;
import esolutions.com.barcodehungyenpc.utils.SharePrefManager;
import esolutions.com.barcodehungyenpc.utils.SoapXML;

import static esolutions.com.barcodehungyenpc.utils.Common.TIME_DELAY_ANIM;
import static esolutions.com.barcodehungyenpc.utils.Common.convertDateSQLToDateUI;
import static esolutions.com.barcodehungyenpc.utils.Common.getDateTimeNow;
import static esolutions.com.barcodehungyenpc.view.DangNhapActivity.KEY_PREF_KEYBOARD;
import static esolutions.com.barcodehungyenpc.view.DangNhapActivity.PARAM_DVI;
import static esolutions.com.barcodehungyenpc.view.DangNhapActivity.PARAM_SERVER_URL;
import static esolutions.com.barcodehungyenpc.view.DangNhapActivity.PARAM_USER;
import static esolutions.com.barcodehungyenpc.view.DangNhapActivity.PREF_CONFIG;

public class MainKiemDinhActivity
        extends BaseActivity
        implements
        DatePickerDialog.OnDateSetListener,
        DsCongToAdapter.OnDsCtoAdapterIteraction {

    private LinearLayout mLLSearchOnline;
    private EditText mEtSearchOnline, mEtSearchLocal;
    private TextView mTvThongKeCto, mTvDate;
    private ImageButton mBtnDate, mBtnClearSearchOnline, mBtnClearSearchLocal, mBtnSearchOnline;
    private ProgressBar mPbarSearchOnline;
    private CoordinatorLayout mCoordinatorLayout;
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
    private List<CongToProxy> mListCtoKD = new ArrayList<>();
    private List<CongToProxy> mListUploadCtoKD = new ArrayList<>();
    private DsCongToAdapter mCtoKDAdapter;
    private SQLiteDatabase mDatabase;
    private SqlDAO mSqlDAO;
    private Common.KIEU_CHUONG_TRINH mKieuChuongTrinh;
    private Common.MENU_BOTTOM_KD menuBottomKD;
    private String mDate;
    Bundle savedInstanceState;

    SoapXML.AsyncSoap<List<CToPBResponse>, ThongBaoResponse> soapSearchCto = null;
    SoapXML.AsyncSoap<List<DonViResponse>, ThongBaoResponse> soapUpload = null;
    private static boolean isLoadedFolder = false;

    //bundle
    private String mStringDvi, mURL, mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kiem_dinh);
        this.savedInstanceState = savedInstanceState;
//        super.hideBar();
        SpannableString s = new SpannableString("Tìm kiếm công tơ");
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, "Tìm kiếm công tơ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        getSupportActionBar().setElevation(0);
        if (Common.checkPermission(this)) {
            return;
        }

        try {
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
        Intent intent = new Intent(MainKiemDinhActivity.this, BarcodeActivity.class);
        startActivity(intent);
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

    }

    @Override
    public void setAction(final Bundle savedInstanceState) {
        try {
            //set share pref
            mPrefManager = SharePrefManager.getInstance(this);
            DangNhapActivity.checkSharePreference(mPrefManager);

            //set Data
            //0 là Phân bổ 1 là đăng nhập
            mKieuChuongTrinh = Common.KIEU_CHUONG_TRINH.KIEM_DINH;
            menuBottomKD = Common.MENU_BOTTOM_KD.ALL;
            //init Data

            mDate = getDateTimeNow(Common.DATE_TIME_TYPE.ddMMyyyy);
            mListCtoKD = mSqlDAO.getByDateAllCongToKD(Common.convertDateUIToDateSQL(mDate));

            //dumpData
//            if (mListCtoPB.size() == 0) {
//                mSqlDAO.insertDumpListCongTo(dumpData(Common.KIEU_CHUONG_TRINH.PHAN_BO), Common.KIEU_CHUONG_TRINH.PHAN_BO);
//                mSqlDAO.insertDumpListCongTo(dumpData(Common.KIEU_CHUONG_TRINH.KIEM_DINH), Common.KIEU_CHUONG_TRINH.KIEM_DINH);
//                mListCtoPB = mSqlDAO.getAllCongTo(Common.KIEU_CHUONG_TRINH.PHAN_BO);
//                mListCtoKD = mSqlDAO.getAllCongTo(Common.KIEU_CHUONG_TRINH.KIEM_DINH);
//            }

            // setup kiểu list công tơ và refersh lại text thống kê
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

    private ArrayAdapter initAdapterDvi() throws Exception {
        List<DienLucProxy> data = new ArrayList<>();
        data = mSqlDAO.getAllTBL_DIENLUC();

        List<String> dataApdater = new ArrayList<>();
        for (DienLucProxy proxy : data
                ) {
            dataApdater.add(proxy.getMA_DVIQLY());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dataApdater);
        return arrayAdapter;
    }

    @Override
    protected void handleListener() {
        try {
            //lay data
            getBundle();

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
                        if (menuBottomKD == Common.MENU_BOTTOM_KD.ALL) {
                            mListCtoKD.clear();

                            mListCtoKD = mSqlDAO.getByDateAllCongToKD(Common.convertDateUIToDateSQL(mDate));
                        }

                        if (menuBottomKD == Common.MENU_BOTTOM_KD.DS_GHIM) {
                            mListCtoKD.clear();
                            mListCtoKD = mSqlDAO.getByDateAllCongToGhimKD(Common.convertDateUIToDateSQL(mDate));
                        }

                        fillDataReyclerFull();

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
                    //clear textSearch
                    mEtSearchOnline.setText("");

                    try {
                        switch (tabId) {
                            case R.id.nav_bottom_ds_thietbi:
                                //show ll search
                                if (mLLSearchOnline.getVisibility() == View.GONE)
                                    mLLSearchOnline.setVisibility(View.VISIBLE);

//                                //init Data
                                menuBottomKD = Common.MENU_BOTTOM_KD.ALL;
                                mListCtoKD.clear();
                                mListCtoKD = mSqlDAO.getByDateAllCongToKD(Common.convertDateUIToDateSQL(mTvDate.getText().toString()));

                                //hide fab
                                if (mFab.getVisibility() == View.VISIBLE) {
                                    mFab.hide(true);
                                    mFab.setVisibility(View.GONE);
                                }
                                break;

                            case R.id.nav_bottom_ds_chon:
//                                //init Data
                                menuBottomKD = Common.MENU_BOTTOM_KD.DS_GHIM;
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
                                break;

                            case R.id.nav_bottom_lichsu:
                                menuBottomKD = Common.MENU_BOTTOM_KD.LICH_SU;

                                break;
                        }

                        fillDataReyclerFull();

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
                    }catch (Exception e)
                    {
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
                        upload();
                    } catch (Exception e) {
                        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void upload() throws Exception {
        prepareDataUpload();

        if (mListUploadCtoKD.size() == 0)
            throw new Exception(Common.MESSAGE.ex22.getContent());

        mBtnUpload.setVisibility(View.GONE);
        mPbarUpload.setVisibility(View.VISIBLE);


        //check thread hiện tại
        if (soapSearchCto != null) {
            if (soapSearchCto.getStatus() == AsyncTask.Status.RUNNING || soapSearchCto.getStatus() == AsyncTask.Status.PENDING) {
                throw new Exception(Common.MESSAGE.ex23.getContent());
            }
        }



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

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();

        mStringDvi = bundle.getString(PARAM_DVI, "");
        mUser = bundle.getString(PARAM_USER, "");
        mURL = bundle.getString(PARAM_SERVER_URL, "");
    }

    private void searchOnline(String searchText) throws Exception {
        //check thread hiện tại
        if (soapSearchCto != null) {
            if (soapSearchCto.getStatus() == AsyncTask.Status.RUNNING || soapSearchCto.getStatus() == AsyncTask.Status.PENDING) {
                mEtSearchOnline.setText("");
                throw new Exception(Common.MESSAGE.ex21.getContent());
            }
        }

        boolean isHasExitCto = false;
        //check data local với text search và date
        String date = Common.convertDateUIToDateSQL(mDate);
        isHasExitCto = mSqlDAO.checkExistByDateTBL_CTO_GUI_KD(searchText, date);
        //nếu có thì load và không tải
        if (isHasExitCto) {
            mEtSearchOnline.setText("");
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, Common.MESSAGE.ex11.getContent(), Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
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

        String[] requestParams = new String[]{mStringDvi, searchText};

        if (!Common.isNetworkConnected(this)) {
            throw new Exception(Common.MESSAGE.ex07.getContent());
        }

        SoapXML.AsyncSoap.AsyncSoapCallBack<List<CToPBResponse>, ThongBaoResponse> callBackCToKD = new SoapXML.AsyncSoap.AsyncSoapCallBack<List<CToPBResponse>, ThongBaoResponse>() {
            @Override
            public void onPre(SoapXML.AsyncSoap soap) {
                //show progress bar
                showProgresbar(true);
            }

            @Override
            public void onUpdate(String message) {
                //ẩn progress bar
                showProgresbar(false);

                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
                snackbar.show();
            }


//            @Override
//            public void onPost(CToPBResponse response) {
//                //ẩn progress bar
//                showProgresbar(false);
//
//                //Xử lý kết quả
//                try {
//                    doProcessAfterSearchOnline(response);
//                } catch (Exception e) {
//                    Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
//                    snackbar.show();
//                    e.printStackTrace();
//                }
//            }

//            @Override
//            public HashMap<String, SoapObject> filterDataReal(SoapObject response) {
//                //lọc từ response trả về những giá trị thực sự của API
//                //tùy chỉnh theo thuộc tính xml node trả về
//                if (response == null)
//                    return null;
//
//                SoapObject soapLv1 = (SoapObject) response.getProperty("diffgram");
//                SoapObject proInfoLv1 = (SoapObject) soapLv1.getProperty("NewDataSet");
//
//                //kiểm tra nếu có property 'CTO' thì lấy dữ liệu dataset
//                //ngược lại nếu là 'Table1" thì lấy dữ liệu thông báo
//                //2 giá trị này được cung cấp bởi server, nên debug các giá trị cây của soapObject để nắm rõ
//
//                HashMap<String, SoapObject> result = null;
//                SoapObject proInfoLv2 = null;
//
//                if (proInfoLv1.hasProperty("CONG_TO")) {
//                    proInfoLv2 = (SoapObject) proInfoLv1.getProperty("CONG_TO");
//                    String soap = proInfoLv2.toString();
//                    Log.d(TAG, "filterDataReal: " + soap);
//
//                    result = new HashMap<>();
//                    result.put("CONG_TO", proInfoLv2);
//                }
//
//                if (proInfoLv1.hasProperty("Table1")) {
//                    proInfoLv2 = (SoapObject) proInfoLv1.getProperty("Table1");
//                    String soap = proInfoLv2.toString();
//                    Log.d(TAG, "filterDataReal: " + soap);
//
//                    result = new HashMap<>();
//                    result.put("Table1", proInfoLv2);
//                }
//                return result;
//            }

            @Override
            public void onPostData(List<CToPBResponse> dataResponse) {
                Log.d(TAG, "onPostData: ");
                //ẩn progress bar
                showProgresbar(false);

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
            public void onPostMessageSever(ThongBaoResponse errorResponse) {
                Log.d(TAG, "onPostMessageSever: ");
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, errorResponse.getThongbao(), Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        };

        soapSearchCto = new SoapXML.AsyncSoap(
                CToPBResponse.class,
                ThongBaoResponse.class,
                "thongbao",
                callBackCToKD,
                SoapXML.METHOD.CTO_KD.getNameMethod(),
                SoapXML.getURL(mURL),
                SoapXML.METHOD.CTO_KD.getNameParams()
        );

        soapSearchCto.execute(requestParams);

    }

    private void doProcessAfterSearchOnline(List<CToPBResponse> listResponse) throws Exception {
        if (listResponse == null)
            throw new Exception(Common.MESSAGE.ex06.getContent());

        for (CToPBResponse cToPBResponse : listResponse) {
            //lấy các dữ liệu cần thiết ghi vào database
            CongTo congTo = new CongTo();
//        congTo.setMA_DVIQLY(response.getMA_DVIQLY());
            congTo.setMA_DVIQLY(mStringDvi);
            //tạm thời chỉ số tháo đang lược bỏ đi từ server
            congTo.setCHISO_THAO("");
            congTo.setCLOAI(String.valueOf(cToPBResponse.getMA_CLOAI()));
            congTo.setMA_CTO(cToPBResponse.getMA_CTO());
            congTo.setNAMSX(cToPBResponse.getNAM_SX());
            congTo.setSO_CTO(cToPBResponse.getSO_CTO());
            congTo.setNGAY_NHAP(Common.getDateTimeNow(Common.DATE_TIME_TYPE.yyyyMMdd));
            congTo.setTRANG_THAI_GHIM(Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode());
            congTo.setTRANG_THAI_CHON(Common.TRANG_THAI_CHON.CHUA_CHON.getCode());
            mSqlDAO.insertTBL_CTO_GUI_KD(congTo);

        }

        //làm mới lại list với việc search local
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
        if (pos >= mListCtoKD.size())
            return;

        try {
            //set filterDataReal GhimCto đồng thời đánh dấu cần refersh lại giá trị công tơ đó
            int statusGhimCto = mListCtoKD.get(pos).getTRANG_THAI_GHIM();
            if (statusGhimCto == Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode())
                statusGhimCto = Common.TRANG_THAI_GHIM.DA_GHIM.getCode();
            else {
                statusGhimCto = Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode();
                mSqlDAO.updateChonCtoKD(mListCtoKD.get(pos).getSTT(), Common.TRANG_THAI_CHON.CHUA_CHON.getCode());
            }
            mSqlDAO.updateGhimCtoKD(mListCtoKD.get(pos).getSTT(), statusGhimCto);


            mListCtoKD.clear();
            String dateSQL = Common.convertDateUIToDateSQL(mDate);

            if (menuBottomKD == Common.MENU_BOTTOM_KD.ALL) {
                mListCtoKD.clear();
                mListCtoKD = mSqlDAO.getByDateAllCongToKD(dateSQL);
            }


            if (menuBottomKD == Common.MENU_BOTTOM_KD.DS_GHIM) {
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

    private void prepareDataUpload() throws Exception{
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
        int size = mListCtoKD.size();
        if (pos >= size) {
            return;
        }

        final CongToProxy congToProxy = mListCtoKD.get(pos);

        OnClickButtonAlertDialog onClickButtonAlertDialog = new OnClickButtonAlertDialog() {
            @Override
            public void doClickYes() {
                try {
                    mSqlDAO.deleteCongToKD(congToProxy.getSTT());

                    if (menuBottomKD == Common.MENU_BOTTOM_KD.ALL) {
                        mListCtoKD.clear();
                        mListCtoKD = mSqlDAO.getByDateAllCongToKD(Common.convertDateUIToDateSQL(mDate));
                    }


                    if (menuBottomKD == Common.MENU_BOTTOM_KD.DS_GHIM) {
                        mListCtoKD.clear();
                        mListCtoKD = mSqlDAO.getByDateAllCongToGhimKD(Common.convertDateUIToDateSQL(mDate));
                    }

                    fillDataReyclerFull();

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
            int statusChonCto = mListCtoKD.get(pos).getTRANG_THAI_CHON();
            if (statusChonCto == Common.TRANG_THAI_CHON.CHUA_CHON.getCode())
                statusChonCto = Common.TRANG_THAI_CHON.DA_CHON.getCode();
            else
                statusChonCto = Common.TRANG_THAI_CHON.CHUA_CHON.getCode();
            mSqlDAO.updateChonCtoKD(mListCtoKD.get(pos).getSTT(), statusChonCto);


            mListCtoKD.clear();
            String dateSQL = Common.convertDateUIToDateSQL(mDate);

            if (menuBottomKD == Common.MENU_BOTTOM_KD.ALL) {
                mListCtoKD.clear();
                mListCtoKD = mSqlDAO.getByDateAllCongToKD(dateSQL);
            }

            if (menuBottomKD == Common.MENU_BOTTOM_KD.DS_GHIM) {
                mListCtoKD.clear();
                mListCtoKD = mSqlDAO.getByDateAllCongToGhimKD(dateSQL);

                prepareDataUpload();

            }

            fillDataReyclerFull();
//            ((DsCongToAdapter)mRvCto.getAdapter()).notifyDataSetChanged();
            mRvCto.invalidate();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //endregion

    public void searchLocal(CharSequence charSearch) {
        try {
            String dateSql = Common.convertDateUIToDateSQL(mDate);
            if (menuBottomKD == Common.MENU_BOTTOM_KD.ALL) {
                mListCtoKD.clear();
                mListCtoKD = mSqlDAO.getByDateAllCongToKD(dateSql);
            }


            if (menuBottomKD == Common.MENU_BOTTOM_KD.DS_GHIM) {
                mListCtoKD.clear();
                mListCtoKD = mSqlDAO.getByDateAllCongToGhimKD(dateSql);
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
            List<CongToProxy> data = new ArrayList<>();
            String query = Common.removeAccent(charSearch.toString().trim().toLowerCase());
            for (CongToProxy congToProxy : mListCtoKD) {
                if (Common.removeAccent(congToProxy.getmMaCto().toLowerCase()).contains(query)
                        || Common.removeAccent(congToProxy.getmSoCto().toLowerCase()).contains(query)
                        || convertDateSQLToDateUI(congToProxy.getNGAY_NHAP()).contains(query)) {
                    data.add(congToProxy);
                }
            }
            //giữ nguyên dữ liệu, lọc cái cần dùng
            fillDataReyclerLocal(mKieuChuongTrinh, data);
        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
            e.printStackTrace();
            Log.e(MainKiemDinhActivity.class.getName(), "interactionDataCongToSearch: " + e.getMessage());
        }
    }


    private void fillDataReyclerFull() throws Exception {
        int mCountCto = mListCtoKD.size();
        if (mCtoKDAdapter == null) {
            mCtoKDAdapter = new DsCongToAdapter(this, mListCtoKD);
            mCtoKDAdapter.setMenuBottomKD(menuBottomKD);
            mRvCto.setAdapter(mCtoKDAdapter);
        } else {
            mCtoKDAdapter.setMenuBottomKD(menuBottomKD);
            mCtoKDAdapter.refresh(mListCtoKD);

        }
        mRvCto.invalidate();

        //set text thống kê cto theo số item adapter
        setTextCountCtoAndDate(mCountCto);
//        int mCountCto = 0;
//        mCtoKDAdapter = new DsCongToAdapter(this, mListCtoKD);
//        ((DsCongToAdapter) mCtoKDAdapter).setMenuBottomKD(menuBottomKD);
//        mRvCto.setAdapter(mCtoKDAdapter);
//        mCountCto = mListCtoKD.size();
//
//        mRvCto.invalidate();
//
//        //set text thống kê cto theo số item adapter
//        setTextCountCtoAndDate(mCountCto);
    }

    private void fillDataReyclerLocal(Common.KIEU_CHUONG_TRINH kieuCongTo, List<CongToProxy> data) throws Exception {
        int mCountCto = 0;

        mCtoKDAdapter = new DsCongToAdapter(this, data);
        ((DsCongToAdapter) mCtoKDAdapter).setMenuBottomKD(menuBottomKD);
        mRvCto.setAdapter(mCtoKDAdapter);
        mCountCto = data.size();

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
