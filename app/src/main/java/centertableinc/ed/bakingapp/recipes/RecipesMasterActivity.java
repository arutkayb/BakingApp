package centertableinc.ed.bakingapp.recipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.RecipesDB;
import centertableinc.ed.bakingapp.recipes.data.udacity_data.UdacityRecipe;
import centertableinc.ed.bakingapp.recipes.data.udacity_data.UdacityRecipesDB;

public class RecipesMasterActivity extends AppCompatActivity implements AsyncDataListener<List<UdacityRecipe>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_master);

        requestForRecipes();
    }

    void requestForRecipes(){
        RecipesDB service = new UdacityRecipesDB();
        service.requestRecipeList(this);
    }

    @Override
    public void onDataLoad(List<UdacityRecipe> data){
        bindDataToView(data);
    }

    private void bindDataToView(List<UdacityRecipe> data){
        //TODO: fill here
    }

}
