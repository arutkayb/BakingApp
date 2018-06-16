package centertableinc.ed.bakingapp.recipes.data;

interface RecipeStep {
    String getStepId();
    String getStepShortDescription();
    String getStepDescription();
    String getStepVideoUrl();
    String getStepThumbnailUrl();
}
