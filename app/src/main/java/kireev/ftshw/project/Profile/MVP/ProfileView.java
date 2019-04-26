package kireev.ftshw.project.Profile.MVP;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface ProfileView extends MvpView {

    void showProfile(ProfileData.User user, String status);

    void showError(String message);
}
