package esolutions.com.barcodehungyenpc.view;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

import esolutions.com.barcodehungyenpc.R;

public class InfoConfigFragment extends BaseFragment {
    private EditText mEtUrlServer, mEtMaDienLuc;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String maDienLuc;
    private String mUrlServer;
    private OnInfoConfigFragmentInteractionListener mListener;


    public InfoConfigFragment() {
        // Required empty public constructor
    }

    public static InfoConfigFragment newInstance(String maDienLuc, String urlServer) {
        InfoConfigFragment fragment = new InfoConfigFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, maDienLuc);
        args.putString(ARG_PARAM2, urlServer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            maDienLuc = getArguments().getString(ARG_PARAM1);
            mUrlServer = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInfoConfigFragmentInteractionListener) {
            mListener = (OnInfoConfigFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInfoConfigFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_info_config, container, false);
        initView(root);
        setAction();
        return root;
    }

    public void setErrorUrlServer(String errorString) {
        if (StringUtils.isEmpty(errorString))
            return;
        mEtUrlServer.setFocusable(true);
        mEtUrlServer.setError(errorString);
        mEtUrlServer.requestFocus();
    }

    public void setErrorMaDienLuc(String errorString) {
        if (StringUtils.isEmpty(errorString))
            return;
        mEtMaDienLuc.setFocusable(true);
        mEtMaDienLuc.setError(errorString);
        mEtMaDienLuc.requestFocus();
    }

    public void setTextMaDienLuc(String maDienLuc) {
        if (StringUtils.isEmpty(maDienLuc))
            return;
        mEtMaDienLuc.setText(maDienLuc);
    }

    public void setTextUrlServer(String url) {
        if (StringUtils.isEmpty(url))
            return;
        mEtUrlServer.setText(url);
    }

    @Override
    void initView(final View rootView) {
        mEtUrlServer = (EditText) rootView.findViewById(R.id.et_url_server);
        mEtMaDienLuc = (EditText) rootView.findViewById(R.id.et_dien_luc);
    }

    @Override
    void setAction() {
        //add sự kiện thay đổi text
        mEtUrlServer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mListener.updateInfoURLServer(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mEtMaDienLuc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mListener.updateInfoCodeDienLuc(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mEtMaDienLuc.setText(maDienLuc);
        mEtUrlServer.setText(mUrlServer);
        mListener.updateInfoURLServer(mEtUrlServer.getText().toString());
        mListener.updateInfoCodeDienLuc(mEtMaDienLuc.getText().toString());
    }

    public interface OnInfoConfigFragmentInteractionListener {
        void updateInfoURLServer(CharSequence charSequence);

        void updateInfoCodeDienLuc(CharSequence charSequence);
    }
}
