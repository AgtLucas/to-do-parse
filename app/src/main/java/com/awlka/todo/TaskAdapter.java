package com.awlka.todo;

import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by AgtLucas on 8/22/14.
 */
public class TaskAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private List<Task> mTasks;

    public TaskAdapter(Context context, List<Task> objects) {
        super(context, R.layout.task_row_item, objects);
        this.mContext = context;
        this.mTasks = objects;
    }
}
