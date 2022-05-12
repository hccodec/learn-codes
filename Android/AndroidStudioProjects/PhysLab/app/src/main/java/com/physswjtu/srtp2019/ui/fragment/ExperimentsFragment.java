/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.physswjtu.srtp2019.HomeworkActivity;
import com.physswjtu.srtp2019.R;
import com.physswjtu.srtp2019.Utils.HttpUtil;
import com.physswjtu.srtp2019.data.SRTPBean;
import com.physswjtu.srtp2019.data.Settings.Settings;
import com.physswjtu.srtp2019.ui.RecyclerView.TimeTableAdapter;
import com.physswjtu.srtp2019.widgets.TextView;
import com.physswjtu.srtp2019.widgets.Toast;

import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.physswjtu.srtp2019.SRTPApplication.url;
import static com.physswjtu.srtp2019.Utils.HttpUtil.SUCCESS;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ALERT;
import static com.physswjtu.srtp2019.Utils.SoundUtil.NOTIFICATION2;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ding;

/**
 * 由 84697 创建
 * 日期为 2019/7/2
 * 工程 PhysLab
 */
public class ExperimentsFragment extends MyFragment {

    private static String ARG_PARAM = "param_key";
    private static Bundle bundle;
    private View root;
    private TextView view;
    private TextView view2;
    private String mParam;
    private String mTime;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Activity mActivity;
    private Context context;
    private mHandler handler;
    private mHandler handler2;
    private RecyclerView recyclerView;
    private static TimeTableAdapter adapter;

    private HttpUtil httpUtil = new HttpUtil();

    @Override
    public void update(@Nullable Object... objects) {
        if (view2 != null)
            view2.setText(MessageFormat.format("{0}", new Date(System.currentTimeMillis()).toString()));
        else Log.e("hbj", "要报空指针异常了！！！");
        mTime = new Date(System.currentTimeMillis()).toString();
        try {
            getStudentInformation();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        bundle.putString("time", mTime);//切换Fragment时能够保存数据
        handler.sendEmptyMessage(12);
    }

    private static class mHandler extends Handler {
        private final WeakReference<ExperimentsFragment> mTarget;
        Context context;

        mHandler(ExperimentsFragment timeTableFragment, Context context) {
            mTarget = new WeakReference<>(timeTableFragment);
            this.context = context; // Fragment 的上下文传进 handleMessage
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ExperimentsFragment fragment = mTarget.get();
            String customResponse = msg.getData().getString("customResponse");
            if (msg.what == 12) {
                fragment.swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(context, "刷新完成", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == SUCCESS) {
                Log.d("hbj", customResponse);
                SRTPBean.StudentChooseExperiments studentChooseExperiment = new SRTPBean.StudentChooseExperiments(customResponse);
                if (studentChooseExperiment.getmData() == null) Log.d("hbj", "暂时是null...");
                adapter.updateData(studentChooseExperiment.getmData());
            } else ding(context, ALERT);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //如果要获取Activity对象，是在onAttach()中将Context对象强转为Activity对象
        // 而不建议调用getActivity()
        this.context = context; //Activity 的上下文传进该 Fragment
        handler = new mHandler(this, context);
        handler2 = new mHandler(this, context);
        mActivity = (Activity) context;
        mParam = getArguments() != null ? getArguments().getString(ARG_PARAM) : ARG_PARAM;
        mTime = getArguments() != null ? getArguments().getString("time") : "0";

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_experiment, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout);
        view = root.findViewById(R.id.title_time_table);
        view2 = root.findViewById(R.id.time_time_table);
        recyclerView = root.findViewById(R.id.recycler_view_time_table);
        adapter = new TimeTableAdapter();
        adapter.setOnItemClickListener(new TimeTableAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String[] s, int position) {
                Toast.makeText(context, "点击的是元素 " + position, Toast.LENGTH_SHORT).show();
//                CameraUtil.askForDifferentCameras(context);
                startActivity(new Intent(getActivity(), HomeworkActivity.class));
            }

            @Override
            public void omItemLongClick(View view, int position) {
                Toast.makeText(context, "长按的是元素 " + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mActivity, R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(this::update);
        view.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "font/FZDHTJW.TTF"));
        view.setText(String.format("您当前的%s信息如下：", mParam));
        view2.setText(MessageFormat.format("{0}", mTime));
        super.onActivityCreated(savedInstanceState);
    }


    private void getStudentInformation() throws MalformedURLException {
        if (Settings.getBoolean(Settings.KEY_DEV_DATA_SOURCE, true)) {
            if (!Settings.getString(Settings.KEY_USER_ID, Settings.ERROR).equals(Settings.ERROR))
                httpUtil.get(url(SRTPBean.StudentChooseExperiments.getInterface(Settings.getString(Settings.KEY_USER_ID, Settings.ERROR))), handler2);
        } else {
            android.widget.Toast.makeText(mActivity, "注意，现在的数据是本地模拟数据", Toast.LENGTH_SHORT).show();
            ding(mActivity, NOTIFICATION2);
            ArrayList<String[]> mData = new ArrayList<>(); //展示数据
            mData.add(0, new String[]{"asd1", "asd", "asd", "asd", "asd", "asd", "asd", "asd"});
            mData.add(1, new String[]{"asd2", "asd", "asd", "asd", "asd", "asd", "asd", "asd"});
            mData.add(2, new String[]{"asd3", "asd", "asd", "asd", "asd", "asd", "asd", "asd"});
            mData.add(3, new String[]{"asd4", "asd", "asd", "asd", "asd", "asd", "asd", "asd"});
            mData.add(4, new String[]{"asd5", "asd", "asd", "asd", "asd", "asd", "asd", "asd"});
            mData.add(5, new String[]{"asd6", "asd", "asd", "asd", "asd", "asd", "asd", "asd"});
            mData.add(6, new String[]{"asd7", "asd", "asd", "asd", "asd", "asd", "asd", "asd"});
            mData.add(7, new String[]{"asd8", "asd", "asd", "asd", "asd", "asd", "asd", "asd"});
            adapter.updateData(mData);
        }
    }

    static ExperimentsFragment newInstance(String str, String a) {
        ExperimentsFragment frag = new ExperimentsFragment();
        bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        bundle.putString("time", a);
        //如果在创建Fragment时要传入参数，必须要通过setArguments(Bundle bundle)方式添加
        // 而不建议通过为Fragment添加带参数的构造函数
        //因为通过setArguments()方式添加，在由于内存紧张导致Fragment被系统杀掉并恢复（re-instantiate）时能保留这些数据。
        frag.setArguments(bundle);
        return frag;
    }
}
