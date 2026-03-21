package fes.carlos.menudominus.services;

import androidx.annotation.NonNull;

import java.util.List;

import fes.carlos.menudominus.models.MenuItemModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MenuService {
    private ApiService apiService;

    public MenuService() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DominosService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public void obtenerMenu(ApiCallback<List<MenuItemModel>> callback) {
        Call<List<MenuItemModel>> call = apiService.obtenerMenu();
        call.enqueue(new Callback<List<MenuItemModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<MenuItemModel>> call, @NonNull Response<List<MenuItemModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error al obtener menú: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MenuItemModel>> call, @NonNull Throwable t) {
                callback.onError("Fallo en la red: " + t.getMessage());
            }
        });
    }

    private interface ApiService {
        @GET("api/Pizzas/Menus")
        Call<List<MenuItemModel>> obtenerMenu();
    }
}