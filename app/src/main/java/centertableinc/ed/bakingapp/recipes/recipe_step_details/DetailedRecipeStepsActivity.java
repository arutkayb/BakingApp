package centertableinc.ed.bakingapp.recipes.recipe_step_details;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.common.RecyclerViewListener;
import centertableinc.ed.bakingapp.recipes.data.RecipeStep;
import centertableinc.ed.bakingapp.recipes.recipe_step_details.recycler.DetailedRecipeStepsRecyclerAdapter;
import centertableinc.ed.bakingapp.util.RecyclerViewUtil;

public class DetailedRecipeStepsActivity extends AppCompatActivity {
    public static final String RECIPE_STEP_LIST_PARCELABLE_KEY = "recipe_step_list";
    public static final String RECIPE_SELECTED_STEP_MEDIA = "selected_step_media";
    public static final String RECIPE_SELECTED_STEP_NO = "selected_step_no";
    public static final String RECIPE_SELECTED_STEP = "selected_step";

    ArrayList<RecipeStep> recipeSteps;
    RecipeStep selectedStep;
    int selectedStepNo;
    int persistedScrollPosition;
    RecipeStepDetailsFragment recipeStepDetailsFragment;

    FragmentManager fragmentManager;

    Button recipeStepButtonPrevious, recipeStepButtonNext;
    RecyclerView detailedRecipeStepsRecyclerView;
    TextView recipeStepDetailsMediaHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_details);

        initialise();

        if(!getRecipeStepsFromIntent())
            finish();

        if(savedInstanceState == null) {
            selectedStepNo = getIntent().getIntExtra(RECIPE_SELECTED_STEP_NO, 0);
            if (selectedStepNo > recipeSteps.size())
                selectedStepNo = 0;
            selectedStep = recipeSteps.get(selectedStepNo);

            bindRecipeStepDetails();
        }else{
            selectedStep = savedInstanceState.getParcelable(RECIPE_SELECTED_STEP);
            selectedStepNo = savedInstanceState.getInt(RECIPE_SELECTED_STEP_NO);
        }

        bindDetailedStepsRecyclerView();

        updateMediaHeader();
    }

    private void initialise(){
        fragmentManager = getSupportFragmentManager();

        recipeStepDetailsMediaHeader = findViewById(R.id.recipe_step_details_media_header);
        recipeStepButtonPrevious = findViewById(R.id.recipe_step_button_previous);
        recipeStepButtonNext = findViewById(R.id.recipe_step_button_next);

        if(!isTwoPane()) {
            recipeStepButtonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setNextStep();
                    bindDetailedRecipeSteps();
                }
            });

            recipeStepButtonPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setPreviousStep();
                    bindDetailedRecipeSteps();
                }
            });
        }

        detailedRecipeStepsRecyclerView = findViewById(R.id.detailed_recipe_steps_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        detailedRecipeStepsRecyclerView.setLayoutManager(layoutManager);
    }

    private void bindDetailedRecipeSteps(){
        bindRecipeStepDetails();
        bindDetailedStepsRecyclerView();
        updateMediaHeader();
    }

    private void updateMediaHeader(){
        if(isTwoPane()) {
            String stepNumberText = selectedStep.getStepDescription();

            recipeStepDetailsMediaHeader.setText(stepNumberText);
        }
    }

    private boolean isTwoPane(){
        return (recipeStepButtonPrevious == null || recipeStepButtonNext == null);
    }


    private void bindDetailedStepsRecyclerView() {
        RecyclerViewUtil.setScrollPosition(detailedRecipeStepsRecyclerView, persistedScrollPosition);

        List<RecipeStep> steps;
        if (!isTwoPane()) {
            steps = new ArrayList<RecipeStep>();
            steps.add(selectedStep);
        } else {
            steps = recipeSteps;
        }

        DetailedRecipeStepsRecyclerAdapter detailedRecipeStepsRecyclerAdapter =
                new DetailedRecipeStepsRecyclerAdapter(this, new RecyclerViewListener() {
                    @Override
                    public void onItemSelectedEvent(int itemNo) {
                        if (isTwoPane()) {
                            setStep(itemNo);
                            bindDetailedRecipeSteps();
                        }
                    }
                }, steps);

        detailedRecipeStepsRecyclerView.setAdapter(detailedRecipeStepsRecyclerAdapter);
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

    private void bindRecipeStepDetails(){
        recipeStepDetailsFragment = RecipeStepDetailsFragment.newInstance(selectedStep.getStepVideoUrl());

        fragmentManager.beginTransaction()
                .replace(R.id.recipe_step_details_fragment_container, recipeStepDetailsFragment)
                .commit();

        updateButtonClickableStatus();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        bindDetailedStepsRecyclerView();
    }

    private void updateButtonClickableStatus(){
        if(!isTwoPane()) {
            recipeStepButtonPrevious.setClickable(true);
            recipeStepButtonNext.setClickable(true);

            if (selectedStepNo == recipeSteps.size() - 1) {
                recipeStepButtonNext.setClickable(false);
            } else if (selectedStepNo == 0) {
                recipeStepButtonPrevious.setClickable(false);
            }
        }
    }

    private void setStep(int stepNo){
        if(stepNo < recipeSteps.size() - 1 && stepNo >= 0){
            selectedStepNo = stepNo;
            selectedStep = recipeSteps.get(selectedStepNo);
        }
    }

    private void setNextStep(){
        int tempStepNo = selectedStepNo + 1;
        setStep(tempStepNo);
    }

    private void setPreviousStep(){
        int tempStepNo = selectedStepNo - 1;
        setStep(tempStepNo);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(RECIPE_SELECTED_STEP_NO, selectedStepNo);
        persistedScrollPosition = getFirstVisibleItemOfRecyclerView();

        outState.putParcelable(RECIPE_SELECTED_STEP, selectedStep);
    }

    private int getFirstVisibleItemOfRecyclerView(){
        return ((LinearLayoutManager)detailedRecipeStepsRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
    }
}
