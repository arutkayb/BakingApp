package centertableinc.ed.bakingapp.recipes.data;

import centertableinc.ed.bakingapp.recipes.AsyncDataListener;

public interface RecipesDB {
    void requestRecipeList(AsyncDataListener listener);
}
