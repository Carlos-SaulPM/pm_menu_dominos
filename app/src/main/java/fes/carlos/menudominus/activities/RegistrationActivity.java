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

import fes.carlos.menudominus.R;
import fes.carlos.menudominus.controllers.RegisterController;

public class RegistrationActivity extends AppCompatActivity {
    private TextInputEditText editTextNombre;
    private TextInputEditText editTextApellidos;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextContraseña;
    private MaterialButton buttonRegistrarse;
    private TextView textViewIniciarSesion;
    private TextView textViewErrorNombre;
    private TextView textViewErrorApellidos;
    private TextView textViewErrorEmail;
    private TextView textViewErrorContraseña;
    private TextView textViewErrorGeneral;
    private ProgressBar progressBar;
    private RegisterController registerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inicializarVistas();
        registerController = new RegisterController(this);
        registerController.inicializarRegistro();
    }

    private void inicializarVistas() {
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellidos = findViewById(R.id.editTextApellidos);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextContraseña = findViewById(R.id.editTextContraseña);
        buttonRegistrarse = findViewById(R.id.buttonRegistrarse);
        textViewIniciarSesion = findViewById(R.id.textViewIniciarSesion);
        textViewErrorNombre = findViewById(R.id.textViewErrorNombre);
        textViewErrorApellidos = findViewById(R.id.textViewErrorApellidos);
        textViewErrorEmail = findViewById(R.id.textViewErrorEmail);
        textViewErrorContraseña = findViewById(R.id.textViewErrorContraseña);
        textViewErrorGeneral = findViewById(R.id.textViewErrorGeneral);
        progressBar = findViewById(R.id.progressBar);
    }

    public TextInputEditText getEditTextNombre() {
        return editTextNombre;
    }

    public TextInputEditText getEditTextApellidos() {
        return editTextApellidos;
    }

    public TextInputEditText getEditTextEmail() {
        return editTextEmail;
    }

    public TextInputEditText getEditTextContraseña() {
        return editTextContraseña;
    }

    public MaterialButton getButtonRegistrarse() {
        return buttonRegistrarse;
    }

    public TextView getTextViewIniciarSesion() {
        return textViewIniciarSesion;
    }

    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void mostrarCarga() {
        progressBar.setVisibility(View.VISIBLE);
        buttonRegistrarse.setEnabled(false);
        textViewIniciarSesion.setEnabled(false);
        editTextNombre.setEnabled(false);
        editTextApellidos.setEnabled(false);
        editTextEmail.setEnabled(false);
        editTextContraseña.setEnabled(false);
    }

    public void ocultarCarga() {
        progressBar.setVisibility(View.GONE);
        buttonRegistrarse.setEnabled(true);
        textViewIniciarSesion.setEnabled(true);
        editTextNombre.setEnabled(true);
        editTextApellidos.setEnabled(true);
        editTextEmail.setEnabled(true);
        editTextContraseña.setEnabled(true);
    }

    public void mostrarErrorNombre(String mensaje) {
        textViewErrorNombre.setText(mensaje);
        textViewErrorNombre.setVisibility(View.VISIBLE);
    }

    public void mostrarErrorApellidos(String mensaje) {
        textViewErrorApellidos.setText(mensaje);
        textViewErrorApellidos.setVisibility(View.VISIBLE);
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
        textViewErrorNombre.setVisibility(View.GONE);
        textViewErrorApellidos.setVisibility(View.GONE);
        textViewErrorEmail.setVisibility(View.GONE);
        textViewErrorContraseña.setVisibility(View.GONE);
        textViewErrorGeneral.setVisibility(View.GONE);
    }
}
