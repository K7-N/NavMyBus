package com.example.nmbapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    Context context;
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Context context) {
        super(fragmentManager, lifecycle);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new SignUpTabFragment(context);
        }
        return new SignInTabFragment(context);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
