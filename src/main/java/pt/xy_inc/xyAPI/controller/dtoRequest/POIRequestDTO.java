package pt.xy_inc.xyAPI.controller.dtoRequest;

public class POIRequestDTO {

    private String nomePoi;
    private Integer coordenadaX;
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
