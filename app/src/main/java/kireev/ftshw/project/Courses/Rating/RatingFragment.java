package kireev.ftshw.project.Courses.Rating;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
public class RatingFragment extends Fragment {
    RecyclerView rvHomeworks;
    HomeworksAdapter homeworksAdapter;
    List<HomeworkVO> homeworkVOList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rating, container, false);
        rvHomeworks = new RecyclerView(getContext());
        rvHomeworks.findViewById(R.id.rvHomeworks);
        homeworksAdapter = new HomeworksAdapter(initRV());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvHomeworks.setLayoutManager(mLayoutManager);
        rvHomeworks.setItemAnimator(new DefaultItemAnimator());
        rvHomeworks.setAdapter(homeworksAdapter);
        getHomeworksData();
        return rvHomeworks;
    }

    private List initRV() {
        final List<HomeworkVO> homeworkList = new ArrayList<HomeworkVO>();
        homeworkList.add(new HomeworkVO("Лекция 1. Основные компоненты Android"));
        homeworkList.add(new HomeworkVO("Лекция 2. Фрагменты"));
        homeworkList.add(new HomeworkVO("Лекция 3. View и viewgroup"));
        homeworkList.add(new HomeworkVO("Лекция 4. Recycler View"));
        homeworkList.add(new HomeworkVO("Лекция 5. Аcинхронное взаимодействие"));
        homeworkList.add(new HomeworkVO("Лекция 6. Работа с сетью"));
        return homeworkList;
    }

    private void getHomeworksData() {
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<HomeworksResponse> call = fintechAPI.getHomeworks();
        call.enqueue(new Callback<HomeworksResponse>() {
            @Override
            public void onResponse(Call call, Response response) {
                HomeworksResponse homeworksResponse = (HomeworksResponse) response.body();
                String headers = response.headers().toString();
                String cookie = response.headers().get("Set-Cookie");
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Status: " + homeworksResponse.getHomeworks().toString(), Toast.LENGTH_SHORT).show();
                    Log.i("getHomeworks Response", "body: " + homeworksResponse.getHomeworks().toString());
                    Log.i("getHomeworks Response", "headers: " + headers);
                    Log.i("getHomeworks Response", "cookie: " + cookie);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("getHomeworks onFailure", "ooops!");
                Toast.makeText(getContext(), "getHomeworks went wrong!", Toast.LENGTH_SHORT).show();
            }


        });
    }

}
