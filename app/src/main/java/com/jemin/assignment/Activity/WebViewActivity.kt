package com.jemin.assignment.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.jemin.assignment.R
import kotlinx.android.synthetic.main.activity_web_view.*
import android.webkit.WebViewClient


class WebViewActivity : AppCompatActivity() {

    var link : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        link = intent.getStringExtra("link") // 접속 URL

        webview_main_layout.webViewClient = WebViewClient()
        webview_main_layout.goBack()
        webview_main_layout.settings.javaScriptEnabled = true

        webview_main_layout.settings.builtInZoomControls = true
        webview_main_layout.settings.setSupportZoom(true)

        webview_main_layout.loadUrl(link)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview_main_layout.canGoBack()) {
            webview_main_layout.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
