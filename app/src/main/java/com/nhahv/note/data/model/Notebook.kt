package com.nhahv.note.data.model

/**
 * Created by Hoang Van Nha on 5/27/2017.
 * <.
 */

class Notebook : Comparable<Notebook> {
    var id: Long = System.currentTimeMillis()
    var key: String? = null
    var title: String? = null
    var content: String? = null
    var place: String? = null
    var date: Long? = System.currentTimeMillis()
    var time: String? = null
    var picture: ArrayList<String> = ArrayList()

    override fun compareTo(other: Notebook): Int {
        return (id - other.id).toInt()
    }
}
