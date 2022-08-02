package portfolio.roque.Dto;
import javax.validation.constraints.NotBlank;

public class dtoAcerca {
    @NotBlank
    private String nombreA;

//Constructores
    public dtoAcerca() {
    }
    public dtoAcerca(String nombreA) {
        this.nombreA = nombreA;
    }
//Getters & Setters
    public String getNombreA() {
        return nombreA;
    }
    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }
}