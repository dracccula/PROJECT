package kireev.ftshw.project.Courses.Rating.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        Double dMark = Double.valueOf(taskVO.getTasksMark());
        Double dMaxMark = Double.valueOf(taskVO.getTaskMax_score());
        String mark = new DecimalFormat("##.##").format(dMark);
        String maxMark = new DecimalFormat("##.##").format(dMaxMark);
        holder.tvMark.setText(mark + "/" + maxMark);
        String deadlineDate = taskVO.getTaskDeadline_date();
        if (deadlineDate != null) {
            holder.tvDeadline.setText(deadlineDate);
        } else {
            holder.tvDeadline.setVisibility(View.INVISIBLE);
        }
        switch (taskVO.getTasksStatus()) {
            case "accepted":
                holder.irvStatus.setBackgroundColor(context.getResources().getColor(R.color.colorAcceptedStatus));
                break;
            case "failed":
                holder.irvStatus.setBackgroundColor(context.getResources().getColor(R.color.colorFailedStatus));
                break;
            default:
                holder.irvStatus.setBackgroundColor(context.getResources().getColor(R.color.colorNewStatus));
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
        TextView tvTitle, tvMark, tvDeadline;
        InitialsRoundView irvStatus;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvMark = itemView.findViewById(R.id.tvMark);
            tvDeadline = itemView.findViewById(R.id.tvDeadline);
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
