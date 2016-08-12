package com.example.nikhil.materialtester;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nikhil.materialtester.network.VolleySingleton;

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

        RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET, "http://php.net", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),"RESPONSE" +response,Toast.LENGTH_LONG).show();
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"ERROR" + error,Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);


        return layout;
    }
}