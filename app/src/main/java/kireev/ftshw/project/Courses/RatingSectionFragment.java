package kireev.ftshw.project.Courses;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import kireev.ftshw.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingSectionFragment extends MvpFragment<RatingSectionView, RatingSectionPresenter> implements RatingSectionView {


    public RatingSectionFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public RatingSectionPresenter createPresenter() {
        return new RatingSectionPresenter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating_section, container, false);
    }

}
