/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.data.Settings;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
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
import java.util.Collections;
import java.util.List;

import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_DEV_DATA_SOURCE;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_IS_SILENT;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_PIC_SIZE_HEIGHT;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_PIC_SIZE_WIDTH;
import static com.physswjtu.srtp2019.data.Settings.Settings.SERVER_ADDRESS_DEFAULT;

/**
 * 抽屉adapter
 * Created by zly on 2016/3/30.
 * <p>
 * 由 84697 创建修改
 * 日期为 2019/6/24
 * 工程 PhysLab
 */
public class SettingsAdapter2 extends RecyclerView.Adapter<SettingsAdapter2.SettingsViewHolder> {

    private static final int TYPE_DIVIDER = 0;
    private static final int TYPE_DIVIDER2 = 1;
    private static final int TYPE_NORMAL = 2;
    private static final int TYPE_HEADER = 3;
    private static final int TYPE_EDIT = 4;

    private static final int TYPE_NORMAL_PARENT = 20;
    private static final int TYPE_NORMAL_PARENT_VALUE = 21;
    private static final int TYPE_NORMAL_SWITCH = 22;
    private static final int TYPE_NORMAL_CHILD = 23;
    private Context mContext;
    private List<Boolean> mGroupItemStatus = new ArrayList<>(); // 保存一级标题的开关状态
    private boolean aBoolean;
    private String results;
    private List<DataListTree<settingsItem, settingsItem>> mData = new ArrayList<>(Arrays.asList(
            new DataListTree<>(new SettingsAdapter2.settingsHeader("常规设置"),
                    Collections.emptyList()),
            new DataListTree<>(new SettingsAdapter2.settingsNormal("是否静音", R.string.fa_angellist_before, null, SettingsAdapter2.TYPE_NORMAL_SWITCH, Settings.getBoolean(KEY_IS_SILENT, false)),
                    Collections.emptyList()),
            new DataListTree<>(new SettingsAdapter2.settingsHeader("开发人员设置"),
                    Collections.emptyList()),
            new DataListTree<>(new SettingsAdapter2.settingsNormal("首页数据来源", R.string.fa_book_before, null, SettingsAdapter2.TYPE_NORMAL_PARENT_VALUE, Settings.getBoolean(KEY_DEV_DATA_SOURCE, true) ? "云端" : "本地"),
                    Arrays.asList(
                            new SettingsAdapter2.settingsDivider(),
                            new SettingsAdapter2.settingsNormal("云端", null, SettingsAdapter2.TYPE_NORMAL_CHILD),
                            new SettingsAdapter2.settingsDivider(),
                            new SettingsAdapter2.settingsNormal("本地", null, SettingsAdapter2.TYPE_NORMAL_CHILD))),
            new DataListTree<>(new SettingsAdapter2.settingsDivider(),
                    Collections.emptyList()),
            new DataListTree<>(new SettingsAdapter2.settingsNormal("服务器地址", R.string.fa_adjust_before, null, SettingsAdapter2.TYPE_NORMAL_PARENT_VALUE, Settings.getString(Settings.KEY_SERVER_ADDRESS, SERVER_ADDRESS_DEFAULT)),
                    Arrays.asList(
                            new SettingsAdapter2.settingsDivider(),
                            new settingsEdit("请输入服务器地址", "", InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT))),
            new DataListTree<>(new SettingsAdapter2.settingsDivider(),
                    Collections.emptyList()),
            new DataListTree<>(new SettingsAdapter2.settingsNormal("图片宽高值", R.string.fa_play_circle_o_before, null, SettingsAdapter2.TYPE_NORMAL_PARENT_VALUE, Settings.getInt(KEY_PIC_SIZE_WIDTH, -1) + " * " + Settings.getInt(KEY_PIC_SIZE_HEIGHT, -1)),
                    Arrays.asList(
                            new SettingsAdapter2.settingsDivider(),
                            new settingsEdit("请输入图片的宽", "宽", InputType.TYPE_NUMBER_FLAG_DECIMAL),
                            new SettingsAdapter2.settingsDivider(),
                            new settingsEdit("请输入图片的高", "高", InputType.TYPE_NUMBER_FLAG_DECIMAL))),
            new DataListTree<>(new SettingsAdapter2.settingsDivider(),
                    Collections.emptyList()),
            new DataListTree<>(new SettingsAdapter2.settingsNormal("清除用户数据", R.string.fa_minus_circle_before, "该操作将删除所有用户数据，请谨慎选择", SettingsAdapter2.TYPE_NORMAL_CHILD),
                    Collections.emptyList())
    ));
    private OnItemClickListener listener;
    private OnCheckedChangeListener checkedChangeListener;

    public SettingsAdapter2(SettingsActivity context) {
        super();
        this.mContext = context;
        initGroupItemStatus();
    }

    public void initGroupItemStatus() {
        for (int i = 0; i < mData.size(); i++) mGroupItemStatus.add(false);
    }

    private ItemStatus getItemStatusByPosition(int position) {
        ItemStatus itemStatus = new ItemStatus();
        int count = 0;    //计算groupItemIndex = i 时，position最大值
        int i = 0;
        //轮询 groupItem 的开关状态
        for (i = 0; i < mGroupItemStatus.size(); i++) {

            //pos刚好等于计数时，item为groupItem
            if (count == position) {
                itemStatus.setViewType(ItemStatus.VIEW_TYPE_GROUP_ITEM);
                itemStatus.setGroupItemIndex(i);
                break;
                //pos大于计数时，item为groupItem(i - 1)中的某个subItem
            } else if (count > position) {

                itemStatus.setViewType(ItemStatus.VIEW_TYPE_SUB_ITEM);
                itemStatus.setGroupItemIndex(i - 1);
                itemStatus.setSubItemIndex(position - (count - mData.get(i - 1).getSubItems().size()));
                break;
            }

            //无论groupItem状态是开或者关，它在列表中都会存在，所有count++
            count++;
            //当轮询到的groupItem的状态为“开”的话，count需要加上该groupItem下面的子项目数目
            if (mGroupItemStatus.get(i)) {
                count += mData.get(i).getSubItems().size();
            }
        }
        //简单地处理当轮询到最后一项groupItem的时候
        if (i >= mGroupItemStatus.size()) {
            itemStatus.setGroupItemIndex(i - 1);
            itemStatus.setViewType(ItemStatus.VIEW_TYPE_SUB_ITEM);
            itemStatus.setSubItemIndex(position - (count - mData.get(i - 1).getSubItems().size()));
        }
        return itemStatus;
    }

    @Override
    public int getItemViewType(int position) {
        ItemStatus itemStatus = getItemStatusByPosition(position);
        int groupItemIndex = itemStatus.getGroupItemIndex();
        List<settingsItem> sub = mData.get(groupItemIndex).getSubItems();
        settingsItem settingsItem = null;
//        Log.d("hbj", MessageFormat.format("第 {0} 项是第 {1} {2}项", position,
//                getItemStatusByPosition(position).getViewType() == ItemStatus.VIEW_TYPE_GROUP_ITEM ? groupItemIndex : itemStatus.getSubItemIndex(),
//                getItemStatusByPosition(position).getViewType() == ItemStatus.VIEW_TYPE_GROUP_ITEM ? "组" : "子"));
        if (itemStatus.getViewType() == ItemStatus.VIEW_TYPE_GROUP_ITEM)
            settingsItem = mData.get(groupItemIndex).getGroupItem();
        else
            settingsItem = sub.get(itemStatus.getSubItemIndex());
        if (settingsItem != null) {
            if (settingsItem instanceof settingsDivider) return TYPE_DIVIDER;
            if (settingsItem instanceof settingsDivider2) return TYPE_DIVIDER2;
            else if (settingsItem instanceof settingsNormal) return TYPE_NORMAL;
            else if (settingsItem instanceof settingsHeader) return TYPE_HEADER;
            else if (settingsItem instanceof settingsEdit) return TYPE_EDIT;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        if (mGroupItemStatus.size() == 0) return 0;
        for (int i = 0; i < mData.size(); i++)
            if (mGroupItemStatus.get(i)) itemCount += mData.get(i).getSubItems().size() + 1;
            else itemCount++;
        Log.d("hbj", getClass().getSimpleName() + ": 对应的itemCount是 " + itemCount);
        return itemCount;
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        SettingsViewHolder viewHolder;
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
                throw new RuntimeException("SettingsAdapter: onCreateViewHolder(): 程序内部错误");
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        ItemStatus itemStatus = getItemStatusByPosition(position); // 获取列表项状态
        int groupIndex = itemStatus.getGroupItemIndex(); // 组索引
        final DataListTree<settingsItem, settingsItem> dt = mData.get(groupIndex);
        if (itemStatus.getViewType() == ItemStatus.VIEW_TYPE_GROUP_ITEM) { // 组类型
            holder.itemView.setOnClickListener(v -> {
                int groupItemIndex = itemStatus.getGroupItemIndex();
                if (holder instanceof NormalViewHolder)
                    ((NormalViewHolder) holder).arrow.animate().rotation(90 - ((SettingsAdapter2.NormalViewHolder) holder).arrow.getRotation()).setDuration(200).start();
                if (mGroupItemStatus.get(groupItemIndex)) {
                    //groupItem由“打开”状态到“关闭”状态
                    mGroupItemStatus.set(groupItemIndex, false);
                    notifyItemRangeRemoved(holder.getAdapterPosition() + 1, dt.getSubItems().size());
                } else {
                    //groupItem由“关闭”状态到“打开”状态
                    mGroupItemStatus.set(groupItemIndex, true);
                    notifyItemRangeInserted(holder.getAdapterPosition() + 1, dt.getSubItems().size());
                }
            });
        } else if (itemStatus.getViewType() == ItemStatus.VIEW_TYPE_SUB_ITEM) { // 子项类型
            int subIndex = itemStatus.getSubItemIndex(); // 组索引
        }

        final settingsItem item;
        if (getItemStatusByPosition(position).getViewType() == ItemStatus.VIEW_TYPE_GROUP_ITEM)
            item = mData.get(itemStatus.getGroupItemIndex()).getGroupItem();
        else
            item = mData.get(itemStatus.getGroupItemIndex()).getSubItems().get(itemStatus.getSubItemIndex());
        if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            final settingsNormal itemNormal = (settingsNormal) item;
            normalViewHolder.icon.setVisibility(View.INVISIBLE);
            normalViewHolder.result.setVisibility(View.GONE);
            normalViewHolder.arrow.setVisibility(View.GONE);
            normalViewHolder.switchCompat.setVisibility(View.GONE);
            normalViewHolder.name.setText(itemNormal.titleRes);
            if (itemNormal.iconRes != 6221) {
                normalViewHolder.icon.setVisibility(View.VISIBLE);
                normalViewHolder.icon.setText(itemNormal.iconRes);
            }
            if (itemNormal.description != null) {
                normalViewHolder.description.setVisibility(View.VISIBLE);
                normalViewHolder.description.setText(itemNormal.description);
            }
            switch (itemNormal.type) {
                case TYPE_NORMAL_PARENT_VALUE:
                    normalViewHolder.result.setVisibility(View.VISIBLE);
                    normalViewHolder.result.setText(itemNormal.result);
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
            if (editViewHolder.title != null) {
                editViewHolder.title.setVisibility(View.VISIBLE);
                editViewHolder.title.setText(MessageFormat.format("{0}: ", itemEdit.title));
            }
            if (itemEdit.hint != null) editViewHolder.editText.setHint(itemEdit.hint);
            editViewHolder.editText.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
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
            editViewHolder.editText.setOnEditorActionListener((v, actionId, event) -> {
                View nextView = v.focusSearch(View.FOCUS_DOWN);
                if (nextView != null) nextView.requestFocus(View.FOCUS_DOWN);
                return false;
            });
            editViewHolder.editTextClear.setOnClickListener(v -> editViewHolder.editText.setText(null));
//            editViewHolder.cancel.setOnClickListener(v ->);
        } else if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            final settingsHeader itemHeader = (settingsHeader) item;
            headerViewHolder.title.setText(itemHeader.title);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (!payloads.isEmpty()) {
            String payload = (String) payloads.get(0);
        }
        onBindViewHolder(holder, position);
    }

    public interface OnItemClickListener {
        void itemClick(settingsNormal settingsNormal, SettingsViewHolder holder, int position);
    }

    public interface OnCheckedChangeListener {
        void itemCheck(CompoundButton buttonView, boolean isChecked);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setCheckedChangeListener(OnCheckedChangeListener listener) {
        this.checkedChangeListener = listener;
    }


    public void setBoolean(boolean aBoolean, int position) {
        this.aBoolean = aBoolean;
    }

    public void setString(String results, int position) {
        this.results = results;
    }

    public void addItemInPosition(int position) {
        if (mData == null) {
            mData = Collections.emptyList();
        }
    }

    public void setmData(List<DataListTree<settingsItem, settingsItem>> mData) {
        this.mData = mData;
    }

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

    public void replaceItem(List<DataListTree<settingsItem, settingsItem>> itemList) {
        if (mData == null) {
            mData = itemList;
        }
        notifyItemInserted(0);
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
    interface settingsItem {
    }

    public static class settingsNormal implements settingsItem {
        int iconRes;//图标
        String description;//说明
        public String titleRes;//标题
        public int type;//元素种类
        boolean aBoolean;//开关
        String result;//结果

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
        //////////////////

        settingsNormal(String titleRes, String description, int type) {
            this.iconRes = 6221;
            this.description = description;
            this.titleRes = titleRes;
            this.type = type;
        }
    }

    public class settingsEdit implements settingsItem {
        String title;
        String hint;
        int inputType;


        public settingsEdit(String title, String hint, int inputType) {
            this.title = title;
            this.hint = hint;
            this.inputType = inputType;
        }
    }

    public class settingsDivider implements settingsItem {
        settingsDivider() {
        }
    }

    public class settingsDivider2 implements settingsItem {
        settingsDivider2() {
        }
    }

    public class settingsHeader implements settingsItem {
        String title;

        settingsHeader(String title) {
            this.title = title;
        }
    }

    //----------------------------------ViewHolder数据模型---------------------------

    public class SettingsViewHolder extends RecyclerView.ViewHolder {
        SettingsViewHolder(View itemView) {
            super((itemView));
        }
    }

    public class NormalViewHolder extends SettingsViewHolder {
        View v;
        TextView name;
        TextView icon;
        TextView description;
        public TextView arrow;
        TextView result;
        SwitchCompat switchCompat;

        NormalViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            name = itemView.findViewById(R.id.name_settings);
            icon = itemView.findViewById(R.id.icon_settings);
            description = itemView.findViewById(R.id.description_settings);
            arrow = itemView.findViewById(R.id.arrow_settings);
            result = itemView.findViewById(R.id.result_settings);
            switchCompat = itemView.findViewById(R.id.switchCompat_settings);
        }
    }

    public class EditViewHolder extends SettingsViewHolder {
        TextView title;
        EditText editText;
        TextView editTextClear;
        TextView cancel;
        TextView confirm;

        EditViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.settings_editText_title);
            editText = itemView.findViewById(R.id.settings_editText);
            editTextClear = itemView.findViewById(R.id.settings_editText_clear);
            cancel = itemView.findViewById(R.id.settings_editText_cancel);
            confirm = itemView.findViewById(R.id.settings_editText_confirm);
        }
    }

    public class DividerViewHolder extends SettingsViewHolder {

        DividerViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class HeaderViewHolder extends SettingsViewHolder {

        private TextView title;

        HeaderViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_settings);
        }
    }


    public class DataListTree<K, V> {

        private K groupItem;
        private List<V> subItems;

        public DataListTree(K groupItem, List<V> subItems) {
            this.groupItem = groupItem;
            this.subItems = subItems;
        }

        public K getGroupItem() {
            return groupItem;
        }

        public List<V> getSubItems() {
            return subItems;
        }
    }

    public static class ItemStatus {

        public static final int VIEW_TYPE_GROUP_ITEM = 0;
        public static final int VIEW_TYPE_SUB_ITEM = 1;
        private int viewType;
        private int groupItemIndex = 0;
        private int subItemIndex = -1;

        public ItemStatus() {
        }


        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }

        public int getGroupItemIndex() {
            return groupItemIndex;
        }

        public void setGroupItemIndex(int groupItemIndex) {
            this.groupItemIndex = groupItemIndex;
        }

        public int getSubItemIndex() {
            return subItemIndex;
        }

        public void setSubItemIndex(int subItemIndex) {
            this.subItemIndex = subItemIndex;
        }
    }

}
