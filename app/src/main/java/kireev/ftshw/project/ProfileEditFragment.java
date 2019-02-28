package kireev.ftshw.project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ProfileEditFragment extends Fragment {

    FragmentTransaction ft;
    Button saveButton;
    Button cancelButton;

    public ProfileEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setActionBarTitle("Редактирование"); // TODO: Использовать R.string.xxx
        View v = inflater.inflate(R.layout.fragment_profile_edit, null);
        saveButton = v.findViewById(R.id.buttonEditFullname);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new ProfileFragment());
                ft.commit();
            }
        });
        cancelButton = v.findViewById(R.id.buttonEditFullname);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new ProfileFragment());
                ft.commit();
            }
        });

        return inflater.inflate(R.layout.fragment_profile_edit, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
