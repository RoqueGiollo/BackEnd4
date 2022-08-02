package portfolio.roque.Dto;
import javax.validation.constraints.NotBlank;

public class dtoHabilidad {
    @NotBlank
    private String nombreH;

//Constructores
    public dtoHabilidad() {
    }
    public dtoHabilidad(String nombreH) {
        this.nombreH = nombreH;
    }
//Getters & Setters
    public String getNombreH() {
        return nombreH;
    }
    public void setNombreH(String nombreH) {
        this.nombreH = nombreH;
    }
}