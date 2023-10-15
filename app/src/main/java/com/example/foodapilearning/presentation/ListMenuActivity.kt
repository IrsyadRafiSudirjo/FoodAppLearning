package com.example.foodapilearning.presentation

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapilearning.data.repository.ApiConfig
import com.example.foodapilearning.data.response.ListMenuResponse
import com.example.foodapilearning.data.response.MealsItem
import com.example.foodapilearning.databinding.ActivityListMenuBinding
import com.example.foodapilearning.domain.MenuAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ListMenuActivity : AppCompatActivity() {

    companion object {
        const val LOCATION_NAME = "location_name"
    }

    private lateinit var binding: ActivityListMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val location_name = intent.getStringExtra(LOCATION_NAME)

        Toast.makeText(this,location_name, Toast.LENGTH_LONG).show()
        val layoutManager = LinearLayoutManager(this)
        binding.rvListMenu.layoutManager = layoutManager

        if (location_name != null) {
            getMenu(location_name)
        }
    }

    private fun getMenu(locationName: String) {
        try {
            val client = ApiConfig.getApiService().getMenu(locationName)
            client.enqueue(object : Callback<ListMenuResponse> {
                override fun onResponse(
                    call: Call<ListMenuResponse>,
                    response: Response<ListMenuResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            setMenuData(responseBody)
                        }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<ListMenuResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        } catch (e: Exception) {
            // handler
            print(e)
        }

    }

    private fun showSelectedMeals(meal: MealsItem) {
        Toast.makeText(this, "Kamu memilih " + meal.idMeal, Toast.LENGTH_SHORT).show()
    }

    private fun setMenuData(menuList: ListMenuResponse) {
        val adapter = MenuAdapter(menuList.meals)
        //adapter.submitList(consumerReviews)
        binding.rvListMenu.adapter = adapter
        adapter.setOnItemClickCallback(object : MenuAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MealsItem) {
                showSelectedMeals(data)
            }
        })
    }
}