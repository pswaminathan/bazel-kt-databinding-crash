package com.example.databinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.view.doOnDetach
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.lib.BR
import kotlin.math.max


/**
 * Generic RecyclerView adapter for binding a list of items.
 */
class DataBindingPagedListAdapter<T>(@IdRes private val itemLayout: Int, var itemContext: Any?)
    : PagedListAdapter<T, DataBindingPagedListAdapter.ViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        // Only called if areItemsTheSame
        return true
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, itemLayout, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            setVariable(BR.item, getItem(position))
            setVariable(BR.itemContext, itemContext)
            setVariable(BR.position, position)
            executePendingBindings()
        }
    }


    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    companion object DataBinding {
        @BindingAdapter("itemLayout", "itemContext", "pagedItems")
        @JvmStatic
        fun <T> setPagedItems(view: RecyclerView, @IdRes itemLayout: Int, itemContext: Any?, items: PagedList<T>?) {
            @Suppress("UNCHECKED_CAST")
            val adapter = (view.adapter as DataBindingPagedListAdapter<T>?) ?: DataBindingPagedListAdapter(itemLayout, itemContext)

            adapter.itemContext = itemContext
            adapter.submitList(items)
            view.adapter = adapter
        }
    }
}
