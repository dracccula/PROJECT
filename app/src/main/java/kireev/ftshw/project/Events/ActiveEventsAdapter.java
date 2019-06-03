package kireev.ftshw.project.Events;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kireev.ftshw.project.R;

public class ActiveEventsAdapter extends RecyclerView.Adapter<ActiveEventsAdapter.ActiveEventsViewHolder> {

    private List<ActiveEventsVO> activeEventsList;
    private Context context;

    ActiveEventsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ActiveEventsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.events_active_item, viewGroup, false);
        return new ActiveEventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveEventsViewHolder activeEventsViewHolder, int i) {
        ActiveEventsVO activeEventsVO = activeEventsList.get(i);
        activeEventsViewHolder.tvActiveEventTitle.setText(activeEventsVO.getTitle());
        String startDate = activeEventsVO.getDateStart();
        String endDate = activeEventsVO.getDateEnd();
        String formattedStartDate = "Отсутствует";
        String formattedEndDate = "Отсутствует";
        if (startDate != null) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            try {
                Date dStartDate = inputFormat.parse(startDate);
                Date dEndDate = inputFormat.parse(endDate);
                SimpleDateFormat newDate = new SimpleDateFormat("MMM yyyy", Locale.getDefault());//set format of new date
                if (dStartDate.getYear() == dEndDate.getYear()) {
                    SimpleDateFormat newStartDate = new SimpleDateFormat("MMM", Locale.getDefault());//set format of new date
                    formattedStartDate = newStartDate.format(dStartDate);
                } else {

                    formattedStartDate = newDate.format(dStartDate);
                }
                formattedEndDate = newDate.format(dEndDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            activeEventsViewHolder.tvActiveEventDate.setText(formattedStartDate.toUpperCase() + " – " + formattedEndDate.toUpperCase());
        } else {
            activeEventsViewHolder.tvActiveEventDate.setVisibility(View.INVISIBLE);
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(4)).error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_placeholder_image);
        Glide.with(context)
                .load("https://picsum.photos/id/" + activeEventsVO.getTitle().length() + "/100/150")
                .apply(requestOptions)
                .into(activeEventsViewHolder.ivActiveEventImage);
        if (activeEventsVO.getEventTypeName() != null) {
            activeEventsViewHolder.tvActiveEventType.setText(activeEventsVO.getEventTypeName().toUpperCase());
            if (activeEventsVO.getEventTypeColor() != null) {
                switch (activeEventsVO.getEventTypeColor()) {
                    case "purple":
                        activeEventsViewHolder.tvActiveEventType.setBackgroundResource(R.drawable.purple_background);
                        break;
                    case "orange":
                        activeEventsViewHolder.tvActiveEventType.setBackgroundResource(R.drawable.orange_background);
                        break;
                    default:
                        activeEventsViewHolder.tvActiveEventType.setBackgroundResource(R.drawable.grey_background);
                }
            }
        } else {
            activeEventsViewHolder.tvActiveEventType.setText("Мероприятие".toUpperCase());
            activeEventsViewHolder.tvActiveEventType.setBackgroundResource(R.drawable.grey_background);
        }
    }

    @Override
    public int getItemCount() {
        return activeEventsList.size();
    }

    void setItems(List<ActiveEventsVO> activeEvents) {
        this.activeEventsList = activeEvents;
        notifyDataSetChanged();
    }

    class ActiveEventsViewHolder extends RecyclerView.ViewHolder {
        TextView tvActiveEventTitle, tvActiveEventDate, tvActiveEventType;
        ImageView ivActiveEventImage;

        ActiveEventsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvActiveEventType = itemView.findViewById(R.id.tvActiveEventType);
            ivActiveEventImage = itemView.findViewById(R.id.ivActiveEventImage);
            tvActiveEventTitle = itemView.findViewById(R.id.tvActiveEventTitle);
            tvActiveEventDate = itemView.findViewById(R.id.tvActiveEventDate);
        }
    }
}
