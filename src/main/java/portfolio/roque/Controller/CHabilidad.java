package portfolio.roque.Controller;

import portfolio.roque.Dto.dtoHabilidad;
import portfolio.roque.Entity.Habilidad;
import portfolio.roque.Security.Controller.Mensaje;
import portfolio.roque.Service.SHabilidad;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/habil") //parametro a cambiar dependiendo el componente
@CrossOrigin(origins = "https://backend-portfolio3.herokuapp.com")
public class CHabilidad {
    @Autowired
    SHabilidad sHabilidad;
  
    @GetMapping("/lista")
    public ResponseEntity<List<Habilidad>> list(){
        List<Habilidad> list = sHabilidad.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id") int id){
        if(!sHabilidad.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Habilidad habilidad = sHabilidad.getOne(id).get();
        return new ResponseEntity(habilidad, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHabilidad.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sHabilidad.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHabilidad dtohab){      
        if(StringUtils.isBlank(dtohab.getNombreH()))
            return new ResponseEntity(new Mensaje("la hablidad es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sHabilidad.existsByNombreH(dtohab.getNombreH()))
            return new ResponseEntity(new Mensaje("Esa habilidad existe"), HttpStatus.BAD_REQUEST);
        
        Habilidad habilidad = new Habilidad(dtohab.getNombreH());
        sHabilidad.save(habilidad);
        
        return new ResponseEntity(new Mensaje("Habilidad agregada"), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHabilidad dtohab){
        //Validamos si existe el ID
        if(!sHabilidad.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        //Compara nombre de experiencias
        if(sHabilidad.existsByNombreH(dtohab.getNombreH()) && sHabilidad.getByNombreH(dtohab.getNombreH()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        //No puede estar vacio
        if(StringUtils.isBlank(dtohab.getNombreH()))
            return new ResponseEntity(new Mensaje("La habilidad es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Habilidad habilidad = sHabilidad.getOne(id).get();
        habilidad.setNombreH(dtohab.getNombreH());
                
        sHabilidad.save(habilidad);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);
    }
}
