package com.example.kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(private var context: Context, private var ds: List<Image>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    //class viewholder
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageResource: ImageView = itemView.findViewById(R.id.img_resource)
        val number: TextView = itemView.findViewById(R.id.tv_number)
        val imgPlus: ImageView = itemView.findViewById(R.id.imgPlus)
        val imgMinus: ImageView = itemView.findViewById(R.id.imgMinus)

    }

    private fun plusNumber(image: Image) {
        val currentNumber = image.number.toIntOrNull() ?: 0 // default là 0, nếu chuyển đổi thất bại
        image.number = (currentNumber + 1).toString()
    }

    private fun minusNumber(image: Image) {
        val currentNumber = image.number.toIntOrNull() ?: 0 // default là 0, nếu chuyển đổi thất bại
        image.number = (currentNumber - 1).toString()
    }

    fun checkOdd(image: Image) {
//        check number odd
        val currentNumber = image.number.toIntOrNull() ?: 0
        image.odd = currentNumber % 2 != 0
    }

    fun getListCheckOdd(){
      //lay ra danh sach le
        ds.forEach { checkOdd(it) }
    }


    fun updateData(newData: MutableList<Image>) {
//        update ds
        ds = newData
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        var image = ds[position] //vi tri
        holder.number.text = image.number

        //Glide image
        Glide.with(context)
            .load(image.resourceImage)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .error(R.drawable.cross)
            .into(holder.imageResource)

        holder.imgPlus.setOnClickListener() {
            plusNumber(image)
            holder.number.text = ds[position].number
        }
        holder.imgMinus.setOnClickListener() {
            minusNumber(image)
            holder.number.text = ds[position].number
        }
    }

}