/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.physswjtu.srtp2019.Utils.OtherUtil;
import com.physswjtu.srtp2019.data.Settings.Settings;
import com.physswjtu.srtp2019.data.Settings.SettingsAdapter;
import com.physswjtu.srtp2019.widgets.Dialogs;
import com.physswjtu.srtp2019.widgets.TextView;
import com.physswjtu.srtp2019.widgets.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.physswjtu.srtp2019.Utils.SoundUtil.NOTIFICATION;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ding;
import static com.physswjtu.srtp2019.data.Settings.Settings.DEVELOPER_PASSWD;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_DEV_DATA_SOURCE;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_IS_IN_DEVELOPER_MODE;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_IS_SILENT;
import static com.physswjtu.srtp2019.data.Settings.Settings.KEY_SERVER_ADDRESS;
import static com.physswjtu.srtp2019.data.Settings.Settings.SERVER_ADDRESS_DEFAULT;
import static com.physswjtu.srtp2019.data.Settings.Settings.wipeData;


/**
 * 由 84697 创建
 * 日期为 2019/7/30
 * 工程 PhysLab
 */
public class SettingsActivity extends BaseActivity {
    private SettingsAdapter adapter;
    @BindView(R.id.home)
    TextView home;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.item_settings)
    RecyclerView itemSettings;
    Dialog loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        OtherUtil.setStatusBar(getWindow());
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        setSupportActionBar(toolbar);
        adapter = new SettingsAdapter(this);
        adapter.init();
        adapter.setCheckedChangeListener((buttonView, isChecked) -> {
            Settings.putBoolean(KEY_IS_SILENT, isChecked, false);
            Toast.makeText(SettingsActivity.this, "已设为" + (isChecked ? "静音" : "响铃") + "模式", Toast.LENGTH_SHORT).show();
            // FIXME: 2019/8/2 记得删除播放声音自检
            ding(SettingsActivity.this, NOTIFICATION);
        });
        adapter.setOnItemClickListener((settingsNormal, holder, position) -> {
            switch (settingsNormal.titleRes) {
                case "进入开发者模式":
                    Dialogs.SRTPAlertDialog.BuilderEdit builder0 = new Dialogs.SRTPAlertDialog.BuilderEdit(this);
                    final Dialogs.SRTPAlertDialog dialog0 = builder0.create();
                    builder0.setTitle("设置 - 开发者模式").setIcon(R.string.fa_picture_o_before).setMessage("确认身份：" +
                            "（请慎重选择，若您并非开发者且没有开发者协助的情况下擅自更改设置，可能会出现软件崩溃或不稳定情况 -> \n会影响您提交的作业数据哦 !!!）").setButton(getString(android.R.string.ok), v -> {
                        if (((EditText) dialog0.findViewById(R.id.dialog_edit)).getText().toString().equals(DEVELOPER_PASSWD)) {
                            Settings.putBoolean(KEY_IS_IN_DEVELOPER_MODE, true, false);
                            Toast.makeText(this, "您已进入开发者模式", Toast.LENGTH_SHORT).show();
                            adapter.init();
                        } else Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                    });
                    dialog0.show();
                    break;
                case "退出开发者模式":
                    Settings.putBoolean(KEY_IS_IN_DEVELOPER_MODE, false, false);
                    adapter.init();
                    Toast.makeText(this, "已退出开发者模式", Toast.LENGTH_SHORT).show();
                    break;
                case "首页数据来源":
                    Dialogs.SRTPAlertDialog.Builder2 builder = new Dialogs.SRTPAlertDialog.Builder2(this);
                    View.OnClickListener listener = v -> {
                        Settings.putBoolean(KEY_DEV_DATA_SOURCE, true, false);
                        Toast.makeText(this, "数据来源已设为云端", Toast.LENGTH_SHORT).show();
                        adapter.update(holder, position);
                    };
                    View.OnClickListener listener2 = v -> {
                        Settings.putBoolean(KEY_DEV_DATA_SOURCE, false, false);
                        Toast.makeText(this, "数据来源已设为本地", Toast.LENGTH_SHORT).show();
                        adapter.update(holder, position);
                    };
                    builder.setTitle("设置 - 首页数据来源").setIcon(R.string.fa_address_book_o_before).setMessage("请选择首页数据来源：")
                            .setButton("云端", listener).setButton2("本地", listener2);
                    builder.create().show();
                    break;
                case "服务器地址":
                    Dialogs.SRTPAlertDialog.BuilderEdit builder1 = new Dialogs.SRTPAlertDialog.BuilderEdit(this);
                    final Dialogs.SRTPAlertDialog dialog1 = builder1.create();
                    builder1.setText(Settings.getString(KEY_SERVER_ADDRESS, SERVER_ADDRESS_DEFAULT));
                    builder1.setTitle("设置 - 服务器地址").setIcon(R.string.fa_server_before).setMessage("请在下方输入新的服务器地址").setButton(getString(android.R.string.ok), v -> {
                        Settings.putString(KEY_SERVER_ADDRESS, ((EditText) dialog1.findViewById(R.id.dialog_edit)).getText().toString(), false);
                        Toast.makeText(this, "服务器地址已更新", Toast.LENGTH_SHORT).show();
                        adapter.update(holder, position);
                    });
                    dialog1.show();
                    break;
                case "图片宽高值":
                    Dialogs.SRTPAlertDialog.BuilderEdit2 builder2 = new Dialogs.SRTPAlertDialog.BuilderEdit2(this);
                    final Dialogs.SRTPAlertDialog dialog2 = builder2.create();
                    builder2.setText(String.valueOf(Settings.getInt(Settings.KEY_PIC_SIZE_WIDTH, -1)));
                    builder2.setText2(String.valueOf(Settings.getInt(Settings.KEY_PIC_SIZE_HEIGHT, -1)));
                    builder2.setTitle("设置 - 上传图片的长宽").setIcon(R.string.fa_picture_o_before).setMessage("请在下方输入新的长宽，推荐值：1200*1600").setButton(getString(android.R.string.ok), v -> {
                        Settings.putInt(Settings.KEY_PIC_SIZE_WIDTH, Integer.valueOf(((EditText) dialog2.findViewById(R.id.dialog_edit1)).getText().toString()), false);
                        Settings.putInt(Settings.KEY_PIC_SIZE_HEIGHT, Integer.valueOf(((EditText) dialog2.findViewById(R.id.dialog_edit2)).getText().toString()), false);
                        Toast.makeText(this, "图片长宽已更新", Toast.LENGTH_SHORT).show();
                        adapter.update(holder, position);
                    });
                    dialog2.show();
                    break;
                case "恢复默认设置":
                    ask(this);
                    break;
            }
        });
        itemSettings.setLayoutManager(new LinearLayoutManager(this));
        itemSettings.setAdapter(adapter);
    }

    public void ask(Context context) {
        Dialogs.SRTPAlertDialog.Builder builder = new Dialogs.SRTPAlertDialog.Builder(context);
        final Dialogs.SRTPAlertDialog[] dialog = {builder.create()};
        View.OnClickListener listener = v -> {
            if (dialog[0] != null) {
                dialog[0].dismiss();
                dialog[0] = null;
            }
            wipe();
        };
        View.OnClickListener listener2 = v -> {
            if (dialog[0] != null) {
                dialog[0].dismiss();
                dialog[0] = null;
            }
        };
        builder.setTitle("恢复默认设置").setIcon(R.string.fa_info_before).
                setMessage("请确认是否恢复默认设置\n (恢复默认设置将退出开发者模式)").setButton("确定", listener).setButton2("再想想", listener2);
        dialog[0].show();
    }

    public void wipe() {
        loading = new Dialogs.loading(this, "正在清空，请稍候………");
        new Thread() {
            public void run() {
                runOnUiThread(() -> loading.show());
                wipeData();
                runOnUiThread(() -> {
                    loading.dismiss();
                    loading = null;
                    Toast.makeText(SettingsActivity.this, "数据已清空", Toast.LENGTH_SHORT).show();
                    adapter.init();
                });
            }
        }.start();
    }

    @OnClick({R.id.home, R.id.toolbar, R.id.item_settings})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home:
                finish();
                break;
            case R.id.toolbar:
                break;
        }
    }
}
