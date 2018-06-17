package centertableinc.ed.bakingapp.recipes.data;

import android.os.Parcelable;

public interface RecipeIngredient extends Parcelable {
    String getIngredientQuantity();
    String getIngredientMeasure();
    String getIngredientName();
}
