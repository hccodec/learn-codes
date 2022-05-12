/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.physswjtu.srtp2019.Utils.OtherUtil;
import com.physswjtu.srtp2019.Utils.SoundUtil;
import com.physswjtu.srtp2019.widgets.TextView;
import com.physswjtu.srtp2019.widgets.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 由 84697 创建
 * 日期为 2019/7/28
 * 工程 PhysLab
 */
public class BrowserActivity extends BaseActivity {


    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.path_et)
    EditText pathEt;
    @BindView(R.id.webView_toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView_pb)
    ProgressBar webViewPb;
    @BindView(R.id.home)
    TextView home;
    @BindView(R.id.webView_rf)
    SwipeRefreshLayout webViewRf;
    @BindView(R.id.webView_title)
    TextView webViewTitle;
    @BindView(R.id.webView_load)
    TextView webViewLoad;
    @BindView(R.id.webView_menu)
    TextView webViewMenu;
    PopupWindow mPopWindow;
    @BindView(R.id.webView_backward)
    TextView webViewBackward;
    @BindView(R.id.webView_home)
    TextView webViewHome;
    @BindView(R.id.webView_foreward)
    TextView webViewForeward;
    @BindView(R.id.webView_refresh)
    TextView webViewRefresh;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OtherUtil.setStatusBar(getWindow());
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);
        initView();
        initToolbar();
        initWebView();
        initWebViewSettings();
        //onHome();
        new Handler().postDelayed(() -> {
            Toast.makeText(BrowserActivity.this, "集成 X5 内核工作筹备中", Toast.LENGTH_SHORT).show();
            SoundUtil.ding(this, SoundUtil.NOTIFICATION2);
        }, 2000);
        Toast.makeText(this, "Bug 较多", Toast.LENGTH_SHORT).show();
        SoundUtil.ding(this, SoundUtil.ALERT);
    }

    private void initView() {
        webViewRf.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        webViewRf.setOnRefreshListener(this::onRefresh);
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_browser, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = contentView.findViewById(R.id.pop_student);
        TextView tv2 = contentView.findViewById(R.id.pop_teacher);
        tv1.setText("sdfsdf");
        tv2.setText("sdfsdf");
        tv1.setOnClickListener(v -> mPopWindow.dismiss());
        tv2.setOnClickListener(v -> mPopWindow.dismiss());
        //相对位置 以mMenuTv为坐标,
        mPopWindow.showAtLocation(webView, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
            mPopWindow = null;
        }
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        return super.onTouchEvent(event);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initWebView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //webview加载完成之后重新测量webview的高度
                /*
                ViewGroup.LayoutParams params = webView.getLayoutParams();
                params.width = getResources().getDisplayMetrics().widthPixels;
                params.height = webView.getHeight() - webViewNestedScrollView.getHeight();
                webView.loadUrl("javascript:App.resize(document.body.getBoundingClientRect().height)");
                webView.setLayoutParams(params);*/
                webViewRefresh.setText(R.string.fa_refresh_before);
                webViewRf.setRefreshing(false);
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                completeRefresh();
                android.widget.Toast.makeText(BrowserActivity.this, "BrowserActivity: onReceivedError(): \" + \"这是什么情况？", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Log.d("hbj", "BrowserActivity: onReceivedError():  " + error.getErrorCode() + error.getDescription());
                }
            }
        });//APP内执行
        webView.addJavascriptInterface(this, "App");

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                webViewPb.setVisibility(View.VISIBLE);
                webViewPb.setProgress(newProgress);
                if (newProgress == 100) webViewPb.setVisibility(View.GONE);
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                webViewTitle.setText(title);
                super.onReceivedTitle(view, title);
            }
        });
    }

    @JavascriptInterface

    public void resize(final float height) {

        runOnUiThread(() -> {
            //Toast.makeText(getActivity(), height + "", Toast.LENGTH_LONG).show();
            webView.setLayoutParams(new FrameLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, (int) (height * getResources().getDisplayMetrics().density)));
        });
    }

    private void initWebViewSettings() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        // settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    @OnClick({R.id.webView, R.id.path_et, R.id.webView_toolbar, R.id.webView_rf, R.id.webView_pb, R.id.home, R.id.webView_load, R.id.webView_menu, R.id.webView_backward, R.id.webView_home, R.id.webView_foreward, R.id.webView_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.webView_rf:
                break;
            case R.id.webView:
                break;
            case R.id.path_et:
                break;
            case R.id.toolbar:
                break;
            case R.id.webView_pb:
                break;
            case R.id.home:
                finish();
                break;
            case R.id.webView_load:
                load();
                break;
            case R.id.webView_menu:
                if (mPopWindow != null && mPopWindow.isShowing()) mPopWindow.dismiss();
                else showPopupWindow();
                break;
            case R.id.webView_backward:
                onBackwards();
                break;
            case R.id.webView_home:
                onHome();
                break;
            case R.id.webView_foreward:
                onForwards();
                break;
            case R.id.webView_refresh:
                if (webViewRf.isRefreshing()) {
                    webView.stopLoading();
                    completeRefresh();
                }
                onRefresh();
                break;
        }
    }

    public void load() {
        String trim = pathEt.getText().toString().trim();
        if (trim.isEmpty())
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
        else {
            //webView.loadUrl("https://" + trim);
            webView.loadUrl(trim);
            Log.d("hbj", getLocalClassName() + ": load(): " + trim);
        }
    }

    public void onHome() {
        webView.loadUrl("https://www.swjtu.edu.cn");
    }

    public void onRefresh() {
        webView.reload();
        webViewRefresh.setText(R.string.fa_times_before);
    }

    public void completeRefresh() {
        webViewRefresh.setText(R.string.fa_refresh_before);
        webViewRf.setRefreshing(false);
    }

    public void onBackwards() {
        webView.goBack();
    }

    public void onForwards() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        //webView.resumeTimers();//恢复webview的状态（不靠谱）
        webView.onResume();//激活webView的状态，能正常加载网页
    }

    @Override
    protected void onPause() {
        super.onPause();
        //当页面被失去焦点被切换到后台不可见状态，需要执行onPause
        //通过onPause动作通知内核暂停所有的动作，比如DOM的解析、plugin的执行、JavaScript执行。
        webView.onPause();
        //当应用程序(存在webView)被切换到后台时，这个方法不仅仅针对当前的 webView 而是全局的全应用程序的 webView
        //它会暂停所有 webView 的 layout ， parsing ，javascripttimer。降低CPU功耗。（不靠谱）
        //webView.pauseTimers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((ViewGroup) findViewById(R.id.webView_rf)).removeView(webView);
        webView.destroy();
    }
}
