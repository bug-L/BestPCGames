package com.example.bestgamespc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class AmazonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amazon)

        title = "Best PC Games"
        
        val webView = findViewById<WebView>(R.id.webView)

        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.amazon.com/gaming-pc/s?k=gaming+pc")
        // this will enable the javascript settings
        webView.settings.javaScriptEnabled = true
        // enable zoom feature
        webView.settings.setSupportZoom(true)

    }

    // navigate to previous webpage when back is pressed
    override fun onBackPressed() {

        val webView = findViewById<WebView>(R.id.webView)
        // if webview can go back it will go back
        if (webView.canGoBack())
            webView.goBack()
        // if webview cannot go back
        // it will exit the activity
        else
            super.onBackPressed()
    }
}