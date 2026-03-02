package fes.carlos.menudominus.controllers;

import android.app.Activity;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import fes.carlos.menudominus.R;
import fes.carlos.menudominus.models.ImagenModel;
import fes.carlos.menudominus.services.DominosService;

public class PizzasController {
    private List<ImagenModel> imagenes = new ArrayList<>();
    private Activity activity;

    public PizzasController(Activity activity) {
        this.activity = activity;
        cargarImagenesDePizzas();
    }

    private void cargarImagenesDePizzas(){
        imagenes.add(new ImagenModel("022238e3-ac62-45af-b765-379a077a9b26.jpg", "imageButtonPizza1"));
        imagenes.add(new ImagenModel("00455854-40f9-45e2-a241-b03e70dfe6cc.jpg", "imageButtonPizza2"));
        imagenes.add(new ImagenModel("be242cad-08db-4b00-becd-be48a9fb86a4.jpg", "imageButtonPizza3"));
        imagenes.add(new ImagenModel("75b89c2b-2c59-4225-8478-4e9ab4432ec9.jpg", "imageButtonPizza4"));
        imagenes.add(new ImagenModel("7f339d0c-bf13-4839-a9dc-de6721210a5b.jpg", "imageButtonPizza5"));
        imagenes.add(new ImagenModel("67734d82-5c38-40f8-a311-255c0d157bd5.jpg", "imageButtonPizza6"));
        imagenes.add(new ImagenModel("0ad30a1e-d7ed-4384-9b1e-b8a5c9587174.jpg", "imageButtonPizza7"));
        imagenes.add(new ImagenModel("8be27c50-bb3c-4774-ac8f-a41f42029617.jpg", "imageButtonPizza8"));
    }

    public void inicializarPizzas() {

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


    private void pintarImageButton(String urlImagen, ImageButton imageButton){
        String urlCompleta = DominosService.BASE_URL +"/pizzas/"+ urlImagen;
        Glide.with(activity)
                .load(urlCompleta)
                .into(imageButton);
    }
}
