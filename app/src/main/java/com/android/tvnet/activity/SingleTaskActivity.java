package com.android.tvnet.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.tvnet.R;
import com.android.tvnet.models.Task;
import com.android.tvnet.util.PhoneUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.tvnet.key.ConsKey.TASK_DATA;

public class SingleTaskActivity extends AppCompatActivity implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {


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
                callPermissions();
                break;
        }
    }
    static final int REQUEST_CALL = 1;

    private void callPermissions() {
        boolean hasPermissionCall = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED);

        if (!hasPermissionCall) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            phoneCall();
        }

    }

    private void phoneCall() {
        PhoneUtil.getInstance().phoneCall(this,task.getCustomer().getPhoneNumber());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.e("PermissionCall", "PermissionCall");

        if (requestCode == REQUEST_CALL) {
            if (grantResults.length != 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
              Log.e("Permishon","Don't open call Permissions");
            } else {
               phoneCall();


            }
        }
    }


}
