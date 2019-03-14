package kireev.ftshw.project.Courses.ui.gradeslist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kireev.ftshw.project.R;


public class GradesListFragment extends Fragment {

    public static RecyclerView rvGrades;
    public static AllContactsAdapter contactAdapter;
    RecyclerView.LayoutManager mLayoutManger;

    public enum Layout {
        LIST,
        GRID
    }

    public static GradesListFragment newInstance() {
        return new GradesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rvGrades = new RecyclerView(getContext());
        rvGrades.findViewById(R.id.gradeslist);
        List<ContactVO> contactVOList = initList();
        contactAdapter = new AllContactsAdapter(contactVOList);
        rvGrades.setAdapter(contactAdapter);
        rvGrades.setLayoutManager(new LinearLayoutManager(getContext()));
        //GradesListFragment.contactAdapter.onCreateViewHolder(GradesListFragment.rvGrades,1);
        return rvGrades;
    }

    public void getAllContacts() {


//        rvGrades.setLayoutManager(mLayoutManger);
//        contactAdapter.onCreateViewHolder(rvGrades,0);


        /*
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    contactVO = new ContactVO();
                    contactVO.setContactName(name);

                    Cursor emailCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (emailCursor.moveToNext()) {
                        String emailId = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    }
                    contactVOList.add(contactVO);
                }
            }

            AllContactsAdapter contactAdapter = new AllContactsAdapter(contactVOList, getContext());
            rvGrades.setLayoutManager(new LinearLayoutManager(GradesListActivity.this));
            rvGrades.setAdapter(contactAdapter);
        }*/
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