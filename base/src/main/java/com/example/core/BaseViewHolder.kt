package com.example.core

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val viewHashMap = hashMapOf<Int, View>()

    protected fun <T : View> getView(id: Int): T {
        var view = viewHashMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap.put(id, view)
        }
        return view as T
    }

    protected fun setText(id: Int, text: String?) {
        getView<TextView>(id).text = text
    }
}