/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.data;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.physswjtu.srtp2019.MainActivity;
import com.physswjtu.srtp2019.R;
import com.physswjtu.srtp2019.widgets.RoundImageView;
import com.physswjtu.srtp2019.widgets.TextView;
import com.physswjtu.srtp2019.widgets.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.physswjtu.srtp2019.Utils.BitmapUtil.drawable2Bitmap;

/**
 * 抽屉adapter
 * Created by zly on 2016/3/30.
 * <p>
 * 由 84697 创建修改
 * 日期为 2019/6/24
 * 工程 PhysLab
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {

    private MainActivity context;

    public DrawerAdapter(MainActivity context) {
        super();
        this.context = context;
    }

    public void init(boolean a) {
        Log.d("hbj", getClass().getName() + (a ? "开发者模式" : "产品模式"));
        dataList = new ArrayList<>(a ? dataDev : dataPro);
        notifyDataSetChanged();
    }

    private static final int TYPE_DIVIDER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_HEADER = 2;

    private static String UserName;
    private static Bitmap Icon;
    private static Bitmap Bg;

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setIcon(Bitmap icon) {
        Icon = icon;
    }

    public void setBg(Bitmap bg) {
        Bg = bg;
    }

    private List<DrawerItem> dataDev = Arrays.asList(
            new DrawerItemHeader("默认用户名", Icon, Bg),
            new DrawerItemNormal(R.string.fa_angellist_before, R.string.action_profile),
            new DrawerItemNormal(R.string.fa_book_before, R.string.action_contact_teacher),
            new DrawerItemDivider(),
            new DrawerItemNormal(R.string.fa_adjust_before, R.string.action_test),
            new DrawerItemNormal(R.string.fa_play_circle_o_before, R.string.action_video),
            new DrawerItemNormal(R.string.fa_play_circle_o_before, R.string.action_live),
            new DrawerItemNormal(R.string.fa_qrcode_before, R.string.action_create_qr),
            new DrawerItemNormal(R.string.fa_qrcode_before, R.string.action_scan_qr)/*,
            new DrawerItemNormal(R.string.fa_refresh_before, R.string.action_built_in_browser)*/
    );
    private List<DrawerItem> dataPro = Arrays.asList(
            new DrawerItemHeader("默认用户名", Icon, Bg),
            new DrawerItemNormal(R.string.fa_angellist_before, R.string.action_profile),
            new DrawerItemNormal(R.string.fa_book_before, R.string.action_contact_teacher),
            new DrawerItemDivider(),
            new DrawerItemNormal(R.string.fa_qrcode_before, R.string.action_create_qr),
            new DrawerItemNormal(R.string.fa_qrcode_before, R.string.action_scan_qr)/*,
            new DrawerItemNormal(R.string.fa_refresh_before, R.string.action_built_in_browser)*/
    );

    private List<DrawerItem> dataList = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {
        DrawerItem drawerItem = dataList.get(position);
        if (drawerItem instanceof DrawerItemDivider) return TYPE_DIVIDER;
        else if (drawerItem instanceof DrawerItemNormal) return TYPE_NORMAL;
        else if (drawerItem instanceof DrawerItemHeader) return TYPE_HEADER;
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return (dataList == null || dataList.size() == 0) ? 0 : dataList.size();
    }

    @NonNull
    @Override
    public DrawerViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        DrawerViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_DIVIDER:
                viewHolder = new DividerViewHolder(inflater.inflate(R.layout.drawer_item_divider, parent, false));
                break;
            case TYPE_HEADER:
                viewHolder = new HeaderViewHolder(inflater.inflate(R.layout.drawer_item_header, parent, false));
                break;
            case TYPE_NORMAL:
                viewHolder = new NormalViewHolder(inflater.inflate(R.layout.drawer_item_normal, parent, false));
                break;
            default:
                viewHolder = null;
                Log.e("hbj", "DrawerAdapter: onCreateViewHolder(): 程序内部错误");
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerViewHolder holder, int position) {
        final DrawerItem item = dataList.get(position);
        if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            final DrawerItemNormal itemNormal = (DrawerItemNormal) item;
            normalViewHolder.iv.setText(itemNormal.iconRes);
            normalViewHolder.tv.setText(itemNormal.titleRes);

            normalViewHolder.view.setOnClickListener(v -> {
                if (listener != null) listener.itemClick(itemNormal);
            });
        } else if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            final DrawerItemHeader itemHeader = (DrawerItemHeader) item;
            if (UserName == null) Log.d("hbj", "DrawerAdapter: " + "UserName不存在");
            else Log.d("hbj", "DrawerAdapter: " + "username 的值为" + Icon);
            if (Bg == null) Log.d("hbj", "DrawerAdapter: " + "Bg不存在");
            else Log.d("hbj", "DrawerAdapter: " + "bg 的值为" + Icon);
            if (Icon == null) Log.d("hbj", "DrawerAdapter: " + "Icon不存在");
            else Log.d("hbj", "DrawerAdapter: " + "icon 的值为" + Icon);

            headerViewHolder.tv_login.setText(UserName == null ? "游客" : UserName);
            headerViewHolder.civ_icon.setImageBitmap(Icon == null ? drawable2Bitmap(context, R.mipmap.ic_launcher_round) : Icon);
            headerViewHolder.bg.setImageBitmap(Bg == null ? drawable2Bitmap(context, R.drawable.timg) : Bg);

            headerViewHolder.bg.setOnClickListener(v -> {
                if (listener != null) listener.itemClick(v, itemHeader);
            });
            headerViewHolder.bg.setOnLongClickListener(v -> {
                Toast.makeText(context, "长按了背景图片", Toast.LENGTH_SHORT).show();
                return true;
            });
            headerViewHolder.civ_icon.setOnClickListener(v -> {
                if (listener != null) listener.itemClick(v, itemHeader);
            });
            headerViewHolder.tv_login.setOnClickListener(v -> {
                if (listener != null) listener.itemClick(v, itemHeader);
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (!payloads.isEmpty()) {
            String payload = (String) payloads.get(0);
        }
        onBindViewHolder(holder, position);
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void itemClick(DrawerItemNormal drawerItemNormal);

        void itemClick(View v, DrawerItemHeader drawerItemHeader);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public void itemMove(int from, int to) {
        try {
            if (from == to) {
                from = 0;
                to = 3;
            } else {
                if ((from >= 0 && from <= 3) && (to < 0 || to > 3)) to = 3;
                if ((to >= 0 && to <= 3) && (from < 0 || from > 3)) from = 0;
            }
            if (from > to) {
                from = from - to;
                to = from + to;
                from = to - from;
            }
            int fromPosition = from * 2 + 1;
            int toPosition = to * 2 + 1;
            dataList.add(toPosition, dataList.remove(fromPosition));
            notifyItemMoved(fromPosition, toPosition);
            Log.d("hbj", from + "" + to + "");
            notifyItemRangeChanged(Math.min(fromPosition, toPosition), Math.abs(fromPosition - toPosition) + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------------------item数据模型------------------------------
    // drawerlayout item统一的数据模型
    private interface DrawerItem {
    }

    //有图片和文字的菜单item
    public class DrawerItemNormal implements DrawerItem {
        int iconRes;
        public int titleRes;

        DrawerItemNormal(int iconRes, int titleRes) {
            this.iconRes = iconRes;
            this.titleRes = titleRes;
        }
    }

    //分割线item
    public class DrawerItemDivider implements DrawerItem {
        DrawerItemDivider() {
        }
    }

    //有头像、背景、用户名的头部item
    public class DrawerItemHeader implements DrawerItem {
        String userName;
        Bitmap icon;
        Bitmap bg;


        DrawerItemHeader(String username, Bitmap icon, Bitmap bg) {
            this.userName = username;
            this.icon = icon;
            this.bg = bg;
        }
    }

    //----------------------------------ViewHolder数据模型---------------------------
    //抽屉ViewHolder模型
    class DrawerViewHolder extends RecyclerView.ViewHolder {
        DrawerViewHolder(View itemView) {
            super(itemView);
        }
    }

    //有图标有文字ViewHolder, 图标为CircleImageView
    public class NormalViewHolder extends DrawerViewHolder {
        public View view;
        TextView tv;
        TextView iv;

        NormalViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }

    //分割线ViewHolder
    public class DividerViewHolder extends DrawerViewHolder {

        DividerViewHolder(View itemView) {
            super(itemView);
        }
    }

    //头部ViewHolder
    public class HeaderViewHolder extends DrawerViewHolder {

        private ImageView bg;
        private RoundImageView civ_icon;
        private TextView tv_login;
        public View view;

        HeaderViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            bg = itemView.findViewById(R.id.bg);
            civ_icon = itemView.findViewById(R.id.civ_icon);
            tv_login = itemView.findViewById(R.id.tv_login);
        }
    }
}
