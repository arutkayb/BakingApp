package centertableinc.ed.bakingapp.recipes.recipe_step_details.recycler;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.common.RecyclerViewListener;
import centertableinc.ed.bakingapp.recipes.data.Recipe;
import centertableinc.ed.bakingapp.recipes.data.RecipeStep;
import centertableinc.ed.bakingapp.recipes.data.udacity_data.Ingredient;
import centertableinc.ed.bakingapp.util.networking.BakingGlideModule;

public class RecipeStepsRecyclerAdapter extends RecyclerView.Adapter<RecipeStepsRecyclerAdapter.RecipeStepsHolder>{
    private RecyclerViewListener listener;
    private List<RecipeStep> stepList;

    public RecipeStepsRecyclerAdapter(List<RecipeStep> steps){
        stepList = steps;
    }

    public void setIngredientList(List<RecipeStep> steps){
        stepList = steps;
    }

    @NonNull
    @Override
    public RecipeStepsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_step_item, parent, false);

        return new RecipeStepsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepsHolder holder, int position) {
        holder.bindHolder(stepList.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecipeStepsHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView description;
        FrameLayout video_container;

        public RecipeStepsHolder(View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            description = itemView.findViewById(R.id.description);
            video_container = itemView.findViewById(R.id.video_container);
        }

        void bindHolder(RecipeStep recipeStep){
            //TODO: fix the weirdo implementation here
            if(listener instanceof Context) {
                Uri thumbnail_uri = Uri.parse(recipeStep.getStepThumbnailUrl());
                BakingGlideModule.loadImageToImageView((Context) listener, thumbnail_uri, thumbnail);
            }

            description.setText(recipeStep.getStepDescription());

            // //TODO: use Exoplayer to bind video to video_container
        }
    }
}
