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

import esolutions.com.barcodehungyenpc.R;

public class InfoUserFragment extends BaseFragment {
    private EditText mEtUser, mEtPass;

    private static final String ARG_PARAM1 = "mUser";
    private static final String ARG_PARAM2 = "mPass";

    // TODO: Rename and change types of parameters
    private String mUser;
    private String mPass;
    private OnInfoUserFragmentInteractionListener mListener;


    public InfoUserFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InfoUserFragment newInstance(String param1, String param2) {
        InfoUserFragment fragment = new InfoUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = getArguments().getString(ARG_PARAM1);
            mPass = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_info_user, container, false);
        initView(rootView);
        setAction();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInfoUserFragmentInteractionListener) {
            mListener = (OnInfoUserFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInfoUserFragmentInteractionListener");
        }
    }

    @Override
    void initView(final View rootView) {
        mEtUser = (EditText) rootView.findViewById(R.id.et_user);
        mEtPass = (EditText) rootView.findViewById(R.id.et_pass);
    }

    @Override
    void setAction() {
        mEtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mListener.updateInfoAccount(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mListener.updateInfoPass(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mEtUser.setText(mUser);
        mEtPass.setText(mPass);
        mListener.updateInfoAccount(mEtUser.getText().toString());
        mListener.updateInfoPass(mEtPass.getText().toString());
    }

    public void setErrorUser(String errorUser) {
        if (TextUtils.isEmpty(errorUser))
            return;
        mEtUser.setFocusable(true);
        mEtUser.setError(errorUser);
        mEtUser.requestFocus();
    }

    public void setErrorPass(String errorPass) {
        if (TextUtils.isEmpty(errorPass))
            return;
        mEtPass.setFocusable(true);
        mEtPass.setError(errorPass);
        mEtPass.requestFocus();
    }

    public void setTextUser(String user) {
        if (TextUtils.isEmpty(user))
            return;
        mEtUser.setText(user);
    }

    public void setTextPass(String pass) {
        if (TextUtils.isEmpty(pass))
            return;
        mEtPass.setText(pass);
    }

    public interface OnInfoUserFragmentInteractionListener {
        void updateInfoAccount(CharSequence charSequence);

        void updateInfoPass(CharSequence charSequence);
    }
}
