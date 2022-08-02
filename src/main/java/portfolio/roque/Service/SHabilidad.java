package portfolio.roque.Service;
import portfolio.roque.Entity.Habilidad;
import portfolio.roque.Repository.RHabilidad;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SHabilidad {
     @Autowired
     RHabilidad rHabilidad;
     public List<Habilidad> list(){
         return rHabilidad.findAll();
     }
     public Optional<Habilidad> getOne(int id){
         return rHabilidad.findById(id);
     }
     public Optional<Habilidad> getByNombreH(String nombreH){
         return rHabilidad.findByNombreH(nombreH);
     }
     public void save(Habilidad habi){
         rHabilidad.save(habi);
     }
     public void delete(int id){
         rHabilidad.deleteById(id);
     }
     public boolean existsById(int id){
         return rHabilidad.existsById(id);
     }
     public boolean existsByNombreH(String nombreH){
         return rHabilidad.existsByNombreH(nombreH);
     }
}

