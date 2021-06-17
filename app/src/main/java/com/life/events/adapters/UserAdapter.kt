package com.life.events.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.life.events.R
import com.life.events.entity.User
import kotlinx.android.synthetic.main.user_item.view.*


class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    val diffCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<User>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.itemView.apply {
            val name = user.name
            val about = user.about
            val gender = user.gender
            val image = user.picture

            tvName.text = name
            tvGender.text = gender
            tvAbout.text = about
            Glide.with(this)
                .load(image)
                .centerCrop()
                .apply(RequestOptions())
                .placeholder(R.mipmap.ic_user_icon)
                .error(R.mipmap.ic_user_icon)
                .into(ivUserPicture)

            setOnClickListener {
                Log.d("TAG", "onBindViewHolder: $position")
                val bundle = bundleOf("id" to user._id)
                findNavController().navigate(R.id.action_usersFragment_to_userFragment, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}