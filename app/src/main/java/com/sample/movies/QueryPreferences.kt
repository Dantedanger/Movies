package com.sample.movies

import android.content.Context
import android.preference.PreferenceManager
import androidx.core.content.edit

private const val PREF_SEARCH_QUERY = "searchQuery"
private const val PREF_SEARCH_QUERY_YEAR = "searchQueryYear"
object QueryPreferences {
    fun getStoredQuery(context: Context):
            String {
        val prefs =
            PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(PREF_SEARCH_QUERY, "")!!
    }
    fun setStoredQuery(context: Context, query: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(PREF_SEARCH_QUERY, query)
            .apply()
    }
    fun getStoredQueryByYear(context: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(PREF_SEARCH_QUERY_YEAR, "")!!
    }
    fun setStoredQueryByYear(context: Context, query: String, year:String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(PREF_SEARCH_QUERY, query)
            .putString(PREF_SEARCH_QUERY_YEAR, year)
            .apply()
    }

}
