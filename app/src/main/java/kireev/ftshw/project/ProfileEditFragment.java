package kireev.ftshw.project;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ProfileEditFragment extends Fragment {

    static final String STUDENT_NAME = "Name";
    static final String STUDENT_SURNAME = "Surname";
    static final String STUDENT_PATRONYMIC = "Patronymic";
    private ProfileEditFragment.OnProfileEditFragmentListener listener;
    Button saveButton, cancelButton;
    static EditText name, surname, patronymic;
    static SharedPreferences sPref;

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
                .setActionBarTitle(getString(R.string.title_edit_profile));
        View v = inflater.inflate(R.layout.fragment_profile_edit, container, false);

        name = v.findViewById(R.id.editTextName);
        surname = v.findViewById(R.id.editTextSurname);
        patronymic = v.findViewById(R.id.editTextPatronymic);

        name.setText(sPref.getString(STUDENT_NAME,""));
        surname.setText(sPref.getString(STUDENT_SURNAME,""));
        patronymic.setText(sPref.getString(STUDENT_PATRONYMIC,""));

        saveButton = v.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((name.getText().equals("")) | (surname.getText().equals("")) | (patronymic.getText().equals(""))){
                    Toast.makeText((MainActivity) getContext(),"Поля ФИО не должны быть пустыми",Toast.LENGTH_LONG);
                } else {
                    saveText();
                }
                getActivity().getSupportFragmentManager().popBackStack();
                /*getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment())
                        .commit();*/
            }
        });

        cancelButton = v.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity) getActivity())
                        .ad.show();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //listener = (ProfileEditFragment.OnProfileEditFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public interface OnProfileEditFragmentListener {
        void onOpenProfile();
    }

    void saveText() {
        sPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(STUDENT_NAME, name.getText().toString());
        ed.putString(STUDENT_SURNAME, surname.getText().toString());
        ed.putString(STUDENT_PATRONYMIC, patronymic.getText().toString());
        ed.commit();
        Log.d("saveText", "name " + name.getText().toString() + ", surname " + surname.getText().toString() + ", patronymic " + patronymic.getText().toString() + " are saved SharedPreferences!");
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
