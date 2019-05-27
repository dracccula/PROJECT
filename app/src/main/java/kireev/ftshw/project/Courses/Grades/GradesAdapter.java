package kireev.ftshw.project.Courses.Grades;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kireev.ftshw.project.R;
import kireev.ftshw.project.Tools.InitialsRoundView;

public class GradesAdapter extends RecyclerView.Adapter<GradesAdapter.GradesViewHolder> {

    private List<GradesVO> gradesVOList;
    private Context context;

    public GradesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GradesAdapter.GradesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.students_grades_item,viewGroup,false);
        return new GradesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradesAdapter.GradesViewHolder gradesViewHolder, int i) {
        gradesViewHolder.tvGradesStudentName.setText(gradesVOList.get(i).getName());
        gradesViewHolder.tvGradesStudentPoints.setText(gradesVOList.get(i).getPoints());
    }

    @Override
    public int getItemCount() {
        return gradesVOList.size();
    }

    void setItems(List<GradesVO> grades){
        this.gradesVOList = grades;
        notifyDataSetChanged();
    }

    class GradesViewHolder extends RecyclerView.ViewHolder {

        TextView tvGradesStudentName, tvGradesStudentPoints;
        InitialsRoundView irvGradesStudentAvatar;

        GradesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGradesStudentName = itemView.findViewById(R.id.tvGradesStudentName);
            tvGradesStudentPoints = itemView.findViewById(R.id.tvGradesStudentPoints);
            irvGradesStudentAvatar = itemView.findViewById(R.id.irvGradesStudentAvatar);
        }
    }
}
