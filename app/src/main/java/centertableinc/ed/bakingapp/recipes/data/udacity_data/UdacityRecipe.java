package centertableinc.ed.bakingapp.recipes.data.udacity_data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import centertableinc.ed.bakingapp.recipes.data.Recipe;
import centertableinc.ed.bakingapp.recipes.data.RecipeIngredient;
import centertableinc.ed.bakingapp.recipes.data.RecipeStep;


public class UdacityRecipe implements Recipe {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String INGREDIENTS = "ingredients";
    private static final String STEPS = "steps";
    private static final String SERVINGS = "servings";
    private static final String IMAGE = "image";

    @SerializedName(ID)
    private String recipeId;

    @SerializedName(NAME)
    private String recipeName;

    @SerializedName(INGREDIENTS)
    private ArrayList<Ingredient> ingredientList;

    @SerializedName(STEPS)
    private ArrayList<Step> stepList;

    @SerializedName(SERVINGS)
    private Integer recipeServings;

    @SerializedName(IMAGE)
    private String recipeImage;


    @Override
    public String getRecipeId() {
        return recipeId;
    }

    @Override
    public String getRecipeName() {
        return recipeName;
    }

    @Override
    public ArrayList getIngredientList() {
        return ingredientList;
    }

    @Override
    public ArrayList getStepList() {
        return stepList;
    }

    @Override
    public Integer getRecipeServings() {
        return recipeServings;
    }

    @Override
    public String getRecipeImage() {
        return recipeImage;
    }
}
