package kireev.ftshw.project.Courses.Rating;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
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
    final List<HomeworkVO> homeworkVOList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rating, container, false);
        rvHomeworks = new RecyclerView(getContext());
        rvHomeworks.findViewById(R.id.rvHomeworks);
        mSwipeRefreshLayout = v.findViewById(R.id.refreshRV);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),homeworkVOList.get(1).getHomeworkId(),Toast.LENGTH_LONG);
            }
        });
        return rvHomeworks;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getHomeworksData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private List<HomeworkVO> getHomeworksData() {
        showProgress();
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<HomeworksResponse> call = fintechAPI.getHomeworks();
        call.enqueue(new Callback<HomeworksResponse>() {
            @Override
            public void onResponse(Call call, Response response) {
                HomeworksResponse homeworksResponse = (HomeworksResponse) response.body();
                List<HomeworksResponse.Homework> homeworkList = homeworksResponse.getHomeworks();
                for (int i = 0; i < homeworkList.size(); i++) {
                    HomeworkVO homeworkVO = new HomeworkVO();
                    homeworkVO.setHomeworkId(homeworkList.get(i).getId());
                    homeworkVO.setHomeworkTitle(homeworkList.get(i).getTitle());
                    homeworkVOList.add(homeworkVO);
                }
                String headers = response.headers().toString();
                String cookie = response.headers().get("Set-Cookie");
                if (response.isSuccessful()) {
                    Log.i("getHomeworks Response", "body: " + homeworkList.toString());
                }
                Log.i("homeworkVOList[3]", homeworkVOList.get(3).getHomeworkTitle());
                Collections.reverse(homeworkVOList);
                HomeworksAdapter.OnUserClickListener onUserClickListener = new HomeworksAdapter.OnUserClickListener() {
                    @Override
                    public void onUserClick(HomeworkVO homeworkVO) {
                        Toast.makeText(getContext(), "id " + homeworkVO.getHomeworkId(), Toast.LENGTH_SHORT).show();
                    }
                };
                homeworksAdapter = new HomeworksAdapter(homeworkVOList);
                rvHomeworks.setAdapter(homeworksAdapter);
                rvHomeworks.setLayoutManager(new LinearLayoutManager(getContext()));
                hideProgress();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("getHomeworks onFailure", "ooops!");
                Toast.makeText(getContext(), "getHomeworks went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
        return homeworkVOList;
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
        Log.d("onRefresh", "refreshed!");
    }
}
