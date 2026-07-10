package com.cetmaster.app;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * AdminPagerAdapter - ViewPager2 adapter for admin panel tabs
 */
public class AdminPagerAdapter extends FragmentStateAdapter {
    private static final int PAGE_COUNT = 5;

    public AdminPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AdminUploadNotesFragment();
            case 1:
                return new AdminUploadPDFFragment();
            case 2:
                return new AdminUploadMCQFragment();
            case 3:
                return new AdminManageUsersFragment();
            case 4:
                return new AdminAnnouncementsFragment();
            default:
                return new AdminUploadNotesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }
}
