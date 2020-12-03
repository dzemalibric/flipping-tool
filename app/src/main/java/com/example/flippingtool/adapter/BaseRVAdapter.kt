package com.example.flippingtool.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/

abstract class BaseRVAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var mItems: ArrayList<in RVModel> = ArrayList()

    constructor(items: ArrayList<in RVModel>) : this() {
        mItems = items
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return (mItems[position] as RVModel).mViewType.ordinal
    }

    fun setItems(items: ArrayList<in RVModel>?) {
        if (items.isNullOrEmpty()) {
            return
        }
        mItems = items
    }

    fun insertItems(items: ArrayList<out RVModel>?) {
        if (items.isNullOrEmpty()) {
            return
        }

        mItems.addAll(items)
        notifyItemRangeInserted(mItems.size.minus(items.size), items.size)
    }

    fun insertItem(item: RVModel?) {
        if (item == null) {
            return
        }
        mItems.add(item)
        notifyItemInserted(mItems.size.minus(1))
    }

    fun insertItem(item: RVModel?, position: Int) {
        if (item == null) {
            return
        }
        mItems.add(position, item)
        notifyItemInserted(position)
    }

    fun removeItem(item: RVModel) {
        mItems.remove(item)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        mItems.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeItems(viewType : RVModel.ViewType) {
        for (index in mItems.indices) {
            if ((mItems[index] as RVModel).mViewType == viewType) {
                mItems.removeAt(index)
                notifyItemRemoved(index)
            }
        }
    }

    fun contains(item : RVModel) : Boolean {
        return mItems.contains(item)
    }

    fun getItemAt(position: Int): RVModel? {
        return mItems.get(position) as RVModel
    }

    fun updateItem(item: RVModel, position: Int) {
        mItems[position] = item
        notifyItemChanged(position)
    }

    fun updateItems(items : ArrayList<out RVModel>,
                    bottomPosition : Int) {
        for (index in items.indices) {
            mItems[index + bottomPosition] = items[index]
        }
    }

}