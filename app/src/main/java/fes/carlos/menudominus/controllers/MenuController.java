package fes.carlos.menudominus.controllers;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageButton;

import androidx.annotation.IdRes;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import fes.carlos.menudominus.models.ImagenModel;
import fes.carlos.menudominus.models.ManejadorDeActividadesModel;
import fes.carlos.menudominus.services.DominosService;

public class MenuController {
    private Activity activity;
    private ManejadorDeActividadesModel manejadorDeActividadesMenu;
    private List<ImagenModel> imagenes = new ArrayList<>();

    public MenuController(Activity activity) {
        this.activity = activity;
        this.manejadorDeActividadesMenu = new ManejadorDeActividadesModel(activity);
        cargarImagenesDelMenu();
    }

    private void cargarImagenesDelMenu(){
        imagenes.add(new ImagenModel("buildYourOwnPizza.jpg", "imageButtonBuildYourOwnPizza"));
        imagenes.add(new ImagenModel("pizza.png", "imageButtonPizzas"));
        imagenes.add(new ImagenModel("breads.png", "imageButtonEntradas"));
        imagenes.add(new ImagenModel("chicken.png", "imageButtonPollo"));
        imagenes.add(new ImagenModel("dessert.png", "imageButtonPostres"));
        imagenes.add(new ImagenModel("drinks.png", "imageButtonBebidas"));
        imagenes.add(new ImagenModel("salsas.jpg", "imageButtonSalsas"));
    }



    public void inicializarMenu() {

        for (ImagenModel imagenModel:
             imagenes) {
            int resId = activity.getResources().getIdentifier(
                    imagenModel.getIdDeLaImagen(),
                    "id",
                    activity.getPackageName());
            ImageButton imagenButton = activity.findViewById(resId);
            if(imagenButton != null){
                pintarImageButton(imagenModel.getNombreImagen(), imagenButton);
            }

        }
    }

    public void cambiarDeActividad(Class<?> claseDeActividadACambiar){
        manejadorDeActividadesMenu.cambiarDeActividad(claseDeActividadACambiar);
    }

    private void pintarImageButton(String urlImagen, ImageButton imageButton){
        String urlCompleta = DominosService.BASE_URL + "/menus/"+urlImagen;
        Glide.with(activity)
                .load(urlCompleta)
                .into(imageButton);
    }

}
