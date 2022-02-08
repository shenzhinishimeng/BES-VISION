package com.catail.bes_vision.function.project.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.catail.bes_vision.R;
import com.catail.bes_vision.base.BaseFragment;
import com.catail.bes_vision.utils.Logger;

import java.util.ArrayList;
import java.util.List;


public class ResourcesHomePagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<BaseFragment> contentList;
    private final List<String> titleList;

    public ResourcesHomePagerAdapter(FragmentManager fm, ArrayList<BaseFragment> contentList1, List<String> titleList1) {
        super(fm);
        this.contentList = contentList1;
        this.titleList = titleList1;
        Logger.e("titleList==",titleList.toString());
    }

    @Override
    public Fragment getItem(int position) {
        return contentList.get(position);
    }

    @Override
    public int getCount() {
        return contentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
