package sw2.clase03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase03.entity.Rol;
import sw2.clase03.entity.Usuario;
import sw2.clase03.repository.RolRepository;
import sw2.clase03.repository.UsuarioRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @GetMapping(value = {"/list", ""})
    public String listarProductos(Model model) {

        List<Usuario> lista = usuarioRepository.findAll();
        model.addAttribute("usuarioList", lista);

        return "usuario/list";
    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("usuario") Usuario usuario,
                                      Model model,
                                      @RequestParam("id") int id) {

        Optional<Usuario> optionalProducts = usuarioRepository.findById(id);


        List<Rol> listarol = rolRepository.findAll();

        //List<Suppliers> listasuppli = supplierRepository.findAll();

        if (optionalProducts.isPresent()) {
            usuario = optionalProducts.get();

            model.addAttribute("usuario", usuario);

            model.addAttribute("listarol",listarol);
            //model.addAttribute("listasuppli",listasuppli);

            return "usuario/newFrm";
        } else {
            return "redirect:/usuario/list";
        }
    }

    @PostMapping("/save")
    public String guardarNuevoProducto(@ModelAttribute("usuario") @Valid Usuario usuario,
                                       BindingResult bindingResult,
                                       RedirectAttributes attr, Model model) {


        if(bindingResult.hasErrors()){

            List<Rol> listarol = rolRepository.findAll();
            //List<Rok> listacatego = categoryRepository.findAll();
            //List<Suppliers> listasuppli = supplierRepository.findAll();

            model.addAttribute("listarol",listarol);
            //model.addAttribute("listasuppli",listasuppli);

            return "usuario/newFrm";

        }else{
            if (usuario.getIdusuario() == null) {
                attr.addFlashAttribute("msg", "Usuario Creado Exitosamente");
                System.out.println("este es el id CTM" + usuario.getIdusuario());
            }
            else {
                attr.addFlashAttribute("msg", "Usuario Actualizado Exitosamente");
                System.out.println("este es el id CTM" + usuario.getIdusuario());
            }

            //System.out.println("este es el id CTM" + products.getProductid());
            //System.out.println("este es el stock CTM" + products.getUnitsinstock());

            usuarioRepository.save(usuario);
            return "redirect:/usuario/list";

        }

    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Usuario> optionalProducts = usuarioRepository.findById(id);

        //System.out.println("ests es lo q mide la lista" + listita.size());

        if (optionalProducts.isPresent()) {
            usuarioRepository.deleteById(id);
            attr.addFlashAttribute("msg","Usuario borrado exitosamente");
        }

        /*

        if (optionalProducts.isPresent()) {
            productRepository.deleteById(id);
            attr.addFlashAttribute("msg","Producto borrado exitosamente");
        }
        */

        return "redirect:/usuario/list";

    }

    @PostMapping(value = "/buscarPorInicio")
    public String buscarPorInicio(@RequestParam("inicio") String inicio,
                                  Model model){

        //List<Shipper> listaTransportistas = shipperRepository.findByCompanyName(companyname);


        List<Usuario> lista = usuarioRepository.buscarUsuariosPorInicioDeNombre(inicio);

        model.addAttribute("usuarioList",lista);

        return "usuario/list";
    }
}
