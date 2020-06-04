package sw2.clase03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase03.entity.*;
import sw2.clase03.repository.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orden")
public class OrdenController {

    @Autowired
    OrdenRepository ordenRepository;

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    ShipperRepository shipperRepository;

    @Autowired
    OrdenDetailRepository ordenDetailRepository;

    @GetMapping(value = {"/list", ""})
    public String listarProductos(Model model) {

        List<Orden> lista = ordenRepository.findAll();
        model.addAttribute("ordenList", lista);

        return "orden/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(@ModelAttribute("orden") Orden orden,
                                   Model model) {

        List<Customers> listaCusto = customersRepository.findAll();
        List<Empleado> listaEmple = empleadoRepository.findAll();
        List<Shipper> listaShip = shipperRepository.findAll();

        model.addAttribute("listaCusto",listaCusto);
        model.addAttribute("listaEmple",listaEmple);
        model.addAttribute("listaShip",listaShip);

        return "orden/newFrm";

    }

    @PostMapping("/save")
    public String guardarNuevoProducto(@ModelAttribute("orden") @Valid Orden orden,
                                       BindingResult bindingResult,
                                       RedirectAttributes attr,
                                       Model model) {

        if(bindingResult.hasErrors()){

            List<Customers> listaCusto = customersRepository.findAll();
            List<Empleado> listaEmple = empleadoRepository.findAll();
            List<Shipper> listaShip = shipperRepository.findAll();

            model.addAttribute("listaCusto",listaCusto);
            model.addAttribute("listaEmple",listaEmple);
            model.addAttribute("listaShip",listaShip);

            return "orden/newFrm";


        }else{

            if (orden.getOrderid() == null) {
                attr.addFlashAttribute("msg", "Producto Creado Exitosamente");
            }
            else {
                attr.addFlashAttribute("msg", "Producto Actualizado Exitosamente");
            }

            //System.out.println("este es el id CTM" + products.getProductid());
            //System.out.println("este es el stock CTM" + products.getUnitsinstock());

            ordenRepository.save(orden);
            return "redirect:/orden/list";

        }

        /*
        if(products.getProductname().length()>6){
            if (products.getProductid() == null) {
                attr.addFlashAttribute("msg", "Producto Creado Exitosamente");
            }
            else {
                attr.addFlashAttribute("msg", "Producto Actualizado Exitosamente");
            }
            productRepository.save(products);
            return "redirect:/product/list";
        }else{
            if (products.getProductid() == null) {
                return "redirect:/product/new";
            }
            else {
                return "redirect:/product/edit";
            }
            //attr.addFlashAttribute("msg", "No Debe tener mas de 6 caracteres");
            //return "products/newFrm";
        }*/



    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("orden") Orden orden,
                                      Model model,
                                      @RequestParam("id") int id) {

        Optional<Orden> optionalProducts = ordenRepository.findById(id);

        List<Customers> listaCusto = customersRepository.findAll();
        List<Empleado> listaEmple = empleadoRepository.findAll();
        List<Shipper> listaShip = shipperRepository.findAll();

        //List<Categories> listacatego = categoryRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        if (optionalProducts.isPresent()) {

            orden = optionalProducts.get();

            model.addAttribute("orden", orden);

            model.addAttribute("listaCusto",listaCusto);
            model.addAttribute("listaEmple",listaEmple);
            model.addAttribute("listaShip",listaShip);

            return "orden/newFrm";
        } else {
            return "redirect:/orden/list";
        }
    }

    @PostMapping(value = "/buscarPorInicio")
    public String buscarPorInicio(@RequestParam("inicio") String inicio,
                                  Model model){

        //List<Shipper> listaTransportistas = shipperRepository.findByCompanyName(companyname);


        List<Orden> lista = ordenRepository.buscarTransportistasPorInicioDeNombre(inicio);

        model.addAttribute("ordenList",lista);

        return "orden/list";
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        List<OrdenDetail> listita = ordenDetailRepository.buscarDetalleOrdenPorIdOrden(id);

        Optional<Orden> optionalProducts = ordenRepository.findById(id);

        System.out.println("ests es lo q mide la lista" + listita.size());


        if(listita.size()==0){
            if (optionalProducts.isPresent()) {
                ordenRepository.deleteById(id);
                attr.addFlashAttribute("msg","Producto borrado exitosamente");
            }
        }else{

            attr.addFlashAttribute("msgError","Esta Orden No Se Puede Borrar");

        }

        /*

        if (optionalProducts.isPresent()) {
            productRepository.deleteById(id);
            attr.addFlashAttribute("msg","Producto borrado exitosamente");
        }
        */

        return "redirect:/orden/list";

    }
}
