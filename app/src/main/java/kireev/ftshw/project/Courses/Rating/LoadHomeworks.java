package kireev.ftshw.project.Courses.Rating;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Model.HomeworksResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoadHomeworks extends AsyncTask<Void, Void, List<HomeworkVO>> {
    HomeworkVO homeworkVO;
    private RatingFragment fragment;
    List<String> homeworkTitles = new ArrayList<>();
    final List<HomeworkVO> homeworkVOList = new ArrayList<>();

    public void subscribe(RatingFragment fragment) {
        this.fragment = fragment;
    }

    public void unsubscribe() {
        this.fragment = null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //fragment.showProgress();
    }

    @Override
    protected List<HomeworkVO> doInBackground(Void... params) {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<HomeworksResponse> call = fintechAPI.getHomeworks();
        call.enqueue(new Callback<HomeworksResponse>() {
            @Override
            public void onResponse(Call call, Response response) {
                HomeworksResponse homeworksResponse = (HomeworksResponse) response.body();
                List<HomeworksResponse.Homework> homeworkList = homeworksResponse.getHomeworks();
                for (int i = 0; i < homeworkList.size(); i++) {
                    homeworkVO = new HomeworkVO();
                    homeworkVO.setHomeworkTitle(homeworkList.get(i).getTitle());
                    homeworkVOList.add(homeworkVO);
                }
                String headers = response.headers().toString();
                String cookie = response.headers().get("Set-Cookie");
                if (response.isSuccessful()) {
                    Log.i("getHomeworks Response", "body: " + homeworkList.toString());
                    Log.i("getHomeworks Response", "headers: " + headers);
                    Log.i("getHomeworks Response", "cookie: " + cookie);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("getHomeworks onFailure", "ooops!");
                Toast.makeText(fragment.getContext(), "getHomeworks went wrong!", Toast.LENGTH_SHORT).show();
            }


        });

        return homeworkVOList;
    }

    @Override
    protected void onPostExecute(List<HomeworkVO> homeworkVOList) {
        // TODO Auto-generated method stub
        super.onPostExecute(homeworkVOList);
        //fragment.hideProgress();
        fragment.showList(homeworkVOList);
    }

}
