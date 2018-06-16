package centertableinc.ed.bakingapp.recipes.data.udacity_data;

import java.util.ArrayList;

import centertableinc.ed.bakingapp.recipes.data.RecipesDB;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/*
Source:
    https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json

Result structure:

    [
      {
        "id": 1,
        "name": "Nutella Pie",
        "ingredients": [
          {
            "quantity": 2,
            "measure": "CUP",
            "ingredient": "Graham Cracker crumbs"
          },
          ..
        ],
        "steps": [
          {
            "id": 0,
            "shortDescription": "UdacityRecipe Introduction",
            "description": "UdacityRecipe Introduction",
            "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4",
            "thumbnailURL": ""
          },
          ..
        ],
        "servings": 8,
        "image": ""
      },
      ..
    ]

 */
public class RetrofitInstance {
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
