package centertableinc.ed.bakingapp.recipes.common;

public interface AsyncDataListener<T> {
    void onDataLoad(T data);
}
