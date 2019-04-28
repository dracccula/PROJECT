package kireev.ftshw.project.Profile.MVP;

import android.support.annotation.Nullable;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter extends MvpBasePresenter<ProfileView> {

    private ProfileView view;
    private final ProfileModel model;

    ProfilePresenter(ProfileModel model) {
        this.model = model;
    }

//    void attachView(ProfileView profileView) {
//        view = profileView;
//    }
//
//    void detachView() {
//        view = null;
//    }


    public void setView(ProfileView view) {
        this.view = view;
    }

    void refresh() {
        if (view != null) {
            model.getUserData(new Callback<ProfileData>() {
                @Override
                public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                    ProfileData profileData = response.body();
                    view.showProfile(profileData.getUser(), profileData.getStatus());
                }

                @Override
                public void onFailure(Call<ProfileData> call, Throwable t) {
                    view.showError(t.getMessage());
                }
            });
        }

    }

    void viewIsReady() {
        refresh();
    }
}
