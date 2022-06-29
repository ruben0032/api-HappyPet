package HappyPet.controllers;


import HappyPet.dao.OrdenDao;
import HappyPet.models.Imagen;
import HappyPet.models.Orden;

import HappyPet.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST} )
public class ordenController {

    @Autowired
    OrdenDao ordenDao;

    @RequestMapping(value = "api/orden", method = RequestMethod.GET)
    public List<Orden> getProductos(){
        return ordenDao.getOrdenes();
    }

    @RequestMapping(value="api/orden/byuser/{id}" , method = RequestMethod.GET)
    public List<Orden> getOrdenByUser(@PathVariable Integer id){
        return ordenDao.getOrdenUser(id);
    }

    @RequestMapping(value="api/orden/{id}" , method = RequestMethod.GET)
    public Orden getOrdenById(@PathVariable Integer id){
        return ordenDao.getOrden(id);
    }

    @RequestMapping(value="api/orden", method = RequestMethod.POST)
    public Integer Post(@RequestBody Orden orden){
        Integer id = ordenDao.postOrden(orden);
        return id;
    }
    @RequestMapping(value="api/orden/pay", method = RequestMethod.POST)
    public void update(@RequestBody Orden orden){
        ordenDao.pay(orden.getOrderItems(), orden.getId());
    }

}
