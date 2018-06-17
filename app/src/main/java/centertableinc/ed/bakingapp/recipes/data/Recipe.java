package centertableinc.ed.bakingapp.recipes.data;

import android.os.Parcelable;

import java.util.List;

public interface Recipe extends Parcelable {
    String getRecipeId();
    String getRecipeName();
    List<RecipeIngredient> getIngredientList();
    List<RecipeStep> getStepList();
    Integer getRecipeServings();
    String getRecipeImage();
}
