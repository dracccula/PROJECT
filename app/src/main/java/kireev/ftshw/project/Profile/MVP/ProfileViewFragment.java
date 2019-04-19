package kireev.ftshw.project.Profile.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.R;

public class ProfileViewFragment extends Fragment implements ProfileView {

    ImageView avatar;
    static EditText name, surname, patronymic;
    Button refresh;

    private ProfilePresenter presenter;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_profile));
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        name = v.findViewById(R.id.textName);
        surname = v.findViewById(R.id.textSurname);
        patronymic = v.findViewById(R.id.textPatronymic);
        avatar = v.findViewById(R.id.ivAvatar);
        getActivity().findViewById(R.id.btnRefresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.refresh();
            }
        });
        return v;
    }

    @Override
    public void showUser(ProfileData.User user) {

    }

    @Override
    public void showError(String message) {

    }

    public ProfileData.User getProfileData() {
        ProfileData.User profileData = new ProfileData.User();
        profileData.;
        userData.setEmail(editTextEmail.getText().toString());
        return userData;
    }

}
