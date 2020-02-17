package ru.guryanov.nettitest.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_post.*
import ru.guryanov.nettitest.R
import ru.guryanov.nettitest.data.entity.Post

class PostsAdapter(private val data: MutableList<Post>) :
    RecyclerView.Adapter<PostsAdapter.PostHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return PostHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_post,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bindView(data[position])
    }


    inner class PostHolder(containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindView(item: Post) {
            title_post.text = item.title
        }

    }


}