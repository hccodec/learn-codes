/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.physswjtu.srtp2019.BaseActivity;
import com.physswjtu.srtp2019.HomeworkActivity;
import com.physswjtu.srtp2019.R;
import com.physswjtu.srtp2019.Utils.CameraUtil;
import com.physswjtu.srtp2019.widgets.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.physswjtu.srtp2019.Utils.BitmapUtil.createThumbnail;

/**
 * 由 84697 创建
 * 日期为 2019/8/9
 * 工程 PhysLab
 */
public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.homeworkHolder> {

    private BaseActivity baseActivity;
    private List<homeworkItem> mData = new ArrayList<>(Collections.singletonList(new homeworkAddItem()));

    public void add(Uri uri) {
        mData.add(getItemCount() - 1, new homeworkPicItem(uri));
        notifyItemInserted(getItemCount() - 1);
    }

    public void del(int position) {
        mData.remove(position);
        HomeworkActivity.fileList.remove(position);
        HomeworkActivity.list2.remove(position);
        notifyItemRemoved(position);
    }

    public HomeworkAdapter(BaseActivity baseActivity) {
        super();
        this.baseActivity = baseActivity;
    }

    private static final int TYPE_PIC = 0;
    private static final int TYPE_ADD = 1;

    @Override
    public int getItemViewType(int position) {
        homeworkItem item = mData.get(position);
        if (item instanceof homeworkPicItem) return TYPE_PIC;
        else if (item instanceof homeworkAddItem) return TYPE_ADD;
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public homeworkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(baseActivity);
        switch (viewType) {
            case TYPE_PIC:
                return new homeworkPicHolder(inflater.inflate(R.layout.find_item, parent, false));
            case TYPE_ADD:
                return new homeworkAddHolder(inflater.inflate(R.layout.find_new_item, parent, false));
            default:
                throw new RuntimeException(getClass().getCanonicalName() + ": onCreateViewHolder(): 程序内部错误");
        }
    }


    public interface OnItemClickListener {
        void itemClick(homeworkItem item, homeworkHolder holder, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull homeworkHolder holder, int position) {
        final homeworkItem item = mData.get(position);
        if (holder instanceof homeworkPicHolder) {
            homeworkPicHolder picHolder = (homeworkPicHolder) holder;
            Bitmap photo1 = null;
            try {
                photo1 = BitmapFactory.decodeStream(baseActivity.getContentResolver().openInputStream(((homeworkPicItem) item).uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (photo1 != null)
                picHolder.imageView.setImageBitmap(createThumbnail(photo1, 100, 200));
            picHolder.textView.setOnClickListener(v -> del(holder.getAdapterPosition()));
            holder.itemView.setOnClickListener(v -> HomeworkActivity.picPreview(baseActivity, position));
        } else if (holder instanceof homeworkAddHolder)
            holder.itemView.setOnClickListener(v -> CameraUtil.askForDifferentMethods(baseActivity));
    }

    @Override
    public int getItemCount() {
        return (mData == null || mData.size() == 0) ? 0 : mData.size();
    }

    public interface homeworkItem {
    }

    public class homeworkPicItem implements homeworkItem {
        Uri uri;

        homeworkPicItem(Uri uri) {
            this.uri = uri;
        }
    }

    public class homeworkAddItem implements homeworkItem {
        homeworkAddItem() {
        }
    }

    public class homeworkHolder extends RecyclerView.ViewHolder {
        homeworkHolder(View itemView) {
            super(itemView);
        }
    }

    public class homeworkPicHolder extends homeworkHolder {
        ImageView imageView;
        TextView textView;

        homeworkPicHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.pics);
            textView = v.findViewById(R.id.pics_del);
        }
    }

    public class homeworkAddHolder extends homeworkHolder {

        homeworkAddHolder(View v) {
            super(v);
        }
    }
}
