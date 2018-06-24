package centertableinc.ed.bakingapp.recipes.recipe_details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.common.RecyclerViewListener;
import centertableinc.ed.bakingapp.recipes.data.Recipe;
import centertableinc.ed.bakingapp.recipes.data.RecipeList;
import centertableinc.ed.bakingapp.recipes.data.Recipe;
import centertableinc.ed.bakingapp.recipes.recipe_details.recycler.BasicRecipeStepsRecyclerAdapter;
import centertableinc.ed.bakingapp.recipes.recipe_details.recycler.RecipeIngredientsRecyclerAdapter;
import centertableinc.ed.bakingapp.recipes.recipe_step_details.DetailedRecipeStepsActivity;

import static centertableinc.ed.bakingapp.recipes.recipe_step_details.DetailedRecipeStepsActivity.RECIPE_SELECTED_STEP_NO;
import static centertableinc.ed.bakingapp.recipes.recipe_step_details.DetailedRecipeStepsActivity.RECIPE_STEP_LIST_PARCELABLE_KEY;

public class RecipeDetailsFragment extends Fragment {
    private static final String PARCELABLE_KEY_RECIPE_DETAILS = "parcelable_key_recipe_details";
    View fragmentView;
    Recipe recipe;
    RecyclerView basicRecipeStepsRecyclerView, recipeIngredientsRecyclerView;

    BasicRecipeStepsRecyclerAdapter basicRecipeStepsRecyclerAdapter;
    RecipeIngredientsRecyclerAdapter recipeIngredientsRecyclerAdapter;

    public RecipeDetailsFragment() {
    }

    public static RecipeDetailsFragment newInstance(Recipe recipe){
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARCELABLE_KEY_RECIPE_DETAILS, recipe);

        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
        recipeDetailsFragment.setArguments(bundle);

        return recipeDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            Recipe recipe = bundle.getParcelable(PARCELABLE_KEY_RECIPE_DETAILS);

            if(recipe != null){
                setRecipe(recipe);
            }else{
                //TODO: Need for an action here
                Log.d(getClass().getName(), "Recipe in bundle is empty!");
            }
        }else{
            //TODO: Need for an action here
            Log.d(getClass().getName(), "Bundle is empty!");
        }
    }

    private void initialise(){
        basicRecipeStepsRecyclerView = fragmentView.findViewById(R.id.basic_recipe_steps_recycler_view);
        recipeIngredientsRecyclerView = fragmentView.findViewById(R.id.recipe_ingredients_recycler_view);
    }

    private void bindRecipeDetails(final Recipe recipe){
        basicRecipeStepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recipeIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        basicRecipeStepsRecyclerAdapter = new BasicRecipeStepsRecyclerAdapter(getContext(),
                new RecyclerViewListener() {
                    @Override
                    public void onItemSelectedEvent(int itemNo) {
                        Intent intent = new Intent(getActivity(), DetailedRecipeStepsActivity.class);
                        intent.putExtra(RECIPE_SELECTED_STEP_NO, recipe.getStepList());
                        intent.putExtra(RECIPE_STEP_LIST_PARCELABLE_KEY, recipe.getStepList());

                        startActivity(intent);
                    }
                },
                recipe.getStepList());
        basicRecipeStepsRecyclerView.setAdapter(basicRecipeStepsRecyclerAdapter);

        recipeIngredientsRecyclerAdapter = new RecipeIngredientsRecyclerAdapter(getContext(), recipe.getIngredientList());
        recipeIngredientsRecyclerView.setAdapter(recipeIngredientsRecyclerAdapter);
    }

    private void setRecipe(Recipe recipe){
        this.recipe = recipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        initialise();

        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindRecipeDetails(recipe);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //TODO: use recipeList to create recyclerView
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //TODO: avoid memory leaks
    }

}
