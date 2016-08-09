package com.example.nikhil.materialtester;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NavigationDrawerFragment extends Fragment implements VivzAdapter.ClickListener{


    private RecyclerView recyclerView;
    private VivzAdapter adapter;

    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER ="user_learned_drawer";

    private ActionBarDrawerToggle mdrawerToggle;
    private DrawerLayout mDrawerlayout;
    private View containerView;

    public boolean mUserLearnedDrawer;
    private boolean mFromSavedInstaneState;


    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER,"false" ));
        if(savedInstanceState !=null){
            mFromSavedInstaneState=true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler);
        adapter = new VivzAdapter(getActivity(),getData());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int[] icon = { R.drawable.cupcake,
                R.drawable.donut,
                R.drawable.eclair,
                R.drawable.froyo,
                R.drawable.gingerbread,
                R.drawable.cupcake,
                R.drawable.donut,
                R.drawable.eclair,
                R.drawable.froyo,
                R.drawable.gingerbread,
                R.drawable.cupcake,
                R.drawable.donut,
                R.drawable.eclair,
                R.drawable.froyo,
                R.drawable.gingerbread,

        };
        String[] titles = { "donut",
                "eclair",
                "froyo",
                "gingerbread",
                "donut",
                "eclair",
                "froyo",
                "gingerbread",
                "donut",
                "eclair",
                "froyo",
                "gingerbread"
        };

        for (int i = 0; i< titles.length;i++){
            Information current = new Information();
            current.name = titles[i];
            current.iconid = icon[i];
            data.add(current);
        }
        return data;
    }

    public void setUp(int fragmentid,DrawerLayout drawerLayout, android.support.v7.widget.Toolbar toolbar) {
        containerView=getActivity().findViewById(fragmentid);
        mDrawerlayout = drawerLayout;
        mdrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                    if(!mUserLearnedDrawer){
                        mUserLearnedDrawer=true;
                        saveToPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,mUserLearnedDrawer+" ");
                    }
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }
        };
        if(!mUserLearnedDrawer && !mFromSavedInstaneState){
            mDrawerlayout.openDrawer(containerView);

        }
        mDrawerlayout.setDrawerListener(mdrawerToggle);
        mDrawerlayout.post(new Runnable() {
            @Override
            public void run() {
                mdrawerToggle.syncState();

            }
        });
    }


    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.apply();
    }
    public static String readFromPreferences(Context context,String PreferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceName,defaultValue);
    }

    @Override
    public void itemclick(View v, int position) {
        startActivity(new Intent(getActivity(),SubActivity.class));
    }
}
