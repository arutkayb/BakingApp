package centertableinc.ed.bakingapp.recipes.recipe_step_details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.RecipeStep;

public class DetailedRecipeStepsActivity extends AppCompatActivity {
    public static final String RECIPE_STEP_LIST_PARCELABLE_KEY = "recipe_step_list";
    ArrayList<RecipeStep> recipeSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_details);

        if(!getRecipeStepsFromIntent())
            finish();

        bindDetailedSteps();
    }

    private boolean getRecipeStepsFromIntent(){
        boolean ret = false;

        Intent intent = getIntent();
        recipeSteps = intent.getParcelableArrayListExtra(RECIPE_STEP_LIST_PARCELABLE_KEY);

        if(recipeSteps == null){
            Log.e(getClass().getName(), "RecipeStepList is null. Finishing the activity.");
            ret = false;
        }else{
            ret = true;
        }

        return ret;
    }

    private void bindDetailedSteps(){
        //TODO: bind the recyclerView here
    }
}
