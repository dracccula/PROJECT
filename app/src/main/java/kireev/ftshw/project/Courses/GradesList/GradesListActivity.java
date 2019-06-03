package kireev.ftshw.project.Courses.GradesList;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import kireev.ftshw.project.R;


public class GradesListActivity extends AppCompatActivity {

    public static boolean mGridMode;
    private static final int REQUEST_CODE_READ_CONTACTS = 1;
    private boolean READ_CONTACTS_GRANTED = false;
    LoadContacts lc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_list_activity);
        setTitle(getString(R.string.title_grades));
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mGridMode = false;
        lc = new LoadContacts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showPermissionRequest();
    }

    @Override
    protected void onDestroy() {
        lc.unsubscribe();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grades_list_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.change_rv_layout);
        item.setVisible(READ_CONTACTS_GRANTED);
        if (item.getTitle().toString().toLowerCase().contains("сетка")) {
            GradesListFragment.rvGrades.setLayoutManager(new LinearLayoutManager(this));
        } else {
            GradesListFragment.rvGrades.setLayoutManager(new GridLayoutManager(this, 3));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change_rv_layout) {
            if (item.getTitle().equals("Сетка")) {
                item.setTitle("Список");
            } else {
                item.setTitle("Сетка");
            }
        }

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        if (item.getItemId() == R.id.change_rv_layout) {
            if (!mGridMode) {
                GradesListFragment.rvGrades.setLayoutManager(new GridLayoutManager(this, 3));
                mGridMode = true;
            } else {
                GradesListFragment.rvGrades.setLayoutManager(new LinearLayoutManager(this));
                mGridMode = false;
            }

        }
        return true;
    }

    public void accessClick(View view) {
        showPermissionRequest();
    }

    public void showPermissionRequest() {
        // получаем разрешения
        int hasReadContactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        // если устройство до API 23, устанавливаем разрешение
        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            READ_CONTACTS_GRANTED = true;
        } else {
            // вызываем диалоговое окно для установки разрешений
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_READ_CONTACTS);
        }
        // если разрешение установлено, загружаем контакты
        if (READ_CONTACTS_GRANTED) {
            invalidateOptionsMenu();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, GradesListFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE_READ_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    READ_CONTACTS_GRANTED = true;
                }
        }
        if (READ_CONTACTS_GRANTED) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, GradesListFragment.newInstance())
                    .commitNow();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, GradesNoContactsAccessFragment.newInstance())
                    .commitNow();
            Toast.makeText(this, "Требуется установить разрешения", Toast.LENGTH_LONG).show();
        }
        invalidateOptionsMenu();
    }
}
