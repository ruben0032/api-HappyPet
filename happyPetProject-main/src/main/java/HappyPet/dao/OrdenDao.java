package HappyPet.dao;


import HappyPet.models.Detalle;
import HappyPet.models.Orden;

import java.util.List;

public interface OrdenDao {
    List<Orden> getOrdenes();

    List<Orden> getOrdenUser(Integer id);

    Orden getOrden(Integer id);

    Integer postOrden(Orden orden);

    void pay(List<Detalle> detalles, Integer idOrden);

    void updateOrden(Integer id);

    void updateCamposOrden(Integer idUsuario, Integer idDireccion, Integer idOrden);
}
