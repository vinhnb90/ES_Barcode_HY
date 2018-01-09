package esolutions.com.barcodehungyenpc.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatSpinner;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.database.SqlConnect;
import esolutions.com.barcodehungyenpc.database.SqlDAO;
import esolutions.com.barcodehungyenpc.entity.DienLuc;
import esolutions.com.barcodehungyenpc.entity.DienLucProxy;
import esolutions.com.barcodehungyenpc.entity.DonViResponse;
import esolutions.com.barcodehungyenpc.entity.EmptyResponse;
import esolutions.com.barcodehungyenpc.entity.ThongBaoResponse;
import esolutions.com.barcodehungyenpc.utils.Common;
import esolutions.com.barcodehungyenpc.utils.SharePrefManager;
import esolutions.com.barcodehungyenpc.utils.SoapXML;

import static esolutions.com.barcodehungyenpc.utils.Common.NAME_FILE_LOG;
import static esolutions.com.barcodehungyenpc.utils.Common.PATH_LOG;
import static esolutions.com.barcodehungyenpc.utils.Log.*;

public class DangNhapActivity extends BaseActivity implements
        ActionBar.TabListener,
//        InfoUserFragment.OnInfoUserFragmentInteractionListener,
        InfoConfigFragment.OnInfoConfigFragmentInteractionListener {

    public static final String TAG = DangNhapActivity.class.getName();
    public static final String PARAM_SERVER_URL = "PARAM_SERVER_URL";
    public static final String PARAM_DVI = "PARAM_DVI";


    private CoordinatorLayout mCoordinatorLayout;
    private ImageButton mIbtnDownDvi;
    private ProgressBar mPbarDownDvi;
    private EditText mEtUser;
    private EditText mEtPass;
    private ImageButton mIbtnVisibePass;
    private CheckBox mCbSaveInfo;
    private Button mBtnLogin;
    private ProgressBar mPbarLogin;


    private SharePrefManager mPrefManager;
    private SQLiteDatabase mDatabase;
    private SqlDAO mSqlDAO;
    private static boolean isLoadedFolder = false;

    SoapXML.AsyncSoap<List<DonViResponse>, ThongBaoResponse> soapDownloadDvi = null;

    SoapXML.AsyncSoap<EmptyResponse, ThongBaoResponse> soapLogin = null;

    public static final String PARAM_USER = "PARAM_USER";
    public static final String PARAM_PASS = "PARAM_PASS";
    public static final String PARAM_CODE_DIENLUC = "PARAM_CODE_DIENLUC";

    public static final String PREF_CONFIG = "PREF_CONFIG";
    public static final String KEY_PREF_POS_PROGRAME = "KEY_PREF_POS_PROGRAME";
    public static final String KEY_PREF_SERVER_URL = "KEY_PREF_SERVER_URL";
    public static final String KEY_PREF_POS_DVI = "KEY_PREF_POS_DVI";
    public static final String KEY_PREF_USER = "KEY_PREF_USER";
    public static final String KEY_PREF_PASS = "KEY_PREF_PASS";
    public static final String KEY_PREF_CB_SAVE = "KEY_PREF_CB_SAVE";
    public static final String KEY_PREF_KEYBOARD = "KEY_PREF_KEYBOARD";

    private String[] tabs = {"Info Config"};

    private String mMaDienLuc, mURLServer;
    private boolean mIsCbSaveChecked;
    private AppCompatSpinner mCompatSpinnerPrograme;
    private AppCompatSpinner mCompatSpinnerDvi;
    private EditText mEtURL;
    private String mUser;
    private String mPass;
    private int mPosPrograme;
    private int mPosDvi;
    private Bundle savedInstanceState;
    public static final String PARAM_POS_PROGRAME = "PARAM_POS_PROGRAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_dangnhap);
        super.hideBar();

        if (Common.checkPermission(this)) {
            return;
        }

        getInstance().setIsModeDebug(true);

        try {
            //setup file debug
            getInstance().setupFile(this, PATH_LOG + NAME_FILE_LOG).setIsModeDebug(true);
            initView();
            handleListener();
            setAction(savedInstanceState);
        } catch (Exception e) {
            try {
                getInstance().loge(DangNhapActivity.class, e.getMessage());
            } catch (Exception e1) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                e1.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            fillInfoLogin();
        } catch (Exception e) {
            try {
                getInstance().loge(DangNhapActivity.class, e.getMessage());
            } catch (Exception e1) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                e1.printStackTrace();
            }
        }
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
                    Toast.makeText(DangNhapActivity.this, "Unable to show permission required", Toast.LENGTH_LONG).show();
                    Log.e(getClass().getName(), "Unable to show permission required");
                } else {
                    try {
                        initView();
                        handleListener();
                        setAction(savedInstanceState);
                    } catch (Exception e) {
                        try {
                            getInstance().loge(DangNhapActivity.class, e.getMessage());
                        } catch (Exception e1) {
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            e1.printStackTrace();
                        }
                    }
                }
                return;
            }
        }
    }

    private void fillInfoLogin() {
        //get data shared pref
        if (mPrefManager == null)
            mPrefManager = SharePrefManager.getInstance(this);
        mPosPrograme = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).
                getInt(KEY_PREF_POS_PROGRAME, 0);
        mURLServer = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).
                getString(KEY_PREF_SERVER_URL, "");
        mPosDvi = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).
                getInt(KEY_PREF_POS_DVI, 0);
        mUser = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).
                getString(KEY_PREF_USER, "");
        mPass = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).
                getString(KEY_PREF_PASS, "");
        mIsCbSaveChecked = mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).
                getBoolean(KEY_PREF_CB_SAVE, false);

        //fill data
        mCompatSpinnerPrograme.setSelection(mPosPrograme);
        mEtURL.setText(mURLServer);
        if (mPosPrograme >= mCompatSpinnerDvi.getCount())
            mCompatSpinnerDvi.setSelection(0);
        else
            mCompatSpinnerDvi.setSelection(mPosDvi);
        mEtUser.setText(mUser);
        mEtPass.setText(mPass);
        mCbSaveInfo.setChecked(mIsCbSaveChecked);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        mCompatSpinnerPrograme = (AppCompatSpinner) findViewById(R.id.spin_program);
        mCompatSpinnerDvi = (AppCompatSpinner) findViewById(R.id.spin_dvi);
        mEtURL = (EditText) findViewById(R.id.et_link);
        mIbtnDownDvi = (ImageButton) findViewById(R.id.ibtn_download_dvi);
        mPbarDownDvi = (ProgressBar) findViewById(R.id.pbar_download_dvi);
        mEtUser = (EditText) findViewById(R.id.et_user);
        mEtPass = (EditText) findViewById(R.id.et_pass);
        mIbtnVisibePass = (ImageButton) findViewById(R.id.ibtn_visible_pass);
        mCbSaveInfo = (CheckBox) findViewById(R.id.cb_save_info);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mPbarLogin = (ProgressBar) findViewById(R.id.pbar_login);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.cl_login);
    }

    @Override
    protected void handleListener() throws Exception {
        try {
            //btn Download dvi
            mIbtnDownDvi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        downloadDonVi();
                    } catch (Exception e) {
                        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        e.printStackTrace();
                    }
                }
            });

            mIbtnVisibePass.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            mEtPass.setInputType(InputType.TYPE_CLASS_TEXT);
                            break;
                        case MotionEvent.ACTION_UP:
                            mEtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            break;
                    }
                    return true;
                }
            });

//            mIbtnVisibePass.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (mEtPass.getInputType() != InputType.TYPE_CLASS_TEXT) {
//                        mEtPass.setInputType(InputType.TYPE_CLASS_TEXT);
//                        mIbtnVisibePass.setImageResource(R.mipmap.ic_visibe_off);
//                    } else {
//                        mEtPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        mIbtnVisibePass.setImageResource(R.mipmap.ic_visible);
//                    }
//                }
//            });

            //btnLogin
            mBtnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!validateInput())
                        return;

                    try {
                        callLogin();
                    } catch (Exception e) {
                        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        e.printStackTrace();
                    }
                }
            });

//        mCbSaveInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                try {
//                    if (mCbSaveInfo.isPressed()) {
//                        mIsCbSaveChecked = isChecked;
//                        if (validateInput()) {
//                            if (isChecked) {
//                                mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE)
//                                        .edit()
//                                        .putString(KEY_PREF_USER, mUser)
//                                        .putString(KEY_PREF_PASS, mPass)
//                                        .putString(KEY_PREF_MA_DIEN_LUC, mMaDienLuc)
//                                        .putString(KEY_PREF_SERVER_URL, mURLServer)
//                                        .putBoolean(KEY_PREF_CB_SAVE, mIsCbSaveChecked)
//                                        .commit();
//                            } else
//                                mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE)
//                                        .edit()
//                                        .clear()
//                                        .commit();
//                        } else {
//                            mIsCbSaveChecked = false;
//                            mCbSaveInfo.setChecked(mIsCbSaveChecked);
//                        }
//                    }
//                } catch (Exception e) {
//                    Log.e(TAG, "onCheckedChanged: " + e.getMessage());
//                }
//            }
//        });

        } catch (Exception e) {
            throw e;
        }
    }

    private void callLogin() throws Exception {
        //check thread dvi
        if (soapDownloadDvi != null) {
            if (soapDownloadDvi.getStatus() == AsyncTask.Status.RUNNING || soapDownloadDvi.getStatus() == AsyncTask.Status.PENDING) {
                throw new Exception(Common.MESSAGE.ex23.getContent());
            }
        }

        //check url
        if (StringUtils.isEmpty(mEtURL.getText().toString().trim())) {
            throw new Exception(Common.MESSAGE.ex08.getContent());
        }

        String dvi = ((ArrayAdapter<String>) mCompatSpinnerDvi.getAdapter()).getItem(mCompatSpinnerDvi.getSelectedItemPosition());
        final String[] requestParams = new String[]{mEtUser.getText().toString().trim(), mEtPass.getText().toString().trim(), dvi};

        if (!Common.isNetworkConnected(this)) {
            throw new Exception(Common.MESSAGE.ex07.getContent());
        }

        final SoapXML.AsyncSoap.AsyncSoapCallBack<Boolean, ThongBaoResponse> callbackLogin = new SoapXML.AsyncSoap.AsyncSoapCallBack<Boolean, ThongBaoResponse>() {
            @Override
            public void onPre(SoapXML.AsyncSoap soap) {
                //show progress Login
                DangNhapActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mBtnLogin.setVisibility(View.GONE);
                        mPbarLogin.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onUpdate(final String message) {
                DangNhapActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //ẩn progress bar
                        mBtnLogin.setVisibility(View.VISIBLE);
                        mPbarLogin.setVisibility(View.GONE);

                        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });

            }


            @Override
            public void onPostData(Boolean dataResponse) {
                Log.d(TAG, "onPostData: " + dataResponse.toString());
                mBtnLogin.setVisibility(View.VISIBLE);
                mPbarLogin.setVisibility(View.GONE);

                if (dataResponse.equals(false)) {
                    onUpdate(Common.MESSAGE.ex24.getContent());
                    return;
                }
                //save info or clear info
                if (mPrefManager == null)
                    mPrefManager = SharePrefManager.getInstance(DangNhapActivity.this);

                mPosPrograme = (mCbSaveInfo.isChecked()) ? mCompatSpinnerPrograme.getSelectedItemPosition() : 0;
                mURLServer = (mCbSaveInfo.isChecked()) ? mEtURL.getText().toString() : "";
                mPosDvi = (mCbSaveInfo.isChecked()) ? mCompatSpinnerDvi.getSelectedItemPosition() : 0;
                mUser = (mCbSaveInfo.isChecked()) ? mEtUser.getText().toString() : "";
                mPass = (mCbSaveInfo.isChecked()) ? mEtPass.getText().toString() : "";
                mIsCbSaveChecked = mCbSaveInfo.isChecked();

                mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).edit().
                        putInt(KEY_PREF_POS_PROGRAME, mPosPrograme).
                        commit();

                mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).edit().
                        putString(KEY_PREF_SERVER_URL, mURLServer).
                        commit();

                mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).edit().
                        putInt(KEY_PREF_POS_DVI, mPosDvi).
                        commit();

                mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).edit().
                        putString(KEY_PREF_USER, mUser).
                        commit();

                mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).edit().
                        putString(KEY_PREF_PASS, mPass).
                        commit();

                mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE).edit().
                        putBoolean(KEY_PREF_CB_SAVE, mIsCbSaveChecked).
                        commit();


                Bundle bundle = new Bundle();
                bundle.putInt(PARAM_POS_PROGRAME, mCompatSpinnerPrograme.getSelectedItemPosition());
                bundle.putString(PARAM_SERVER_URL, mEtURL.getText().toString());
                String dvi = ((ArrayAdapter<String>) mCompatSpinnerDvi.getAdapter()).getItem(mCompatSpinnerDvi.getSelectedItemPosition());
                bundle.putString(PARAM_DVI, dvi);
                bundle.putString(PARAM_USER, mEtUser.getText().toString());
                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public void onPostMessageSever(String errorResponse) {

            }

        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    soapLogin = new SoapXML.AsyncSoap(
                            Boolean.class,
                            ThongBaoResponse.class,
                            "thongbao",
                            callbackLogin,
                            SoapXML.METHOD.Select_DangNhap.getNameMethod(),
                            SoapXML.getURL(mEtURL.getText().toString()),
                            SoapXML.METHOD.Select_DangNhap.getNameParams()
                    );
                } catch (Exception e) {
                    try {
                        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        getInstance().loge(DangNhapActivity.class, e.getMessage());
                        e.printStackTrace();
                    } catch (Exception e1) {
                        Toast.makeText(DangNhapActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        e1.printStackTrace();
                    }
                }

                soapLogin.execute(requestParams);
            }
        }).start();

    }

    private void downloadDonVi() throws Exception {
        //check thread hiện tại
        if (soapDownloadDvi != null) {
            if (soapDownloadDvi.getStatus() == AsyncTask.Status.RUNNING || soapDownloadDvi.getStatus() == AsyncTask.Status.PENDING) {
                throw new Exception(Common.MESSAGE.ex23.getContent());
            }
        }

        //check url
        if (StringUtils.isEmpty(mEtURL.getText().toString().trim())) {
            throw new Exception(Common.MESSAGE.ex08.getContent());
        }

        String[] requestParams = new String[]{};

        if (!Common.isNetworkConnected(this)) {
            throw new Exception(Common.MESSAGE.ex07.getContent());
        }

        SoapXML.AsyncSoap.AsyncSoapCallBack<List<DonViResponse>, ThongBaoResponse> callBackDonVi = new SoapXML.AsyncSoap.AsyncSoapCallBack<List<DonViResponse>, ThongBaoResponse>() {
            @Override
            public void onPre(SoapXML.AsyncSoap soap) {
                //show progress bar dvi
                mIbtnDownDvi.setVisibility(View.GONE);
                mPbarDownDvi.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUpdate(String message) {
                //ẩn progress bar
                mIbtnDownDvi.setVisibility(View.VISIBLE);
                mPbarDownDvi.setVisibility(View.GONE);

                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
                snackbar.show();
            }

            @Override
            public void onPostData(List<DonViResponse> dataResponse) {
                Log.d(TAG, "onPostData: " + dataResponse.toString());

                mPbarDownDvi.setVisibility(View.GONE);
                mIbtnDownDvi.setVisibility(View.VISIBLE);

                try {
                    if (dataResponse.isEmpty())
                        throw new Exception(Common.MESSAGE.ex06.getContent());

                    //ghi vao data base
                    for (DonViResponse dvi : dataResponse
                            ) {
                        //check dvi
                        if (!mSqlDAO.checkExistTBL_DIENLUC(dvi.getMA_DVIQLY()))
                            mSqlDAO.insertTBL_DIENLUC(new DienLuc(dvi.getMA_DVIQLY()));

                    }

                    //refreshListKD data spin dien luc
                    setSpinDienLuc();

                } catch (Exception e) {
                    try {
                        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        getInstance().loge(DangNhapActivity.class, e.getMessage());
                        e.printStackTrace();
                    } catch (Exception e1) {
                        Toast.makeText(DangNhapActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void onPostMessageSever(String errorResponse) {
                Log.e(TAG, "onPostData: " + errorResponse.toString());
                mPbarDownDvi.setVisibility(View.GONE);
                mIbtnDownDvi.setVisibility(View.VISIBLE);
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, errorResponse, Snackbar.LENGTH_LONG);
                snackbar.show();
            }

//            @Override
//            public void onPostMessageSever(ThongBaoResponse errorResponse) {
//                Log.e(TAG, "onPostData: " + errorResponse.toString());
//                mPbarDownDvi.setVisibility(View.GONE);
//                mIbtnDownDvi.setVisibility(View.VISIBLE);
//                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, errorResponse.getThongbao(), Snackbar.LENGTH_LONG);
//                snackbar.show();
//            }
        };

        soapDownloadDvi = new SoapXML.AsyncSoap(
                DonViResponse.class,
                ThongBaoResponse.class,
                "thongbao",
                callBackDonVi,
                SoapXML.METHOD.Select_MADVIQLY.getNameMethod(),
                SoapXML.getURL(mEtURL.getText().toString()),
                SoapXML.METHOD.Select_MADVIQLY.getNameParams()
        );

        soapDownloadDvi.execute(requestParams);
    }

    @Override
    public void setAction(Bundle savedInstanceState) throws Exception {

        mDatabase = SqlConnect.getInstance(this).open();
        mSqlDAO = new SqlDAO(mDatabase, this);

        //hiển thị folder trên sdcard
        if (!isLoadedFolder) {
            Common.showFolder(this);
            isLoadedFolder = !isLoadedFolder;
        }

        //init shared preref
        mPrefManager = SharePrefManager.getInstance(this);

        this.checkSharePreference(mPrefManager);

        //show spin programe
        mCompatSpinnerPrograme.setAdapter(new ArrayAdapter<String>(DangNhapActivity.this, R.layout.row_spin_type_1, R.id.tv_spin, new String[]{Common.KIEU_CHUONG_TRINH.PHAN_BO.getName(), Common.KIEU_CHUONG_TRINH.KIEM_DINH.getName()}));

        //show spin dien luc
        setSpinDienLuc();

        ((TextView) findViewById(R.id.tv_version)).setText("Phiên bản phần mềm : " + Common.getVersionApp(DangNhapActivity.this));

        mEtURL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mEtURL.setError(null);
                return false;
            }
        });

        mEtUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mEtUser.setError(null);
                return false;
            }
        });


        mEtPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mEtPass.setError(null);
                return false;
            }
        });


    }

    private void setSpinDienLuc() throws Exception {
        List<DienLucProxy> dienLucProxies = mSqlDAO.getAllTBL_DIENLUC();
        List<String> dienLuc = new ArrayList<>();
        //dump data
//        dienLuc.add("PD0100");
//        dienLuc.add("PD");
        for (DienLucProxy proxy :
                dienLucProxies) {
            dienLuc.add(proxy.getMA_DVIQLY());
        }
        mCompatSpinnerDvi.setAdapter(new ArrayAdapter<String>(DangNhapActivity.this, R.layout.row_spin_type_1, R.id.tv_spin, dienLuc));

    }

    private boolean validateInput() {
        if (StringUtils.isEmpty(mEtURL.getText().toString())) {
            mEtURL.setError(Common.MESSAGE.ex03.getContent());
            return false;
        }

        if (StringUtils.isEmpty(mEtUser.getText().toString())) {
            mEtUser.setError(Common.MESSAGE.ex03.getContent());
            return false;
        }

        if (StringUtils.isEmpty(mEtPass.getText().toString())) {
            mEtPass.setError(Common.MESSAGE.ex03.getContent());
            return false;
        }

        if (mCompatSpinnerDvi.getAdapter().getCount() == 0) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, Common.MESSAGE.ex27.getContent(), Snackbar.LENGTH_LONG);
            snackbar.show();
            return false;
        }

        String dvi = ((ArrayAdapter<String>) mCompatSpinnerDvi.getAdapter()).getItem(mCompatSpinnerDvi.getSelectedItemPosition());
        if (StringUtils.isEmpty(dvi)) {
            ((TextView) mCompatSpinnerDvi.getChildAt(0)).setError(Common.MESSAGE.ex27.getContent());
            return false;
        }
        return true;
    }

    //region ActionBar.TabListener
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
    //endregion

    //region InfoUserFragment.OnInfoUserFragmentInteractionListener
//    @Override
//    public void updateInfoAccount(CharSequence charSequence) {
//        mUser = charSequence.toString();
//    }
//
//    @Override
//    public void updateInfoPass(CharSequence charSequence) {
//        mPass = charSequence.toString();
//    }
    //endregion

    //region InfoConfigFragment.OnInfoConfigFragmentInteractionListener
    @Override
    public void updateInfoURLServer(CharSequence charSequence) {
        mURLServer = charSequence.toString();
    }

    @Override
    public void updateInfoCodeDienLuc(CharSequence charSequence) {
        mMaDienLuc = charSequence.toString();
    }

    public static void checkSharePreference(SharePrefManager mPrefManager) {
        if (!mPrefManager.checkExistSharePref(PREF_CONFIG)) {
            mPrefManager.addSharePref(PREF_CONFIG, MODE_PRIVATE);
            mPrefManager.getSharePref(PREF_CONFIG, MODE_PRIVATE)
                    .edit()
                    .putInt(KEY_PREF_POS_PROGRAME, 0)
                    .putString(KEY_PREF_SERVER_URL, "")
                    .putInt(KEY_PREF_POS_DVI, 0)
                    .putString(KEY_PREF_USER, "")
                    .putString(KEY_PREF_PASS, "")
                    .putBoolean(KEY_PREF_CB_SAVE, false)
                    .putBoolean(KEY_PREF_KEYBOARD, true)
                    .commit();
        }
    }
    //endregion

//    private List<DienLuc> dumpDataSpin() {
//        if (mListDienLuc.isEmpty()) {
//            mListDienLuc.add(new DienLuc("Tổng công ty Điện Lực Hà Nội", "PD"));
//            mListDienLuc.add(new DienLuc("Tổng công ty Điện Lực Hà Nội", "PD"));
//            mListDienLuc.add(new DienLuc("Tổng công ty Điện Lực Hà Nội", "PD"));
//            mListDienLuc.add(new DienLuc("Tổng công ty Điện Lực Hà Nội", "PD"));
//            mListDienLuc.add(new DienLuc("Tổng công ty Điện Lực Hà Nội", "PD"));
//        }
//        return mListDienLuc;
//    }
}
