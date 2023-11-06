package com.serdararici.fotografpaylasmafirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.serdararici.fotografpaylasmafirebase.databinding.RecyclerRowBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext

class HaberRecyclerAdapter(val postList : ArrayList<Post>) : RecyclerView.Adapter<HaberRecyclerAdapter.PostHolder>() {


    class PostHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HaberRecyclerAdapter.PostHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row, parent, false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: HaberRecyclerAdapter.PostHolder, position: Int) {
        holder.binding.recyclerRowKullaniciEmail.text = postList[position].kullaniciEmail
        holder.binding.recyclerRowYorumText.text = postList[position].kullaniciYorum
        // Picasso internetten fotoğrafı çekip uygulamada kullanabilmemize yarayan bir kütüphane
        // önce Picasso kütüphanesini implement ettik
        Picasso.get().load(postList[position].gorselUrl).into(holder.binding.recyclerRowImageView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}