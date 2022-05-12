package com.physswjtu.srtp2019.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.physswjtu.srtp2019.R;
import com.physswjtu.srtp2019.widgets.TextView;
import com.physswjtu.srtp2019.widgets.Toast;

import java.lang.ref.WeakReference;

import static com.physswjtu.srtp2019.Utils.BitmapUtil.drawable2Bitmap;

/**
 * 由 84697 创建
 * 日期为 2019/7/2
 * 工程 PhysLab
 */
public class CourseSelectFragment extends MyFragment {

    private static String ARG_PARAM = "param_key";

    private static Bundle bundle;
    private View root;
    private String mParam;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Activity mActivity;
    private Context context;
    private ImageView imageView;
    private mHandler handler;
    private Bitmap bitmap;

    @Override
    public void update(@Nullable Object... objects) {
        if (objects != null)
            if (imageView != null && objects.length > 0) {
                imageView.setImageBitmap((Bitmap) objects[0]);
                bundle.putParcelable("time", (Bitmap) objects[0]);//切换Fragment时能够保存数据
            }

        handler.sendEmptyMessage(12);
    }

    private static class mHandler extends Handler {
        private final WeakReference<CourseSelectFragment> mTarget;
        Context context;

        mHandler(CourseSelectFragment timeTableFragment, Context context) {
            mTarget = new WeakReference<>(timeTableFragment);
            this.context = context; // Fragment 的上下文传进 handleMessage
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CourseSelectFragment fragment = mTarget.get();
            Log.d("hbj", "成功传到 CourseSelectFragment 的 handleMessage, msg.what 是" + msg.what);
            if (msg.what == 12) {
                fragment.swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(context, "刷新完成", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //如果要获取Activity对象，是在onAttach()中将Context对象强转为Activity对象
        // 而不建议调用getActivity()
        this.context = context; //Activity 的上下文传进该 Fragment
        handler = new mHandler(this, context);
        bitmap = drawable2Bitmap(context, R.drawable.badge_phys_school);
        mActivity = (Activity) context;
        mParam = getArguments() != null ? getArguments().getString(ARG_PARAM) : ARG_PARAM;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_course_select, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        TextView view = root.findViewById(R.id.title_course_select);
        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mActivity, R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(() -> update(bitmap));
        view.setText(mParam);
        imageView = root.findViewById(R.id.capture_image);
        imageView.setImageBitmap(drawable2Bitmap(context, R.drawable.badge_phys_school));
        super.onActivityCreated(savedInstanceState);
    }

    static CourseSelectFragment newInstance(String str) {
        CourseSelectFragment frag = new CourseSelectFragment();
        bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        //如果在创建Fragment时要传入参数，必须要通过setArguments(Bundle bundle)方式添加
        // 而不建议通过为Fragment添加带参数的构造函数
        //因为通过setArguments()方式添加，在由于内存紧张导致Fragment被系统杀掉并恢复（re-instantiate）时能保留这些数据。
        frag.setArguments(bundle);
        return frag;
    }

}
