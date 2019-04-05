package kireev.ftshw.project.Courses.Rating.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kireev.ftshw.project.R;

public class HomeworksAdapter extends RecyclerView.Adapter<HomeworksAdapter.HomeworkViewHolder> {
    private List<HomeworkVO> homeworkList;
    private HomeworkViewHolder homeworkViewHolder;
    private OnClickListener onClickListener;
    private Context context;

//    public HomeworksAdapter(List<HomeworkVO> homeworkList) {
//        this.homeworkList = homeworkList;
//    }

    @NonNull
    @Override
    public HomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homeworks_item, parent, false);
        homeworkViewHolder = new HomeworkViewHolder(view);
        return homeworkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        HomeworkVO homeworkVO = homeworkList.get(position);
        holder.tvHomeworkTitle.setText(homeworkVO.getHomeworkTitle());
    }

    public HomeworksAdapter(OnClickListener onClickListener, Context context) {
        this.onClickListener = onClickListener;
        this.context = context;
    }

    public interface OnClickListener {
        void onClick(HomeworkVO homeworkVO);
    }

    public void setItems(List<HomeworkVO> homeworks) {
        this.homeworkList = homeworks;
        notifyDataSetChanged();
    }

    public void clearList(List<HomeworkVO> homeworks) {
        if (homeworks == null){
            homeworks.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }

    public class HomeworkViewHolder extends RecyclerView.ViewHolder {
        TextView tvHomeworkTitle;

        public HomeworkViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvHomeworkTitle = itemView.findViewById(R.id.tvHomeworkTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomeworkVO homeworkVO = homeworkList.get(getLayoutPosition());
                    onClickListener.onClick(homeworkVO);
                }
            });
        }
    }
}
