package esolutions.com.barcodehungyenpc.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.view.InfoConfigFragment;
import esolutions.com.barcodehungyenpc.view.InfoUserFragment;

/**
 * Created by VinhNB on 8/9/2017.
 */

public class TabConfigLoginAdapter extends FragmentStatePagerAdapter {

    String user, pass, maDienLuc, mUrlServer;

//    public TabConfigLoginAdapter(FragmentManager fm, String user, String pass, String maDienLuc, String mUrlServer) {
//        super(fm);
//        fragmentList.add(new InfoConfigFragment().newInstance(maDienLuc, mUrlServer));
//    }
//


    public TabConfigLoginAdapter(FragmentManager fm, String user, String pass, String maDienLuc, String mUrlServer) {
        super(fm);
        this.user = user;
        this.pass = pass;
        this.maDienLuc = maDienLuc;
        this.mUrlServer = mUrlServer;
    }

    public TabConfigLoginAdapter(FragmentManager fm, String maDienLuc, String mUrlServer) {
        super(fm);
        this.user = user;
        this.pass = pass;
        this.maDienLuc = maDienLuc;
        this.mUrlServer = mUrlServer;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
//            case 0:
//                Fragment infoUser = new InfoUserFragment().newInstance(user, pass);
//                return infoUser;
            case 0:
                Fragment infoConfig = new InfoConfigFragment().newInstance(maDienLuc, mUrlServer);
                return infoConfig;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return POSITION_NONE;
    }

    public void updateDataInfoUser(String user, String pass, String urlServer, String maDienLuc) {
//        this.user = user;
//        this.pass = pass;
        this.maDienLuc = maDienLuc;
        this.mUrlServer = urlServer;
        notifyDataSetChanged();
    }
    public void updateDataInfoUser(String urlServer, String maDienLuc) {
//        this.user = user;
//        this.pass = pass;
        this.maDienLuc = maDienLuc;
        this.mUrlServer = urlServer;
        notifyDataSetChanged();
    }
}
