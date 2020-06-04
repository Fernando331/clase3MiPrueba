package sw2.clase03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sw2.clase03.entity.OrdenDetail;
import sw2.clase03.entity.Products;
import sw2.clase03.repository.OrdenDetailRepository;
import sw2.clase03.repository.OrdenRepository;
import sw2.clase03.repository.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/orderdetails")
public class OrderDetailController {

    @Autowired
    OrdenDetailRepository ordenDetailRepository;

    @Autowired
    OrdenRepository ordenRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = {"/list", ""})
    public String listarProductos(Model model) {

        List<OrdenDetail> lista = ordenDetailRepository.findAll();
        model.addAttribute("orderDetailList", lista);

        return "orderdetails/list";
    }


}
