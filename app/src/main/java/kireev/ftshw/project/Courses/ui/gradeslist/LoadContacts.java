package kireev.ftshw.project.Courses.ui.gradeslist;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.ArrayList;

import kireev.ftshw.project.Courses.GradesListActivity;


public class LoadContacts extends AsyncTask<Void, Void, ArrayList<ContactVO>> {
    ProgressDialog pd;
    GradesListActivity context;
    ContactVO contactVO;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = ProgressDialog.show(context, "Loading Contacts",
                "Please Wait");
    }

    @Override
    protected ArrayList<ContactVO> doInBackground(Void... params) {
        final ArrayList<ContactVO> contactVOList = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor c = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
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

        return contactVOList;
    }

    @Override
    protected void onPostExecute(ArrayList<ContactVO> contacts) {
        // TODO Auto-generated method stub
        super.onPostExecute(contacts);
        pd.cancel();


    }

}

