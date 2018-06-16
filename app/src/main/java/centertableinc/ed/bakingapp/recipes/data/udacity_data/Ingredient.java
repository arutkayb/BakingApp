package centertableinc.ed.bakingapp.recipes.data.udacity_data;

import com.google.gson.annotations.SerializedName;

import centertableinc.ed.bakingapp.recipes.data.RecipeIngredient;

/*
    "ingredients": [
      {
        "quantity": 2,
        "measure": "CUP",
        "ingredient": "Graham Cracker crumbs"
      }
 */

public class Ingredient implements RecipeIngredient{
    private static final String QUANTITY = "quantity";
    private static final String MEASURE = "measure";
    private static final String INGREDIENT = "ingredient";

    @SerializedName(QUANTITY)
    private String ingredientQuantity;

    @SerializedName(MEASURE)
    private String ingredientMeasure;

    @SerializedName(INGREDIENT)
    private String ingredient;

    @Override
    public String getIngredientQuantity() {
        return ingredientQuantity;
    }

    @Override
    public String getIngredientMeasure() {
        return ingredientMeasure;
    }

    @Override
    public String getIngredientName() {
        return ingredient;
    }
}
