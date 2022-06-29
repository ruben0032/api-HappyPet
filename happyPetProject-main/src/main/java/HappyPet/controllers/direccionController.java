package HappyPet.controllers;

import HappyPet.dao.DireccionDao;
import HappyPet.models.Direccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST} )
public class direccionController {

    @Autowired
    DireccionDao direccionDao;

    @RequestMapping(value="api/direccion", method = RequestMethod.POST)
    public void PostDireccion(@RequestBody Direccion direccion){
        direccionDao.post(direccion);
    }

}
