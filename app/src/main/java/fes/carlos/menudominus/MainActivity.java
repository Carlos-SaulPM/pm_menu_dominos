package fes.carlos.menudominus;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageButton[] imagenesBotones = new ImageButton[7];
    String baseUrl = "https://595z7z9r-3000.usw3.devtunnels.ms/images/menus/";
    String[] urlsImagenes = {
            "buildYourOwnPizza.jpg",
            "pizza.png",
            "breads.png",
            "chicken.png",
            "dessert.png",
            "drinks.png",
            "salsas.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imagenesBotones[0] = findViewById(R.id.imageButtonBuildYourOwnPizza);
        imagenesBotones[1] = findViewById(R.id.imageButtonPizzas);
        imagenesBotones[2] = findViewById(R.id.imageButtonEntradas);
        imagenesBotones[3] = findViewById(R.id.imageButtonPollo);
        imagenesBotones[4] = findViewById(R.id.imageButtonPostres);
        imagenesBotones[5] = findViewById(R.id.imageButtonBebidas);
        imagenesBotones[6] = findViewById(R.id.imageButtonSalsas);
        for (int i = 0; i<=6; i++) {
            Glide.with(this).load(baseUrl+urlsImagenes[i]).into(imagenesBotones[i]);
        }
    }



}