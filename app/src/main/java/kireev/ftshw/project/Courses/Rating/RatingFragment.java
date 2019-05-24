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
import kireev.ftshw.project.Courses.Rating.Adapters.HomeworkVO;
import kireev.ftshw.project.Courses.Rating.Adapters.HomeworksAdapter;
import kireev.ftshw.project.Courses.Rating.Adapters.TaskVO;
import kireev.ftshw.project.Courses.Rating.Tasks.TasksActivity;
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

import static kireev.ftshw.project.MainActivity.spStorage;

public class RatingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView rvHomeworks;
    HomeworksAdapter homeworksAdapter;
    ProgressDialog pd;
    ProjectDatabase db;
    public final List<HomeworkVO> homeworkVOList = new ArrayList<>();
    public final List<TaskVO> taskVOList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rating, container, false);
        rvHomeworks = v.findViewById(R.id.rvHomeworks);
        rvHomeworks.setLayoutManager(new LinearLayoutManager(getContext()));
        mSwipeRefreshLayout = v.findViewById(R.id.refreshHomeworksRV);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        HomeworksAdapter.OnClickListener onClickListener = new HomeworksAdapter.OnClickListener() {
            @Override
            public void onClick(HomeworkVO homeworkVO) {
                Intent intent = new Intent(getActivity(), TasksActivity.class);
                intent.putExtra(TasksActivity.HOMEWORK_TITLE, homeworkVO.getHomeworkTitle());
                intent.putExtra(String.valueOf(TasksActivity.HOMEWORK_ID), homeworkVO.getHomeworkId());
                startActivity(intent);
            }
        };
        homeworksAdapter = new HomeworksAdapter(onClickListener, getContext());
        db = App.getInstance().getDatabase();
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
        HomeworksDao homeworksDao = db.homeworksDao();
        if (homeworksDao.getAll().isEmpty()) {
            getHomeworksData();
        } else {
            getHomeworksFromDb();
        }
    }

    private void getHomeworksFromDb() {
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

    public void updateHomeworksDB(List<HomeworksResponse.Homework> homeworkList) {
        HomeworksDao homeworksDao = db.homeworksDao();
        Homeworks homeworks = new Homeworks();
        for (int i = 0; i < homeworkList.size(); i++) {
            homeworks.id = homeworkList.get(i).getId();
            homeworks.title = homeworkList.get(i).getTitle();
            homeworksDao.insert(homeworks);
            Log.i("to Homeworks inserted", "id:" + homeworkList.get(i).getId().toString() + " title:" + homeworkList.get(i).getTitle());
        }


    }

    public void updateTaskDB(HomeworksResponse.Task taskresponse, int tasksId, int homeworkId, String status, String mark) {
        db = App.getInstance().getDatabase();
        TasksDao tasksDao = db.tasksDao();
        Tasks tasks = new Tasks(taskresponse.getId(), tasksId, homeworkId, status, mark, taskresponse.getTitle(), taskresponse.getTaskType(), taskresponse.getMaxScore(), taskresponse.getDeadlineDate(), taskresponse.getShortName());
        tasksDao.insert(tasks);
        Log.i("to Tasks inserted", "homeworkId:" + homeworkId + " id:" + taskresponse.getId().toString() + " tasksId:" + tasksId + " title:" + taskresponse.getTitle());
    }

    private void getHomeworksData() {
        showProgress();
        homeworkVOList.clear();
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<HomeworksResponse> call = fintechAPI.getHomeworks(spStorage.getString("courseUrl", ""));
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
                    HomeworksResponse.Task task = new HomeworksResponse.Task();
                    for (int i = 0; i < homeworkList.size(); i++) {
                        HomeworkVO homeworkVO = new HomeworkVO();
                        homeworkVO.setHomeworkId(homeworkList.get(i).getId());
                        homeworkVO.setHomeworkTitle(homeworkList.get(i).getTitle());
                        tasksList = homeworkList.get(i).getTasks();
                        updateHomeworksDB(homeworkList);
                        for (int j = 0; j < tasksList.size(); j++) {
                            task = tasksList.get(j).getTask();
                            updateTaskDB(task, tasksList.get(j).getId(), homeworkList.get(i).getId(), tasksList.get(j).getStatus(), tasksList.get(j).getMark());
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
