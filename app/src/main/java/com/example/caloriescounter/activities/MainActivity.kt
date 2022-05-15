package com.example.caloriescounter.activities

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caloriescounter.Model.FoodViewModel
import com.example.caloriescounter.R
import com.example.caloriescounter.adapters.MainActivityAdapter
import com.example.caloriescounter.database.EntityFood

class MainActivity : AppCompatActivity() {
    private lateinit var model: FoodViewModel
    private lateinit var adapter: RecyclerView.Adapter<*>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.rvFoodList)
        val btnAdd = findViewById<Button>(R.id.btnAddFood)

        val tvCal = findViewById<TextView>(R.id.tvCaloriesVolume)
        val tvProt = findViewById<TextView>(R.id.tvProteinsVolume)
        val tvCarbons = findViewById<TextView>(R.id.tvCarbonsVolume)
        val tvFats = findViewById<TextView>(R.id.tvFatsVolume)

        val pbCal = findViewById<ProgressBar>(R.id.pbCalories)
        val pbProt = findViewById<ProgressBar>(R.id.pbProteins)
        val pbCarbons = findViewById<ProgressBar>(R.id.pbCarbons)
        val pbFats = findViewById<ProgressBar>(R.id.pbFats)


        // Get the view model
        model = ViewModelProviders.of(this).get(FoodViewModel::class.java)

        // Specify layout for recycler view
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,false)
        recyclerView.layoutManager = linearLayoutManager


        // Observe the model
        model.allFood.observe(this, Observer{ foods->
            // Data bind the recycler view
            adapter =  MainActivityAdapter(foods,this)
            recyclerView.adapter =adapter

            var f = 0;
            var p = 0;
            var c = 0;
            var cal = 0;

            for (food in foods){
                f += food.fat?.replace("g","")?.toInt()!!
                p += food.protein?.replace("g","")?.toInt()!!
                cal += food.calories?.toInt()!!
                c += food.carbons?.replace("g","")?.toInt()!!
            }

            val s = "/100"

            tvCal.text = "${cal}  /2000"
            tvCarbons.text = "${c}  $s"
            tvProt.text = "${p}  $s"
            tvFats.text = "${f}  $s"

            pbCal.progress = cal
            pbCarbons.progress = c
            pbProt.progress = p
            pbFats.progress = f


        })

        btnAdd.setOnClickListener{
            val intent = Intent(this@MainActivity, FoodListActivity::class.java)
            startActivity(intent)
        }

    }



fun deleteItem(food:EntityFood){
    model.delete(food)
}
}
