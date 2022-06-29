package HappyPet.dao;

import HappyPet.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

    void delete( Integer id);

    void post(Usuario usuario);

    Usuario getUsuario(Integer id);

    Usuario obtenerUsuariosPorCredenciales(Usuario usuario);
}
