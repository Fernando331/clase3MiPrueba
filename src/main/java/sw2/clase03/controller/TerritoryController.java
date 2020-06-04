package sw2.clase03.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase03.entity.Products;
import sw2.clase03.entity.Region;
import sw2.clase03.entity.Territories;
import sw2.clase03.repository.RegionRepository;
import sw2.clase03.repository.TerritoryRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/territory")
public class TerritoryController {

    @Autowired
    TerritoryRepository territoryRepository;

    @Autowired
    RegionRepository regionRepository;

    @GetMapping(value = {"/list", ""})
    public String listarProductos(Model model) {

        List<Territories> lista = territoryRepository.findAll();
        model.addAttribute("territList", lista);

        return "territory/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(@ModelAttribute("territories") Territories territories,
                                   Model model) {

        List<Region> listareg = regionRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        model.addAttribute("listareg",listareg);
        //model.addAttribute("listasuppli",listasuppli);

        return "territory/newFrm";
    }

    @PostMapping("/save")
    public String guardarNuevoProducto(@ModelAttribute("territories") @Valid Territories territories,
                                       BindingResult bindingResult,
                                       RedirectAttributes attr, Model model) {


        if(bindingResult.hasErrors()){

            List<Region> listareg = regionRepository.findAll();

            model.addAttribute("listareg",listareg);

            return "territory/newFrm";

        }else{

            String ident = territories.getTerritoryid();
            Optional<Territories> terri = territoryRepository.findById(ident);

            if (terri.isPresent()) {
                attr.addFlashAttribute("msg", "Territorio Actualizada Exitosamente");
            }
            else {
                attr.addFlashAttribute("msg", "Territorio Creada Exitosamente");
            }

            //System.out.println("este es el id CTM" + products.getProductid());
            //System.out.println("este es el stock CTM" + products.getUnitsinstock());

            territoryRepository.save(territories);
            return "redirect:/territory/list";

        }

        /*
        if (products.getProductid() == null) {
            attr.addFlashAttribute("msg", "Producto Creado Exitosamente");
        }
        else {
            attr.addFlashAttribute("msg", "Producto Actualizado Exitosamente");
        }

        System.out.println("este es el id CTM" + products.getProductid());
        System.out.println("este es el stock CTM" + products.getUnitsinstock());

        productRepository.save(products);
        return "redirect:/product/list";
        */
    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("territories") Territories territories,
                                      Model model,
                                      @RequestParam("id") String id) {

        Optional<Territories> optionalProducts = territoryRepository.findById(id);

        List<Region> listareg = regionRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        //List<Categories> listacatego = categoryRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        if (optionalProducts.isPresent()) {
            territories = optionalProducts.get();

            model.addAttribute("listareg",listareg);
            model.addAttribute("territories", territories);
            //model.addAttribute("listacatego",listacatego);
            //model.addAttribute("listasuppli",listasuppli);

            return "territory/newFrm";
        } else {
            return "redirect:/territory/list";
        }
    }

    @PostMapping(value = "/buscarPorInicio")
    public String buscarPorInicio(@RequestParam("inicio") String inicio,
                                  Model model){

        //List<Shipper> listaTransportistas = shipperRepository.findByCompanyName(companyname);


        List<Territories> lista = territoryRepository.buscarTransportistasPorInicioDeNombre(inicio);

        model.addAttribute("territList",lista);

        return "territory/list";
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        List<Territories> listita = territoryRepository.buscarSiSePuedeBorrarRegion(id);

        Optional<Region> optionalProducts = regionRepository.findById(id);

        System.out.println("ests es lo q mide la lista" + listita.size());


        if(listita.size()==0){
            if (optionalProducts.isPresent()) {
                regionRepository.deleteById(id);
                attr.addFlashAttribute("msg","Region borrada exitosamente");
            }
        }else{

            attr.addFlashAttribute("msgError","Esta Region No Se Puede Borrar");

        }

        /*

        if (optionalProducts.isPresent()) {
            productRepository.deleteById(id);
            attr.addFlashAttribute("msg","Producto borrado exitosamente");
        }
        */

        return "redirect:/region/list";

    }
}
