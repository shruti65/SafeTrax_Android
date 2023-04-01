package com.safeinvest.safetrax;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull Viewtask fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new viewtoday();
            case 1:
                return new viewwithintat();
            case 2:
                return new ViewTaskB1to5();
            case 3:
                return new ViewTaskB1to10();
            case 4 :
                return new viewmoretat();
            default:
                return new viewtoday();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
