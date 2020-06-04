package sw2.clase03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase03.entity.Region;
import sw2.clase03.entity.Territories;
import sw2.clase03.repository.RegionRepository;
import sw2.clase03.repository.TerritoryRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    TerritoryRepository territoryRepository;

    @GetMapping(value = {"/list", ""})
    public String listarProductos(Model model) {

        List<Region> lista = regionRepository.findAll();
        model.addAttribute("regionList", lista);

        return "region/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(@ModelAttribute("region") Region region,
                                   Model model) {

        //List<Categories> listacatego = categoryRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        //model.addAttribute("listacatego",listacatego);
        //model.addAttribute("listasuppli",listasuppli);

        return "region/newFrm";
    }

    @PostMapping("/save")
    public String guardarNuevoProducto(@ModelAttribute("region") @Valid Region region,
                                       BindingResult bindingResult,
                                       RedirectAttributes attr, Model model) {


        if(bindingResult.hasErrors()){

            //List<Categories> listacatego = categoryRepository.findAll();
            //List<Suppliers> listasuppli = supplierRepository.findAll();

            //model.addAttribute("listacatego",listacatego);
            //model.addAttribute("listasuppli",listasuppli);

            return "region/newFrm";

        }else{

            Integer id = region.getRegionid();

            Optional<Region> regi = regionRepository.findById(id);



            if (regi.isPresent()) {
                attr.addFlashAttribute("msg", "Region Actualizada Exitosamente");
            }
            else {
                attr.addFlashAttribute("msg", "Region Creada Exitosamente");
            }

            //System.out.println("este es el id CTM" + products.getProductid());
            //System.out.println("este es el stock CTM" + products.getUnitsinstock());

            regionRepository.save(region);
            return "redirect:/region/list";

        }



    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("region") Region region,
                                      Model model,
                                      @RequestParam("id") int id) {

        Optional<Region> optionalProducts = regionRepository.findById(id);

        //List<Categories> listacatego = categoryRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        if (optionalProducts.isPresent()) {
            region = optionalProducts.get();
            model.addAttribute("region", region);
            //model.addAttribute("listacatego",listacatego);
            //model.addAttribute("listasuppli",listasuppli);

            return "region/newFrm";
        } else {
            return "redirect:/region/list";
        }
    }

    @PostMapping(value = "/buscarPorInicio")
    public String buscarPorInicio(@RequestParam("inicio") String inicio,
                                  Model model){

        //List<Shipper> listaTransportistas = shipperRepository.findByCompanyName(companyname);


        List<Region> lista = regionRepository.buscarTransportistasPorInicioDeNombre(inicio);

        model.addAttribute("regionList",lista);

        return "region/list";
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
