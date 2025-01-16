package pt.xy_inc.xyAPI.controller.dtoRequest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class POIRequestDTO {

    private String nomePoi;

    @Min(value = 0, message = "This coordenate must be positive")
    private Integer coordenadaX;

    @Min(value = 0, message = "This coordenate must be positive")
    private Integer coordenadaY;

    public String getNomePoi() {
        return nomePoi;
    }

    public void setNomePoi(String nomePoi) {
        this.nomePoi = nomePoi;
    }

    public Integer getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Integer coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Integer getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Integer coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
}
