package kireev.ftshw.project.Courses.Rating.Tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import kireev.ftshw.project.R;

public class TasksActivity extends AppCompatActivity {
    public static final String HOMEWORK_TITLE = "title";
    public static final int HOMEWORK_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        setTitle(getIntent().getStringExtra(HOMEWORK_TITLE));
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new TasksFragment())
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("Back", "clicked!");
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

