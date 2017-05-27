package com.nhahv.note.util.sharepreference

/**
 * Created by Hoang Van Nha on 5/27/2017.
 * <>>
 */

interface ISharePrefercence {
  operator fun <T> get(key: String, tClass: Class<T>): T?

  fun <T> put(key: String, value: T)

  fun clear()
}
