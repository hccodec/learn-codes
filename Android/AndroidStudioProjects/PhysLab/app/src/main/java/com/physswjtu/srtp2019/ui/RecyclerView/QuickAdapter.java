/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.ui.RecyclerView;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.physswjtu.srtp2019.widgets.TextView;

import java.util.List;

/**
 * 由 84697 创建
 * 日期为 2019/7/22
 * 工程 PhysLab
 */
public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH> {
    private List<T> mDatas;

    public QuickAdapter(List<T> datas) {
        this.mDatas = datas;
    }

    public abstract int getLayoutId(int viewType);

    @NonNull
    @Override
    public QuickAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return VH.get(parent, getLayoutId(viewType));
    }

    public abstract void convert(VH holder, T data, int position);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        private SparseArray<View> mViews;
        private View mContentView;

        private VH(View v) {
            super(v);
            mContentView = v;
            mViews = new SparseArray<>();
        }

        public static VH get(ViewGroup parent, int layoutId) {
            View contentView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(contentView);
        }

        public <T extends View> T getView(int id) {
            View v = mViews.get(id);
            if (v == null) {
                v = mContentView.findViewById(id);
                mViews.put(id, v);
            }
            return (T) v;
        }

        public void setText(int id, String value) {
            TextView view = getView(id);
            view.setText(value);
        }
    }

}
