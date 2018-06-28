package centertableinc.ed.bakingapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.Recipe;
import centertableinc.ed.bakingapp.recipes.data.RecipeIngredient;

public class SharedPrefsUtil {
    public static final String INGREDIENT_PREFS = "ingredient_prefs";
    public static final String LATEST_INGREDIENTS = "latest_ingredients";
    public static final String LATEST_RECIPE_NAME = "latest_recipe";

    public static void saveLastSelectedRecipeIngredientNames(Context context, Recipe recipe){
        SharedPreferences prefs = context.getSharedPreferences(
                INGREDIENT_PREFS, Context.MODE_PRIVATE);

        List<RecipeIngredient> ingredientList = recipe.getIngredientList();

        List<String> ingredients = new ArrayList<>();
        for(int i = 0; i < ingredientList.size(); i++){
            ingredients.add(ingredientList.get(i).getIngredientName());
        }

        Set<String> ingredientSet = new HashSet<>(ingredients);

        prefs.edit().putString(LATEST_RECIPE_NAME, recipe.getRecipeName()).apply();

        prefs.edit().putStringSet(LATEST_INGREDIENTS, ingredientSet).apply();
    }

    @Nullable
    public static List<String> retrieveLastSelectedRecipeIngredientNames(Context context){
        SharedPreferences prefs = context.getSharedPreferences(
                INGREDIENT_PREFS, Context.MODE_PRIVATE);

        Set<String> ingredientSet = prefs.getStringSet(LATEST_INGREDIENTS,null);

        return (new ArrayList<>(ingredientSet));
    }

    @Nullable
    public static String retrieveLastSelectedRecipeName(Context context){
        SharedPreferences prefs = context.getSharedPreferences(
                INGREDIENT_PREFS, Context.MODE_PRIVATE);

        String recipeName = prefs.getString(LATEST_RECIPE_NAME,context.getString(R.string.widget_header));

        return recipeName;
    }
}
