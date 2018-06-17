package centertableinc.ed.bakingapp.recipes.data.udacity_data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

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


    protected Ingredient(Parcel in) {
        ingredientQuantity = in.readString();
        ingredientMeasure = in.readString();
        ingredient = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(ingredientQuantity);
        dest.writeString(ingredientMeasure);
        dest.writeString(ingredient);
    }

    public static final Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

}
