package fes.carlos.menudominus.controllers;

import android.app.Activity;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import fes.carlos.menudominus.MainActivity;
import fes.carlos.menudominus.R;
import fes.carlos.menudominus.activities.LoginActivity;
import fes.carlos.menudominus.activities.RegistrationActivity;
import fes.carlos.menudominus.models.AuthResponse;
import fes.carlos.menudominus.models.ManejadorDeActividadesModel;
import fes.carlos.menudominus.services.ApiCallback;
import fes.carlos.menudominus.services.AuthService;
import fes.carlos.menudominus.utils.ValidationUtils;

public class LoginController {
    private Activity activity;
    private LoginActivity loginActivity;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextContraseña;
    private MaterialButton buttonEntrar;
    private MaterialTextView textViewRegistrarse;
    private ManejadorDeActividadesModel manejadorDeActividades;
    private AuthService authService;

    public LoginController(Activity activity) {
        this.activity = activity;
        this.loginActivity = (LoginActivity) activity;
        this.manejadorDeActividades = ManejadorDeActividadesModel.getInstancia();
        this.manejadorDeActividades.setActivity(activity);
        this.authService = new AuthService();
    }

    public void inicializarLogin() {
        editTextEmail = loginActivity.getEditTextEmail();
        editTextContraseña = loginActivity.getEditTextContraseña();
        buttonEntrar = loginActivity.getButtonEntrar();
        textViewRegistrarse = loginActivity.getTextViewRegistrarse();

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCredenciales();
            }
        });

        textViewRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonEntrar.isEnabled()) {
                    manejadorDeActividades.cambiarDeActividad(RegistrationActivity.class);
                }
            }
        });
    }

    private void validarCredenciales() {
        loginActivity.mostrarCarga();
        loginActivity.limpiarErrores();

        String email = obtenerTexto(editTextEmail);
        String contraseña = obtenerTexto(editTextContraseña);

        if (!validarEmail(email)) {
            loginActivity.ocultarCarga();
            return;
        }

        if (!validarContraseña(contraseña)) {
            loginActivity.ocultarCarga();
            return;
        }

        authService.iniciarSesion(email, contraseña, new ApiCallback<AuthResponse>() {
            @Override
            public void onSuccess(AuthResponse data) {
                loginActivity.ocultarCarga();
                loginActivity.mostrarMensaje("Login exitoso");
                manejadorDeActividades.cambiarDeActividad(MainActivity.class);
                activity.finish();
            }

            @Override
            public void onError(String error) {
                loginActivity.ocultarCarga();
                loginActivity.mostrarErrorGeneral(error);
            }
        });
    }

    private boolean validarEmail(String email) {
        if (email.isEmpty()) {
            loginActivity.mostrarErrorEmail("El email es requerido");
            return false;
        }
        if (!ValidationUtils.esEmailValido(email)) {
            loginActivity.mostrarErrorEmail(ValidationUtils.getMensajeErrorEmail());
            return false;
        }
        return true;
    }

    private boolean validarContraseña(String contraseña) {
        if (contraseña.isEmpty()) {
            loginActivity.mostrarErrorContraseña("La contraseña es requerida");
            return false;
        }
        if (!ValidationUtils.esPasswordValido(contraseña)) {
            loginActivity.mostrarErrorContraseña(ValidationUtils.getMensajeErrorPassword());
            return false;
        }
        return true;
    }

    private String obtenerTexto(TextInputEditText editText) {
        if (editText.getText() != null) {
            return editText.getText().toString().trim();
        }
        return "";
    }
}
