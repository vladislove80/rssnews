package com.rssnews.ua.base

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.category.view.*

/**
 * Created by Vladyslav Ulianytskyi on 28.11.2018.
 */
abstract class BaseAdapter<T>(private val listener: BaseHolder.OnItemClickListener<T>? = null) :
    RecyclerView.Adapter<BaseHolder<T>>() {

    val items: MutableList<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> =
        onCreateHolder(parent, viewType).apply {
            setOnItemClickListener(listener)
        }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        Log.d("BaseAdapter", "$holder, ${items[position]}, ${holder.containerView.tvCategory.chipBackgroundColor}")
        holder.bindModel(items[position])
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