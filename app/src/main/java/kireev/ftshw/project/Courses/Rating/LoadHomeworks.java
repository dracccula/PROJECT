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

public class LoadHomeworks extends AsyncTask<Void, Void, ArrayList<HomeworkVO>> {
    HomeworkVO homeworkVO;
    private RatingFragment fragment;
    final ArrayList<HomeworkVO> homeworkVOList = new ArrayList<>();

    public void subscribe(RatingFragment fragment) {
        this.fragment = fragment;
    }

    public void unsubscribe() {
        this.fragment = null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        fragment.showProgress();
    }

    @Override
    protected ArrayList<HomeworkVO> doInBackground(Void... params) {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        return homeworkVOList;
    }

    @Override
    protected void onPostExecute(ArrayList<HomeworkVO> homeworkVOList) {
        // TODO Auto-generated method stub
        super.onPostExecute(homeworkVOList);
        if (homeworkVOList.size() != 0) {
            Log.d("homeworkVOList[3]:", homeworkVOList.get(3).getHomeworkTitle());
        }
        fragment.hideProgress();
        //fragment.showList(homeworkVOList);

//        Toast.makeText(fragment.getContext(),homeworkVOList.get(3).getHomeworkTitle(),Toast.LENGTH_LONG);
    }

}
