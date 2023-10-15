package com.example.foodapilearning.data.response

import com.google.gson.annotations.SerializedName
//model yang dibuat untuk parse json
data class ListMenuResponse(

	@field:SerializedName("meals")
	val meals: ArrayList<MealsItem?>? = null
)

data class MealsItem(

	@field:SerializedName("strMealThumb")
	val strMealThumb: String? = null,

	@field:SerializedName("idMeal")
	val idMeal: String? = null,

	@field:SerializedName("strMeal")
	val strMeal: String? = null
)
