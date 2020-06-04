package sw2.clase03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase03.entity.Customers;
import sw2.clase03.repository.CustomersRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomersRepository customersRepository;

    @GetMapping(value = {"/list", ""})
    public String listarProductos(Model model) {

        List<Customers> lista = customersRepository.findAll();
        model.addAttribute("custoList", lista);

        return "customer/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(Model model) {


        //List<Categories> listacatego = categoryRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        //model.addAttribute("listacatego",listacatego);
        //model.addAttribute("listasuppli",listasuppli);

        return "customer/newFrm";
    }

    @PostMapping(value = "/buscarPorInicio")
    public String buscarPorInicio(@RequestParam("inicio") String inicio,
                                  Model model){

        //List<Shipper> listaTransportistas = shipperRepository.findByCompanyName(companyname);


        List<Customers> lista = customersRepository.buscarTransportistasPorInicioDeNombre(inicio);

        model.addAttribute("custoList",lista);

        return "customer/list";
    }

    @PostMapping("/save")
    public String guardarNuevoProducto(Customers customers,
                                       RedirectAttributes attr) {


        String identif = customers.getCustomerid();

        Optional<Customers>custo=customersRepository.findById(identif);

        if(custo.isPresent()){
            attr.addFlashAttribute("msg", "Producto Actualizado Exitosamente");
        }else{
            attr.addFlashAttribute("msg", "Producto Creado Exitosamente");
        }

        customersRepository.save(customers);

        return "redirect:/customer/list";

    }

    @GetMapping("/edit")
    public String editarTransportista(Model model,
                                      @RequestParam("id") String id) {

        Optional<Customers> optionalCategories = customersRepository.findById(id);

        //List<Categories> listacatego = categoryRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        if (optionalCategories.isPresent()) {
            Customers customers = optionalCategories.get();
            model.addAttribute("customers", customers);
            //model.addAttribute("listacatego",listacatego);
            //model.addAttribute("listasuppli",listasuppli);

            return "customer/editFrm";
        } else {
            return "redirect:/customer/list";
        }
    }


}