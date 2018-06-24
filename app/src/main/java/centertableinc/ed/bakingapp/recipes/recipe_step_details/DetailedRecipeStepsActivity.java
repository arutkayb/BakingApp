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
    public static final String RECIPE_SELECTED_STEP_NO = "selected_step";

    ArrayList<RecipeStep> recipeSteps;
    RecipeStep selectedStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_details);

        if(!getRecipeStepsFromIntent())
            finish();

        int selectedStepNo = getIntent().getIntExtra(RECIPE_SELECTED_STEP_NO, 0);
        if(selectedStepNo > recipeSteps.size())
            selectedStepNo = 0;
        selectedStep = recipeSteps.get(selectedStepNo);

        bindDetailedSteps(selectedStep);
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

    private void bindDetailedSteps(RecipeStep step){
        //TODO: bind the step details here
    }
}
