package kireev.ftshw.project.Courses.Rating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import kireev.ftshw.project.Profile.AnonimProfileFragment;
import kireev.ftshw.project.R;

import static kireev.ftshw.project.MainActivity.spStorage;

public class RatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        setTitle(getString(R.string.title_rating));
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                new RatingFragment()).commitNow();
//        if (spStorage.getBoolean("IS_AUTORIZED", false)) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.container,
//                    new RatingFragment()).commitNow();
//        } else {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new AnonimProfileFragment()).commitNow();
//        }
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
