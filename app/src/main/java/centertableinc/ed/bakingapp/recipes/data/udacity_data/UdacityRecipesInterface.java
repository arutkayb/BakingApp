package centertableinc.ed.bakingapp.recipes.data.udacity_data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UdacityRecipesInterface {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<UdacityRecipe>> getRecipeList();
}
