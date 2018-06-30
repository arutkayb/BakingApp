package centertableinc.ed.bakingapp.recipes.recipes_overview;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.recipes_overview.recycler.OverviewRecyclerAdapter;
import centertableinc.ed.bakingapp.recipes.common.AsyncDataListener;
import centertableinc.ed.bakingapp.recipes.common.RecyclerViewListener;
import centertableinc.ed.bakingapp.recipes.data.Recipe;
import centertableinc.ed.bakingapp.recipes.data.RecipeList;
import centertableinc.ed.bakingapp.recipes.data.RecipesDB;
import centertableinc.ed.bakingapp.recipes.data.udacity_data.UdacityRecipesDB;
import centertableinc.ed.bakingapp.recipes.recipe_details.RecipeDetailsActivity;
import centertableinc.ed.bakingapp.recipes.recipe_details.RecipeDetailsFragment;
import centertableinc.ed.bakingapp.util.RecyclerViewUtil;
import centertableinc.ed.bakingapp.util.SharedPrefsUtil;
import centertableinc.ed.bakingapp.widget.BakingWidget;

public class RecipesMasterActivity extends AppCompatActivity
        implements AsyncDataListener<RecipeList> {

    RecipeList recipeList;
    FrameLayout recipe_details_fragment_container;
    RecyclerView overviewRecyclerView;

    private int persistedScrollPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_master);

        initialise();

        getIdlingResource();

        RecipesDB service = new UdacityRecipesDB();
        requestForRecipes(service);
    }

    private void initialise(){
        recipe_details_fragment_container = findViewById(R.id.recipe_details_fragment_container);

        overviewRecyclerView = findViewById(R.id.recycler_view_recipes_overview);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        overviewRecyclerView.setLayoutManager(layoutManager);
    }

    private boolean isTwoPane(){
        return recipe_details_fragment_container != null;
    }

    private void requestForRecipes(RecipesDB service){
        if(recipesIdlingResource != null)
            recipesIdlingResource.setIdleState(false);

        service.requestRecipeList(this);
    }

    @Override
    public void onDataLoad(RecipeList list){
        recipeList = list;
        bindRecipeList(list);

        if(recipesIdlingResource != null)
            recipesIdlingResource.setIdleState(true);
    }

    private void bindRecipeList(final RecipeList list){
        OverviewRecyclerAdapter overviewRecyclerAdapter = new OverviewRecyclerAdapter(getApplicationContext(),
                new RecyclerViewListener() {
                    @Override
                    public void onItemSelectedEvent(int itemNo) {
                        callRecipeDetails(list.getRecipeList().get(itemNo));

                        SharedPrefsUtil.saveLastSelectedRecipeIngredientNames(getApplicationContext(),
                                list.getRecipeList().get(itemNo));

                        Intent intent = new Intent(getApplicationContext(), BakingWidget.class);
                        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
                        int ids[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), BakingWidget.class));
                        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
                        sendBroadcast(intent);
                    }
                }, list);

        overviewRecyclerView.setAdapter(overviewRecyclerAdapter);
    }

    private void callRecipeDetails(Recipe recipe){
        if(isTwoPane()) {
            RecipeDetailsFragment recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipe);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_details_fragment_container, recipeDetailsFragment)
                    .commit();
        }else{
            Intent intent = new Intent(this, RecipeDetailsActivity.class);
            intent.putExtra("parcelable_recipe", recipe);
            startActivity(intent);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        RecyclerViewUtil.setScrollPosition(overviewRecyclerView, persistedScrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        persistedScrollPosition = getFirstVisibleItemOfRecyclerView();
    }

    private int getFirstVisibleItemOfRecyclerView(){
        return ((LinearLayoutManager)overviewRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
    }

    @Nullable
    private RecipesIdlingResource recipesIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link RecipesIdlingResource
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (recipesIdlingResource == null) {
            recipesIdlingResource = new RecipesIdlingResource();
        }
        return recipesIdlingResource;
    }
}
