package ir.hamergallery.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.Gravity;

public class MainActivity extends Activity {

    private WebView web;
    private LinearLayout splash;
    private ProgressBar bar;
    private static final String URL = "https://hamergallery.ir/";
    private static final String HOST = "hamergallery.ir";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.parseColor("#101426"));

        // ---- Splash ----
        splash = new LinearLayout(this);
        splash.setOrientation(LinearLayout.VERTICAL);
        splash.setGravity(Gravity.CENTER);
        splash.setBackgroundColor(Color.parseColor("#101426"));

        ImageView logo = new ImageView(this);
        logo.setImageResource(R.mipmap.ic_launcher);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(280, 280);
        logo.setLayoutParams(lp);
        ScaleAnimation pop = new ScaleAnimation(0.2f, 1f, 0.2f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        pop.setDuration(900);
        pop.setStartOffset(100);
        logo.startAnimation(pop);
        splash.addView(logo);

        TextView name = new TextView(this);
        name.setText("هامرگالری");
        name.setTextColor(Color.WHITE);
        name.setTextSize(30);
        name.setTypeface(null, android.graphics.Typeface.BOLD);
        name.setGravity(Gravity.CENTER);
        name.setPadding(0, 40, 0, 0);
        splash.addView(name);

        TextView tag = new TextView(this);
        tag.setText("محصولات چوبی فانتزی 🎁");
        tag.setTextColor(Color.parseColor("#929AB2"));
        tag.setTextSize(14);
        tag.setGravity(Gravity.CENTER);
        tag.setPadding(0, 12, 0, 40);
        splash.addView(tag);

        bar = new ProgressBar(this);
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(80, 80);
        bar.setLayoutParams(bp);
        splash.addView(bar);

        root.addView(splash);

        // ---- WebView ----
        web = new WebView(this);
        WebSettings ws = web.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setLoadWithOverviewMode(true);
        ws.setUseWideViewPort(true);
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        ws.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri u = Uri.parse(url);
                if (HOST.equals(u.getHost()) || u.getHost() != null && u.getHost().endsWith("." + HOST)) {
                    return false; // inside app
                }
                startActivity(new Intent(Intent.ACTION_VIEW, u)); // external links
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                splash.setVisibility(View.GONE);
                splash.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
                super.onPageFinished(view, url);
            }
        });
        web.loadUrl(URL);
        root.addView(web, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        setContentView(root);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
