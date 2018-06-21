package centertableinc.ed.bakingapp.recipes.data.udacity_data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import centertableinc.ed.bakingapp.recipes.AsyncDataListener;
import centertableinc.ed.bakingapp.recipes.data.RecipeList;
import centertableinc.ed.bakingapp.recipes.data.RecipesDB;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UdacityRecipesDB implements RecipesDB{

    public void requestRecipeList(final AsyncDataListener listener){
        UdacityRecipesInterface udacityRecipes = RetrofitInstance.getRetrofitInstance()
                .create(UdacityRecipesInterface.class);

        Call<ArrayList<UdacityRecipe>> call = udacityRecipes.getRecipeList();

        call.enqueue(new Callback<ArrayList<UdacityRecipe>>() {
            @Override
            public void onResponse(Call<ArrayList<UdacityRecipe>> call, Response<ArrayList<UdacityRecipe>> response) {
                try {
                    listener.onDataLoad(new RecipeList(response.body()));
                }catch (RecipeList.InsufficientListTypeException ex){
                    Log.e(getClass().getName(), "Returned data is not a type of List<Recipe>, exception: " + ex.toString());
                }catch (Exception ex){
                    Log.e(getClass().getName(), "Exception: " + ex.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UdacityRecipe>> call, Throwable t) {
                Log.e(getClass().getName(), "Network error");
            }
        });
    }
}
