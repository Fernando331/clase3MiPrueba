package sw2.clase03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase03.entity.*;
import sw2.clase03.repository.CategoryRepository;
import sw2.clase03.repository.OrdenDetailRepository;
import sw2.clase03.repository.ProductRepository;
import sw2.clase03.repository.SupplierRepository;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrdenDetailRepository ordenDetailRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value = {"/list", ""})
    public String listarProductos(Model model, HttpSession session) {

        List<Products> lista = productRepository.findAll();
        model.addAttribute("productList", lista);

        session.setAttribute("SaludoRespetuoso","Hola CTM");

        return "products/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(@ModelAttribute("products") Products products,
                                   Model model, HttpSession session) {

        List<Categories> listacatego = categoryRepository.findAll();
        List<Suppliers> listasuppli = supplierRepository.findAll();

        model.addAttribute("listacatego",listacatego);
        model.addAttribute("listasuppli",listasuppli);

        UsuarioSession usuarioSession = (UsuarioSession) session.getAttribute("usu");

        System.out.println("El usuario es : " + usuarioSession.getNombre() + " " + usuarioSession.getApellido());


        return "products/newFrm";
    }

    @PostMapping("/save")
    public String guardarNuevoProducto(@ModelAttribute("products") @Valid Products products,
                                            BindingResult bindingResult,
                                            RedirectAttributes attr,Model model) {


        if(bindingResult.hasErrors()){

            List<Categories> listacatego = categoryRepository.findAll();
            List<Suppliers> listasuppli = supplierRepository.findAll();

            model.addAttribute("listacatego",listacatego);
            model.addAttribute("listasuppli",listasuppli);

            return "products/newFrm";

        }else{
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

        }

    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("products") Products products,
                                      Model model,
                                      @RequestParam("id") int id) {

        Optional<Products> optionalProducts = productRepository.findById(id);

        List<Categories> listacatego = categoryRepository.findAll();
        List<Suppliers> listasuppli = supplierRepository.findAll();

        if (optionalProducts.isPresent()) {
            products = optionalProducts.get();
            model.addAttribute("products", products);
            model.addAttribute("listacatego",listacatego);
            model.addAttribute("listasuppli",listasuppli);

            return "products/newFrm";
        } else {
            return "redirect:/product/list";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        List<OrdenDetail> listita = ordenDetailRepository.buscarDetalleOrdenPorIdProducto(id);

        Optional<Products> optionalProducts = productRepository.findById(id);

        System.out.println("ests es lo q mide la lista" + listita.size());


        if(listita.size()==0){
            if (optionalProducts.isPresent()) {
                productRepository.deleteById(id);
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

        return "redirect:/product/list";

    }

    @PostMapping(value = "/buscarPorInicio")
    public String buscarPorInicio(@RequestParam("inicio") String inicio,
                                  Model model){

        //List<Shipper> listaTransportistas = shipperRepository.findByCompanyName(companyname);


        List<Products> lista = productRepository.buscarTransportistasPorInicioDeNombre(inicio);

        model.addAttribute("productList",lista);

        return "products/list";
    }

}
