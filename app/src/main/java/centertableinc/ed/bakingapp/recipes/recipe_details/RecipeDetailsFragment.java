package centertableinc.ed.bakingapp.recipes.recipe_details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.Recipe;
import centertableinc.ed.bakingapp.recipes.data.RecipeList;
import centertableinc.ed.bakingapp.recipes.data.Recipe;

public class RecipeDetailsFragment extends Fragment {
    private static final String PARCELABLE_KEY_RECIPE_DETAILS = "parcelable_key_recipe_details";
    Recipe recipe;

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
                bindRecipeDetails(recipe);
            }else{
                //TODO: Need for an action here
                Log.d(getClass().getName(), "Recipe in bundle is empty!");
            }
        }else{
            //TODO: Need for an action here
            Log.d(getClass().getName(), "Bundle is empty!");
        }
    }

    private void bindRecipeDetails(Recipe recipe){
    //TODO: bind the recyclerView here
    }

    private void setRecipe(Recipe recipe){
        this.recipe = recipe;
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
