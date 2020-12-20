package com.example.bestgamespc

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
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
import androidx.core.view.marginLeft
import androidx.fragment.app.Fragment
import java.net.URL

class PhysicalStoresFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
            : View? {

        val view = inflater.inflate(
            R.layout.content_physical_stores,
            container,
            false)

        var layout = view.findViewById<LinearLayout>(R.id.physical_store_layout)

        val imageViewStore = layout.findViewById<ImageView>(R.id.imageViewPhysicalStore)
        imageViewStore.setImageResource(R.drawable.physical_store)

        val dm = DataManager(requireActivity())
        val storesCursor = dm.selectAllPhysicalStores()

        val linearLayout = LinearLayout(requireActivity())
        linearLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL

        //populate mutable list of games using cursor data
        while (storesCursor.moveToNext()) {

            val storeName = storesCursor.getString(1)
            val storeAddress = storesCursor.getString(2)
            val storePhone = storesCursor.getString(3)

            //Dynamically create textviews
            val textViewName = TextView(requireActivity())
            textViewName.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            textViewName.text = storeName
            textViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            textViewName.setTextColor(Color.DKGRAY)
            textViewName.setPadding(65, 20, 65, 1)
            linearLayout.addView(textViewName)

            val textViewAddress = TextView(requireActivity())
            textViewAddress.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            textViewAddress.text = storeAddress
            textViewAddress.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            textViewAddress.setTextColor(Color.GRAY)
            textViewAddress.setPadding(65, 1, 65, 1)
            linearLayout.addView(textViewAddress)


            val textViewPhoneNum = TextView(requireActivity())
            textViewPhoneNum.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            textViewPhoneNum.text = storePhone
            textViewPhoneNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            textViewPhoneNum.setTextColor(Color.BLUE)
            textViewPhoneNum.setPadding(65, 1, 65, 20)
            linearLayout.addView(textViewPhoneNum)

            textViewPhoneNum.setOnClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:$storePhone")
                startActivity(dialIntent)
            }

        }

        val scrollView = ScrollView(requireActivity())
        scrollView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        scrollView.addView(linearLayout)
        layout.addView(scrollView)

        return view
    }
}