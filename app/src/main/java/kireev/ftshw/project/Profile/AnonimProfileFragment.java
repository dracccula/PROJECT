package kireev.ftshw.project.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_profile));
        return inflater.inflate(R.layout.fragment_anonim_profile, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        MenuItem item = menu.findItem(R.id.logout);
        item.setVisible(false);
    }

}
