package HappyPet.dao;

import HappyPet.models.Imagen;
import HappyPet.models.Producto;
import HappyPet.models.Usuario;

import java.util.List;

public interface ProductoDao {

    List<Producto> getProductos();

    List<String> getIdProductos();

    Integer getId(String title);

    void update(Producto producto);

    void deleteImagen(String id);

    Producto getProductoId(Integer id);

    List<Producto> getProductosByType(String type);

    List<Producto> getProductosByTitle(String title);

    void updateStock(Integer id, Integer stock);

    void post(Producto producto, List<Imagen> imagen);

    void put(Producto producto, List<Imagen> imagen);

}
