package com.example.myapplication

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.models.Quote
import com.google.gson.Gson

object DataManager {

    var data = emptyArray<Quote>()
    var correctQuote: Quote? = null
    var currectPage = mutableStateOf(Pages.LISTING)
    var isDataLoaded = mutableStateOf(false)

    fun loadAssetsFromFile(context: Context) {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quote>::class.java)
        isDataLoaded.value = true
    }

    fun switchPages() {
        if (currectPage.value == Pages.LISTING) {
            currectPage.value = Pages.DETAIL
        } else {
            currectPage.value = Pages.LISTING
        }
    }
}