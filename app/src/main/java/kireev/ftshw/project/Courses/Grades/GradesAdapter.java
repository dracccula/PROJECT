package kireev.ftshw.project.Courses.Grades;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kireev.ftshw.project.App;
import kireev.ftshw.project.R;
import kireev.ftshw.project.Tools.InitialsRoundView;

public class GradesAdapter extends RecyclerView.Adapter<GradesAdapter.GradesViewHolder> {

    private List<GradesVO> gradesVOList;
    private Context context;
    private int activeUser;

    public GradesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GradesAdapter.GradesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.students_grades_item, viewGroup, false);
        return new GradesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradesAdapter.GradesViewHolder gradesViewHolder, int i) {
        int dpPadding = 8;
        final float scale = App.getContext().getResources().getDisplayMetrics().density;
        int pxPadding = (int) (dpPadding * scale + 0.5f);
        if (gradesVOList.get(i).isActiveUser()) {
            gradesViewHolder.tvGradesStudentName.setText(gradesVOList.get(i).getName());
            gradesViewHolder.irvGradesStudentAvatar.setText(gradesVOList.get(i).getName());
            gradesViewHolder.irvGradesStudentAvatar.setBackgroundColor(gradesVOList.get(i).getColor());
            gradesViewHolder.tvGradesStudentPoints.setText(gradesVOList.get(i).getPoints() + "");
            gradesViewHolder.tvGradesStudentName.setTypeface(Typeface.DEFAULT_BOLD);
            gradesViewHolder.tvGradesStudentPoints.setBackgroundResource(R.drawable.badge_active_user);
            gradesViewHolder.tvGradesStudentPoints.setPadding(pxPadding, pxPadding, pxPadding, pxPadding);
            gradesViewHolder.tvGradesStudentPoints.setTextColor(Color.BLACK);
            gradesViewHolder.tvGradesStudentPoints.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            gradesViewHolder.tvGradesStudentName.setText(gradesVOList.get(i).getName());
            gradesViewHolder.irvGradesStudentAvatar.setText(gradesVOList.get(i).getName());
            gradesViewHolder.irvGradesStudentAvatar.setBackgroundColor(gradesVOList.get(i).getColor());
            gradesViewHolder.tvGradesStudentPoints.setText(gradesVOList.get(i).getPoints() + "");
            gradesViewHolder.tvGradesStudentName.setTypeface(Typeface.DEFAULT);
            gradesViewHolder.tvGradesStudentPoints.setBackgroundResource(R.drawable.badge);
            gradesViewHolder.tvGradesStudentPoints.setPadding(pxPadding, pxPadding, pxPadding, pxPadding);
            gradesViewHolder.tvGradesStudentPoints.setTextColor(Color.WHITE);
            gradesViewHolder.tvGradesStudentPoints.setTypeface(Typeface.DEFAULT);
        }

    }

    @Override
    public int getItemCount() {
        return gradesVOList.size();
    }

    void setItems(List<GradesVO> grades) {
        this.gradesVOList = grades;
        notifyDataSetChanged();
        for (int i = 0; i < gradesVOList.size(); i++) {
            if (gradesVOList.get(i).isActiveUser()) {
                activeUser = i;
            }
        }
    }

    void clearList(List<GradesVO> grades) {
        if (grades == null) {
            grades.clear();
            notifyDataSetChanged();
        }
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
