package kireev.ftshw.project.Profile.MVP;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.Objects;

import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.R;

public class ProfileViewFragment extends MvpFragment<ProfileView, ProfilePresenter> implements ProfileView {

    ImageView avatar, avatarTop;
    EditText name, surname, patronymic;
    Button refreshButton;
    CardView cardView;
    ConstraintLayout constraintLayout;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) Objects.requireNonNull(getActivity()))
                .setActionBarTitle(getString(R.string.title_profile));
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        name = v.findViewById(R.id.textName);
        surname = v.findViewById(R.id.textSurname);
        patronymic = v.findViewById(R.id.textPatronymic);
        cardView = v.findViewById(R.id.cvPoints);
        cardView.setVisibility(View.INVISIBLE);
        constraintLayout = v.findViewById(R.id.clAvatar);
        constraintLayout.setVisibility(View.INVISIBLE);
        avatar = v.findViewById(R.id.ivAvatar);
        avatarTop = v.findViewById(R.id.ivAvatarTop);
        refreshButton = v.findViewById(R.id.btnRefresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.refresh();
            }
        });
        Log.e("Profile onCreateView", "view attached");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.viewIsReady();
    }

    @Override
    public void showProfile(ProfileData.User user, String status) {
        cardView.setVisibility(View.VISIBLE);
        constraintLayout.setVisibility(View.VISIBLE);
        name.setText(user.getFirstName());
        surname.setText(user.getLastName());
        patronymic.setText(user.getMiddleName());
        if (isAdded()) {
            Glide.with(Objects.requireNonNull(this))
                    .load("https://fintech.tinkoff.ru" + user.getAvatar())
                    .apply(RequestOptions.circleCropTransform())
                    .into(avatar);
            Glide.with(Objects.requireNonNull(this))
                    .load("https://fintech.tinkoff.ru" + user.getAvatar())
                    .apply(RequestOptions.centerCropTransform())
                    .into(avatarTop);
            Toast.makeText(getContext(), "Status: " + status, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError(String message) {
        Log.e("getUserData onFailure", "ooops!");
        Toast.makeText(getContext(), "getUserData went wrong!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        MenuItem item = menu.findItem(R.id.logout);
        item.setVisible(true);
    }

    @NonNull
    @Override
    public ProfilePresenter createPresenter() {
        ProfileModel profileModel = new ProfileModel();
        presenter = new ProfilePresenter(profileModel);
        return presenter;
    }
}
