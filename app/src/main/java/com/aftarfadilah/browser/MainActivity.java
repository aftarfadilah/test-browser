package com.aftarfadilah.browser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText et_url;
    private WebView wv_main;
    private String mainUrl;
    private ImageButton btn_go, btn_back, btn_forward, btn_refresh, btn_clear;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainUrl = "https://start.duckduckgo.com";

        et_url = findViewById(R.id.et_url);

        wv_main = findViewById(R.id.wv_mainnn);
        wv_main.getSettings().setJavaScriptEnabled(true);
        wv_main.loadUrl(mainUrl);

        btn_go = findViewById(R.id.btn_go);
        btn_back = findViewById(R.id.btn_back);
        btn_forward = findViewById(R.id.btn_forward);
        btn_refresh = findViewById(R.id.btn_refresh);
        btn_clear = findViewById(R.id.btn_clear);

        et_url.setText(mainUrl);

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv_main.loadUrl(et_url.getText().toString());
                 btn_forward.getBackground().setAlpha(50);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wv_main.canGoBack()) {
                    wv_main.goBack();
                } else {
                    Toast.makeText(MainActivity.this, "Can't go back",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wv_main.canGoForward()) {
                    wv_main.goForward();
                } else {
                    Toast.makeText(MainActivity.this, "Can't go Forward",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv_main.reload();
                Toast.makeText(MainActivity.this, "Refreshing Web...",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv_main.clearHistory();
//                TODO: History gurung mari dhol
                Log.d("History", "History mu bersih");
                Toast.makeText(MainActivity.this, "History cleared", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
