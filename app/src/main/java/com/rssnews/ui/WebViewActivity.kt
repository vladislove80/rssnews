package com.rssnews.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rssnews.R
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Created by Vladyslav Ulianytskyi on 07.12.2018.
 */

fun Context.webViewActivityIntent(link: String): Intent {
    return Intent(this, WebViewActivity::class.java).apply {
        putExtra(INTENT_KEY, link)
    }
}

private const val INTENT_KEY = "intent_key"

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
        webView.loadUrl(intent.getStringExtra(INTENT_KEY))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
}