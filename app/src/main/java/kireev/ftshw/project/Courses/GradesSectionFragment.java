package kireev.ftshw.project.Courses;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kireev.ftshw.project.Tools.InitialsRoundView;
import kireev.ftshw.project.R;

public class GradesSectionFragment extends Fragment {

    public static TextView pointsBadge1, pointsBadge2, pointsBadge3;
    public static TextView tvAvatarNameOne, tvAvatarNameTwo, tvAvatarNameThree;
    public static InitialsRoundView viewAvatarOne, viewAvatarTwo, viewAvatarThree;


    public GradesSectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_grades_section, container, false);
        pointsBadge1 = v.findViewById(R.id.pointsBadgeOne);
        pointsBadge2 = v.findViewById(R.id.pointsBadgeTwo);
        pointsBadge3 = v.findViewById(R.id.pointsBadgeThree);
        tvAvatarNameOne = v.findViewById(R.id.tvAvatarNameOne);
        tvAvatarNameTwo = v.findViewById(R.id.tvAvatarNameTwo);
        tvAvatarNameThree = v.findViewById(R.id.tvAvatarNameThree);
        viewAvatarOne = v.findViewById(R.id.viewAvatarOne);
        viewAvatarTwo = v.findViewById(R.id.viewAvatarTwo);
        viewAvatarThree = v.findViewById(R.id.viewAvatarThree);

        viewAvatarOne.setText((String) tvAvatarNameOne.getText());
        viewAvatarTwo.setText((String) tvAvatarNameTwo.getText());
        viewAvatarThree.setText((String) tvAvatarNameThree.getText());
        return v;
    }

}
