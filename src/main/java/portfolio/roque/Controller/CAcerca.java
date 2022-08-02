package portfolio.roque.Controller;

import portfolio.roque.Dto.dtoAcerca;
import portfolio.roque.Dto.dtoHabilidad;
import portfolio.roque.Entity.Acerca;
import portfolio.roque.Entity.Habilidad;
import portfolio.roque.Security.Controller.Mensaje;
import portfolio.roque.Service.SAcerca;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acerc") //parametro a cambiar dependiendo el componente
@CrossOrigin(origins = "https://backend-portfolio3.herokuapp.com")
public class CAcerca {
    @Autowired
    SAcerca sAcerca;
  
    @GetMapping("/lista")
    public ResponseEntity<List<Acerca>> list(){
        List<Acerca> list = sAcerca.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id") int id){
        if(!sAcerca.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Acerca acerca = sAcerca.getOne(id).get();
        return new ResponseEntity(acerca, HttpStatus.OK);
    }   

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoAcerca dtoace){      
        if(StringUtils.isBlank(dtoace.getNombreA()))
            return new ResponseEntity(new Mensaje("la Acerca es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sAcerca.existsByNombreA(dtoace.getNombreA()))
            return new ResponseEntity(new Mensaje("Esa Acerca existe"), HttpStatus.BAD_REQUEST);
        
        Acerca acerca = new Acerca(dtoace.getNombreA());
        sAcerca.save(acerca);
        
        return new ResponseEntity(new Mensaje("Habilidad agregada"), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoAcerca dtoace){
        //Validamos si existe el ID
        if(!sAcerca.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        //Compara nombre de experiencias

        //No puede estar vacio
        if(StringUtils.isBlank(dtoace.getNombreA()))
            return new ResponseEntity(new Mensaje("el texto es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Acerca acerca = sAcerca.getOne(id).get();
        acerca.setNombreA(dtoace.getNombreA());
                
        sAcerca.save(acerca);
        return new ResponseEntity(new Mensaje("Acerca actualizada"), HttpStatus.OK);
    }
}
