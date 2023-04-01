package com.safeinvest.safetrax;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyCrmPageAdapter extends FragmentStateAdapter {
    public MyCrmPageAdapter(@NonNull CrmMain fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new CrmAdd() ;
            case 1:
                return new Crm();
            default:
                return new CrmAdd();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
