package com.rssnews.ua.base

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by Vladyslav Ulianytskyi on 28.11.2018.
 */
abstract class BaseAdapter<T>(private val listener: BaseHolder.OnItemClickListener<T>? = null) : RecyclerView.Adapter<BaseHolder<T>>() {

    val items: MutableList<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> = onCreateHolder(parent, viewType).apply {
        setOnItemClickListener(listener)
    }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.bindModel(items[position])
        setTransitionName(holder.itemView, position)
    }

    //todo check it
    private fun setTransitionName(view: View, position: Int) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                view.getChildAt(i)?.run {
                    ViewCompat.setTransitionName(this, "${this.hashCode()} $position")
                }
            }
        } else {
            ViewCompat.setTransitionName(view, "${view.hashCode()} $position")
        }
    }

    abstract fun onCreateHolder(recyclerView: ViewGroup, viewType: Int): BaseHolder<T>

    override fun getItemCount(): Int {
        return items.size
    }

    fun isEmpty() = items.isEmpty()

    open fun addNewItems(newItems: MutableList<T>) {
        val nonNullItems = newItems.filterNot { it == null }
        val isAdded = items.addAll(nonNullItems)
        if (isAdded) notifyDataSetChanged()
    }

    fun removeItem(item: T) {
        val removedIndex = items.indexOf(item)
        if (removedIndex >= 0) {
            items.removeAt(removedIndex)
            notifyItemRemoved(removedIndex)
        }
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }
}