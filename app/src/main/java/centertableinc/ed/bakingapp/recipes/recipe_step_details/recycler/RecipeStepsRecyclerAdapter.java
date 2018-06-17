package centertableinc.ed.bakingapp.recipes.recipe_step_details.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.Recipe;
import centertableinc.ed.bakingapp.recipes.data.udacity_data.Ingredient;

public class RecipeStepsRecyclerAdapter extends RecyclerView.Adapter<RecipeStepsRecyclerAdapter.RecipeStepsHolder>{
    private List<Ingredient> ingredientList;

    public RecipeStepsRecyclerAdapter(List<Ingredient> ingredients){
        ingredientList = ingredients;
    }

    public void setIngredientList(List<Ingredient> ingredients){
        ingredientList = ingredients;
    }

    @NonNull
    @Override
    public RecipeStepsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_ingredient_item, parent, false);

        return new RecipeStepsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecipeStepsHolder extends RecyclerView.ViewHolder{
        public RecipeStepsHolder(View itemView) {
            super(itemView);
        }
    }
}
