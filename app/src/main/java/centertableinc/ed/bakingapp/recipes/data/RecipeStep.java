package centertableinc.ed.bakingapp.recipes.data;

import android.os.Parcelable;

public interface RecipeStep extends Parcelable {
    String getStepId();
    String getStepShortDescription();
    String getStepDescription();
    String getStepVideoUrl();
    String getStepThumbnailUrl();
}
