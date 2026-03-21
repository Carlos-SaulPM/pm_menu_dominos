package fes.carlos.menudominus.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import fes.carlos.menudominus.R;
import fes.carlos.menudominus.controllers.LoginController;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextContraseña;
    private MaterialButton buttonEntrar;
    private MaterialTextView textViewRegistrarse;
    private TextView textViewErrorEmail;
    private TextView textViewErrorContraseña;
    private TextView textViewErrorGeneral;
    private ProgressBar progressBar;
    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inicializarVistas();
        loginController = new LoginController(this);
        loginController.inicializarLogin();
    }

    private void inicializarVistas() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextContraseña = findViewById(R.id.editTextContraseña);
        buttonEntrar = findViewById(R.id.buttonEntrar);
        textViewRegistrarse = findViewById(R.id.textViewRegistrarse);
        textViewErrorEmail = findViewById(R.id.textViewErrorEmail);
        textViewErrorContraseña = findViewById(R.id.textViewErrorContraseña);
        textViewErrorGeneral = findViewById(R.id.textViewErrorGeneral);
        progressBar = findViewById(R.id.progressBar);
    }

    public TextInputEditText getEditTextEmail() {
        return editTextEmail;
    }

    public TextInputEditText getEditTextContraseña() {
        return editTextContraseña;
    }

    public MaterialButton getButtonEntrar() {
        return buttonEntrar;
    }

    public MaterialTextView getTextViewRegistrarse() {
        return textViewRegistrarse;
    }

    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void mostrarCarga() {
        progressBar.setVisibility(View.VISIBLE);
        buttonEntrar.setEnabled(false);
        textViewRegistrarse.setEnabled(false);
        editTextEmail.setEnabled(false);
        editTextContraseña.setEnabled(false);
    }

    public void ocultarCarga() {
        progressBar.setVisibility(View.GONE);
        buttonEntrar.setEnabled(true);
        textViewRegistrarse.setEnabled(true);
        editTextEmail.setEnabled(true);
        editTextContraseña.setEnabled(true);
    }

    public void mostrarErrorEmail(String mensaje) {
        textViewErrorEmail.setText(mensaje);
        textViewErrorEmail.setVisibility(View.VISIBLE);
    }

    public void mostrarErrorContraseña(String mensaje) {
        textViewErrorContraseña.setText(mensaje);
        textViewErrorContraseña.setVisibility(View.VISIBLE);
    }

    public void mostrarErrorGeneral(String mensaje) {
        textViewErrorGeneral.setText(mensaje);
        textViewErrorGeneral.setVisibility(View.VISIBLE);
    }

    public void limpiarErrores() {
        textViewErrorEmail.setVisibility(View.GONE);
        textViewErrorContraseña.setVisibility(View.GONE);
        textViewErrorGeneral.setVisibility(View.GONE);
    }
}
