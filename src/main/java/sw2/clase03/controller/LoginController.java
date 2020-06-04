package sw2.clase03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sw2.clase03.entity.Usuario;
import sw2.clase03.entity.UsuarioSession;
import sw2.clase03.repository.UsuarioRepository;
import sw2.clase03.repository.UsuarioSessionRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UsuarioSessionRepository usuarioSessionRepository;

    @GetMapping(value = {"/loginForm"})
    public String loginForm() {

        return "login/loginForm";
    }

    @GetMapping(value = {"/redirectByRole"})
    public String redirectByRole(Authentication authentication, HttpSession session) {

        String rol = "";
        for (GrantedAuthority role : authentication.getAuthorities()) {
            rol = role.getAuthority();
            break;
        }

        String username = authentication.getName();
        UsuarioSession usuario = usuarioSessionRepository.findByEmail(username);

        session.setAttribute("usu",usuario);

        if (rol.equals("admin")) {
            return "redirect:/shipper/";
        } else {
            return "redirect:/product/";
        }

    }





}