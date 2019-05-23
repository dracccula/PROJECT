package kireev.ftshw.project.Courses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kireev.ftshw.project.Database.Entity.Course;
import kireev.ftshw.project.R;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {

    private Context context;
    private CoursesVO coursesVO;

    public CoursesAdapter(Context context) {this.context = context;
    }

    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.courses_item,viewGroup, false);
        return new CoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder coursesViewHolder, int i) {
        coursesViewHolder.tvCourseDate.setText(coursesVO.getDateStart());
        coursesViewHolder.tvCourseTitle.setText(coursesVO.getTitle());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class CoursesViewHolder extends RecyclerView.ViewHolder {

        TextView tvCourseTitle, tvCourseDate, tvCoursePoints;


        CoursesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseTitle = itemView.findViewById(R.id.tvCourseTitle);
            tvCourseDate = itemView.findViewById(R.id.tvCourseDate);
            tvCoursePoints = itemView.findViewById(R.id.tvCoursePoints);
        }
    }
}
