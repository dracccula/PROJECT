package kireev.ftshw.project.Events;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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

import kireev.ftshw.project.App;
import kireev.ftshw.project.R;

public class ArchiveEventsAdapter extends RecyclerView.Adapter<ArchiveEventsAdapter.ArchiveEventsViewHolder> {

    private List<ArchiveEventsVO> archiveEventsList;
    private Context context;

    ArchiveEventsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ArchiveEventsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.events_archive_item, viewGroup, false);
        return new ArchiveEventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchiveEventsViewHolder archiveEventsViewHolder, int i) {
        ArchiveEventsVO archiveEventsVO = archiveEventsList.get(i);
        archiveEventsViewHolder.tvArchiveEventTitle.setText(archiveEventsVO.getTitle());
        if (archiveEventsVO.getEventTypeName() != null) {
            archiveEventsViewHolder.tvArchiveEventType.setText(archiveEventsVO.getEventTypeName());
            if (archiveEventsVO.getEventTypeColor() != null) {
                switch (archiveEventsVO.getEventTypeColor()) {
                    case "purple":
                        archiveEventsViewHolder.ivArchiveEventImage.setImageResource(R.drawable.ic_purple_event);
                        break;
                    case "orange":
                        archiveEventsViewHolder.ivArchiveEventImage.setImageResource(R.drawable.ic_orange_event);
                        break;
                    default:
                        archiveEventsViewHolder.ivArchiveEventImage.setImageResource(R.drawable.ic_null_event);
                }
            }
        } else {
            archiveEventsViewHolder.tvArchiveEventType.setText("Мероприятие");
            archiveEventsViewHolder.ivArchiveEventImage.setImageResource(R.drawable.ic_null_event);
        }
    }

    @Override
    public int getItemCount() {
        return archiveEventsList.size();
    }

    void setItems(List<ArchiveEventsVO> archiveEvents) {
        this.archiveEventsList = archiveEvents;
        notifyDataSetChanged();
    }

    class ArchiveEventsViewHolder extends RecyclerView.ViewHolder {
        TextView tvArchiveEventTitle, tvArchiveEventType;
        ImageView ivArchiveEventImage;

        ArchiveEventsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvArchiveEventType = itemView.findViewById(R.id.tvArchiveEventType);
            ivArchiveEventImage = itemView.findViewById(R.id.ivArchiveEventImage);
            tvArchiveEventTitle = itemView.findViewById(R.id.tvArchiveEventTitle);
        }
    }

    public static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private final int[] ATTRS = new int[]{android.R.attr.listDivider};

        private Drawable divider;
        private int sizeInDp = 72;

        /**
         * Default divider will be used
         */
        public DividerItemDecoration(Context context) {
            final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
            divider = styledAttributes.getDrawable(0);
            styledAttributes.recycle();
        }

        /**
         * Custom divider will be used
         */
        DividerItemDecoration(Context context, int resId) {
            divider = ContextCompat.getDrawable(context, resId);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

            float scale = App.getContext().getResources().getDisplayMetrics().density;
            int dpAsPixels = (int) (sizeInDp*scale + 0.5f);

//            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(dpAsPixels, top, right, bottom);
                divider.draw(c);
            }
        }
    }
}
