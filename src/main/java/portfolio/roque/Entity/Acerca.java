package portfolio.roque.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Acerca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String nombreA;
//Constructores
    public Acerca() {
    }
    public Acerca(String nombreA) {
        this.nombreA = nombreA;
    }
//Getters and settersW
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreA() {
        return nombreA;
    }
    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }
}