package fhdw.hotel.Async;

public interface IAsyncTaskCompleteListener<T> {
    void onTaskComplete(T result);
}
