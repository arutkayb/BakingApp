package centertableinc.ed.bakingapp.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewUtil {
    public static void setScrollPosition(RecyclerView view, int scrollPosition){
        if(scrollPosition < 0)
            scrollPosition = 0;

        view.scrollToPosition(scrollPosition);
    }
}
