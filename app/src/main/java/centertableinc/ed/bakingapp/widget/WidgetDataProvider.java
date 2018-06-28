package centertableinc.ed.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import centertableinc.ed.bakingapp.util.SharedPrefsUtil;

/**
 * WidgetDataProvider acts as the adapter for the collection view widget,
 * providing RemoteViews to the widget in the getViewAt method.
 */
public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "WidgetDataProvider";

    List<String> ingredients;
    Context context = null;

    public WidgetDataProvider(Context context, Intent intent) {
        ingredients = new ArrayList<>();
        this.context = context;
    }

    @Override
    public void onCreate() {
        try{
            ingredients = SharedPrefsUtil.retrieveLastSelectedRecipeIngredientNames(context);
        }catch (Exception ex){
            Log.e(getClass().getName(), "exception on retrieve ingredient list, ex: " + ex.toString());
        }
    }

    @Override
    public void onDataSetChanged() {
        try{
            ingredients = SharedPrefsUtil.retrieveLastSelectedRecipeIngredientNames(context);
        }catch (Exception ex){
            Log.e(getClass().getName(), "exception on retrieve ingredient list, ex: " + ex.toString());
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews view = new RemoteViews(context.getPackageName(),
                android.R.layout.simple_list_item_1);
        view.setTextViewText(android.R.id.text1, ingredients.get(position));
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


}
