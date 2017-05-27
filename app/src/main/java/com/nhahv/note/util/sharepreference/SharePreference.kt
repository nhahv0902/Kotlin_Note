package com.nhahv.note.util.sharepreference

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.lang.*
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String

@Suppress("UNCHECKED_CAST")
/**
 * Created by Hoang Van Nha on 5/27/2017.
 * <>>
 */

class SharePreference private constructor(context: Context) : ISharePrefercence {
  private val mPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
      context)

  override fun <T> get(key: String, tClass: Class<T>): T {
    if (tClass == String::class.java) {
      return mPreferences.getString(key, null) as T
    } else if (tClass == Boolean::class.java) {
      return mPreferences.getBoolean(key, false) as T
    } else if (tClass == Float::class.java) {
      return mPreferences.getFloat(key, 0f) as T
    } else if (tClass == Int::class.java) {
      return mPreferences.getInt(key, 0) as T
    } else if (tClass == Long::class.java) {
      return mPreferences.getLong(key, 0) as T
    }
    return null as T
  }

  override fun <T> put(key: String, value: T) {
    val editor = mPreferences.edit()
    if (value is String) {
      editor.putString(key, value)
    } else if (value is Boolean) {
      editor.putBoolean(key, value)
    } else if (value is Float) {
      editor.putFloat(key, value)
    } else if (value is Int) {
      editor.putInt(key, value)
    } else if (value is Long) {
      editor.putLong(key, value)
    }
    editor.apply()
  }

  override fun clear() {
    mPreferences.edit().clear().apply()
  }

  companion object {
    private var mInstances: SharePreference? = null

    fun getIntances(context: Context): SharePreference {
      if (mInstances == null) {
        mInstances = SharePreference(context)
      }
      return mInstances as SharePreference
    }
  }
}
