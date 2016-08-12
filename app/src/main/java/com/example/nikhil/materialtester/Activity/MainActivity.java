package com.example.nikhil.materialtester.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.nikhil.materialtester.Fragment.FragmentFavourities;
import com.example.nikhil.materialtester.Fragment.FragmentPopular;
import com.example.nikhil.materialtester.Fragment.FragmentTopRated;
import com.example.nikhil.materialtester.NavigationDrawerFragment;
import com.example.nikhil.materialtester.R;
import com.example.nikhil.materialtester.tabs.SlidingTabLayout;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private SlidingTabLayout mTabs;
    private static final int MOVIE_POPULAR = 0;
    private static final int MOVIE_TOPRATED = 1;
    private static final int MOVIE_FAVOURITE =2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        mTabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mTabs.setDistributeEvenly(true);
        mTabs.setViewPager(mViewPager);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.navigate) {
            Intent intent = new Intent(this, ActivityUsingTabLibrary.class);
            startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }

    //Using Fragment state pager adapter because it will save the state and not discard it
    //If we were using the FragmentPagerAdapter the onsave instance will have never been called in the
    //Fragments
    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        int[] icons = {R.drawable.mostpopular1, R.drawable.toprated2, R.drawable.favourite2};

        String[] tabs = getResources().getStringArray(R.array.tabs);

        public MyPagerAdapter(FragmentManager fm) {

            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case MOVIE_POPULAR:
                    fragment = FragmentPopular.newInstance("","");
                    break;
                case MOVIE_TOPRATED:
                    fragment = FragmentTopRated.newInstance("", "");
                    break;
                case MOVIE_FAVOURITE:
                    fragment = FragmentFavourities.newInstance("", "");
                    break;

            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Drawable drawable = getResources().getDrawable(icons[position]);
            if (drawable != null) {
                drawable.setBounds(0, 0, 64, 64);
            }
            ImageSpan imageSpan = new ImageSpan(drawable);
            SpannableString spannableString = new SpannableString(" ");
            spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }



}
