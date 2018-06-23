package centertableinc.ed.bakingapp.recipes.recipe_step_details;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.Recipe;
import centertableinc.ed.bakingapp.recipes.data.RecipeStep;

public class RecipeStepDetailsFragment extends Fragment {
    private static final String PARCELABLE_KEY_RECIPE_STEP_DETAILS = "parcelable_key_recipe_step_details";
    RecipeStep recipeStep;

    public RecipeStepDetailsFragment(){
    }

    public static RecipeStepDetailsFragment newInstance(RecipeStep recipeStep){
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARCELABLE_KEY_RECIPE_STEP_DETAILS, recipeStep);

        RecipeStepDetailsFragment fragment = new RecipeStepDetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            RecipeStep recipeStep = bundle.getParcelable(PARCELABLE_KEY_RECIPE_STEP_DETAILS);

            if(recipeStep != null){
                setRecipeStep(recipeStep);
            }else{
                //TODO: Need for an action here
                Log.d(getClass().getName(), "Recipe in bundle is empty!");
            }
        }else{
            //TODO: Need for an action here
            Log.d(getClass().getName(), "Bundle is empty!");
        }
    }

    private void setRecipeStep(RecipeStep recipeStep){
        this.recipeStep = recipeStep;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_details, container, false);
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
