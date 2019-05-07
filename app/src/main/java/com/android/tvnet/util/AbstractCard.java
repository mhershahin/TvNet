package com.android.tvnet.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class AbstractCard extends RecyclerView.ViewHolder {

    private Context context;
    public AbstractCard(View itemView) {
        super(itemView);
    }

    public AbstractCard(View cardView, Context context) {
        super(cardView);
        this.context = context;

    }


}