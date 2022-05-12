/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.data.Settings;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.physswjtu.srtp2019.R;
import com.physswjtu.srtp2019.SettingsActivity;
import com.physswjtu.srtp2019.widgets.TextView;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_DEV_DATA_SOURCE;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_IS_SILENT;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_PIC_SIZE_HEIGHT;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_PIC_SIZE_WIDTH;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_SERVER_ADDRESS;
import static com.physswjtu.srtp2019.data.Settings.Settings.SERVER_ADDRESS_DEFAULT;

/**
 * 抽屉adapter
 * Created by zly on 2016/3/30.
 * <p>
 * 由 84697 创建修改
 * 日期为 2019/6/24
 * 工程 PhysLab
 */
public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.settingsViewHolder> {

    private SettingsActivity context;

    public SettingsAdapter(SettingsActivity context) {
        super();
        this.context = context;
    }

    private static final int TYPE_DIVIDER = 0;
    private static final int TYPE_DIVIDER2 = 1;
    private static final int TYPE_NORMAL = 2;
    private static final int TYPE_HEADER = 3;
    private static final int TYPE_EDIT = 4;
    private static final int TYPE_NORMAL_PARENT = 20;
    private static final int TYPE_NORMAL_PARENT_VALUE = 21;
    private static final int TYPE_NORMAL_SWITCH = 22;
    private static final int TYPE_NORMAL_CHILD = 23;

    private static String result;
    private static int position;

    public void update(settingsViewHolder holder, int position) {
        switch (position) {
            case 3:
                result = Settings.getBoolean(KEY_DEV_DATA_SOURCE, true) ? "云端" : "本地";
                break;
            case 5:
                result = Settings.getString(KEY_SERVER_ADDRESS, SERVER_ADDRESS_DEFAULT);
                break;
            case 7:
                result = MessageFormat.format("{0} * {1}", Settings.getInt(KEY_PIC_SIZE_WIDTH, -1), Settings.getInt(KEY_PIC_SIZE_HEIGHT, -1));
        }
        SettingsAdapter.position = position;
        notifyItemChanged(holder.getAdapterPosition(), "hbj");
    }

    private List<settingsItem> defaultDataDev = Arrays.asList(
            new SettingsAdapter.settingsHeader("常规设置"),//0
            new SettingsAdapter.settingsNormal("是否静音", R.string.fa_angellist_before, null, SettingsAdapter.TYPE_NORMAL_SWITCH, Settings.getBoolean(KEY_IS_SILENT, false)),//1
            new SettingsAdapter.settingsHeader("开发人员设置"),//2
            new SettingsAdapter.settingsNormal("首页数据来源", R.string.fa_book_before, null, SettingsAdapter.TYPE_NORMAL_PARENT_VALUE, Settings.getBoolean(KEY_DEV_DATA_SOURCE, true) ? "云端" : "本地"),//3
            new SettingsAdapter.settingsDivider(),//4
            new SettingsAdapter.settingsNormal("服务器地址", R.string.fa_adjust_before, null, SettingsAdapter.TYPE_NORMAL_PARENT_VALUE, Settings.getString(KEY_SERVER_ADDRESS, SERVER_ADDRESS_DEFAULT)),//5
            new SettingsAdapter.settingsDivider(),//6
            new SettingsAdapter.settingsNormal("图片宽高值", R.string.fa_play_circle_o_before, null, SettingsAdapter.TYPE_NORMAL_PARENT_VALUE, MessageFormat.format("{0} * {1}", Settings.getInt(KEY_PIC_SIZE_WIDTH, -1), Settings.getInt(KEY_PIC_SIZE_HEIGHT, -1))),//7
            new SettingsAdapter.settingsDivider(),//4
            new SettingsAdapter.settingsNormal("退出开发者模式", R.string.fa_outdent_before, null, SettingsAdapter.TYPE_NORMAL_CHILD),//5
            new SettingsAdapter.settingsDivider(),//6
            new SettingsAdapter.settingsNormal("恢复默认设置", R.string.fa_minus_circle_before, null, SettingsAdapter.TYPE_NORMAL_CHILD)//7
    );

    private List<settingsItem> defaultDataPro = Arrays.asList(
            new SettingsAdapter.settingsHeader("常规设置"),//0
            new SettingsAdapter.settingsNormal("是否静音", R.string.fa_angellist_before, null, SettingsAdapter.TYPE_NORMAL_SWITCH, Settings.getBoolean(KEY_IS_SILENT, false)),//1
            new SettingsAdapter.settingsDivider(),//2
            new SettingsAdapter.settingsNormal("进入开发者模式", R.string.fa_minus_circle_before, null, SettingsAdapter.TYPE_NORMAL_CHILD),//3
            new SettingsAdapter.settingsDivider(),//4
            new SettingsAdapter.settingsNormal("恢复默认设置", R.string.fa_minus_circle_before, null, SettingsAdapter.TYPE_NORMAL_CHILD)//5
    );

    public void init() {
        mData = new ArrayList<>(Settings.getBoolean(Settings.KEY_IS_IN_DEVELOPER_MODE, false) ? defaultDataDev : defaultDataPro);
        notifyDataSetChanged();
    }

    private List<settingsItem> mData;

    public void deleteItem() {
        if (mData == null || mData.isEmpty()) {
            return;
        }
        mData.remove(0);
        notifyItemRemoved(0);
    }

    public void deleteItem(int position) {
        if (mData == null || mData.isEmpty()) {
            return;
        }
        mData.remove(position);
        notifyDataSetChanged();
    }

    public void replaceItem(List<settingsItem> itemList) {
        if (mData == null) {
            mData = itemList;
        }
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        settingsItem settingsItem = mData.get(position);
        if (settingsItem instanceof settingsDivider) return TYPE_DIVIDER;
        else if (settingsItem instanceof settingsDivider2) return TYPE_DIVIDER2;
        else if (settingsItem instanceof settingsNormal) return TYPE_NORMAL;
        else if (settingsItem instanceof settingsHeader) return TYPE_HEADER;
        else if (settingsItem instanceof settingsEdit) return TYPE_EDIT;
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return (mData == null || mData.size() == 0) ? 0 : mData.size();
    }

    @NonNull
    @Override
    public settingsViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        settingsViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_DIVIDER:
                viewHolder = new DividerViewHolder(inflater.inflate(R.layout.settings_divider1, parent, false));
                break;
            case TYPE_DIVIDER2:
                viewHolder = new DividerViewHolder(inflater.inflate(R.layout.settings_divider2, parent, false));
                break;
            case TYPE_HEADER:
                viewHolder = new HeaderViewHolder(inflater.inflate(R.layout.settings_header, parent, false));
                break;
            case TYPE_NORMAL:
                viewHolder = new NormalViewHolder(inflater.inflate(R.layout.settings_item, parent, false));
                break;
            case TYPE_EDIT:
                viewHolder = new EditViewHolder(inflater.inflate(R.layout.settings_edit, parent, false));
                break;
            default:
                throw new RuntimeException(getClass().getCanonicalName() + ": onCreateViewHolder(): 程序内部错误");
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull settingsViewHolder holder, int position) {
        final settingsItem item = mData.get(position);
        System.identityHashCode(mData);
        Log.d("hbj", "刷新了咩");

        if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            final settingsNormal itemNormal = (settingsNormal) item;

            normalViewHolder.description.setVisibility(View.GONE);
            normalViewHolder.arrow.setVisibility(View.GONE);
            normalViewHolder.result.setVisibility(View.GONE);
            normalViewHolder.switchCompat.setVisibility(View.GONE);

            normalViewHolder.icon.setText(itemNormal.iconRes);
            normalViewHolder.name.setText(itemNormal.titleRes);
            if (itemNormal.description != null) {
                normalViewHolder.description.setVisibility(View.VISIBLE);
                normalViewHolder.description.setText(itemNormal.description);
            }
            normalViewHolder.view.setOnClickListener(v -> {
                if (listener != null) listener.itemClick(itemNormal, holder, position);
            });
            switch (itemNormal.type) {
                case TYPE_NORMAL_PARENT_VALUE:
                    normalViewHolder.result.setVisibility(View.VISIBLE);
                    normalViewHolder.result.setText(result != null && SettingsAdapter.position == position ? result : itemNormal.result);
                    result = null;
                    SettingsAdapter.position = 0;
                    normalViewHolder.arrow.setVisibility(View.VISIBLE);
                    break;
                case TYPE_NORMAL_PARENT:
                    normalViewHolder.arrow.setVisibility(View.VISIBLE);
                    break;
                case TYPE_NORMAL_SWITCH:
                    normalViewHolder.switchCompat.setChecked(itemNormal.aBoolean);
                    normalViewHolder.switchCompat.setVisibility(View.VISIBLE);
                    normalViewHolder.switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        if (checkedChangeListener != null)
                            checkedChangeListener.itemCheck(buttonView, isChecked);
                    });
                    break;
            }
        } else if (holder instanceof EditViewHolder) {
            EditViewHolder editViewHolder = (EditViewHolder) holder;
            final settingsEdit itemEdit = (settingsEdit) item;
            editViewHolder.title.setText(MessageFormat.format("{0}: ", itemEdit.title));
            if (itemEdit.hint != null) editViewHolder.editText.setHint(itemEdit.hint);
            editViewHolder.editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 0) {
                        editViewHolder.editTextClear.setVisibility(View.GONE);
                        editViewHolder.confirm.setVisibility(View.GONE);
                        editViewHolder.cancel.setVisibility(View.GONE);
                    } else {
                        editViewHolder.editTextClear.setVisibility(View.VISIBLE);
                        editViewHolder.confirm.setVisibility(View.VISIBLE);
                        editViewHolder.cancel.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            editViewHolder.editTextClear.setOnClickListener(v -> editViewHolder.editText.setText(null));
            //editViewHolder.cancel.setOnClickListener(v ->);
        } else if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            final settingsHeader itemHeader = (settingsHeader) item;
            headerViewHolder.title.setText(itemHeader.title);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull settingsViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (!payloads.isEmpty()) {
            String payload = (String) payloads.get(0);
        }
        onBindViewHolder(holder, position);
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void itemClick(settingsNormal settingsNormal, settingsViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnCheckedChangeListener checkedChangeListener;

    public interface OnCheckedChangeListener {
        void itemCheck(CompoundButton buttonView, boolean isChecked);
    }

    public void setCheckedChangeListener(OnCheckedChangeListener listener) {
        this.checkedChangeListener = listener;
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
            mData.add(toPosition, mData.remove(fromPosition));
            notifyItemMoved(fromPosition, toPosition);
            Log.d("hbj", from + "" + to + "");
            notifyItemRangeChanged(Math.min(fromPosition, toPosition), Math.abs(fromPosition - toPosition) + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------------------item数据模型------------------------------
// drawerlayout item统一的数据模型
    interface settingsItem {
    }

    //有图标和文字的菜单item
    public class settingsNormal implements settingsItem {
        int iconRes;//图标
        String description;//说明
        public String titleRes;//标题
        public int type;//元素种类
        boolean aBoolean;
        String result;

        settingsNormal(String titleRes, int iconRes, String description, int type) {
            this.iconRes = iconRes;
            this.description = description;
            this.titleRes = titleRes;
            this.type = type;
        }


        settingsNormal(String titleRes, int iconRes, String description, int type, boolean aBoolean) {
            this(titleRes, iconRes, description, type);
            this.aBoolean = aBoolean;
        }

        settingsNormal(String titleRes, int iconRes, String description, int type, String result) {
            this(titleRes, iconRes, description, type);
            this.result = result;
        }

    }

    public class settingsEdit implements settingsItem {
        String title;
        String hint;

        public settingsEdit(String title) {
            this.title = title;
        }

        public settingsEdit(String title, String hint) {
            this.title = title;
            this.hint = hint;
        }

    }

    //分割线item
    public class settingsDivider implements settingsItem {
        settingsDivider() {
        }
    }

    //分割线item
    public class settingsDivider2 implements settingsItem {
        settingsDivider2() {
        }
    }

    //有头像、背景、用户名的头部item
    public class settingsHeader implements settingsItem {
        String title;

        settingsHeader(String title) {
            this.title = title;
        }
    }

    //----------------------------------ViewHolder数据模型---------------------------
//抽屉ViewHolder模型
    public class settingsViewHolder extends RecyclerView.ViewHolder {
        EditText et;
        TextView tv;
        TextView cancel;
        TextView confirm;

        settingsViewHolder(View itemView) {
            super(itemView);
            et = itemView.findViewById(R.id.settings_editText);
            tv = itemView.findViewById(R.id.settings_editText_clear);
            cancel = itemView.findViewById(R.id.settings_editText_cancel);
            confirm = itemView.findViewById(R.id.settings_editText_confirm);
        }
    }

    //有图标有文字ViewHolder, 图标为CircleImageView
    public class NormalViewHolder extends settingsViewHolder {
        public View view;
        TextView name;
        TextView icon;
        TextView description;//
        TextView arrow;//
        TextView result;//
        SwitchCompat switchCompat;//

        NormalViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.name_settings);
            icon = itemView.findViewById(R.id.icon_settings);
            description = itemView.findViewById(R.id.description_settings);
            arrow = itemView.findViewById(R.id.arrow_settings);
            result = itemView.findViewById(R.id.result_settings);
            switchCompat = itemView.findViewById(R.id.switchCompat_settings);
        }
    }

    //有图标有文字ViewHolder, 图标为CircleImageView
    public class EditViewHolder extends settingsViewHolder {
        public View view;
        TextView title;
        EditText editText;
        TextView editTextClear;

        EditViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.settings_editText_title);
            editText = itemView.findViewById(R.id.settings_editText);
            editTextClear = itemView.findViewById(R.id.settings_editText_clear);
        }
    }

    //分割线ViewHolder
    public class DividerViewHolder extends settingsViewHolder {

        DividerViewHolder(View itemView) {
            super(itemView);
        }
    }

    //头部ViewHolder
    public class HeaderViewHolder extends settingsViewHolder {

        private TextView title;

        HeaderViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_settings);
        }
    }
}
