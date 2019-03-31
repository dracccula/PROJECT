package kireev.ftshw.project.Courses.GradesList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kireev.ftshw.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GradesNoContactsAccessFragment extends Fragment {


    public GradesNoContactsAccessFragment() {
        // Required empty public constructor
    }

    public static GradesNoContactsAccessFragment newInstance() {
        return new GradesNoContactsAccessFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.grades_no_contacts_access_fragment, container, false);
    }

}
