package kireev.ftshw.project.Courses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import kireev.ftshw.project.Courses.ui.gradeslist.AllContactsAdapter;
import kireev.ftshw.project.Courses.ui.gradeslist.GradesListFragment;
import kireev.ftshw.project.R;


public class GradesListActivity extends AppCompatActivity {

    public static boolean mGridMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_list_activity);
        setTitle(getString(R.string.title_grades_list));
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, GradesListFragment.newInstance())
//                    .commitNow();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grades_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.change_rv_layout) {
            if (item.getTitle().equals("Сетка")) {
                item.setTitle("Список");
            }
            else {
                item.setTitle("Сетка");
            }
        }

        if (item.getItemId() == android.R.id.home) {
            Log.d("Back","clicked!");
            finish();
        }

        if (item.getItemId() == R.id.change_rv_layout){
            if (mGridMode == false) {
                GradesListFragment.rvGrades.setLayoutManager(new GridLayoutManager(this,3));
                Log.d("GridMode true","Layout changed to GRID");
                mGridMode = true;
            } else {
                GradesListFragment.rvGrades.setLayoutManager(new LinearLayoutManager(this));
                Log.d("GridMode true","Layout changed to LIST");
                mGridMode = false;
            }

        }
        return true;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        finish();
//        return true;
//    }
}
