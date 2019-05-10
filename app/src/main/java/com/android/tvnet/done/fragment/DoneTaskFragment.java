package com.android.tvnet.done.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.android.tvnet.R;
import com.android.tvnet.activity.MainActivity;
import com.android.tvnet.activity.SingleTaskActivity;
import com.android.tvnet.done.adapter.TaskAdapter;
import com.android.tvnet.listeners.IReloadListener;
import com.android.tvnet.listeners.ITaskListener;
import com.android.tvnet.models.Customer;
import com.android.tvnet.models.Task;
import com.android.tvnet.util.PhoneUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.tvnet.key.ConsKey.NO_INTERNET_CARD;
import static com.android.tvnet.key.ConsKey.TASK_DATA;

public class DoneTaskFragment extends Fragment implements IReloadListener, ITaskListener {
    @BindView(R.id.done_task_recycler)
    RecyclerView recycler;


    private Context context;

    private LinearLayoutManager mLayoutManager;
    private TaskAdapter adapter;


    private List<Task> tasks = new ArrayList<>();

    public static DoneTaskFragment newInstance() {
        final DoneTaskFragment fragment = new DoneTaskFragment();
        return fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done_tasks, container, false);
        ButterKnife.bind(this, view);


        getTasks();

        mLayoutManager = new LinearLayoutManager(context);
        adapter = new TaskAdapter(context, tasks, this, this);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(adapter);

        return view;
    }


    private void getTasks() {
        //TODO Delete this function
        tasks.clear();

        List<String> comments = new ArrayList<>();
        comments.add("be careful");
        comments.add("The customer very stupid");
        if (PhoneUtil.getInstance().isInternetAvailable(context)) {


            tasks.add(new Task(new Customer("Hasmik Grigoryan", "Komistas 55/2", "077590505"), 1234, "Change lines", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "12:00 10.06.19", 5, comments));
            tasks.add(new Task(new Customer("Varduhi Mnacakanyan", "Miasnikyan 5/2", "077590505"), 12213, "Spoiled equipment", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 9.06.19", "12:00 9.06.19", 2, comments));
            tasks.add(new Task(new Customer("Karen Vardanyan", "Dexatan  100/2", "077590505"), 435345, "Change lines", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "18:00 11.06.19", "13:00 10.06.19", 1, comments));
            tasks.add(new Task(new Customer("Siranush Papikyan", "Komistas 55/2", "077590505"), 647546, "New Conection", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "14:00 10.06.18", 5, comments));
            tasks.add(new Task(new Customer("Serj Sargsyan", "Vardananc 3/5", "077590505"), 234234, "Change lines", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "16:30 6.06.19", 6, comments));
            tasks.add(new Task(new Customer("Nikol pashinyan", "Komistas 55/2", "077590505"), 54645, "New Conection", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "17:40 9.06.19", 7, comments));
            tasks.add(new Task(new Customer("Vachagan Andreasyan", "mayisi 9", "077590505"), 67907, "Change lines", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "12:00 5.06.19", 9, comments));
            tasks.add(new Task(new Customer("Armen vardapetyan", "Arshakunyac 55/2", "077590505"), 34895, "New Conection", "Description is the pattern of narrative development that aims to make vivid a place, object, character, or group. Description is one of four rhetorical modes, along with exposition, argumentation, and narration. In practice it would be difficult to write literature that drew on just one of the four basic modes.", "16:00 10.06.19", "16:00 13.06.19", 1, comments));
        } else {
            Task noInternet = new Task();
            noInternet.setViewType(NO_INTERNET_CARD);
            tasks.add(noInternet);
        }

    }


    @Override
    public void reloadData() {
        getTasks();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void cardClick(int position) {
        Intent intent = new Intent(context.getApplicationContext(), SingleTaskActivity.class);
        intent.putExtra(TASK_DATA, (Parcelable) tasks.get(position));
        startActivity(intent);

    }
}
