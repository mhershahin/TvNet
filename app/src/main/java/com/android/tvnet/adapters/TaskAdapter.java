package com.android.tvnet.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.android.tvnet.cards.NoInternetCard;
import com.android.tvnet.cards.TaskCard;
import com.android.tvnet.listeners.IReloadListener;
import com.android.tvnet.listeners.ITaskListener;
import com.android.tvnet.models.Task;
import com.android.tvnet.util.AbstractCard;

import java.util.List;

import static com.android.tvnet.key.ConsKey.NO_INTERNET_CARD;
import static com.android.tvnet.key.ConsKey.TASK_CARD;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Task> tasks;
    private IReloadListener iReloadListener;
    private ITaskListener iTaskListener;

    public TaskAdapter(Context context, List<Task> tasks,IReloadListener iReloadListener,ITaskListener iTaskListener) {
        this.context = context;
        this.tasks = tasks;
        this.iReloadListener = iReloadListener;
        this.iTaskListener = iTaskListener;
    }
    @Override
    public int getItemViewType(int position) {

        return tasks.get(position).getViewType();

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        AbstractCard card;
        switch (viewType) {
            case NO_INTERNET_CARD:
                card =new NoInternetCard(context,parent);
                break;
            case TASK_CARD:
                card =new TaskCard(context,parent);
                break;
            default:
                card = new TaskCard(context, parent);
                break;
        }
        return card;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Task data = tasks.get(position);
        AbstractCard card = (AbstractCard) holder;
        switch (data.getViewType()){
            case NO_INTERNET_CARD:
              card.setIReloadListener(iReloadListener);
                break;
            case TASK_CARD:
                card.setiTaskListener(iTaskListener);
                break;
        }
        card.bind(data);
    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }


}