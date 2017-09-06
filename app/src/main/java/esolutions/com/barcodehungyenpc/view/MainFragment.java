package esolutions.com.barcodehungyenpc.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.model.DsCongToViewPagerAdapter;

public class MainFragment extends BaseFragment {
    private static final Object TAG = MainFragment.class.getName();
    private EditText etTimKiem;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    public MainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_danh_sach_cong_to, container, false);
        initView(rootView);
        setAction();
        return rootView;
    }

    @Override
    void initView(final View rootView) {
        etTimKiem = (EditText) rootView.findViewById(R.id.et_search_type);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar_frag_ds_cto);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        DsCongToViewPagerAdapter adapter = new DsCongToViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new DsCtoFragment(), "DANH SÁCH CÔNG TƠ");
        adapter.addFragment(new DsGhimCtoFragment(), "GHIM");
        viewPager.setAdapter(adapter);
    }

    @Override
    void setAction() {
        setupViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Toast.makeText(MainFragment.this.getActivity(), "onPageScrolled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainFragment.this.getActivity(), "onPageSelected", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Toast.makeText(MainFragment.this.getActivity(), "onPageScrollStateChanged", Toast.LENGTH_SHORT).show();
            }
        });

        etTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void clickBtnGhimRowCto(int pos) {
        //gọi fragment
        Fragment fragment = ((DsCongToViewPagerAdapter)viewPager.getAdapter()).getItem(viewPager.getCurrentItem());
        if(fragment instanceof DsCtoFragment)
        {
            ((DsCtoFragment)fragment).clickBtnGhimRowCto(pos);
        }

        if(fragment instanceof DsGhimCtoFragment)
        {
            ((DsGhimCtoFragment)fragment).clickBtnGhimRowCto(pos);
        }

//        if (pos >= mListCto.size())
//            return;

//        setRecycler(false);
    }

    public void clickBtnChiTiet(int pos) {
//        if (pos >= mListCto.size())
//            return;
//
//        showDialogChiTiet(pos);
    }

    public void clickBtnDsCtoGhim() {
        etTimKiem.setText("");
//        setRecycler(true);
    }

    public void clickBtnXoa(int pos, boolean isDsCtoGhim) {
//        if (pos >= mListCto.size())
//            return;
//        setRecycler(isDsCtoGhim);
    }

//    private void showDialogChiTiet(int pos) {
//        CongToProxy congToProxy = mListCto.get(pos);
//
//        final Dialog dialog = new Dialog(this.getActivity());
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_chi_tiet_cong_to);
//        dialog.getWindow().setLayout(android.app.ActionBar.LayoutParams.MATCH_PARENT, android.app.ActionBar.LayoutParams.MATCH_PARENT);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCanceledOnTouchOutside(false);
//
////        final ImageButton ibtnGhim = (ImageButton) dialog.findViewById(R.id.ibtn_dialog_ghim);
//        final TextView tvSoCto = (TextView) dialog.findViewById(R.id.tv_dialog_so_cto);
//        final TextView tvMaCto = (TextView) dialog.findViewById(R.id.tv_dialog_ma_cto);
//        final TextView tvMaCLoai = (TextView) dialog.findViewById(R.id.tv_dialog_ma_chung_loai);
//        final TextView tvNamSx = (TextView) dialog.findViewById(R.id.tv_dialog_nam_sx);
//        final TextView tvDienLuc = (TextView) dialog.findViewById(R.id.tv_dialog_dienluc);
//        final TextView tvChiSoThao = (TextView) dialog.findViewById(R.id.tv_dialog_chi_so_thao);
//
//        tvSoCto.setText(congToProxy.getSO_CTO());
//        tvMaCto.setText(congToProxy.getMA_CTO());
//        tvMaCLoai.setText(congToProxy.getCLOAI());
//        tvNamSx.setText(congToProxy.getNAMSX());
//        tvChiSoThao.setText(congToProxy.getCHISO_THAO());
//        String maDienLuc = congToProxy.getMaDLuc();
//        String tenDienLuc = mListener.interactionDataDienLuc(maDienLuc);
//        tvDienLuc.setText(maDienLuc + " " + tenDienLuc);
//
////        int getmGhimCto = congToProxy.getmGhimCto();
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
////            ibtnGhim.setBackground(getContext().getApplicationContext().getResources().getDrawable((getmGhimCto == 0) ? R.drawable.ic_unmark : R.drawable.ic_unmark));
////        } else
////            ibtnGhim.setBackgroundDrawable(getContext().getApplicationContext().getResources().getDrawable((getmGhimCto == 0) ? R.drawable.ic_unmark : R.drawable.ic_unmark));
//
//        dialog.show();
//    }

    public void clickBtnDsCto() {
        etTimKiem.setText("");
//        setRecycler(false);
    }

    public void search() {
//        setRecycler(isDsCtoGhim);
    }

}
