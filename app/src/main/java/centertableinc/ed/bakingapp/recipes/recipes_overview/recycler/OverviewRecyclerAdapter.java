package centertableinc.ed.bakingapp.recipes.recipes_overview.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.Recipe;

public class OverviewRecyclerAdapter extends RecyclerView.Adapter<OverviewRecyclerAdapter.RecipesOverviewHolder>{
    private List<Recipe> recipeList;

    public OverviewRecyclerAdapter(List<Recipe> recipes){
        recipeList = recipes;
    }

    public void setRecipeList(List<Recipe> recipes){
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
        holder.bindHolder(recipeList.get(position));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


    public class RecipesOverviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        RecipesOverviewHolder(View view){
            super(view);
        }

        void bindHolder(Recipe recipe){

        }

        @Override
        public void onClick(View view) {

        }
    }
}
