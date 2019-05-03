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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.List;
import java.util.Objects;

import kireev.ftshw.project.Database.Entity.Profile;
import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.R;

public class ProfileViewFragment extends MvpFragment<ProfileView, ProfilePresenter> implements ProfileView {

    ImageView avatarTop;
    TextView headerName, headerEmail,
            about, aboutSignature, quotesStart, quotesEnd, workValue,
            phoneValue, emailValue, cityValue, schoolValue, schoolGraduationValue,
            universityValue, facultyValue, departmentValue, universityGraduationValue;
    CardView cardView;

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

        headerName = v.findViewById(R.id.tvHeaderName);
        headerName.bringToFront();
        headerEmail = v.findViewById(R.id.tvHeaderEmail);
        headerEmail.bringToFront();

        quotesStart = v.findViewById(R.id.tvQuotesStart);
        quotesStart.setVisibility(View.INVISIBLE);
        quotesEnd = v.findViewById(R.id.tvQuotesEnd);
        quotesEnd.setVisibility(View.INVISIBLE);
        about = v.findViewById(R.id.tvAbout);
        aboutSignature = v.findViewById(R.id.tvAboutSignature);

        phoneValue = v.findViewById(R.id.tvPhoneValue);
        emailValue = v.findViewById(R.id.tvEmailValue);
        cityValue = v.findViewById(R.id.tvCityValue);

        schoolValue = v.findViewById(R.id.tvSchoolValue);
        schoolGraduationValue = v.findViewById(R.id.tvSchoolGraduationValue);
        universityValue = v.findViewById(R.id.tvUniversityValue);
        facultyValue = v.findViewById(R.id.tvFacultyValue);
        departmentValue = v.findViewById(R.id.tvDepartmentValue);
        universityGraduationValue = v.findViewById(R.id.tvUniversityGraduationValue);
        workValue = v.findViewById(R.id.tvWorkValue);

        cardView = v.findViewById(R.id.cvPoints);
        cardView.setVisibility(View.INVISIBLE);
        avatarTop = v.findViewById(R.id.ivAvatarTop);
        Log.e("Profile onCreateView", "view attached");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.viewIsReady();
    }

    @Override
    public void showProfileFromResponse(ProfileData.User user, String status) {
        cardView.setVisibility(View.VISIBLE);
        headerName.setText(user.getFirstName() + "\n" + user.getLastName());
        headerEmail.setText(user.getEmail());

        quotesStart.setVisibility(View.VISIBLE);
        quotesEnd.setVisibility(View.VISIBLE);
        about.setText(user.getDescription());
        aboutSignature.setText(user.getFirstName() + " " + user.getLastName());

        phoneValue.setText(user.getPhoneMobile());
        emailValue.setText(user.getEmail());
        cityValue.setText(user.getRegion());

        schoolValue.setText(user.getSchool());
        schoolGraduationValue.setText(user.getSchoolGraduation() + " г.");
        universityValue.setText(user.getUniversity());
        facultyValue.setText(user.getFaculty());
        departmentValue.setText(user.getDepartment());
        universityGraduationValue.setText(user.getUniversityGraduation() + " г.");
        workValue.setText(user.getCurrentWork());

        if (isAdded()) {
            Glide.with(this)
                    .load("https://fintech.tinkoff.ru" + user.getAvatar())
                    .apply(RequestOptions.centerCropTransform())
                    .into(avatarTop);
        }
    }

    @Override
    public void showProfileFromDB(List<Profile> profileList) {
        cardView.setVisibility(View.VISIBLE);
        headerName.setText(profileList.get(0).getFirstName() + "\n" + profileList.get(0).getLastName());
        headerEmail.setText(profileList.get(0).getEmail());

        quotesStart.setVisibility(View.VISIBLE);
        quotesEnd.setVisibility(View.VISIBLE);
        about.setText(profileList.get(0).getDescription());
        aboutSignature.setText(profileList.get(0).getFirstName() + " " + profileList.get(0).getLastName());

        phoneValue.setText(profileList.get(0).getPhoneMobile());
        emailValue.setText(profileList.get(0).getEmail());
        cityValue.setText(profileList.get(0).getRegion());

        schoolValue.setText(profileList.get(0).getSchool());
        schoolGraduationValue.setText(profileList.get(0).getSchoolGraduation() + " г.");
        universityValue.setText(profileList.get(0).getUniversity());
        facultyValue.setText(profileList.get(0).getFaculty());
        departmentValue.setText(profileList.get(0).getDepartment());
        universityGraduationValue.setText(profileList.get(0).getUniversityGraduation() + " г.");
        workValue.setText(profileList.get(0).getCurrentWork());

        if (isAdded()) {
            Glide.with(this)
                    .load("https://fintech.tinkoff.ru" + profileList.get(0).getAvatar())
                    .apply(RequestOptions.centerCropTransform())
                    .into(avatarTop);
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
