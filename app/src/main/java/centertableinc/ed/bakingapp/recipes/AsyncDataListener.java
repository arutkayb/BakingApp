package centertableinc.ed.bakingapp.recipes;

public interface AsyncDataListener<T> {
    void onDataLoad(T data);
}
