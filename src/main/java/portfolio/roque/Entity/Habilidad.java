package portfolio.roque.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreH;
    
//Constructores
    public Habilidad() {
    }
    public Habilidad(String nombreH) {
        this.nombreH = nombreH;
    }
//Getters and settersW
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreH() {
        return nombreH;
    }
    public void setNombreH(String nombreH) {
        this.nombreH = nombreH;
    }
}