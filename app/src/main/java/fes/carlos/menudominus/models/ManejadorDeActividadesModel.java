package fes.carlos.menudominus.models;

import android.app.Activity;
import android.content.Intent;

public class ManejadorDeActividadesModel {
    private static ManejadorDeActividadesModel instancia;
    private Activity activity;

    private ManejadorDeActividadesModel() {
    }

    public static ManejadorDeActividadesModel getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorDeActividadesModel();
        }
        return instancia;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void cambiarDeActividad(Class<?> claseDestino) {
        Intent intent = new Intent(activity, claseDestino);
        activity.startActivity(intent);
    }

    public Activity getActivity() {
        return activity;
    }
}
