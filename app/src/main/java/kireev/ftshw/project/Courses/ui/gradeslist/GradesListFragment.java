package kireev.ftshw.project.Courses.ui.gradeslist;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kireev.ftshw.project.R;


public class GradesListFragment extends Fragment {


    public static RecyclerView rvGrades, rv;
    public static AllContactsAdapter contactAdapter;
    ContactVO contactVO;


    public static GradesListFragment newInstance() {
        return new GradesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return showContacts();
    }

    private RecyclerView showContacts() {
        rvGrades = new RecyclerView(getContext());
        rvGrades.findViewById(R.id.gradeslist);
        List<ContactVO> contactVOList = getAllContacts();
        contactAdapter = new AllContactsAdapter(contactVOList);
        rvGrades.setAdapter(contactAdapter);
        rvGrades.setLayoutManager(new LinearLayoutManager(getContext()));
        return rvGrades;
    }

    private List<ContactVO> getAllContacts() {
        final ArrayList<ContactVO> contactVOList = new ArrayList<>();
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {

                // получаем каждый контакт
                String contact = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contactVO = new ContactVO();
                contactVO.setContactName(name);
                // добавляем контакт в список
                contactVOList.add(contactVO);
            }
            cursor.close();
        }
        return contactVOList;
    }

    private List<ContactVO> initList() {
        final ArrayList<ContactVO> contactVOList = new ArrayList<>();
        contactVOList.add(new ContactVO("Krista Smartt"));
        contactVOList.add(new ContactVO("Keith Wint"));
        contactVOList.add(new ContactVO("Lizette Digennaro"));
        contactVOList.add(new ContactVO("Ma Ketcham"));
        contactVOList.add(new ContactVO("Sybil Schwartzman"));
        contactVOList.add(new ContactVO("Gary Nowland"));
        contactVOList.add(new ContactVO("Bettie People"));
        contactVOList.add(new ContactVO("Joan Fudge"));
        contactVOList.add(new ContactVO("Cathern Canfield"));
        contactVOList.add(new ContactVO("Samira Hammonds"));
        contactVOList.add(new ContactVO("Broderick Rafael"));
        contactVOList.add(new ContactVO("Monty Laconte"));
        contactVOList.add(new ContactVO("Richelle You"));
        contactVOList.add(new ContactVO("Cedric Link"));
        contactVOList.add(new ContactVO("Colette Surprenant"));
        contactVOList.add(new ContactVO("Filomena Mclelland"));
        contactVOList.add(new ContactVO("Fumiko Bylsma"));
        contactVOList.add(new ContactVO("Another Krista Smartt"));
        contactVOList.add(new ContactVO("Another Keith Wint"));
        contactVOList.add(new ContactVO("Another Lizette Digennaro"));
        contactVOList.add(new ContactVO("Another Ma Ketcham"));
        contactVOList.add(new ContactVO("Another Sybil Schwartzman"));
        contactVOList.add(new ContactVO("Another Gary Nowland"));
        contactVOList.add(new ContactVO("Another Bettie People"));
        contactVOList.add(new ContactVO("Another Joan Fudge"));
        contactVOList.add(new ContactVO("Another Cathern Canfield"));
        contactVOList.add(new ContactVO("Another Samira Hammonds"));
        contactVOList.add(new ContactVO("Another Broderick Rafael"));
        contactVOList.add(new ContactVO("Another Monty Laconte"));
        contactVOList.add(new ContactVO("Another Richelle You"));
        contactVOList.add(new ContactVO("Another Cedric Link"));
        contactVOList.add(new ContactVO("Another Colette Surprenant"));
        contactVOList.add(new ContactVO("Another Filomena Mclelland"));
        contactVOList.add(new ContactVO("Another Fumiko Bylsma"));
        contactVOList.add(new ContactVO("Another Krista Smartt"));
        contactVOList.add(new ContactVO("Another Keith Wint"));
        contactVOList.add(new ContactVO("Another Lizette Digennaro"));
        contactVOList.add(new ContactVO("Another Ma Ketcham"));
        contactVOList.add(new ContactVO("Another Sybil Schwartzman"));
        contactVOList.add(new ContactVO("Another Gary Nowland"));
        contactVOList.add(new ContactVO("Another Bettie People"));
        contactVOList.add(new ContactVO("Another Joan Fudge"));
        contactVOList.add(new ContactVO("Another Cathern Canfield"));
        contactVOList.add(new ContactVO("Another Samira Hammonds"));
        contactVOList.add(new ContactVO("Another Broderick Rafael"));
        contactVOList.add(new ContactVO("Another Monty Laconte"));
        contactVOList.add(new ContactVO("Another Richelle You"));
        contactVOList.add(new ContactVO("Another Cedric Link"));
        contactVOList.add(new ContactVO("Another Colette Surprenant"));
        contactVOList.add(new ContactVO("Another Filomena Mclelland"));
        contactVOList.add(new ContactVO("Another Fumiko Bylsma"));
        return contactVOList;
    }

}