package centertableinc.ed.bakingapp.recipes.recipe_details;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recipe = getIntent().getParcelableExtra("parcelable_recipe");

        if(recipe == null){
            Log.e(getClass().getName(), "Passed recipe is null, terminating the activity");
            finish();
        }

        RecipeDetailsFragment recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipe);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.recipe_details_fragment_container, recipeDetailsFragment)
                .commit();
    }
}
