package esolutions.com.barcodehungyenpc.view;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import esolutions.com.barcodehungyenpc.model.DsCongToAdapter;
import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.database.SqlConnect;
import esolutions.com.barcodehungyenpc.database.SqlDAO;
import esolutions.com.barcodehungyenpc.entity.CongTo;
import esolutions.com.barcodehungyenpc.entity.CongToProxy;
import esolutions.com.barcodehungyenpc.trash.DangNhapCuActivity;
import esolutions.com.barcodehungyenpc.utils.Common;

public class MainActivity
        extends BaseActivity
        implements
        DsCtoFragment.OnDsCtoFragmentInteractionListener,
        DsGhimCtoFragment.OnDsGhimCtoFragmentInteractionListener,
        DsCongToAdapter.OnDsCtoAdapterIteraction {

    private List<FloatingActionMenu> menus = new ArrayList<>();
    private FloatingActionMenu mFabMenu;
    private FloatingActionButton mFabBluetooth;
    private FloatingActionButton mFabCamera;

    //    private FloatingActionButton mFabCamera;
//    private FloatingActionButton mFabBluetooth;
    private Handler mUiHandler = new Handler();

    private static final String TAG = MainActivity.class.getName();

    private RelativeLayout mRvMain;
    public static BottomBar sNavigation;
    public static String sMaDLuc, sTaiKhoan;
    private String mMatKhau;
    private List<CongToProxy> mListCto = new ArrayList<>();
    private List<CongToProxy> mListCtoGhim = new ArrayList<>();
    private SQLiteDatabase mDatabase;
    private SqlDAO mSqlDAO;
    private Common.KIEU_CONG_TO mKieuCongTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.hideBar();
        try {
            initView();
            handleListener();
            setAction(savedInstanceState);
        } catch (Exception e) {
            Log.e(TAG, "onCreate: " + e.getMessage());
        }
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        sMaDLuc = bundle.getString(DangNhapCuActivity.PARAM_CODE_DIENLUC, "");
        sTaiKhoan = bundle.getString(DangNhapCuActivity.PARAM_USER, "");
        mMatKhau = bundle.getString(DangNhapCuActivity.PARAM_PASS, "");
    }

    @Override
    protected void initView() {
        mRvMain = (RelativeLayout) findViewById(R.id.rl_main);
        sNavigation = (BottomBar) findViewById(R.id.nav_bottom_menu);
        mFabMenu = (FloatingActionMenu) findViewById(R.id.fab_menu);
        mFabBluetooth = (FloatingActionButton) findViewById(R.id.fab_bluetooth);
        mFabCamera = (FloatingActionButton) findViewById(R.id.fab_camera);
    }

    @Override
    public void setAction(final Bundle savedInstanceState) {
        menus.add(mFabMenu);

        //set Data
        mKieuCongTo = Common.KIEU_CONG_TO.PHAN_BO;
        mListCto = mSqlDAO.getAllCongTo(mKieuCongTo);
        mListCtoGhim = mSqlDAO.getAllCongToGhim(mKieuCongTo);
        if (mListCto.size() == 0) {
            mSqlDAO.insertListCongTo(dumpData());
            mListCto = mSqlDAO.getAllCongTo(mKieuCongTo);
            mListCtoGhim = mSqlDAO.getAllCongToGhim(mKieuCongTo);
        }

        //call Fragment
        callFragment(TAG_FRAG.DANHSACH);
    }

    @Override
    protected void handleListener() {
//        getBundle();
        mDatabase = SqlConnect.getInstance(this).open();
        mSqlDAO = new SqlDAO(mDatabase, this);

        sNavigation.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fragment = null;
                switch (tabId) {
                    case R.id.nav_bottom_cto_phanbo:
                        /*try {
                            mKieuCongTo = Common.KIEU_CONG_TO.PHAN_BO;
                            mListCto.clear();
                            mListCto = mSqlDAO.getAllCongTo(mKieuCongTo);

                            fragment = getSupportFragmentManager().findFragmentById(R.id.rl_main);
                            if (fragment instanceof MainFragment) {
                                ((MainFragment) fragment).clickBtnDsCto();
                            }
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Rất tiếc đã gặp vấn đề với chức năng này. Vui lòng liên hệ hỗ trợ! (4)", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                            Log.e("e", "mBtnDsCto: " + e.getMessage());
                        }*/
                        break;

                    case R.id.nav_bottom_cto_kiemdinh:
                       /* try {
                            mKieuCongTo = Common.KIEU_CONG_TO.KIEM_DINH;
                            mListCto.clear();
                            mListCto = mSqlDAO.getAllCongTo(mKieuCongTo);

                            fragment = getSupportFragmentManager().findFragmentById(R.id.rl_main);
                            if (fragment instanceof MainFragment) {
                                ((MainFragment) fragment).clickBtnDsCtoGhim();
                            }
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Rất tiếc đã gặp vấn đề với chức năng này. Vui lòng liên hệ hỗ trợ! (4)", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                            Log.e("e", "mBtnDsCto: " + e.getMessage());
                        }*/
                        break;
                }
            }
        });
        menus.add(mFabMenu);
        mFabMenu.setClosedOnTouchOutside(true);
        mFabMenu.hideMenuButton(false);

        mFabBluetooth.setOnClickListener(clickListener);
        mFabCamera.setOnClickListener(clickListener);
        int delay = 400;
        for (final FloatingActionMenu menu : menus) {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu.showMenuButton(true);
                }
            }, delay);
            delay += 150;
        }


        mFabMenu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFabMenu.isOpened()) {
                    Toast.makeText(MainActivity.this, mFabMenu.getMenuButtonLabelText(), Toast.LENGTH_SHORT).show();
                }

                mFabMenu.toggle(true);
            }
        });

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
        if (pos >= mListCto.size())
            return;

        try {
            //set data GhimCto đồng thời đánh dấu cần refersh lại giá trị công tơ đó
            int statusGhimCto = mListCto.get(pos).getmGhimCto();
            mSqlDAO.updateGhimCto(mListCto.get(pos).getmIdCto(), mKieuCongTo, (statusGhimCto == 0) ? 1 : 0);

            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.rl_main);
            if (fragment instanceof MainFragment) {
                ((MainFragment) fragment).clickBtnGhimRowCto(pos);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Rất tiếc đã gặp vấn đề với chức năng này. Vui lòng liên hệ hỗ trợ! (1)", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            Log.e("e", "clickBtnGhimRowCto: " + e.getMessage());
        }
    }

    @Override
    public void clickBtnChiTiet(int pos) {
        if (pos >= mListCto.size())
            return;

        try {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.rl_main);
            if (fragment instanceof MainFragment) {
                ((MainFragment) fragment).clickBtnChiTiet(pos);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Rất tiếc đã gặp vấn đề với chức năng này. Vui lòng liên hệ hỗ trợ!(2)", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            Log.e("e", "clickBtnGhimRowCto: " + e.getMessage());
        }
    }

    @Override
    public void clickBtnXoa(final int pos, final boolean isDsCtoGhim) {
        if (pos >= mListCto.size())
            return;

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Xóa dữ liệu công tơ")
                .setMessage("Bạn có chắc muốn xóa công tơ này?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        mSqlDAO.deleteCongTo(mListCto.get(pos).getmIdCto());
                        mListCto.clear();
                        mListCto = (isDsCtoGhim) ? mSqlDAO.getAllCongToGhim() : mSqlDAO.getAllCongTo();

                        try {
                            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.rl_main);
                            if (fragment instanceof MainFragment) {
                                ((MainFragment) fragment).clickBtnXoa(pos, isDsCtoGhim);
                            }
                        } catch (final Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Rất tiếc đã gặp vấn đề với chức năng này. Vui lòng liên hệ hỗ trợ! (3)", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                    Log.e("e", "clickBtnGhimRowCto: " + e.getMessage());
                                }
                            });

                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    //endregion

    //region OnFragmentInteractionListener
    @Override
    public List<CongToProxy> interactionDataCongTo(
//            Common.KIEU_DANHSACH kieuDanhsach
    ) {
        mListCto.clear();
            mListCto = mSqlDAO.getAllCongTo(mKieuCongTo);
            return mListCto;

//        if(kieuDanhsach == Common.KIEU_DANHSACH.ALL) {
//            mListCto.clear();
//            mListCto = mSqlDAO.getAllCongTo(mKieuCongTo);
//            return mListCto;
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
        return mSqlDAO.selectDienLuc(maDienLuc);
    }

    @Override
    public void interactionDataCongToSearch(CharSequence charSearch, boolean isDsCtoGhim) {
        if (TextUtils.isEmpty(charSearch))
            return;

        try {
            mListCto.clear();
            mListCto = (isDsCtoGhim) ? mSqlDAO.getAllCongToGhim() : mSqlDAO.getAllCongTo();

            List<CongToProxy> data = new ArrayList<>();
            String query = Common.removeAccent(charSearch.toString().trim().toLowerCase());
            for (CongToProxy congToProxy : mListCto) {
                if (Common.removeAccent(congToProxy.getmMaCto().toLowerCase()).contains(query)
                        || Common.removeAccent(congToProxy.getmMaCto().toLowerCase()).contains(query)) {
                    data.add(congToProxy);
                }
            }

            mListCto.clear();
            mListCto.addAll(data);

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

        mListCtoGhim.clear();
        mListCtoGhim = mSqlDAO.getAllCongToGhim(mKieuCongTo);
        return mListCtoGhim;
    }
    //endregion

    private List<CongTo> dumpData() {
        List<CongTo> result = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            CongTo congTo = new CongTo();
            congTo.setmMaDLuc("PD" + i + 1);
            congTo.setmChiSoThao("" + i + 1);
            congTo.setmMaChungLoai("China");
            congTo.setmMaCto("Z" + i + 1);
            congTo.setmNamSX("2015");
            congTo.setmSoCto("QZ" + i + 1);
            congTo.setmTaiKhoan(MainActivity.sTaiKhoan);
            congTo.setmNgayGhiDuLieu(Common.getDateTimeNow(Common.DATE_TIME_TYPE.FULL));
            congTo.setmGhimCto(0);
            congTo.setmKieuCongTo(i%2);
            result.add(congTo);
        }
        return result;
    }

    public enum TAG_FRAG {
        DANHSACH, CHITIET
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_bluetooth:
                    Toast.makeText(MainActivity.this, "Quét bluetooth", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.fab_camera:
                    Toast.makeText(MainActivity.this, "Quét camera", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
