package com.example.fetchcodingchallenge

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("listItems")
fun bindRecyclerView(recyclerView: RecyclerView, listItems: List<ListItem>?) {
    val adapter = recyclerView.adapter as ListItemAdapter
    listItems?.filter { it.name == null }
    adapter.submitList(listItems)
}

@BindingAdapter("id")
fun TextView.setIdText(item: ListItem?) {
    item?.let {
        text = String.format(context.getString(R.string.id_text_format), item.id)
    }
}

@BindingAdapter("listId")
fun TextView.setListIdText(item: ListItem?) {
    item?.let {
        text = String.format(context.getString(R.string.list_id_text_format), item.listId)
    }
}

@BindingAdapter("name")
fun TextView.setNameText(item: ListItem?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("status")
fun bindApiStatus(progressBar: ProgressBar, status: FetchApiStatus?) {
    when (status) {
        FetchApiStatus.LOADING -> {
            progressBar.visibility = VISIBLE
        }
        FetchApiStatus.ERROR -> {
            progressBar.visibility = GONE

        }
       else -> {
            progressBar.visibility = GONE
        }
    }
}

@BindingAdapter("statusImage")
fun bindStatusImage(statusImageView: ImageView, status: FetchApiStatus?) {
    when (status) {
        FetchApiStatus.LOADING -> {
            statusImageView.visibility = GONE
        }
        FetchApiStatus.ERROR -> {
            statusImageView.visibility = VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_refresh_48)
        }
       else -> {
            statusImageView.visibility = GONE
        }
    }
}

@BindingAdapter("statusText")
fun TextView.setStatusText(status: FetchApiStatus?) {
    when (status) {
        FetchApiStatus.LOADING -> {
            visibility = GONE
        }
        FetchApiStatus.ERROR -> {
            visibility = VISIBLE
            text = context.getString(R.string.error_message)
        }
        else -> {
            visibility = GONE
        }
    }
}