package kireev.ftshw.project.Courses.Rating;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kireev.ftshw.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingFragment extends Fragment {
    RecyclerView rvHomeworks;
    HomeworksAdapter homeworksAdapter;
    LoadHomeworks lh;
    List<HomeworkVO> homeworkList;
    ProgressDialog pd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.fragment_rating, container, false);
        rvHomeworks = new RecyclerView(getContext());
        rvHomeworks.findViewById(R.id.rvHomeworks);
//        homeworksAdapter = new HomeworksAdapter(initRV());
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        rvHomeworks.setLayoutManager(mLayoutManager);
//        rvHomeworks.setItemAnimator(new DefaultItemAnimator());
//        rvHomeworks.setAdapter(homeworksAdapter);
        return rvHomeworks;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (homeworkList == null){
            lh = new LoadHomeworks();
            //lh.subscribe(this);
            lh.execute();
        }
    }

    @Override
    public void onDestroyView() {
        //lh.unsubscribe();
        super.onDestroyView();
    }

    private List initRV() {
        homeworkList.add(new HomeworkVO("Лекция 1. Основные компоненты Android"));
        homeworkList.add(new HomeworkVO("Лекция 2. Фрагменты"));
        homeworkList.add(new HomeworkVO("Лекция 3. View и viewgroup"));
        homeworkList.add(new HomeworkVO("Лекция 4. Recycler View"));
        homeworkList.add(new HomeworkVO("Лекция 5. Аcинхронное взаимодействие"));
        homeworkList.add(new HomeworkVO("Лекция 6. Работа с сетью"));
        return homeworkList;
    }

    private void getHomeworksData() {

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

    public void showList(List<HomeworkVO> homeworkVOList) {
        homeworksAdapter = new HomeworksAdapter(homeworkVOList);
        rvHomeworks.setAdapter(homeworksAdapter);
        rvHomeworks.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
