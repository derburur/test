package org.androidtown.myfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {


    PlaceholderFragment fragment1;
    MenuFragment fragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 = new  PlaceholderFragment();
        fragment2 = new MenuFragment();
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment1)
                    .commit();
        }
    }

    public void onButtonClicked(View v){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, fragment2).commit();
    }

    public static class PlacehoderFragment extends Fragment {
        public PlacehoderFragment(){

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
           View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
