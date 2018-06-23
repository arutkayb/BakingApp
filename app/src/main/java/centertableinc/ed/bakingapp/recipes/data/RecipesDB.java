package centertableinc.ed.bakingapp.recipes.data;

import centertableinc.ed.bakingapp.recipes.common.AsyncDataListener;

public interface RecipesDB {
    void requestRecipeList(AsyncDataListener listener);
}
