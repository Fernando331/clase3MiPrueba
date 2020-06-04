package sw2.clase03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sw2.clase03.dto.EmpleadoPaisDto;
import sw2.clase03.dto.EmpledosRegionDto;
import sw2.clase03.entity.Empleado;
import sw2.clase03.repository.EmpleadoRepository;

import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoRepository empleadoRepository;

    @GetMapping(value = {"/listaEmpleadosPorRegion"})
    public String listarPorRegion(Model model) {

        List<EmpledosRegionDto> lista = empleadoRepository.buscarEmpledosPorRegion();
        model.addAttribute("empxregionlist", lista);

        return "empleado/listaEmpleadosPorRegion";
    }

    @GetMapping(value = {"/listaEmpleadosPorPais"})
    public String listarPorPais(Model model) {

        List<EmpleadoPaisDto> lista = empleadoRepository.buscarEmpledosPorPais();
        model.addAttribute("empxpaislist", lista);

        return "empleado/listaEmpleadosPorPais";
    }

    @GetMapping(value = {"/list", ""})
    public String listarProductos(Model model) {

        List<Empleado> lista = empleadoRepository.findAll();
        model.addAttribute("empleadoList", lista);

        List<EmpleadoPaisDto> listaa = empleadoRepository.buscarEmpledosPorPais();
        model.addAttribute("empxpaislist", listaa);

        List<EmpledosRegionDto> listaaa = empleadoRepository.buscarEmpledosPorRegion();
        model.addAttribute("empxregionlist", listaaa);



        return "empleado/list";
    }
}
