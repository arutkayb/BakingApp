package centertableinc.ed.bakingapp.recipes.recipe_step_details;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.RecipeStep;
import centertableinc.ed.bakingapp.recipes.recipe_details.RecipeDetailsFragment;

public class DetailedRecipeStepsActivity extends AppCompatActivity {
    public static final String RECIPE_STEP_LIST_PARCELABLE_KEY = "recipe_step_list";
    public static final String RECIPE_SELECTED_STEP_NO = "selected_step_no";
    public static final String RECIPE_SELECTED_STEP = "selected_step";

    ArrayList<RecipeStep> recipeSteps;
    RecipeStep selectedStep;
    int selectedStepNo;
    RecipeStepDetailsFragment recipeStepDetailsFragment;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_details);

        initialise();

        if(!getRecipeStepsFromIntent())
            finish();

        selectedStepNo = getIntent().getIntExtra(RECIPE_SELECTED_STEP_NO, 0);
        if(selectedStepNo > recipeSteps.size())
            selectedStepNo = 0;
        selectedStep = recipeSteps.get(selectedStepNo);

        bindDetailedSteps(selectedStep, selectedStepNo);
    }

    private void initialise(){
        fragmentManager = getSupportFragmentManager();
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

    private void bindDetailedSteps(RecipeStep step, int stepNo){
        if(recipeStepDetailsFragment == null)
            recipeStepDetailsFragment = RecipeStepDetailsFragment.newInstance(step, stepNo);
        else
            recipeStepDetailsFragment.setRecipeStep(step, stepNo);

        fragmentManager.beginTransaction()
                .replace(R.id.recipe_step_details_fragment_container, recipeStepDetailsFragment)
                .commit();
    }
}
