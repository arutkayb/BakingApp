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

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.RecipeStep;
import centertableinc.ed.bakingapp.recipes.recipe_details.RecipeDetailsFragment;
import centertableinc.ed.bakingapp.recipes.recipe_step_details.recycler.DetailedRecipeStepsRecyclerAdapter;
import centertableinc.ed.bakingapp.util.RecyclerViewUtil;

import static centertableinc.ed.bakingapp.recipes.recipe_step_details.DetailedRecipeStepsActivity.RECIPE_SELECTED_STEP;
import static centertableinc.ed.bakingapp.recipes.recipe_step_details.DetailedRecipeStepsActivity.RECIPE_SELECTED_STEP_NO;

public class RecipeStepDetailsFragment extends Fragment implements Player.EventListener{
    private static final String KEY_START_POS = "start_position";
    private static final String KEY_START_AUTO_PLAY = "start_auto_play";
    long startPosition;
    boolean startAutoPlay;

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

            if(savedInstanceState != null) {
                startPosition = savedInstanceState.getLong(KEY_START_POS);
                startAutoPlay = savedInstanceState.getBoolean(KEY_START_AUTO_PLAY);
            }
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

        DetailedRecipeStepsRecyclerAdapter detailedRecipeStepsRecyclerAdapter = new DetailedRecipeStepsRecyclerAdapter(getContext(), steps, stepNo);

        detailedRecipeStepsRecyclerView.setAdapter(detailedRecipeStepsRecyclerAdapter);
    }

    private void initialise(){
        detailedRecipeStepsRecyclerView = view.findViewById(R.id.detailed_recipe_steps_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        detailedRecipeStepsRecyclerView.setLayoutManager(layoutManager);

        if(!step.getStepVideoUrl().isEmpty())
            initialiseMedia();
    }

    private void initialiseMedia(){
        /*
        1- Add ExoPlayer as a dependency to your project.
        2- Create a SimpleExoPlayer instance.
        3- Attach the player to a view (for video output and user input).
        4- Prepare the player with a MediaSource to play.
        5- Release the player when done.
         */

        playerView = view.findViewById(R.id.recipe_steps_player_view);
        playerView.setVisibility(View.VISIBLE);
        createPlayer();
        preparePlayer();
    }

    private void createPlayer(){
        // 1. Create a default TrackSelector
        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create the player
        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

        player.setPlayWhenReady(startAutoPlay);
        // Bind the player to the view.
        playerView.setPlayer(player);
    }

    private void preparePlayer(){
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), getString(R.string.app_name)), bandwidthMeter);
        // This is the MediaSource representing the media to be played.
        Uri videoUri = Uri.parse(step.getStepVideoUrl());
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(videoUri);
        // Prepare the player with the source.
        player.prepare(videoSource);

        player.seekTo(startPosition);

        player.addListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        //TODO: avoid memory leaks
        if(player != null)
            player.release();
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

        if(player != null) {
            long startPos = Math.max(0, player.getContentPosition());
            outState.putLong(KEY_START_POS, startPos);

            boolean autoPlay = player.getPlayWhenReady();
            outState.putBoolean(KEY_START_AUTO_PLAY, autoPlay);
        }
    }

    private int getFirstVisibleItemOfRecyclerView(){
        return ((LinearLayoutManager)detailedRecipeStepsRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }
}
