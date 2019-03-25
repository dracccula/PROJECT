package kireev.ftshw.project.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnonimProfileFragment extends Fragment {


    public AnonimProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_profile));
        return inflater.inflate(R.layout.fragment_anonim_profile, container, false);
    }

}
