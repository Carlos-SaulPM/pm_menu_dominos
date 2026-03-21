package fes.carlos.menudominus.services;

import androidx.annotation.NonNull;

import java.util.Base64;

import fes.carlos.menudominus.models.AuthResponse;
import fes.carlos.menudominus.models.ClienteModel;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class AuthService {
    private ApiService apiService;

    public AuthService() {
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

    public void registrarCliente(ClienteModel cliente, ApiCallback<AuthResponse> callback) {
        Call<AuthResponse> call = apiService.registrarCliente(cliente);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error al registrar: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                callback.onError("Fallo en la red: " + t.getMessage());
            }
        });
    }

    public void iniciarSesion(String email, String password, ApiCallback<AuthResponse> callback) {
        String credentials = Credentials.basic(email, password);
        Call<AuthResponse> call = apiService.iniciarSesion(credentials);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error al iniciar sesión: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                callback.onError("Fallo en la red: " + t.getMessage());
            }
        });
    }

    private interface ApiService {
        @POST("api/Pizzas/Clientes")
        Call<AuthResponse> registrarCliente(@Body ClienteModel cliente);

        @POST("api/Pizzas/Clientes/InicioDeSesiones")
        Call<AuthResponse> iniciarSesion(@Header("Authorization") String credentials);
    }
}