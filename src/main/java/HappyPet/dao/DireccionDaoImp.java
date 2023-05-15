package HappyPet.dao;

import HappyPet.models.Direccion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class DireccionDaoImp implements DireccionDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Integer post(Direccion direccion) {
        entityManager.persist(direccion);
        return direccion.getId();
    }
}
