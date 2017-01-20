package org.androidtown.myfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

;

/**
 * Created by PB on 2016-09-24.
 */
public class MenuFragment extends Fragment {
    public MenuFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_manu, container, false);
        return  rootview;
    }
}
