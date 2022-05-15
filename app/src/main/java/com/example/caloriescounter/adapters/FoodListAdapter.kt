package com.example.caloriescounter.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caloriescounter.Food
import com.example.caloriescounter.R
import com.example.caloriescounter.database.DataBase
import com.example.caloriescounter.database.EntityFood
import com.squareup.picasso.Picasso

class FoodListAdapter(private val data: List<Food>?,private val activity:Activity) : RecyclerView.Adapter<FoodListAdapter.MyViewHolder>()  {

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.ivFood)
        var title: TextView = view.findViewById(R.id.tvTitle)
        var fats: TextView = view.findViewById(R.id.tvFats)
        var protein: TextView =  view.findViewById(R.id.tvProtein)
        var carbons: TextView = view.findViewById(R.id.tvCarbons)
        var cal: TextView = view.findViewById(R.id.tvCal)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 1
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(data?.get(position)?.image).into(holder.image)
        holder.title.text = data?.get(position)?.title
        holder.cal.text = "${holder.cal.text} ${data?.get(position)?.calories}"
        holder.carbons.text = "${holder.carbons.text} ${data?.get(position)?.carbs}"
        holder.fats.text = "${holder.fats.text} ${data?.get(position)?.fat}"
        holder.protein.text = "${holder.protein.text} ${data?.get(position)?.protein}"


        holder.itemView.setOnClickListener { // вызываем метод слушателя, передавая ему данные
            // Get the view model
            val rnds = (14..65000).random()
            val dataBase:DataBase = DataBase.getInstance(activity)!!
            dataBase.DAO().insert(EntityFood(rnds,title = data?.get(position)?.title.toString(),
                                            calories = data?.get(position)?.calories,
                                            fat = data?.get(position)?.fat.toString(),
                                            protein = data?.get(position)?.protein.toString(),
                                            carbons = data?.get(position)?.carbs.toString()))
            activity.onBackPressed();
        }
    }
}