package com.rssnews.ua.fragment.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by Vladyslav Ulianytskyi on 28.11.2018.
 */

abstract class BaseHolder<T>(recyclerView: ViewGroup, @LayoutRes layoutId: Int, viewIds: IntArray? = null) : RecyclerView.ViewHolder(createView(recyclerView, layoutId)), View.OnClickListener, LayoutContainer {

    protected val context: Context = itemView.context

    private var listener: OnItemClickListener<T>? = null

    private var item: T? = null

    override val containerView: View = itemView

    init {
        initListeners(viewIds)
    }

    private fun initListeners(viewIds: IntArray?) {
        itemView.setOnClickListener(this)
        viewIds?.forEach { itemView.findViewById<View>(it)?.setOnClickListener(this) }
    }

    internal fun bindModel(model: T) {
        this.item = model
        bindItem(model)
    }

    abstract fun bindItem(model: T)

    override fun onClick(v: View) {
        item?.let { model -> listener?.onItemViewClick(v, model) }
    }

    fun setOnItemClickListener(listener: OnItemClickListener<T>?) {
        this.listener = listener
    }

    protected fun getString(@StringRes stringId: Int): String = context.getString(stringId)

    protected fun getColorStateList(@ColorRes colorId: Int) = ContextCompat.getColorStateList(context, colorId)

    interface OnItemClickListener<T> {
        fun onItemViewClick(view: View, model: T)
    }

    private companion object {
        fun createView(recyclerView: ViewGroup, @LayoutRes id: Int): View {
            return LayoutInflater.from(recyclerView.context).inflate(id, recyclerView, false)
        }
    }
}
