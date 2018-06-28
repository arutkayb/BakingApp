package centertableinc.ed.bakingapp.recipes.recipe_step_details;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

import java.util.ArrayList;
import java.util.List;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.RecipeStep;
import centertableinc.ed.bakingapp.recipes.recipe_details.RecipeDetailsFragment;
import centertableinc.ed.bakingapp.recipes.recipe_step_details.recycler.DetailedRecipeStepsRecyclerAdapter;
import centertableinc.ed.bakingapp.util.RecyclerViewUtil;

import static centertableinc.ed.bakingapp.recipes.recipe_step_details.DetailedRecipeStepsActivity.RECIPE_SELECTED_STEP;
import static centertableinc.ed.bakingapp.recipes.recipe_step_details.DetailedRecipeStepsActivity.RECIPE_SELECTED_STEP_NO;

public class RecipeStepDetailsFragment extends Fragment {
    int stepNo;
    RecipeStep step;
    View view;
    RecyclerView detailedRecipeStepsRecyclerView;
    ImageView recipeStepsMediaContainer;
    SimpleExoPlayer player;
    PlayerView playerView;

    int persistedScrollPosition = 0;


    public RecipeStepDetailsFragment(){
    }

    public static RecipeStepDetailsFragment newInstance(RecipeStep step, int stepNo){
        Bundle bundle = new Bundle();
        bundle.putInt(RECIPE_SELECTED_STEP_NO, stepNo);
        bundle.putParcelable(RECIPE_SELECTED_STEP, step);

        RecipeStepDetailsFragment recipeStepDetailsFragment = new RecipeStepDetailsFragment();
        recipeStepDetailsFragment.setArguments(bundle);

        return recipeStepDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            int stepNo = bundle.getInt(RECIPE_SELECTED_STEP_NO, 0);
            RecipeStep step = bundle.getParcelable(RECIPE_SELECTED_STEP);

            setRecipeStep(step, stepNo);
        }else{
            Log.e(getClass().getName(), "Bundle is null! Cannot set recipe step");
        }
    }

    public void setRecipeStep(RecipeStep recipeStep, int stepNo){
        this.step = recipeStep;
        this.stepNo = stepNo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recipe_step_details, container, false);

        initialise();

        bindDetailedStepsRecyclerView();

        return view;
    }

    private void bindDetailedStepsRecyclerView(){
        List<RecipeStep> steps = new ArrayList<RecipeStep>();
        steps.add(step);

        DetailedRecipeStepsRecyclerAdapter detailedRecipeStepsRecyclerAdapter = new DetailedRecipeStepsRecyclerAdapter(getContext(), steps);

        detailedRecipeStepsRecyclerView.setAdapter(detailedRecipeStepsRecyclerAdapter);
    }

    private void initialise(){
        detailedRecipeStepsRecyclerView = view.findViewById(R.id.detailed_recipe_steps_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        detailedRecipeStepsRecyclerView.setLayoutManager(layoutManager);

        //TODO: init the view and fill the necessary fields
        initialiseMedia();
    }

    private void initialiseMedia(){
        //TODO: media goes here
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        RecyclerViewUtil.setScrollPosition(detailedRecipeStepsRecyclerView, persistedScrollPosition);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        persistedScrollPosition = getFirstVisibleItemOfRecyclerView();
    }

    private int getFirstVisibleItemOfRecyclerView(){
        return ((LinearLayoutManager)detailedRecipeStepsRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
    }
}
