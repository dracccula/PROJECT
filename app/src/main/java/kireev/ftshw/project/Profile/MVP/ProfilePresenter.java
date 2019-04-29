package kireev.ftshw.project.Profile.MVP;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter extends MvpBasePresenter<ProfileView> {


    private final ProfileModel model;

    ProfilePresenter(ProfileModel model) {
        this.model = model;
    }

    void refresh() {
        if (getView() != null) {
            model.getUserData(new Callback<ProfileData>() {
                @Override
                public void onResponse(@NonNull Call<ProfileData> call, @NonNull Response<ProfileData> response) {
                    ProfileData profileData = response.body();
                    if (getView() != null) {
                        getView().showProfile(profileData.getUser(), profileData.getStatus());
                    }
                }

                @Override
                public void onFailure(Call<ProfileData> call, Throwable t) {
                    getView().showError(t.getMessage());
                }
            });
        }

    }

    void viewIsReady() {
        refresh();
    }
}
