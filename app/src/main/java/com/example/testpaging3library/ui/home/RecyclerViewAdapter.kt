package com.example.testpaging3library.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testpaging3library.data.model.UnsplashImage
import com.example.testpaging3library.databinding.RecyclerViewItemBinding

class RecyclerViewAdapter(
    private val listener : RecyclerViewListener
) : PagingDataAdapter<UnsplashImage,ViewHolder>(diffCallback)
{



    companion object
    {
        private val diffCallback = object : DiffUtil.ItemCallback<UnsplashImage>()
        {
            override fun areItemsTheSame(oldItem: UnsplashImage, newItem: UnsplashImage): Boolean
            {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: UnsplashImage, newItem: UnsplashImage): Boolean
            {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = getItem(position)
        Log.d("Kerolos", "bind: $position")
        if(image != null)
        {
            listener.stopShimmerEffect()

            Log.d("Kerolos", "bind: ${image.urls.regular}")
            holder.bind(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }
}

class ViewHolder(val binding : RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(image : UnsplashImage)
    {

        binding.itemImageLikes.text = image.likes.toString()
        binding.itemImageName.text = image.user.username
        Glide.with(binding.itemImage).load(image.urls.regular).into(binding.itemImage)
    }

    companion object
    {
        fun create(parent : ViewGroup) : ViewHolder
        {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}