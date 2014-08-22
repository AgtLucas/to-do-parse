package com.awlka.todo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class AlphaSample extends Activity {

    private EditText mTaskInput;
    private ListView mListView;
    private TaskAdapter mAdapter;

    public String APP_ID = "APP_ID";
    public String CLIENT_ID = "CLIENT_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);

        Parse.initialize(this, APP_ID, CLIENT_ID);
        ParseAnalytics.trackAppOpened(getIntent());
        ParseObject.registerSubclass(Task.class);

        mAdapter = new TaskAdapter(this, new ArrayList<Task>());

        mTaskInput = (EditText) findViewById(R.id.task_input);
        mListView = (ListView) findViewById(R.id.task_list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.alpha, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createTask(View v) {
        if (mTaskInput.getText().length() > 0) {
            Task t = new Task();
            t.setDescription(mTaskInput.getText().toString());
            t.setCompleted(false);
            t.saveEventually();
            mAdapter.insert(t, 0);
            mTaskInput.setText("");
        }
    }

    public void updateData() {
        ParseQuery<Task> query = ParseQuery.getQuery(Task.class);
        query.findInBackground(new FindCallback<Task>() {
            @Override
            public void done(List<Task> tasks, ParseException e) {
                if (tasks != null) {
                    mAdapter.clear();
                    mAdapter.addAll(tasks);
                }
            }
        });
    }
}
