package centertableinc.ed.bakingapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import centertableinc.ed.bakingapp.recipes.recipes_overview.RecipesMasterActivity;

import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class RecipesMasterActivityEspressoTest {
    @Rule
    public ActivityTestRule<RecipesMasterActivity> activityTestRule =
            new ActivityTestRule<>(RecipesMasterActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = activityTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

    @Test
    public void selectRecipe() {
        onView(ViewMatchers.withId(R.id.recycler_view_recipes_overview)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.basic_recipe_steps_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void selectRecipeStepAndCheckPlayerViewDisplayed() {
        onView(ViewMatchers.withId(R.id.recycler_view_recipes_overview)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(ViewMatchers.withId(R.id.basic_recipe_steps_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.recipe_steps_player_view)).check(matches(isDisplayed()));
    }
}
