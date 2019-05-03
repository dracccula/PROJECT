package kireev.ftshw.project.Profile.MVP;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

import kireev.ftshw.project.Database.Entity.Profile;

public interface ProfileView extends MvpView {

    void showProfileFromResponse(ProfileData.User user, String status);

    void showProfileFromDB(List<Profile> profileList);

    void showError(String message);
}
