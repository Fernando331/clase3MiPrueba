package sw2.clase03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase03.entity.Categories;
import sw2.clase03.entity.Products;
import sw2.clase03.repository.CategoryRepository;
import sw2.clase03.repository.ProductRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = {"/list", ""})
    public String listarProductos(Model model) {

        List<Categories> lista = categoryRepository.findAll();
        model.addAttribute("categoList", lista);

        return "category/list";
    }
    @PostMapping(value = "/buscarPorInicio")
    public String buscarPorInicio(@RequestParam("inicio") String inicio,
                                  Model model){

        //List<Shipper> listaTransportistas = shipperRepository.findByCompanyName(companyname);


        List<Categories> lista = categoryRepository.buscarTransportistasPorInicioDeNombre(inicio);

        model.addAttribute("categoList",lista);

        return "category/list";
    }
    @GetMapping("/new")
    public String nuevoProductoFrm(@ModelAttribute("categories") Categories categories,
                                   Model model) {


        //List<Categories> listacatego = categoryRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        //model.addAttribute("listacatego",listacatego);
        //model.addAttribute("listasuppli",listasuppli);

        return "category/newFrm";
    }
    @PostMapping("/save")
    public String guardarNuevoProducto(@ModelAttribute("categories") @Valid Categories categories,
                                       BindingResult bindingResult,
                                       RedirectAttributes attr) {


        if(bindingResult.hasErrors()){

            return "category/newFrm";
        }else{

            if (categories.getCategoryid() == null) {
                attr.addFlashAttribute("msg", "Producto Creado Exitosamente");
            }
            else {
                attr.addFlashAttribute("msg", "Producto Actualizado Exitosamente");
            }

            //System.out.println("este es el id CTM" + categories.getCategoryid());
            //System.out.println("este es el stock CTM" + categories.getCategoryname());

            categoryRepository.save(categories);
            return "redirect:/category/list";

        }

    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("categories") Categories categories,
                                      Model model,
                                      @RequestParam("id") int id) {

        Optional<Categories> optionalCategories = categoryRepository.findById(id);

        //List<Categories> listacatego = categoryRepository.findAll();
        //List<Suppliers> listasuppli = supplierRepository.findAll();

        if (optionalCategories.isPresent()) {
            categories = optionalCategories.get();
            model.addAttribute("categories", categories);
            //model.addAttribute("listacatego",listacatego);
            //model.addAttribute("listasuppli",listasuppli);

            return "category/newFrm";
        } else {
            return "redirect:/category/list";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        List<Products> listita = productRepository.buscarSiSePuedeBorrar(id);

        Optional<Categories> optionalProducts = categoryRepository.findById(id);

        //System.out.println("ests es lo q mide la lista" + listita.size());


        if(listita.size()==0){
            if (optionalProducts.isPresent()) {
                categoryRepository.deleteById(id);
                attr.addFlashAttribute("msg","Producto borrado exitosamente");
            }
        }else{
            attr.addFlashAttribute("msgError","Este Producto No Se Puede Borrar");
        }
        /*

        if (optionalProducts.isPresent()) {
            productRepository.deleteById(id);
            attr.addFlashAttribute("msg","Producto borrado exitosamente");
        }
        */

        return "redirect:/category/list";

    }
}
