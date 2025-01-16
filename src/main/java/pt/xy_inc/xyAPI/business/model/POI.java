package pt.xy_inc.xyAPI.business.model;

public class POI {

    private Long id;
    private String nomePoi;
    private Integer coordenadaX;
    private Integer coordenadaY;

    public POI() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
