package com.aftarfadilah.browser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText et_url;
    private WebView wv_main;
    private ImageButton btn_back;
    private ImageButton btn_forward;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String mainUrl = "https://start.duckduckgo.com/";


        wv_main = findViewById(R.id.wv_mainnn);

        wv_main.getSettings().setLoadWithOverviewMode(true);
        wv_main.getSettings().setJavaScriptEnabled(true);
        wv_main.getSettings().setUseWideViewPort(true);

        wv_main.setWebViewClient(new ourViewWebClient());

        et_url = findViewById(R.id.et_url);
        wv_main.loadUrl(mainUrl);

        ImageButton btn_go = findViewById(R.id.btn_go);
        btn_back = findViewById(R.id.btn_back);
        btn_forward = findViewById(R.id.btn_forward);
        ImageButton btn_refresh = findViewById(R.id.btn_refresh);
        ImageButton btn_clear = findViewById(R.id.btn_clear);

        checkHistory();

//        et_url.setText(mainUrl);

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(URLUtil.isValidUrl(et_url.getText().toString())) {
                    Log.d("Debug", "Go to -> " + et_url.getText().toString());
                    wv_main.loadUrl(et_url.getText().toString());
                    et_url.setText("");
                    checkHistory();
                } else {
                    Toast.makeText(MainActivity.this, "URL is not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebBackForwardList webBackForwardList = wv_main.copyBackForwardList();
                if(wv_main.canGoBack()) {
                    wv_main.goBack();
                    Log.d("Debug", "Back to -> " + webBackForwardList.getItemAtIndex(
                            webBackForwardList.getCurrentIndex()-1).getUrl());
//                    et_url.setText(webBackForwardList.getItemAtIndex(webBackForwardList.
//                            getCurrentIndex()-1).getUrl());
                } else {
                    Toast.makeText(MainActivity.this, "Can't go back",
                            Toast.LENGTH_SHORT).show();
                }
                checkHistory();
            }
        });

        btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebBackForwardList webBackForwardList = wv_main.copyBackForwardList();
                if(wv_main.canGoForward()) {
                    wv_main.goForward();
                    Log.d("Debug", "Forward to -> " + webBackForwardList.getItemAtIndex(
                            webBackForwardList.getCurrentIndex()+1).getUrl());
//                    et_url.setText(webBackForwardList.getItemAtIndex(webBackForwardList.
//                            getCurrentIndex()+1).getUrl());

                } else {
                    Toast.makeText(MainActivity.this, "Can't go Forward",
                            Toast.LENGTH_SHORT).show();
                }
                checkHistory();
            }
        });

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv_main.reload();
                Toast.makeText(MainActivity.this, "Refreshing Web...",
                        Toast.LENGTH_SHORT).show();
                Log.d("Debug", "Refreshing");
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv_main.clearHistory();
                Log.d("History", "History cleared");
                Toast.makeText(MainActivity.this, "History cleared", Toast.LENGTH_SHORT).show();
                checkHistory();
            }
        });

    }

    public void checkHistory() {
        if(wv_main.canGoBack()) {
            btn_back.setAlpha((float) 1);
            btn_back.setEnabled(true);
        } else {
            btn_back.setAlpha((float) 0.2);
            btn_back.setEnabled(false);
        }
        if(wv_main.canGoForward()) {
            btn_forward.setAlpha((float) 1);
            btn_forward.setEnabled(true);
        } else {
            btn_forward.setAlpha((float) 0.2);
            btn_forward.setEnabled(false);
        }
    }

}
