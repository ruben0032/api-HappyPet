package HappyPet.dao;

import HappyPet.models.Imagen;
import HappyPet.models.Producto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ProductoDaoImp implements ProductoDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Producto> getProductos() {
        String query = "FROM Producto";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<String> getIdProductos() {
        String query = "SELECT id FROM Producto";
        List<Integer> lista = entityManager.createQuery(query).getResultList();
        List<String> result = new ArrayList<String>();
        for(int i=0;i<lista.size();i++){
            result.add(lista.get(i).toString());
        }
        return result;
    }

    @Override
    public Integer getId(String title) {
        String query = "SELECT id FROM Producto WHERE title = :title";
        List<Integer> id = entityManager.createQuery(query).setParameter("title", title).getResultList();
        return id.get(0);
    }

    @Override
    public void update(Producto producto) {
        String query = "UPDATE Producto SET sizes = :sizes, tags = :tags, type = :type, gender = :gender, description = :description, title = :title, inStock = :inStock, price = :price, date = :date, status = :status WHERE id = :id";
        entityManager.createQuery(query).setParameter("sizes", producto.getSizes())
                .setParameter("tags", producto.getTags())
                .setParameter("type", producto.getType())
                .setParameter("gender", producto.getGender())
                .setParameter("description", producto.getDescription())
                .setParameter("title", producto.getTitle())
                .setParameter("inStock", producto.getInStock())
                .setParameter("price", producto.getPrice())
                .setParameter("date", producto.getDate())
                .setParameter("status", producto.getStatus())
                .setParameter("id", producto.getId()).executeUpdate();
    }

    @Override
    public void deleteImagen(String id) {
        Imagen imagen = entityManager.find(Imagen.class, id);
        entityManager.remove(imagen);
    }

    @Override
    public Producto getProductoId(Integer id) {
        String query = "FROM Producto WHERE id = :id";
        List<Producto> lista = entityManager.createQuery(query).setParameter("id", id).getResultList();
        return lista.get(0);
    }

    @Override
    public List<Producto> getProductosByType(String type) {
        List<Producto> listaType = getProductos();
        List<Producto> response = listaType.stream().filter(p -> p.getType().contains(type)).collect(Collectors.toList());
        return response;
    }

    @Override
    public List<Producto> getProductosByTitle(String title) {
        List<Producto> listaTitle = getProductos();
        List<Producto> response = listaTitle.stream().filter(p -> p.getTitle().contains(title)).collect(Collectors.toList());
        return response;
    }

    @Override
    public void updateStock(Integer id, Integer stock) {
        String query = "UPDATE Producto SET inStock = :stock WHERE id = :id";
        entityManager.createQuery(query).setParameter("stock", stock)
                .setParameter("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public void post(Producto producto, List<Imagen> imagen) {
        entityManager.merge(producto);
        Integer id = getId(producto.getTitle());
        for(int i=0;i<imagen.size();i++){
            imagen.get(i).setIdProducto(id);
            entityManager.merge(imagen.get(i));
        }
    }

    @Override
    @Transactional
    public void put(Producto producto, List<Imagen> imagen) {
        update(producto);
        Integer id = producto.getId();
        for(int i=0;i<imagen.size();i++){
            imagen.get(i).setIdProducto(id);
            entityManager.merge(imagen.get(i));
        }
    }
}
