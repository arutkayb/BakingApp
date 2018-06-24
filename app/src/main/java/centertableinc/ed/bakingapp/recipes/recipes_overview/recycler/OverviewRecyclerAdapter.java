package centertableinc.ed.bakingapp.recipes.recipes_overview.recycler;

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

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.common.RecyclerViewListener;
import centertableinc.ed.bakingapp.recipes.data.Recipe;
import centertableinc.ed.bakingapp.recipes.data.RecipeList;
import centertableinc.ed.bakingapp.util.networking.BakingGlideModule;

public class OverviewRecyclerAdapter extends RecyclerView.Adapter<OverviewRecyclerAdapter.RecipesOverviewHolder>{
    private RecipeList recipeList;
    private RecyclerViewListener listener;
    Context context;

    public OverviewRecyclerAdapter(Context context, RecyclerViewListener listener, RecipeList recipes){
        recipeList = recipes;
        this.listener = listener;
        this.context = context;
    }

    public void setRecipeList(RecipeList recipes){
        recipeList = recipes;
    }

    @Override
    @NonNull
    public RecipesOverviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);

        return new RecipesOverviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesOverviewHolder holder, final int position) {
        holder.bindHolder(recipeList.getRecipeList().get(position));
    }

    @Override
    public int getItemCount() {
        return recipeList.getRecipeList().size();
    }


    public class RecipesOverviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView recipe_image;
        TextView recipe_name;
        TextView servings;

        RecipesOverviewHolder(View view){
            super(view);

            recipe_image = view.findViewById(R.id.recipe_image);
            recipe_name = view.findViewById(R.id.recipe_name);
            servings = view.findViewById(R.id.servings);

            view.setOnClickListener(this);
        }

        void bindHolder(final Recipe recipe){
            Uri thumbnail_uri = Uri.parse(recipe.getRecipeImage());
            BakingGlideModule.loadImageToImageView(context,
                    new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            recipe_image.setBackgroundResource(R.drawable.recipe_overview_icon);
                            Log.e(getClass().getName(), "Error loading image", e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    },
                    thumbnail_uri,
                    recipe_image);

            recipe_name.setText(recipe.getRecipeName());

            String servingsString = context.getString(R.string.servings)
                    + ": " + String.valueOf(recipe.getRecipeServings());
            servings.setText(servingsString);
        }

        @Override
        public void onClick(View view) {
            listener.onItemSelectedEvent(getAdapterPosition());
        }
    }
}
