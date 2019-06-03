package kireev.ftshw.project.Courses.GradesList;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
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
    ContactVO contactVO;
    LoadContacts lc;
    GradesListActivity context;
    ProgressDialog pd;
    List<ContactVO> contactVOList;


    public static GradesListFragment newInstance() {
        return new GradesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.grades_list_fragment, container, false);
        rvGrades = new RecyclerView(getContext());
        rvGrades.findViewById(R.id.gradeslist);
        return rvGrades;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (contactVOList == null){
            lc = new LoadContacts();
            lc.subscribe(this);
            lc.execute();
        }
    }

    @Override
    public void onDestroyView() {
        lc.unsubscribe();
        super.onDestroyView();
    }


    public void showProgress() {
        pd = new ProgressDialog(getActivity());
        pd.setTitle("Загрузка контактов");
        pd.setMessage("Ожидайте..");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
    }

    public void hideProgress() {
        pd.dismiss();
    }

    public void showList(ArrayList<ContactVO> contacts) {
        contactVOList = contacts;
        contactAdapter = new AllContactsAdapter(contactVOList);
        rvGrades.setAdapter(contactAdapter);
        if (!GradesListActivity.mGridMode){
            rvGrades.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            GradesListFragment.rvGrades.setLayoutManager(new GridLayoutManager(getContext(), 3));
        }
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