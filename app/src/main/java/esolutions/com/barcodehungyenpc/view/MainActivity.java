package esolutions.com.barcodehungyenpc.view;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import esolutions.com.barcodehungyenpc.entity.CToPBResponse;
import esolutions.com.barcodehungyenpc.entity.DienLuc;
import esolutions.com.barcodehungyenpc.entity.DienLucProxy;
import esolutions.com.barcodehungyenpc.model.DsCongToAdapter;
import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.database.SqlConnect;
import esolutions.com.barcodehungyenpc.database.SqlDAO;
import esolutions.com.barcodehungyenpc.entity.CongTo;
import esolutions.com.barcodehungyenpc.entity.CongToProxy;
import esolutions.com.barcodehungyenpc.trash.DangNhapCuActivity;
import esolutions.com.barcodehungyenpc.utils.Common;
import esolutions.com.barcodehungyenpc.utils.SharePrefManager;
import esolutions.com.barcodehungyenpc.utils.SoapXML;

import static esolutions.com.barcodehungyenpc.utils.Common.convertDateSQLToDateUI;
import static esolutions.com.barcodehungyenpc.utils.Common.getDateTimeNow;

public class MainActivity
        extends BaseActivity
        implements
        DsCtoFragment.OnDsCtoFragmentInteractionListener,
        DsGhimCtoFragment.OnDsGhimCtoFragmentInteractionListener,
        DatePickerDialog.OnDateSetListener,
        DsCongToAdapter.OnDsCtoAdapterIteraction {

    //    private List<FloatingActionMenu> menus = new ArrayList<>();
//    private FloatingActionMenu mFabMenu;
//    private FloatingActionButton mFabBluetooth;
//    private FloatingActionButton mFabCamera;
    private EditText mEtSearchOnline, mEtSearchLocal;
    private TextView mTvThongKeCto, mTvDate;
    private ImageButton mBtnDate, mBtnClearSearchOnline, mBtnClearSearchLocal, mBtnSearchOnline;
    private ProgressBar mPbarSearchOnline;
    private CoordinatorLayout mCoordinatorLayout;

    private SharePrefManager mPrefManager;

    public static final String PREF_CONFIG = "PREF_CONFIG";
    public static final String KEY_PREF_MA_DIEN_LUC = "KEY_PREF_MA_DIEN_LUC";
    public static final String KEY_PREF_SERVER_URL = "KEY_PREF_SERVER_URL";

    //    private FloatingActionButton mFabCamera;
//    private FloatingActionButton mFabBluetooth;
    private Handler mUiHandler = new Handler();

    private static final String TAG = MainActivity.class.getName();

    private RelativeLayout mRlMain;
    private RecyclerView mRvCto;
    public BottomBar mNavigation;
    public static String sMaDLuc, sTaiKhoan;
    private String mMatKhau;
    private List<CongToProxy> mListCtoPB = new ArrayList<>();
    private List<CongToProxy> mListCtoKD = new ArrayList<>();

    private DsCongToAdapter mCtoPBAdapter;
    private DsCongToAdapter mCtoKDAdapter;
    //    private List<CongToProxy> mListCtoGhim = new ArrayList<>();
    private SQLiteDatabase mDatabase;
    private SqlDAO mSqlDAO;
    private Common.KIEU_CONG_TO mKieuCongTo;

    //barcode
    private IOnPauseScannerBarcodeListner pauseScannerBarcodeListner;
    public static final int REQUEST_BARCODE = 999;
    public static final int RESPONSE_BARCODE = 1000;
    private String mDate;
    private String mTextSearchOnlineOldSession;

//    private int mCountCto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        super.hideBar();
        SpannableString s = new SpannableString("Tìm kiếm công tơ");
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, "Tìm kiếm công tơ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        getSupportActionBar().setElevation(0);
        try {
            initView();
            handleListener();
            setAction(savedInstanceState);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_BARCODE && resultCode == RESPONSE_BARCODE & data != null) {

            if (pauseScannerBarcodeListner != null) {
                pauseScannerBarcodeListner.pause();
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
                Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show();
                processCamera();
                break;

            case R.id.menu_barcode_bluetooth:
                processBluetooth();
                Toast.makeText(this, "Blutooth", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void processCamera() {
        BarcodeScannerDialog dialog = new BarcodeScannerDialog(MainActivity.this, new BarcodeScannerDialog.OnResultListener() {
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
        dialogConfig.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialogConfig.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialogConfig.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialogConfig.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        final EditText etURL = (EditText) dialogConfig.findViewById(R.id.et_url);
        final EditText etDvi = (EditText) dialogConfig.findViewById(R.id.aet_dvi);
        final Button btSave = (Button) dialogConfig.findViewById(R.id.btn_save_config);

        final ImageButton mBtnListDvi = (ImageButton) dialogConfig.findViewById(R.id.ibtn_auto_dvi);
        final AutoCompleteTextView mAutoEtDvi = (AutoCompleteTextView) dialogConfig.findViewById(R.id.aet_dvi);

        //fill Data
        if (mPrefManager == null)
            mPrefManager = SharePrefManager.getInstance(this);

        //check url
        String URL = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).getString(KEY_PREF_SERVER_URL, "");
        etURL.setText(URL);

        //check dvi
        String dvi = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).getString(KEY_PREF_MA_DIEN_LUC, "");
        mAutoEtDvi.setText(dvi);

        //set Adapter autoComplex dvi
        mAutoEtDvi.setThreshold(2);
        mAutoEtDvi.setAdapter(initAdapterDvi());
        mAutoEtDvi.invalidate();

        //catch click
        mBtnListDvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAutoEtDvi.showDropDown();
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String dvi = etDvi.getText().toString();
                    String url = etURL.getText().toString();

                    //check
                    if (TextUtils.isEmpty(url)) {
                        etURL.setError(Common.MESSAGE.ex03.getContent());
                        etURL.setFocusable(true);
                        return;
                    }

                    if (TextUtils.isEmpty(dvi)) {
                        etDvi.setError(Common.MESSAGE.ex03.getContent());
                        etDvi.setFocusable(true);
                        return;
                    }

                    mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE)
                            .edit()
                            .putString(KEY_PREF_MA_DIEN_LUC, dvi)
                            .putString(KEY_PREF_SERVER_URL, url)
                            .commit();

                    //check nếu dvi mơi thì thêm vào database và làm mới lại auto et dvi
                    //lưu shared pref
                    boolean isHasDviExits = false;

                    isHasDviExits = mSqlDAO.checkExistTBL_DIENLUC(dvi);

                    if (!isHasDviExits) {
                        DienLuc dienLuc = new DienLuc();
                        dienLuc.setMA_DVIQLY(dvi);

                        mSqlDAO.insertTBL_DIENLUC(dienLuc);

                        //set Adapter autoComplex dvi
                        mAutoEtDvi.setThreshold(1);
                        mAutoEtDvi.setAdapter(initAdapterDvi());
                        mAutoEtDvi.invalidate();
//                        mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE)
//                                .edit()
//                                .putString(KEY_PREF_MA_DIEN_LUC, dvi)
//                                .commit();
                    }

                } catch (Exception e) {
                    Snackbar snackbar = Snackbar
                            .make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);

                    snackbar.show();
                } finally {
                    dialogConfig.dismiss();
                }
            }
        });

//        final Spinner spinner = (Spinner) dialogConfig.findViewById(R.id.spin_dvi);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"A", "B", "C"});
//        spinner.fillDataReyclerFull(adapter);
//        spinner.invalidate();

        dialogConfig.show();
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        sMaDLuc = bundle.getString(DangNhapCuActivity.PARAM_CODE_DIENLUC, "");
        sTaiKhoan = bundle.getString(DangNhapCuActivity.PARAM_USER, "");
        mMatKhau = bundle.getString(DangNhapCuActivity.PARAM_PASS, "");
    }

    @Override
    protected void initView() {
//        mRlMain = (RelativeLayout) findViewById(R.id.rl_main);
        mRvCto = (RecyclerView) findViewById(R.id.rv_ds_cto);
        mNavigation = (BottomBar) findViewById(R.id.nav_bottom_menu);
//        mFabMenu = (FloatingActionMenu) findViewById(R.id.fab_menu);
//        mFabBluetooth = (FloatingActionButton) findViewById(R.id.fab_bluetooth);
//        mFabCamera = (FloatingActionButton) findViewById(R.id.fab_camera);

        mEtSearchOnline = (EditText) findViewById(R.id.et_search_type);
        mEtSearchLocal = (EditText) findViewById(R.id.et_search_type2);

        mTvThongKeCto = (TextView) findViewById(R.id.et_thongKe);
        mTvDate = (TextView) findViewById(R.id.et_ngay);
        mBtnDate = (ImageButton) findViewById(R.id.ibtn_search_date);
        mBtnClearSearchOnline = (ImageButton) findViewById(R.id.ibtn_clear_search_type);
        mBtnClearSearchLocal = (ImageButton) findViewById(R.id.ibtn_clear_search_type2);
        mBtnSearchOnline = (ImageButton) findViewById(R.id.ibtn_search_online);
        mPbarSearchOnline = (ProgressBar) findViewById(R.id.pbar_search_online);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.cl_main);

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

            if (!mPrefManager.checkExistSharePref(PREF_CONFIG)) {
                mPrefManager.addSharePref(PREF_CONFIG, MODE_PRIVATE);
                mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE)
                        .edit()
                        .putString(KEY_PREF_MA_DIEN_LUC, "")
                        .putString(KEY_PREF_SERVER_URL, "")
                        .commit();
            }

            //set fab
//            menus.add(mFabMenu);

            //set Data
            mKieuCongTo = Common.KIEU_CONG_TO.PHAN_BO;

            //init Data
            mListCtoPB = mSqlDAO.getAllCongTo(Common.KIEU_CONG_TO.PHAN_BO);
            mListCtoKD = mSqlDAO.getAllCongTo(Common.KIEU_CONG_TO.KIEM_DINH);
            mDate = getDateTimeNow(Common.DATE_TIME_TYPE.ddMMyyyy);

            //dumpData
//            if (mListCtoPB.size() == 0) {
//                mSqlDAO.insertDumpListCongTo(dumpData(Common.KIEU_CONG_TO.PHAN_BO), Common.KIEU_CONG_TO.PHAN_BO);
//                mSqlDAO.insertDumpListCongTo(dumpData(Common.KIEU_CONG_TO.KIEM_DINH), Common.KIEU_CONG_TO.KIEM_DINH);
//                mListCtoPB = mSqlDAO.getAllCongTo(Common.KIEU_CONG_TO.PHAN_BO);
//                mListCtoKD = mSqlDAO.getAllCongTo(Common.KIEU_CONG_TO.KIEM_DINH);
//            }

            // setup kiểu list công tơ và refersh lại text thống kê
            mKieuCongTo = Common.KIEU_CONG_TO.PHAN_BO;
            searchLocal(mDate);

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

//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.row_spin_ds_dien_luc, R.id.tv_spin_dvi, data);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dataApdater);
        return arrayAdapter;
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("key pressed", String.valueOf(event.getKeyCode()));
//        Object pressedKeyCodeObject = new Integer(event.getKeyCode());
//        String pressedKeyText = KeyEvent.getKeyText((Integer)pressedKeyCodeObject);
//        Log.i("key pressed", pressedKeyText);


//        int  keyaction = event.getAction();
//
//        if(keyaction == KeyEvent.ACTION_DOWN)
//        {
//            int keycode = event.getKeyCode();
//            int keyunicode = event.getUnicodeChar(event.getMetaState() );
//            char character = (char) keyunicode;
//
//            System.out.println("DEBUG MESSAGE KEY=" + character + " KEYCODE=" +  keycode);
//        }


        return super.dispatchKeyEvent(event);
    }


    @Override
    protected void handleListener() {
        try {
//        getBundle();
            mDatabase = SqlConnect.getInstance(this).open();
            mSqlDAO = new SqlDAO(mDatabase, this);

            //hiển thị folder trên sdcard
            Common.showFolder(this);

            mTvDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
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

//            mEtSearchOnline.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    try {
//                        //nếu text search online là rỗng thì hiển thị tất cả
//                        if (charSequence.toString().equals("")) {
//                            searchLocalOnDate(charSequence);
//                        }
//
//                        mTextSearchOnlineOldSession = charSequence.toString();
//
//
//                        //catch sự kiện kết thúc scan từ máy barcode chưa được
//                        // nên fake theo điều kiện lớn hơn 2 kí tự cùng lúc thì xem như là một lần quét mới
//                        if (charSequence.toString().length() - mTextSearchOnlineOldSession.length() > 2) {
//                            String newTextSession = charSequence.toString().substring(0, mTextSearchOnlineOldSession.length());
//                            mTextSearchOnlineOldSession = newTextSession;
//                            //xóa text cũ gán text mới
//                            mEtSearchOnline.setText(newTextSession);
//                            return;
//                        }
//
//                        //sau khi xóa text cũ gán text mới thì đồng thời cho tìm kiếm online
//                        if(charSequence.toString().equals(mTextSearchOnlineOldSession))
//                        {
//                            searchOnline(mTextSearchOnlineOldSession);
//                        }
//
//                    } catch (Exception e) {
//                        Snackbar snackbar = Snackbar
//                                .make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
//
//                        snackbar.show();
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//
//                }
//            });

//            mEtFocusAfterScan.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
////                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
////                        //khi có action next thì chuyển qua b
////                        return true;
////                    }
//
//                    return false;
//                }
//            });


//            mEtSearchOnline.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
//                        Log.i(TAG,"Enter pressed");
//                    }
//                    return false;
//                }
//            });

//            mEtSearchOnline.setOnTouchListener(new View.OnTouchListener()
//            {
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//                    evalue="1";
//                    return false;
//                }
//            });

//            mEtFocusAfterScan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    Log.i(TAG, "onFocusChange: " + hasFocus);
//                    Toast.makeText(MainActivity.this, "hasFocus " + hasFocus, Toast.LENGTH_SHORT).show();
//                    if (hasFocus) {
//                        mEtSearchOnline.setText("");
//                        mEtSearchOnline.requestFocus();
////                        Log.i(TAG, "onFocusChange: " + hasFocus);
//                        // code to execute when EditText loses focus
//                    }
//                }
//            });

            mBtnClearSearchOnline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEtSearchOnline.setText("");
                }
            });

            mBtnClearSearchLocal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEtSearchLocal.setText("");
                }
            });

            mBtnSearchOnline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(mEtSearchOnline.getText().toString()))
                        return;

                    try {
                        searchOnline(mEtSearchOnline.getText().toString());
                    } catch (Exception e) {
                        Snackbar snackbar = Snackbar
                                .make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);

                        snackbar.show();
                        e.printStackTrace();
                    } finally {
                        //ẩn progress bar
                        showProgresbar(false);
                    }

                }
            });

            mBtnDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Common.runAnimationClickViewScale(view, R.anim.scale_view_pull, Common.TIME_DELAY_ANIM);
                    showDialogChooseDate();
//                    Toast.makeText(MainActivity.this, "Click Date", Toast.LENGTH_SHORT).show();
                }
            });

            //set menu bottom
            mNavigation.setOnTabSelectListener(new OnTabSelectListener() {
                @Override
                public void onTabSelected(@IdRes int tabId) {
                    //clear textSearch
                    mEtSearchOnline.setText("");

                    Fragment fragment = null;
                    try {
                        switch (tabId) {
                            case R.id.nav_bottom_cto_phanbo:
                                //init Data
                                mKieuCongTo = Common.KIEU_CONG_TO.PHAN_BO;
                                mListCtoPB = mSqlDAO.getAllCongTo(mKieuCongTo);

                                break;

                            case R.id.nav_bottom_cto_kiemdinh:
                                //init Data
                                mKieuCongTo = Common.KIEU_CONG_TO.KIEM_DINH;
                                mListCtoKD = mSqlDAO.getAllCongTo(Common.KIEU_CONG_TO.KIEM_DINH);
                                break;
                        }

                        fillDataReyclerFull(mKieuCongTo);

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, Common.MESSAGE.ex02.getContent(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        return;
                    }
                }
            });

            //set float action button
//            menus.add(mFabMenu);
//            mFabMenu.setClosedOnTouchOutside(true);
//            mFabMenu.hideMenuButton(false);
//
//            mFabBluetooth.setOnClickListener(clickListener);
//            mFabCamera.setOnClickListener(clickListener);
//            int delay = 400;
//            for (final FloatingActionMenu menu : menus) {
//                mUiHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        menu.showMenuButton(true);
//                    }
//                }, delay);
//                delay += 150;
//            }
//            mFabMenu.setOnMenuButtonClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mFabMenu.isOpened()) {
//                        Toast.makeText(MainActivity.this, mFabMenu.getMenuButtonLabelText(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    mFabMenu.toggle(true);
//                }
//            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void searchOnline(String searchText) throws Exception {
        boolean isHasExitCto = false;
        //check data local với text search
        if (mKieuCongTo == Common.KIEU_CONG_TO.PHAN_BO) {
            isHasExitCto = mSqlDAO.checkExistTBL_CTO_PB(searchText);
        } else {
            isHasExitCto = mSqlDAO.checkExistTBL_CTO_GUI_KD(searchText);
        }
        //nếu có thì load và không tải
        if (isHasExitCto) {
            //Chuyển text sang mEtSearchLocal
            mEtSearchLocal.setText(searchText);
            mEtSearchOnline.setText("");
//            mEtSearchLocal.requestFocus();
//            mEtSearchLocal.setFocusable(true);
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, Common.MESSAGE.ex11.getContent(), Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        } else {
            mEtSearchLocal.setText("");
        }

        //check url
        String URL = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).getString(KEY_PREF_SERVER_URL, "");
        if (TextUtils.isEmpty(URL)) {
            showDialogConfig();
            throw new Exception(Common.MESSAGE.ex08.getContent());
        }

        //check dvi
        String dvi = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).getString(KEY_PREF_MA_DIEN_LUC, "");
        if (TextUtils.isEmpty(dvi)) {
            showDialogConfig();
            throw new Exception(Common.MESSAGE.ex09.getContent());
        }

        String[] requestParams = new String[]{dvi, searchText};

        if (!Common.isNetworkConnected(this)) {
            throw new Exception(Common.MESSAGE.ex07.getContent());
        }

        SoapXML.AsyncSoap.AsyncSoapCallBack<CToPBResponse> callBackCToPB = new SoapXML.AsyncSoap.AsyncSoapCallBack<CToPBResponse>() {
            @Override
            public void onPre(SoapXML.AsyncSoap soap) {
                //show progress bar
                showProgresbar(true);
            }

            @Override
            public void onUpdate(String message) {
                //hide progress bar
                showProgresbar(false);
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
                snackbar.show();
            }

            @Override
            public void onPost(CToPBResponse response) {
                //ẩn progress bar
                showProgresbar(false);

                //Xử lý kết quả
                try {
                    doProcessAfterSearchOnline(response);
                } catch (Exception e) {
                    Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    e.printStackTrace();
                }

            }

            @Override
            public HashMap<String, SoapObject> filterDataReal(SoapObject response) {
                //lọc từ response trả về những giá trị thực sự của API
                //tùy chỉnh theo thuộc tính xml node trả về
                if (response == null)
                    return null;

                SoapObject soapLv1 = (SoapObject) response.getProperty("diffgram");
                SoapObject proInfoLv1 = (SoapObject) soapLv1.getProperty("NewDataSet");

                //kiểm tra nếu có property 'CTO' thì lấy dữ liệu dataset
                //ngược lại nếu là 'Table1" thì lấy dữ liệu thông báo
                //2 giá trị này được cung cấp bởi server, nên debug các giá trị cây của soapObject để nắm rõ

                HashMap<String, SoapObject> result = null;
                SoapObject proInfoLv2 = null;

                if (proInfoLv1.hasProperty("CTO")) {
                    proInfoLv2 = (SoapObject) proInfoLv1.getProperty("CTO");
                    String soap = proInfoLv2.toString();
                    Log.d(TAG, "filterDataReal: " + soap);

                    result = new HashMap<>();
                    result.put("CTO", proInfoLv2);
                }

                if (proInfoLv1.hasProperty("Table1")) {
                    proInfoLv2 = (SoapObject) proInfoLv1.getProperty("Table1");
                    String soap = proInfoLv2.toString();
                    Log.d(TAG, "filterDataReal: " + soap);

                    result = new HashMap<>();
                    result.put("Table1", proInfoLv2);
                }
                return result;
            }
        };

        //hiện tại server đang trả về cùng 1 dạng response là CToPBResponse nên sẽ dùng chung
        SoapXML.AsyncSoap.AsyncSoapCallBack<CToPBResponse> callBackCToKD = new SoapXML.AsyncSoap.AsyncSoapCallBack<CToPBResponse>() {
            @Override
            public void onPre(SoapXML.AsyncSoap soap) {
                //show progress bar
                showProgresbar(true);
            }

            @Override
            public void onUpdate(String message) {
                //ẩn progress bar
                showProgresbar(false);

                showProgresbar(false);
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
                snackbar.show();
            }

            @Override
            public void onPost(CToPBResponse response) {
                //ẩn progress bar
                showProgresbar(false);

                //Xử lý kết quả
                try {
                    doProcessAfterSearchOnline(response);
                } catch (Exception e) {
                    Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    e.printStackTrace();
                }
            }

            @Override
            public HashMap<String, SoapObject> filterDataReal(SoapObject response) {
                //lọc từ response trả về những giá trị thực sự của API
                //tùy chỉnh theo thuộc tính xml node trả về
                if (response == null)
                    return null;

                SoapObject soapLv1 = (SoapObject) response.getProperty("diffgram");
                SoapObject proInfoLv1 = (SoapObject) soapLv1.getProperty("NewDataSet");

                //kiểm tra nếu có property 'CTO' thì lấy dữ liệu dataset
                //ngược lại nếu là 'Table1" thì lấy dữ liệu thông báo
                //2 giá trị này được cung cấp bởi server, nên debug các giá trị cây của soapObject để nắm rõ

                HashMap<String, SoapObject> result = null;
                SoapObject proInfoLv2 = null;

                if (proInfoLv1.hasProperty("CTO")) {
                    proInfoLv2 = (SoapObject) proInfoLv1.getProperty("CTO");
                    String soap = proInfoLv2.toString();
                    Log.d(TAG, "filterDataReal: " + soap);

                    result = new HashMap<>();
                    result.put("CTO", proInfoLv2);
                }

                if (proInfoLv1.hasProperty("Table1")) {
                    proInfoLv2 = (SoapObject) proInfoLv1.getProperty("Table1");
                    String soap = proInfoLv2.toString();
                    Log.d(TAG, "filterDataReal: " + soap);

                    result = new HashMap<>();
                    result.put("Table1", proInfoLv2);
                }
                return result;
            }
        };

        SoapXML.AsyncSoap<CToPBResponse> soapSearchCto = null;

        if (mKieuCongTo == Common.KIEU_CONG_TO.PHAN_BO)
            soapSearchCto = new SoapXML.AsyncSoap(
                    CToPBResponse.class,
                    callBackCToPB,
                    SoapXML.METHOD.CTO_PB.getNameMethod(),
                    SoapXML.getURL(URL),
                    SoapXML.METHOD.CTO_PB.getNameParams()
            );
        else
            soapSearchCto = new SoapXML.AsyncSoap(
                    CToPBResponse.class,
                    callBackCToKD,
                    SoapXML.METHOD.CTO_KD.getNameMethod(),
                    SoapXML.getURL(URL),
                    SoapXML.METHOD.CTO_KD.getNameParams()
            );

        soapSearchCto.execute(requestParams);

    }

    private void doProcessAfterSearchOnline(CToPBResponse response) throws Exception {
        if (response == null)
            throw new Exception(Common.MESSAGE.ex06.getContent());

        //lấy các dữ liệu cần thiết ghi vào database
        CongTo congTo = new CongTo();
        congTo.setMA_DVIQLY(response.getMA_DVIQLY());
        //tạm thời chỉ số tháo đang lược bỏ đi từ server
        congTo.setCHISO_THAO("");
        congTo.setCLOAI(String.valueOf(response.getMA_CLOAI()));
        congTo.setMA_CTO(response.getMA_CTO());
        congTo.setNAMSX(response.getNAM_SX());
        congTo.setSO_CTO(response.getSO_CTO());
        congTo.setNGAY_NHAP(Common.getDateTimeNow(Common.DATE_TIME_TYPE.yyyyMMdd));

        if (mKieuCongTo == Common.KIEU_CONG_TO.PHAN_BO)
            mSqlDAO.insertTBL_CTO_PB(congTo);
        else mSqlDAO.insertTBL_CTO_GUI_KD(congTo);

        //làm mới lại list với việc search local
        searchLocal(mEtSearchOnline.getText().toString());

        //làm mới lại text đếm cto

    }

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

    private void callFragment(TAG_FRAG tagFrag) {
        Fragment fragment = null;
        if (tagFrag == TAG_FRAG.DANHSACH) {
            fragment = MainFragment.newInstance();
        }
        if (tagFrag == TAG_FRAG.CHITIET) {
            fragment = CtietCongToFragment.newInstance("", "");
        }

        if (fragment == null)
            return;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rl_main, fragment, tagFrag.name());
        fragmentTransaction.commit();
    }

    //region DsCongToAdapter.OnDsCtoAdapterIteraction
    @Override
    public void clickBtnGhimRowCto(int pos) {
//        if (pos >= mListCtoPB.size())
//            return;
//
//        try {
//            //set filterDataReal GhimCto đồng thời đánh dấu cần refersh lại giá trị công tơ đó
//            int statusGhimCto = mListCtoPB.get(pos).getmGhimCto();
//            mSqlDAO.updateGhimCto(mListCtoPB.get(pos).getSTT(), mKieuCongTo, (statusGhimCto == 0) ? 1 : 0);
//
//            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.rl_main);
//            if (fragment instanceof MainFragment) {
//                ((MainFragment) fragment).clickBtnGhimRowCto(pos);
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void clickBtnChiTiet(int pos) {
        if (pos >= mListCtoPB.size())
            return;

        try {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.rl_main);
            if (fragment instanceof MainFragment) {
                ((MainFragment) fragment).clickBtnChiTiet(pos);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void clickBtnXoa(final int pos) {
        int size = (mKieuCongTo == Common.KIEU_CONG_TO.PHAN_BO) ? mListCtoPB.size() : mListCtoKD.size();
        if (pos >= size) {
            return;
        }

        final CongToProxy congToProxy = (mKieuCongTo == Common.KIEU_CONG_TO.PHAN_BO) ? mListCtoPB.get(pos) : mListCtoKD.get(pos);

        OnClickButtonAlertDialog onClickButtonAlertDialog = new OnClickButtonAlertDialog() {
            @Override
            public void doClickYes() {
                try {
                    mSqlDAO.deleteCongTo(congToProxy.getSTT());

                    if (mKieuCongTo == Common.KIEU_CONG_TO.PHAN_BO) {
                        mListCtoPB.clear();
                        mListCtoPB = mSqlDAO.getAllCongTo(mKieuCongTo);
                    } else {
                        mListCtoKD.clear();
                        mListCtoKD = mSqlDAO.getAllCongTo(mKieuCongTo);
                    }
                    fillDataReyclerFull(mKieuCongTo);
//                    //refresh lại list
//                    MainActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                if (mKieuCongTo == Common.KIEU_CONG_TO.PHAN_BO) {
//                                    mListCtoPB.clear();
//                                    mListCtoPB = mSqlDAO.getAllCongTo(mKieuCongTo);
//                                } else {
//                                    mListCtoKD.clear();
//                                    mListCtoKD = mSqlDAO.getAllCongTo(mKieuCongTo);
//                                }
//                                fillDataReyclerFull(mKieuCongTo);
//                            } catch (Exception e) {
//                                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
//                                snackbar.show();
//                                e.printStackTrace();
//                            }
//                        }
//                    });

                } catch (Exception e) {
                    Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    e.printStackTrace();
                }
            }

            @Override
            public void doClickNo() {
                Toast.makeText(MainActivity.this, "doClickNo", Toast.LENGTH_SHORT).show();
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
    //endregion

    //region OnFragmentInteractionListener
    @Override
    public List<CongToProxy> interactionDataCongTo(
//            Common.KIEU_DANHSACH kieuDanhsach
    ) {
//        mListCtoPB.clear();
//        mListCtoPB = mSqlDAO.getAllCongTo(mKieuCongTo);
        return mListCtoPB;

//        if(kieuDanhsach == Common.KIEU_DANHSACH.ALL) {
//            mListCtoPB.clear();
//            mListCtoPB = mSqlDAO.getAllCongTo(mKieuCongTo);
//            return mListCtoPB;
//        }
//
//        if(kieuDanhsach == Common.KIEU_DANHSACH.ALL) {
//            mListCtoGhim.clear();
//            mListCtoGhim = mSqlDAO.getAllCongToGhim(mKieuCongTo);
//            return mListCtoGhim;
//        }
//
//        return new ArrayList<>();
    }

    @Override
    public String interactionDataDienLuc(String maDienLuc) {
        if (TextUtils.isEmpty(maDienLuc))
            return "";
        String result = "";
        try {
            result = mSqlDAO.selectDienLuc(maDienLuc);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    public void searchLocal(CharSequence charSearch) {
        try {
            String dateSql = Common.convertDateUIToDateSQL(mDate);
            if (mKieuCongTo == Common.KIEU_CONG_TO.PHAN_BO) {
                mListCtoPB = mSqlDAO.getAllCongTo(mKieuCongTo, dateSql);
            } else {
                mListCtoKD = mSqlDAO.getAllCongTo(mKieuCongTo, dateSql);
            }
            searchLocalOnDate(charSearch);

        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
            e.printStackTrace();
            Log.e(MainActivity.class.getName(), "interactionDataCongToSearch: " + e.getMessage());
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
            if (mKieuCongTo == Common.KIEU_CONG_TO.PHAN_BO) {
                String query = Common.removeAccent(charSearch.toString().trim().toLowerCase());
                for (CongToProxy congToProxy : mListCtoPB) {
                    if (Common.removeAccent(congToProxy.getmMaCto().toLowerCase()).contains(query)
                            || Common.removeAccent(congToProxy.getmSoCto().toLowerCase()).contains(query)
                            || convertDateSQLToDateUI(congToProxy.getNGAY_NHAP()).contains(query)) {
                        data.add(congToProxy);
                    }
                }
            } else {
                String query = Common.removeAccent(charSearch.toString().trim().toLowerCase());
                for (CongToProxy congToProxy : mListCtoKD) {
                    if (Common.removeAccent(congToProxy.getmMaCto().toLowerCase()).contains(query)
                            || Common.removeAccent(congToProxy.getmSoCto().toLowerCase()).contains(query)
                            || convertDateSQLToDateUI(congToProxy.getNGAY_NHAP()).contains(query)) {
                        data.add(congToProxy);
                    }
                }
            }

            //set text thống kê cto theo số item adapter
//            setTextCountCtoAndDate(data.size());

            //giữ nguyên dữ liệu, lọc cái cần dùng
            fillDataReyclerLocal(mKieuCongTo, data);
//            MainActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                      fillDataReyclerFull(mKieuCongTo);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
            e.printStackTrace();
            Log.e(MainActivity.class.getName(), "interactionDataCongToSearch: " + e.getMessage());
        }
    }

    private void fillDataReyclerFull(Common.KIEU_CONG_TO kieuCongTo) throws Exception {
        int mCountCto = 0;
        if (kieuCongTo == Common.KIEU_CONG_TO.PHAN_BO) {
//            if (mCtoPBAdapter == null) {
            mCtoPBAdapter = new DsCongToAdapter(this, mListCtoPB);
            mRvCto.setAdapter(mCtoPBAdapter);
//            } else
//                mCtoPBAdapter.refresh(mListCtoPB);

            mCountCto = mListCtoPB.size();
        } else {
//            if (mCtoKDAdapter == null) {
            mCtoKDAdapter = new DsCongToAdapter(this, mListCtoKD);
            mRvCto.setAdapter(mCtoKDAdapter);
//            } else
//                mCtoKDAdapter.refresh(mListCtoKD);

            mCountCto = mListCtoKD.size();
        }

        mRvCto.invalidate();

        //set text thống kê cto theo số item adapter
        setTextCountCtoAndDate(mCountCto);
//        MainActivity.this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                setTextCountCtoAndDate(mCountCto);
//            }
//        });
    }

    private void fillDataReyclerLocal(Common.KIEU_CONG_TO kieuCongTo, List<CongToProxy> data) throws Exception {
        int mCountCto = 0;

        if (kieuCongTo == Common.KIEU_CONG_TO.PHAN_BO) {
            mCtoPBAdapter = new DsCongToAdapter(this, data);
            mRvCto.setAdapter(mCtoPBAdapter);
            mCountCto = data.size();
        } else {
            mCtoKDAdapter = new DsCongToAdapter(this, data);
            mRvCto.setAdapter(mCtoKDAdapter);
            mCountCto = data.size();
        }

        mRvCto.invalidate();

        //set text thống kê cto theo số item adapter
        setTextCountCtoAndDate(mCountCto);
    }

    @Override
    public void interactionDataCongToSearch(CharSequence charSearch, boolean isDsCtoGhim) {
        if (TextUtils.isEmpty(charSearch))
            return;

        try {
            mListCtoPB.clear();
            mListCtoPB = (isDsCtoGhim) ? mSqlDAO.getAllCongToGhim() : mSqlDAO.getAllCongTo();

            List<CongToProxy> data = new ArrayList<>();
            String query = Common.removeAccent(charSearch.toString().trim().toLowerCase());
            for (CongToProxy congToProxy : mListCtoPB) {
                if (Common.removeAccent(congToProxy.getmMaCto().toLowerCase()).contains(query)
                        || Common.removeAccent(congToProxy.getmMaCto().toLowerCase()).contains(query)) {
                    data.add(congToProxy);
                }
            }

            mListCtoPB.clear();
            mListCtoPB.addAll(data);

            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.rl_main);
            if (fragment instanceof MainFragment) {
                ((MainFragment) fragment).search();
            }
        } catch (Exception e) {
            Log.e(MainActivity.class.getName(), "interactionDataCongToSearch: " + e.getMessage());
        }
    }
    //endregion

    //region DsGhimCtoFragment.OnDsGhimCtoFragmentInteractionListener
    @Override
    public List<CongToProxy> interactionDataGhimCongTo() {

//
//        mListCtoGhim.clear();
//        mListCtoGhim = mSqlDAO.getAllCongToGhim(mKieuCongTo);
//        return mListCtoGhim;
        return null;
    }
    //endregion

    private List<CongTo> dumpData(Common.KIEU_CONG_TO type) {

        List<CongTo> result = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            CongTo congTo = new CongTo();
            congTo.setMA_DVIQLY(type.toString() + " PD" + i + 1);
            congTo.setCHISO_THAO(type.toString() + "" + i + 1);
            congTo.setCLOAI(type.toString() + "China");
            congTo.setMA_CTO(type.toString() + "Z" + i + 1);
            congTo.setNAMSX(type.toString() + "2015");
            congTo.setSO_CTO(type.toString() + "QZ" + i + 1);
//            congTo.setmTaiKhoan(MainActivity.sTaiKhoan);
            congTo.setNGAY_NHAP(Common.getDateTimeNow(Common.DATE_TIME_TYPE.yyyyMMdd));
//            congTo.setmGhimCto(0);
//            congTo.setmKieuCongTo(i % 2);
            result.add(congTo);
        }
        return result;
    }

    //endregion

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

    public enum TAG_FRAG {
        DANHSACH, CHITIET
    }

//    private View.OnClickListener clickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.fab_bluetooth:
//                    Toast.makeText(MainActivity.this, "Quét bluetooth", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.fab_camera:
//                    Toast.makeText(MainActivity.this, "Quét camera", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    };

    private void processBluetooth() {
        mEtSearchOnline.setFocusable(true);
        Intent settingsIntent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
        startActivity(settingsIntent);
//        Intent intent = new Intent(MainActivity.this, BluetoothDemo.class);
//        startActivity(intent);
//        if (!BA.isEnabled()) {
//            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(turnOn, 0);
//            Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
//        }
    }

    public interface OnClickButtonAlertDialog {
        void doClickYes();

        void doClickNo();
    }

    public interface IOnPauseScannerBarcodeListner {
        public void pause();
    }

    public void setPauseScannerBarcodeListner(IOnPauseScannerBarcodeListner pauseScannerBarcodeListner) {
        this.pauseScannerBarcodeListner = pauseScannerBarcodeListner;
    }
}
