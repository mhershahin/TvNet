package com.android.tvnet.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.tvnet.listeners.IReloadListener;
import com.android.tvnet.listeners.ITaskListener;

public abstract class AbstractCard extends RecyclerView.ViewHolder {

    private Context context;
    public AbstractCard(View itemView) {
        super(itemView);
    }

    public AbstractCard(View cardView, Context context) {
        super(cardView);
        this.context = context;

    }
    public abstract void bind(Object data);

    private IReloadListener iReloadListener;

    public void setIReloadListener(IReloadListener iReloadListener) {
        this.iReloadListener = iReloadListener;
    }

    protected void reloadData(){
        if (iReloadListener != null) {
            iReloadListener.reloadData();
        }
    }
    private ITaskListener iTaskListener;

    public void setiTaskListener(ITaskListener iTaskListener) {
        this.iTaskListener = iTaskListener;
    }

    protected void cardClick(int poss){
        if (iTaskListener != null) {
            iTaskListener.cardClick(poss);
        }
    }

}