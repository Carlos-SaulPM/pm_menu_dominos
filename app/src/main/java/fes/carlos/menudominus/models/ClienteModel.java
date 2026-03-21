package fes.carlos.menudominus.models;

public class ClienteModel {
    private String encodedkey;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasenia;

    public ClienteModel(String nombre, String apellidos, String correo, String contrasenia) {
        this.encodedkey = null;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public String getEncodedkey() {
        return encodedkey;
    }

    public void setEncodedkey(String encodedkey) {
        this.encodedkey = encodedkey;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}