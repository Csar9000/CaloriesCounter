package com.example.caloriescounter.activities

import android.annotation.SuppressLint
import android.icu.text.CaseMap
import com.example.caloriescounter.adapters.FoodListAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caloriescounter.Food
import com.example.caloriescounter.R
import com.example.caloriescounter.apiService.InterfaceAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class FoodListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var search: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        manager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.rv_findFood)
        search = findViewById(R.id.search)
        recyclerView.setLayoutManager(manager);

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.notifyDataSetChanged()
                if (newText?.length ==0){
                    getDefaultItems()
                }
                else{
                    if (newText != null) {
                        getByTitleSearch(newText)
                    }
                }
                return false
            }

        })



        getDefaultItems()


    }

    private fun getDefaultItems(){
        val apiInterface = InterfaceAPI.create().getFoodList(5)
        recyclerView.adapter = null
        apiInterface.enqueue(object : Callback<List<Food>> {
            override fun onResponse(call: Call<List<Food>>?, response: Response<List<Food>>?) {
                adapter = FoodListAdapter(response?.body(), activity = this@FoodListActivity);
                recyclerView.adapter = adapter
            }
            override fun onFailure(call: Call<List<Food>>?, t: Throwable?) {
            }
        })
    }

    private fun getByTitleSearch(title: String){
        val apiInterface = InterfaceAPI.create().getFoodByTitle(title)
        recyclerView.adapter = null
        apiInterface.enqueue(object : Callback<List<Food>> {
            override fun onResponse(call: Call<List<Food>>?, response: Response<List<Food>>?) {
                adapter = FoodListAdapter(response?.body(), activity = this@FoodListActivity);
                recyclerView.adapter = adapter
            }
            override fun onFailure(call: Call<List<Food>>?, t: Throwable?) {
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}