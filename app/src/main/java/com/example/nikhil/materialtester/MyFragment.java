package com.example.nikhil.materialtester;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nikhil on 12-08-2016.
 */
public class MyFragment extends Fragment {

    private TextView textView;

    public static MyFragment getInstance(int position) {
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragment.setArguments(args);
        return myFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_my, container, false);
        textView = (TextView) layout.findViewById(R.id.text_my);
        Bundle bundle = getArguments();
        if (bundle != null) {
            textView.setText("The page currently selected is " + bundle.getInt("position"));

        }
        return layout;
    }
}