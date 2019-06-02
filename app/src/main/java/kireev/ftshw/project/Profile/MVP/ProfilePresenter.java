package kireev.ftshw.project.Profile.MVP;

import android.support.annotation.NonNull;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;
import java.util.Objects;

import kireev.ftshw.project.App;
import kireev.ftshw.project.Database.Dao.ProfileDao;
import kireev.ftshw.project.Database.Entity.Profile;
import kireev.ftshw.project.Database.ProjectDatabase;
import kireev.ftshw.project.Network.Model.GradesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kireev.ftshw.project.MainActivity.spStorage;

public class ProfilePresenter extends MvpBasePresenter<ProfileView> {

    private final ProfileModel model;
    private ProjectDatabase db;

    ProfilePresenter(ProfileModel model) {
        this.model = model;
    }

    private void checkDatabase() {
        db = App.getInstance().getDatabase();
        ProfileDao profileDao = db.profileDao();
        if (profileDao.getAll().isEmpty()) {
            refresh();
        } else {
            getProfileFromDb();
            getProfileProgressFromSp();
            refresh();
        }
    }

    private void getProfileFromDb() {
        db = App.getInstance().getDatabase();
        ProfileDao profileDao = db.profileDao();
        List<Profile> profileList = profileDao.getAll();
        getView().showProfileFromDB(profileList);
    }

    private int getProfileIdFromDb() {
        db = App.getInstance().getDatabase();
        ProfileDao profileDao = db.profileDao();
        List<Profile> profileList = profileDao.getAll();
        return (profileList != null) ? (int) profileList.get(0).getId() : 0;
    }

    private void getProfileProgressFromSp() {
        getView().showProgressOnCard(spStorage.getInt("profilePoints", 0), spStorage.getInt("profileTests", 0), spStorage.getInt("profileCourses", 0));
    }


    private void updateProfileData(ProfileData.User user) {
        db = App.getInstance().getDatabase();
        ProfileDao profileDao = db.profileDao();
        if (user != null) {
            Profile profile = new Profile(user.getId(), user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getEmail(),
                    user.getBirthday(), user.getPhoneMobile(), user.getDescription(), user.getRegion(), user.getSchool(), user.getSchoolGraduation(),
                    user.getUniversity(), user.getFaculty(), user.getUniversityGraduation(), user.getGrade(), user.getDepartment(),
                    user.getCurrentWork(), user.getAvatar(), user.getResume(), user.getSkypeLogin(), user.getIsClient(), user.getTShirtSize(), user.getAdmin());
            profileDao.insert(profile);
        }

    }

    private void refresh() {
        if (getView() != null) {
            model.getUserData(new Callback<ProfileData>() {
                @Override
                public void onResponse(@NonNull Call<ProfileData> call, @NonNull Response<ProfileData> response) {
                    ProfileData profileData = response.body();
                    updateProfileData(Objects.requireNonNull(profileData).getUser());
                    if (profileData.getUser() != null) {
                        if (getView() != null) {
                            getView().showProfileFromResponse(profileData.getUser(), profileData.getStatus());
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ProfileData> call, @NonNull Throwable t) {
                    getView().showError(t.getMessage());
                }
            });
        }

    }

    void viewIsReady() {
        checkDatabase();
        getGrades();
    }

    private void getGrades() {
        model.getGrades(new Callback<List<GradesResponse>>() {
            @Override
            public void onResponse(Call<List<GradesResponse>> call, Response<List<GradesResponse>> response) {
                int testCount = 0;
                double points = 0;
                int intPoints = 0;
                List<GradesResponse> gradesResponseList = response.body();
                for (int i = 0; i < gradesResponseList.size(); i++) {
                    if (gradesResponseList.get(i).getName().toLowerCase().contains("общий")) {
                        List<GradesResponse.Grades> gradesList = gradesResponseList.get(i).getGrades();
                        for (int j = 0; j < gradesList.size(); j++) {
                            if (gradesList.get(j).getStudentId() == getProfileIdFromDb()) {
                                points = gradesList.get(j).getGrades().get(0).getMark();
                                intPoints = (int) Math.round(points);
                            }
                        }
                    }
                }
                for (int i = 0; i < gradesResponseList.size(); i++) {
                    if (gradesResponseList.get(i).getName().toLowerCase().contains("доступ")) {
                        List<List<GradesResponse.GroupedTask>> groupedTaskList = gradesResponseList.get(i).getGroupedTasks();
                        for (int j = 0; j < groupedTaskList.size(); j++) {
                            for (int k = 0; k < groupedTaskList.get(j).size(); k++) {
                                if (!groupedTaskList.get(j).get(k).getTitle().toLowerCase().contains("сумма")) {
                                    if (groupedTaskList.get(j).get(k).getTitle().toLowerCase().contains("тест")) {
                                        testCount++;
                                    }
                                }
                            }
                        }
                    }
                }
                final int finalTestCount = testCount;
                final int finalIntPoints = intPoints;
                spStorage.edit().putInt("profilePoints", finalIntPoints).apply();
                spStorage.edit().putInt("profileTests", finalTestCount).apply();
                spStorage.edit().putInt("profileCourses", 1).apply();
                ifViewAttached(view -> view.showProgressOnCard(finalIntPoints, finalTestCount, 1));
                Log.i("Profile header:", "Баллов: " + intPoints + " Тестов: " + testCount + " Курсов: 1");
            }

            @Override
            public void onFailure(Call<List<GradesResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
