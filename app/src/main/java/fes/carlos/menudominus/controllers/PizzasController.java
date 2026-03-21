package fes.carlos.menudominus.controllers;

import android.app.Activity;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fes.carlos.menudominus.R;
import fes.carlos.menudominus.models.PizzaModel;
import fes.carlos.menudominus.services.ApiCallback;
import fes.carlos.menudominus.services.PizzasService;

public class PizzasController {
    private Activity activity;
    private PizzasService pizzasService;
    private int[] idsImageButtons;
    private int[] idsTextViews;

    public PizzasController(Activity activity) {
        this.activity = activity;
        this.pizzasService = new PizzasService();
        inicializarIds();
    }

    private void inicializarIds() {
        idsImageButtons = new int[]{
                R.id.imageButtonPizza1,
                R.id.imageButtonPizza2,
                R.id.imageButtonPizza3,
                R.id.imageButtonPizza4,
                R.id.imageButtonPizza5,
                R.id.imageButtonPizza6,
                R.id.imageButtonPizza7,
                R.id.imageButtonPizza8
        };
        idsTextViews = new int[]{
                R.id.textView8,
                R.id.textViewPizzas,
                R.id.textViewEntradas,
                R.id.textViewPollo,
                R.id.textViewPostres,
                R.id.textViewBebidas,
                R.id.textViewSalsas,
                R.id.textViewSalsas4
        };
    }

    public void inicializarPizzas() {
        pizzasService.obtenerPizzas(new ApiCallback<List<PizzaModel>>() {
            @Override
            public void onSuccess(List<PizzaModel> data) {
                int limite = Math.min(data.size(), 8);
                for (int i = 0; i < limite; i++) {
                    PizzaModel pizza = data.get(i);
                    ImageButton imageButton = activity.findViewById(idsImageButtons[i]);
                    if (imageButton != null) {
                        String urlCompleta = "https://utilidades.vmartinez84.xyz" + pizza.getRuta();
                        if (!pizza.getRuta().startsWith("/")) {
                            urlCompleta = "https://utilidades.vmartinez84.xyz/" + pizza.getRuta();
                        }
                        Glide.with(activity)
                                .load(urlCompleta)
                                .into(imageButton);
                    }
                    android.widget.TextView textView = activity.findViewById(idsTextViews[i]);
                    if (textView != null) {
                        textView.setText(pizza.getNombre());
                    }
                }
            }

            @Override
            public void onError(String error) {
                android.util.Log.e("PizzasController", error);
            }
        });
    }
}