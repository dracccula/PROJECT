package kireev.ftshw.project.Profile.MVP;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {

    private ProfileView view;
    private final ProfileModel model;

    public ProfilePresenter(ProfileModel model) {
        this.model = model;
    }

    public void attachView(ProfileView profileView) {
        view = profileView;
    }

    public void detachView() {
        view = null;
    }

    public void refresh() {
        model.getUserData(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                ProfileData profileData = response.body();
                view.showUser(profileData.getUser());
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }
}
