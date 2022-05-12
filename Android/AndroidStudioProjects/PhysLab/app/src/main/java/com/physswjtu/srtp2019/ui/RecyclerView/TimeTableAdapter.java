/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.ui.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.physswjtu.srtp2019.R;
import com.physswjtu.srtp2019.widgets.TextView;

import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * 由 84697 创建
 * 日期为 2019/7/21
 * 工程 PhysLab
 */
//adapter
public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.ViewHolder> {
    private ArrayList<String[]> mData; //展示数据
    private OnItemClickListener onItemClickListener; //事件回调监听

    public TimeTableAdapter() {
    }

    public void updateData(ArrayList<String[]> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void addItem() {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        notifyItemInserted(0);
    }

    public void deleteItem() {
        if (mData == null || mData.isEmpty()) {
            return;
        }
        mData.remove(0);
        notifyItemRemoved(0);
    }

    //点击回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, String[] s, int position);

        void omItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(TimeTableAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化展示的View
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_time_table_item, parent, false);
        //实例化ViewHolder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //绑定数据
        // TODO: 2019/7/25 这里控制格式
        holder.expName.setText(MessageFormat.format("实验名: {0}", mData.get(position)[0]));
        holder.grade.setText(MessageFormat.format("成绩: {0}", mData.get(position)[2]));
        holder.week_day_time.setText(MessageFormat.format("第{0}周星期{1}第{2}讲",
                mData.get(position)[1], mData.get(position)[5], mData.get(position)[3]));
        holder.roomId.setText(MessageFormat.format("X{0}", mData.get(position)[6]));

        //设置点击事件
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                int pos = holder.getLayoutPosition();
                onItemClickListener.onItemClick(holder.itemView, mData.get(pos), pos);
            }
        });
        holder.itemView.setOnLongClickListener(v -> {
            if (onItemClickListener != null) {
                int pos = holder.getLayoutPosition();
                onItemClickListener.omItemLongClick(holder.itemView, pos);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView expName;
        TextView grade;
        TextView week_day_time;
        TextView roomId;

        ViewHolder(View itemView) {
            super(itemView);
            expName = itemView.findViewById(R.id.expName);
            grade = itemView.findViewById(R.id.grade);
            week_day_time = itemView.findViewById(R.id.week_day_time);
            roomId = itemView.findViewById(R.id.roomId);
        }
    }
}
