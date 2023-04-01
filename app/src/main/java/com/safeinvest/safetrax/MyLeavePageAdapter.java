package com.safeinvest.safetrax;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyLeavePageAdapter extends FragmentStateAdapter {

    public MyLeavePageAdapter(@NonNull LeaveMain fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new leave();
            case 1:
                return new LeaveList();
            default:
                return new leave();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
