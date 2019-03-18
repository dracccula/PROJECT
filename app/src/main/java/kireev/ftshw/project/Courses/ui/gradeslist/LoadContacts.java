package kireev.ftshw.project.Courses.ui.gradeslist;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.ArrayList;

import kireev.ftshw.project.Courses.GradesListActivity;


public class LoadContacts extends AsyncTask<Void, Void, ArrayList<ContactVO>> {
    ProgressDialog pd;
    ContactVO contactVO;
    private GradesListFragment fragment;

    public void subscribe(GradesListFragment fragment) {
        this.fragment = fragment;
    }

    public void unsubscribe() {
        this.fragment = null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        fragment.showProgress();
//        pd = ProgressDialog.show(context, "Loading Contacts",
//                "Please Wait");
    }

    @Override
    protected ArrayList<ContactVO> doInBackground(Void... params) {
        final ArrayList<ContactVO> contactVOList = new ArrayList<>();
        Context context = fragment.getContext();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor c = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
                // получаем каждый контакт
                String contact = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contactVO = new ContactVO();
                contactVO.setContactName(name);
                // добавляем контакт в список
                contactVOList.add(contactVO);
            }
            c.close();
        }

        return contactVOList;
    }

    @Override
    protected void onPostExecute(ArrayList<ContactVO> contacts) {
        // TODO Auto-generated method stub
        super.onPostExecute(contacts);
        fragment.hideProgress();
        fragment.showList(contacts);
//        pd.cancel();
    }

}

