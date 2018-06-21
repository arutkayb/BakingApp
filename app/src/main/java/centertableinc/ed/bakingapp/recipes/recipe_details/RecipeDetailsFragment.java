package centertableinc.ed.bakingapp.recipes.recipe_details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.RecipeList;
import centertableinc.ed.bakingapp.recipes.recipe_step_details.RecipeStepDetailsFragment;

public class RecipeDetailsFragment extends Fragment {
    public RecipeDetailsFragment() {
    }

    public static RecipeDetailsFragment newInstance(){
        //TODO: implement newInstance
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
