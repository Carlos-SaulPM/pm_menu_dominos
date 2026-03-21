package fes.carlos.menudominus.controllers;

import android.app.Activity;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import fes.carlos.menudominus.R;
import fes.carlos.menudominus.activities.LoginActivity;
import fes.carlos.menudominus.activities.RegistrationActivity;
import fes.carlos.menudominus.models.ClienteModel;
import fes.carlos.menudominus.models.ManejadorDeActividadesModel;
import fes.carlos.menudominus.services.ApiCallback;
import fes.carlos.menudominus.services.AuthService;
import fes.carlos.menudominus.utils.ValidationUtils;

public class RegisterController {
    private Activity activity;
    private RegistrationActivity registrationActivity;
    private TextInputEditText editTextNombre;
    private TextInputEditText editTextApellidos;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextContraseña;
    private MaterialButton buttonRegistrarse;
    private AuthService authService;
    private ManejadorDeActividadesModel manejadorDeActividades;

    public RegisterController(Activity activity) {
        this.activity = activity;
        this.registrationActivity = (RegistrationActivity) activity;
        this.authService = new AuthService();
        this.manejadorDeActividades = ManejadorDeActividadesModel.getInstancia();
        this.manejadorDeActividades.setActivity(activity);
    }

    public void inicializarRegistro() {
        editTextNombre = registrationActivity.getEditTextNombre();
        editTextApellidos = registrationActivity.getEditTextApellidos();
        editTextEmail = registrationActivity.getEditTextEmail();
        editTextContraseña = registrationActivity.getEditTextContraseña();
        buttonRegistrarse = registrationActivity.getButtonRegistrarse();

        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        registrationActivity.mostrarCarga();
        registrationActivity.limpiarErrores();

        String nombre = obtenerTexto(editTextNombre);
        String apellidos = obtenerTexto(editTextApellidos);
        String email = obtenerTexto(editTextEmail);
        String contraseña = obtenerTexto(editTextContraseña);

        if (!validarNombre(nombre)) {
            registrationActivity.ocultarCarga();
            return;
        }

        if (!validarApellidos(apellidos)) {
            registrationActivity.ocultarCarga();
            return;
        }

        if (!validarEmail(email)) {
            registrationActivity.ocultarCarga();
            return;
        }

        if (!validarContraseña(contraseña)) {
            registrationActivity.ocultarCarga();
            return;
        }

        ClienteModel cliente = new ClienteModel(nombre, apellidos, email, contraseña);
        authService.registrarCliente(cliente, new ApiCallback<fes.carlos.menudominus.models.AuthResponse>() {
            @Override
            public void onSuccess(fes.carlos.menudominus.models.AuthResponse data) {
                registrationActivity.ocultarCarga();
                registrationActivity.mostrarMensaje("Registro exitoso");
                manejadorDeActividades.cambiarDeActividad(LoginActivity.class);
                activity.finish();
            }

            @Override
            public void onError(String error) {
                registrationActivity.ocultarCarga();
                registrationActivity.mostrarErrorGeneral(error);
            }
        });
    }

    private boolean validarNombre(String nombre) {
        if (nombre.isEmpty()) {
            registrationActivity.mostrarErrorNombre("El nombre es requerido");
            return false;
        }
        if (!ValidationUtils.esNombreValido(nombre)) {
            registrationActivity.mostrarErrorNombre(ValidationUtils.getMensajeErrorNombre());
            return false;
        }
        return true;
    }

    private boolean validarApellidos(String apellidos) {
        if (apellidos.isEmpty()) {
            registrationActivity.mostrarErrorApellidos("Los apellidos son requeridos");
            return false;
        }
        if (!ValidationUtils.esApellidoValido(apellidos)) {
            registrationActivity.mostrarErrorApellidos(ValidationUtils.getMensajeErrorApellido());
            return false;
        }
        return true;
    }

    private boolean validarEmail(String email) {
        if (email.isEmpty()) {
            registrationActivity.mostrarErrorEmail("El email es requerido");
            return false;
        }
        if (!ValidationUtils.esEmailValido(email)) {
            registrationActivity.mostrarErrorEmail(ValidationUtils.getMensajeErrorEmail());
            return false;
        }
        return true;
    }

    private boolean validarContraseña(String contraseña) {
        if (contraseña.isEmpty()) {
            registrationActivity.mostrarErrorContraseña("La contraseña es requerida");
            return false;
        }
        if (!ValidationUtils.esPasswordValido(contraseña)) {
            registrationActivity.mostrarErrorContraseña(ValidationUtils.getMensajeErrorPassword());
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
