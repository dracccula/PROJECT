package kireev.ftshw.project.Courses.GradesList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kireev.ftshw.project.Tools.InitialsRoundView;
import kireev.ftshw.project.R;
import kireev.ftshw.project.TempTools.SetRandom;

import static kireev.ftshw.project.Courses.GradesList.GradesListActivity.mGridMode;

public class AllContactsAdapter extends RecyclerView.Adapter<AllContactsAdapter.ContactViewHolder> {

    private List<ContactVO> mContactVOList;
    private ContactViewHolder contactViewHolder;

    public AllContactsAdapter(List<ContactVO> contactVOList) {
        mContactVOList = contactVOList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grades_list_item, parent, false);
        if (viewType == R.layout.grades_list_item) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grades_list_item, parent, false);
            contactViewHolder = new ContactViewHolder(view);
        }
        if (viewType == R.layout.grades_grid_item) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grades_grid_item, parent, false);
            contactViewHolder = new ContactViewHolder(view);
        }
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        ContactVO contactVO = mContactVOList.get(position);
        holder.irvIcon.setText(contactVO.getContactName());
        holder.irvIcon.setBackgroundColor(SetRandom.SetRandomColor());
        holder.tvContactName.setText(contactVO.getContactName());
        holder.tvPoints.setText(String.valueOf(SetRandom.SetRandomInt()));

    }

    @Override
    public int getItemViewType(int position) {
        return mGridMode ? R.layout.grades_grid_item : R.layout.grades_list_item;
    }

    @Override
    public int getItemCount() {
        return mContactVOList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView tvContactName;
        TextView tvPoints;
        InitialsRoundView irvIcon;

        public ContactViewHolder(View itemView) {
            super(itemView);
            tvContactName = itemView.findViewById(R.id.tvContactName);
            tvPoints = itemView.findViewById(R.id.tvPoints);
            irvIcon = itemView.findViewById(R.id.itvIcon);
        }
    }
}