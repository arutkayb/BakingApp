package centertableinc.ed.bakingapp.recipes.recipes_overview.recycler;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        void bindHolder(Recipe recipe){
            Uri thumbnail_uri = Uri.parse(recipe.getRecipeImage());
            BakingGlideModule.loadImageToImageView(context, thumbnail_uri, recipe_image);

            recipe_name.setText(recipe.getRecipeName());
            servings.setText(String.valueOf(recipe.getRecipeServings()));
        }

        @Override
        public void onClick(View view) {
            listener.onItemSelectedEvent(getAdapterPosition());
        }
    }
}
