package kireev.ftshw.project.Courses.ui.gradeslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kireev.ftshw.project.InitialsRoundView;
import kireev.ftshw.project.R;
import kireev.ftshw.project.TempTools.SetRandom;

public class AllContactsAdapter extends RecyclerView.Adapter<AllContactsAdapter.ContactViewHolder> {

    private List<ContactVO> contactVOList;
    private Context mContext;

    public AllContactsAdapter(List<ContactVO> contactVOList, Context mContext) {
        this.contactVOList = contactVOList;
        this.mContext = mContext;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grades_list_item, null);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        ContactVO contactVO = contactVOList.get(position);
        holder.irvIcon.setText(contactVO.getContactName());
        holder.irvIcon.setBackgroundColor(SetRandom.SetRandomColor());
        holder.tvContactName.setText(contactVO.getContactName());
        holder.tvPoints.setText(String.valueOf(SetRandom.SetRandomInt()) + " баллов");
    }

    @Override
    public int getItemCount() {
        return contactVOList.size();
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