package kireev.ftshw.project.Courses.GradesList;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class LoadContacts extends AsyncTask<Void, Void, ArrayList<ContactVO>> {
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
    }

    @Override
    protected ArrayList<ContactVO> doInBackground(Void... params) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    }

}

