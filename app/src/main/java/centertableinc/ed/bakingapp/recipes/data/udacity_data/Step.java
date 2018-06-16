package centertableinc.ed.bakingapp.recipes.data.udacity_data;

import com.google.gson.annotations.SerializedName;

import centertableinc.ed.bakingapp.recipes.data.RecipeStep;

/*
    "steps": [
      {
        "id": 0,
        "shortDescription": "UdacityRecipe Introduction",
        "description": "UdacityRecipe Introduction",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4",
        "thumbnailURL": ""
      }
 */

public class Step implements RecipeStep{
    private static final String ID = "id";
    private static final String SHORT_DESCRIPTION = "shortDescription";
    private static final String DESCRIPTION = "description";
    private static final String VIDEO_URL = "videoURL";
    private static final String THUMBNAIL_URL = "thumbnailURL";

    @SerializedName(ID)
    private String stepId;

    @SerializedName(SHORT_DESCRIPTION)
    private String stepShortDescription;

    @SerializedName(DESCRIPTION)
    private String stepDescription;

    @SerializedName(VIDEO_URL)
    private String stepVideoUrl;

    @SerializedName(THUMBNAIL_URL)
    private String stepThumbnailUrl;

    @Override
    public String getStepId() {
        return stepId;
    }

    @Override
    public String getStepShortDescription() {
        return stepShortDescription;
    }

    @Override
    public String getStepDescription() {
        return stepDescription;
    }

    @Override
    public String getStepVideoUrl() {
        return stepVideoUrl;
    }

    @Override
    public String getStepThumbnailUrl() {
        return stepThumbnailUrl;
    }
}
