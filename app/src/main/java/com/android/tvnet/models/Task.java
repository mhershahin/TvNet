package com.android.tvnet.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import static com.android.tvnet.key.ConsKey.TASK_CARD;

public class Task implements Parcelable {
    private Customer customer;

    private int taskId;

   private String shortDescription;
   private String description;

   private String deadline;
   private String startDate;

   private int important;
   private List<String> comments = new ArrayList<>();

    public Task() {
    }

    public Task(Customer customer, int taskId, String shortDescription, String description, String deadline, String startDate, int important, List<String> comments) {
        this.customer = customer;
        this.taskId = taskId;
        this.shortDescription = shortDescription;
        this.description = description;
        this.deadline = deadline;
        this.startDate = startDate;
        this.important = important;
        this.comments = comments;
    }

    protected Task(Parcel in) {
        customer = in.readParcelable(Customer.class.getClassLoader());
        taskId = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        deadline = in.readString();
        startDate = in.readString();
        important = in.readInt();
        comments = in.createStringArrayList();
        viewType = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

private int viewType = TASK_CARD;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(customer, flags);
        dest.writeInt(taskId);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(deadline);
        dest.writeString(startDate);
        dest.writeInt(important);
        dest.writeStringList(comments);
        dest.writeInt(viewType);
    }
}
