package esolutions.com.barcodehungyenpc.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by VinhNB on 8/8/2017.
 */

public abstract class BaseFragment extends Fragment {
    abstract void initView(final View rootView);

    abstract void setAction();
}
