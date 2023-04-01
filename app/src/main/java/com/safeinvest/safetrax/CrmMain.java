package com.safeinvest.safetrax;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class CrmMain extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyCrmPageAdapter myCrmPageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_crm_main, container, false);
        tabLayout=(TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.setTabRippleColorResource(R.color.blue);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.golden));
        viewPager2=(ViewPager2) view.findViewById(R.id.view_pager);
        myCrmPageAdapter=new MyCrmPageAdapter(this);
        viewPager2.setAdapter(myCrmPageAdapter);
        viewPager2.setOffscreenPageLimit(1);
        //viewPager2.setUserInputEnabled(false);

     tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
         @Override
         public void onTabSelected(TabLayout.Tab tab) {
             viewPager2.setCurrentItem(tab.getPosition());
         }

         @Override
         public void onTabUnselected(TabLayout.Tab tab) {

         }

         @Override
         public void onTabReselected(TabLayout.Tab tab) {

         }
     });


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        return view;
    }
}