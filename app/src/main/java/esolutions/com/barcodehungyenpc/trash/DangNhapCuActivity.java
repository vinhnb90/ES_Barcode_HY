package esolutions.com.barcodehungyenpc.trash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.List;

import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.model.TabConfigLoginAdapter;
import esolutions.com.barcodehungyenpc.utils.SharePrefManager;
import esolutions.com.barcodehungyenpc.view.BaseActivity;
import esolutions.com.barcodehungyenpc.view.InfoConfigFragment;
import esolutions.com.barcodehungyenpc.view.InfoUserFragment;
import esolutions.com.barcodehungyenpc.view.MainActivity;

public class DangNhapCuActivity extends BaseActivity implements
        ActionBar.TabListener,
        InfoUserFragment.OnInfoUserFragmentInteractionListener,
        InfoConfigFragment.OnInfoConfigFragmentInteractionListener {

    private static final String TAG = DangNhapCuActivity.class.getName();
    private Button mBtnLogin;
    private ViewPager mVPage;
    private TabConfigLoginAdapter mAdapter;
    private ActionBar mActionBar;
    private CheckBox mCbSaveInfo;

    private SharePrefManager mPrefManager;

    public static final String PARAM_USER = "PARAM_USER";
    public static final String PARAM_PASS = "PARAM_PASS";
    public static final String PARAM_CODE_DIENLUC = "PARAM_CODE_DIENLUC";

    public static final String PREF_LOGIN = "PREF_LOGIN";
    public static final String KEY_PREF_USER = "KEY_PREF_USER";
    public static final String KEY_PREF_PASS = "KEY_PREF_PASS";
    public static final String KEY_PREF_MA_DIEN_LUC = "KEY_PREF_MA_DIEN_LUC";
    public static final String KEY_PREF_SERVER_URL = "KEY_PREF_SERVER_URL";
    public static final String KEY_PREF_CHECK_BOX = "KEY_PREF_CHECK_BOX";

    private String[] tabs = {"Info User", "Info Config"};

    private String mUser, mPass, mMaDienLuc, mURLServer;
    private boolean isCbSaveInfoChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        super.hideBar();
        initView();
        handleListener();
        setAction(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            fillInfoLogin();
        } catch (Exception e) {
            Log.e(DangNhapCuActivity.class.getName(), "fillInfoLogin: " + e.getMessage());
        }
    }

    private void fillInfoLogin() {
        if (mPrefManager == null)
            mPrefManager = SharePrefManager.getInstance(this);
        mUser = mPrefManager.getSharePref(PREF_LOGIN, MODE_PRIVATE).
                getString(KEY_PREF_USER, "");
        mPass = mPrefManager.getSharePref(PREF_LOGIN, MODE_PRIVATE).
                getString(KEY_PREF_PASS, "");
        mMaDienLuc = mPrefManager.getSharePref(PREF_LOGIN, MODE_PRIVATE).
                getString(KEY_PREF_MA_DIEN_LUC, "");
        mURLServer = mPrefManager.getSharePref(PREF_LOGIN, MODE_PRIVATE).
                getString(KEY_PREF_SERVER_URL, "");
        isCbSaveInfoChecked = mPrefManager.getSharePref(PREF_LOGIN, MODE_PRIVATE).
                getBoolean(KEY_PREF_CHECK_BOX, false);

        if (mAdapter == null)
            mAdapter = new TabConfigLoginAdapter(getSupportFragmentManager(), mUser, mPass, mMaDienLuc, mURLServer);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.updateDataInfoUser(mUser, mPass, mURLServer, mMaDienLuc);
            }
        });

        mCbSaveInfo.setChecked(isCbSaveInfoChecked);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        mBtnLogin = (Button) findViewById(R.id.btn_dienLuc);
        mCbSaveInfo = (CheckBox) findViewById(R.id.cb_save_info);
        mVPage = (ViewPager) findViewById(R.id.vpage_login);
        mAdapter = new TabConfigLoginAdapter(getSupportFragmentManager(), mUser, mPass, mMaDienLuc, mURLServer);
        mVPage.setAdapter(mAdapter);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeButtonEnabled(false);
            mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            // Adding Tabs
            for (String tab_name : tabs) {
                mActionBar.addTab(mActionBar.newTab().setText(tab_name)
                        .setTabListener(this));
            }
        }
    }

    @Override
    protected void handleListener() {
        //btnLogin
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()) {
                    if (isCbSaveInfoChecked) {
                        mPrefManager.getSharePref(PREF_LOGIN, MODE_PRIVATE)
                                .edit()
                                .putString(KEY_PREF_USER, mUser)
                                .putString(KEY_PREF_PASS, mPass)
                                .putString(KEY_PREF_MA_DIEN_LUC, mMaDienLuc)
                                .putString(KEY_PREF_SERVER_URL, mURLServer)
                                .putBoolean(KEY_PREF_CHECK_BOX, isCbSaveInfoChecked)
                                .commit();
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString(DangNhapCuActivity.PARAM_USER, mUser);
                    bundle.putString(DangNhapCuActivity.PARAM_PASS, mPass);
                    bundle.putString(DangNhapCuActivity.PARAM_CODE_DIENLUC, "CODE");

                    Intent intent = new Intent(DangNhapCuActivity.this, MainActivity.class);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
                ;
            }
        });

        mCbSaveInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                try {
                    if (mCbSaveInfo.isPressed()) {
                        isCbSaveInfoChecked = isChecked;
                        if (validateInput()) {
                            if (isChecked) {
                                mPrefManager.getSharePref(PREF_LOGIN, MODE_PRIVATE)
                                        .edit()
                                        .putString(KEY_PREF_USER, mUser)
                                        .putString(KEY_PREF_PASS, mPass)
                                        .putString(KEY_PREF_MA_DIEN_LUC, mMaDienLuc)
                                        .putString(KEY_PREF_SERVER_URL, mURLServer)
                                        .putBoolean(KEY_PREF_CHECK_BOX, isCbSaveInfoChecked)
                                        .commit();
                            } else
                                mPrefManager.getSharePref(PREF_LOGIN, MODE_PRIVATE)
                                        .edit()
                                        .clear()
                                        .commit();
                        } else {
                            isCbSaveInfoChecked = false;
                            mCbSaveInfo.setChecked(isCbSaveInfoChecked);
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, "onCheckedChanged: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void setAction(Bundle savedInstanceState) {
        mPrefManager = SharePrefManager.getInstance(this);
        mPrefManager.addSharePref(PREF_LOGIN, MODE_PRIVATE);
    }

    private boolean validateInput() {

        if (TextUtils.isEmpty(mUser)) {
            mVPage.setCurrentItem(0);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
                    if (mVPage.getCurrentItem() == 0) {
                        ((InfoUserFragment) fragmentList.get(0)).setErrorUser("Vui lòng không để trống!");
                    }
                }
            });
            return false;
        }

        if (TextUtils.isEmpty(mPass)) {
            mVPage.setCurrentItem(0);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
                    if (mVPage.getCurrentItem() == 0) {
                        ((InfoUserFragment) fragmentList.get(0)).setErrorPass("Vui lòng không để trống!");
                    }
                }
            });
            return false;
        }

        if (TextUtils.isEmpty(mMaDienLuc)) {
            mVPage.setCurrentItem(1);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
                    if (mVPage.getCurrentItem() == 1) {
                        ((InfoConfigFragment) fragmentList.get(1)).setErrorMaDienLuc("Vui lòng không để trống!");
                    }
                }
            });
            return false;
        }

        if (TextUtils.isEmpty(mURLServer)) {
            mVPage.setCurrentItem(1);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
                    if (mVPage.getCurrentItem() == 1) {
                        ((InfoConfigFragment) fragmentList.get(1)).setErrorUrlServer("Vui lòng không để trống!");
                    }
                }
            });
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
    @Override
    public void updateInfoAccount(CharSequence charSequence) {
        mUser = charSequence.toString();
    }

    @Override
    public void updateInfoPass(CharSequence charSequence) {
        mPass = charSequence.toString();
    }
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
