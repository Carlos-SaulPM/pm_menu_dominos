package fes.carlos.menudominus;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageView[] imagenesView = new ImageView[7];
    String baseUrl = "https://595z7z9r-3000.usw3.devtunnels.ms/images/menus/";
    String urlsImagenes[] = {
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
        imagenesView[0] = findViewById(R.id.imageViewBuildYourOwnPizza);
        imagenesView[1] = findViewById(R.id.imageViewPizzas);
        imagenesView[2] = findViewById(R.id.imageViewEntradas);
        imagenesView[3] = findViewById(R.id.imageViewPollo);
        imagenesView[4] = findViewById(R.id.imageViewPostres);
        imagenesView[5] = findViewById(R.id.imageViewBebidas);
        imagenesView[6] = findViewById(R.id.imageViewSalsas);
        for (int i = 0; i<=6; i++) {
            Glide.with(this).load(baseUrl+urlsImagenes[i]).into(imagenesView[i]);
        }
    }



}