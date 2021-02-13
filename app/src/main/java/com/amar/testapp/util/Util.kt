package com.amar.testapp.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.text.SimpleDateFormat

class Util {

    companion object {

        fun getDateAsString(date: String) : String {
            val format = "yyyy-MM-dd'T'HH:mm:ssX"
            val date1 = SimpleDateFormat(format).parse(date)
            val format2 = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            return  format2.format(date1)
        }

        fun openLinkInBrowser(context: Context, url: String) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }

    }

}