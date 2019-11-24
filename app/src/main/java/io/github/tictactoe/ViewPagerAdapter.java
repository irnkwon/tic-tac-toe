/*
    ViewPagerAdapter.java

    Created by Irene Kwon
    Last Modified at Nov 23, 2019
*/

package io.github.tictactoe;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> fragList = new ArrayList<>();
    private final ArrayList<String> fragTitle = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        return fragList.get(pos);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }

    public void addFragment(Fragment frag, String title) {
        fragList.add(frag);
        fragTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int pos) {
        return fragTitle.get(pos);
    }

}
