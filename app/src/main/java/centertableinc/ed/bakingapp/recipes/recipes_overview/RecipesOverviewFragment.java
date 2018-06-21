package centertableinc.ed.bakingapp.recipes.recipes_overview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.RecipeList;

public class RecipesOverviewFragment extends Fragment {
    private static final String RECIPE_LIST_PARCELABLE_KEY = "recipe_list";
    RecipeList recipeList;

    public RecipesOverviewFragment(){
    }

    public static RecipesOverviewFragment newInstance(RecipeList recipes){
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(RECIPE_LIST_PARCELABLE_KEY, recipes.getRecipeList());

        RecipesOverviewFragment fragment = new RecipesOverviewFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList recipes = null;

        Bundle bundle = getArguments();
        if(bundle != null) {
            recipes = bundle.getParcelableArrayList(RECIPE_LIST_PARCELABLE_KEY);

            if(recipes != null){
                setRecipeList(recipes);
            }else{
                //TODO: Need for an action here
                Log.d(getClass().getName(), "Recipes in bundle is empty!");
            }

        }else{
            //TODO: Need for an action here
            Log.d(getClass().getName(), "Bundle is empty!");
        }
    }

    private void setRecipeList(ArrayList recipes){
        try{
            recipeList.setRecipeList(recipes);
        }catch (RecipeList.InsufficientListTypeException ex) {
            //TODO: Need for an action here
            Log.e(getClass().getName(), "Bad recipe list, exception: " + ex.toString());
        }
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
