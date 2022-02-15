package com.sapar.froyoapplication.ui.establishment_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sapar.froyoapplication.common.const.PIZZA_URL
import com.sapar.froyoapplication.data.menu.CategoryMeal
import com.sapar.froyoapplication.data.menu.Meal

class EstablishmentDetailViewModel : ViewModel() {
    val liveData: MutableLiveData<List<CategoryMeal>> = MutableLiveData()

    init {
        fetchAllCategories()
    }

    var totalPrice = 0

    private fun fetchAllCategories() {
        val meals1 = Meal(
            id = "1",
            name = "Маргаритта",
            imageUrl = PIZZA_URL,
            price = 1700,
            description = "23456"
        )
        val meals2 = Meal(
            id = "2",
            name = "Маргаритта",
            imageUrl = PIZZA_URL,
            price = 1700,
            description = "23456"
        )
        val meals3 = Meal(
            id = "3",
            name = "Маргаритта3",
            imageUrl = PIZZA_URL,
            price = 1800,
            description = "23456"
        )
        val categoryPizza = CategoryMeal(
            1,
            category = "Пицца",
            meals = listOf(
                meals1,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3
            )
        )
        val categoryDrinks = CategoryMeal(
            2,
            category = "Напитки",
            meals = listOf(
                meals1,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3
            )
        )
        val categoryFruits = CategoryMeal(
            3,
            category = "Фрукты",
            meals = listOf(
                meals1,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3
            )
        )
        val categoryFruits1 = CategoryMeal(
            3,
            category = "Фрукты1",
            meals = listOf(
                meals1,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3
            )
        )
        val categoryFruits12 = CategoryMeal(
            3,
            category = "Фрукты12",
            meals = listOf(
                meals1,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3
            )
        )
        val categoryFruits13 = CategoryMeal(
            3,
            category = "Фрукты13",
            meals = listOf(
                meals1,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3
            )
        )
        val categoryFruits14 = CategoryMeal(
            3,
            category = "Фрукты14",
            meals = listOf(
                meals1,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3,
                meals2,
                meals3
            )
        )
        val categoryFruits15 = CategoryMeal(
            3,
            category = "Фрукты15",
            meals = listOf(
                meals1
            )
        )

        liveData.postValue(
            listOf(
                categoryPizza,
                categoryFruits,
                categoryDrinks,
                categoryFruits1,
                categoryFruits12,
                categoryFruits13,
                categoryFruits14,
                categoryFruits15
            )
        )
    }
}