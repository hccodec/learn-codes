/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;

import com.physswjtu.srtp2019.R;
import com.physswjtu.srtp2019.Utils.SoundUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.physswjtu.srtp2019.Utils.BitmapUtil.sp2px;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ALERT;

/**
 * 由 84697 创建
 * 日期为 2019/8/10
 * 工程 PhysLab
 */
public class Dialogs {
    public static AlertDialog toolbarDialog(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_logo_location);
        builder.setTitle("选择一下");
        final String[] itemsId = new String[]{"自组电桥实验", "光电子实验", "c", "d", "e"};
        final boolean[] checkedItems = new boolean[]{true, true, false, false, false};
        builder.setMultiChoiceItems(itemsId, checkedItems, (dialog, which, isChecked) -> checkedItems[which] = isChecked);
        builder.setPositiveButton("确定", null);
        builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(v -> {
            Log.d("hbj", "checkedItems: " + Arrays.toString(checkedItems));
            StringBuilder text = new StringBuilder();
            boolean hasSelected = false;
            for (int i = 0; i < itemsId.length; i++) {
                text.append(checkedItems[i] ? itemsId[i] + "," : "");
                if (checkedItems[i]) {
                    hasSelected = checkedItems[i];
                    break;
                }
            }
            if (hasSelected) {
                Toast.makeText(context, "选择成功！", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            } else {
                Toast.makeText(context, "请选择您要注册的实验", Toast.LENGTH_LONG).show();
                SoundUtil.ding(context, ALERT);
            }
        });
        return dialog;
    }

    //loading 的 Dialog，主要是会动……
    public static class loading extends Dialog {
        TextView icon;//图标
        Context context;
        Dialog result;

        public loading(Context context, String msg) {
            super(context);
            this.context = context;
            LinearLayout linearLayout = new LinearLayout(context);
            icon = new TextView(context);
            TextView hint = new TextView(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.black_overlay));
            linearLayout.setPadding(60, 20, 60, 20);
            linearLayout.setGravity(Gravity.CENTER);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 20, 15, 40);
            params2.setMargins(0, 0, 0, 20);
            icon.setLayoutParams(params);
            hint.setLayoutParams(params2);
            icon.setIsIconFont();
            icon.setText(R.string.fa_spinner_before);
            hint.setText(String.format("%s……", msg));
            icon.setTextColor(Color.WHITE);
            hint.setTextColor(Color.WHITE);
            icon.setTextSize(sp2px(context, 10));
            hint.setTextSize(sp2px(context, 5));
            linearLayout.addView(icon);
            linearLayout.addView(hint);
            result = new Dialog(context, R.style.mDialog);
            result.addContentView(linearLayout, params);
            result.setCanceledOnTouchOutside(false);
        }

        @Override
        public void show() {
            icon.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate));
            if (!result.isShowing()) result.show();
        }

        @Override
        public void dismiss() {
            icon.clearAnimation();
            if (context != null && !((Activity) context).isFinishing() && result.isShowing())
                result.dismiss();
        }
    }

    //可自定义的 Dialog ，用于设置项
    public static class settingDialog extends Dialog {
        Dialog result;
        List<TextView> textViews = new ArrayList<>();

        public settingDialog(Context context, String... mTexts) {
            super(context);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.black_overlay));
            linearLayout.setPadding(60, 20, 60, 20);
            linearLayout.setGravity(Gravity.CENTER);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 20, 15, 40);
            result = new Dialog(context);
            for (String mText1 : mTexts) {
                TextView textView = new TextView(context);
                textView.setLayoutParams(params);
                textView.setText(mText1);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(sp2px(context, 20));
                linearLayout.addView(textView);
                textViews.add(textView);
            }
            Log.d("hbj", textViews.toString());
            result.addContentView(linearLayout, params);
        }

        private int getItemCount() {
            return (textViews == null || textViews.size() == 0) ? 0 : textViews.size();
        }

        public void setItemListener(View.OnClickListener... listeners) {
            if (listeners.length <= getItemCount())
                for (int i = 0; i < listeners.length; i++)
                    textViews.get(i).setOnClickListener(listeners[i]);
        }

        @Override
        public void show() {
            if (!result.isShowing()) result.show();
        }

        @Override
        public void dismiss() {
            if (result.isShowing()) result.dismiss();
        }

    }

    public static class SRTPAlertDialog extends Dialog {

        private SRTPAlertDialog(Context context, int themeResId) {
            super(context, themeResId);
        }

        public static class Builder {

            private View view;
            private Context context;
            private TextView mIcon;
            private TextView mTitle;
            private TextView mMessage;
            private Button mButton;
            private Button mButton2;

            private View.OnClickListener mButtonClickListener;
            private View.OnClickListener mButtonClickListener2;

            private SRTPAlertDialog mDialog;

            public Builder(Context context) {
                this.context = context;
                mDialog = new SRTPAlertDialog(context, R.style.mDialog);
                view = LayoutInflater.from(context).inflate(R.layout.view_dialog, null);
                mIcon = view.findViewById(R.id.dialog_icon);
                mTitle = view.findViewById(R.id.dialog_title);
                mMessage = view.findViewById(R.id.dialog_message);
                mButton = view.findViewById(R.id.dialog_button);
                mButton2 = view.findViewById(R.id.dialog_button2);
            }

            /**
             * 通过 ID 设置 Dialog 图标
             */
            public Builder setIcon(int resId) {
                mIcon.setText(resId);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Drawable 作为 Dialog 图标
             */
            public Builder setIcon(Drawable drawable) {
                mIcon.setBackground(drawable);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Bitmap 作为 Dialog 图标
             */
            public Builder setIcon(Bitmap bitmap) {
                mIcon.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置 Dialog 标题
             */
            public Builder setTitle(@NonNull String title) {
                mTitle.setText(title);
                mTitle.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置 Message
             */
            public Builder setMessage(@NonNull String message) {
                mMessage.setText(message);
                mMessage.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置按钮文字和监听
             */
            public Builder setButton(@NonNull String text, View.OnClickListener listener) {
                mButton.setText(text);
                mButton.setVisibility(View.VISIBLE);
                mButtonClickListener = listener;
                return this;
            }

            /**
             * 设置按钮文字和监听
             */
            public Builder setButton2(@NonNull String text, View.OnClickListener listener) {
                mButton2.setText(text);
                mButton2.setVisibility(View.VISIBLE);
                mButtonClickListener2 = listener;
                return this;
            }

            public SRTPAlertDialog create() {
                mButton.setOnClickListener(view -> {
                    mDialog.dismiss();
                    mButtonClickListener.onClick(view);
                });
                mButton2.setOnClickListener(view -> {
                    mDialog.dismiss();
                    mButtonClickListener2.onClick(view);
                });
                mDialog.setContentView(view);
                mDialog.setCancelable(true);                //用户    可以点击后退键关闭 Dialog
                mDialog.setCanceledOnTouchOutside(false);   //用户 不 可以点击外部来关闭 Dialog
                return mDialog;
            }

            public View getView() {
                return view;
            }
        }

        public static class Builder2 {

            private View view;
            private Context context;
            private TextView mIcon;
            private TextView mTitle;
            private TextView mMessage;
            private Button mButton;
            private Button mButton2;

            private View.OnClickListener mButtonClickListener;
            private View.OnClickListener mButtonClickListener2;

            private SRTPAlertDialog mDialog;

            public Builder2(Context context) {
                this.context = context;
                mDialog = new SRTPAlertDialog(context, R.style.mDialog);
                view = LayoutInflater.from(context).inflate(R.layout.view_dialog2, null);

                mIcon = view.findViewById(R.id.dialog_icon);
                mTitle = view.findViewById(R.id.dialog_title);
                mMessage = view.findViewById(R.id.dialog_message);
                mButton = view.findViewById(R.id.dialog_button);
                mButton2 = view.findViewById(R.id.dialog_button2);
                mTitle.setGravity(Gravity.CENTER);
            }

            /**
             * 通过 ID 设置 Dialog 图标
             */
            public Builder2 setIcon(int resId) {
                mIcon.setText(resId);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Drawable 作为 Dialog 图标
             */
            public Builder2 setIcon(Drawable drawable) {
                mIcon.setBackground(drawable);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Bitmap 作为 Dialog 图标
             */
            public Builder2 setIcon(Bitmap bitmap) {
                mIcon.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置 Dialog 标题
             */
            public Builder2 setTitle(@NonNull String title) {
                mTitle.setText(title);
                mTitle.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置 Message
             */
            public Builder2 setMessage(@NonNull String message) {
                mMessage.setText(message);
                mMessage.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置按钮文字和监听
             */
            public Builder2 setButton(@NonNull String text, View.OnClickListener listener) {
                mButton.setText(text);
                mButton.setVisibility(View.VISIBLE);
                mButtonClickListener = listener;
                return this;
            }

            /**
             * 设置按钮文字和监听
             */
            public Builder2 setButton2(@NonNull String text, View.OnClickListener listener) {
                mButton2.setText(text);
                mButton2.setVisibility(View.VISIBLE);
                mButtonClickListener2 = listener;
                return this;
            }

            public SRTPAlertDialog create() {
                mButton.setOnClickListener(view -> {
                    mDialog.dismiss();
                    mButtonClickListener.onClick(view);
                });
                mButton2.setOnClickListener(view -> {
                    mDialog.dismiss();
                    mButtonClickListener2.onClick(view);
                });
                mDialog.setContentView(view);
                mDialog.setCancelable(true);                //用户可以点击后退键关闭 Dialog
                mDialog.setCanceledOnTouchOutside(false);   //用户不可以点击外部来关闭 Dialog
                return mDialog;
            }

            public View getView() {
                return view;
            }
        }

        public static class BuilderPic {

            private View view;
            private Context context;
            private TextView mIcon;
            private TextView mTitle;
            private TextView mMessage;
            private AppCompatImageView mPic;

            private SRTPAlertDialog mDialog;

            public BuilderPic(Context context) {
                this.context = context;
                mDialog = new SRTPAlertDialog(context, R.style.mDialog);
                //            view = LayoutInflater.from(context).inflate(R.layout.view_dialog, null);
                view = LayoutInflater.from(context).inflate(R.layout.view_dialog_picpreview, null);
                mIcon = view.findViewById(R.id.dialog_icon);
                mTitle = view.findViewById(R.id.dialog_title);
                mMessage = view.findViewById(R.id.dialog_message);
                mPic = view.findViewById(R.id.picPreview);
                mPic.setOnClickListener(v -> {
                    if (mDialog != null) mDialog.dismiss();
                });
            }

            /**
             * 通过 ID 设置 Dialog 图标
             */
            public BuilderPic setIcon(int resId) {
                mIcon.setText(resId);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Drawable 作为 Dialog 图标
             */
            public BuilderPic setIcon(Drawable drawable) {
                mIcon.setBackground(drawable);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Bitmap 作为 Dialog 图标
             */
            public BuilderPic setIcon(Bitmap bitmap) {
                mIcon.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置 Dialog 标题
             */
            public BuilderPic setTitle(@NonNull String title) {
                mTitle.setText(title);
                mTitle.setVisibility(View.VISIBLE);
                return this;
            }

            public BuilderPic setmPic(Uri uri) {
                mPic.setImageURI(uri);
                mMessage.setText(uri.getLastPathSegment());
                return this;
            }

            /**
             * 设置 Message
             */
            public BuilderPic setMessage(@NonNull String message) {
                mMessage.setText(message);
                mMessage.setVisibility(View.VISIBLE);
                return this;
            }

            public SRTPAlertDialog create() {
                mDialog.setContentView(view);
                mDialog.setCancelable(true);                //用户    可以点击后退键关闭 Dialog
                mDialog.setCanceledOnTouchOutside(false);   //用户 不 可以点击外部来关闭 Dialog
                return mDialog;
            }

            public View getView() {
                return view;
            }
        }

        public static class BuilderEdit {

            private View view;
            private Context context;
            private TextView mIcon;
            private TextView mTitle;
            private TextView mMessage;
            private EditText mEditText;
            private Button mButton;
            private Button mButton2;

            private View.OnClickListener mButtonClickListener;
            private View.OnClickListener mButtonClickListener2;

            private SRTPAlertDialog mDialog;

            public BuilderEdit(Context context) {
                this.context = context;
                mDialog = new SRTPAlertDialog(context, R.style.mDialog);
                view = LayoutInflater.from(context).inflate(R.layout.view_dialog_edit, null);
                mEditText = view.findViewById(R.id.dialog_edit);
                mIcon = view.findViewById(R.id.dialog_icon);
                mTitle = view.findViewById(R.id.dialog_title);
                mMessage = view.findViewById(R.id.dialog_message);
                mButton = view.findViewById(R.id.dialog_button);
                mButton2 = view.findViewById(R.id.dialog_button2);
                mTitle.setGravity(Gravity.CENTER);
                view.findViewById(R.id.dialog_edit_clear).setOnClickListener(v -> mEditText.setText(""));
                mEditText.setOnFocusChangeListener((v, hasFocus) -> {
                    if (hasFocus) ((EditText) v).setSelection(((EditText) v).getText().length());
                });
            }

            /**
             * 通过 ID 设置 Dialog 图标
             */
            public BuilderEdit setIcon(int resId) {
                mIcon.setText(resId);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Drawable 作为 Dialog 图标
             */
            public BuilderEdit setIcon(Drawable drawable) {
                mIcon.setBackground(drawable);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Bitmap 作为 Dialog 图标
             */
            public BuilderEdit setIcon(Bitmap bitmap) {
                mIcon.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置 Dialog 标题
             */
            public BuilderEdit setTitle(@NonNull String title) {
                mTitle.setText(title);
                mTitle.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置 Dialog 标题
             */
            public BuilderEdit setText(@NonNull String text) {
                mEditText.setText(text);
                return this;
            }

            /**
             * 设置 Message
             */
            public BuilderEdit setMessage(@NonNull String message) {
                mMessage.setText(message);
                mMessage.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置按钮文字和监听
             */
            public BuilderEdit setButton(@NonNull String text, View.OnClickListener listener) {
                mButton.setText(text);
                mButton.setVisibility(View.VISIBLE);
                mButtonClickListener = listener;
                return this;
            }

            /**
             * 设置按钮文字和监听
             */
            public BuilderEdit setButton2(@NonNull String text, View.OnClickListener listener) {
                mButton2.setText(text);
                mButton2.setVisibility(View.VISIBLE);
                mButtonClickListener2 = listener;
                return this;
            }

            public SRTPAlertDialog create() {
                mButton.setOnClickListener(view -> {
                    mDialog.dismiss();
                    mButtonClickListener.onClick(view);
                });
                mButton2.setOnClickListener(view -> {
                    mDialog.dismiss();
                    mButtonClickListener2.onClick(view);
                });
                mDialog.setContentView(view);
                mDialog.setCancelable(true);                //用户可以点击后退键关闭 Dialog
                mDialog.setCanceledOnTouchOutside(false);   //用户不可以点击外部来关闭 Dialog
                return mDialog;
            }

            public View getView() {
                return view;
            }
        }

        public static class BuilderEdit2 {

            private View view;
            private Context context;
            private TextView mIcon;
            private TextView mTitle;
            private TextView mMessage;
            private EditText mEditText;
            private EditText mEditText2;
            private Button mButton;
            private Button mButton2;

            private View.OnClickListener mButtonClickListener;
            private View.OnClickListener mButtonClickListener2;

            private SRTPAlertDialog mDialog;

            public BuilderEdit2(Context context) {
                this.context = context;
                mDialog = new SRTPAlertDialog(context, R.style.mDialog);
                view = LayoutInflater.from(context).inflate(R.layout.view_dialog_edit_picsize, null);
                mEditText = view.findViewById(R.id.dialog_edit1);
                mEditText2 = view.findViewById(R.id.dialog_edit2);
                mIcon = view.findViewById(R.id.dialog_icon);
                mTitle = view.findViewById(R.id.dialog_title);
                mMessage = view.findViewById(R.id.dialog_message);
                mButton = view.findViewById(R.id.dialog_button);
                mButton2 = view.findViewById(R.id.dialog_button2);
                mTitle.setGravity(Gravity.CENTER);
                mEditText.setOnFocusChangeListener((v, hasFocus) -> {
                    if (hasFocus) ((EditText) v).setSelection(((EditText) v).getText().length());
                });
                mEditText2.setOnFocusChangeListener((v, hasFocus) -> {
                    if (hasFocus) ((EditText) v).setSelection(((EditText) v).getText().length());
                });
            }

            /**
             * 通过 ID 设置 Dialog 图标
             */
            public BuilderEdit2 setIcon(int resId) {
                mIcon.setText(resId);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Drawable 作为 Dialog 图标
             */
            public BuilderEdit2 setIcon(Drawable drawable) {
                mIcon.setBackground(drawable);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Bitmap 作为 Dialog 图标
             */
            public BuilderEdit2 setIcon(Bitmap bitmap) {
                mIcon.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置 Dialog 标题
             */
            public BuilderEdit2 setTitle(@NonNull String title) {
                mTitle.setText(title);
                mTitle.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置 Dialog 标题
             */
            public BuilderEdit2 setText(@NonNull String text) {
                mEditText.setText(text);
                return this;
            }

            public BuilderEdit2 setText2(@NonNull String text) {
                mEditText2.setText(text);
                return this;
            }

            /**
             * 设置 Message
             */
            public BuilderEdit2 setMessage(@NonNull String message) {
                mMessage.setText(message);
                mMessage.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置按钮文字和监听
             */
            public BuilderEdit2 setButton(@NonNull String text, View.OnClickListener listener) {
                mButton.setText(text);
                mButton.setVisibility(View.VISIBLE);
                mButtonClickListener = listener;
                return this;
            }

            /**
             * 设置按钮文字和监听
             */
            public BuilderEdit2 setButton2(@NonNull String text, View.OnClickListener listener) {
                mButton2.setText(text);
                mButton2.setVisibility(View.VISIBLE);
                mButtonClickListener2 = listener;
                return this;
            }

            public SRTPAlertDialog create() {
                mButton.setOnClickListener(view -> {
                    mDialog.dismiss();
                    mButtonClickListener.onClick(view);
                });
                mButton2.setOnClickListener(view -> {
                    mDialog.dismiss();
                    mButtonClickListener2.onClick(view);
                });
                mDialog.setContentView(view);
                mDialog.setCancelable(true);                //用户可以点击后退键关闭 Dialog
                mDialog.setCanceledOnTouchOutside(false);   //用户不可以点击外部来关闭 Dialog
                return mDialog;
            }

            public View getView() {
                return view;
            }
        }


        public static class BuilderEdit3 {

            private View view;
            private Context context;
            private TextView mIcon;
            private TextView mTitle;
            private TextView mMessage;
            private EditText mEditText;
            private EditText mEditText2;
            private EditText mEditText3;
            private TextView show1;
            private TextView show2;
            private TextView show3;
            private TextView clear1;
            private TextView clear2;
            private TextView clear3;
            private Button mButton;
            private Button mButton2;
            private boolean isShownPasswd;
            private boolean isShownPasswd2;
            private boolean isShownPasswd3;

            private View.OnClickListener mButtonClickListener;
            private View.OnClickListener mButtonClickListener2;

            private SRTPAlertDialog mDialog;

            public BuilderEdit3(Context context) {
                this.context = context;
                mDialog = new SRTPAlertDialog(context, R.style.mDialog);
                view = LayoutInflater.from(context).inflate(R.layout.view_dialog_changepasswd, null);
                mEditText = view.findViewById(R.id.change_passwd_1);
                mEditText2 = view.findViewById(R.id.change_passwd_2);
                mEditText3 = view.findViewById(R.id.change_passwd_3);
                show1 = view.findViewById(R.id.change_passwd_1_show);
                show2 = view.findViewById(R.id.change_passwd_2_show);
                show3 = view.findViewById(R.id.change_passwd_3_show);
                clear1 = view.findViewById(R.id.change_passwd_1_clear);
                clear2 = view.findViewById(R.id.change_passwd_2_clear);
                clear3 = view.findViewById(R.id.change_passwd_3_clear);
                mIcon = view.findViewById(R.id.dialog_icon);
                mTitle = view.findViewById(R.id.dialog_title);
                mMessage = view.findViewById(R.id.dialog_message);
                mButton = view.findViewById(R.id.dialog_button);
                mButton2 = view.findViewById(R.id.dialog_button2);
                mTitle.setGravity(Gravity.CENTER);
                show1.setOnClickListener(v -> {
                    isShownPasswd = !isShownPasswd;
                    show1.setText(isShownPasswd ? R.string.fa_eye_before : R.string.fa_eye_slash_before);
                    mEditText.setTransformationMethod(isShownPasswd ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                    mEditText.setSelection(mEditText.getText().toString().length());
                });
                show2.setOnClickListener(v -> {
                    isShownPasswd2 = !isShownPasswd2;
                    show2.setText(isShownPasswd2 ? R.string.fa_eye_before : R.string.fa_eye_slash_before);
                    mEditText2.setTransformationMethod(isShownPasswd2 ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                    mEditText2.setSelection(mEditText2.getText().toString().length());
                });
                show3.setOnClickListener(v -> {
                    isShownPasswd3 = !isShownPasswd3;
                    show3.setText(isShownPasswd3 ? R.string.fa_eye_before : R.string.fa_eye_slash_before);
                    mEditText3.setTransformationMethod(isShownPasswd3 ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                    mEditText3.setSelection(mEditText3.getText().toString().length());
                });
                clear1.setOnClickListener(v -> mEditText.setText(""));
                clear2.setOnClickListener(v -> mEditText2.setText(""));
                clear3.setOnClickListener(v -> mEditText3.setText(""));
                mEditText.setOnFocusChangeListener((v, hasFocus) -> {
                    if (hasFocus) ((EditText) v).setSelection(((EditText) v).getText().length());
                });
                mEditText2.setOnFocusChangeListener((v, hasFocus) -> {
                    if (hasFocus) ((EditText) v).setSelection(((EditText) v).getText().length());
                });
                mEditText3.setOnFocusChangeListener((v, hasFocus) -> {
                    if (hasFocus) ((EditText) v).setSelection(((EditText) v).getText().length());
                });
            }

            /**
             * 通过 ID 设置 Dialog 图标
             */
            public BuilderEdit3 setIcon(int resId) {
                mIcon.setText(resId);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Drawable 作为 Dialog 图标
             */
            public BuilderEdit3 setIcon(Drawable drawable) {
                mIcon.setBackground(drawable);
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 用 Bitmap 作为 Dialog 图标
             */
            public BuilderEdit3 setIcon(Bitmap bitmap) {
                mIcon.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                mIcon.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置 Dialog 标题
             */
            public BuilderEdit3 setTitle(@NonNull String title) {
                mTitle.setText(title);
                mTitle.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置 Dialog 标题
             */
            public BuilderEdit3 setText(@NonNull String text) {
                mEditText.setText(text);
                return this;
            }

            public BuilderEdit3 setText2(@NonNull String text) {
                mEditText2.setText(text);
                return this;
            }

            public BuilderEdit3 setText3(@NonNull String text) {
                mEditText3.setText(text);
                return this;
            }

            public EditText getEditText(int position) {
                switch (position) {
                    case 1:
                        return mEditText;
                    case 2:
                        return mEditText2;
                    case 3:
                        return mEditText3;
                }
                return mEditText;
            }

            /**
             * 设置 Message
             */
            public BuilderEdit3 setMessage(@NonNull String message) {
                mMessage.setText(message);
                mMessage.setVisibility(View.VISIBLE);
                return this;
            }

            /**
             * 设置按钮文字和监听
             */
            public BuilderEdit3 setButton(@NonNull String text, View.OnClickListener listener) {
                mButton.setText(text);
                mButton.setVisibility(View.VISIBLE);
                mButtonClickListener = listener;
                return this;
            }

            /**
             * 设置按钮文字和监听
             */
            public BuilderEdit3 setButton2(@NonNull String text, View.OnClickListener listener) {
                mButton2.setText(text);
                mButton2.setVisibility(View.VISIBLE);
                mButtonClickListener2 = listener;
                return this;
            }

            public SRTPAlertDialog create() {
                mButton.setOnClickListener(view -> {
                    mDialog.dismiss();
                    mButtonClickListener.onClick(view);
                });
                mButton2.setOnClickListener(view -> {
                    mDialog.dismiss();
                    mButtonClickListener2.onClick(view);
                });
                mDialog.setContentView(view);
                mDialog.setCancelable(true);                //用户可以点击后退键关闭 Dialog
                mDialog.setCanceledOnTouchOutside(false);   //用户不可以点击外部来关闭 Dialog
                return mDialog;
            }

            public View getView() {
                return view;
            }
        }

        @Override
        public <T extends View> T findViewById(int id) {
            return super.findViewById(id);
        }
    }

}

