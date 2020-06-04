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
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolRepository rolRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = {"/list", ""})
    public String listarProductos(Model model) {

        List<Rol> lista = rolRepository.findAll();
        model.addAttribute("rolList", lista);

        return "rol/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(@ModelAttribute("rol") Rol rol,
                                   Model model) {

        //List<Categories> listacatego = categoryRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        //model.addAttribute("listacatego",listacatego);
        //model.addAttribute("listasuppli",listasuppli);

        return "rol/newFrm";
    }
    @PostMapping("/save")
    public String guardarNuevoProducto(@ModelAttribute("rol") @Valid Rol rol,
                                       BindingResult bindingResult,
                                       RedirectAttributes attr, Model model) {


        if(bindingResult.hasErrors()){

            //List<Categories> listacatego = categoryRepository.findAll();
            //List<Suppliers> listasuppli = supplierRepository.findAll();

            //model.addAttribute("listacatego",listacatego);
            //model.addAttribute("listasuppli",listasuppli);

            return "rol/newFrm";

        }else{
            if (rol.getIdrol() == null) {
                attr.addFlashAttribute("msg", "Producto Creado Exitosamente");
            }
            else {
                attr.addFlashAttribute("msg", "Producto Actualizado Exitosamente");
            }

            //System.out.println("este es el id CTM" + products.getProductid());
            //System.out.println("este es el stock CTM" + products.getUnitsinstock());

            rolRepository.save(rol);
            return "redirect:/rol/list";

        }

    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("rol") Rol rol,
                                      Model model,
                                      @RequestParam("id") int id) {

        Optional<Rol> optionalProducts = rolRepository.findById(id);

        //List<Categories> listacatego = categoryRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        if (optionalProducts.isPresent()) {
            rol = optionalProducts.get();

            model.addAttribute("rol", rol);

            //model.addAttribute("listacatego",listacatego);
            //model.addAttribute("listasuppli",listasuppli);

            return "rol/newFrm";
        } else {
            return "redirect:/rol/list";
        }
    }

    @PostMapping(value = "/buscarPorInicio")
    public String buscarPorInicio(@RequestParam("inicio") String inicio,
                                  Model model){

        //List<Shipper> listaTransportistas = shipperRepository.findByCompanyName(companyname);

        List<Rol> lista = rolRepository.buscarRolPorInicioDeNombre(inicio);

        model.addAttribute("rolList",lista);

        return "rol/list";
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        List<Usuario> listita = usuarioRepository.buscarSiTieneUnRolAsociado(id);

        Optional<Rol> optionalProducts = rolRepository.findById(id);

        System.out.println("esto es lo q mide la lista" + listita.size());


        if(listita.size()==0){
            if (optionalProducts.isPresent()) {
                rolRepository.deleteById(id);
                attr.addFlashAttribute("msg","Rol borrado exitosamente");
            }
        }else{

            attr.addFlashAttribute("msgError","Este Rol No Se Puede Borrar");

        }

        /*

        if (optionalProducts.isPresent()) {
            productRepository.deleteById(id);
            attr.addFlashAttribute("msg","Producto borrado exitosamente");
        }
        */

        return "redirect:/rol/list";

    }

}
