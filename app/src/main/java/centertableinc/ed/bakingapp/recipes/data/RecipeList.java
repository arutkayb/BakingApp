package centertableinc.ed.bakingapp.recipes.data;

import java.util.ArrayList;

public final class RecipeList {
    private ArrayList<Recipe> recipeList;

    public RecipeList(ArrayList list) throws InsufficientListTypeException {
        setRecipeList(list);
    }

    public void setRecipeList(ArrayList list) throws InsufficientListTypeException {
        if(list == null || list.isEmpty() || !(list.get(0) instanceof Recipe))
            throw new InsufficientListTypeException();

        recipeList = list;
    }

    public ArrayList<Recipe> getRecipeList(){
        return recipeList;
    }

    public final class InsufficientListTypeException extends Exception{
        public InsufficientListTypeException() {
            super();
        }

        @Override
        public String getMessage() {
            return "Unkown list items in list";
        }
    }
}
