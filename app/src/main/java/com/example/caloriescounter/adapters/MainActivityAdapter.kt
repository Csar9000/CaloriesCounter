package com.example.caloriescounter.adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caloriescounter.R
import com.example.caloriescounter.database.EntityFood
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.content.DialogInterface
import com.example.caloriescounter.activities.MainActivity


class MainActivityAdapter(val foods: List<EntityFood>,val context: Context)
    : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MainActivityAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item,parent,false)
        return ViewHolder(v,parent)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainActivityAdapter.ViewHolder, position: Int) {
        holder.title.text = foods[position].title
        holder.carbons.text = foods[position].carbons
        holder.cal.text = "K: ${foods[position].calories.toString()}"
        holder.fats.text = foods[position].fat
        holder.proteins.text = foods[position].protein


        holder.itemView.setOnLongClickListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Item deletion")
            dialog.setMessage("Do you want to delete this item?")
            dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                (context as MainActivity).deleteItem(foods[position])
            })
            dialog.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->
            })
            dialog.setNeutralButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            })
            dialog.show()

            return@setOnLongClickListener(true)
        }

    }

    override fun getItemCount(): Int {
        return foods.size
    }


    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    class ViewHolder(itemView:View,viewGroup: ViewGroup): RecyclerView.ViewHolder(itemView){
        var title: TextView  = itemView.findViewById(R.id.tvTitle)
        var carbons: TextView = itemView.findViewById(R.id.tvCarbons)
        var cal: TextView = itemView.findViewById(R.id.tvCal)
        var proteins: TextView = itemView.findViewById(R.id.tvProtein)
        var fats: TextView = itemView.findViewById(R.id.tvFats)
        var imageView: ImageView = itemView.findViewById(R.id.ivFood)

        init {
            imageView.visibility = View.GONE


        }
    }
}