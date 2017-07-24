package com.esp.hangul.Home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.esp.hangul.R;
import com.esp.hangul.StudyDetail.StudyDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class StudyItemAdapter extends RecyclerView.Adapter<StudyItemAdapter.StudyItemViewHolder>{

    private Context context;
    private List<StudyItem> itemList;
    private int parentWidth;
    private int parentHeight;

    public StudyItemAdapter(Context context, int parentWidth, int parentHeight) {
        this.context = context;
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        itemList = new ArrayList<StudyItem>();
        initItemList();
    }

    private void initItemList() {
        itemList.add(new StudyItem("Ẩm thực"));
        itemList.add(new StudyItem("Màu sắc", R.drawable.color));
        itemList.add(new StudyItem("Địa điểm", R.drawable.place));
        itemList.add(new StudyItem("Ẩm thực"));
        itemList.add(new StudyItem("Ẩm thực"));
        itemList.add(new StudyItem("Ẩm thực"));
        itemList.add(new StudyItem("Ẩm thực"));
        itemList.add(new StudyItem("Ẩm thực"));
        itemList.add(new StudyItem("Ẩm thực"));
        itemList.add(new StudyItem("Ẩm thực"));
        itemList.add(new StudyItem("Ẩm thực"));


    }

    @Override
    public StudyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.study_item, null);
        return new StudyItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudyItemViewHolder holder, int position) {
        StudyItem item = itemList.get(position);
        holder.titleView.setText(item.getTitle());
        if (item.getImageResId() != 0) {
            holder.imageView.setImageResource(item.getImageResId());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudyDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class StudyItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleView;

        public StudyItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.study_item_image);
            titleView = (TextView) itemView.findViewById(R.id.study_item_title);
        }
    }

}
