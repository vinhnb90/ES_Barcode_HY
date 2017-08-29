package esolutions.com.barcodehungyenpc.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.entity.CongToProxy;
import esolutions.com.barcodehungyenpc.model.DsCongToAdapter;
import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * A simple {@link Fragment} subclass.
 */
public class DsCtoFragment extends BaseFragment {
    private static final String TAG = DsCtoFragment.class.getName();
    private RecyclerView mRvDsCto;
    //Nhận biết ds là ghim (true) hay tất cả (false)
    private boolean isDsCtoGhim = false;
    private List<CongToProxy> mListCto = new ArrayList<>();
    private OnDsCtoFragmentInteractionListener mListener;
    private DsCongToAdapter mAdapter;

    public DsCtoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mListener = (MainActivity) context;
        } else {
            throw new ClassCastException("class must be implement OnDsCtoAdapterIteraction!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ds_cto, container, false);
        initView(rootView);
        setAction();
        return rootView;
    }

    @Override
    void initView(final View rootView) {

        mRvDsCto = (RecyclerView) rootView.findViewById(R.id.rv_ds_congto);
    }

    @Override
    void setAction() {
//        setAdapter
        setRecycler();
    }

    private void setRecycler() {
        try {
            mListCto.clear();
            mListCto.addAll(mListener.interactionDataCongTo(
//                    Common.KIEU_DANHSACH.ALL
            ));

            if (mAdapter == null) {
                mAdapter = new DsCongToAdapter(getContext(), mListCto, false);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                mRvDsCto.setLayoutManager(linearLayoutManager);
                mRvDsCto.setHasFixedSize(true);
                mRvDsCto.setAdapter(mAdapter);
                mRvDsCto.invalidate();
            } else {
                mAdapter.refresh(mListCto, false);
                mRvDsCto.invalidate();
            }

            Toast.makeText(this.getActivity(), "mListCto = " + mListCto.size(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "setRecycler: error" + e.getMessage());
        }
    }

    public void clickBtnGhimRowCto(int pos) {
        if (pos >= mListCto.size())
            return;

        setRecycler();
    }

    public interface OnDsCtoFragmentInteractionListener {
        // TODO: Update argument type and name
        List<CongToProxy> interactionDataCongTo(
//                Common.KIEU_DANHSACH kieuDanhsach
        );

        String interactionDataDienLuc(String maDienLuc);

        void interactionDataCongToSearch(CharSequence charSearch, boolean isDsCtoGhim);
    }
}
