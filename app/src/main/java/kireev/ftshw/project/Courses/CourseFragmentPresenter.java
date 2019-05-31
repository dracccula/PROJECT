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
import kireev.ftshw.project.Courses.Rating.RatingSectionFragment;
import kireev.ftshw.project.Database.Dao.GradesDao;
import kireev.ftshw.project.Database.Dao.ProfileDao;
import kireev.ftshw.project.Database.Entity.Grades;
import kireev.ftshw.project.Database.Entity.Profile;
import kireev.ftshw.project.Database.ProjectDatabase;
import kireev.ftshw.project.Network.Model.ConnectionsResponse;
import kireev.ftshw.project.Network.Model.GradesResponse;
import kireev.ftshw.project.Profile.MVP.ProfileData;
import kireev.ftshw.project.TempTools.SetRandom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kireev.ftshw.project.MainActivity.spStorage;

public class CourseFragmentPresenter extends MvpBasePresenter<CoursesFragmentView> {

    private CoursesFragmentModel model;
    private List<GradesVO> gradesVOList = new ArrayList<>();
    private ProjectDatabase db;
    private GradesSectionFragment gradesSectionFragment;
    private RatingSectionFragment ratingSectionFragment;
    private FinishedCoursesSectionFragment finishedCoursesSectionFragment;

    CourseFragmentPresenter(CoursesFragmentModel coursesFragmentModel) {
        this.model = coursesFragmentModel;
    }

    public void setGradesSectionFragment(GradesSectionFragment grades) {
        this.gradesSectionFragment = grades;
    }

    public void setRatingSectionFragment(RatingSectionFragment rating) {
        this.ratingSectionFragment = rating;
    }

    public void setFinishedCoursesSectionFragment(FinishedCoursesSectionFragment courses) {
        this.finishedCoursesSectionFragment = courses;
    }

    private void checkDatabase() {
        db = App.getInstance().getDatabase();
        GradesDao gradesDao = db.gradesDao();
        if (gradesDao.getAllOrderedByMark().isEmpty()) {
            refreshData();
        } else {
            getGradesFromDb();
            //getRatingFromSP();
            getProfileIdFromDb();
            refreshData();
        }
    }

    private void getGradesFromDb() {
        db = App.getInstance().getDatabase();
        GradesDao gradesDao = db.gradesDao();
        List<Grades> gradesList = gradesDao.getAllOrderedByMark();
        for (int i = 0; i < gradesList.size(); i++) {
            GradesVO gradesVO = new GradesVO();
            gradesVO.setId(gradesList.get(i).student_id);
            gradesVO.setName(gradesList.get(i).name);
            gradesVO.setPoints(gradesList.get(i).mark);
            gradesVO.setColor(gradesList.get(i).color);
            gradesVOList.add(gradesVO);
        }
        ifViewAttached(view -> {
            gradesSectionFragment.showGrades(gradesVOList);
        });
    }

    private void refreshData() {
        getGrades();
        getProfile();
    }

    private int getProfileIdFromDb() {
        db = App.getInstance().getDatabase();
        ProfileDao profileDao = db.profileDao();
        List<Profile> profileList = profileDao.getAll();
        return (int) profileList.get(0).getId();
    }

    private void getRatingFromSP() {
        int profilePoints = spStorage.getInt("profilePoints", 0);
        int allStudents = spStorage.getInt("allStudents", 0);
        int studentPosition = spStorage.getInt("studentPosition", 0);
        int acceptedTests = spStorage.getInt("acceptedTests", 0);
        int allTests = spStorage.getInt("allTests", 0);
        int acceptedHomeworks = spStorage.getInt("acceptedHomeworks", 0);
        int allHomeworks = spStorage.getInt("allHomeworks", 0);
        int allLessons = spStorage.getInt("allLessons", 0);
        int lessonsDone = spStorage.getInt("lessonsDone", 0);
        int lessonsLeft = spStorage.getInt("lessonsLeft", 0);
        ifViewAttached(view -> ratingSectionFragment.showRating(profilePoints, allStudents, studentPosition, acceptedTests, allTests, acceptedHomeworks, allHomeworks, allLessons, lessonsDone, lessonsLeft));
    }

    private void updateGradesData(int student_id, String name, int mark, int color) {
        db = App.getInstance().getDatabase();
        GradesDao gradesDao = db.gradesDao();
        Grades grades = new Grades();
        grades.setStudent_id(student_id);
        grades.setName(name);
        grades.setMark(mark);
        grades.setColor(color);
        gradesDao.insert(grades);


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

    private void getProfile() {
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
                int studentId;
                String studentName = "";
                int profilePoints = 0;
                int studentPoints;
                int allStudents = 0, studentPosition = 0, acceptedTests = 0, allTests = 0, acceptedHomeworks = 0, allHomeworks = 0, allLessons = 0, lessonsDone = 0, lessonsLeft = 0;
                int activeStudentId = getProfileIdFromDb();

                List<GradesResponse> gradesResponseList = response.body();
                for (int i = 0; i < gradesResponseList.size(); i++) {
                    if (gradesResponseList.get(i).getName().toLowerCase().contains("общий")) {
                        List<GradesResponse.Grades> gradesList = gradesResponseList.get(i).getGrades();
                        for (int j = 0; j < gradesList.size(); j++) {
                            allStudents = gradesList.size();
                            if (gradesList.get(j).getStudentId() == activeStudentId) {
                                double points = gradesList.get(j).getGrades().get(0).getMark();
                                profilePoints = (int) Math.round(points);
                                spStorage.edit().putInt("profilePoints", profilePoints).apply();
                            }
                            GradesVO gradesVO = new GradesVO();
                            studentId = gradesList.get(j).getStudentId();
                            gradesVO.setId(studentId);
                            studentName = gradesList.get(j).getStudent();
                            gradesVO.setName(studentName);
                            List<GradesResponse.StudentGrade> studentGradeList = gradesList.get(j).getGrades();
                            Double dPoints = studentGradeList.get(studentGradeList.size() - 1).getMark();
                            studentPoints = (int) Math.round(dPoints);
                            gradesVO.setPoints(studentPoints);
                            int studentColor = SetRandom.SetRandomColor();
                            gradesVO.setColor(studentColor);
                            gradesVOList.add(gradesVO);
                            updateGradesData(studentId, studentName, studentPoints, studentColor);
                        }
                    }
                    if (gradesResponseList.get(i).getName().toLowerCase().contains("доступ")) {
                        List<GradesResponse.Grades> gradesList = gradesResponseList.get(i).getGrades();
                        for (int j = 0; j < gradesList.size(); j++) {
                            for (int k = 0; k < gradesList.size(); k++) {
                                if (gradesList.get(k).getStudentId() == activeStudentId){
                                    for (int l = 0; l < gradesList.size(); l++) {
                                        if (gradesList.get(k).getGrades().get(l).getTaskType().toLowerCase().contains("test")) {
                                            allTests++;
                                            if (gradesList.get(k).getGrades().get(l).getStatus().contentEquals("accepted")) {
                                                acceptedTests++;
                                            }
                                        }
                                        if (gradesList.get(k).getGrades().get(l).getTaskType().toLowerCase().contains("full")) {
                                            allHomeworks++;
                                            if (gradesList.get(k).getGrades().get(l).getStatus().contentEquals("accepted")) {
                                                acceptedHomeworks++;
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                    Collections.sort(gradesVOList, new Comparator<GradesVO>() {
                        @Override
                        public int compare(GradesVO o1, GradesVO o2) {
                            return Integer.compare(o1.getPoints(), o2.getPoints());
                        }
                    });
                    Collections.reverse(gradesVOList);
                    for (int m = 0; m < gradesVOList.size(); m++) {
                        if (gradesVOList.get(m).getId() == activeStudentId) {
                            studentPosition = m+1;
                        }
                    }
                }
                Log.i("hui123", "getGrades in main");
                gradesSectionFragment.showGrades(gradesVOList);
                ratingSectionFragment.showRating(profilePoints, allStudents, studentPosition, acceptedTests, allTests, acceptedHomeworks, allHomeworks, 5, 2, 3);
                finishedCoursesSectionFragment.showCourses(getCourseTitleFromSP(), getCourseStartDateFromSP(), getCoursePointsFromSP());
                spStorage.edit()
                        .putInt("profilePoints", profilePoints)
                        .putInt("allStudents", allStudents)
                        .putInt("studentPosition", studentPosition)
                        .putInt("acceptedTests", acceptedTests)
                        .putInt("allTests", allTests)
                        .putInt("acceptedHomeworks", acceptedHomeworks)
                        .putInt("allHomeworks", allHomeworks)
                        .putInt("allLessons", allLessons)
                        .putInt("lessonsDone", lessonsDone)
                        .putInt("lessonsLeft", lessonsLeft)
                        .apply();
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

    public void viewIsReady() {
        getConnections();
        checkDatabase();
    }
}
