package centertableinc.ed.bakingapp.recipes.recipe_step_details;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

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

    Button recipeStepButtonPrevious, recipeStepButtonNext;

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

        if(savedInstanceState == null)
            bindDetailedSteps(selectedStep, selectedStepNo);
    }

    private void initialise(){
        fragmentManager = getSupportFragmentManager();

        recipeStepButtonPrevious = findViewById(R.id.recipe_step_button_previous);
        recipeStepButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeStep step = getPreviousStep();
                bindDetailedSteps(step, selectedStepNo);
            }
        });

        recipeStepButtonNext = findViewById(R.id.recipe_step_button_next);
        recipeStepButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeStep step = getNextStep();
                bindDetailedSteps(step, selectedStepNo);
            }
        });
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
        recipeStepDetailsFragment = null;

        recipeStepDetailsFragment = RecipeStepDetailsFragment.newInstance(step, stepNo);

        if(step != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.recipe_step_details_fragment_container, recipeStepDetailsFragment)
                    .commit();
        }

        updateButtonClickableStatus();
    }

    private void updateButtonClickableStatus(){
        recipeStepButtonPrevious.setClickable(true);
        recipeStepButtonNext.setClickable(true);

        if(selectedStepNo == recipeSteps.size() - 1){
            recipeStepButtonNext.setClickable(false);
        }else if(selectedStepNo == 0){
            recipeStepButtonPrevious.setClickable(false);
        }
    }

    private RecipeStep getNextStep(){
        RecipeStep resultStep = null;

        if(selectedStepNo < recipeSteps.size() - 1){
            selectedStepNo++;
            resultStep = recipeSteps.get(selectedStepNo);
        }

        return resultStep;
    }

    private RecipeStep getPreviousStep(){
        RecipeStep resultStep = null;

        if(selectedStepNo > 0){
            selectedStepNo--;
            resultStep = recipeSteps.get(selectedStepNo);
        }

        return resultStep;
    }
}
