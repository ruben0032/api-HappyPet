package HappyPet.dao;

import HappyPet.models.Detalle;
import HappyPet.models.Direccion;
import HappyPet.models.Orden;
import HappyPet.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class OrdenDaoImp implements OrdenDao{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DireccionDao direccionDao;

    @Autowired
    ProductoDao productoDao;

    @Override
    public List<Orden> getOrdenes() {
        String query = "FROM Orden";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Orden> getOrdenUser(Integer id) {
        String query = "FROM Orden WHERE idusuario = :id";
        List<Orden> lista = entityManager.createQuery(query).setParameter("id", id).getResultList();
        return lista;
    }

    @Override
    public Orden getOrden(Integer id) {
        String query = "FROM Orden WHERE id = :id";
        List<Orden> response = entityManager.createQuery(query).setParameter("id", id).getResultList();
        return response.get(0);
    }

    @Override
    @Transactional
    public Integer postOrden(Orden orden) {
        Direccion direccion = orden.getShippingAddress();

        Orden ordenfiltrada = new Orden();
        ordenfiltrada.setId(orden.getId());
        ordenfiltrada.setIsPaid(orden.getIsPaid());
        ordenfiltrada.setSubTotal(orden.getSubTotal());
        ordenfiltrada.setTax(orden.getTax());
        ordenfiltrada.setTotal(orden.getTotal());
        ordenfiltrada.setPaidAt(orden.getPaidAt());
        ordenfiltrada.setIdPaypal(orden.getIdPaypal());
        ordenfiltrada.setNumberOfItems(orden.getNumberOfItems());

        List<Detalle> detalles = orden.getOrderItems();

        Integer idDireccion = direccionDao.post(direccion);
        Integer idUsuario = orden.getUser().getId();

        entityManager.persist(ordenfiltrada);

        Integer idOrden = ordenfiltrada.getId();

        updateCamposOrden(idUsuario, idDireccion, idOrden);

        for(int i=0;i<detalles.size();i++){
            detalles.get(i).setIdOrden(idOrden);
            entityManager.persist(detalles.get(i));
        }
        return idOrden;
    }

    @Override
    public void pay(List<Detalle> detalles, Integer idOrden) {
        for(int i=0;i<detalles.size();i++){
            Producto product = productoDao.getProductoId(detalles.get(i).getIdproducto());
            Integer stock = product.getInStock() - detalles.get(i).getQuantity();
            Integer id = product.getId();
            if(product.getPrice().equals(detalles.get(i).getPrice()) && stock >= 0){
                productoDao.updateStock(id, stock);
            }
        }
        updateOrden(idOrden);
    }

    @Override
    public void updateOrden(Integer id) {
        String query = "UPDATE Orden SET isPaid = 'Pagado' WHERE id = :id";
        entityManager.createQuery(query).setParameter("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public void updateCamposOrden(Integer idUsuario, Integer idDireccion, Integer idOrden) {
        String query = "UPDATE Orden SET idusuario = :idU, iddireccion = :idD WHERE id = :id";
        entityManager.createQuery(query).setParameter("idU", idUsuario)
                .setParameter("idD", idDireccion)
                .setParameter("id", idOrden).executeUpdate();
    }
}
