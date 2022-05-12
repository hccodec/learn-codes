/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.zxing.WriterException;
import com.physswjtu.srtp2019.widgets.Dialogs;
import com.physswjtu.srtp2019.Utils.FileUtil;
import com.physswjtu.srtp2019.Utils.OtherUtil;
import com.physswjtu.srtp2019.widgets.TextView;
import com.physswjtu.srtp2019.widgets.Toast;
import com.uuzuche.lib_zxing.encoding.EncodingHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.physswjtu.srtp2019.Utils.FileUtil.fileToUri;

/**
 * 由 84697 创建
 * 日期为 2019/7/23
 * 工程 PhysLab
 */
public class CreateQRActivity extends BaseActivity {
    @BindView(R.id.homeAsUp)
    TextView homeAsUp;
    @BindView(R.id.my_title)
    TextView myTitle;
    @BindView(R.id.text)
    EditText text;
    @BindView(R.id.text_clear)
    TextView textClear;
    @BindView(R.id.field1)
    RelativeLayout field1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.action_share)
    TextView actionShare;
    @BindView(R.id.create_result_preview)
    ImageView createResultPreview;
    @BindView(R.id.error)
    TextView error;
    @BindView(R.id.create_result_preview1)
    ImageView createResultPreview1;
    Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OtherUtil.setStatusBar(getWindow());
        setContentView(R.layout.activity_create_qr);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        textClear.setOnClickListener(v -> text.setText(""));
        text.setHint("请输入内容");
        text.setOnFocusChangeListener((v, hasFocus) -> {
            text.setHint(hasFocus ? "" : getString(R.string.hint));
            textClear.setVisibility((hasFocus && !text.getText().toString().isEmpty()) ? View.VISIBLE : View.GONE);
        });
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textClear.setVisibility(text.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!text.getText().toString().isEmpty())
                    try {
                        error.setVisibility(View.GONE);
                        bitmap = EncodingHandler.createQRCode(text.getText().toString(), 200);
                        createResultPreview.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    error.setVisibility(View.VISIBLE);
                    createResultPreview.setImageDrawable(getDrawable(R.drawable.ic_badge_phys_school));
                }
            }
        });
        textClear.setVisibility(View.GONE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (text.hasFocus()) text.clearFocus();
        ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
        return super.onTouchEvent(event);
    }

    @OnClick({R.id.homeAsUp, R.id.my_title, R.id.toolbar, R.id.text, R.id.text_clear, R.id.field1, R.id.action_share, R.id.create_result_preview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeAsUp:
                finish();
                break;
            case R.id.my_title:
                break;
            case R.id.toolbar:
                break;
            case R.id.text:
                break;
            case R.id.text_clear:
                break;
            case R.id.field1:
                break;
            case R.id.action_share:
                askForShare(this);
                break;
            case R.id.create_result_preview:
                askForShare(this);
                break;
        }
    }

    public void share(String type) {
        final Uri[] uri = new Uri[1];
        final File[] mTemp = new File[1];
        Dialogs.loading loading = new Dialogs.loading(CreateQRActivity.this, "图片生成中…………");
        new Thread() {
            public void run() {
                try {
                    runOnUiThread(loading::show);
                    //File file = new File(OtherUtil.getSpecificFilePath(CreateQRActivity.this, null) + "Custom" + System.currentTimeMillis() + ".jpg");
                    //String path = file.getPath();
                    //Uri uri = Uri.parse(path);
                    //uri[0] = Uri.fromFile(file);
                    mTemp[0] = FileUtil.newFile(getExternalCacheDir().getPath(), "temp." + (type.equals("PNG") ? "png" : "jpg"));
                    uri[0] = fileToUri(CreateQRActivity.this, mTemp[0]);
                    //uri[0] = CameraUtil.getTempUri();
                    Log.d("hbj", uri[0] + "");
                    OutputStream out = new FileOutputStream(String.valueOf(mTemp[0]));
                    bitmap = EncodingHandler.createQRCode(String.valueOf(text.getText()), 2000);
                    Bitmap bitmap1 = draw(bitmap);
                    if (bitmap1.compress(type.equals("PNG") ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 100, out)) {
                        out.flush();
                        out.close();
                    }
                    //runOnUiThread(() -> createResultPreview.setImageBitmap(bitmap1));
                    runOnUiThread(loading::dismiss);
                } catch (IOException | WriterException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M)
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                else
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                //intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(mTemp[0]));
                intent.putExtra(Intent.EXTRA_STREAM, uri[0]);
                intent.setType("image/*");
                //intent.setDataAndType(uri[0], "image/*");
                intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
                startActivity(Intent.createChooser(intent, "hbj"));
            }
        }.start();
    }

    public void askForShare(Context context) {
        if (bitmap == null) Toast.makeText(this, "请输入要分享的内容哦", Toast.LENGTH_SHORT).show();
        else {
            Dialogs.SRTPAlertDialog.Builder builder = new Dialogs.SRTPAlertDialog.Builder(context);
            final Dialogs.SRTPAlertDialog[] dialog = {builder.create()};
            View.OnClickListener listener = v -> {
                if (dialog[0] != null) {
                    dialog[0].dismiss();
                    dialog[0] = null;
                }
                share("PNG");
            };
            View.OnClickListener listener2 = v -> {
                if (dialog[0] != null) {
                    dialog[0].dismiss();
                    dialog[0] = null;
                }
                share("JPEG");
            };
            builder.setTitle("图片选择").setIcon(bitmap)
                    .setMessage("请选择您要发送的图片类别：（png图片带有透明度，但在分享过程中QQ等软件默认将图片强转为JPEG格式时会涂黑透明像素导致图片全黑）")
                    .setButton("PNG", listener).setButton2("JPEG", listener2);
            dialog[0].show();
        }
    }

    private void createBitmap() {

    }

    public Bitmap draw(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++) {
                    int color = bitmap.getPixel(j, i);
                    int g = Color.green(color);
                    int r = Color.red(color);
                    int b = Color.blue(color);
                    int a = Color.alpha(color);
                    if (a == 0) {
                        /*
                        r = (int) (1 + Math.random() * (250 - 1 + 1));
                        g = (int) (1 + Math.random() * (250 - 1 + 1));
                        b = (int) (1 + Math.random() * (250 - 1 + 1));
                        */
                        a = r = g = b = 255;
                    }
                    color = Color.argb(a, r, g, b);
                    createBitmap.setPixel(j, i, color);
                }
        }
        return createBitmap;
    }

    public void click(View v) {
        text.setText("http://192.168.1.102:8083");
    }
}
