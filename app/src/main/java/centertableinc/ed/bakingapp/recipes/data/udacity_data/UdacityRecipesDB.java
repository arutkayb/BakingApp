package centertableinc.ed.bakingapp.recipes.data.udacity_data;

import android.util.Log;

import java.util.List;

import centertableinc.ed.bakingapp.recipes.AsyncDataListener;
import centertableinc.ed.bakingapp.recipes.data.RecipesDB;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UdacityRecipesDB implements RecipesDB{

    public void requestRecipeList(final AsyncDataListener listener){
        UdacityRecipesInterface udacityRecipes = RetrofitInstance.getRetrofitInstance()
                .create(UdacityRecipesInterface.class);

        Call<List<UdacityRecipe>> call = udacityRecipes.getRecipeList();

        call.enqueue(new Callback<List<UdacityRecipe>>() {
            @Override
            public void onResponse(Call<List<UdacityRecipe>> call, Response<List<UdacityRecipe>> response) {
                listener.onDataLoad(response.body());
            }

            @Override
            public void onFailure(Call<List<UdacityRecipe>> call, Throwable t) {
                Log.e(getClass().getName(), "Network error");
            }
        });
    }
}
