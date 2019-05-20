package kireev.ftshw.project.Courses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kireev.ftshw.project.R;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {
    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.courses_item,viewGroup, false);
        return new CoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder coursesViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CoursesViewHolder extends RecyclerView.ViewHolder {


        CoursesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
