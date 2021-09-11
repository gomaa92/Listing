package com.nagwa.listing.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseRecyclerViewAdapter<T, V : BaseRecyclerViewAdapter.BaseRecyclerViewHolder<T>>() :
    RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseRecyclerViewHolder<T>>() {

    private var mData: ArrayList<T>? = null

    init {
        this.mData = ArrayList<T>()
    }

    constructor(mData: ArrayList<T>, mLocale: Locale) : this() {
        this.mData = ArrayList<T>()
        addItems(mData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<T> {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(getLayout(viewType), parent, false)
        return getViewHolder(view, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<T>, position: Int) {

        mData?.get(position)?.let { holder.onBind(it) }
        // or make in bind take any not T
    }

    override fun getItemCount(): Int {
        return mData?.size!!
    }

    protected abstract fun getLayout(type: Int): Int
    protected abstract fun getViewHolder(view: View, type: Int): V


    fun addItems(items: List<T>?) {
        if (items != null) {
            mData?.addAll(items)
        }
        notifyDataSetChanged()
    }

    fun setItems(items: List<T>?) {
        mData!!.clear()
        mData!!.addAll(items!!)
        notifyDataSetChanged()
    }

    fun addItem(item: T) {
        mData!!.add(item)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mData!!.removeAt(position)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mData!!.clear()
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<T>? {
        return mData

    }

    abstract class BaseRecyclerViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun onBind(item: T)
    }
}