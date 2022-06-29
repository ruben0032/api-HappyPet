package HappyPet.controllers;


import HappyPet.dao.UsuarioDao;
import HappyPet.models.Usuario;
import HappyPet.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST} )
public class sesionController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public Map<String, Map<String, String>> login(@RequestBody Usuario usuario) {
        Usuario usuarioLogueado = usuarioDao.obtenerUsuariosPorCredenciales(usuario);
        return createToken(usuarioLogueado, usuarioLogueado.getId());
    }

    @RequestMapping(value = "api/validate-token", method = RequestMethod.GET)
    public Map<String, Map<String, String>> validateToken(@RequestHeader(value = "Authorization") String tokenUsuario) {

        System.out.println(tokenUsuario);
        String usuarioId = jwtUtil.getKey(tokenUsuario);
        Usuario usuarioLogueado = usuarioDao.getUsuario(Integer.parseInt(usuarioId));
        System.out.println(usuarioLogueado);

        return createToken(usuarioLogueado, Integer.parseInt(usuarioId));

    }

    @RequestMapping(value = "api/validtoken", method = RequestMethod.GET)
    public Map<String, String> validToken(@RequestHeader(value = "Authorization") String tokenUsuario) {

        String usuarioId = jwtUtil.getKey(tokenUsuario);
        Usuario usuarioLogueado = usuarioDao.getUsuario(Integer.parseInt(usuarioId));

        Map<String, String> map = new HashMap<String, String>();

        if (usuarioLogueado != null){

            map.put("id", String.valueOf(usuarioLogueado.getId()));
            map.put("rol",usuarioLogueado.getRol());

            System.out.println(map);
            return map;
        }

        return map;
    }


    private Map<String, Map<String, String>> createToken(Usuario usuarioLogueado, int id) {


        String usuarioCorreo = usuarioLogueado.getCorreo();
        String usuarioNombre = usuarioLogueado.getNombre();
        String usuarioRol = usuarioLogueado.getRol();


        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

        Map<String,String> user = new HashMap<String,String>();
        Map<String,String> token = new HashMap<String,String>();

        user.put("correo",usuarioCorreo);
        user.put("rol",usuarioRol);
        user.put("nombre",usuarioNombre);

        if (usuarioLogueado != null){

            String tokenjwt = jwtUtil.create(String.valueOf(id), usuarioCorreo);
            token.put("jwt", tokenjwt);

            map.put("user", user);
            map.put("token",token);

            System.out.println(map);
            return map;
        }

        return map;
    }

}
