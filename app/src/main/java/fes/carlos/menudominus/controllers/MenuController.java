package fes.carlos.menudominus.controllers;

import android.app.Activity;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fes.carlos.menudominus.R;
import fes.carlos.menudominus.models.ManejadorDeActividadesModel;
import fes.carlos.menudominus.models.MenuItemModel;
import fes.carlos.menudominus.services.ApiCallback;
import fes.carlos.menudominus.services.MenuService;

public class MenuController {
    private Activity activity;
    private ManejadorDeActividadesModel manejadorDeActividades;
    private MenuService menuService;
    private Map<String, Integer> mapeoCategorias;

    public MenuController(Activity activity) {
        this.activity = activity;
        this.manejadorDeActividades = ManejadorDeActividadesModel.getInstancia();
        this.manejadorDeActividades.setActivity(activity);
        this.menuService = new MenuService();
        inicializarMapeo();
    }

    private void inicializarMapeo() {
        mapeoCategorias = new HashMap<>();
        mapeoCategorias.put("pizzas", R.id.imageButtonPizzas);
        mapeoCategorias.put("pollo", R.id.imageButtonPollo);
        mapeoCategorias.put("adicionales", R.id.imageButtonEntradas);
        mapeoCategorias.put("adicionesles", R.id.imageButtonEntradas);
        mapeoCategorias.put("bebidas", R.id.imageButtonBebidas);
        mapeoCategorias.put("postres", R.id.imageButtonPostres);
    }

    public void inicializarMenu() {
        menuService.obtenerMenu(new ApiCallback<List<MenuItemModel>>() {
            @Override
            public void onSuccess(List<MenuItemModel> data) {
                for (MenuItemModel item : data) {
                    Integer resId = mapeoCategorias.get(item.getTitulo().toLowerCase());
                    if (resId != null) {
                        ImageButton imageButton = activity.findViewById(resId);
                        if (imageButton != null) {
                            String urlCompleta = "https://utilidades.vmartinez84.xyz" + item.getRuta();
                            Glide.with(activity)
                                    .load(urlCompleta)
                                    .into(imageButton);
                        }
                    }
                }
            }

            @Override
            public void onError(String error) {
                android.util.Log.e("MenuController", error);
            }
        });
    }

    public void cambiarDeActividad(Class<?> claseDeActividadACambiar) {
        manejadorDeActividades.cambiarDeActividad(claseDeActividadACambiar);
    }
}