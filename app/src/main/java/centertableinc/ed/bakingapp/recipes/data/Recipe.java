package centertableinc.ed.bakingapp.recipes.data;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public interface Recipe extends Parcelable {
    String getRecipeId();
    String getRecipeName();
    ArrayList<RecipeIngredient> getIngredientList();
    ArrayList<RecipeStep> getStepList();
    Integer getRecipeServings();
    String getRecipeImage();

}
