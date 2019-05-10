package com.android.tvnet.cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.tvnet.R;
import com.android.tvnet.util.AbstractCard;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoInternetCard extends AbstractCard {
    @BindView(R.id.liner_reload)
    LinearLayout linerReload;
    private Context context;

    public NoInternetCard(Context context, ViewGroup parent) {
        this(context, LayoutInflater.from(context).inflate(R.layout.card_no_internet, parent, false));
        this.context = context;
    }

    private NoInternetCard(Context context, View view) {
        super(view, context);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bind(Object data) {
        linerReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadData();
            }
        });
    }


}
