package fes.carlos.menudominus.models;

import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class ImagenModel {
    private String nombreImagen;
    private String idDeLaImagen;

    public ImagenModel(String nombreImagen, String idDeLaImagen) {
        this.nombreImagen = nombreImagen;
        this.idDeLaImagen = idDeLaImagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getIdDeLaImagen() {
        return idDeLaImagen;
    }

    public void setIdDeLaImagen(String idDeLaImagen) {
        this.idDeLaImagen = idDeLaImagen;
    }
}
