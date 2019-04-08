package kireev.ftshw.project.Courses.Rating.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kireev.ftshw.project.R;
import kireev.ftshw.project.Tools.InitialsRoundView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<TaskVO> taskList;
    private TaskViewHolder taskViewHolder;
    private OnClickListener onClickListener;
    private Context context;


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_item, parent, false);
        taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskVO taskVO = taskList.get(position);
        holder.tvTitle.setText(taskVO.getTaskTitle());
        holder.tvMark.setText(taskVO.getTasksMark());
        if (taskVO.getTasksStatus().equals("accepted")) {
            holder.irvStatus.setBackgroundColor(Color.GREEN);
        }
        if (taskVO.getTasksStatus().equals("failed")) {
            holder.irvStatus.setBackgroundColor(Color.RED);
        }
    }

    public TaskAdapter(OnClickListener onClickListener, Context context) {
        this.onClickListener = onClickListener;
        this.context = context;
    }

    public interface OnClickListener {
        void onClick(TaskVO taskVO);
    }

    public void setItems(List<TaskVO> task) {
        this.taskList = task;
        notifyDataSetChanged();
    }

    public void clearList(List<TaskVO> tasks) {
        if (tasks == null) {
            tasks.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvMark;
        InitialsRoundView irvStatus;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvMark = itemView.findViewById(R.id.tvMark);
            irvStatus = itemView.findViewById(R.id.irvStatus);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TaskVO taskVO = taskList.get(getLayoutPosition());
                    onClickListener.onClick(taskVO);
                }
            });
        }
    }
}
