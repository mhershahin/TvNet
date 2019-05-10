package com.android.tvnet.done;

import android.app.UiAutomation;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.android.tvnet.R;
import com.android.tvnet.done.adapter.FragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoneActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.done_back)
    LinearLayout doneBack;
    @BindView(R.id.done_tab)
    TabLayout doneTab;
    @BindView(R.id.done_filter)
    LinearLayout doneFilter;
    @BindView(R.id.done_view_pager)
    ViewPager pager;
    @BindView(R.id.liner_toolbar)
    LinearLayout linerToolbar;

    private  FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        ButterKnife.bind(this);

        adapter = new FragmentAdapter(getSupportFragmentManager(),this);
        pager.setAdapter(adapter);
        doneTab.setupWithViewPager(pager, true);
        doneBack.setOnClickListener(this);
        doneFilter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.done_back:
                onBackPressed();
                break;
            case R.id.done_filter:
//TODO Open Time Filter
               break;
        }
    }
}
