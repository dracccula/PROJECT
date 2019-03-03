package kireev.ftshw.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static kireev.ftshw.project.ProfileEditFragment.STUDENT_NAME;
import static kireev.ftshw.project.ProfileEditFragment.STUDENT_PATRONYMIC;
import static kireev.ftshw.project.ProfileEditFragment.STUDENT_SURNAME;
import static kireev.ftshw.project.ProfileEditFragment.name;
import static kireev.ftshw.project.ProfileEditFragment.patronymic;
import static kireev.ftshw.project.ProfileEditFragment.surname;
import static kireev.ftshw.project.ProfileEditFragment.sPref;


public class ProfileFragment extends Fragment {

    Button editButton;
    static EditText name, surname, patronymic;
    private OnProfileFragmentListener listener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_profile));
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        name = v.findViewById(R.id.textName);
        surname = v.findViewById(R.id.textSurname);
        patronymic = v.findViewById(R.id.textPatronymic);
        loadText();
        editButton = v.findViewById(R.id.buttonEditFullname);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onViewClicked", "yehhh!!");
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ProfileEditFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnProfileFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadText();
    }

    public interface OnProfileFragmentListener {
        void onOpenEditProfile();
    }

    void loadText() {
        sPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        String savedName = sPref.getString(STUDENT_NAME,"");
        String savedSurname = sPref.getString(STUDENT_SURNAME,"");
        String savedPatronymic = sPref.getString(STUDENT_PATRONYMIC,"");
        name.setText(savedName);
        surname.setText(savedSurname);
        patronymic.setText(savedPatronymic);
    }
}
