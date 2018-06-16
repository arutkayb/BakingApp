package centertableinc.ed.bakingapp.recipes.data;

import java.util.List;

interface Recipe {
    String getRecipeId();
    String getRecipeName();
    List<RecipeIngredient> getIngredientList();
    List<RecipeStep> getStepList();
    Integer getRecipeServings();
    String getRecipeImage();
}
