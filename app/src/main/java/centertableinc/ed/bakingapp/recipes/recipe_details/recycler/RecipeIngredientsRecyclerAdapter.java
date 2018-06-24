package centertableinc.ed.bakingapp.recipes.recipe_details.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.RecipeIngredient;
import centertableinc.ed.bakingapp.recipes.data.udacity_data.Ingredient;
import centertableinc.ed.bakingapp.recipes.data.udacity_data.Ingredient;

public class RecipeIngredientsRecyclerAdapter extends RecyclerView.Adapter<RecipeIngredientsRecyclerAdapter.DetailsHolder>{
    private List<RecipeIngredient> ingredientList;
    Context context;

    public RecipeIngredientsRecyclerAdapter(Context context, List<RecipeIngredient> ingredients){
        this.context = context;
        ingredientList = ingredients;
    }

    public void setIngredientList(List<RecipeIngredient> ingredients){
        ingredientList = ingredients;
    }

    @NonNull
    @Override
    public DetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_ingredient_item, parent, false);

        return new DetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsHolder holder, int position) {
        holder.bindHolder(ingredientList.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    class DetailsHolder extends RecyclerView.ViewHolder{
        TextView ingredientName;
        TextView quantity;
        TextView measure;

        DetailsHolder(View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.ingredient_name);
            quantity = itemView.findViewById(R.id.quantity);
            measure = itemView.findViewById(R.id.measure);
        }

        void bindHolder(RecipeIngredient ingredient){
            String ingredientNameString = context.getString(R.string.ingredient_name)
                    + ": " + ingredient.getIngredientName();
            ingredientName.setText(ingredientNameString);

            String ingredientQuantityString = context.getString(R.string.ingredient_quantity)
                    + ": " + ingredient.getIngredientQuantity();
            quantity.setText(ingredientQuantityString);

            String ingredientMeasureString = context.getString(R.string.ingredient_measure)
                    + ": " + ingredient.getIngredientMeasure();
            measure.setText(ingredientMeasureString);
        }
    }
}
