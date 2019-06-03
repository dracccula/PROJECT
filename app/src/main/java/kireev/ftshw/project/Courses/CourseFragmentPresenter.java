package kireev.ftshw.project.Courses;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import kireev.ftshw.project.Network.Model.HomeworksResponse;
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

    CourseFragmentPresenter(CoursesFragmentModel coursesFragmentModel) {
        this.model = coursesFragmentModel;
    }

    private void checkDatabase() {
        db = App.getInstance().getDatabase();
        GradesDao gradesDao = db.gradesDao();
        if (!spStorage.contains("lessonsLeft")) {
            refreshData();
        } else {
            if (gradesDao.getAllOrderedByMark().isEmpty()) {
                refreshData();
            } else {
                ifViewAttached(view -> {
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        view.hideGradesProgressBar();
                        view.hideRatingProgressBar();
                        view.hideCoursesProgressBar();
                    }, 1);
                    getGradesFromDb();
                    getRatingFromSP();
                    //getProfileIdFromDb();
                    ifViewAttached(view2 -> {
                        Handler handler2 = new Handler();
                        handler2.postDelayed(() -> {
                            view.showCourses(getCourseTitleFromSP(), getCourseStartDateFromSP(), getCoursePointsFromSP());
                        }, 30);
                    });
                });
            }
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
            gradesVO.setActiveUser(gradesList.get(i).isActiveUser);
            gradesVOList.add(gradesVO);
        }
        ifViewAttached(view -> {
            Handler handler = new Handler();
            handler.postDelayed(() -> view.showGrades(gradesVOList), 30);
        });
    }

    public void refreshData() {
        ifViewAttached(view -> view.stopScrollRV());
        getConnections();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            getHomeworksData();
            getGrades();
            getProfile();
        }, 500);
    }

    private int getProfileIdFromDb() {
        db = App.getInstance().getDatabase();
        ProfileDao profileDao = db.profileDao();
        List<Profile> profileList = profileDao.getAll();
        return (profileList != null) ? (int) profileList.get(0).getId() : 0;
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
        ifViewAttached(view -> {
            Handler handler = new Handler();
            handler.postDelayed(() -> view.showRating(profilePoints, allStudents, studentPosition,
                    acceptedTests, allTests, acceptedHomeworks, allHomeworks,
                    allLessons, lessonsDone, lessonsLeft), 30);
        });
    }

    private void updateGradesData(int student_id, String name, int mark, int color, boolean isActive) {
        db = App.getInstance().getDatabase();
        GradesDao gradesDao = db.gradesDao();
        Grades grades = new Grades();
        grades.setStudent_id(student_id);
        grades.setName(name);
        grades.setMark(mark);
        grades.setColor(color);
        grades.setActiveUser(isActive);
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
                ifViewAttached(view -> {
                    view.stopRefreshing();
                    view.showError("Что-то пошло не так..");
                });
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
                ifViewAttached(view -> {
                    view.stopRefreshing();
                    view.showError("Что-то пошло не так..");
                });
            }
        });
    }

    private void getGrades() {
        gradesVOList.clear();
        Handler handler = new Handler();
        handler.postDelayed(() -> model.getGrades(new Callback<List<GradesResponse>>() {
            @Override
            public void onResponse(Call<List<GradesResponse>> call, Response<List<GradesResponse>> response) {
                int activeStudentId = 0;
                int studentId;
                String studentName = "";
                int profilePoints = 0;
                int studentPoints;
                int allStudents = 0, studentPosition = 0, acceptedTests = 0, allTests = 0, acceptedHomeworks = 0, allHomeworks = 0, allLessons = 0, lessonsDone = 0, lessonsLeft = 0;
                if (spStorage.contains("IS_AUTORIZED")) {
                    activeStudentId = getProfileIdFromDb();
                } else {
                    activeStudentId = 0;
                }
                List<GradesResponse> gradesResponseList = response.body();
                for (int i = 0; i < gradesResponseList.size(); i++) {
                    if (gradesResponseList.get(i).getName().toLowerCase().contains("общий")) {
                        List<GradesResponse.Grades> gradesList = gradesResponseList.get(i).getGrades();
                        for (int e = 0; e < gradesList.size(); e++) {
                            allStudents = gradesList.size();
                            boolean isActiveUser = false;
                            if (gradesList.get(e).getStudentId() == activeStudentId) {
                                double points = gradesList.get(e).getGrades().get(0).getMark();
                                profilePoints = (int) Math.round(points);
                                spStorage.edit().putInt("profilePoints", profilePoints).apply();
                                isActiveUser = true;
                            }
                            GradesVO gradesVO = new GradesVO();
                            studentId = gradesList.get(e).getStudentId();
                            gradesVO.setId(studentId);
                            studentName = gradesList.get(e).getStudent();
                            gradesVO.setName(studentName);
                            List<GradesResponse.StudentGrade> studentGradeList = gradesList.get(e).getGrades();
                            Double dPoints = studentGradeList.get(studentGradeList.size() - 1).getMark();
                            studentPoints = (int) Math.round(dPoints);
                            gradesVO.setPoints(studentPoints);
                            int studentColor = SetRandom.SetRandomColor();
                            gradesVO.setColor(studentColor);
                            gradesVO.setActiveUser(isActiveUser);
                            gradesVOList.add(gradesVO);
                            updateGradesData(studentId, studentName, studentPoints, studentColor, isActiveUser);
                        }
                    }
                    if (gradesResponseList.get(i).getName().toLowerCase().contains("доступ")) {
                        List<GradesResponse.Grades> gradesList = gradesResponseList.get(i).getGrades();
                        List<List<GradesResponse.GroupedTask>> groupedTaskList = gradesResponseList.get(i).getGroupedTasks();
                        for (int j = 0; j < gradesList.size(); j++) {
                            for (int k = 0; k < gradesList.size(); k++) {
                                if (gradesList.get(k).getStudentId() == activeStudentId) {
                                    List<GradesResponse.StudentGrade> activeStudentGradeList = gradesList.get(k).getGrades();
                                    for (int l = 0; l < activeStudentGradeList.size(); l++) {
                                        if (activeStudentGradeList.get(l).getTaskType() != null) {
                                            if (activeStudentGradeList.get(l).getTaskType().toLowerCase().contains("test")) {
                                                allTests++;
                                                if (gradesList.get(k).getGrades().get(l).getStatus().contentEquals("accepted")) {
                                                    acceptedTests++;
                                                }
                                            }
                                            if (activeStudentGradeList.get(l).getTaskType().toLowerCase().contains("full")) {
                                                allHomeworks++;
                                                if (activeStudentGradeList.get(l).getStatus().contentEquals("accepted")) {
                                                    acceptedHomeworks++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        for (int j = 0; j < groupedTaskList.size(); j++) {
                            for (int k = 0; k < groupedTaskList.get(j).size(); k++) {
                                if (!groupedTaskList.get(j).get(k).getTitle().toLowerCase().contains("сумма")
                                        && !groupedTaskList.get(j).get(k).getTitle().toLowerCase().contains("тест")
                                        && !groupedTaskList.get(j).get(k).getTitle().toLowerCase().contains("вопрос")) {
                                    allLessons++;
                                }

                            }
                        }
                    }
                    Collections.sort(gradesVOList, (o1, o2) -> Integer.compare(o1.getPoints(), o2.getPoints()));
                    Collections.reverse(gradesVOList);
                    for (int m = 0; m < gradesVOList.size(); m++) {
                        if (gradesVOList.get(m).getId() == activeStudentId) {
                            studentPosition = m + 1;
                        }
                    }
                }
                lessonsLeft = getLessonsLeft();
                lessonsDone = allLessons - lessonsLeft;
                int finalProfilePoints = profilePoints;
                int finalAllStudents = allStudents;
                int finalStudentPosition = studentPosition;
                int finalAcceptedTests = acceptedTests;
                int finalAllTests = allTests;
                int finalAcceptedHomeworks = acceptedHomeworks;
                int finalAllHomeworks = allHomeworks;
                int finalAllLessons = allLessons;
                int finalLessonsDone = lessonsDone;
                int finalLessonsLeft = lessonsLeft;
                ifViewAttached(view -> {
                    view.showGrades(gradesVOList);
                    view.showRating(finalProfilePoints, finalAllStudents, finalStudentPosition, finalAcceptedTests, finalAllTests, finalAcceptedHomeworks, finalAllHomeworks, finalAllLessons, finalLessonsDone, finalLessonsLeft);
                    view.showCourses(getCourseTitleFromSP(), getCourseStartDateFromSP(), getCoursePointsFromSP());
                });
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
                        .apply();
                ifViewAttached(CoursesFragmentView::stopRefreshing);
            }

            @Override
            public void onFailure(Call<List<GradesResponse>> call, Throwable t) {
                t.printStackTrace();
                ifViewAttached(view -> {
                    view.stopRefreshing();
                    view.showError("Что-то пошло не так..");
                });
            }
        }), 500);

    }

    private void getHomeworksData() {
        model.getHomeworksData(new Callback<HomeworksResponse>() {
            @Override
            public void onResponse(Call<HomeworksResponse> call, Response<HomeworksResponse> response) {
                int code = response.code();
                HomeworksResponse homeworksResponse = (HomeworksResponse) response.body();
                if (code == 200) {
                    List<HomeworksResponse.Homework> homeworkList = homeworksResponse.getHomeworks();
                    List<HomeworksResponse.Tasks> tasksList = new ArrayList<>();
                    HomeworksResponse.Task task = new HomeworksResponse.Task();
                    int lessonsLeft = 0;
                    for (int i = 0; i < homeworkList.size(); i++) {
                        tasksList = homeworkList.get(i).getTasks();
                        for (int j = 0; j < tasksList.size(); j++) {
                            task = tasksList.get(j).getTask();

                            Date currentTime = Calendar.getInstance().getTime();
                            Date lessonEnd = null;
                            String deadlineDate = task.getDeadlineDate();
                            String formattedDeadlineDate = "XXX";
                            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
                            if (deadlineDate != null) {
                                try {
                                    lessonEnd = inputFormat.parse(deadlineDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (lessonEnd != null) {
                                    if (lessonEnd.after(currentTime)) {
                                        lessonsLeft++;
                                    }
                                }
                            }
                        }
                    }
                    spStorage.edit().putInt("lessonsLeft", lessonsLeft).apply();
                }
            }

            @Override
            public void onFailure(Call<HomeworksResponse> call, Throwable t) {
                ifViewAttached(view -> {
                    view.stopRefreshing();
                    view.showError("Что-то пошло не так..");
                });
            }
        });
    }

    private int getLessonsLeft() {
        return spStorage.getInt("lessonsLeft", 12);
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
        checkDatabase();
    }
}
