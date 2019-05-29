package kireev.ftshw.project.Courses;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import kireev.ftshw.project.App;
import kireev.ftshw.project.Courses.FinishedCourses.FinishedCoursesSectionFragment;
import kireev.ftshw.project.Courses.Grades.GradesSectionFragment;
import kireev.ftshw.project.Courses.Grades.GradesVO;
import kireev.ftshw.project.Database.Dao.ProfileDao;
import kireev.ftshw.project.Database.Entity.Profile;
import kireev.ftshw.project.Database.ProjectDatabase;
import kireev.ftshw.project.Network.Model.ConnectionsResponse;
import kireev.ftshw.project.Network.Model.GradesResponse;
import kireev.ftshw.project.Profile.MVP.ProfileData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kireev.ftshw.project.MainActivity.spStorage;

class CourseFragmentPresenter extends MvpBasePresenter<CoursesFragmentView> {

    private CoursesFragmentModel model;
    private List<GradesVO> gradesVOList = new ArrayList<>();
    private ProjectDatabase db;
    private GradesSectionFragment gradesSectionFragmentragment;
    private FinishedCoursesSectionFragment finishedCoursesSectionFragment;

    CourseFragmentPresenter(CoursesFragmentModel coursesFragmentModel) {
        this.model = coursesFragmentModel;
    }

    public void setGradesSectionFragment(GradesSectionFragment grades) {
        this.gradesSectionFragmentragment = grades;
    }

    public void setFinishedCoursesSectionFragment(FinishedCoursesSectionFragment courses) {
        this.finishedCoursesSectionFragment = courses;
    }

    private int getProfileIdFromDb() {
        db = App.getInstance().getDatabase();
        ProfileDao profileDao = db.profileDao();
        List<Profile> profileList = profileDao.getAll();
        return (int) profileList.get(0).getId();
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

    private void getConnections() {
        model.getConnections(new Callback<ConnectionsResponse>() {
            @Override
            public void onResponse(Call<ConnectionsResponse> call, Response<ConnectionsResponse> response) {
                ConnectionsResponse connectionsResponse = response.body();
                int code = response.code();
                if (code == 200 && Objects.requireNonNull(connectionsResponse).getCourses() != null) {
                    List<ConnectionsResponse.Course> courseList = connectionsResponse.getCourses();
                    SharedPreferences.Editor ed = spStorage.edit();
                    String title = courseList.get(0).getTitle();
                    String url = courseList.get(0).getUrl();
                    String status = courseList.get(0).getStatus();
                    String dateStart = courseList.get(0).getEventDateStart();
                    ed.putString("courseTitle", title);
                    ed.putString("courseUrl", url);
                    ed.putString("courseStatus", status);
                    ed.putString("courseDateStart", dateStart);
                    ed.apply();
                    Log.e("getConnections", url + title + status + dateStart);
                }
            }

            @Override
            public void onFailure(Call<ConnectionsResponse> call, Throwable t) {
            }
        });
    }

    private void getProfile(){
        model.getUserData(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                ProfileData profileData = response.body();
                updateProfileData(Objects.requireNonNull(profileData).getUser());
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {

            }
        });
    }

    private void getGrades() {
        if (!spStorage.contains("courseUrl")) {
            getConnections();
        }
        gradesVOList.clear();
        model.getGrades(new Callback<List<GradesResponse>>() {
            @Override
            public void onResponse(Call<List<GradesResponse>> call, Response<List<GradesResponse>> response) {
                String studentName = "";
                int profilePoints = 0;
                int studentPoints;
                List<GradesResponse> gradesResponseList = response.body();
                for (int i = 0; i < gradesResponseList.size(); i++) {
                    if (gradesResponseList.get(i).getName().toLowerCase().contains("общий")) {
                        List<GradesResponse.Grades> gradesList = gradesResponseList.get(i).getGrades();
                        for (int j = 0; j < gradesList.size(); j++) {
                            if (gradesList.get(j).getStudentId() == getProfileIdFromDb()){
                                double points = gradesList.get(j).getGrades().get(0).getMark();
                                profilePoints = (int) Math.round(points);
                                spStorage.edit().putInt("profilePoints", profilePoints).apply();
                            }
                            GradesVO gradesVO = new GradesVO();
                            studentName = gradesList.get(j).getStudent();
                            gradesVO.setName(studentName);
                            List<GradesResponse.StudentGrade> studentGradeList = gradesList.get(j).getGrades();
                            Double dPoints = studentGradeList.get(studentGradeList.size() - 1).getMark();
                            studentPoints = (int) Math.round(dPoints);
                            gradesVO.setPoints(studentPoints);
                            gradesVOList.add(gradesVO);
                        }
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Collections.sort(gradesVOList, Comparator.comparing(GradesVO::getPoints));
                    }
                    Collections.reverse(gradesVOList);
                }
                Log.i("hui123", "getGrades in main");
                gradesSectionFragmentragment.showGrades(gradesVOList);
                finishedCoursesSectionFragment.showCourses(getCourseTitleFromSP(),getCourseStartDateFromSP(),getCoursePointsFromSP());
                Log.i("Profile header:", gradesVOList.toString());
                ifViewAttached(CoursesFragmentView::stopRefreshing);
            }

            @Override
            public void onFailure(Call<List<GradesResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private String getCourseTitleFromSP() {
        return spStorage.getString("courseTitle", "");
    }

    private String getCourseStartDateFromSP() {
        return spStorage.getString("courseDateStart", "");
    }

    private String getCoursePointsFromSP() {
        return String.valueOf(spStorage.getInt("profilePoints", 0));
    }

    public void viewIsReady(){
        getConnections();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getGrades();
        getProfile();
    }
}
