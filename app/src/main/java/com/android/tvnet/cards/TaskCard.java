package com.android.tvnet.cards;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.tvnet.R;
import com.android.tvnet.models.Task;
import com.android.tvnet.util.AbstractCard;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskCard extends AbstractCard {
    @BindView(R.id.task_card)
    LinearLayout taskCard;
    @BindView(R.id.user_name)
    AppCompatTextView userName;
    @BindView(R.id.user_address)
    AppCompatTextView userAddress;
    @BindView(R.id.user_short_description)
    AppCompatTextView userShortDescription;
    @BindView(R.id.user_start_date)
    AppCompatTextView userStartDate;
    @BindView(R.id.important_progress)
    ProgressBar importantProgress;

    private Context context;

    public TaskCard(Context context, ViewGroup parent) {
        this(context, LayoutInflater.from(context).inflate(R.layout.card_task, parent, false));
        this.context = context;
    }

    private TaskCard(Context context, View view) {
        super(view, context);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bind(Object data) {
        Task task = (Task) data;
        if (task != null) {
            userName.setText(task.getCustomer().getName());
            userAddress.setText(task.getCustomer().getAddress());
            userShortDescription.setText(task.getShortDescription());
            userStartDate.setText(task.getStartDate());
            importantProgress.setProgress(task.getImportant());

        }
        taskCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardClick(getAdapterPosition());
            }
        });

    }


}