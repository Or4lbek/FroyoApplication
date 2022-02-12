package com.sapar.froyoapplication.ui.establishment_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sapar.froyoapplication.model.menu.CategoryMeal
import com.sapar.froyoapplication.model.menu.Meal

class EstablishmentDetailViewModel : ViewModel() {
    val liveData: MutableLiveData<List<CategoryMeal>> = MutableLiveData()
    val pizzaUrl =
        "https://www.simplyrecipes.com/thmb/8caxM88NgxZjz-T2aeRW3xjhzBg=/2000x1125/smart/filters:no_upscale()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__09__easy-pepperoni-pizza-lead-3-8f256746d649404baa36a44d271329bc.jpg"

    init {
        fetchAllCategories()
    }

    private fun fetchAllCategories() {
        val meals1 = Meal(
            id = "1",
            name = "Маргаритта",
            imageUrl = pizzaUrl,
            price = 1700,
            description = "23456"
        )
        val meals2 = Meal(
            id = "2",
            name = "Маргаритта",
            imageUrl = pizzaUrl,
            price = 1700,
            description = "23456"
        )
        val meals3 = Meal(
            id = "3",
            name = "Маргаритта3",
            imageUrl = pizzaUrl,
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

        liveData.postValue(
            listOf(
                categoryPizza,
                categoryFruits,
                categoryDrinks
            )
        )
    }
}