package fes.carlos.menudominus.services;

public interface ApiCallback<T> {
    void onSuccess(T data);
    void onError(String error);
}