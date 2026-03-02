package fes.carlos.menudominus.models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ManejadorDeActividadesModel {
    private Activity activity;

    public ManejadorDeActividadesModel(Activity activity) {
        this.activity = activity;
    }

    public void cambiarDeActividad(Class<?> claseDestino) {
        Intent intent = new Intent(this.activity, claseDestino);
        this.activity.startActivity(intent);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
