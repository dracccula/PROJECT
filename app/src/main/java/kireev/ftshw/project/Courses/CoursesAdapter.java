package kireev.ftshw.project.Courses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kireev.ftshw.project.R;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {

    private List<CoursesVO> coursesVOList;
    private OnClickListener onClickListener;
    private Context context;

    CoursesAdapter(OnClickListener onClickListener, Context context) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.courses_item,viewGroup, false);
        return new CoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder coursesViewHolder, int i) {
        String courseDateStart = coursesVOList.get(0).getDateStart();
        String formattedDateStart = "Отсутствует";
        if (courseDateStart != null) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            try {
                Date dStartDate = inputFormat.parse(courseDateStart);
                SimpleDateFormat newDate = new SimpleDateFormat("MMM yyyy", Locale.getDefault());//set format of new date

                formattedDateStart = newDate.format(dStartDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            coursesViewHolder.tvCourseDate.setText(formattedDateStart.toUpperCase());
        } else {
            coursesViewHolder.tvCourseDate.setVisibility(View.INVISIBLE);
        }
        coursesViewHolder.tvCourseDate.setText(formattedDateStart);
        coursesViewHolder.tvCourseTitle.setText(coursesVOList.get(0).getTitle());
    }

    public interface OnClickListener {
        void onClick(CoursesVO coursesVO);
    }

    @Override
    public int getItemCount() {
        return coursesVOList.size();
    }

    void setItems(List<CoursesVO> courses) {
        this.coursesVOList = courses;
        notifyDataSetChanged();
    }

    class CoursesViewHolder extends RecyclerView.ViewHolder {

        TextView tvCourseTitle, tvCourseDate, tvCoursePoints;


        CoursesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseTitle = itemView.findViewById(R.id.tvCourseTitle);
            tvCourseDate = itemView.findViewById(R.id.tvCourseDate);
            tvCoursePoints = itemView.findViewById(R.id.tvCoursePoints);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CoursesVO coursesVO = coursesVOList.get(getLayoutPosition());
                    onClickListener.onClick(coursesVO);
                }
            });
        }
    }
}
