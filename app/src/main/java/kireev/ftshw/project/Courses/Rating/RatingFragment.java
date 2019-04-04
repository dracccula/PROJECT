package kireev.ftshw.project.Courses.Rating;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kireev.ftshw.project.App;
import kireev.ftshw.project.Database.Dao.HomeworksDao;
import kireev.ftshw.project.Database.Dao.TasksDao;
import kireev.ftshw.project.Database.Entity.Homeworks;
import kireev.ftshw.project.Database.Entity.Tasks;
import kireev.ftshw.project.Database.ProjectDatabase;
import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Model.HomeworksResponse;
import kireev.ftshw.project.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView rvHomeworks;
    HomeworksAdapter homeworksAdapter;
    ProgressDialog pd;
    ProjectDatabase db;
    public final List<HomeworkVO> homeworkVOList = new ArrayList<>();
    public final List<TasksVO> tasksVOList = new ArrayList<>();
    public final List<TaskVO> taskVOList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rating, container, false);
        rvHomeworks = v.findViewById(R.id.rvHomeworks);
        rvHomeworks.setLayoutManager(new LinearLayoutManager(getContext()));
        mSwipeRefreshLayout = v.findViewById(R.id.refreshRV);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        HomeworksAdapter.OnClickListener onClickListener = new HomeworksAdapter.OnClickListener() {
            @Override
            public void onClick(HomeworkVO homeworkVO) {
                Intent intent = new Intent(getActivity(), TasksActivity.class);
                intent.putExtra(TasksActivity.HOMEWORK_TITLE, homeworkVO.getHomeworkTitle());
                startActivity(intent);
                Toast.makeText(getContext(), "id " + homeworkVO.getHomeworkId(), Toast.LENGTH_SHORT).show();
            }
        };
        homeworksAdapter = new HomeworksAdapter(onClickListener, getContext());
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkDatabase();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void checkDatabase() {
        db = App.getInstance().getDatabase();
        HomeworksDao homeworksDao = db.homeworksDao();
        if (homeworksDao.getAll().isEmpty()) {
            getHomeworksData();
        } else {
            getHomeworksFromDb();
        }
    }

    private void getHomeworksFromDb() {
        db = App.getInstance().getDatabase();
        HomeworksDao homeworksDao = db.homeworksDao();
        List<Homeworks> homeworksList = homeworksDao.getAll();
        for (int i = 0; i < homeworksList.size(); i++) {
            HomeworkVO homeworkVO = new HomeworkVO();
            homeworkVO.setHomeworkId(homeworksList.get(i).id);
            homeworkVO.setHomeworkTitle(homeworksList.get(i).title);
            homeworkVOList.add(homeworkVO);
        }
        homeworksAdapter.setItems(homeworkVOList);
        rvHomeworks.setAdapter(homeworksAdapter);
    }

    public void updateDB(List<HomeworksResponse.Homework> homeworkList, List<HomeworksResponse.Tasks> tasksList) {
        ProjectDatabase db = App.getInstance().getDatabase();
        HomeworksDao homeworksDao = db.homeworksDao();
        Homeworks homeworks = new Homeworks();
        TasksDao tasksDao = db.tasksDao();
        Tasks tasks = new Tasks();
        if (homeworksDao.getAll().isEmpty()) {
            for (int i = 0; i < homeworkList.size(); i++) {
                homeworks.id = homeworkList.get(i).getId();
                homeworks.title = homeworkList.get(i).getTitle();
                homeworksDao.insert(homeworks);
            }
            for (int i = 0; i < tasksList.size(); i++) {
                tasks.id = tasksList.get(i).getId();
                tasks.status = tasksList.get(i).getStatus();
                tasks.mark = tasksList.get(i).getMark();
                tasksDao.insert(tasks);
            }

        } else {
            for (int i = 0; i < homeworkList.size(); i++) {
                homeworks.id = homeworkList.get(i).getId();
                homeworks.title = homeworkList.get(i).getTitle();
                homeworksDao.update(homeworks);
            }
            for (int i = 0; i < tasksList.size(); i++) {
                tasks.id = tasksList.get(i).getId();
                tasks.status = tasksList.get(i).getStatus();
                tasks.mark = tasksList.get(i).getMark();
                tasksDao.update(tasks);
            }
        }
    }

    private void getHomeworksData() {
        showProgress();
        homeworkVOList.clear();
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<HomeworksResponse> call = fintechAPI.getHomeworks();
        call.enqueue(new Callback<HomeworksResponse>() {
            @Override
            public void onResponse(Call call, Response response) {
                HomeworksResponse homeworksResponse = (HomeworksResponse) response.body();
                int code = response.code();
                if (code == 403) {
                    Toast.makeText(getContext(), String.valueOf(code), Toast.LENGTH_SHORT).show();
                    hideProgress();
                }
                if (code == 200) {
                    List<HomeworksResponse.Homework> homeworkList = homeworksResponse.getHomeworks();
                    List<HomeworksResponse.Tasks> tasksList = new ArrayList<>();
                    List<HomeworksResponse.Task> taskList = new ArrayList<>();
                    for (int i = 0; i < homeworkList.size(); i++) {
                        HomeworkVO homeworkVO = new HomeworkVO();
                        homeworkVO.setHomeworkId(homeworkList.get(i).getId());
                        homeworkVO.setHomeworkTitle(homeworkList.get(i).getTitle());
                        for (int j = 0; j < homeworkList.size(); j++) {
                            TasksVO tasksVO = new TasksVO();
                            tasksVO.setTasksId(tasksList.get(j).getId());
                            tasksVO.setTasksStatus(tasksList.get(j).getStatus());
                            tasksVO.setTasksMark(tasksList.get(j).getMark());
                            for (int k = 0; k < homeworkList.size(); k++) {
                                TaskVO taskVO = new TaskVO();
                                taskVO.setTaskId(taskList.get(k).getId());
                                taskVO.setTaskTitle(taskList.get(k).getTitle());
                                taskVO.setTaskTask_type(taskList.get(k).getTaskType());
                                taskVO.setTaskMax_score(taskList.get(k).getMaxScore());
                                taskVO.setTaskDeadline_date(taskList.get(k).getDeadlineDate());
                                taskVO.setTaskShort_name(taskList.get(k).getShortName());
                                taskVOList.add(taskVO);
                            }
                            tasksVOList.add(tasksVO);
                        }
                        homeworkVOList.add(homeworkVO);
                    }
                    String headers = response.headers().toString();
                    String cookie = response.headers().get("Set-Cookie");
                    if (response.isSuccessful()) {
                        Log.i("getHomeworks Response", "body: " + homeworkList.toString());
                    }
                    Log.i("homeworkVOList[3]", homeworkVOList.get(3).getHomeworkTitle());
                    Collections.reverse(homeworkVOList);
                    homeworksAdapter.setItems(homeworkVOList);
                    rvHomeworks.setAdapter(homeworksAdapter);
                    updateDB(homeworkList, tasksList);
                    hideProgress();
                } else {
                    Toast.makeText(getContext(), String.valueOf(code), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("getHomeworks onFailure", "ooops!");
                Toast.makeText(getContext(), "getHomeworks went wrong!", Toast.LENGTH_SHORT).show();
                getHomeworksFromDb();
                hideProgress();
            }
        });
    }

    public void showProgress() {
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Загрузка домашек...");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
    }

    public void hideProgress() {
        pd.dismiss();
    }

    @Override
    public void onRefresh() {
        getHomeworksData();
        mSwipeRefreshLayout.setRefreshing(false);
        Log.d("onRefresh", "refreshed!");
    }
}
