package HappyPet.controllers;
import HappyPet.utils.JWTUtil;
import HappyPet.dao.UsuarioDao;
import HappyPet.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST} )
public class usuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        //
        return usuarioId != null;
    }

    @RequestMapping(value="api/usuario", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(){

        return usuarioDao.getUsuarios();

    }

    @RequestMapping(value="api/usuario/{id}" , method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Integer id){
        Usuario usuario = usuarioDao.getUsuario(id);
        return usuario;
    }



    @RequestMapping(value="api/usuario", method = RequestMethod.POST)
    public void PostUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.post(usuario);
    }

    /*
    @RequestMapping(value="api/usuario/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@PathVariable Integer id){
        usuarioDao.delete(id);
    }
    */


}
