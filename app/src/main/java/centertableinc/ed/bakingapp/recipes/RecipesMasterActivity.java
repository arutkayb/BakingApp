package centertableinc.ed.bakingapp.recipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.Recipe;
import centertableinc.ed.bakingapp.recipes.data.RecipeList;
import centertableinc.ed.bakingapp.recipes.data.RecipesDB;
import centertableinc.ed.bakingapp.recipes.data.udacity_data.UdacityRecipesDB;

public class RecipesMasterActivity extends AppCompatActivity implements AsyncDataListener<RecipeList> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_master);

        RecipesDB service = new UdacityRecipesDB();
        requestForRecipes(service);
    }

    void requestForRecipes(RecipesDB service){
        service.requestRecipeList(this);
    }

    @Override
    public void onDataLoad(RecipeList list){
        bindDataToView(list);
    }

    private void bindDataToView(RecipeList list){
        List<Recipe> recipeList = list.getRecipeList();
        //TODO: fill here
    }

}
