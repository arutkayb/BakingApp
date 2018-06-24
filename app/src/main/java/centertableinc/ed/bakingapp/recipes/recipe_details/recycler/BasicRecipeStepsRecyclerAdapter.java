package centertableinc.ed.bakingapp.recipes.recipe_details.recycler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.common.RecyclerViewListener;
import centertableinc.ed.bakingapp.recipes.data.RecipeStep;
import centertableinc.ed.bakingapp.util.networking.BakingGlideModule;

public class BasicRecipeStepsRecyclerAdapter extends RecyclerView.Adapter<BasicRecipeStepsRecyclerAdapter.RecipeStepsHolder>{
    private RecyclerViewListener listener;
    private List<RecipeStep> stepList;
    private Context context;

    public BasicRecipeStepsRecyclerAdapter(Context context, RecyclerViewListener listener, List<RecipeStep> steps){
        this.listener = listener;
        this.context = context;
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
        return stepList.size();
    }

    class RecipeStepsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView thumbnail;
        TextView shortDescription;
        TextView stepNumber;

        RecipeStepsHolder(View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            shortDescription = itemView.findViewById(R.id.description);
            stepNumber = itemView.findViewById(R.id.step_number);

            itemView.setOnClickListener(this);
        }

        void bindHolder(RecipeStep recipeStep){
            Uri thumbnail_uri = Uri.parse(recipeStep.getStepThumbnailUrl());
            BakingGlideModule.loadImageToImageView(context,
                    new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            thumbnail.setBackgroundResource(R.drawable.recipe_step_icon);
                            Log.e(getClass().getName(), "Error loading image", e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    },
                    thumbnail_uri,
                    thumbnail);

            shortDescription.setText(recipeStep.getStepShortDescription());

            String stepNumberText = context.getString(R.string.step_number)
                    + ": " + String.valueOf(getAdapterPosition());
            stepNumber.setText(stepNumberText);
        }

        @Override
        public void onClick(View view) {
            listener.onItemSelectedEvent(getAdapterPosition());
        }

    }


}
