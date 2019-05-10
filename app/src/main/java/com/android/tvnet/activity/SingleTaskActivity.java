package com.android.tvnet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.tvnet.R;
import com.android.tvnet.models.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.tvnet.key.ConsKey.TASK_DATA;

public class SingleTaskActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.single_back)
    LinearLayout back;
    @BindView(R.id.single_task_title)
    AppCompatTextView taskTitle;
    @BindView(R.id.single_name)
    AppCompatTextView name;
    @BindView(R.id.single_address)
    AppCompatTextView address;
    @BindView(R.id.single_phone_number)
    AppCompatTextView phoneNumber;
    @BindView(R.id.start_end_date)
    AppCompatTextView startEndDate;
    @BindView(R.id.single_description)
    AppCompatTextView description;
    @BindView(R.id.single_progress)
    ProgressBar progress;
    @BindView(R.id.single_comments)
    AppCompatTextView comments;
    @BindView(R.id.single_id)
    AppCompatTextView id;

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        ButterKnife.bind(this);

        task = getIntent().getParcelableExtra(TASK_DATA);
        if (task != null) {
            initUI();
        }
        
        back.setOnClickListener(this);
        phoneNumber.setOnClickListener(this);

    }

    private void initUI() {
        taskTitle.setText(task.getShortDescription());
        id.setText(task.getTaskId() + "");
        name.setText(task.getCustomer().getName());
        address.setText(task.getCustomer().getAddress());
        phoneNumber.setText(task.getCustomer().getPhoneNumber());
        startEndDate.setText(task.getStartDate() + " - " + task.getDeadline());
        description.setText(task.getDescription());
        progress.setProgress(task.getImportant());
        for (String s : task.getComments()) {
            comments.setText(comments.getText() + "\n*" + s);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.single_back:
                onBackPressed();
                break;
            case R.id.single_phone_number:

                break;
        }
    }


}
