package com.example.wangjiawang.schemedemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    /**
     * 第三方scheme
     */
    private static final String url = "mqq://";
    private static final String weiXinUrl ="weixin://";
    private static final String mainUrl ="wjw://app.main";
    private static final String loginUrl ="wjw://app.login";

    /**
     * 本地html文件
     */
    private static final String successUrl = "file:///android_asset/success.html";
    private static final String faildUrl = "file:///android_asset/fail.html";
    private static final String indexUrl = "file:///android_asset/index.html";
    private static final String localMainUrl = "file:///android_asset/main.html";
    private static final String localLoginUrl = "file:///android_asset/login.html";



    private WebView mWebview;
    private WebView mMainWebview;
    private WebView mLoginWebview;
    WebViewClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClient = initClient();
        configWebView(mClient);
    }

    private WebViewClient initClient() {
        return new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, String url) {
                Log.d(TAG,"执行了url");
//                String url = request.getUrl().toString();
                if(url.startsWith("http") || url.startsWith("https")) { //http与https协议开头的执行正常流程。即返回false系统自己处理。
                    return false;
                } else {    //其他的url开启一个Activity然后去调用原生APP
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                    if(intent.resolveActivity(getPackageManager()) == null) {
                        //说明系统不存在这个Activity
                        view.post(()->{
                            Toast.makeText(MainActivity.this,"应用未安装",Toast.LENGTH_SHORT).show();
                            view.loadUrl(faildUrl);
                        });
                    }else {
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        startActivity(intent);
                        //加载成功后跳转
                        view.post(()-> view.loadUrl(successUrl));
                    }
                }
                return true;
            }
        };
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void configWebView(WebViewClient client) {
        mWebview = findViewById(R.id.webview);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(client);

        mMainWebview = findViewById(R.id.main_webview);
        mMainWebview.getSettings().setJavaScriptEnabled(true);
        mMainWebview.setWebViewClient(client);

        mLoginWebview = findViewById(R.id.login_webview);
        mLoginWebview.getSettings().setJavaScriptEnabled(true);
        mLoginWebview.setWebViewClient(client);

        mWebview.loadUrl(indexUrl);
        mMainWebview.loadUrl(localMainUrl);
        mLoginWebview.loadUrl(localLoginUrl);
    }

    /**
     * 打开QQ
     */
    public void openQQ(View v) {
        loadUrl(url);
    }

    /**
     * 打开微信
     */
    public void openWeiXin(View v) {
        loadUrl(weiXinUrl);
    }

    /**
     * 打开主页
     */
    public void openMain(View v) {
        loadUrl(mainUrl);
    }

    public void openLogin(View v) {
        loadUrl(loginUrl);
    }

    private void loadUrl(String url) {
        mWebview.loadUrl(url);
    }
}
