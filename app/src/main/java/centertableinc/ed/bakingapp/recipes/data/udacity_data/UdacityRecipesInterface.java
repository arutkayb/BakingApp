package centertableinc.ed.bakingapp.recipes.data.udacity_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UdacityRecipesInterface {
    @GET("baking.json")
    Call<List<UdacityRecipe>> getRecipeList();
}
