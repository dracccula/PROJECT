package kireev.ftshw.project.Courses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kireev.ftshw.project.Courses.ui.gradeslist.GradesListFragment;
import kireev.ftshw.project.R;

public class GradesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_list_activity);
        setTitle(getString(R.string.title_grades_list));
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, GradesListFragment.newInstance())
                    .commitNow();
        }
        //setContentView(R.layout.grades_list_fragment);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
