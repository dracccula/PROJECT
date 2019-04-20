package kireev.ftshw.project.Profile.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.R;

public class ProfileViewFragment extends Fragment implements ProfileView {

    ImageView avatar;
    static EditText name, surname, patronymic;
    Button refreshButton;

    ProfilePresenter presenter;

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
        refreshButton = v.findViewById(R.id.btnRefresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.refresh();
            }
        });
        ProfileModel profileModel = new ProfileModel();
        presenter = new ProfilePresenter(profileModel);
        presenter.attachView(this);
        Log.e("onCreateView", "view attached");
        //presenter.viewIsReady();
        return v;
    }

    @Override
    public void showProfile(ProfileData.User user, String status) {
        name.setText(user.getFirstName());
        surname.setText(user.getLastName());
        patronymic.setText(user.getMiddleName());
        Glide.with(getContext())
                .load("https://fintech.tinkoff.ru" + user.getAvatar())
                .apply(RequestOptions.circleCropTransform())
                .into(avatar);
        Toast.makeText(getContext(), "Status: " + status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Log.e("getUserData onFailure", "ooops!");
        Toast.makeText(getContext(), "getUserData went wrong!", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        presenter.refresh();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        presenter.refresh();
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        Log.e("onDestroyView", "view detached");
    }
}
