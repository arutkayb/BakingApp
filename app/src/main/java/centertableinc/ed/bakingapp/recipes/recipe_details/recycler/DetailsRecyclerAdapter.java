package centertableinc.ed.bakingapp.recipes.recipe_details.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import centertableinc.ed.bakingapp.R;
import centertableinc.ed.bakingapp.recipes.data.udacity_data.Ingredient;
import centertableinc.ed.bakingapp.recipes.data.udacity_data.Step;

public class DetailsRecyclerAdapter extends RecyclerView.Adapter<DetailsRecyclerAdapter.DetailsHolder>{
    private List<Step> stepList;

    public DetailsRecyclerAdapter(List<Step> steps){
        stepList = steps;
    }

    public void setStepList(List<Step> steps){
        stepList = steps;
    }
    
    @NonNull
    @Override
    public DetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_step_item, parent, false);

        return new DetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DetailsHolder extends RecyclerView.ViewHolder{
        public DetailsHolder(View itemView) {
            super(itemView);
        }
    }
}
