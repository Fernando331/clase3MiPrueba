package sw2.clase03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase03.entity.Shipper;
import sw2.clase03.repository.ShipperRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shipper")
public class ShipperController {

    @Autowired
    ShipperRepository shipperRepository;

    @GetMapping(value = {"/list", ""})
    public String listarTransportistas(Model model) {

        List<Shipper> lista = shipperRepository.findAll();
        model.addAttribute("shipperList", lista);

        return "shipper/list";
    }

    @GetMapping("/new")
    public String nuevoTransportistaFrm(@ModelAttribute("shipper") Shipper s) {
        return "shipper/newFrm";
    }

    @PostMapping("/save")
    public String guardarNuevoTransportista(@ModelAttribute("shipper") Shipper shipper,
                                            Model model,
                                            RedirectAttributes attr) {


        System.out.println("este es el name ctm" + shipper.getCompanyname());

        if(shipper.getCompanyname().equals("")){

            model.addAttribute("errorCompany", "El Nombre no puede quedar vacio");
            return "shipper/newFrm";

        }else{


            if (shipper.getShipperId() != 0) {

                attr.addFlashAttribute("msg", "Transportista Actualizado Exitosamente");

            } else {
                attr.addFlashAttribute("msg", "Transportista Creado Exitosamente");
            }

            shipperRepository.save(shipper);
            return "redirect:/shipper/list";

        }
        /*
        if (shipper.getShipperId() != 0) {

            attr.addFlashAttribute("msg", "Transportista Actualizado Exitosamente");

        } else {
            attr.addFlashAttribute("msg", "Transportista Creado Exitosamente");
        }

        shipperRepository.save(shipper);
        return "redirect:/shipper/list";
        */
    }

    @PostMapping(value = "/buscarTransportista")
    public String buscarTransportista(@RequestParam("companyname") String companyname,
                                      Model model){

        //List<Shipper> listaTransportistas = shipperRepository.findByCompanyName(companyname);


        List<Shipper> listaTransportistas = shipperRepository.buscarTransportistasPorNombre(companyname);

        model.addAttribute("shipperList",listaTransportistas);

        return "shipper/list";
    }

    @PostMapping(value = "/buscarPorInicio")
    public String buscarPorInicio(@RequestParam("inicio") String inicio,
                                      Model model){

        //List<Shipper> listaTransportistas = shipperRepository.findByCompanyName(companyname);


        List<Shipper> listaTransportistas = shipperRepository.buscarTransportistasPorInicioDeNombre(inicio,inicio);

        model.addAttribute("shipperList",listaTransportistas);

        return "shipper/list";
    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("shipper") Shipper shipper,
                                      Model model,
                                      @RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            shipper = optShipper.get();
            model.addAttribute("shipper", shipper);
            return "shipper/newFrm";
        } else {
            return "redirect:/shipper/list";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            shipperRepository.deleteById(id);
        }
        return "redirect:/shipper/list";

    }

}

