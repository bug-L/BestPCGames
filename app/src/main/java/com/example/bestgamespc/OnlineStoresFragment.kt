package com.example.bestgamespc

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.URL

class OnlineStoresFragment  : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
            : View? {

        val view = inflater.inflate(
            R.layout.content_online_stores,
            container,
            false)

        var layout = view.findViewById<LinearLayout>(R.id.online_store_layout)

        val imageViewStore = layout.findViewById<ImageView>(R.id.imageViewOnlineStore)
        imageViewStore.setImageResource(R.drawable.online_store)

        val dm = DataManager(requireActivity())
        val storesCursor = dm.selectAllOnlineStores()

        val scrollView = ScrollView(requireActivity())
        scrollView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        val linearLayout = LinearLayout(requireActivity())
        linearLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL

        //populate mutable list of games using cursor data
        while (storesCursor.moveToNext()) {

            val storeName = storesCursor.getString(1)
            val storeLink = storesCursor.getString(2)

            //Dynamically create textviews
            val textViewName = TextView(requireActivity())
            textViewName.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            textViewName.text = storeName
            textViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
            textViewName.setTextColor(Color.DKGRAY)
            textViewName.setPadding(65, 30, 65, 1)
            linearLayout.addView(textViewName)

            val textViewLink =  TextView(requireActivity())
            textViewLink.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            textViewLink.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            textViewLink.setTextColor(Color.BLUE)
            textViewLink.setPadding(65, 1, 65, 30)
            textViewLink.text = HtmlCompat.fromHtml("<a href=\"$storeLink\">Open in browser" +
                    "</a>", HtmlCompat.FROM_HTML_MODE_LEGACY)
            textViewLink.movementMethod = LinkMovementMethod.getInstance()
            linearLayout.addView(textViewLink)

        }

        scrollView.addView(linearLayout)
        layout.addView(scrollView)

        return view
    }
}