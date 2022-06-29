package HappyPet.controllers;
import HappyPet.dao.ProductoDao;
import HappyPet.models.Imagen;
import HappyPet.models.Producto;
import HappyPet.utils.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT} )
public class productoController {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    CloudinaryUtil cloudinaryUtil;


    @RequestMapping(value="api/producto/{id}" , method = RequestMethod.GET)
    public Producto getProducto(@PathVariable Integer id){
        return productoDao.getProductoId(id);
    }

    @RequestMapping(value = "api/producto", method = RequestMethod.GET)
    public List<Producto> getProductos(){
        return productoDao.getProductos();
    }

    @RequestMapping(value = "api/producto/lista", method = RequestMethod.GET)
    public List<String> getListaId(){
        return productoDao.getIdProductos();
    }

    @RequestMapping(value="api/producto/tipo/{type}" , method = RequestMethod.GET)
    public List<Producto> getProductosType(@PathVariable String type){ return  productoDao.getProductosByType(type); }

    @RequestMapping(value="api/producto/nombre/{title}" , method = RequestMethod.GET)
    public List<Producto> getProductosTitle(@PathVariable String title){ return  productoDao.getProductosByTitle(title); }


    @RequestMapping(value = "api/producto/cloud/upload", method = RequestMethod.POST)
    public ResponseEntity<Map> postCloud(@RequestBody MultipartFile multipartFile) throws IOException {
        Map result = cloudinaryUtil.upload(multipartFile);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "api/producto/cloud/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map> deleteCloud(@PathVariable String id) throws IOException {
        Map result = cloudinaryUtil.delete(id);
        productoDao.deleteImagen(id);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value="api/producto", method = RequestMethod.POST)
    public void PostPruducto(@RequestBody Producto producto){
        List<Imagen> lista = producto.getImages();
        Producto productoFiltrado = new Producto();
        productoFiltrado.setDescription(producto.getDescription());
        productoFiltrado.setInStock(producto.getInStock());
        productoFiltrado.setPrice(producto.getPrice());
        productoFiltrado.setSizes(producto.getSizes());
        productoFiltrado.setTags(producto.getTags());
        productoFiltrado.setTitle(producto.getTitle());
        productoFiltrado.setType(producto.getType());
        productoFiltrado.setGender(producto.getGender());
        productoFiltrado.setDate(producto.getDate());

        productoDao.post(productoFiltrado, lista);
    }

    @RequestMapping(value="api/producto", method = RequestMethod.PUT)
    public void PutPruducto(@RequestBody Producto producto){
        List<Imagen> lista = producto.getImages();
        Producto productoFiltrado = new Producto();

        System.out.println("********************");
        System.out.println(lista);


        productoFiltrado.setId(producto.getId());
        productoFiltrado.setDescription(producto.getDescription());
        productoFiltrado.setInStock(producto.getInStock());
        productoFiltrado.setPrice(producto.getPrice());
        productoFiltrado.setSizes(producto.getSizes());
        productoFiltrado.setTags(producto.getTags());
        productoFiltrado.setTitle(producto.getTitle());
        productoFiltrado.setType(producto.getType());
        productoFiltrado.setGender(producto.getGender());
        productoFiltrado.setDate(producto.getDate());
        productoFiltrado.setStatus(producto.getStatus());

        System.out.println(productoFiltrado);

        productoDao.put(productoFiltrado, lista);
    }

}
