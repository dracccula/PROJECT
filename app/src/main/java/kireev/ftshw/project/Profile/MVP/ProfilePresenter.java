package kireev.ftshw.project.Profile.MVP;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;
import java.util.Objects;

import kireev.ftshw.project.App;
import kireev.ftshw.project.Database.Dao.HomeworksDao;
import kireev.ftshw.project.Database.Dao.ProfileDao;
import kireev.ftshw.project.Database.Entity.Profile;
import kireev.ftshw.project.Database.ProjectDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter extends MvpBasePresenter<ProfileView> {


    private final ProfileModel model;
    ProjectDatabase db;

    ProfilePresenter(ProfileModel model) {
        this.model = model;
    }


    private void checkDatabase() {
        db = App.getInstance().getDatabase();
        ProfileDao profileDao = db.profileDao();
        if (profileDao.getAll().isEmpty()) {
            updateProfileData();
        } else {
            getProfileFromDb();
        }
    }

    private void getProfileFromDb() {
        db = App.getInstance().getDatabase();
        ProfileDao profileDao = db.profileDao();
    }


    private void updateProfileData(ProfileData.User user) {
        db = App.getInstance().getDatabase();
        ProfileDao profileDao = db.profileDao();
        Profile profile = new Profile(user.getId(), user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getEmail(),
                user.getBirthday(), user.getPhoneMobile(), user.getDescription(), user.getRegion(), user.getSchool(), user.getSchoolGraduation(),
                user.getUniversity(), user.getFaculty(), user.getUniversityGraduation(), user.getGrade(), user.getDepartment(),
                user.getCurrentWork(), user.getAvatar(), user.getResume(), user.getSkypeLogin(), user.getIsClient(), user.getTShirtSize(), user.getAdmin());
        profileDao.insert(profile);

    }

    void updateProfileData() {
        if (getView() != null) {
            model.getUserData(new Callback<ProfileData>() {
                @Override
                public void onResponse(@NonNull Call<ProfileData> call, @NonNull Response<ProfileData> response) {
                    ProfileData profileData = response.body();
                    updateProfileData(Objects.requireNonNull(profileData).getUser());
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
        updateProfileData();
    }
}
