package fes.carlos.menudominus.utils;

import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Pattern PATTERN_NOMBRE = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,30}$");
    private static final Pattern PATTERN_EMAIL = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PATTERN_PASSWORD = Pattern.compile("^.{6,20}$");

    public static boolean esNombreValido(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        return PATTERN_NOMBRE.matcher(nombre.trim()).matches();
    }

    public static boolean esApellidoValido(String apellidos) {
        if (apellidos == null || apellidos.trim().isEmpty()) {
            return false;
        }
        return PATTERN_NOMBRE.matcher(apellidos.trim()).matches();
    }

    public static boolean esEmailValido(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return PATTERN_EMAIL.matcher(email.trim()).matches();
    }

    public static boolean esPasswordValido(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return PATTERN_PASSWORD.matcher(password).matches();
    }

    public static String getMensajeErrorNombre() {
        return "El nombre debe tener entre 2 y 30 caracteres (solo letras)";
    }

    public static String getMensajeErrorApellido() {
        return "Los apellidos deben tener entre 2 y 30 caracteres (solo letras)";
    }

    public static String getMensajeErrorEmail() {
        return "Ingresa un email válido";
    }

    public static String getMensajeErrorPassword() {
        return "La contraseña debe tener entre 6 y 20 caracteres";
    }
}
